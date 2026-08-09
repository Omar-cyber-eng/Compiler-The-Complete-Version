package compiler.ast.nodes.python;

import compiler.ast.core.AstNode;
import compiler.ast.visitors.AstVisitor;

public class GlobalNode extends AstNode {

    public GlobalNode(int line) {
        super("Global", line);
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visitGlobal(this);
    }
}