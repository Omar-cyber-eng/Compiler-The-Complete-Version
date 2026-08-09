package compiler.ast.core;

public abstract class StmtNode extends AstNode {
    protected StmtNode(String nodeName, int line) {
        super(nodeName, line);
    }
}