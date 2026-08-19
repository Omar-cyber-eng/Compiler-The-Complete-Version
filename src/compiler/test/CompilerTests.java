package compiler.test;

import compiler.ast.core.AstNode;
import compiler.ast.nodes.TemplateNode;
import compiler.ast.nodes.html.HtmlElementNode;
import compiler.ast.nodes.python.PythonFileNode;
import compiler.ast.visitors.PrintVisitor;
import compiler.codegen.CodeGenerator;
import compiler.lexer.PythonIndentingLexer;
import compiler.parser.*;
import compiler.semantic.JinjaSemanticAnalyzer;
import compiler.semantic.SemanticAnalyzer;
import compiler.semantic.TemplateContextBuilder;
import compiler.symbol.Scope;
import compiler.symbol.Symbol;
import compiler.symbol.SymbolTableVisitor;
import compiler.visitors.CssAstBuilder;
import compiler.visitors.PythonAstBuilder;
import compiler.visitors.TemplateAstBuilder;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.*;

/**
 * مجموعة اختبارات للتحقق من متطلبات المشروع السبعة.
 *
 * التشغيل من جذر المشروع:
 * java -cp "out;lib/antlr-4.13.2-complete.jar" compiler.test.CompilerTests
 *
 * كل اختبار يطبع PASS أو FAIL، وتنتهي العملية برمز خروج 1 عند أي فشل.
 */
public class CompilerTests {

    private static final String BASE = "src/compiler/main/test_app/";

    private static int passed = 0;
    private static int failed = 0;

    // =========================================================================
    // أدوات الاختبار
    // =========================================================================

    private static void check(String name, boolean ok) {
        if (ok) {
            passed++;
            System.out.println("  [PASS] " + name);
        } else {
            failed++;
            System.out.println("  [FAIL] " + name);
        }
    }

    private static void checkEquals(String name, Object expected, Object actual) {
        boolean ok = Objects.equals(expected, actual);
        if (ok) {
            passed++;
            System.out.println("  [PASS] " + name);
        } else {
            failed++;
            System.out.println("  [FAIL] " + name
                    + "\n         expected: " + expected
                    + "\n         actual  : " + actual);
        }
    }

    private static void section(String title) {
        System.out.println("\n=== " + title + " ===");
    }

    // =========================================================================
    // مساعدات التحليل
    // =========================================================================

    private static AstNode parsePython(String path) throws Exception {
        var lexer = new PythonIndentingLexer(CharStreams.fromFileName(path));
        var parser = new PythonSubsetParser(new CommonTokenStream(lexer));
        return new PythonAstBuilder().visit(parser.file_input());
    }

    private static AstNode parseTemplateFile(String path) throws Exception {
        var lexer = new TemplateLexer(CharStreams.fromFileName(path));
        var parser = new TemplateParser(new CommonTokenStream(lexer));
        return new TemplateAstBuilder().visit(parser.template());
    }

    /** يحلّل نص قالب مباشرة (يُستخدم لاختبار ميزات Jinja) */
    private static AstNode parseTemplateText(String source) {
        var lexer = new TemplateLexer(CharStreams.fromString(source));
        var parser = new TemplateParser(new CommonTokenStream(lexer));
        return new TemplateAstBuilder().visit(parser.template());
    }

    private static String render(String source, Map<String, Object> context) {
        return new CodeGenerator().generateHTML(parseTemplateText(source), context);
    }

    private static Map<String, Object> demoContext() {
        Map<String, Object> laptop = new LinkedHashMap<>();
        laptop.put("id", 1);
        laptop.put("name", "Laptop");
        laptop.put("price", 1200);
        laptop.put("image", "images/img.png");

        Map<String, Object> phone = new LinkedHashMap<>();
        phone.put("id", 2);
        phone.put("name", "Phone");
        phone.put("price", 800);
        phone.put("image", "images/img.png");

        Map<String, Object> ctx = new HashMap<>();
        ctx.put("products", new ArrayList<>(List.of(laptop, phone)));
        ctx.put("product", laptop);
        return ctx;
    }

    // =========================================================================
    // main
    // =========================================================================

    public static void main(String[] args) throws Exception {
        System.out.println("=========================================");
        System.out.println("   COMPILER TEST SUITE");
        System.out.println("=========================================");

        testParsersAndAst();
        testNodeStructure();
        testSymbolTable();
        testPythonSemantics();
        testJinjaSemantics();
        testCodeGeneration();
        testEndToEndPages();
        testPrinting();

        System.out.println("\n=========================================");
        System.out.println("   PASSED: " + passed + " | FAILED: " + failed);
        System.out.println("=========================================");

        if (failed > 0)
            System.exit(1);
    }

    // ── (1) القواعد والمحللات: Python / Jinja / HTML / CSS ────────────────────
    private static void testParsersAndAst() throws Exception {
        section("1. Lexers & Parsers");

        AstNode pythonAst = parsePython(BASE + "app.py");
        check("app.py builds a Python AST", pythonAst instanceof PythonFileNode);
        check("Python AST has statements", !pythonAst.getChildren().isEmpty());

        AstNode templateAst = parseTemplateFile(BASE + "templates/list_products.jinja");
        check("list_products.jinja builds a Template AST", templateAst instanceof TemplateNode);

        var cssLexer = new CSSLexer(CharStreams.fromFileName(BASE + "resources/css/style.css"));
        var cssParser = new CSSParser(new CommonTokenStream(cssLexer));
        var cssTree = cssParser.stylesheet();
        AstNode cssAst = new CssAstBuilder().visit(cssTree);
        check("style.css parses without syntax errors", cssParser.getNumberOfSyntaxErrors() == 0);
        check("style.css builds a CSS AST", cssAst != null && !cssAst.getChildren().isEmpty());

        check("HTML elements are parsed inside templates",
                countNodes(templateAst, HtmlElementNode.class) > 5);

        // تعليقات Jinja: {# ... #}
        var lexer = new TemplateLexer(CharStreams.fromString("<p>a</p>{# hidden #}<p>b</p>"));
        var parser = new TemplateParser(new CommonTokenStream(lexer));
        parser.template();
        checkEquals("Jinja comments {# .. #} parse cleanly", 0, parser.getNumberOfSyntaxErrors());
    }

    // ── (3) بنية العقد: وراثة، تعدد أشكال، اسم العقدة ورقم السطر ─────────────
    private static void testNodeStructure() throws Exception {
        section("3. Node structure (OOP / inheritance / polymorphism)");

        AstNode templateAst = parseTemplateFile(BASE + "templates/product_detail.jinja");

        boolean allNamed = true;
        boolean allLines = true;
        for (AstNode node : flatten(templateAst)) {
            if (node.getNodeName() == null || node.getNodeName().isEmpty())
                allNamed = false;
            if (node.getLine() <= 0)
                allLines = false;
        }
        check("every node carries a node name", allNamed);
        check("every node carries a line number", allLines);

        // تعدد الأشكال: accept() يوجّه كل عقدة إلى تابع الزيارة الخاص بها
        String printed = new PrintVisitor().getTreeString(templateAst);
        check("visitor dispatch reaches HtmlElement nodes", printed.contains("HtmlElement"));
        check("visitor dispatch reaches Jinja nodes", printed.contains("JinjaExpr"));
    }

    // ── جدول الرموز ───────────────────────────────────────────────────────────
    private static void testSymbolTable() throws Exception {
        section("Symbol table");

        AstNode pythonAst = parsePython(BASE + "app.py");
        SymbolTableVisitor visitor = new SymbolTableVisitor();
        pythonAst.accept(visitor);
        Scope global = visitor.getGlobalScope();

        Symbol products = global.resolveLocal("products");
        check("global 'products' is in the symbol table", products != null);
        checkEquals("'products' keeps its definition line (not the 'global' statement)",
                5, products != null ? products.getLine() : -1);

        Scope deleteScope = null;
        for (Scope child : global.getChildren()) {
            if (child.getName().equals("delete_product"))
                deleteScope = child;
        }
        check("function scopes are created", deleteScope != null);
        if (deleteScope != null) {
            Symbol local = deleteScope.resolveLocal("products");
            check("'global products' is recorded as a reference, not a new local",
                    local != null && local.getType().equals("global_ref"));
            check("function parameters are recorded",
                    deleteScope.resolveLocal("pid") != null);
        }
    }

    // ── (4أ) التحليل الدلالي: Python ──────────────────────────────────────────
    private static void testPythonSemantics() throws Exception {
        section("4a. Python semantic analysis");

        AstNode errorsAst = parsePython(BASE + "test_semantic_errors.py");
        SemanticAnalyzer analyzer = new SemanticAnalyzer();
        errorsAst.accept(analyzer);

        Set<SemanticAnalyzer.SemanticError.ErrorType> types = new HashSet<>();
        for (var e : analyzer.getErrors())
            types.add(e.getType());

        check("detects at least 5 kinds of Python semantic errors (found "
                + types.size() + ")", types.size() >= 5);
        for (String expected : List.of("UNDEFINED_VAR", "UNDEFINED_FUNC", "REDEFINE_FUNC",
                "WRONG_ARG_COUNT", "MISSING_RETURN", "REDEFINE_VAR", "UNUSED_VAR", "DEAD_CODE")) {
            check("detects " + expected,
                    types.contains(SemanticAnalyzer.SemanticError.ErrorType.valueOf(expected)));
        }

        // الملف الحقيقي يجب أن يكون سليماً
        AstNode appAst = parsePython(BASE + "app.py");
        SemanticAnalyzer appAnalyzer = new SemanticAnalyzer();
        appAst.accept(appAnalyzer);
        check("app.py has no false-positive semantic errors "
                + appAnalyzer.getErrors(), appAnalyzer.getErrors().isEmpty());
    }

    // ── (4ب) التحليل الدلالي: Jinja ───────────────────────────────────────────
    private static void testJinjaSemantics() throws Exception {
        section("4b. Jinja semantic analysis");

        AstNode pythonAst = parsePython(BASE + "app.py");
        TemplateContextBuilder ctx = new TemplateContextBuilder();
        ctx.build(pythonAst);

        check("render_template context is extracted from app.py",
                ctx.varsForTemplate("product_detail.jinja").contains("product"));

        AstNode errorsAst = parseTemplateFile(BASE + "test_semantic_errors.jinja");
        JinjaSemanticAnalyzer analyzer = new JinjaSemanticAnalyzer();
        analyzer.addKnownVars(ctx.getGlobalVars());
        analyzer.analyze(errorsAst);

        Set<JinjaSemanticAnalyzer.JinjaSemanticError.ErrorType> types = new HashSet<>();
        for (var e : analyzer.getErrors())
            types.add(e.getType());

        check("detects at least 5 kinds of Jinja semantic errors (found "
                + types.size() + ")", types.size() >= 5);
        for (String expected : List.of("UNDEFINED_VARIABLE", "UNDEFINED_ITERABLE",
                "LOOP_VAR_OUT_OF_SCOPE", "UNKNOWN_FILTER", "UNDEFINED_FUNCTION")) {
            check("detects " + expected, types.contains(
                    JinjaSemanticAnalyzer.JinjaSemanticError.ErrorType.valueOf(expected)));
        }

        // تعابير داخل قيم الـ attributes يجب ألا تفلت من الفحص
        JinjaSemanticAnalyzer attrAnalyzer = new JinjaSemanticAnalyzer();
        attrAnalyzer.addKnownVar("products");
        attrAnalyzer.analyze(parseTemplateText("<a href=\"/x/{{ ghost.id }}\">t</a>"));
        check("undefined variables inside attributes are reported",
                !attrAnalyzer.getErrors().isEmpty());

        // القوالب الحقيقية يجب أن تكون سليمة
        for (String name : List.of("list_products.jinja", "add_product.jinja",
                "product_detail.jinja")) {
            JinjaSemanticAnalyzer real = new JinjaSemanticAnalyzer();
            real.addKnownVars(ctx.varsForTemplate(name));
            real.analyze(parseTemplateFile(BASE + "templates/" + name));
            check(name + " has no false-positive errors " + real.getErrors(),
                    real.getErrors().isEmpty());
        }
    }

    // ── (2 + 5) تمرير البيانات وتوليد الكود ──────────────────────────────────
    private static void testCodeGeneration() throws Exception {
        section("2 & 5. Data passing + code generation");

        AstNode pythonAst = parsePython(BASE + "app.py");
        CodeGenerator generator = new CodeGenerator();
        generator.extractFromPythonAST(pythonAst);

        Object products = generator.getGlobalContext().get("products");
        check("products array is extracted from the Python AST", products instanceof List);
        checkEquals("both demo products are extracted", 2,
                (products instanceof List) ? ((List<?>) products).size() : -1);

        Map<String, Object> ctx = demoContext();

        checkEquals("{% for %} loops over the data",
                "<li>Laptop</li>\n<li>Phone</li>\n",
                render("{% for p in products %}<li>{{ p.name }}</li>{% endfor %}", ctx));

        checkEquals("{% if %} with a plain name condition renders its body",
                "<b>yes</b>\n",
                render("{% if products %}<b>yes</b>{% endif %}", ctx));

        checkEquals("{% elif %} branch is selected", "B",
                render("{% if product.price > 5000 %}A"
                        + "{% elif product.price > 1000 %}B{% else %}C{% endif %}", ctx));

        Map<String, Object> emptyCtx = new HashMap<>();
        emptyCtx.put("products", new ArrayList<>());
        checkEquals("{% else %} branch is rendered", "empty",
                render("{% if products %}full{% else %}empty{% endif %}", emptyCtx));

        checkEquals("{% if not x %} is supported", "empty",
                render("{% if not products %}empty{% endif %}", emptyCtx));

        checkEquals("filters are applied", "<p>LAPTOP</p>\n",
                render("<p>{{ product.name | upper }}</p>", ctx));

        checkEquals("length filter counts collections", "<p>2</p>\n",
                render("<p>{{ products | length }}</p>", ctx));

        checkEquals("loop.index is available", "1:Laptop 2:Phone ",
                render("{% for p in products %}{{ loop.index }}:{{ p.name }} {% endfor %}", ctx));

        checkEquals("each attribute resolves its own expression",
                "<img src=\"images/img.png\" alt=\"Laptop\">\n",
                render("<img src=\"{{ product.image }}\" alt=\"{{ product.name }}\">", ctx));

        checkEquals("text spacing around expressions is preserved",
                "<p>Price: 1200 $</p>\n",
                render("<p>Price: {{ product.price }} $</p>", ctx));

        Map<String, Object> evilCtx = new HashMap<>();
        Map<String, Object> evil = new LinkedHashMap<>();
        evil.put("name", "<script>alert(1)</script>");
        evilCtx.put("product", evil);
        checkEquals("injected values are HTML-escaped",
                "<p>&lt;script&gt;alert(1)&lt;/script&gt;</p>\n",
                render("<p>{{ product.name }}</p>", evilCtx));

        checkEquals("| safe keeps raw HTML", "<p><b>x</b></p>\n",
                render("<p>{{ raw | safe }}</p>", Map.of("raw", "<b>x</b>")));
    }

    // ── (6) الصفحات الناتجة والتنقل ──────────────────────────────────────────
    private static void testEndToEndPages() throws Exception {
        section("6. Generated pages & navigation");

        AstNode pythonAst = parsePython(BASE + "app.py");
        CodeGenerator generator = new CodeGenerator();
        generator.extractFromPythonAST(pythonAst);

        Object products = generator.getGlobalContext().get("products");
        Map<String, Object> extra = new HashMap<>();
        if (products instanceof List<?> list && !list.isEmpty())
            extra.put("product", list.get(0));

        String listPage = generator.generateForTemplate(
                parseTemplateFile(BASE + "templates/list_products.jinja"),
                "list_products.jinja", new HashMap<>());
        String addPage = generator.generateForTemplate(
                parseTemplateFile(BASE + "templates/add_product.jinja"),
                "add_product.jinja", new HashMap<>());
        String detailPage = generator.generateForTemplate(
                parseTemplateFile(BASE + "templates/product_detail.jinja"),
                "product_detail.jinja", extra);

        for (Map.Entry<String, String> page : Map.of(
                "list_products.html", listPage,
                "add_product.html", addPage,
                "product_detail.html", detailPage).entrySet()) {
            check(page.getKey() + " has no unresolved Jinja placeholders",
                    !page.getValue().contains("{{"));
            check(page.getKey() + " links the stylesheet",
                    page.getValue().contains("/static/css/style.css"));
        }

        check("list page shows the products", listPage.contains("Laptop")
                && listPage.contains("Phone"));
        check("list page links to product details", listPage.contains("href=\"/products/1\""));
        check("list page has a delete form per product",
                listPage.contains("action=\"/delete/1\"") && listPage.contains("action=\"/delete/2\""));
        check("list page links to the add page", listPage.contains("href=\"/add\""));
        check("add page posts a form", addPage.contains("method=\"post\""));
        check("add page links back to the list", addPage.contains("href=\"/products\""));
        check("detail page shows the product", detailPage.contains("Laptop"));
        check("detail page links back to the list", detailPage.contains("href=\"/products\""));
    }

    // ── (7) الطباعة ──────────────────────────────────────────────────────────
    private static void testPrinting() throws Exception {
        section("7. Printing");

        AstNode pythonAst = parsePython(BASE + "app.py");
        String printed = new PrintVisitor().getTreeString(pythonAst);

        check("tree printer produces output", printed.length() > 100);
        check("printed nodes include line numbers", printed.contains("(line "));
        check("printed tree is indented (children under parents)", printed.contains("\n  "));
        check("printed tree covers functions", printed.contains("Def"));

        AstNode templateAst = parseTemplateFile(BASE + "templates/list_products.jinja");
        String templatePrinted = new PrintVisitor().getTreeString(templateAst);
        check("template printer shows attributes", templatePrinted.contains("attrs=["));
    }

    // =========================================================================
    // أدوات مساعدة على الشجرة
    // =========================================================================

    private static List<AstNode> flatten(AstNode root) {
        List<AstNode> all = new ArrayList<>();
        Deque<AstNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            AstNode node = stack.pop();
            all.add(node);
            for (AstNode child : node.getChildren())
                stack.push(child);
        }
        return all;
    }

    private static int countNodes(AstNode root, Class<?> type) {
        int count = 0;
        for (AstNode node : flatten(root)) {
            if (type.isInstance(node))
                count++;
        }
        return count;
    }
}
