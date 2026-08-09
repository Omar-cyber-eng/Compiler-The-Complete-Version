package compiler.ast.nodes.css;

import compiler.ast.core.AstNode;
import compiler.ast.visitors.AstVisitor;

public class CssStylesheetNode extends AstNode {
    public CssStylesheetNode(int line) {
        super("CssStylesheet", line);
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visitCssStylesheet(this);
    }
}