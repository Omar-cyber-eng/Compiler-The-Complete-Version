package compiler.ast.visitors;

import compiler.ast.nodes.*;
import compiler.ast.nodes.css.*;
import compiler.ast.nodes.html.HtmlElementNode;
import compiler.ast.nodes.jinja.*;
import compiler.ast.nodes.python.*;
import compiler.ast.core.*;

import java.util.Map;

public class PrintVisitor implements AstVisitor<Void> {

    private final StringBuilder sb = new StringBuilder();
    private int indentLevel = 0;

    private void indent() {
        sb.append("  ".repeat(indentLevel));
    }

    private void printNode(AstNode node) {
        indentLevel++;
        for (AstNode child : node.getChildren()) {
            // العقد المجهولة (Subscript/KeywordArg/GeneratorExpr...) لا تملك تابع
            // زيارة مخصّصاً (accept يرجع null) — نطبعها عبر مسار عام.
            if (child.getClass().isAnonymousClass()) {
                printGeneric(child);
            } else {
                child.accept(this);
            }
        }
        indentLevel--;
    }

    private void printGeneric(AstNode node) {
        printHeader(node.getNodeName() + " (line " + node.getLine() + ")");
        printNode(node);
    }

    private void printHeader(String header) {
        indent();
        sb.append(header).append("\n");
    }

    // =========================================================================
    // Template
    // =========================================================================
    @Override
    public Void visitTemplate(TemplateNode node) {
        printHeader("Template (line " + node.getLine() + ")");
        printNode(node);
        return null;
    }

    @Override
    public Void visitText(TextNode node) {
        printHeader("Text (line " + node.getLine() + ") \""
                + node.getText().trim().replace("\n", "\\n") + "\"");
        printNode(node);
        return null;
    }

    // =========================================================================
    // Jinja
    // =========================================================================
    @Override
    public Void visitJinjaExpr(JinjaExprNode node) {
        printHeader("JinjaExpr (line " + node.getLine() + ")");
        printNode(node);
        return null;
    }

    @Override
    public Void visitJinjaStmt(JinjaStmtNode node) {
        printHeader("JinjaStmt (line " + node.getLine() + ")");
        printNode(node);
        return null;
    }

    // =========================================================================
    // HTML
    // =========================================================================
    @Override
    public Void visitHtmlElement(HtmlElementNode node) {
        StringBuilder header = new StringBuilder();
        header.append("HtmlElement <").append(node.getTagName())
                .append("> (line ").append(node.getLine()).append(")");

        Map<String, String> attrs = node.getAttributes();
        if (!attrs.isEmpty()) {
            header.append(" attrs=[");
            attrs.forEach((k, v) -> header.append(k).append("=\"").append(v).append("\" "));
            header.append("]");
        }

        printHeader(header.toString());
        printNode(node);
        return null;
    }

    // =========================================================================
    // CSS
    // =========================================================================
    @Override
    public Void visitCssStylesheet(CssStylesheetNode node) {
        printHeader("CssStylesheet (line " + node.getLine() + ")");
        printNode(node);
        return null;
    }

    @Override
    public Void visitCssRule(CssRuleNode node) {
        printHeader("CssRule selector=\"" + node.getSelector()
                + "\" (line " + node.getLine() + ")");
        printNode(node);
        return null;
    }

    @Override
    public Void visitCssDeclaration(CssDeclarationNode node) {
        printHeader("CssDeclaration " + node.getProperty()
                + ": " + node.getValue() + " (line " + node.getLine() + ")");
        printNode(node);
        return null;
    }

    // =========================================================================
    // Python - File & Structure
    // =========================================================================
    @Override
    public Void visitPythonFile(PythonFileNode node) {
        printHeader("PythonFile (line " + node.getLine() + ")");
        printNode(node);
        return null;
    }

    @Override
    public Void visitSuite(SuiteNode node) {
        printHeader("Suite (line " + node.getLine() + ")");
        printNode(node);
        return null;
    }

    @Override
    public Void visitDecorator(DecoratorNode node) {
        printHeader("Decorator \"" + node.getDecoratorName()
                + "\" (line " + node.getLine() + ")");
        printNode(node);
        return null;
    }

    // =========================================================================
    // Python - Statements
    // =========================================================================
    @Override
    public Void visitDef(DefNode node) {
        printHeader("Def \"" + node.getName()
                + "\" params=" + node.getParams()
                + " (line " + node.getLine() + ")");
        printNode(node);
        return null;
    }

    @Override
    public Void visitAssign(AssignNode node) {
        printHeader("Assign (line " + node.getLine() + ")");
        printNode(node);
        return null;
    }

    @Override
    public Void visitReturn(ReturnNode node) {
        printHeader("Return (line " + node.getLine() + ")");
        printNode(node);
        return null;
    }

    @Override
    public Void visitFor(ForNode node) {
        printHeader("For var=\"" + node.getVarName()
                + "\" (line " + node.getLine() + ")");
        printNode(node);
        return null;
    }

    @Override
    public Void visitIf(IfNode node) {
        printHeader("If (line " + node.getLine() + ")");
        printNode(node);
        return null;
    }

    @Override
    public Void visitGlobal(GlobalNode node) {
        printHeader("Global (line " + node.getLine() + ")");
        printNode(node);
        return null;
    }

    @Override
    public Void visitImport(ImportNode node) {
        printHeader("Import \"" + node.getModuleName()
                + "\" (line " + node.getLine() + ")");
        printNode(node);
        return null;
    }

    // =========================================================================
    // Python - Expressions
    // =========================================================================
    @Override
    public Void visitName(NameNode node) {
        printHeader("Name \"" + node.getName()
                + "\" (line " + node.getLine() + ")");
        printNode(node);
        return null;
    }

    @Override
    public Void visitString(StringNode node) {
        printHeader("String \"" + node.getValue()
                + "\" (line " + node.getLine() + ")");
        printNode(node);
        return null;
    }

    @Override
    public Void visitNumber(NumberNode node) {
        printHeader("Number " + node.getValue()
                + " (line " + node.getLine() + ")");
        printNode(node);
        return null;
    }

    @Override
    public Void visitAttrAccess(AttrAccessNode node) {
        printHeader("AttrAccess (line " + node.getLine() + ")");
        printNode(node);
        return null;
    }

    @Override
    public Void visitCall(CallNode node) {
        printHeader("Call (line " + node.getLine() + ")");
        printNode(node);
        return null;
    }

    @Override
    public Void visitBinOp(BinOpNode node) {
        printHeader("BinOp op=\"" + node.getOp()
                + "\" (line " + node.getLine() + ")");
        printNode(node);
        return null;
    }

    @Override
    public Void visitList(ListNode node) {
        printHeader("List (line " + node.getLine() + ")");
        printNode(node);
        return null;
    }

    @Override
    public Void visitDict(DictNode node) {
        printHeader("Dict (line " + node.getLine() + ")");
        printNode(node);
        return null;
    }

    // =========================================================================
    // Utility
    // =========================================================================
    public void printTree(AstNode root) {
        sb.setLength(0);
        root.accept(this);
        System.out.print(sb.toString());
    }

    public String getTreeString(AstNode root) {
        sb.setLength(0);
        root.accept(this);
        return sb.toString();
    }
}