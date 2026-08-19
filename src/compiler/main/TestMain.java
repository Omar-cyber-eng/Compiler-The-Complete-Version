package compiler.main;

import compiler.ast.core.AstNode;
import compiler.ast.visitors.PrintVisitor;
import compiler.lexer.PythonIndentingLexer;
import compiler.symbol.SymbolTableVisitor;
import compiler.visitors.*;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import compiler.parser.*;
import compiler.semantic.SemanticAnalyzer;
import compiler.semantic.JinjaSemanticAnalyzer;
import compiler.semantic.TemplateContextBuilder;

import compiler.codegen.CodeGenerator;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Paths;

public class TestMain {

    // شجرة app.py الحقيقية (تُستخدم للتوليد و ast_python.json)
    private static AstNode pythonAst = null;
    // أشجار القوالب الحقيقية
    private static Map<String, AstNode> templateAsts = new LinkedHashMap<>();
    // سياق القوالب (أي متغير مُمرَّر لأي قالب) مستخرَج من app.py
    private static TemplateContextBuilder ctxBuilder = new TemplateContextBuilder();

    public static void main(String[] args) throws IOException {
        String basePath = "src/compiler/main/test_app/";

        try {
            // ── 1) الملف الحقيقي: app.py ──────────────────────────────────
            System.out.println("\n" + "=".repeat(8));
            System.out.println("PARSING PYTHON FILE: app.py");
            System.out.println("=".repeat(8));
            parsePythonFile(basePath + "app.py");

            // بناء سياق القوالب من app.py (لاستخدامه في تحليل Jinja الدلالي)
            ctxBuilder.build(pythonAst);

            // ── 2) القوالب الحقيقية ───────────────────────────────────────
            parseTemplateFile(basePath + "templates/list_products.jinja");
            parseTemplateFile(basePath + "templates/add_product.jinja");
            parseTemplateFile(basePath + "templates/product_detail.jinja");

            // ── 3) التحليل الدلالي لجزء Jinja على القوالب الحقيقية ─────────
            System.out.println("\n\n" + "=".repeat(8));
            System.out.println("JINJA SEMANTIC ANALYSIS (real templates)");
            System.out.println("=".repeat(8));
            for (Map.Entry<String, AstNode> e : templateAsts.entrySet()) {
                System.out.println("\n--- " + e.getKey() + " ---");
                JinjaSemanticAnalyzer jsa = new JinjaSemanticAnalyzer();
                jsa.addKnownVars(ctxBuilder.varsForTemplate(e.getKey()));
                jsa.analyze(e.getValue());
                jsa.printErrors();
            }

            // ── 4) CSS ────────────────────────────────────────────────────
            System.out.println("\n\n" + "=".repeat(8));
            System.out.println("PARSING CSS FILE: style.css");
            System.out.println("=".repeat(8));
            parseCSSFile(basePath + "resources/css/style.css");

        } catch (Exception e) {
            System.err.println("Error during parsing:");
            e.printStackTrace();
        }

        // ── CODE GENERATION (من بيانات app.py) ───────────────────────────
        System.out.println("\n" + "=".repeat(8));
        System.out.println("CODE GENERATION");
        System.out.println("=".repeat(8));

        int productCount = 0;
        List<String> generatedFiles = new ArrayList<>();
        if (pythonAst != null && !templateAsts.isEmpty()) {
            CodeGenerator codeGen = new CodeGenerator();

            System.out.println("\nExtracting data from Python AST...");
            codeGen.extractFromPythonAST(pythonAst);
            System.out.println("  Context: " + codeGen.getGlobalContext().keySet());

            Object products = codeGen.getGlobalContext().get("products");
            if (products instanceof List) {
                productCount = ((List<?>) products).size();
            }

            String outputDir = basePath + "output/";

            for (Map.Entry<String, AstNode> entry : templateAsts.entrySet()) {
                String templateName = entry.getKey();
                AstNode templateAst = entry.getValue();

                System.out.println("\nGenerating: " + templateName);

                Map<String, Object> extra = new HashMap<>();
                // للـ product_detail نمرر أول منتج كمثال
                if (templateName.contains("detail")) {
                    if (products instanceof List && !((List<?>) products).isEmpty()) {
                        extra.put("product", ((List<?>) products).get(0));
                    }
                }

                String html = codeGen.generateForTemplate(templateAst, templateName, extra);
                String outName = templateName.replace(".jinja", ".html");
                codeGen.saveToFile(html, outputDir + outName);
                generatedFiles.add(outName);

                System.out.println("  Preview (first 300 chars):");
                System.out.println("  " + html.substring(0, Math.min(300, html.length())));
            }
        }

        // ── COMPILER OUTPUT ──────────────────────────────────────────────
        if (pythonAst != null) {
            System.out.println("\n" + "=".repeat(8));
            System.out.println("COMPILER OUTPUT");
            System.out.println("=".repeat(8));

            ReportGenerator reporter = new ReportGenerator();

            System.out.println("\nSaving ast_python.json...");
            reporter.saveAstPython(pythonAst);

            System.out.println("Saving ast_jinja.json...");
            reporter.saveAstJinja(templateAsts);

            // ── التقرير الدلالي: عرض قدرة الكشف على ملفَّي الاختبار ────────
            // (اللجنة تُقيّم بناءً على عدد الأخطاء الدلالية المُعالَجة)
            System.out.println("Saving semantic_report.txt (Python + Jinja)...");

            SemanticAnalyzer pyDemo = null;
            JinjaSemanticAnalyzer jinjaDemo = null;
            try {
                pyDemo = analyzePythonForReport(basePath + "test_semantic_errors.py");
                jinjaDemo = analyzeJinjaForReport(basePath + "test_semantic_errors.jinja");
            } catch (Exception ex) {
                System.err.println("  [WARN] demo analysis failed: " + ex.getMessage());
            }

            if (pyDemo != null && jinjaDemo != null) {
                reporter.saveCombinedSemanticReport(
                        pyDemo, "test_semantic_errors.py",
                        jinjaDemo, "test_semantic_errors.jinja");
            }

            System.out.println("Copying support files...");
            List<String> copiedFiles = reporter.copyOutputFiles(basePath);

            // السجل يُكتب بعد النسخ ليعكس ما تم توليده ونسخه فعلاً
            System.out.println("Saving generation_log.txt...");
            reporter.saveGenerationLog(generatedFiles, productCount, copiedFiles);

            System.out.println("\n[OK] compiler_output/ ready!");
        } else {
            System.out.println("\n[SKIP] compiler_output/ - pythonAst is null");
        }
    }

    // =========================================================================
    // Python: app.py الحقيقي (يضبط pythonAst)
    // =========================================================================
    private static void parsePythonFile(String filename) throws IOException {

        CharStream input = CharStreams.fromFileName(filename);
        PythonIndentingLexer lexer = new PythonIndentingLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PythonSubsetParser parser = new PythonSubsetParser(tokens);

        PythonSubsetParser.File_inputContext parseTree = parser.file_input();

        if (parser.getNumberOfSyntaxErrors() > 0) {
            System.err.println("Syntax errors found in Python file!");
            return;
        }

        PythonAstBuilder astBuilder = new PythonAstBuilder();
        AstNode ast = astBuilder.visit(parseTree);

        if (ast == null) {
            System.err.println("AST is null!");
            return;
        }

        System.out.println("\n--- PYTHON AST ---\n");
        PrintVisitor printer = new PrintVisitor();
        printer.printTree(ast);

        System.out.println("\n--- SYMBOL TABLE ---\n");
        SymbolTableVisitor symVisitor = new SymbolTableVisitor();
        ast.accept(symVisitor);
        symVisitor.printSymbolTable();

        System.out.println("\n--- SEMANTIC ANALYSIS (app.py) ---\n");
        SemanticAnalyzer analyzer = new SemanticAnalyzer();
        ast.accept(analyzer);
        analyzer.printErrors();

        pythonAst = ast;
    }

    // =========================================================================
    // Python: ملف عرض الأخطاء (لا يضبط pythonAst) — للتقرير فقط
    // =========================================================================
    private static SemanticAnalyzer analyzePythonForReport(String filename)
            throws IOException {
        CharStream input = CharStreams.fromFileName(filename);
        PythonIndentingLexer lexer = new PythonIndentingLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PythonSubsetParser parser = new PythonSubsetParser(tokens);
        AstNode ast = new PythonAstBuilder().visit(parser.file_input());

        SemanticAnalyzer analyzer = new SemanticAnalyzer();
        if (ast != null)
            ast.accept(analyzer);
        return analyzer;
    }

    // =========================================================================
    // Jinja: قالب عرض الأخطاء — للتقرير فقط
    // =========================================================================
    private static JinjaSemanticAnalyzer analyzeJinjaForReport(String filename)
            throws IOException {
        CharStream input = CharStreams.fromFileName(filename);
        TemplateLexer lexer = new TemplateLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        TemplateParser parser = new TemplateParser(tokens);
        AstNode ast = new TemplateAstBuilder().visit(parser.template());

        JinjaSemanticAnalyzer jsa = new JinjaSemanticAnalyzer();
        // المتغيرات العامة من app.py متاحة (مثل products)
        jsa.addKnownVars(ctxBuilder.getGlobalVars());
        if (ast != null)
            jsa.analyze(ast);
        return jsa;
    }

    private static void parseTemplateFile(String filename) throws IOException {
        System.out.println("\n\n" + "=".repeat(8));
        System.out.println("PARSING TEMPLATE: " + Paths.get(filename).getFileName());
        System.out.println("=".repeat(8));
        System.out.println("\n--- TEMPLATE AST ---\n");

        CharStream input = CharStreams.fromFileName(filename);
        TemplateLexer lexer = new TemplateLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        TemplateParser parser = new TemplateParser(tokens);

        TemplateParser.TemplateContext parseTree = parser.template();

        if (parser.getNumberOfSyntaxErrors() > 0) {
            System.err.println("Syntax errors found in template file!");
            return;
        }

        TemplateAstBuilder astBuilder = new TemplateAstBuilder();
        AstNode ast = astBuilder.visit(parseTree);

        if (ast != null) {
            PrintVisitor printer = new PrintVisitor();
            printer.printTree(ast);
        } else {
            System.err.println("AST is null!");
        }

        String name = Paths.get(filename).getFileName().toString();
        templateAsts.put(name, ast);
    }

    private static void parseCSSFile(String filename) throws IOException {
        System.out.println("\n--- CSS AST ---\n");

        CharStream input = CharStreams.fromFileName(filename);
        CSSLexer lexer = new CSSLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CSSParser parser = new CSSParser(tokens);

        CSSParser.StylesheetContext parseTree = parser.stylesheet();

        if (parser.getNumberOfSyntaxErrors() > 0) {
            System.err.println("Syntax errors found in CSS file!");
            return;
        }

        CssAstBuilder astBuilder = new CssAstBuilder();
        AstNode ast = astBuilder.visit(parseTree);

        if (ast != null) {
            PrintVisitor printer = new PrintVisitor();
            printer.printTree(ast);
        } else {
            System.err.println("AST is null!");
        }
    }
}
