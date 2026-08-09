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

            Object val = evaluateJinjaExpr(
                    node.getChildren().isEmpty() ? null : node.getChildren().get(0),
                    context);
            sb.append(val != null ? val.toString() : "");
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

            // إذا القيمة تحتوي {{dynamic}} - نبحث عن JinjaExpr في الأبناء
            if (attrVal.contains("{{")) {
                attrVal = resolveAttrValue(attrVal, node, context);
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

    /**
     * يحل قيمة attribute التي تحتوي {{dynamic}}
     */
    private String resolveAttrValue(String attrVal,
            HtmlElementNode node,
            Map<String, Object> context) {

        // نبحث عن CallNode أو JinjaExpr في الأبناء
        for (AstNode child : node.getChildren()) {
            // ⭐ إذا كان CallNode مباشرة
            if (child instanceof CallNode) {
                Object val = evaluateJinjaCall((CallNode) child, context);
                if (val != null && !val.toString().isEmpty()) {
                    return attrVal.replace("{{dynamic}}", val.toString())
                            .replace("{{...}}", val.toString());
                }
            }
            // أو JinjaExprNode
            else if (child instanceof JinjaExprNode) {
                Object val = evaluateJinjaExpr(
                        child.getChildren().isEmpty() ? null : child.getChildren().get(0),
                        context);
                if (val != null) {
                    return attrVal.replace("{{dynamic}}", val.toString())
                            .replace("{{...}}", val.toString());
                }
            }
            // ⭐ أو AttrAccessNode مباشرة (مثل product.id)
            else if (child instanceof AttrAccessNode) {
                Object val = evaluateJinjaExpr(child, context);
                if (val != null) {
                    return attrVal.replace("{{dynamic}}", val.toString())
                            .replace("{{...}}", val.toString());
                }
            }
        }

        return attrVal.replace("{{...}}", "#");
    }

    // ── Jinja Statement ───────────────────────────────────────────────────────

    @SuppressWarnings("unchecked")
    private void evaluateJinjaStmt(AstNode node,
            Map<String, Object> context,
            StringBuilder sb) {
        List<AstNode> children = node.getChildren();
        if (children.isEmpty())
            return;

        AstNode first = children.get(0);

        // ── FOR loop ──────────────────────────────────────────────────────────
        // بنية: [NameNode(var), expr(iterable), content...]
        if (first instanceof NameNode && children.size() >= 2) {
            String loopVar = ((NameNode) first).getName();
            AstNode iterable = children.get(1);

            Object iterObj = evaluateJinjaExpr(iterable, context);

            if (iterObj instanceof List) {
                List<Object> items = (List<Object>) iterObj;
                for (Object item : items) {
                    // نبني context جديد للـ iteration
                    Map<String, Object> loopContext = new HashMap<>(context);
                    loopContext.put(loopVar, item);

                    // نولّد المحتوى لكل عنصر
                    for (int i = 2; i < children.size(); i++) {
                        evaluateNode(children.get(i), loopContext, sb);
                    }
                }
            }
            return;
        }

        // ── IF statement ──────────────────────────────────────────────────────
        // بنية: [condition, content...]
        Object condVal = evaluateJinjaExpr(first, context);
        if (isTruthy(condVal)) {
            for (int i = 1; i < children.size(); i++) {
                AstNode child = children.get(i);
                // تجاهل elif/else nodes
                if (!(child instanceof JinjaStmtNode)) {
                    evaluateNode(child, context, sb);
                }
            }
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

        // Number literal
        if (node instanceof NumberNode) {
            return ((NumberNode) node).getValue();
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

        // Subscript: product["id"]
        String nodeName = node.getNodeName();
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
        if (val instanceof Integer)
            return (Integer) val != 0;
        if (val instanceof String)
            return !((String) val).isEmpty();
        if (val instanceof List)
            return !((List<?>) val).isEmpty();
        return true;
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
