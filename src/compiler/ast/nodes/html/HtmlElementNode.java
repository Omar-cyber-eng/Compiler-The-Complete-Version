package compiler.ast.nodes.html;

import compiler.ast.core.AstNode;
import compiler.ast.visitors.AstVisitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HtmlElementNode extends AstNode {
    private final String tagName;

    // LinkedHashMap: نحافظ على ترتيب الـ attributes كما في الملف المصدري
    private final Map<String, String> attributes = new LinkedHashMap<>();

    // تعابير Jinja الموجودة داخل قيمة كل attribute، مرتّبة كما ظهرت
    // (اسم الـ attribute → قائمة العقد) لتُستبدل كل واحدة في موضعها الصحيح
    private final Map<String, List<AstNode>> attributeExprs = new LinkedHashMap<>();

    public HtmlElementNode(String tagName, int line) {
        super("HtmlElement", line);
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }

    public void addAttribute(String name, String value) {
        attributes.put(name, value);
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    /** يسجّل تعبير Jinja ظهر داخل قيمة attribute معيّن (بترتيب الظهور). */
    public void addAttributeExpr(String attrName, AstNode expr) {
        if (attrName == null || expr == null)
            return;
        attributeExprs.computeIfAbsent(attrName, k -> new ArrayList<>()).add(expr);
    }

    /** تعابير Jinja الخاصة بـ attribute معيّن (فارغة إذا كانت قيمته ثابتة). */
    public List<AstNode> getAttributeExprs(String attrName) {
        return attributeExprs.getOrDefault(attrName, Collections.emptyList());
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visitHtmlElement(this);
    }
}
