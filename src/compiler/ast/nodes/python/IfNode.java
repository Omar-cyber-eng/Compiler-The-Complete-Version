package compiler.ast.nodes.python;

import compiler.ast.core.StmtNode;
import compiler.ast.visitors.AstVisitor;

public class IfNode extends StmtNode {
    public IfNode(int line) {
        super("If", line);
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visitIf(this);
    }
}