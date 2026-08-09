package compiler.symbol;

import java.util.*;

/**
 * Represents a scope in the symbol table hierarchy
 * Each scope has a name, parent scope, symbols, and child scopes
 */
public class Scope {
    private final String name;
    private final Scope parent;
    private final Map<String, Symbol> symbols;
    private final List<Scope> children;

    public Scope(String name, Scope parent) {
        this.name = name;
        this.parent = parent;
        this.symbols = new LinkedHashMap<>(); // Preserve insertion order
        this.children = new ArrayList<>();
    }

    /**
     * Define a symbol in this scope
     * @param symbol the symbol to define
     */
    public void define(Symbol symbol) {
        symbols.put(symbol.getName(), symbol);
    }

    /**
     * Resolve a symbol by name, searching this scope and parent scopes
     * @param name the name of the symbol
     * @return the symbol if found, null otherwise
     */
    public Symbol resolve(String name) {
        Symbol symbol = symbols.get(name);
        if (symbol != null) {
            return symbol;
        }

        // Search in parent scope
        if (parent != null) {
            return parent.resolve(name);
        }

        return null;
    }

    /**
     * Resolve a symbol only in this scope (not parent scopes)
     * @param name the name of the symbol
     * @return the symbol if found in this scope, null otherwise
     */
    public Symbol resolveLocal(String name) {
        return symbols.get(name);
    }

    /**
     * Add a child scope to this scope
     * @param child the child scope to add
     */
    public void addChild(Scope child) {
        children.add(child);
    }

    /**
     * Check if a symbol is defined in this scope (not parent scopes)
     * @param name the name of the symbol
     * @return true if defined in this scope, false otherwise
     */
    public boolean isDefined(String name) {
        return symbols.containsKey(name);
    }

    /**
     * Get the name of this scope
     * @return the scope name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the parent scope
     * @return the parent scope, or null if this is the global scope
     */
    public Scope getParent() {
        return parent;
    }

    /**
     * Get all symbols defined in this scope
     * @return map of symbol names to symbols
     */
    public Map<String, Symbol> getSymbols() {
        return Collections.unmodifiableMap(symbols);
    }

    /**
     * Get all child scopes
     * @return list of child scopes
     */
    public List<Scope> getChildren() {
        return Collections.unmodifiableList(children);
    }

    /**
     * Get the full scope path (e.g., "global.function_name.nested_block")
     * @return the full scope path
     */
    public String getFullPath() {
        if (parent == null) {
            return name;
        }
        return parent.getFullPath() + "." + name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Scope: ").append(name);
        if (parent != null) {
            sb.append(" (parent: ").append(parent.getName()).append(")");
        }
        sb.append("\n");

        if (!symbols.isEmpty()) {
            sb.append("Symbols:\n");
            for (Symbol symbol : symbols.values()) {
                sb.append("  ").append(symbol).append("\n");
            }
        }

        return sb.toString();
    }
}