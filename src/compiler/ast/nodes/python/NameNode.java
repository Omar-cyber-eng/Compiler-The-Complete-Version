package compiler.ast.nodes.python;

import compiler.ast.core.ExprNode;
import compiler.ast.visitors.AstVisitor;

public class NameNode extends ExprNode {
    private final String name;

    public NameNode(String name, int line) {
        super("Name", line);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visitName(this);
    }
}