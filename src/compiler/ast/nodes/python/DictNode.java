package compiler.ast.nodes.python;

import compiler.ast.core.AstNode;
import compiler.ast.visitors.AstVisitor;

public class DictNode extends AstNode {

    public DictNode(int line) {
        super("Dict", line);
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visitDict(this);
    }
}