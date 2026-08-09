package compiler.main;

import compiler.ast.core.AstNode;
import compiler.ast.visitors.JsonExporter;
import compiler.semantic.SemanticAnalyzer;
import compiler.semantic.SemanticAnalyzer.SemanticError;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * يولّد كل ملفات compiler_output/
 */
public class ReportGenerator {

    private static final String OUTPUT_DIR = "compiler_output/";
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final JsonExporter jsonExporter = new JsonExporter();

    // ── 1. ast_python.json ────────────────────────────────────────────
    public void saveAstPython(AstNode pythonAst) {
        jsonExporter.saveToFile(pythonAst,
                OUTPUT_DIR + "ast_python.json");
    }

    // ── 2. ast_jinja.json ─────────────────────────────────────────────
    public void saveAstJinja(Map<String, AstNode> templateAsts) {
        // ندمج كل الـ templates في JSON واحد
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("  \"type\": \"JinjaTemplates\",\n");
        sb.append("  \"generated\": \"")
                .append(LocalDateTime.now().format(FMT)).append("\",\n");
        sb.append("  \"templates\": {\n");

        int i = 0;
        for (Map.Entry<String, AstNode> entry : templateAsts.entrySet()) {
            sb.append("    \"").append(entry.getKey()).append("\": ");
            String json = jsonExporter.export(entry.getValue());
            // indent كل سطر بـ 4 مسافات
            json = json.replace("\n", "\n    ");
            sb.append(json);
            if (i < templateAsts.size() - 1)
                sb.append(",");
            sb.append("\n");
            i++;
        }

        sb.append("  }\n");
        sb.append("}");

        saveString(sb.toString(), OUTPUT_DIR + "ast_jinja.json");
        System.out.println("  ✅ Saved: " + OUTPUT_DIR + "ast_jinja.json");
    }

    // ── 3. semantic_report.txt ────────────────────────────────────────
    public void saveSemanticReport(SemanticAnalyzer analyzer,
            String fileName) {
        StringBuilder sb = new StringBuilder();
        sb.append("=".repeat(50)).append("\n");
        sb.append("    SEMANTIC ANALYSIS REPORT\n");
        sb.append("=".repeat(50)).append("\n");
        sb.append("Date     : ")
                .append(LocalDateTime.now().format(FMT)).append("\n");
        sb.append("File     : ").append(fileName).append("\n");
        sb.append("Checks   : 9 types of semantic errors\n");
        sb.append("-".repeat(50)).append("\n\n");

        // ← هنا التغيير
        List<SemanticError> errors = analyzer.getErrors();

        if (errors.isEmpty()) {
            sb.append("Status   : ✅ No semantic errors found.\n\n");
        } else {
            sb.append("Status   : ❌ Found ")
                    .append(errors.size()).append(" error(s)\n\n");
            sb.append("Errors:\n");
            for (int i = 0; i < errors.size(); i++) {
                sb.append("  [").append(i + 1).append("] ")
                        .append(errors.get(i).toString()) // ← .toString()
                        .append("\n");
            }
        }

        sb.append("\n").append("-".repeat(50)).append("\n");
        sb.append("Error Types Checked:\n");
        sb.append("  [1] UNDEFINED_VAR    - متغير غير معرّف\n");
        sb.append("  [2] UNDEFINED_FUNC   - دالة غير معرّفة\n");
        sb.append("  [3] REDEFINE_FUNC    - إعادة تعريف دالة\n");
        sb.append("  [4] WRONG_ARG_COUNT  - عدد arguments خاطئ\n");
        sb.append("  [5] MISSING_RETURN   - دالة بدون return\n");
        sb.append("  [6] REDEFINE_VAR     - إعادة تعريف متغير\n");
        sb.append("  [7] UNUSED_VAR       - متغير غير مستخدم\n");
        sb.append("  [8] DEAD_CODE        - كود بعد return\n");
        sb.append("  [9] INVALID_ASSIGN   - تعيين غير صحيح\n");
        sb.append("=".repeat(50)).append("\n");

        saveString(sb.toString(), OUTPUT_DIR + "semantic_report.txt");
        System.out.println("  ✅ Saved: " + OUTPUT_DIR + "semantic_report.txt");
    }

    // ── 3b. semantic_report.txt (Python + Jinja معاً) ─────────────────
    public void saveCombinedSemanticReport(
            SemanticAnalyzer pythonAnalyzer, String pythonFile,
            compiler.semantic.JinjaSemanticAnalyzer jinjaAnalyzer, String jinjaFile) {

        StringBuilder sb = new StringBuilder();
        sb.append("=".repeat(50)).append("\n");
        sb.append("    SEMANTIC ANALYSIS REPORT\n");
        sb.append("=".repeat(50)).append("\n");
        sb.append("Date     : ")
                .append(LocalDateTime.now().format(FMT)).append("\n");
        sb.append("-".repeat(50)).append("\n\n");

        // ── PART 1: Python ────────────────────────────────────────────
        sb.append("### PART 1 - PYTHON (Flask) ###\n");
        sb.append("File   : ").append(pythonFile).append("\n");
        List<SemanticError> pyErrors = pythonAnalyzer.getErrors();
        if (pyErrors.isEmpty()) {
            sb.append("Status : ✅ No semantic errors found.\n\n");
        } else {
            sb.append("Status : ❌ Found ")
                    .append(pyErrors.size()).append(" error(s)\n\n");
            for (int i = 0; i < pyErrors.size(); i++) {
                sb.append("  [").append(i + 1).append("] ")
                        .append(pyErrors.get(i).toString()).append("\n");
            }
            sb.append("\n");
        }

        // ── PART 2: Jinja ─────────────────────────────────────────────
        sb.append("### PART 2 - JINJA (Templates) ###\n");
        sb.append("File   : ").append(jinjaFile).append("\n");
        var jErrors = jinjaAnalyzer.getErrors();
        if (jErrors.isEmpty()) {
            sb.append("Status : ✅ No semantic errors found.\n\n");
        } else {
            sb.append("Status : ❌ Found ")
                    .append(jErrors.size()).append(" error(s)\n\n");
            for (int i = 0; i < jErrors.size(); i++) {
                sb.append("  [").append(i + 1).append("] ")
                        .append(jErrors.get(i).toString()).append("\n");
            }
            sb.append("\n");
        }

        sb.append("-".repeat(50)).append("\n");
        sb.append("Python error types (9): UNDEFINED_VAR, UNDEFINED_FUNC,\n");
        sb.append("  REDEFINE_FUNC, WRONG_ARG_COUNT, MISSING_RETURN,\n");
        sb.append("  REDEFINE_VAR, UNUSED_VAR, DEAD_CODE, INVALID_ASSIGN\n");
        sb.append("Jinja error types (5): UNDEFINED_VARIABLE, UNDEFINED_ITERABLE,\n");
        sb.append("  LOOP_VAR_OUT_OF_SCOPE, UNKNOWN_FILTER, UNDEFINED_FUNCTION\n");
        sb.append("=".repeat(50)).append("\n");

        saveString(sb.toString(), OUTPUT_DIR + "semantic_report.txt");
        System.out.println("  [OK] Saved: " + OUTPUT_DIR + "semantic_report.txt");
    }

    // ── 4. generation_log.txt ─────────────────────────────────────────
    public void saveGenerationLog(List<String> generatedFiles,
            int productCount) {
        StringBuilder sb = new StringBuilder();
        sb.append("=".repeat(50)).append("\n");
        sb.append("    CODE GENERATION LOG\n");
        sb.append("=".repeat(50)).append("\n");
        sb.append("Date      : ")
                .append(LocalDateTime.now().format(FMT)).append("\n");
        sb.append("-".repeat(50)).append("\n\n");

        sb.append("Step [1] - Extract data from Python AST\n");
        sb.append("  → Products extracted: ")
                .append(productCount).append(" items\n\n");

        sb.append("Step [2] - Generate HTML from Jinja templates\n");
        for (int i = 0; i < generatedFiles.size(); i++) {
            sb.append("  [").append(i + 1).append("] ")
                    .append(generatedFiles.get(i)).append(" → OK ✅\n");
        }

        sb.append("\nStep [3] - Copy support files\n");
        sb.append("  [1] app.py    → output/ ✅\n");
        sb.append("  [2] style.css → output/ ✅\n");

        sb.append("\n").append("-".repeat(50)).append("\n");
        sb.append("Total files generated : ")
                .append(generatedFiles.size()).append("\n");
        sb.append("Status               : ✅ Success\n");
        sb.append("=".repeat(50)).append("\n");

        saveString(sb.toString(), OUTPUT_DIR + "generation_log.txt");
        System.out.println("  ✅ Saved: " + OUTPUT_DIR + "generation_log.txt");
    }

    // ── Helper ────────────────────────────────────────────────────────
    private void saveString(String content, String filePath) {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            try (Writer w = new OutputStreamWriter(
                    new FileOutputStream(file),
                    StandardCharsets.UTF_8)) {
                w.write(content);
            }
        } catch (IOException e) {
            System.err.println("❌ Failed to save: " + filePath);
            e.printStackTrace();
        }
    }

    // أضف هذه الدالة
    public void copyOutputFiles(String basePath) {
        try {
            String outputDir = basePath + "output/";
            new File(outputDir).mkdirs();

            // نسخ app.py
            Files.copy(
                    Paths.get(basePath + "app.py"),
                    Paths.get(outputDir + "app.py"),
                    StandardCopyOption.REPLACE_EXISTING);
            System.out.println("  [OK] Copied: app.py -> output/");

            // نسخ style.css
            Files.copy(
                    Paths.get(basePath + "resources/css/style.css"),
                    Paths.get(outputDir + "style.css"),
                    StandardCopyOption.REPLACE_EXISTING);
            System.out.println("  [OK] Copied: style.css -> output/");

            // نسخ script.js إن وُجد (ملف مرافق اختياري حسب ملاحظة المعيدة)
            Path scriptSrc = Paths.get(basePath + "resources/js/script.js");
            if (Files.exists(scriptSrc)) {
                Files.copy(scriptSrc,
                        Paths.get(outputDir + "script.js"),
                        StandardCopyOption.REPLACE_EXISTING);
                System.out.println("  [OK] Copied: script.js -> output/");
            }

        } catch (IOException e) {
            System.err.println("  [ERROR] Copy failed: " + e.getMessage());
        }
    }
}