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

/**
 * نسخة الخادم مع إعادة التوليد من جهة المتصفح (client-side regeneration).
 *
 * مطابقة لـ {@link WebServer} (توليد HTML من قوالب Jinja + بيانات Python)،
 * لكنها تضيف آليتين إضافيتين تجيبان على ملاحظة المعيدة
 * ("الجافا سكريبت تستمع للمتغيرات وتعيد التوليد"):
 *
 * 1) واجهة JSON: GET /api/products → البيانات الحالية.
 * 2) حقن script.js في كل صفحة: يستمع (polling) لتغيّرات البيانات
 * عبر /api/products، وعند أي تغيير يعيد توليد الصفحة تلقائياً.
 *
 * الجزء server-side لا يزال يعيد التوليد عند كل طلب (مثل WebServer)،
 * فتحصل على الآليتين معاً.
 *
 * التشغيل (لا تُشغّل WebServer و WebServerJS معاً — كلاهما على المنفذ 8080):
 * java -cp "out;lib/antlr-4.13.2-complete.jar" compiler.main.WebServerJS
 */
public class WebServerJS {

    // ── البيانات الحية في الذاكرة ──────────────────────────────────────
    private static List<Map<String, Object>> products = new ArrayList<>();
    private static int nextId = 1;

    // ── المسارات ───────────────────────────────────────────────────────
    static final String BASE = "src/compiler/main/test_app/";
    static final String TEMPLATES = BASE + "templates/";
    static final String RESOURCES = BASE + "resources/";

    // ── CodeGenerator ──────────────────────────────────────────────────
    private static CodeGenerator codeGen = new CodeGenerator();

    // ═══════════════════════════════════════════════════════════════════
    public static void main(String[] args) throws Exception {

        loadInitialData();

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/", new RootHandler());
        server.createContext("/api/products", new ApiProductsHandler()); // ← جديد
        server.createContext("/products/", new ProductDetailHandler());
        server.createContext("/products", new ProductsHandler());
        server.createContext("/add", new AddHandler());
        server.createContext("/delete/", new DeleteHandler());
        server.createContext("/static/", new StaticHandler());

        server.setExecutor(null);
        server.start();

        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║  WebServerJS running on port 8080          ║");
        System.out.println("║  http://localhost:8080/products            ║");
        System.out.println("║  Client-side regeneration via script.js    ║");
        System.out.println("╚════════════════════════════════════════════╝");
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

            Object raw = codeGen.getGlobalContext().get("products");
            if (raw instanceof List<?> list) {
                for (Object item : list) {
                    if (item instanceof Map<?, ?> m) {
                        Map<String, Object> p = new LinkedHashMap<>();
                        m.forEach((k, v) -> p.put(k.toString(), v));
                        products.add(p);
                        Object id = p.get("id");
                        if (id instanceof Number n && n.intValue() >= nextId) {
                            nextId = n.intValue() + 1;
                        }
                    }
                }
            }
            System.out.println("[OK] Loaded " + products.size() + " products from app.py");
        } catch (Exception e) {
            System.err.println("[WARN] Could not load app.py: " + e.getMessage());
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
    // توليد HTML من القالب + البيانات الحالية + حقن script.js
    // ═══════════════════════════════════════════════════════════════════
    private static String generateHtml(String templateName, Map<String, Object> extra)
            throws Exception {
        var input = CharStreams.fromFileName(TEMPLATES + templateName);
        var lexer = new TemplateLexer(input);
        var tokens = new CommonTokenStream(lexer);
        var parser = new TemplateParser(tokens);
        var tree = parser.template();
        AstNode templateAst = new TemplateAstBuilder().visit(tree);

        Map<String, Object> liveContext = new HashMap<>();
        liveContext.put("products", products);
        if (extra != null) {
            liveContext.putAll(extra);
        }

        String html = codeGen.generateForTemplate(templateAst, templateName, liveContext);
        return injectScript(html);
    }

    /** يحقن وسم script.js قبل نهاية </body> ليعمل الاستماع/إعادة التوليد. */
    private static String injectScript(String html) {
        String tag = "<script src=\"/static/js/script.js\"></script>";
        int idx = html.lastIndexOf("</body>");
        if (idx >= 0) {
            return html.substring(0, idx) + tag + "\n" + html.substring(idx);
        }
        return html + "\n" + tag;
    }

    // ═══════════════════════════════════════════════════════════════════
    // JSON بسيط لقائمة المنتجات
    // ═══════════════════════════════════════════════════════════════════
    private static String productsToJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < products.size(); i++) {
            Map<String, Object> p = products.get(i);
            sb.append("{");
            int j = 0;
            for (Map.Entry<String, Object> e : p.entrySet()) {
                sb.append("\"").append(escapeJson(e.getKey())).append("\":");
                Object v = e.getValue();
                if (v instanceof Number || v instanceof Boolean) {
                    sb.append(String.valueOf(v));
                } else {
                    sb.append("\"").append(escapeJson(String.valueOf(v))).append("\"");
                }
                if (++j < p.size())
                    sb.append(",");
            }
            sb.append("}");
            if (i < products.size() - 1)
                sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    private static String escapeJson(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"")
                .replace("\n", "\\n").replace("\r", "\\r");
    }

    // ═══════════════════════════════════════════════════════════════════
    // إرسال Response
    // ═══════════════════════════════════════════════════════════════════
    static void sendHtml(HttpExchange ex, String html, int code) throws IOException {
        byte[] bytes = html.getBytes(StandardCharsets.UTF_8);
        ex.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
        ex.sendResponseHeaders(code, bytes.length);
        try (OutputStream os = ex.getResponseBody()) {
            os.write(bytes);
        }
    }

    static void sendJson(HttpExchange ex, String json) throws IOException {
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        ex.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        ex.sendResponseHeaders(200, bytes.length);
        try (OutputStream os = ex.getResponseBody()) {
            os.write(bytes);
        }
    }

    static void sendRedirect(HttpExchange ex, String location) throws IOException {
        ex.getResponseHeaders().set("Location", location);
        ex.sendResponseHeaders(302, -1);
        ex.getResponseBody().close();
    }

    // ═══════════════════════════════════════════════════════════════════
    // HANDLERS
    // ═══════════════════════════════════════════════════════════════════

    static class RootHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange ex) throws IOException {
            sendRedirect(ex, "/products");
        }
    }

    // GET /api/products → البيانات الحالية (يستمع إليها script.js)
    static class ApiProductsHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange ex) throws IOException {
            sendJson(ex, productsToJson());
        }
    }

    static class ProductDetailHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange ex) throws IOException {
            try {
                String path = ex.getRequestURI().getPath();
                String[] parts = path.split("/");
                if (parts.length < 3) {
                    sendRedirect(ex, "/products");
                    return;
                }
                int pid = Integer.parseInt(parts[parts.length - 1]);

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

                if (found == null) {
                    sendHtml(ex, "<h1>Product not found!</h1>", 404);
                    return;
                }

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

    static class ProductsHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange ex) throws IOException {
            try {
                String html = generateHtml("list_products.jinja", new HashMap<>());
                sendHtml(ex, html, 200);
            } catch (Exception e) {
                sendHtml(ex, "<h1>Error: " + e.getMessage() + "</h1>", 500);
                e.printStackTrace();
            }
        }
    }

    static class AddHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange ex) throws IOException {
            try {
                if ("GET".equals(ex.getRequestMethod())) {
                    String html = generateHtml("add_product.jinja", new HashMap<>());
                    sendHtml(ex, html, 200);

                } else if ("POST".equals(ex.getRequestMethod())) {
                    String body = new String(
                            ex.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
                    Map<String, String> form = parseForm(body);

                    String name = form.getOrDefault("name", "").trim();
                    String price = form.getOrDefault("price", "").trim();
                    String details = form.getOrDefault("details", "").trim();

                    List<String> errors = new ArrayList<>();
                    if (name.isEmpty())
                        errors.add("Name is required");
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
                    if (details.isEmpty())
                        errors.add("Details are required");

                    if (!errors.isEmpty()) {
                        sendHtml(ex, injectScript(buildErrorPage(errors, name, price, details)), 400);
                        return;
                    }

                    Map<String, Object> newProduct = new LinkedHashMap<>();
                    newProduct.put("id", nextId++);
                    newProduct.put("name", name);
                    newProduct.put("price", price);
                    newProduct.put("details", details);
                    newProduct.put("image", "images/img.png");

                    products.add(newProduct);
                    System.out.println("[OK] Added product: " + name);

                    sendRedirect(ex, "/products");
                }
            } catch (Exception e) {
                sendHtml(ex, "<h1>Error: " + e.getMessage() + "</h1>", 500);
                e.printStackTrace();
            }
        }

        private String buildErrorPage(List<String> errors, String name,
                String price, String details) {
            StringBuilder sb = new StringBuilder();
            sb.append("<!DOCTYPE html><html><head><title>Add Product</title>");
            sb.append("<link rel='stylesheet' href='/static/css/style.css'>");
            sb.append("</head><body>");
            sb.append("<h1>Add Product</h1>");
            sb.append("<div style='color:red; border:1px solid red; padding:10px; margin:10px 0;'>");
            sb.append("<strong>Please fix the following errors:</strong><ul>");
            for (String err : errors) {
                sb.append("<li>").append(err).append("</li>");
            }
            sb.append("</ul></div>");
            sb.append("<form method='post'>");
            sb.append("<label>Name:</label>");
            sb.append("<input type='text' name='name' value='").append(name).append("'><br>");
            sb.append("<label>Price:</label>");
            sb.append("<input type='number' name='price' value='").append(price).append("'><br>");
            sb.append("<label>Details:</label>");
            sb.append("<textarea name='details'>").append(details).append("</textarea><br>");
            sb.append("<button type='submit'>Add</button>");
            sb.append("</form>");
            sb.append("<a href='/products'>Back to products</a>");
            sb.append("</body></html>");
            return sb.toString();
        }
    }

    static class DeleteHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange ex) throws IOException {
            try {
                String path = ex.getRequestURI().getPath();
                String[] parts = path.split("/");
                int pid = Integer.parseInt(parts[parts.length - 1]);

                boolean removed = products.removeIf(p -> {
                    Object id = p.get("id");
                    if (id instanceof Number n) {
                        return n.intValue() == pid;
                    }
                    return String.valueOf(id).equals(String.valueOf(pid));
                });

                System.out.println(removed
                        ? "[OK] Deleted product id=" + pid
                        : "[WARN] Product id=" + pid + " not found");

                sendRedirect(ex, "/products");

            } catch (Exception e) {
                sendHtml(ex, "<h1>Error: " + e.getMessage() + "</h1>", 500);
                e.printStackTrace();
            }
        }
    }

    static class StaticHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange ex) throws IOException {
            try {
                String uriPath = ex.getRequestURI().getPath();
                String filePath = RESOURCES + uriPath.substring("/static/".length());

                File file = new File(filePath);
                if (!file.exists()) {
                    ex.sendResponseHeaders(404, -1);
                    ex.getResponseBody().close();
                    return;
                }

                String contentType = "text/plain";
                if (filePath.endsWith(".css"))
                    contentType = "text/css";
                if (filePath.endsWith(".png"))
                    contentType = "image/png";
                if (filePath.endsWith(".jpg"))
                    contentType = "image/jpeg";
                if (filePath.endsWith(".js"))
                    contentType = "application/javascript";

                byte[] bytes = Files.readAllBytes(file.toPath());
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
// # الخادم مع JavaScript regeneration
// java -cp "out;lib/antlr-4.13.2-complete.jar" compiler.main.WebServerJS