package compiler.lexer;

import compiler.parser.PythonSubsetLexer;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.misc.Pair;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Custom Python Lexer that handles:
 * 1. INDENT / DEDENT injection based on indentation stack
 * 2. Implicit Line Joining inside (), [], {}
 * 3. Blank lines and comment-only lines are ignored for indentation
 */
public class PythonIndentingLexer extends Lexer {

    private final PythonSubsetLexer lexer;

    // Stack to track indentation levels
    private final Stack<Integer> indentStack = new Stack<>();

    // Queue of tokens to emit before fetching the next one
    private final Queue<Token> pendingTokens = new LinkedList<>();

    // Nesting depth for (), [], {} - when > 0, newlines are ignored (implicit join)
    private int nestingDepth = 0;

    // Whether we are at the beginning of a new logical line
    private boolean atLineStart = true;

    // Track the last emitted token type (on default channel)
    private int lastEmittedType = -1;

    public PythonIndentingLexer(CharStream input) {
        super(input);
        this.lexer = new PythonSubsetLexer(input);
        indentStack.push(0); // base indentation level
    }

    // -------------------------------------------------------------------------
    // Core method
    // -------------------------------------------------------------------------

    @Override
    public Token nextToken() {

        // Always drain the pending queue first
        if (!pendingTokens.isEmpty()) {
            return emitToken(pendingTokens.poll());
        }

        while (true) {
            Token token = lexer.nextToken();
            int type = token.getType();

            // ------------------------------------------------------------------
            // 1. EOF → close all open indentation levels
            // ------------------------------------------------------------------
            if (type == Token.EOF) {
                handleEOF(token);
                if (!pendingTokens.isEmpty()) {
                    Token first = pendingTokens.poll();
                    pendingTokens.add(token); // EOF goes last
                    return emitToken(first);
                }
                return emitToken(token);
            }

            // ------------------------------------------------------------------
            // 2. Hidden-channel tokens (WS, COMMENT) → forward transparently
            // ------------------------------------------------------------------
            if (token.getChannel() != Lexer.DEFAULT_TOKEN_CHANNEL) {
                // We still need to forward them so ANTLR streams work correctly,
                // but they don't affect indent logic.
                return emitToken(token);
            }

            // ------------------------------------------------------------------
            // 3. Track nesting depth for implicit line joining
            // ------------------------------------------------------------------
            if (type == PythonSubsetLexer.LPAREN
                    || type == PythonSubsetLexer.LBRACK
                    || type == PythonSubsetLexer.LBRACE) {
                nestingDepth++;
            } else if (type == PythonSubsetLexer.RPAREN
                    || type == PythonSubsetLexer.RBRACK
                    || type == PythonSubsetLexer.RBRACE) {
                if (nestingDepth > 0)
                    nestingDepth--;
            }

            // ------------------------------------------------------------------
            // 4. NEWLINE handling
            // ------------------------------------------------------------------
            if (type == PythonSubsetLexer.NEWLINE) {

                // Inside brackets → swallow the NEWLINE entirely (implicit join)
                if (nestingDepth > 0) {
                    // don't emit, don't change atLineStart
                    continue;
                }

                // Blank line check: if we are already at line start and see NEWLINE
                // it means the line was empty → skip it
                if (atLineStart) {
                    continue;
                }

                // Normal logical NEWLINE
                atLineStart = true;
                lastEmittedType = PythonSubsetLexer.NEWLINE;
                return emitToken(token);
            }

            // ------------------------------------------------------------------
            // 5. Indentation logic – only when at the start of a logical line
            // and NOT inside brackets
            // ------------------------------------------------------------------
            if (atLineStart && nestingDepth == 0) {
                atLineStart = false;

                int currentIndent = token.getCharPositionInLine();
                int previousIndent = indentStack.peek();

                if (currentIndent > previousIndent) {
                    // ── INDENT ──────────────────────────────────────────────
                    indentStack.push(currentIndent);
                    pendingTokens.add(token); // the real token comes after INDENT
                    lastEmittedType = PythonSubsetLexer.INDENT;
                    return emitToken(makeToken(PythonSubsetLexer.INDENT, "<<<INDENT>>>", token));

                } else if (currentIndent < previousIndent) {
                    // ── DEDENT(s) ────────────────────────────────────────────
                    while (indentStack.size() > 1 && indentStack.peek() > currentIndent) {
                        indentStack.pop();
                        pendingTokens.add(makeToken(PythonSubsetLexer.DEDENT, "<<<DEDENT>>>", token));
                    }
                    pendingTokens.add(token); // real token after all DEDENTs
                    lastEmittedType = PythonSubsetLexer.DEDENT;
                    return emitToken(pendingTokens.poll());

                } else {
                    // ── SAME LEVEL ───────────────────────────────────────────
                    // no indent token needed, just fall through
                    atLineStart = false;
                }
            } else {
                atLineStart = false;
            }

            // ------------------------------------------------------------------
            // 6. Normal token
            // ------------------------------------------------------------------
            lastEmittedType = type;
            return emitToken(token);
        }
    }

    // -------------------------------------------------------------------------
    // EOF handling: emit NEWLINE + all remaining DEDENTs
    // -------------------------------------------------------------------------
    private void handleEOF(Token eofToken) {
        // Emit a trailing NEWLINE if the last token wasn't one
        if (lastEmittedType != PythonSubsetLexer.NEWLINE && lastEmittedType != -1) {
            pendingTokens.add(makeToken(PythonSubsetLexer.NEWLINE, "\n", eofToken));
        }

        // Close all open indentation levels
        while (indentStack.size() > 1) {
            indentStack.pop();
            pendingTokens.add(makeToken(PythonSubsetLexer.DEDENT, "<<<DEDENT>>>", eofToken));
        }
    }

    // -------------------------------------------------------------------------
    // Helper: emit a token and keep track of lastEmittedType
    // -------------------------------------------------------------------------
    private Token emitToken(Token t) {
        if (t.getChannel() == Lexer.DEFAULT_TOKEN_CHANNEL) {
            lastEmittedType = t.getType();
        }
        return t;
    }

    // -------------------------------------------------------------------------
    // Helper: create a synthetic token
    // -------------------------------------------------------------------------
    private Token makeToken(int type, String text, Token reference) {
        CommonToken t = new CommonToken(
                new Pair<>(lexer, lexer.getInputStream()),
                type,
                Lexer.DEFAULT_TOKEN_CHANNEL,
                reference.getStartIndex(),
                reference.getStopIndex());
        t.setLine(reference.getLine());
        t.setCharPositionInLine(reference.getCharPositionInLine());
        t.setText(text);
        return t;
    }

    // -------------------------------------------------------------------------
    // Required ANTLR overrides – delegate to inner lexer
    // -------------------------------------------------------------------------

    @Override
    public String getGrammarFileName() {
        return lexer.getGrammarFileName();
    }

    @Override
    public String[] getRuleNames() {
        return lexer.getRuleNames();
    }

    @Override
    public String getSerializedATN() {
        return lexer.getSerializedATN();
    }

    @Override
    public String[] getChannelNames() {
        return lexer.getChannelNames();
    }

    @Override
    public String[] getModeNames() {
        return lexer.getModeNames();
    }

    @Override
    public Vocabulary getVocabulary() {
        return lexer.getVocabulary();
    }

    @Override
    public ATN getATN() {
        return lexer.getATN();
    }
}