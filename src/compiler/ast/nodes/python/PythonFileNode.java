package compiler.ast.nodes.python;

import compiler.ast.core.AstNode;
import compiler.ast.visitors.AstVisitor;

public class PythonFileNode extends AstNode {

    public PythonFileNode(int line) {
        super("PythonFile", line);
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visitPythonFile(this);
    }
}