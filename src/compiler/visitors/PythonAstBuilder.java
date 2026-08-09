package compiler.visitors;

import java.util.List;

import compiler.ast.core.AstNode;
import compiler.ast.nodes.python.*;
import compiler.ast.visitors.AstVisitor;
import compiler.parser.PythonSubsetParser.*;
import compiler.parser.PythonSubsetParserBaseVisitor;

public class PythonAstBuilder extends PythonSubsetParserBaseVisitor<AstNode> {

    // =========================================================================
    // Helper
    // =========================================================================
    private AstNode makeNode(String name, int line) {
        return new AstNode(name, line) {
            @Override
            public <R> R accept(AstVisitor<R> visitor) {
                return null;
            }
        };
    }

    // =========================================================================
    // File root
    // =========================================================================
    @Override
    public AstNode visitFile_input(File_inputContext ctx) {
        PythonFileNode root = new PythonFileNode(1);
        for (StmtContext stmt : ctx.stmt()) {
            AstNode stmtNode = visit(stmt);
            if (stmtNode != null)
                root.addChild(stmtNode);
        }
        return root;
    }

    // =========================================================================
    // stmt → simple_stmt | compound_stmt
    // =========================================================================
    @Override
    public AstNode visitStmt(StmtContext ctx) {
        if (ctx.simple_stmt() != null)
            return visit(ctx.simple_stmt());
        if (ctx.compound_stmt() != null)
            return visit(ctx.compound_stmt());
        return null;
    }

    // =========================================================================
    // simple_stmt → small_stmt NEWLINE
    // =========================================================================
    @Override
    public AstNode visitSimple_stmt(Simple_stmtContext ctx) {
        return visit(ctx.small_stmt());
    }

    // =========================================================================
    // small_stmt - يمرر للقاعدة الصحيحة
    // =========================================================================
    @Override
    public AstNode visitSmall_stmt(Small_stmtContext ctx) {
        return visitChildren(ctx);
    }

    // =========================================================================
    // Suite - الإصلاح الجوهري
    // =========================================================================
    @Override
    public AstNode visitSuite(SuiteContext ctx) {
        int line = ctx.getStart().getLine();
        SuiteNode suite = new SuiteNode(line);

        // suite: simple_stmt | NEWLINE INDENT stmt+ DEDENT
        if (ctx.simple_stmt() != null) {
            // سطر واحد - نضيفه كابن للـ suite
            AstNode child = visit(ctx.simple_stmt());
            if (child != null)
                suite.addChild(child);
        } else {
            // عدة أسطر
            for (StmtContext stmt : ctx.stmt()) {
                AstNode stmtNode = visit(stmt);
                if (stmtNode != null)
                    suite.addChild(stmtNode);
            }
        }
        return suite;
    }

    // =========================================================================
    // Function definition
    // =========================================================================
    @Override
    public AstNode visitFunction_def(Function_defContext ctx) {
        int line = ctx.getStart().getLine();
        String funcName = ctx.NAME().getText();
        DefNode defNode = new DefNode(funcName, line);

        // Decorators
        for (DecoratorContext dec : ctx.decorator()) {
            AstNode decNode = visitDecorator(dec);
            if (decNode != null)
                defNode.addChild(decNode);
        }

        // Parameters
        if (ctx.parameters() != null) {
            for (ParameterContext param : ctx.parameters().parameter()) {
                defNode.addParam(param.NAME().getText());
            }
        }

        // Body
        AstNode body = visit(ctx.suite());
        if (body != null)
            defNode.addChild(body);

        return defNode;
    }

    // =========================================================================
    // Decorator
    // =========================================================================
    @Override
    public AstNode visitDecorator(DecoratorContext ctx) {
        int line = ctx.getStart().getLine();
        String name = ctx.decorator_name().getText();
        DecoratorNode decNode = new DecoratorNode(name, line);
        if (ctx.arguments() != null) {
            AstNode argsNode = visitArguments_safe(ctx.arguments(), line);
            if (argsNode != null)
                decNode.addChild(argsNode);
        }
        return decNode;
    }

    // =========================================================================
    // Statements
    // =========================================================================
    @Override
    public AstNode visitAssign_stmt(Assign_stmtContext ctx) {
        int line = ctx.getStart().getLine();
        AssignNode assignNode = new AssignNode(line);
        assignNode.addChild(new NameNode(ctx.NAME().getText(), line));
        AstNode value = visit(ctx.test());
        if (value != null)
            assignNode.addChild(value);
        return assignNode;
    }

    @Override
    public AstNode visitFor_stmt(For_stmtContext ctx) {
        int line = ctx.getStart().getLine();
        ForNode forNode = new ForNode(ctx.NAME().getText(), line);
        AstNode iterable = visit(ctx.test());
        if (iterable != null)
            forNode.addChild(iterable);
        AstNode body = visit(ctx.suite());
        if (body != null)
            forNode.addChild(body);
        return forNode;
    }

    @Override
    public AstNode visitIf_stmt(If_stmtContext ctx) {
        int line = ctx.getStart().getLine();
        IfNode ifNode = new IfNode(line);

        AstNode condition = visit(ctx.test(0));
        if (condition != null)
            ifNode.addChild(condition);

        AstNode thenBlock = visit(ctx.suite(0));
        if (thenBlock != null)
            ifNode.addChild(thenBlock);

        for (int i = 1; i < ctx.test().size(); i++) {
            AstNode elifCond = visit(ctx.test(i));
            if (elifCond != null)
                ifNode.addChild(elifCond);
            int suiteIdx = i;
            if (suiteIdx < ctx.suite().size()) {
                AstNode elifBody = visit(ctx.suite(suiteIdx));
                if (elifBody != null)
                    ifNode.addChild(elifBody);
            }
        }

        if (ctx.ELSE() != null) {
            AstNode elseBlock = visit(ctx.suite(ctx.suite().size() - 1));
            if (elseBlock != null)
                ifNode.addChild(elseBlock);
        }

        return ifNode;
    }

    @Override
    public AstNode visitReturn_stmt(Return_stmtContext ctx) {
        int line = ctx.getStart().getLine();
        ReturnNode returnNode = new ReturnNode(line);
        if (ctx.test() != null) {
            AstNode value = visit(ctx.test());
            if (value != null)
                returnNode.addChild(value);
        }
        return returnNode;
    }

    @Override
    public AstNode visitGlobal_stmt(Global_stmtContext ctx) {
        int line = ctx.getStart().getLine();
        GlobalNode globalNode = new GlobalNode(line);
        for (var name : ctx.NAME()) {
            globalNode.addChild(new NameNode(name.getText(), line));
        }
        return globalNode;
    }

    @Override
    public AstNode visitImport_stmt(Import_stmtContext ctx) {
        int line = ctx.getStart().getLine();
        return new ImportNode(ctx.dotted_name().getText(), line);
    }

    @Override
    public AstNode visitFrom_import_stmt(From_import_stmtContext ctx) {
        int line = ctx.getStart().getLine();
        return new ImportNode("from." + ctx.dotted_name().getText(), line);
    }

    @Override
    public AstNode visitExpr_stmt(Expr_stmtContext ctx) {
        return visit(ctx.test());
    }

    // =========================================================================
    // Atoms
    // =========================================================================
    @Override
    public AstNode visitNameAtom(NameAtomContext ctx) {
        return new NameNode(ctx.NAME().getText(), ctx.getStart().getLine());
    }

    @Override
    public AstNode visitNumberAtom(NumberAtomContext ctx) {
        return new NumberNode(ctx.NUMBER().getText(), ctx.getStart().getLine());
    }

    @Override
    public AstNode visitStringAtom(StringAtomContext ctx) {
        int line = ctx.getStart().getLine();
        StringBuilder sb = new StringBuilder();
        for (var str : ctx.STRING()) {
            String text = str.getText();
            text = text.substring(1, text.length() - 1);
            sb.append(text);
        }
        return new StringNode(sb.toString(), line);
    }

    @Override
    public AstNode visitTrueAtom(TrueAtomContext ctx) {
        return new NameNode("True", ctx.getStart().getLine());
    }

    @Override
    public AstNode visitFalseAtom(FalseAtomContext ctx) {
        return new NameNode("False", ctx.getStart().getLine());
    }

    @Override
    public AstNode visitNoneAtom(NoneAtomContext ctx) {
        return new NameNode("None", ctx.getStart().getLine());
    }

    // =========================================================================
    // ParenAtom
    // =========================================================================
    @Override
    public AstNode visitParenAtom(ParenAtomContext ctx) {
        int line = ctx.getStart().getLine();

        if (ctx.testlist_comp() == null)
            return null;

        Testlist_compContext tlc = ctx.testlist_comp();

        if (tlc.comp_for() != null) {
            AstNode genNode = makeNode("GeneratorExpr", line);
            AstNode elem = visit(tlc.test(0));
            if (elem != null)
                genNode.addChild(elem);
            AstNode compFor = visitComp_for(tlc.comp_for());
            if (compFor != null)
                genNode.addChild(compFor);
            return genNode;
        }

        if (tlc.test().size() == 1) {
            return visit(tlc.test(0));
        }

        AstNode tupleNode = makeNode("Tuple", line);
        for (TestContext t : tlc.test()) {
            AstNode elem = visit(t);
            if (elem != null)
                tupleNode.addChild(elem);
        }
        return tupleNode;
    }

    // =========================================================================
    // ListAtom
    // =========================================================================
    @Override
    public AstNode visitListAtom(ListAtomContext ctx) {
        int line = ctx.getStart().getLine();

        if (ctx.listmaker() == null)
            return new ListNode(line);

        ListmakerContext lm = ctx.listmaker();

        if (lm.comp_for() != null) {
            AstNode compNode = makeNode("ListComp", line);
            AstNode elem = visit(lm.test(0));
            if (elem != null)
                compNode.addChild(elem);
            AstNode compFor = visitComp_for(lm.comp_for());
            if (compFor != null)
                compNode.addChild(compFor);
            return compNode;
        }

        ListNode listNode = new ListNode(line);
        for (TestContext t : lm.test()) {
            AstNode elem = visit(t);
            if (elem != null)
                listNode.addChild(elem);
        }
        return listNode;
    }

    // =========================================================================
    // DictAtom
    // =========================================================================
    @Override
    public AstNode visitDictAtom(DictAtomContext ctx) {
        int line = ctx.getStart().getLine();
        DictNode dictNode = new DictNode(line);

        if (ctx.dictorsetmaker() == null)
            return dictNode;

        DictorsetmakerContext maker = ctx.dictorsetmaker();

        // ⭐ الطريقة الصحيحة: نمشي على الـ children يدوياً
        // dictorsetmaker: (test ':' test) (',' test ':' test)* ','?

        List<TestContext> allTests = maker.test();

        // الـ tests تأتي بالترتيب: key1, val1, key2, val2, ...
        for (int i = 0; i + 1 < allTests.size(); i += 2) {
            AstNode key = visit(allTests.get(i));
            AstNode val = visit(allTests.get(i + 1));

            if (key != null)
                dictNode.addChild(key);
            if (val != null)
                dictNode.addChild(val);
        }

        return dictNode;
    }

    // =========================================================================
    // comp_for
    // =========================================================================
    @Override
    public AstNode visitComp_for(Comp_forContext ctx) {
        int line = ctx.getStart().getLine();
        AstNode compFor = makeNode("CompFor", line);
        compFor.addChild(new NameNode(ctx.NAME().getText(), line));
        AstNode iterable = visit(ctx.or_test(0));
        if (iterable != null)
            compFor.addChild(iterable);
        if (ctx.or_test().size() > 1) {
            AstNode condition = visit(ctx.or_test(1));
            if (condition != null)
                compFor.addChild(condition);
        }
        return compFor;
    }

    // =========================================================================
    // atom_expr
    // =========================================================================
    @Override
    public AstNode visitAtom_expr(Atom_exprContext ctx) {
        int line = ctx.getStart().getLine();
        AstNode current = visit(ctx.atom());

        if (ctx.trailer() == null || ctx.trailer().isEmpty())
            return current;

        for (TrailerContext trailer : ctx.trailer()) {
            if (trailer instanceof CallTrailerContext) {
                CallNode callNode = new CallNode(line);
                callNode.addChild(current);
                CallTrailerContext call = (CallTrailerContext) trailer;
                if (call.arguments() != null) {
                    AstNode args = visitArguments_safe(call.arguments(), line);
                    if (args != null) {
                        for (AstNode arg : args.getChildren()) {
                            callNode.addChild(arg);
                        }
                    }
                }
                current = callNode;

            } else if (trailer instanceof AttrTrailerContext) {
                AttrAccessNode attrNode = new AttrAccessNode(line);
                attrNode.addChild(current);
                attrNode.addChild(
                        new NameNode(((AttrTrailerContext) trailer).NAME().getText(), line));
                current = attrNode;

            } else if (trailer instanceof IndexTrailerContext) {
                AstNode subscript = makeNode("Subscript", line);
                subscript.addChild(current);
                AstNode idx = visit(((IndexTrailerContext) trailer).test());
                if (idx != null)
                    subscript.addChild(idx);
                current = subscript;
            }
        }
        return current;
    }

    // =========================================================================
    // Arguments
    // =========================================================================
    private AstNode visitArguments_safe(ArgumentsContext ctx, int line) {
        AstNode argsNode = makeNode("Args", line);

        if (ctx.comp_for() != null) {
            AstNode genNode = makeNode("GeneratorExpr", line);
            AstNode elem = visit(ctx.test());
            if (elem != null)
                genNode.addChild(elem);
            AstNode compFor = visitComp_for(ctx.comp_for());
            if (compFor != null)
                genNode.addChild(compFor);
            argsNode.addChild(genNode);
            return argsNode;
        }

        if (ctx.argument() != null) {
            for (ArgumentContext arg : ctx.argument()) {
                AstNode argNode = visit(arg);
                if (argNode != null)
                    argsNode.addChild(argNode);
            }
        }
        return argsNode;
    }

    @Override
    public AstNode visitPositionalArgument(PositionalArgumentContext ctx) {
        return visit(ctx.test());
    }

    @Override
    public AstNode visitKeywordArgument(KeywordArgumentContext ctx) {
        int line = ctx.getStart().getLine();
        AstNode kwNode = makeNode("KeywordArg", line);
        kwNode.addChild(new NameNode(ctx.NAME().getText(), line));
        AstNode value = visit(ctx.test());
        if (value != null)
            kwNode.addChild(value);
        return kwNode;
    }

    // =========================================================================
    // Expressions
    // =========================================================================
    @Override
    public AstNode visitComparison(ComparisonContext ctx) {
        if (ctx.comp_op() == null || ctx.comp_op().isEmpty()) {
            return visit(ctx.expr(0));
        }
        int line = ctx.getStart().getLine();
        BinOpNode binOp = new BinOpNode(ctx.comp_op(0).getText(), line);
        AstNode left = visit(ctx.expr(0));
        AstNode right = visit(ctx.expr(1));
        if (left != null)
            binOp.addChild(left);
        if (right != null)
            binOp.addChild(right);
        return binOp;
    }

    @Override
    public AstNode visitExpr(ExprContext ctx) {
        if (ctx.term().size() == 1)
            return visit(ctx.term(0));
        int line = ctx.getStart().getLine();
        String op = (ctx.PLUS() != null && !ctx.PLUS().isEmpty()) ? "+" : "-";
        BinOpNode binOp = new BinOpNode(op, line);
        AstNode left = visit(ctx.term(0));
        AstNode right = visit(ctx.term(1));
        if (left != null)
            binOp.addChild(left);
        if (right != null)
            binOp.addChild(right);
        return binOp;
    }

    @Override
    public AstNode visitTerm(TermContext ctx) {
        if (ctx.factor().size() == 1)
            return visit(ctx.factor(0));
        int line = ctx.getStart().getLine();
        String op = "*";
        if (ctx.SLASH() != null && !ctx.SLASH().isEmpty())
            op = "/";
        else if (ctx.PERCENT() != null && !ctx.PERCENT().isEmpty())
            op = "%";
        else if (ctx.DOUBLESLASH() != null && !ctx.DOUBLESLASH().isEmpty())
            op = "//";
        BinOpNode binOp = new BinOpNode(op, line);
        AstNode left = visit(ctx.factor(0));
        AstNode right = visit(ctx.factor(1));
        if (left != null)
            binOp.addChild(left);
        if (right != null)
            binOp.addChild(right);
        return binOp;
    }
}