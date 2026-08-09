package compiler.ast.core;

import compiler.ast.visitors.AstVisitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstract base class for all AST nodes.
 * Enforces: node name, line number, children, polymorphism via visitor.
 */
public abstract class AstNode {
    protected final String nodeName;
    protected final int line;
    protected final List<AstNode> children = new ArrayList<>();

    protected AstNode(String nodeName, int line) {
        this.nodeName = nodeName;
        this.line = line;
    }

    public String getNodeName() {
        return nodeName;
    }

    public int getLine() {
        return line;
    }

    public List<AstNode> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public void addChild(AstNode child) {
        if (child != null) children.add(child);
    }

    public void addChildren(List<? extends AstNode> childs) {
        if (childs != null) children.addAll(childs);
    }

    /** Visitor pattern entry point */
    public abstract <R> R accept(AstVisitor<R> visitor);

    @Override
    public String toString() {
        return nodeName + "(line " + line + ")";
    }
}