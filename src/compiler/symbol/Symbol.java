package compiler.symbol;

/**
 * Represents a symbol in the symbol table
 * Contains: name, type, line number, and optional value
 */
public class Symbol {
    private final String name;
    private final String type;
    private final int line;
    private Object value;

    public Symbol(String name, String type, int line) {
        this.name = name;
        this.type = type;
        this.line = line;
        this.value = null;
    }

    public Symbol(String name, String type, int line, Object value) {
        this.name = name;
        this.type = type;
        this.line = line;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getLine() {
        return line;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("%s: %s (line %d)", name, type, line);
    }
}