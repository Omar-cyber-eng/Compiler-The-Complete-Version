package compiler.ast.nodes.python;

import compiler.ast.core.ExprNode;
import compiler.ast.visitors.AstVisitor;

public class BinOpNode extends ExprNode {
    private final String op;

    public BinOpNode(String op, int line) {
        super("BinOp", line);
        this.op = op;
    }

    public String getOp() {
        return op;
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visitBinOp(this);
    }
}