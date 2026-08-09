package compiler.ast.nodes.python;

import compiler.ast.core.AstNode;
import compiler.ast.visitors.AstVisitor;

public class ListNode extends AstNode {

    public ListNode(int line) {
        super("List", line);
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visitList(this);
    }
}