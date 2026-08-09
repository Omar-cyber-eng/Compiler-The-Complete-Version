package compiler.ast.nodes.python;

import compiler.ast.core.StmtNode;
import compiler.ast.visitors.AstVisitor;

public class AssignNode extends StmtNode {
    public AssignNode(int line) {
        super("Assign", line);
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visitAssign(this);
    }
}