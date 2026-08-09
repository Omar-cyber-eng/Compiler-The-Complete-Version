package compiler.ast.nodes.python;

import compiler.ast.core.StmtNode;
import compiler.ast.visitors.AstVisitor;

public class ForNode extends StmtNode {
    private final String varName;

    public ForNode(String varName, int line) {
        super("For", line);
        this.varName = varName;
    }

    public String getVarName() {
        return varName;
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visitFor(this);
    }
}