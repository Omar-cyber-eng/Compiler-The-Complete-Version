package compiler.ast.nodes.css;

import compiler.ast.core.AstNode;
import compiler.ast.visitors.AstVisitor;

public class CssRuleNode extends AstNode {
    private final String selector;

    public CssRuleNode(String selector, int line) {
        super("CssRule", line);
        this.selector = selector;
    }

    public String getSelector() {
        return selector;
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visitCssRule(this);
    }
}