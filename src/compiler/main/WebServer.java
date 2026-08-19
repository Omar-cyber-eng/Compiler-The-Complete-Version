package compiler.main;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import compiler.ast.core.AstNode;
import compiler.codegen.CodeGenerator;
import compiler.lexer.PythonIndentingLexer;
import compiler.parser.*;
import compiler.visitors.*;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class WebServer {

    // ── البيانات الحية في الذاكرة ──────────────────────────────────────
    private static List<Map<String, Object>> products = new ArrayList<>();
    private static int nextId = 1;

    // ── المسارات ───────────────────────────────────────────────────────
    static final String BASE = "src/compiler/main/test_app/";
    static final String TEMPLATES = BASE + "templates/";
    static final String GENERATED = BASE + "output/";
    static final String RESOURCES = BASE + "resources/";

    // ── CodeGenerator ──────────────────────────────────────────────────
    private static CodeGenerator codeGen = new CodeGenerator();

    // ═══════════════════════════════════════════════════════════════════
    public static void main(String[] args) throws Exception {

        // 1) تحميل البيانات الأولية من app.py
        loadInitialData();

        // 2) توليد أولي لملفات output/ من البيانات المحمّلة
        regenerateOutputFiles("startup");

        // 3) إنشاء السيرفر على port 8080
        HttpServer server = HttpServer.create(
                new InetSocketAddress(8080), 0);

        // 4) تسجيل المسارات
        server.createContext("/", new RootHandler());
        server.createContext("/products/", new ProductDetailHandler());
        server.createContext("/products", new ProductsHandler());
        server.createContext("/add", new AddHandler());
        server.createContext("/delete/", new DeleteHandler());
        server.createContext("/static/", new StaticHandler());

        server.setExecutor(null);
        server.start();

        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║  Server running on port 8080     ║");
        System.out.println("║  http://localhost:8080/products  ║");
        System.out.println("╚══════════════════════════════════╝");
    }

    // ═══════════════════════════════════════════════════════════════════
    // تحميل البيانات من Python AST
    // ═══════════════════════════════════════════════════════════════════
    private static void loadInitialData() {
        try {
            var input = CharStreams.fromFileName(BASE + "app.py");
            var lexer = new PythonIndentingLexer(input);
            var tokens = new CommonTokenStream(lexer);
            var parser = new PythonSubsetParser(tokens);
            var tree = parser.file_input();

            AstNode pythonAst = new PythonAstBuilder().visit(tree);
            codeGen.extractFromPythonAST(pythonAst);

            // نسخ البيانات إلى قائمتنا الحية
            Object raw = codeGen.getGlobalContext().get("products");
            if (raw instanceof List<?> list) {
                for (Object item : list) {
                    if (item instanceof Map<?, ?> m) {
                        Map<String, Object> p = new LinkedHashMap<>();
                        m.forEach((k, v) -> p.put(k.toString(), v));
                        products.add(p);
                        // تتبع أعلى id
                        Object id = p.get("id");
                        if (id instanceof Number n &&
                                n.intValue() >= nextId) {
                            nextId = n.intValue() + 1;
                        }
                    }
                }
            }
            System.out.println("✅ Loaded " + products.size() +
                    " products from app.py");
        } catch (Exception e) {
            System.err.println("⚠️ Could not load app.py: " + e.getMessage());
            // بيانات افتراضية إذا فشل التحميل
            addDefaultProducts();
        }
    }

    private static void addDefaultProducts() {
        Map<String, Object> p1 = new LinkedHashMap<>();
        p1.put("id", 1);
        p1.put("name", "Laptop");
        p1.put("price", 1200);
        p1.put("details", "High performance laptop");
        p1.put("image", "images/img.png");
        products.add(p1);

        Map<String, Object> p2 = new LinkedHashMap<>();
        p2.put("id", 2);
        p2.put("name", "Phone");
        p2.put("price", 800);
        p2.put("details", "Smartphone with great camera");
        p2.put("image", "images/img.png");
        products.add(p2);

        nextId = 3;
    }

    // ═══════════════════════════════════════════════════════════════════
    // توليد HTML من القالب + البيانات الحالية
    // ═══════════════════════════════════════════════════════════════════
    private static String generateHtml(String templateName,
            Map<String, Object> extra)
            throws Exception {
        // 1) حلّل القالب
        var input = CharStreams.fromFileName(TEMPLATES + templateName);
        var lexer = new TemplateLexer(input);
        var tokens = new CommonTokenStream(lexer);
        var parser = new TemplateParser(tokens);
        var tree = parser.template();
        AstNode templateAst = new TemplateAstBuilder().visit(tree);

        // 2) ابنِ extra context يحتوي البيانات الحية
        // بدل ما نعدّل على globalContext مباشرة
        Map<String, Object> liveContext = new HashMap<>();
        liveContext.put("products", products); // ← البيانات الحية دائماً
        if (extra != null) {
            liveContext.putAll(extra); // ← أي بيانات إضافية
        }

        // 3) generateForTemplate ستدمج globalContext + liveContext تلقائياً
        return codeGen.generateForTemplate(templateAst, templateName, liveContext);
    }

    // ═══════════════════════════════════════════════════════════════════
    // إعادة التوليد (regeneration)
    //
    // الجافا هي التي "تستمع" لتغيّر البيانات: أي إضافة أو حذف منتج تستدعي
    // إعادة توليد صفحات output/ من قوالب Jinja + البيانات الحالية، فتبقى
    // الملفات المولَّدة متزامنة مع البيانات (لا مجرّد ناتج التشغيل الأول).
    // ═══════════════════════════════════════════════════════════════════
    private static synchronized void regenerateOutputFiles(String reason) {
        try {
            Files.createDirectories(Paths.get(GENERATED));

            writeGenerated("list_products.jinja", "list_products.html", null);
            writeGenerated("add_product.jinja", "add_product.html", null);

            if (!products.isEmpty()) {
                Map<String, Object> extra = new HashMap<>();
                extra.put("product", products.get(0));
                writeGenerated("product_detail.jinja", "product_detail.html", extra);
            }

            System.out.println("[REGEN] output/ regenerated (" + reason
                    + ") - products: " + products.size());
        } catch (Exception e) {
            System.err.println("[WARN] Regeneration failed: " + e.getMessage());
        }
    }

    private static void writeGenerated(String templateName, String outName,
            Map<String, Object> extra) throws Exception {
        Files.writeString(Paths.get(GENERATED + outName),
                generateHtml(templateName, extra));
    }

    // ═══════════════════════════════════════════════════════════════════
    // إرسال Response
    // ═══════════════════════════════════════════════════════════════════
    static void sendHtml(HttpExchange ex, String html, int code)
            throws IOException {
        byte[] bytes = html.getBytes(StandardCharsets.UTF_8);
        ex.getResponseHeaders().set("Content-Type",
                "text/html; charset=UTF-8");
        ex.sendResponseHeaders(code, bytes.length);
        try (OutputStream os = ex.getResponseBody()) {
            os.write(bytes);
        }
    }

    static void sendRedirect(HttpExchange ex, String location)
            throws IOException {
        ex.getResponseHeaders().set("Location", location);
        ex.sendResponseHeaders(302, -1);
        ex.getResponseBody().close();
    }

    // ═══════════════════════════════════════════════════════════════════
    // HANDLERS
    // ═══════════════════════════════════════════════════════════════════

    // / → redirect إلى /products
    static class RootHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange ex) throws IOException {
            sendRedirect(ex, "/products");
        }
    }

    // /products/1 → عرض تفاصيل منتج
    static class ProductDetailHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange ex) throws IOException {
            try {
                // استخراج الـ id من URL
                String path = ex.getRequestURI().getPath();
                // path = /products/1
                String[] parts = path.split("/");

                // تحقق أن الـ URL فيه id
                if (parts.length < 3) {
                    sendRedirect(ex, "/products");
                    return;
                }

                int pid = Integer.parseInt(parts[parts.length - 1]);

                // ابحث عن المنتج
                Map<String, Object> found = null;
                for (Map<String, Object> p : products) {
                    Object id = p.get("id");
                    int productId = (id instanceof Number n)
                            ? n.intValue()
                            : Integer.parseInt(id.toString());
                    if (productId == pid) {
                        found = p;
                        break;
                    }
                }

                // إذا ما وجدناه
                if (found == null) {
                    sendHtml(ex, "<h1>Product not found!</h1>", 404);
                    return;
                }

                // ولّد صفحة التفاصيل
                Map<String, Object> extra = new HashMap<>();
                extra.put("product", found);
                String html = generateHtml("product_detail.jinja", extra);
                sendHtml(ex, html, 200);

            } catch (NumberFormatException e) {
                sendRedirect(ex, "/products");
            } catch (Exception e) {
                sendHtml(ex, "<h1>Error: " + e.getMessage() + "</h1>", 500);
                e.printStackTrace();
            }
        }
    }

    // /products → عرض قائمة المنتجات
    static class ProductsHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange ex) throws IOException {
            try {
                String html = generateHtml(
                        "list_products.jinja",
                        new HashMap<>());
                sendHtml(ex, html, 200);
            } catch (Exception e) {
                sendHtml(ex, "<h1>Error: " + e.getMessage() + "</h1>", 500);
                e.printStackTrace();
            }
        }
    }

    // /add → GET: form الإضافة | POST: حفظ المنتج
    static class AddHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange ex) throws IOException {
            try {
                if ("GET".equals(ex.getRequestMethod())) {
                    String html = generateHtml("add_product.jinja", new HashMap<>());
                    sendHtml(ex, html, 200);

                } else if ("POST".equals(ex.getRequestMethod())) {
                    String body = new String(
                            ex.getRequestBody().readAllBytes(),
                            StandardCharsets.UTF_8);
                    Map<String, String> form = parseForm(body);

                    String name = form.getOrDefault("name", "").trim();
                    String price = form.getOrDefault("price", "").trim();
                    String details = form.getOrDefault("details", "").trim();

                    // ── Validation ──────────────────────────────────────
                    List<String> errors = new ArrayList<>();

                    if (name.isEmpty()) {
                        errors.add("Name is required");
                    }
                    if (price.isEmpty()) {
                        errors.add("Price is required");
                    } else {
                        try {
                            double p = Double.parseDouble(price);
                            if (p <= 0)
                                errors.add("Price must be greater than 0");
                        } catch (NumberFormatException e) {
                            errors.add("Price must be a valid number");
                        }
                    }
                    if (details.isEmpty()) {
                        errors.add("Details are required");
                    }

                    // إذا في أخطاء - أرجع صفحة الإضافة مع رسالة خطأ
                    if (!errors.isEmpty()) {
                        String errorHtml = buildErrorPage(errors, name, price, details);
                        sendHtml(ex, errorHtml, 400);
                        return;
                    }

                    // ── إضافة المنتج ────────────────────────────────────
                    Map<String, Object> newProduct = new LinkedHashMap<>();
                    newProduct.put("id", nextId++);
                    newProduct.put("name", name);
                    newProduct.put("price", price);
                    newProduct.put("details", details);
                    newProduct.put("image", "images/img.png");

                    products.add(newProduct);
                    System.out.println("✅ Added product: " + name);
                    regenerateOutputFiles("added product: " + name);

                    sendRedirect(ex, "/products");
                }
            } catch (Exception e) {
                sendHtml(ex, "<h1>Error: " + e.getMessage() + "</h1>", 500);
                e.printStackTrace();
            }
        }

        // صفحة الخطأ مع الحقول المملوءة سابقاً
        private String buildErrorPage(List<String> errors,
                String name,
                String price,
                String details) {
            StringBuilder sb = new StringBuilder();
            sb.append("<!DOCTYPE html><html><head><title>Add Product</title>");
            sb.append("<link rel='stylesheet' href='/static/css/style.css'>");
            sb.append("</head><body>");
            sb.append("<h1>Add Product</h1>");

            // عرض الأخطاء
            sb.append("<div style='color:red; border:1px solid red; padding:10px; margin:10px 0;'>");
            sb.append("<strong>Please fix the following errors:</strong><ul>");
            for (String err : errors) {
                sb.append("<li>").append(err).append("</li>");
            }
            sb.append("</ul></div>");

            // الفورم مع القيم المحفوظة
            sb.append("<form method='post'>");
            sb.append("<label>Name:</label>");
            sb.append("<input type='text' name='name' value='")
                    .append(name).append("'><br>");
            sb.append("<label>Price:</label>");
            sb.append("<input type='number' name='price' value='")
                    .append(price).append("'><br>");
            sb.append("<label>Details:</label>");
            sb.append("<textarea name='details'>")
                    .append(details).append("</textarea><br>");
            sb.append("<button type='submit'>Add</button>");
            sb.append("</form>");
            sb.append("<a href='/products'>Back to products</a>");
            sb.append("</body></html>");

            return sb.toString();
        }
    }

    // /delete/{id} → حذف منتج
    static class DeleteHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange ex) throws IOException {
            try {
                // استخراج الـ id من الـ URL
                String path = ex.getRequestURI().getPath();
                // path = /delete/2
                String[] parts = path.split("/");

                // معرّف غير رقمي (/delete/abc) كان يُنتج صفحة خطأ 500
                Integer parsed = parseId(parts.length > 0 ? parts[parts.length - 1] : null);
                if (parsed == null) {
                    sendRedirect(ex, "/products");
                    return;
                }
                int pid = parsed;

                // حذف المنتج
                boolean removed = products.removeIf(p -> {
                    Object id = p.get("id");
                    if (id instanceof Number n) {
                        return n.intValue() == pid;
                    }
                    return String.valueOf(id).equals(String.valueOf(pid));
                });

                System.out.println(removed
                        ? "✅ Deleted product id=" + pid
                        : "⚠️ Product id=" + pid + " not found");

                if (removed) {
                    regenerateOutputFiles("deleted product id=" + pid);
                }

                sendRedirect(ex, "/products");

            } catch (Exception e) {
                sendHtml(ex, "<h1>Error: " + e.getMessage() + "</h1>", 500);
                e.printStackTrace();
            }
        }
    }

    // /static/ → خدمة ملفات CSS والصور
    static class StaticHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange ex) throws IOException {
            try {
                String uriPath = ex.getRequestURI().getPath();
                // /static/css/style.css → resources/css/style.css
                // نُطبّع المسار ونتأكد أنه لا يخرج من مجلد resources،
                // وإلا استطاع طلب مثل /static/../app.py قراءة ملفات المشروع
                Path baseDir = Paths.get(RESOURCES).toAbsolutePath().normalize();
                Path target = baseDir
                        .resolve(uriPath.substring("/static/".length()))
                        .normalize();

                if (!target.startsWith(baseDir) || !Files.isRegularFile(target)) {
                    ex.sendResponseHeaders(404, -1);
                    ex.getResponseBody().close();
                    return;
                }

                // تحديد نوع المحتوى
                String filePath = target.toString();
                String contentType = "text/plain";
                if (filePath.endsWith(".css"))
                    contentType = "text/css";
                if (filePath.endsWith(".png"))
                    contentType = "image/png";
                if (filePath.endsWith(".jpg"))
                    contentType = "image/jpeg";
                if (filePath.endsWith(".js"))
                    contentType = "application/javascript";

                byte[] bytes = Files.readAllBytes(target);
                ex.getResponseHeaders().set("Content-Type", contentType);
                ex.sendResponseHeaders(200, bytes.length);
                try (OutputStream os = ex.getResponseBody()) {
                    os.write(bytes);
                }
            } catch (Exception e) {
                ex.sendResponseHeaders(500, -1);
                ex.getResponseBody().close();
            }
        }
    }

    // ═══════════════════════════════════════════════════════════════════
    // Helper: يقرأ معرّفاً رقمياً من مقطع URL (null إذا لم يكن رقماً)
    // ═══════════════════════════════════════════════════════════════════
    static Integer parseId(String raw) {
        if (raw == null || raw.isEmpty())
            return null;
        try {
            return Integer.valueOf(raw);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    // ═══════════════════════════════════════════════════════════════════
    // Helper: parse form body
    // name=Laptop&price=1200&details=...
    // ═══════════════════════════════════════════════════════════════════
    static Map<String, String> parseForm(String body) {
        Map<String, String> map = new LinkedHashMap<>();
        if (body == null || body.isEmpty())
            return map;
        for (String pair : body.split("&")) {
            String[] kv = pair.split("=", 2);
            String key = URLDecoder.decode(kv[0], StandardCharsets.UTF_8);
            String val = kv.length > 1
                    ? URLDecoder.decode(kv[1], StandardCharsets.UTF_8)
                    : "";
            map.put(key, val);
        }
        return map;
    }
}
// # الخادم الأصلي
// java -cp "out;lib/antlr-4.13.2-complete.jar" compiler.main.WebServer