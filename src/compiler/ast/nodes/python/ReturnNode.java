package compiler.ast.nodes.python;

import compiler.ast.core.AstNode;
import compiler.ast.visitors.AstVisitor;

public class ReturnNode extends AstNode {

    public ReturnNode(int line) {
        super("Return", line);
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visitReturn(this);
    }
}