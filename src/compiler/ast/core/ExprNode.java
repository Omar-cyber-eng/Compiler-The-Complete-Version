package compiler.ast.core;

public abstract class ExprNode extends AstNode {
    protected ExprNode(String nodeName, int line) {
        super(nodeName, line);
    }
}