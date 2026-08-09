package compiler.semantic;

import compiler.ast.core.AstNode;
import compiler.ast.nodes.*;
import compiler.ast.nodes.css.*;
import compiler.ast.nodes.html.HtmlElementNode;
import compiler.ast.nodes.jinja.*;
import compiler.ast.nodes.python.*;
import compiler.ast.visitors.AstVisitor;
import compiler.symbol.Scope;
import compiler.symbol.Symbol;

import java.util.*;

public class SemanticAnalyzer implements AstVisitor<Void> {

    // =========================================================================
    // SemanticError
    // =========================================================================

    public static class SemanticError {
        public enum ErrorType {
            UNDEFINED_VAR,
            UNDEFINED_FUNC,
            REDEFINE_FUNC,
            WRONG_ARG_COUNT,
            MISSING_RETURN,
            REDEFINE_VAR,
            UNUSED_VAR,
            DEAD_CODE,
            INVALID_ASSIGN
        }

        private final ErrorType type;
        private final String message;
        private final int line;

        public SemanticError(ErrorType type, String message, int line) {
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
            return String.format("[SEMANTIC ERROR - %s] Line %d: %s",
                    type.name(), line, message);
        }
    }

    // =========================================================================
    // الحالة الداخلية
    // =========================================================================

    private final List<SemanticError> errors = new ArrayList<>();

    private Scope currentScope;
    private Scope globalScope;

    // اسم الدالة → عدد parameters (-1 = variable args)
    private final Map<String, Integer> functionParamCount = new HashMap<>();

    // تتبع المتغيرات المستخدمة فعلاً: scope path → set of used names
    private final Map<String, Set<String>> usedVars = new HashMap<>();

    // تتبع المتغيرات المعرّفة: scope path → set of defined names
    private final Map<String, Set<String>> definedVars = new HashMap<>();

    // هل الدالة الحالية تحتوي return
    private boolean currentFuncHasReturn = false;
    private String currentFuncName = null;

    // هل رأينا return في هذا الـ suite (لكشف dead code)
    private boolean seenReturnInBlock = false;

    // الـ builtins
    private static final Set<String> BUILTIN_NAMES = new HashSet<>(Arrays.asList(
            "True", "False", "None",
            "print", "len", "range", "str", "int", "float", "bool",
            "list", "dict", "set", "tuple",
            "next", "iter", "enumerate", "zip", "map", "filter",
            "sorted", "reversed", "sum", "min", "max", "abs",
            "isinstance", "issubclass", "type", "hasattr", "getattr",
            "setattr", "delattr", "callable",
            "__name__", "__file__", "__doc__",
            "Exception", "ValueError", "TypeError", "KeyError",
            "IndexError", "AttributeError", "NotImplementedError",
            // Flask
            "request", "redirect", "url_for", "render_template",
            "Flask", "jsonify", "abort", "flash", "session",
            "g", "current_app"));

    private final Set<String> importedNames = new HashSet<>();

    // =========================================================================
    // Constructor
    // =========================================================================

    public SemanticAnalyzer() {
        globalScope = new Scope("global", null);
        currentScope = globalScope;

        // دوال بـ variable args = -1
        functionParamCount.put("print", -1);
        functionParamCount.put("len", -1);
        functionParamCount.put("range", -1);
        functionParamCount.put("str", -1);
        functionParamCount.put("int", -1);
        functionParamCount.put("float", -1);
        functionParamCount.put("bool", -1);
        functionParamCount.put("list", -1);
        functionParamCount.put("dict", -1);
        functionParamCount.put("sorted", -1);
        functionParamCount.put("enumerate", -1);
        functionParamCount.put("zip", -1);
        functionParamCount.put("map", -1);
        functionParamCount.put("filter", -1);
        functionParamCount.put("isinstance", -1);
        functionParamCount.put("type", -1);
        functionParamCount.put("next", -1);
        functionParamCount.put("iter", -1);
        functionParamCount.put("sum", -1);
        functionParamCount.put("min", -1);
        functionParamCount.put("max", -1);
        functionParamCount.put("abs", -1);
        // Flask
        functionParamCount.put("Flask", -1);
        functionParamCount.put("render_template", -1);
        functionParamCount.put("redirect", -1);
        functionParamCount.put("url_for", -1);
        functionParamCount.put("jsonify", -1);
        functionParamCount.put("abort", -1);
        functionParamCount.put("flash", -1);
    }

    // =========================================================================
    // Public API
    // =========================================================================

    public List<SemanticError> getErrors() {
        return Collections.unmodifiableList(errors);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public void printErrors() {
        if (errors.isEmpty()) {
            System.out.println("  No semantic errors found.");
            return;
        }
        System.out.println("  Found " + errors.size() + " semantic error(s):\n");
        for (SemanticError e : errors) {
            System.out.println("  " + e);
        }
    }

    // =========================================================================
    // Helpers
    // =========================================================================

    private void addError(SemanticError.ErrorType type, String msg, int line) {
        errors.add(new SemanticError(type, msg, line));
    }

    private Void visitChildren(AstNode node) {
        for (AstNode child : node.getChildren()) {
            if (child != null)
                child.accept(this);
        }
        return null;
    }

    private void enterScope(String name) {
        Scope s = new Scope(name, currentScope);
        currentScope.addChild(s);
        currentScope = s;
    }

    private void exitScope() {
        // ── UNUSED_VAR: قبل الخروج من الـ scope نكتشف المتغيرات غير المستخدمة
        checkUnusedVars();
        if (currentScope.getParent() != null) {
            currentScope = currentScope.getParent();
        }
    }

    private void defineSymbol(String name, String type, int line) {
        // REDEFINE_VAR: إذا كان معرّفاً مسبقاً في نفس الـ scope
        if (currentScope.resolveLocal(name) != null) {
            String existingType = currentScope.resolveLocal(name).getType();
            // لا نبلّغ عن إعادة تعريف loop variables أو globals
            if (!existingType.equals("loop_variable")
                    && !existingType.equals("global_ref")
                    && !existingType.equals("parameter")) {
                addError(
                        SemanticError.ErrorType.REDEFINE_VAR,
                        "Variable '" + name + "' is already defined in this scope"
                                + " (first defined at line "
                                + currentScope.resolveLocal(name).getLine() + ")",
                        line);
            }
        }
        currentScope.define(new Symbol(name, type, line));

        // نسجّل في definedVars
        String scopePath = currentScope.getFullPath();
        definedVars.computeIfAbsent(scopePath, k -> new HashSet<>()).add(name);
    }

    private void markUsed(String name) {
        String scopePath = currentScope.getFullPath();
        usedVars.computeIfAbsent(scopePath, k -> new HashSet<>()).add(name);
        // نمشي للـ parent scopes أيضاً
        Scope s = currentScope.getParent();
        while (s != null) {
            usedVars.computeIfAbsent(s.getFullPath(), k -> new HashSet<>()).add(name);
            s = s.getParent();
        }
    }

    private void checkUnusedVars() {
        String scopePath = currentScope.getFullPath();
        Set<String> used = usedVars.getOrDefault(scopePath, new HashSet<>());

        for (Symbol sym : currentScope.getSymbols().values()) {
            String name = sym.getName();

            // نتجاهل: parameters, imports, functions, globals, loop variables
            if (sym.getType().equals("parameter")
                    || sym.getType().equals("import")
                    || sym.getType().equals("function")
                    || sym.getType().equals("global_variable")
                    || sym.getType().equals("global_ref")
                    || sym.getType().equals("loop_variable")
                    || BUILTIN_NAMES.contains(name)) {
                continue;
            }

            // ❌ احذف هذا الشرط كاملاً:
            // if (sym.getType().equals("variable")
            // && !currentScope.getName().equals("global")) {
            // continue;
            // }

            // ✅ فقط تحقق من global scope بشكل مختلف
            // في الـ global scope نتجاهل المتغيرات العادية
            // لأنها قد تُستخدم في أي مكان
            if (currentScope.getName().equals("global")) {
                continue;
            }

            if (!used.contains(name)) {
                addError(
                        SemanticError.ErrorType.UNUSED_VAR,
                        "Variable '" + name + "' is defined but never used",
                        sym.getLine());
            }
        }
    }

    private boolean isKnownName(String name) {
        return BUILTIN_NAMES.contains(name)
                || importedNames.contains(name)
                || currentScope.resolve(name) != null
                || functionParamCount.containsKey(name);
    }

    // =========================================================================
    // visitPythonFile - مرحلة أولى لجمع الدوال
    // =========================================================================

    @Override
    public Void visitPythonFile(PythonFileNode node) {
        // المرور الأول: جمع أسماء الدوال والـ imports
        for (AstNode child : node.getChildren()) {
            if (child instanceof ImportNode) {
                handleImport((ImportNode) child);
            } else if (child instanceof DefNode) {
                DefNode def = (DefNode) child;
                String funcName = def.getName();

                // REDEFINE_FUNC
                if (functionParamCount.containsKey(funcName)
                        && !BUILTIN_NAMES.contains(funcName)
                        && !importedNames.contains(funcName)) {
                    addError(
                            SemanticError.ErrorType.REDEFINE_FUNC,
                            "Function '" + funcName + "' is already defined",
                            def.getLine());
                } else {
                    functionParamCount.put(funcName, def.getParams().size());
                    currentScope.define(new Symbol(funcName, "function", def.getLine()));
                }
            } else if (child instanceof AssignNode) {
                // نسجّل المتغيرات العالمية من المرور الأول
                if (!child.getChildren().isEmpty()) {
                    AstNode target = child.getChildren().get(0);
                    if (target instanceof NameNode) {
                        String varName = ((NameNode) target).getName();
                        if (currentScope.resolveLocal(varName) == null) {
                            currentScope.define(new Symbol(varName, "variable",
                                    child.getLine()));
                        }
                    }
                }
            }
        }

        // المرور الثاني: التحليل الكامل
        return visitChildren(node);
    }

    private void handleImport(ImportNode node) {
        String moduleName = node.getModuleName();
        importedNames.add(moduleName);
        currentScope.define(new Symbol(moduleName, "import", node.getLine()));

        // إضافة الأسماء المستوردة من flask
        if (moduleName.toLowerCase().contains("flask")) {
            importedNames.add("Flask");
            importedNames.add("render_template");
            importedNames.add("redirect");
            importedNames.add("url_for");
            importedNames.add("request");
            importedNames.add("jsonify");
            importedNames.add("abort");
            importedNames.add("flash");
            importedNames.add("session");
        }
    }

    // =========================================================================
    // Python Statements
    // =========================================================================

    @Override
    public Void visitDef(DefNode node) {
        String funcName = node.getName();
        enterScope(funcName);

        // تعريف الـ parameters
        for (String param : node.getParams()) {
            currentScope.define(new Symbol(param, "parameter", node.getLine()));
            // نعتبر parameters مستخدمة دائماً
            markUsed(param);
        }

        // حفظ حالة الـ return
        boolean prevHasReturn = currentFuncHasReturn;
        String prevFuncName = currentFuncName;
        boolean prevSeenReturn = seenReturnInBlock;

        currentFuncHasReturn = false;
        currentFuncName = funcName;
        seenReturnInBlock = false;

        // زيارة الأبناء
        visitChildren(node);

        // MISSING_RETURN: كل دالة يجب أن تحتوي return
        if (!currentFuncHasReturn) {
            addError(
                    SemanticError.ErrorType.MISSING_RETURN,
                    "Function '" + funcName + "' does not have a return statement",
                    node.getLine());
        }

        // إعادة الحالة
        currentFuncHasReturn = prevHasReturn;
        currentFuncName = prevFuncName;
        seenReturnInBlock = prevSeenReturn;

        exitScope();
        return null;
    }

    @Override
    public Void visitAssign(AssignNode node) {
        List<AstNode> children = node.getChildren();
        if (children.isEmpty())
            return null;

        if (children.size() > 1) {
            children.get(1).accept(this);
        }

        if (seenReturnInBlock) {
            addError(
                    SemanticError.ErrorType.DEAD_CODE,
                    "Code after return statement is unreachable",
                    node.getLine());
        }

        AstNode target = children.get(0);
        if (target instanceof NameNode) {
            String varName = ((NameNode) target).getName();
            // ⭐ إذا كان معرّفاً من المرور الأول في global scope، لا نبلّغ
            Symbol existing = currentScope.resolveLocal(varName);
            if (existing == null) {
                defineSymbol(varName, "variable", node.getLine());
            }
            // إذا كان في function scope وهو variable عادي = redefine
            else if (!currentScope.getName().equals("global")
                    && existing.getType().equals("variable")) {
                addError(
                        SemanticError.ErrorType.REDEFINE_VAR,
                        "Variable '" + varName + "' is already defined in this scope"
                                + " (first defined at line " + existing.getLine() + ")",
                        node.getLine());
            }
        } else if (!(target instanceof AttrAccessNode)) {
            addError(
                    SemanticError.ErrorType.INVALID_ASSIGN,
                    "Invalid assignment target",
                    node.getLine());
        }

        return null;
    }

    @Override
    public Void visitFor(ForNode node) {
        // تعريف متغير الحلقة
        String varName = node.getVarName();
        defineSymbol(varName, "loop_variable", node.getLine());
        markUsed(varName); // loop variable دائماً "مستخدم"

        return visitChildren(node);
    }

    @Override
    public Void visitIf(IfNode node) {
        boolean prevSeenReturn = seenReturnInBlock;
        seenReturnInBlock = false; // ⭐ نصفّر لكل branch
        visitChildren(node);
        seenReturnInBlock = prevSeenReturn; // ⭐ نرجع للحالة السابقة
        return null;
    }

    @Override
    public Void visitReturn(ReturnNode node) {
        // DEAD_CODE: إذا رأينا return مسبقاً
        if (seenReturnInBlock) {
            addError(
                    SemanticError.ErrorType.DEAD_CODE,
                    "Unreachable return statement",
                    node.getLine());
        }

        currentFuncHasReturn = true;
        seenReturnInBlock = true;
        return visitChildren(node);
    }

    @Override
    public Void visitSuite(SuiteNode node) {
        boolean prevSeenReturn = seenReturnInBlock;
        seenReturnInBlock = false;
        visitChildren(node);
        // نرجع seenReturn للـ outer block إذا رأينا return هنا
        if (seenReturnInBlock) {
            // لا نرجّع prevSeenReturn لأننا في block جديد
        } else {
            seenReturnInBlock = prevSeenReturn;
        }
        return null;
    }

    @Override
    public Void visitGlobal(GlobalNode node) {
        for (AstNode child : node.getChildren()) {
            if (child instanceof NameNode) {
                String varName = ((NameNode) child).getName();
                // تحقق أن المتغير موجود فعلاً في الـ global scope
                if (globalScope.resolveLocal(varName) == null) {
                    addError(
                            SemanticError.ErrorType.UNDEFINED_VAR,
                            "Global variable '" + varName
                                    + "' is declared global but not defined in global scope",
                            node.getLine());
                }
                currentScope.define(new Symbol(varName, "global_ref", node.getLine()));
                markUsed(varName);
            }
        }
        return null;
    }

    @Override
    public Void visitImport(ImportNode node) {
        handleImport(node);
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
        String name = node.getName();
        markUsed(name);

        // ⭐ نمرر markUsed للـ parent scopes أيضاً
        Scope s = currentScope;
        while (s != null) {
            usedVars.computeIfAbsent(s.getFullPath(), k -> new HashSet<>()).add(name);
            s = s.getParent();
        }

        if (!isKnownName(name)) {
            addError(
                    SemanticError.ErrorType.UNDEFINED_VAR,
                    "Name '" + name + "' is used but not defined",
                    node.getLine());
        }

        return null;
    }

    @Override
    public Void visitCall(CallNode node) {
        List<AstNode> children = node.getChildren();
        if (children.isEmpty())
            return null;

        AstNode funcNode = children.get(0);

        // إذا الدالة هي method على object (AttrAccess) - لا نتحقق منها
        if (funcNode instanceof AttrAccessNode) {
            // نزور الـ object فقط
            List<AstNode> attrChildren = funcNode.getChildren();
            if (!attrChildren.isEmpty()) {
                attrChildren.get(0).accept(this);
            }
            // زيارة الـ arguments - من index 1
            for (int i = 1; i < children.size(); i++) {
                visitArg(children.get(i));
            }
            return null;
        }

        // دالة مباشرة
        String funcName = null;
        if (funcNode instanceof NameNode) {
            funcName = ((NameNode) funcNode).getName();
            markUsed(funcName);
        }

        if (funcName != null) {
            // UNDEFINED_FUNC
            if (!isKnownName(funcName) && !functionParamCount.containsKey(funcName)) {
                addError(
                        SemanticError.ErrorType.UNDEFINED_FUNC,
                        "Function '" + funcName + "' is called but not defined",
                        node.getLine());
            }

            // WRONG_ARG_COUNT - فقط للدوال التي عرّفها المستخدم
            if (functionParamCount.containsKey(funcName)) {
                int expected = functionParamCount.get(funcName);
                if (expected >= 0) {
                    int actual = children.size() - 1;
                    if (actual != expected) {
                        addError(
                                SemanticError.ErrorType.WRONG_ARG_COUNT,
                                "Function '" + funcName + "' expects " + expected
                                        + " argument(s) but got " + actual,
                                node.getLine());
                    }
                }
            }
        }

        // زيارة الـ arguments
        for (int i = 1; i < children.size(); i++) {
            visitArg(children.get(i));
        }

        return null;
    }

    /**
     * يزور وسيطاً واحداً في استدعاء دالة.
     * الحالة الخاصة: keyword argument (key=value) — نزور القيمة فقط،
     * لأن المفتاح (مثل products= أو methods=) اسم وسيط وليس متغيراً مُستخدَماً.
     * أما العقد المجهولة الأخرى (GeneratorExpr / ListComp) فتُترك كما هي
     * (لأنها تعرّف متغيرات comprehension خاصة بها لا يتتبعها المحلل).
     */
    private void visitArg(AstNode arg) {
        if (arg == null)
            return;
        String name = arg.getNodeName();
        if ("KeywordArg".equals(name)) {
            List<AstNode> ch = arg.getChildren();
            if (ch.size() >= 2)
                visitArg(ch.get(1)); // القيمة فقط، دون اسم المفتاح
            return;
        }
        arg.accept(this);
    }

    @Override
    public Void visitAttrAccess(AttrAccessNode node) {
        List<AstNode> children = node.getChildren();
        if (!children.isEmpty()) {
            children.get(0).accept(this);
        }
        return null;
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
    // Template / HTML / CSS
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
    public Void visitHtmlElement(HtmlElementNode n) {
        return visitChildren(n);
    }

    @Override
    public Void visitJinjaExpr(JinjaExprNode node) {
        return visitChildren(node);
    }

    @Override
    public Void visitJinjaStmt(JinjaStmtNode node) {
        return visitChildren(node);
    }

    @Override
    public Void visitCssStylesheet(CssStylesheetNode n) {
        return null;
    }

    @Override
    public Void visitCssRule(CssRuleNode node) {
        return null;
    }

    @Override
    public Void visitCssDeclaration(CssDeclarationNode n) {
        return null;
    }
}