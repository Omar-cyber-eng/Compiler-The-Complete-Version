package compiler.ast.nodes.python;

import compiler.ast.core.AstNode;
import compiler.ast.visitors.AstVisitor;

public class SuiteNode extends AstNode {

    public SuiteNode(int line) {
        super("Suite", line);
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visitSuite(this);
    }
}