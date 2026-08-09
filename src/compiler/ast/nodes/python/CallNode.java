package compiler.ast.nodes.python;

import compiler.ast.core.ExprNode;
import compiler.ast.visitors.AstVisitor;

public class CallNode extends ExprNode {
    public CallNode(int line) {
        super("Call", line);
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visitCall(this);
    }
}