package compiler.ast.nodes.css;

import compiler.ast.core.AstNode;
import compiler.ast.visitors.AstVisitor;

public class CssDeclarationNode extends AstNode {
    private final String property;
    private final String value;

    public CssDeclarationNode(String property, String value, int line) {
        super("CssDeclaration", line);
        this.property = property;
        this.value = value;
    }

    public String getProperty() {
        return property;
    }

    public String getValue() {
        return value;
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visitCssDeclaration(this);
    }
}