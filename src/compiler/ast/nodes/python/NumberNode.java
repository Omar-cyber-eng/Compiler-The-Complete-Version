package compiler.ast.nodes.python;

import compiler.ast.core.ExprNode;
import compiler.ast.visitors.AstVisitor;

public class NumberNode extends ExprNode {
    private final String value;

    public NumberNode(String value, int line) {
        super("Number", line);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visitNumber(this);
    }
}