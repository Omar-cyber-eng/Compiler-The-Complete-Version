package compiler.ast.nodes;

import compiler.ast.core.AstNode;
import compiler.ast.visitors.AstVisitor;

public class TemplateNode extends AstNode {
    public TemplateNode(int line) {
        super("Template", line);
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visitTemplate(this);
    }
}