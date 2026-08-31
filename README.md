# Compiler-The-Complete-Version

This project involves compiling Python/Jinja and rendering them into HTML templates.

## البناء والتشغيل (من جذر المشروع)

```bash
# 1) البناء
javac -encoding UTF-8 -d out -cp "lib/antlr-4.13.2-complete.jar" $(find src -name "*.java")

# 2) تشغيل المترجم كاملاً (تحليل + جدول رموز + تحليل دلالي + توليد)
java -cp "out;lib/antlr-4.13.2-complete.jar" compiler.main.TestMain

# 3) اختبارات التحقق من المتطلبات
java -cp "out;lib/antlr-4.13.2-complete.jar" compiler.test.CompilerTests

# 4) تشغيل التطبيق على http://localhost:8080/products
java -cp "out;lib/antlr-4.13.2-complete.jar" compiler.main.WebServer
#    أو النسخة التي تضيف /api/products و script.js للاستماع لتغيّر البيانات:
java -cp "out;lib/antlr-4.13.2-complete.jar" compiler.main.WebServerJS
```

> على Windows افصل بين مسارات الـ classpath بـ `;` وعلى Linux/macOS بـ `:`.

## الملفات الناتجة

| المسار | المحتوى |
| --- | --- |
| `src/compiler/main/test_app/output/` | صفحات HTML المولَّدة + الملفات المرافقة (`app.py`, `style.css`, `script.js`) |
| `compiler_output/` | `ast_python.json`, `ast_jinja.json`, `semantic_report.txt`, `generation_log.txt` |

إعادة التوليد: عند إضافة منتج أو حذفه من الواجهة يعيد الخادم (Java) توليد صفحات
`output/` من قوالب Jinja والبيانات الحالية، فتبقى الملفات المولَّدة متزامنة مع البيانات.

## بنية المشروع

| المجلد | الدور |
| --- | --- |
| `src/compiler/grammar/` | قواعد ANTLR للغات الأربع: Python, Jinja/HTML (Template), HTML, CSS |
| `src/compiler/parser/` | المحللات المولَّدة من ANTLR |
| `src/compiler/ast/` | عقد الشجرة (`AstNode` وأبناؤها) وزوّارها (طباعة، تصدير JSON) |
| `src/compiler/visitors/` | بناة الأشجار: Python, Template, CSS |
| `src/compiler/symbol/` | جدول الرموز والنطاقات |
| `src/compiler/semantic/` | التحليل الدلالي لـ Python و Jinja وبناء سياق القوالب |
| `src/compiler/codegen/` | مولّد الكود: من Python AST + Jinja AST إلى HTML |
| `src/compiler/main/` | نقاط التشغيل والتقارير والتطبيق التجريبي |
| `src/compiler/test/` | اختبارات التحقق من المتطلبات |

مخطط بنية الـ AST: [AST_DIAGRAM.md](AST_DIAGRAM.md)
