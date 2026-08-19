package compiler.codegen;

import compiler.ast.core.AstNode;
import compiler.ast.nodes.*;
import compiler.ast.nodes.html.HtmlElementNode;
import compiler.ast.nodes.jinja.*;
import compiler.ast.nodes.python.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * AST-Based Code Generator
 * يستخرج البيانات من Python AST ويحقنها في Jinja AST
 * لينتج Static HTML Files
 */
public class CodeGenerator {

    // =========================================================================
    // البيانات المستخرجة من Python AST
    // =========================================================================

    // context عام: اسم المتغير → قيمته
    private final Map<String, Object> globalContext = new LinkedHashMap<>();

    // =========================================================================
    // الخطوة 1: استخراج البيانات من Python AST
    // =========================================================================

    /**
     * يمشي على Python AST ويستخرج البيانات (products, variables, إلخ)
     */
    public void extractFromPythonAST(AstNode pythonAst) {
        if (pythonAst == null)
            return;

        for (AstNode child : pythonAst.getChildren()) {
            if (child instanceof AssignNode) {
                extractAssignment((AssignNode) child);
            }
        }
    }

    private void extractAssignment(AssignNode node) {
        List<AstNode> children = node.getChildren();
        if (children.size() < 2)
            return;

        AstNode target = children.get(0);
        AstNode value = children.get(1);

        if (target instanceof NameNode) {
            String varName = ((NameNode) target).getName();
            Object val = evaluatePythonExpr(value);
            if (val != null) {
                globalContext.put(varName, val);
            }
        }
    }

    /**
     * يقيّم تعبير Python ويرجع قيمته كـ Java Object
     */
    private Object evaluatePythonExpr(AstNode node) {
        if (node == null)
            return null;

        // String literal
        if (node instanceof StringNode) {
            return ((StringNode) node).getValue();
        }

        // Number literal
        if (node instanceof NumberNode) {
            String val = ((NumberNode) node).getValue();
            try {
                if (val.contains("."))
                    return Double.parseDouble(val);
                else
                    return Integer.parseInt(val);
            } catch (NumberFormatException e) {
                return val;
            }
        }

        // Name reference
        if (node instanceof NameNode) {
            String name = ((NameNode) node).getName();
            if (name.equals("True"))
                return Boolean.TRUE;
            if (name.equals("False"))
                return Boolean.FALSE;
            if (name.equals("None"))
                return null;
            return globalContext.getOrDefault(name, name);
        }

        // List
        if (node instanceof ListNode) {
            List<Object> list = new ArrayList<>();
            for (AstNode item : node.getChildren()) {
                Object val = evaluatePythonExpr(item);
                list.add(val);
            }
            return list;
        }

        // Dict
        if (node instanceof DictNode) {
            return extractDict(node);
        }

        // BinOp مثل len(products) + 1
        if (node instanceof BinOpNode) {
            return evaluateBinOp((BinOpNode) node);
        }

        return null;
    }

    /**
     * يستخرج Dict من AST
     * DictNode أبناؤه: key1, val1, key2, val2, ...
     */
    private Map<String, Object> extractDict(AstNode dictNode) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<AstNode> children = dictNode.getChildren();

        // الأبناء: key, value, key, value, ...
        for (int i = 0; i + 1 < children.size(); i += 2) {
            AstNode keyNode = children.get(i);
            AstNode valNode = children.get(i + 1);

            String key = null;
            if (keyNode instanceof StringNode) {
                key = ((StringNode) keyNode).getValue();
            } else if (keyNode instanceof NameNode) {
                key = ((NameNode) keyNode).getName();
            }

            if (key != null) {
                Object val = evaluatePythonExpr(valNode);
                map.put(key, val);
            }
        }
        return map;
    }

    private Object evaluateBinOp(BinOpNode node) {
        List<AstNode> children = node.getChildren();
        if (children.size() < 2)
            return null;

        Object left = evaluatePythonExpr(children.get(0));
        Object right = evaluatePythonExpr(children.get(1));
        String op = node.getOp();

        if (left instanceof Integer && right instanceof Integer) {
            int l = (Integer) left;
            int r = (Integer) right;
            switch (op) {
                case "+":
                    return l + r;
                case "-":
                    return l - r;
                case "*":
                    return l * r;
                case "/":
                    return l / r;
            }
        }
        return null;
    }

    // =========================================================================
    // الخطوة 2 & 3: تقييم Jinja AST وتوليد HTML
    // =========================================================================

    /**
     * يقيّم Template AST ويولّد HTML string
     */
    public String generateHTML(AstNode templateAst, Map<String, Object> context) {

        StringBuilder sb = new StringBuilder();

        evaluateNode(templateAst, context, sb);

        return sb.toString();
    }

    /**
     * يقيّم node واحد ويضيف الناتج للـ StringBuilder
     */
    private void evaluateNode(AstNode node, Map<String, Object> context, StringBuilder sb) {
        if (node == null)
            return;

        // ── TemplateNode ──────────────────────────────────────────────────────
        if (node instanceof TemplateNode) {
            for (AstNode child : node.getChildren()) {
                evaluateNode(child, context, sb);
            }
            return;
        }

        // ── TextNode ──────────────────────────────────────────────────────────
        if (node instanceof TextNode) {
            sb.append(((TextNode) node).getText());
            return;
        }

        // ── HtmlElementNode ───────────────────────────────────────────────────
        if (node instanceof HtmlElementNode) {
            evaluateHtmlElement((HtmlElementNode) node, context, sb);
            return;
        }

        // ── JinjaExprNode: {{ expr }} ─────────────────────────────────────────
        if (node instanceof JinjaExprNode) {
            List<AstNode> children = node.getChildren();
            Object val = evaluateJinjaExpr(
                    children.isEmpty() ? null : children.get(0), context);

            // تطبيق الفلاتر بالترتيب: {{ name | trim | upper }}
            boolean safe = false;
            for (int i = 1; i < children.size(); i++) {
                AstNode mark = children.get(i);
                if (!(mark instanceof NameNode))
                    continue;
                String markName = ((NameNode) mark).getName();
                if (!markName.startsWith("filter:"))
                    continue;
                String filterName = markName.substring("filter:".length());
                if (filterName.equals("safe")) {
                    safe = true; // | safe يمنع الترميز (escaping)
                    continue;
                }
                val = applyFilter(filterName, val, filterArgs(mark, context));
            }

            String text = (val != null) ? val.toString() : "";
            // نرمّز القيم المُحقَّنة (كما يفعل Jinja2 تلقائياً) حتى لا يفسد
            // اسمُ منتجٍ يحتوي < أو & أو " صفحةَ HTML الناتجة
            sb.append(safe ? text : escapeHtml(text));
            return;
        }

        // ── JinjaStmtNode: {% for %} أو {% if %} ──────────────────────────────
        if (node instanceof JinjaStmtNode) {
            evaluateJinjaStmt(node, context, sb);
            return;
        }

        // ── غير ذلك: زيارة الأبناء ───────────────────────────────────────────
        for (AstNode child : node.getChildren()) {
            evaluateNode(child, context, sb);
        }
    }

    // ── HTML Element ──────────────────────────────────────────────────────────

    private static final Set<String> VOID_ELEMENTS = new HashSet<>(Arrays.asList(
            "area", "base", "br", "col", "embed", "hr", "img", "input",
            "link", "meta", "param", "source", "track", "wbr"));

    private void evaluateHtmlElement(HtmlElementNode node,
            Map<String, Object> context,
            StringBuilder sb) {
        String tag = node.getTagName();

        // Opening tag
        sb.append("<").append(tag);

        // Attributes
        for (Map.Entry<String, String> attr : node.getAttributes().entrySet()) {
            String attrName = attr.getKey();
            String attrVal = attr.getValue();

            // إذا القيمة تحتوي تعابير Jinja نستبدل كل تعبير بقيمته،
            // وإلا نكتفي بترميز علامات التنصيص كي تبقى الـ HTML سليمة
            if (attrVal.contains(ATTR_PLACEHOLDER)) {
                attrVal = resolveAttrValue(attrVal, node, attrName, context);
            } else {
                attrVal = attrVal.replace("\"", "&quot;");
            }

            sb.append(" ").append(attrName).append("=\"").append(attrVal).append("\"");
        }

        // Self-closing أو Void
        if (VOID_ELEMENTS.contains(tag.toLowerCase())) {
            sb.append(">\n");
            return;
        }

        sb.append(">");

        // Children - نتجاهل JinjaExpr nodes التي هي في attributes
        for (AstNode child : node.getChildren()) {
            if (child instanceof JinjaExprNode || child instanceof JinjaStmtNode
                    || child instanceof HtmlElementNode || child instanceof TextNode) {
                evaluateNode(child, context, sb);
            }
        }

        // Closing tag
        sb.append("</").append(tag).append(">\n");
    }

    /** العلامة النائبة التي يضعها بانيُ الشجرة مكان كل تعبير Jinja داخل attribute */
    private static final String ATTR_PLACEHOLDER = "{{...}}";

    /**
     * يحل قيمة attribute تحتوي تعابير Jinja.
     *
     * كل علامة نائبة تُستبدل بتعبيرها الخاص وبالترتيب، لأن الوسم قد يحتوي
     * أكثر من attribute ديناميكي
     * (مثل &lt;img src="{{ p.image }}" alt="{{ p.name }}"&gt;).
     */
    private String resolveAttrValue(String attrVal,
            HtmlElementNode node,
            String attrName,
            Map<String, Object> context) {

        List<AstNode> exprs = node.getAttributeExprs(attrName);

        StringBuilder out = new StringBuilder();
        int exprIndex = 0;
        int from = 0;
        int at;
        while ((at = attrVal.indexOf(ATTR_PLACEHOLDER, from)) >= 0) {
            out.append(attrVal, from, at);

            String value = "#"; // تعبير غير معروف: قيمة محيّدة
            if (exprIndex < exprs.size()) {
                Object val = evaluateJinjaExpr(exprs.get(exprIndex), context);
                value = (val != null) ? val.toString() : "";
            }
            out.append(escapeHtml(value));

            exprIndex++;
            from = at + ATTR_PLACEHOLDER.length();
        }
        out.append(attrVal.substring(from));

        return out.toString();
    }

    // ── Jinja Statement ───────────────────────────────────────────────────────

    private void evaluateJinjaStmt(AstNode node,
            Map<String, Object> context,
            StringBuilder sb) {
        List<AstNode> children = node.getChildren();
        if (children.isEmpty())
            return;

        // نوع التعليمة مخزَّن في العقدة نفسها (for / if / elif / else)،
        // ولا يجوز استنتاجه من شكل الأبناء: {% if products %} أول أبنائها
        // اسم أيضاً، فكانت تُعالَج خطأً كحلقة for ولا تُولَّد أبداً
        String kind = (node instanceof JinjaStmtNode)
                ? ((JinjaStmtNode) node).getKind()
                : "";

        if ("for".equals(kind)) {
            evaluateForStmt(children, context, sb);
            return;
        }

        if ("if".equals(kind) || "elif".equals(kind)) {
            evaluateIfStmt(children, context, sb);
            return;
        }

        if ("else".equals(kind)) {
            for (AstNode child : children) {
                evaluateNode(child, context, sb);
            }
            return;
        }

        // نوع غير معروف: احتياطياً نولّد الأبناء
        for (AstNode child : children) {
            evaluateNode(child, context, sb);
        }
    }

    /** {% for var in iterable %} ... {% endfor %} */
    private void evaluateForStmt(List<AstNode> children,
            Map<String, Object> context,
            StringBuilder sb) {
        if (children.size() < 2 || !(children.get(0) instanceof NameNode))
            return;

        String loopVar = ((NameNode) children.get(0)).getName();
        Object iterObj = evaluateJinjaExpr(children.get(1), context);

        List<Object> items = toIterableList(iterObj);
        int total = items.size();

        for (int index = 0; index < total; index++) {
            // نبني context جديد للـ iteration
            Map<String, Object> loopContext = new HashMap<>(context);
            loopContext.put(loopVar, items.get(index));
            loopContext.put("loop", loopInfo(index, total));

            // نولّد المحتوى لكل عنصر
            for (int i = 2; i < children.size(); i++) {
                evaluateNode(children.get(i), loopContext, sb);
            }
        }
    }

    /** يحوّل ناتج التقييم إلى قائمة قابلة للمرور (list / dict / نص) */
    private List<Object> toIterableList(Object iterObj) {
        if (iterObj instanceof List) {
            return new ArrayList<>((List<?>) iterObj);
        }
        if (iterObj instanceof Map) {
            // Jinja يمرّ على مفاتيح القاموس
            return new ArrayList<>(((Map<?, ?>) iterObj).keySet());
        }
        if (iterObj instanceof Iterable) {
            List<Object> list = new ArrayList<>();
            for (Object o : (Iterable<?>) iterObj)
                list.add(o);
            return list;
        }
        return Collections.emptyList();
    }

    /** متغيّر loop المتاح داخل الحلقات: loop.index / loop.first / ... */
    private Map<String, Object> loopInfo(int index, int total) {
        Map<String, Object> loop = new LinkedHashMap<>();
        loop.put("index", index + 1);
        loop.put("index0", index);
        loop.put("revindex", total - index);
        loop.put("revindex0", total - index - 1);
        loop.put("first", index == 0);
        loop.put("last", index == total - 1);
        loop.put("length", total);
        return loop;
    }

    /**
     * {% if %} / {% elif %} / {% else %}
     *
     * الأبناء: [الشرط, محتوى الجسم..., عقد elif/else].
     * نميّز عقد elif/else بنوعها (kind) لا بصنفها، لأن الجسم نفسه قد يحتوي
     * تعليمات Jinja متداخلة يجب توليدها.
     */
    private void evaluateIfStmt(List<AstNode> children,
            Map<String, Object> context,
            StringBuilder sb) {

        List<AstNode> body = new ArrayList<>();
        List<JinjaStmtNode> clauses = new ArrayList<>();

        for (int i = 1; i < children.size(); i++) {
            AstNode child = children.get(i);
            if (child instanceof JinjaStmtNode) {
                String k = ((JinjaStmtNode) child).getKind();
                if ("elif".equals(k) || "else".equals(k)) {
                    clauses.add((JinjaStmtNode) child);
                    continue;
                }
            }
            body.add(child);
        }

        if (isTruthy(evaluateJinjaExpr(children.get(0), context))) {
            for (AstNode child : body) {
                evaluateNode(child, context, sb);
            }
            return;
        }

        // الشرط غير محقّق: نجرّب elif بالترتيب ثم else
        for (JinjaStmtNode clause : clauses) {
            List<AstNode> cc = clause.getChildren();

            if ("elif".equals(clause.getKind())) {
                if (cc.isEmpty())
                    continue;
                if (!isTruthy(evaluateJinjaExpr(cc.get(0), context)))
                    continue;
                for (int i = 1; i < cc.size(); i++) {
                    evaluateNode(cc.get(i), context, sb);
                }
                return;
            }

            // else
            for (AstNode child : cc) {
                evaluateNode(child, context, sb);
            }
            return;
        }
    }

    // ── Jinja Expression Evaluator ────────────────────────────────────────────

    @SuppressWarnings("unchecked")
    private Object evaluateJinjaExpr(AstNode node, Map<String, Object> context) {

        if (node == null)
            return "";

        // ⭐ CallNode أولاً - BEFORE NameNode
        if (node instanceof CallNode) {

            return evaluateJinjaCall((CallNode) node, context);
        }

        // Name: ابحث في context
        if (node instanceof NameNode) {
            String name = ((NameNode) node).getName();
            return context.getOrDefault(name, globalContext.getOrDefault(name, ""));
        }

        // String literal
        if (node instanceof StringNode) {
            return ((StringNode) node).getValue();
        }

        // Number literal → قيمة رقمية (لتعمل الحسابات والفلاتر عليها)
        if (node instanceof NumberNode) {
            return parseNumber(((NumberNode) node).getValue());
        }

        // AttrAccess: product.name
        if (node instanceof AttrAccessNode) {
            List<AstNode> children = node.getChildren();
            if (children.size() < 2)
                return "";

            Object obj = evaluateJinjaExpr(children.get(0), context);
            String attr = "";
            if (children.get(1) instanceof NameNode) {
                attr = ((NameNode) children.get(1)).getName();
            }

            if (obj instanceof Map) {
                Map<String, Object> map = (Map<String, Object>) obj;
                return map.getOrDefault(attr, "");
            }
            return "";
        }

        // BinOp: مقارنات مثل product.id == pid
        if (node instanceof BinOpNode) {
            return evaluateJinjaBinOp((BinOpNode) node, context);
        }

        // Not: {% if not products %}
        String nodeName = node.getNodeName();
        if (nodeName != null && nodeName.equals("Not")) {
            List<AstNode> children = node.getChildren();
            if (children.isEmpty())
                return true;
            return !isTruthy(evaluateJinjaExpr(children.get(0), context));
        }

        // Subscript: product["id"]
        if (nodeName != null && nodeName.equals("Subscript")) {
            List<AstNode> children = node.getChildren();
            if (children.size() < 2)
                return "";
            Object obj = evaluateJinjaExpr(children.get(0), context);
            Object key = evaluateJinjaExpr(children.get(1), context);
            if (obj instanceof Map && key != null) {
                return ((Map<String, Object>) obj)
                        .getOrDefault(key.toString(), "");
            }
            return "";
        }

        return "";
    }

    private Object evaluateJinjaBinOp(BinOpNode node, Map<String, Object> context) {
        List<AstNode> children = node.getChildren();
        if (children.size() < 2)
            return false;

        Object left = evaluateJinjaExpr(children.get(0), context);
        Object right = evaluateJinjaExpr(children.get(1), context);
        String op = node.getOp();

        switch (op) {
            case "==":
                return Objects.equals(left, right)
                        || (left != null && right != null
                                && left.toString().equals(right.toString()));
            case "!=":
                return !Objects.equals(left, right);
            case ">":
                return compareNumbers(left, right) > 0;
            case "<":
                return compareNumbers(left, right) < 0;
            case ">=":
                return compareNumbers(left, right) >= 0;
            case "<=":
                return compareNumbers(left, right) <= 0;
            case "and":
                return isTruthy(left) && isTruthy(right);
            case "or":
                return isTruthy(left) || isTruthy(right);
            case "+":
                if (left instanceof Integer && right instanceof Integer)
                    return (Integer) left + (Integer) right;
                return String.valueOf(left) + String.valueOf(right);
            default:
                return false;
        }
    }

    private int compareNumbers(Object a, Object b) {
        try {
            double da = Double.parseDouble(String.valueOf(a));
            double db = Double.parseDouble(String.valueOf(b));
            return Double.compare(da, db);
        } catch (Exception e) {
            return 0;
        }
    }

    private Object evaluateJinjaCall(CallNode node, Map<String, Object> context) {

        List<AstNode> children = node.getChildren();
        if (children.isEmpty())
            return "";

        AstNode funcNode = children.get(0);

        String funcName = "";

        if (funcNode instanceof NameNode) {
            funcName = ((NameNode) funcNode).getName();

        }
        // ⭐ أضف هذا - للـ method calls مثل app.route()
        else if (funcNode instanceof AttrAccessNode) {

            List<AstNode> attrChildren = funcNode.getChildren();
            if (attrChildren.size() >= 2 && attrChildren.get(1) instanceof NameNode) {
                funcName = ((NameNode) attrChildren.get(1)).getName();

            }
        }

        // url_for('static', filename='css/style.css')
        if (funcName.equals("url_for")) {
            if (children.size() >= 2) {
                Object endpoint = evaluateJinjaExpr(children.get(1), context);

                if ("static".equals(String.valueOf(endpoint))) {
                    for (int i = 2; i < children.size(); i++) {
                        AstNode arg = children.get(i);
                        String argName = arg.getNodeName();
                        if (argName != null && argName.startsWith("KwArg:filename")) {
                            Object val = evaluateJinjaExpr(
                                    arg.getChildren().isEmpty() ? null
                                            : arg.getChildren().get(0),
                                    context);
                            return "/static/" + val;
                        }
                    }
                    return "/static/";
                }
                return "/" + endpoint;
            }
            return "#";
        }

        return "";
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    private boolean isTruthy(Object val) {
        if (val == null)
            return false;
        if (val instanceof Boolean)
            return (Boolean) val;
        if (val instanceof Number)
            return ((Number) val).doubleValue() != 0;
        if (val instanceof String)
            return !((String) val).isEmpty();
        if (val instanceof List)
            return !((List<?>) val).isEmpty();
        if (val instanceof Map)
            return !((Map<?, ?>) val).isEmpty();
        return true;
    }

    /** يحوّل نصاً رقمياً إلى Integer أو Double (أو يعيده كما هو إذا فشل) */
    private Object parseNumber(String raw) {
        if (raw == null)
            return null;
        try {
            return raw.contains(".")
                    ? (Object) Double.valueOf(raw)
                    : (Object) Integer.valueOf(raw);
        } catch (NumberFormatException e) {
            return raw;
        }
    }

    // ── الفلاتر: {{ value | filter }} ─────────────────────────────────────────

    /** يقيّم وسائط الفلتر المخزَّنة كأبناء لعلامة الفلتر */
    private List<Object> filterArgs(AstNode filterMark, Map<String, Object> context) {
        List<Object> args = new ArrayList<>();
        for (AstNode arg : filterMark.getChildren()) {
            args.add(evaluateJinjaExpr(arg, context));
        }
        return args;
    }

    /**
     * يطبّق فلتر Jinja على قيمة.
     * الفلاتر غير المعروفة تُترك القيمة كما هي (المحلل الدلالي هو من يبلّغ عنها).
     */
    private Object applyFilter(String name, Object value, List<Object> args) {
        String text = (value != null) ? String.valueOf(value) : "";
        Object arg0 = args.isEmpty() ? null : args.get(0);

        switch (name) {
            case "upper":
                return text.toUpperCase();
            case "lower":
                return text.toLowerCase();
            case "capitalize":
                return text.isEmpty()
                        ? text
                        : Character.toUpperCase(text.charAt(0)) + text.substring(1).toLowerCase();
            case "title": {
                StringBuilder out = new StringBuilder();
                boolean startOfWord = true;
                for (char c : text.toCharArray()) {
                    out.append(startOfWord ? Character.toUpperCase(c) : Character.toLowerCase(c));
                    startOfWord = !Character.isLetterOrDigit(c);
                }
                return out.toString();
            }
            case "trim":
                return text.trim();
            case "length":
            case "count":
                if (value instanceof List)
                    return ((List<?>) value).size();
                if (value instanceof Map)
                    return ((Map<?, ?>) value).size();
                return text.length();
            case "default":
                return isTruthy(value) ? value : (arg0 != null ? arg0 : "");
            case "int": {
                Object n = toNumber(value);
                return (n != null) ? ((Number) n).intValue() : 0;
            }
            case "float": {
                Object n = toNumber(value);
                return (n != null) ? ((Number) n).doubleValue() : 0.0d;
            }
            case "round": {
                Object n = toNumber(value);
                if (n == null)
                    return value;
                int digits = (arg0 instanceof Number) ? ((Number) arg0).intValue() : 0;
                double factor = Math.pow(10, digits);
                double rounded = Math.round(((Number) n).doubleValue() * factor) / factor;
                return (digits == 0) ? (Object) (long) rounded : (Object) rounded;
            }
            case "abs": {
                Object n = toNumber(value);
                return (n != null) ? Math.abs(((Number) n).doubleValue()) : value;
            }
            case "sum": {
                double total = 0;
                if (value instanceof List) {
                    for (Object item : (List<?>) value) {
                        Object n = toNumber(item);
                        if (n != null)
                            total += ((Number) n).doubleValue();
                    }
                }
                return (total == Math.rint(total)) ? (Object) (long) total : (Object) total;
            }
            case "string":
                return text;
            case "escape":
            case "e":
                return escapeHtml(text);
            case "striptags":
                return text.replaceAll("<[^>]*>", "");
            case "replace":
                if (args.size() >= 2) {
                    return text.replace(String.valueOf(args.get(0)),
                            String.valueOf(args.get(1)));
                }
                return text;
            case "truncate": {
                int limit = (arg0 instanceof Number) ? ((Number) arg0).intValue() : 255;
                return (text.length() <= limit) ? text : text.substring(0, limit) + "...";
            }
            case "join": {
                String sep = (arg0 != null) ? String.valueOf(arg0) : "";
                if (!(value instanceof List))
                    return text;
                StringBuilder out = new StringBuilder();
                List<?> items = (List<?>) value;
                for (int i = 0; i < items.size(); i++) {
                    if (i > 0)
                        out.append(sep);
                    out.append(String.valueOf(items.get(i)));
                }
                return out.toString();
            }
            case "first":
                if (value instanceof List && !((List<?>) value).isEmpty())
                    return ((List<?>) value).get(0);
                return text.isEmpty() ? "" : text.substring(0, 1);
            case "last":
                if (value instanceof List && !((List<?>) value).isEmpty()) {
                    List<?> items = (List<?>) value;
                    return items.get(items.size() - 1);
                }
                return text.isEmpty() ? "" : text.substring(text.length() - 1);
            case "reverse": {
                if (value instanceof List) {
                    List<Object> items = new ArrayList<>((List<?>) value);
                    Collections.reverse(items);
                    return items;
                }
                return new StringBuilder(text).reverse().toString();
            }
            case "list":
                return (value instanceof List) ? value : toIterableList(value);
            case "sort": {
                if (!(value instanceof List))
                    return value;
                List<Object> items = new ArrayList<>((List<?>) value);
                items.sort(Comparator.comparing(o -> String.valueOf(o)));
                return items;
            }
            default:
                return value; // فلتر غير مدعوم: القيمة كما هي
        }
    }

    /** يحوّل قيمة إلى Number إن أمكن، وإلا null */
    private Object toNumber(Object value) {
        if (value instanceof Number)
            return value;
        try {
            String s = String.valueOf(value);
            return s.contains(".") ? (Object) Double.valueOf(s) : (Object) Integer.valueOf(s);
        } catch (Exception e) {
            return null;
        }
    }

    /** ترميز محارف HTML الخاصة (كالسلوك الافتراضي في Jinja2) */
    private String escapeHtml(String text) {
        if (text == null || text.isEmpty())
            return "";
        return text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }

    // =========================================================================
    // Public: توليد ملفات HTML
    // =========================================================================

    /**
     * يولّد HTML لقالب واحد
     */
    public String generateForTemplate(AstNode templateAst,
            String templateName,
            Map<String, Object> extraContext) {
        Map<String, Object> context = new HashMap<>(globalContext);
        if (extraContext != null)
            context.putAll(extraContext);

        String html = generateHTML(templateAst, context);

        // إضافة DOCTYPE إذا لم يكن موجوداً
        if (!html.startsWith("<!DOCTYPE")) {
            html = "<!DOCTYPE html>\n" + html;
        }
        return html;
    }

    /**
     * يحفظ HTML في ملف
     */
    public void saveToFile(String html, String outputPath) throws IOException {
        Path path = Paths.get(outputPath);
        Files.createDirectories(path.getParent());
        Files.writeString(path, html);
        System.out.println("  Generated: " + outputPath);
    }

    // =========================================================================
    // Getters
    // =========================================================================

    public Map<String, Object> getGlobalContext() {
        return Collections.unmodifiableMap(globalContext);
    }
}
