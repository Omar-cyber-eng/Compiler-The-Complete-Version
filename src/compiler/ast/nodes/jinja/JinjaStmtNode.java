package compiler.ast.nodes.jinja;

import compiler.ast.core.StmtNode;
import compiler.ast.visitors.AstVisitor;

public class JinjaStmtNode extends StmtNode {

    // نوع الجملة: "for" | "if" | "elif" | "else"
    // يُستخدم للتمييز الموثوق بين الحلقات والشروط في التحليل الدلالي.
    private String kind;

    public JinjaStmtNode(int line) {
        super("JinjaStmt", line);
    }

    public JinjaStmtNode(int line, String kind) {
        super("JinjaStmt", line);
        this.kind = kind;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visitJinjaStmt(this);
    }
}
