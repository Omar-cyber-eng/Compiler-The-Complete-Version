package compiler.symbol;

import compiler.ast.core.AstNode;
import compiler.ast.nodes.*;
import compiler.ast.nodes.css.*;
import compiler.ast.nodes.html.HtmlElementNode;
import compiler.ast.nodes.jinja.*;
import compiler.ast.nodes.python.*;
import compiler.ast.visitors.AstVisitor;

public class SymbolTableVisitor implements AstVisitor<Void> {

    private Scope currentScope;
    private Scope globalScope;

    // الأسماء المعلَنة global داخل الدالة الحالية: الإسناد إليها يعدّل
    // المتغيّر العام ولا يُنشئ متغيّراً محلياً جديداً
    private java.util.Set<String> currentGlobalNames = new java.util.HashSet<>();

    public SymbolTableVisitor() {
        globalScope = new Scope("global", null);
        currentScope = globalScope;
    }

    public Scope getGlobalScope() {
        return globalScope;
    }

    public void printSymbolTable() {
        printScope(globalScope, 0);
    }

    private void printScope(Scope scope, int indent) {
        String indentStr = "  ".repeat(indent);
        System.out.println(indentStr + "Scope: " + scope.getName());

        if (!scope.getSymbols().isEmpty()) {
            System.out.println(indentStr + "Symbols:");
            for (Symbol symbol : scope.getSymbols().values()) {
                System.out.println(indentStr + "  " + symbol.getName()
                        + ": " + symbol.getType()
                        + " (line " + symbol.getLine() + ")");
            }
        }

        for (Scope child : scope.getChildren()) {
            System.out.println();
            printScope(child, indent + 1);
        }
    }

    // =========================================================================
    // Helper: زيارة كل الأبناء
    // =========================================================================
    private Void visitChildren(AstNode node) {
        for (AstNode child : node.getChildren()) {
            child.accept(this);
        }
        return null;
    }

    // =========================================================================
    // Python File
    // =========================================================================
    @Override
    public Void visitPythonFile(PythonFileNode node) {
        return visitChildren(node);
    }

    // =========================================================================
    // Python Statements
    // =========================================================================
    @Override
    public Void visitDef(DefNode node) {
        // تعريف الدالة في الـ scope الحالي
        Symbol funcSymbol = new Symbol(node.getName(), "function", node.getLine());
        currentScope.define(funcSymbol);

        // إنشاء scope جديد للدالة
        Scope funcScope = new Scope(node.getName(), currentScope);
        currentScope.addChild(funcScope);
        Scope previousScope = currentScope;
        currentScope = funcScope;

        // تعريف المعاملات في scope الدالة
        for (String param : node.getParams()) {
            Symbol paramSymbol = new Symbol(param, "parameter", node.getLine());
            currentScope.define(paramSymbol);
        }

        // إعلانات global خاصة بكل دالة
        java.util.Set<String> previousGlobals = currentGlobalNames;
        currentGlobalNames = new java.util.HashSet<>();

        // زيارة جسم الدالة
        visitChildren(node);

        // العودة للـ scope السابق
        currentGlobalNames = previousGlobals;
        currentScope = previousScope;
        return null;
    }

    @Override
    public Void visitAssign(AssignNode node) {
        if (!node.getChildren().isEmpty()) {
            AstNode target = node.getChildren().get(0);
            if (target instanceof NameNode) {
                String varName = ((NameNode) target).getName();
                // الإسناد إلى اسم معلَن global يعدّل المتغيّر العام نفسه،
                // فلا نُعرّف نسخة محلية مضلِّلة داخل الدالة
                if (!currentGlobalNames.contains(varName)) {
                    Symbol varSymbol = new Symbol(varName, "variable", node.getLine());
                    currentScope.define(varSymbol);
                }
            }
            if (node.getChildren().size() > 1) {
                node.getChildren().get(1).accept(this);
            }
        }
        return null;
    }

    @Override
    public Void visitFor(ForNode node) {
        Symbol loopVar = new Symbol(node.getVarName(), "loop_variable", node.getLine());
        currentScope.define(loopVar);
        return visitChildren(node);
    }

    @Override
    public Void visitIf(IfNode node) {
        return visitChildren(node);
    }

    @Override
    public Void visitReturn(ReturnNode node) {
        return visitChildren(node);
    }

    @Override
    public Void visitSuite(SuiteNode node) {
        return visitChildren(node);
    }

    @Override
    public Void visitGlobal(GlobalNode node) {
        // تسجيل إعلان global: لا نطمس تعريف المتغيّر الأصلي في النطاق العام
        // (كان يستبدل سطر تعريفه بسطر تعليمة global)، ونسجّل مرجعاً في الدالة
        for (AstNode child : node.getChildren()) {
            if (child instanceof NameNode) {
                String varName = ((NameNode) child).getName();
                currentGlobalNames.add(varName);

                if (globalScope.resolveLocal(varName) == null) {
                    globalScope.define(
                            new Symbol(varName, "global_variable", node.getLine()));
                }
                if (currentScope != globalScope) {
                    currentScope.define(
                            new Symbol(varName, "global_ref", node.getLine()));
                }
            }
        }
        return null;
    }

    @Override
    public Void visitImport(ImportNode node) {
        // تسجيل الـ module المستورد
        Symbol importSymbol = new Symbol(node.getModuleName(), "import", node.getLine());
        currentScope.define(importSymbol);
        return null;
    }

    @Override
    public Void visitDecorator(DecoratorNode node) {
        return visitChildren(node);
    }

    // =========================================================================
    // Python Expressions
    // =========================================================================
    @Override
    public Void visitName(NameNode node) {
        return null;
    }

    @Override
    public Void visitCall(CallNode node) {
        return visitChildren(node);
    }

    @Override
    public Void visitAttrAccess(AttrAccessNode node) {
        return visitChildren(node);
    }

    @Override
    public Void visitBinOp(BinOpNode node) {
        return visitChildren(node);
    }

    @Override
    public Void visitString(StringNode node) {
        return null;
    }

    @Override
    public Void visitNumber(NumberNode node) {
        return null;
    }

    @Override
    public Void visitList(ListNode node) {
        return visitChildren(node);
    }

    @Override
    public Void visitDict(DictNode node) {
        return visitChildren(node);
    }

    // =========================================================================
    // Template Nodes
    // =========================================================================
    @Override
    public Void visitTemplate(TemplateNode node) {
        return visitChildren(node);
    }

    @Override
    public Void visitText(TextNode node) {
        return null;
    }

    @Override
    public Void visitHtmlElement(HtmlElementNode node) {
        return visitChildren(node);
    }

    @Override
    public Void visitJinjaExpr(JinjaExprNode node) {
        return visitChildren(node);
    }

    @Override
    public Void visitJinjaStmt(JinjaStmtNode node) {
        return visitChildren(node);
    }

    // =========================================================================
    // CSS Nodes
    // =========================================================================
    @Override
    public Void visitCssStylesheet(CssStylesheetNode node) {
        return null;
    }

    @Override
    public Void visitCssRule(CssRuleNode node) {
        return null;
    }

    @Override
    public Void visitCssDeclaration(CssDeclarationNode node) {
        return null;
    }
}