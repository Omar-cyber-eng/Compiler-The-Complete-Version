package compiler.ast.visitors;

import compiler.ast.nodes.*;
import compiler.ast.nodes.css.*;
import compiler.ast.nodes.html.HtmlElementNode;
import compiler.ast.nodes.jinja.*;
import compiler.ast.nodes.python.*;

public interface AstVisitor<R> {

    // === Template root and basic parts ===
    R visitTemplate(TemplateNode node);

    R visitText(TextNode node);

    // === Jinja ===
    R visitJinjaExpr(JinjaExprNode node);

    R visitJinjaStmt(JinjaStmtNode node);

    // === HTML ===
    R visitHtmlElement(HtmlElementNode node);

    // === CSS ===
    R visitCssStylesheet(CssStylesheetNode node);

    R visitCssRule(CssRuleNode node);

    R visitCssDeclaration(CssDeclarationNode node);

    // === Python Expressions ===
    R visitName(NameNode node);

    R visitString(StringNode node);

    R visitNumber(NumberNode node);

    R visitAttrAccess(AttrAccessNode node);

    R visitCall(CallNode node);

    R visitBinOp(BinOpNode node);

    R visitList(ListNode node);

    R visitDict(DictNode node);

    // === Python Statements ===
    R visitAssign(AssignNode node);

    R visitFor(ForNode node);

    R visitIf(IfNode node);

    R visitDef(DefNode node);

    R visitReturn(ReturnNode node);

    R visitSuite(SuiteNode node);

    R visitImport(ImportNode node);

    R visitGlobal(GlobalNode node);

    R visitDecorator(DecoratorNode node);

    R visitPythonFile(PythonFileNode node);
}