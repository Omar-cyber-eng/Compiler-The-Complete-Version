package compiler.ast.nodes.python;

import compiler.ast.core.StmtNode;
import compiler.ast.visitors.AstVisitor;
import java.util.ArrayList;
import java.util.List;

public class DefNode extends StmtNode {
    private final String name;
    private final List<String> params = new ArrayList<>();

    public DefNode(String name, int line) {
        super("Def", line);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<String> getParams() {
        return params;
    }

    public void addParam(String param) {
        params.add(param);
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visitDef(this);
    }
}
