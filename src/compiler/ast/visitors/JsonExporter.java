package compiler.ast.visitors;

import compiler.ast.nodes.*;
import compiler.ast.nodes.css.*;
import compiler.ast.nodes.html.HtmlElementNode;
import compiler.ast.nodes.jinja.*;
import compiler.ast.nodes.python.*;
import compiler.ast.core.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * Exports AST to JSON format.
 * Mirrors PrintVisitor structure exactly.
 */
public class JsonExporter implements AstVisitor<String> {

    private int indentLevel = 0;

    // ── Indent helpers ────────────────────────────────────────────────
    private String indent() {
        return "  ".repeat(indentLevel);
    }

    // ── بناء JSON لعقدة مع أبنائها ───────────────────────────────────
    private String buildNode(String type, String extra,
            AstNode node) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append("{\n");
        indentLevel++;

        sb.append(indent()).append("\"type\": \"")
                .append(type).append("\"");

        if (extra != null && !extra.isEmpty()) {
            sb.append(",\n").append(extra);
        }

        sb.append(",\n").append(indent())
                .append("\"line\": ").append(node.getLine());

        List<AstNode> children = node.getChildren();
        if (!children.isEmpty()) {
            sb.append(",\n").append(indent())
                    .append("\"children\": [\n");
            indentLevel++;
            for (int i = 0; i < children.size(); i++) {
                String childJson = children.get(i).accept(this);
                // العقد المجهولة (Subscript/KeywordArg/GeneratorExpr...) يرجع accept
                // فيها null — نصدّرها عبر مسار عام حتى لا تظهر كـ null في JSON.
                if (childJson == null)
                    childJson = exportGeneric(children.get(i));
                sb.append(childJson);
                if (i < children.size() - 1)
                    sb.append(",");
                sb.append("\n");
            }
            indentLevel--;
            sb.append(indent()).append("]");
        }

        indentLevel--;
        sb.append("\n").append(indent()).append("}");
        return sb.toString();
    }

    // ── تصدير عام لأي عقدة (للعقد التي لا تملك تابع زيارة مخصّصاً) ──────
    private String exportGeneric(AstNode node) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append("{\n");
        indentLevel++;

        sb.append(indent()).append("\"type\": \"")
                .append(esc(node.getNodeName())).append("\"");
        sb.append(",\n").append(indent())
                .append("\"line\": ").append(node.getLine());

        List<AstNode> children = node.getChildren();
        if (!children.isEmpty()) {
            sb.append(",\n").append(indent())
                    .append("\"children\": [\n");
            indentLevel++;
            for (int i = 0; i < children.size(); i++) {
                String childJson = children.get(i).accept(this);
                if (childJson == null)
                    childJson = exportGeneric(children.get(i));
                sb.append(childJson);
                if (i < children.size() - 1)
                    sb.append(",");
                sb.append("\n");
            }
            indentLevel--;
            sb.append(indent()).append("]");
        }

        indentLevel--;
        sb.append("\n").append(indent()).append("}");
        return sb.toString();
    }

    // ── escape للـ strings ────────────────────────────────────────────
    private String esc(String s) {
        if (s == null)
            return "";
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

    // ── extra field helper ────────────────────────────────────────────
    private String field(String key, String value) {
        return indent() + "\"" + key + "\": \"" + esc(value) + "\"";
    }

    // =========================================================================
    // Template
    // =========================================================================
    @Override
    public String visitTemplate(TemplateNode node) {
        return buildNode("Template", null, node);
    }

    @Override
    public String visitText(TextNode node) {
        return buildNode("Text",
                field("value", node.getText().trim()),
                node);
    }

    // =========================================================================
    // Jinja
    // =========================================================================
    @Override
    public String visitJinjaExpr(JinjaExprNode node) {
        return buildNode("JinjaExpr", null, node);
    }

    @Override
    public String visitJinjaStmt(JinjaStmtNode node) {
        return buildNode("JinjaStmt", null, node);
    }

    // =========================================================================
    // HTML
    // =========================================================================
    @Override
    public String visitHtmlElement(HtmlElementNode node) {
        StringBuilder extra = new StringBuilder();
        extra.append(field("tag", node.getTagName()));

        Map<String, String> attrs = node.getAttributes();
        if (!attrs.isEmpty()) {
            extra.append(",\n");
            extra.append(indent()).append("\"attributes\": {\n");
            indentLevel++;
            int i = 0;
            for (Map.Entry<String, String> e : attrs.entrySet()) {
                extra.append(indent())
                        .append("\"").append(esc(e.getKey())).append("\": ")
                        .append("\"").append(esc(e.getValue())).append("\"");
                if (i < attrs.size() - 1)
                    extra.append(",");
                extra.append("\n");
                i++;
            }
            indentLevel--;
            extra.append(indent()).append("}");
        }

        return buildNode("HtmlElement", extra.toString(), node);
    }

    // =========================================================================
    // CSS
    // =========================================================================
    @Override
    public String visitCssStylesheet(CssStylesheetNode node) {
        return buildNode("CssStylesheet", null, node);
    }

    @Override
    public String visitCssRule(CssRuleNode node) {
        return buildNode("CssRule",
                field("selector", node.getSelector()),
                node);
    }

    @Override
    public String visitCssDeclaration(CssDeclarationNode node) {
        String extra = field("property", node.getProperty())
                + ",\n"
                + field("value", node.getValue());
        return buildNode("CssDeclaration", extra, node);
    }

    // =========================================================================
    // Python - File & Structure
    // =========================================================================
    @Override
    public String visitPythonFile(PythonFileNode node) {
        return buildNode("PythonFile", null, node);
    }

    @Override
    public String visitSuite(SuiteNode node) {
        return buildNode("Suite", null, node);
    }

    @Override
    public String visitDecorator(DecoratorNode node) {
        return buildNode("Decorator",
                field("name", node.getDecoratorName()),
                node);
    }

    // =========================================================================
    // Python - Statements
    // =========================================================================
    @Override
    public String visitDef(DefNode node) {
        StringBuilder extra = new StringBuilder();
        extra.append(field("name", node.getName()));
        extra.append(",\n");
        extra.append(indent()).append("\"params\": [");
        List<String> params = node.getParams();
        for (int i = 0; i < params.size(); i++) {
            extra.append("\"").append(esc(params.get(i))).append("\"");
            if (i < params.size() - 1)
                extra.append(", ");
        }
        extra.append("]");
        return buildNode("Def", extra.toString(), node);
    }

    @Override
    public String visitAssign(AssignNode node) {
        return buildNode("Assign", null, node);
    }

    @Override
    public String visitReturn(ReturnNode node) {
        return buildNode("Return", null, node);
    }

    @Override
    public String visitFor(ForNode node) {
        return buildNode("For",
                field("var", node.getVarName()),
                node);
    }

    @Override
    public String visitIf(IfNode node) {
        return buildNode("If", null, node);
    }

    @Override
    public String visitGlobal(GlobalNode node) {
        return buildNode("Global", null, node);
    }

    @Override
    public String visitImport(ImportNode node) {
        return buildNode("Import",
                field("module", node.getModuleName()),
                node);
    }

    // =========================================================================
    // Python - Expressions
    // =========================================================================
    @Override
    public String visitName(NameNode node) {
        return buildNode("Name",
                field("value", node.getName()),
                node);
    }

    @Override
    public String visitString(StringNode node) {
        return buildNode("String",
                field("value", node.getValue()),
                node);
    }

    @Override
    public String visitNumber(NumberNode node) {
        return buildNode("Number",
                indent() + "\"value\": " + node.getValue(),
                node);
    }

    @Override
    public String visitAttrAccess(AttrAccessNode node) {
        return buildNode("AttrAccess", null, node);
    }

    @Override
    public String visitCall(CallNode node) {
        return buildNode("Call", null, node);
    }

    @Override
    public String visitBinOp(BinOpNode node) {
        return buildNode("BinOp",
                field("op", node.getOp()),
                node);
    }

    @Override
    public String visitList(ListNode node) {
        return buildNode("List", null, node);
    }

    @Override
    public String visitDict(DictNode node) {
        return buildNode("Dict", null, node);
    }

    // =========================================================================
    // Public API
    // =========================================================================

    /**
     * تصدير AST كـ JSON string
     */
    public String export(AstNode root) {
        indentLevel = 0;
        return root.accept(this);
    }

    /**
     * حفظ JSON إلى ملف
     */
    public void saveToFile(AstNode root, String filePath) {
        String json = export(root);
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            try (Writer w = new OutputStreamWriter(
                    new FileOutputStream(file),
                    StandardCharsets.UTF_8)) {
                w.write(json);
            }
            System.out.println("  ✅ Saved: " + filePath);
        } catch (IOException e) {
            System.err.println("  ❌ Failed to save: " + filePath);
            e.printStackTrace();
        }
    }
}