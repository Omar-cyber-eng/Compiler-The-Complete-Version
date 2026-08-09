package compiler.semantic;

import compiler.ast.core.AstNode;
import compiler.ast.nodes.python.*;

import java.util.*;

/**
 * يبني "سياق" كل قالب: أي المتغيرات مُمرَّرة إليه من كود Python.
 *
 * يمشي على شجرة Python بحثاً عن استدعاءات:
 *   render_template("name.jinja", key1=value1, key2=value2, ...)
 * فيجمع لكل اسم قالب مجموعة أسماء المتغيرات (المفاتيح) المُمرَّرة إليه.
 *
 * هذا يجسّد فكرة المشروع: تمرير البيانات من Python إلى شجرة Jinja،
 * ويُستخدم في {@link JinjaSemanticAnalyzer} لكشف المتغيرات غير المعرّفة.
 */
public class TemplateContextBuilder {

    // اسم القالب → مجموعة المتغيرات المُمرَّرة إليه
    private final Map<String, Set<String>> templateVars = new LinkedHashMap<>();

    // المتغيرات العامة (global) المعرّفة في أعلى ملف Python — متاحة كمرجع متساهل
    private final Set<String> globalVars = new LinkedHashSet<>();

    public void build(AstNode pythonAst) {
        if (pythonAst == null)
            return;

        // 1) المتغيرات العامة من الإسنادات في المستوى الأعلى
        for (AstNode child : pythonAst.getChildren()) {
            if (child instanceof AssignNode && !child.getChildren().isEmpty()) {
                AstNode target = child.getChildren().get(0);
                if (target instanceof NameNode) {
                    globalVars.add(((NameNode) target).getName());
                }
            }
        }

        // 2) البحث التعاودي عن استدعاءات render_template
        scan(pythonAst);
    }

    private void scan(AstNode node) {
        if (node == null)
            return;

        if (node instanceof CallNode) {
            handleCall((CallNode) node);
        }

        for (AstNode child : node.getChildren()) {
            scan(child);
        }
    }

    private void handleCall(CallNode call) {
        List<AstNode> children = call.getChildren();
        if (children.isEmpty())
            return;

        // اسم الدالة
        AstNode funcNode = children.get(0);
        String funcName = null;
        if (funcNode instanceof NameNode) {
            funcName = ((NameNode) funcNode).getName();
        }
        if (!"render_template".equals(funcName))
            return;

        // الوسيط الأول: اسم القالب (نص حرفي)
        String templateName = null;
        if (children.size() >= 2 && children.get(1) instanceof StringNode) {
            templateName = ((StringNode) children.get(1)).getValue();
        }
        if (templateName == null)
            return;

        Set<String> vars = templateVars.computeIfAbsent(
                templateName, k -> new LinkedHashSet<>());

        // بقية الوسائط: keyword arguments (المفتاح = اسم متغير متاح للقالب)
        for (int i = 2; i < children.size(); i++) {
            AstNode arg = children.get(i);
            String argName = arg.getNodeName();
            if (argName != null && argName.equals("KeywordArg")
                    && !arg.getChildren().isEmpty()
                    && arg.getChildren().get(0) instanceof NameNode) {
                vars.add(((NameNode) arg.getChildren().get(0)).getName());
            }
        }
    }

    /**
     * المتغيرات المتاحة لقالب معيّن = المُمرَّرة إليه عبر render_template
     * + المتغيرات العامة (كمرجع متساهل لتفادي إنذارات كاذبة).
     */
    public Set<String> varsForTemplate(String templateName) {
        Set<String> result = new LinkedHashSet<>(globalVars);
        Set<String> passed = templateVars.get(templateName);
        if (passed != null)
            result.addAll(passed);
        return result;
    }

    public Set<String> getGlobalVars() {
        return Collections.unmodifiableSet(globalVars);
    }

    public Map<String, Set<String>> getTemplateVars() {
        return Collections.unmodifiableMap(templateVars);
    }
}
