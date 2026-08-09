package compiler.ast.nodes.python;

import compiler.ast.core.ExprNode;
import compiler.ast.visitors.AstVisitor;

public class AttrAccessNode extends ExprNode {
    public AttrAccessNode(int line) {
        super("AttrAccess", line);
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visitAttrAccess(this);
    }
}