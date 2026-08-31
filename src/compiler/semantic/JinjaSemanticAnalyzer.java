package compiler.semantic;

import compiler.ast.core.AstNode;
import compiler.ast.nodes.TemplateNode;
import compiler.ast.nodes.TextNode;
import compiler.ast.nodes.html.HtmlElementNode;
import compiler.ast.nodes.jinja.*;
import compiler.ast.nodes.python.*;

import java.util.*;

/**
 * التحليل الدلالي لجزء Jinja (القوالب).
 *
 * يكمل جزء Python في {@link SemanticAnalyzer} لتحقيق متطلب المشروع:
 * "معالجة الأخطاء الدلالية في كلا الجزئين".
 *
 * لا يستخدم نمط الـ Visitor لأن بعض عقد Jinja (Subscript / Not / KwArg)
 * تُبنى كعقد مجهولة يُرجع فيها accept() القيمة null، لذلك نجتاز الشجرة
 * يدوياً عبر getChildren().
 *
 * الأخطاء الدلالية المكتشفة (5 أنواع):
 * 1. UNDEFINED_VARIABLE - استخدام متغير غير مُمرَّر من Python ولا معرّف في
 * القالب
 * 2. UNDEFINED_ITERABLE - {% for x in items %} حيث items غير معرّف
 * 3. LOOP_VAR_OUT_OF_SCOPE- استخدام متغير الحلقة خارج نطاق {% for %}
 * 4. UNKNOWN_FILTER - فلتر غير معروف مثل {{ x | wrongfilter }}
 * 5. UNDEFINED_FUNCTION - استدعاء دالة غير معروفة في القالب
 */
public class JinjaSemanticAnalyzer {

    // =========================================================================
    // JinjaSemanticError
    // =========================================================================

    public static class JinjaSemanticError {
        public enum ErrorType {
            UNDEFINED_VARIABLE,
            UNDEFINED_ITERABLE,
            LOOP_VAR_OUT_OF_SCOPE,
            UNKNOWN_FILTER,
            UNDEFINED_FUNCTION
        }

        private final ErrorType type;
        private final String message;
        private final int line;

        public JinjaSemanticError(ErrorType type, String message, int line) {
            this.type = type;
            this.message = message;
            this.line = line;
        }

        public ErrorType getType() {
            return type;
        }

        public String getMessage() {
            return message;
        }

        public int getLine() {
            return line;
        }

        @Override
        public String toString() {
            return String.format("[JINJA SEMANTIC ERROR - %s] Line %d: %s",
                    type.name(), line, message);
        }
    }

    // =========================================================================
    // الحالة الداخلية
    // =========================================================================

    private final List<JinjaSemanticError> errors = new ArrayList<>();

    // المتغيرات المتاحة للقالب (قادمة من Python: globals + render_template kwargs)
    private final Set<String> knownVars = new LinkedHashSet<>();

    // متغيرات الحلقات المعرّفة حالياً (block scope) — تُضاف داخل for وتُزال بعده
    private final Deque<String> loopVarStack = new ArrayDeque<>();

    // كل متغيرات الحلقات التي شوهدت (لكشف الاستخدام خارج النطاق برسالة أدق)
    private final Set<String> allLoopVarsSeen = new HashSet<>();

    // أسماء دائمة التعريف داخل القوالب (Flask / Jinja builtins)
    private static final Set<String> BUILTIN_VARS = new HashSet<>(Arrays.asList(
            "true", "false", "none", "True", "False", "None",
            "loop", "request", "session", "g", "config", "namespace"));

    // الدوال المعروفة داخل القوالب
    private static final Set<String> BUILTIN_FUNCS = new HashSet<>(Arrays.asList(
            "url_for", "range", "len", "super", "dict", "lipsum", "cycler"));

    // الفلاتر المعروفة في Jinja2
    private static final Set<String> KNOWN_FILTERS = new HashSet<>(Arrays.asList(
            "upper", "lower", "capitalize", "title", "trim", "length", "count",
            "default", "round", "int", "float", "string", "safe", "escape", "e",
            "join", "replace", "first", "last", "reverse", "sort", "list",
            "abs", "wordcount", "truncate", "striptags", "urlencode", "format",
            "tojson", "map", "select", "reject", "sum", "min", "max"));

    // =========================================================================
    // Public API
    // =========================================================================

    public JinjaSemanticAnalyzer() {
    }

    /** يضيف اسم متغير متاح للقالب (قادم من Python). */
    public void addKnownVar(String name) {
        if (name != null && !name.isEmpty())
            knownVars.add(name);
    }

    public void addKnownVars(Collection<String> names) {
        if (names != null)
            for (String n : names)
                addKnownVar(n);
    }

    public List<JinjaSemanticError> getErrors() {
        return Collections.unmodifiableList(errors);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public void printErrors() {
        if (errors.isEmpty()) {
            System.out.println("  No Jinja semantic errors found.");
            return;
        }
        System.out.println("  Found " + errors.size() + " Jinja semantic error(s):\n");
        for (JinjaSemanticError e : errors) {
            System.out.println("  " + e);
        }
    }

    /** نقطة الدخول: يحلّل شجرة قالب Jinja كاملة. */
    public void analyze(AstNode templateAst) {
        if (templateAst == null)
            return;
        walk(templateAst);
    }

    // =========================================================================
    // Helpers
    // =========================================================================

    private void addError(JinjaSemanticError.ErrorType type, String msg, int line) {
        errors.add(new JinjaSemanticError(type, msg, line));
    }

    private boolean isDefined(String name) {
        return knownVars.contains(name)
                || loopVarStack.contains(name)
                || BUILTIN_VARS.contains(name);
    }

    // =========================================================================
    // اجتياز البنية (statements / structure)
    // =========================================================================

    private void walk(AstNode node) {
        if (node == null)
            return;

        if (node instanceof JinjaStmtNode) {
            walkStmt((JinjaStmtNode) node);
            return;
        }

        if (node instanceof JinjaExprNode) {
            walkExprNode((JinjaExprNode) node);
            return;
        }

        // TemplateNode / HtmlElementNode / TextNode / غيرها: ندخل على الأبناء.
        // ملاحظة: تعابير Jinja الواردة داخل قيم الـ attributes تُخزَّن كأبناء
        // مباشرة للوسم (Name / AttrAccess / Call ...) دون غلاف JinjaExprNode،
        // فنمرّرها إلى فاحص التعابير كي لا تفلت من التحليل الدلالي
        // مثل: <a href="/products/{{ ghost.id }}">
        for (AstNode child : node.getChildren()) {
            if (isStructuralNode(child)) {
                walk(child);
            } else {
                checkExpr(child);
            }
        }
    }

    /** هل العقدة جزء من بنية القالب (لا تعبير Jinja)؟ */
    private boolean isStructuralNode(AstNode node) {
        return node instanceof TemplateNode
                || node instanceof TextNode
                || node instanceof HtmlElementNode
                || node instanceof JinjaStmtNode
                || node instanceof JinjaExprNode;
    }

    private void walkStmt(JinjaStmtNode node) {
        List<AstNode> children = node.getChildren();
        if (children.isEmpty())
            return;

        String kind = node.getKind();

        // ── FOR ──────────────────────────────────────────────────────────────
        if ("for".equals(kind)) {
            String loopVar = null;
            AstNode first = children.get(0);
            if (first instanceof NameNode)
                loopVar = ((NameNode) first).getName();

            // الـ iterable هو الطفل الثاني
            if (children.size() >= 2) {
                AstNode iterable = children.get(1);
                checkIterable(iterable);
            }

            // ندخل نطاق الحلقة
            if (loopVar != null) {
                loopVarStack.push(loopVar);
                allLoopVarsSeen.add(loopVar);
            }
            for (int i = 2; i < children.size(); i++) {
                walk(children.get(i));
            }
            if (loopVar != null) {
                loopVarStack.pop();
            }
            return;
        }

        // ── IF / ELIF ──────────────────────────────────────────────────────
        if ("if".equals(kind) || "elif".equals(kind)) {
            // الطفل الأول هو الشرط
            checkExpr(children.get(0));
            for (int i = 1; i < children.size(); i++) {
                walk(children.get(i));
            }
            return;
        }

        // ── ELSE ─────────────────────────────────────────────────────────────
        if ("else".equals(kind)) {
            for (AstNode child : children) {
                walk(child);
            }
            return;
        }

        // نوع غير معروف: احتياطياً ندخل على كل الأبناء
        for (AstNode child : children) {
            walk(child);
        }
    }

    private void walkExprNode(JinjaExprNode node) {
        for (AstNode child : node.getChildren()) {
            // علامات الفلاتر تُخزَّن كـ NameNode باسم "filter:xxx"
            if (child instanceof NameNode
                    && ((NameNode) child).getName().startsWith("filter:")) {
                String filter = ((NameNode) child).getName().substring("filter:".length());
                if (!KNOWN_FILTERS.contains(filter)) {
                    addError(JinjaSemanticError.ErrorType.UNKNOWN_FILTER,
                            "Unknown filter '" + filter + "'", child.getLine());
                }
                // وسائط الفلتر (مثل | default(fallback)) تُفحص كتعابير أيضاً
                for (AstNode arg : child.getChildren()) {
                    checkExpr(arg);
                }
            } else {
                checkExpr(child);
            }
        }
    }

    // =========================================================================
    // فحص التعابير (expressions)
    // =========================================================================

    private void checkIterable(AstNode node) {
        if (node instanceof NameNode) {
            String name = ((NameNode) node).getName();
            if (!isDefined(name) && !BUILTIN_FUNCS.contains(name)) {
                addError(JinjaSemanticError.ErrorType.UNDEFINED_ITERABLE,
                        "Cannot loop over '" + name + "': it is not passed from Python",
                        node.getLine());
            }
            return;
        }
        // iterable قد يكون استدعاء دالة مثل range(...) أو تعبير آخر
        checkExpr(node);
    }

    private void checkExpr(AstNode node) {
        if (node == null)
            return;

        // NameNode: متغير
        if (node instanceof NameNode) {
            String name = ((NameNode) node).getName();
            if (name.startsWith("filter:"))
                return;
            if (isDefined(name))
                return;
            // اسم معروف كدالة لكنه استُخدم كمتغير — نتجاهله (نادر)
            if (BUILTIN_FUNCS.contains(name))
                return;
            // كان متغير حلقة لكنه خارج نطاقه الآن
            if (allLoopVarsSeen.contains(name)) {
                addError(JinjaSemanticError.ErrorType.LOOP_VAR_OUT_OF_SCOPE,
                        "Loop variable '" + name + "' used outside its {% for %} block",
                        node.getLine());
            } else {
                addError(JinjaSemanticError.ErrorType.UNDEFINED_VARIABLE,
                        "Variable '" + name + "' is used but not defined in the template context",
                        node.getLine());
            }
            return;
        }

        // AttrAccess: obj.attr → نفحص obj فقط (attr اسم حقل وليس متغيراً)
        if (node instanceof AttrAccessNode) {
            List<AstNode> ch = node.getChildren();
            if (!ch.isEmpty())
                checkExpr(ch.get(0));
            return;
        }

        // CallNode: استدعاء دالة
        if (node instanceof CallNode) {
            checkCall((CallNode) node);
            return;
        }

        // BinOpNode: مقارنة / and / or / +
        if (node instanceof BinOpNode) {
            for (AstNode ch : node.getChildren())
                checkExpr(ch);
            return;
        }

        // القيم الحرفية: لا شيء نفحصه
        if (node instanceof StringNode || node instanceof NumberNode)
            return;

        // العقد المجهولة (Subscript / Not / KwArg): نميّزها بالاسم
        String nodeName = node.getNodeName();
        if (nodeName != null) {
            if (nodeName.equals("Subscript")) {
                List<AstNode> ch = node.getChildren();
                if (ch.size() >= 1)
                    checkExpr(ch.get(0)); // الكائن
                if (ch.size() >= 2)
                    checkExpr(ch.get(1)); // الفهرس (قد يكون متغيراً)
                return;
            }
            if (nodeName.equals("Not")) {
                for (AstNode ch : node.getChildren())
                    checkExpr(ch);
                return;
            }
            if (nodeName.startsWith("KwArg")) {
                for (AstNode ch : node.getChildren())
                    checkExpr(ch);
                return;
            }
        }

        // احتياطياً: ندخل على الأبناء
        for (AstNode ch : node.getChildren())
            checkExpr(ch);
    }

    private void checkCall(CallNode node) {
        List<AstNode> children = node.getChildren();
        if (children.isEmpty())
            return;

        AstNode funcNode = children.get(0);
        String funcName = null;
        if (funcNode instanceof NameNode) {
            funcName = ((NameNode) funcNode).getName();
        } else if (funcNode instanceof AttrAccessNode) {
            // استدعاء توصيفي مثل obj.method() — نفحص الكائن فقط
            List<AstNode> ac = funcNode.getChildren();
            if (!ac.isEmpty())
                checkExpr(ac.get(0));
        }

        if (funcName != null
                && !BUILTIN_FUNCS.contains(funcName)
                && !isDefined(funcName)) {
            addError(JinjaSemanticError.ErrorType.UNDEFINED_FUNCTION,
                    "Function '" + funcName + "' is called but not defined in the template context",
                    node.getLine());
        }

        // فحص الوسائط
        for (int i = 1; i < children.size(); i++) {
            checkExpr(children.get(i));
        }
    }
}
