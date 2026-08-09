package compiler.ast.nodes.python;

import compiler.ast.core.AstNode;
import compiler.ast.visitors.AstVisitor;

public class DecoratorNode extends AstNode {

    private final String decoratorName;

    public DecoratorNode(String decoratorName, int line) {
        super("Decorator", line);
        this.decoratorName = decoratorName;
    }

    public String getDecoratorName() {
        return decoratorName;
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visitDecorator(this);
    }
}