package compiler.ast.nodes.python;

import compiler.ast.core.AstNode;
import compiler.ast.visitors.AstVisitor;

public class ImportNode extends AstNode {

    private final String moduleName;

    public ImportNode(String moduleName, int line) {
        super("Import", line);
        this.moduleName = moduleName;
    }

    public String getModuleName() {
        return moduleName;
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visitImport(this);
    }
}