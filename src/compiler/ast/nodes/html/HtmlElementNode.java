package compiler.ast.nodes.html;

import compiler.ast.core.AstNode;
import compiler.ast.visitors.AstVisitor;

import java.util.HashMap;
import java.util.Map;

public class HtmlElementNode extends AstNode {
    private final String tagName;
    private final Map<String, String> attributes = new HashMap<>();

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

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visitHtmlElement(this);
    }
}