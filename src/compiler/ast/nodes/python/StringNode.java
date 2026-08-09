package compiler.ast.nodes.python;

import compiler.ast.core.ExprNode;
import compiler.ast.visitors.AstVisitor;

public class StringNode extends ExprNode {
    private final String value;

    public StringNode(String value, int line) {
        super("String", line);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visitString(this);
    }
}