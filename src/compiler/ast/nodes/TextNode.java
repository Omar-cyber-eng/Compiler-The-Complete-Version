package compiler.ast.nodes;

import compiler.ast.core.AstNode;
import compiler.ast.visitors.AstVisitor;

public class TextNode extends AstNode {
    private final String text;

    public TextNode(String text, int line) {
        super("Text", line);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visitText(this);
    }
}