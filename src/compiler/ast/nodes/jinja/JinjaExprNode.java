package compiler.ast.nodes.jinja;

import compiler.ast.core.ExprNode;
import compiler.ast.visitors.AstVisitor;

public class JinjaExprNode extends ExprNode {
    public JinjaExprNode(int line) {
        super("JinjaExpr", line);
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visitJinjaExpr(this);
    }
}