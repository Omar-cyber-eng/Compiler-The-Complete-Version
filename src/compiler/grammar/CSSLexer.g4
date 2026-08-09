lexer grammar CSSLexer;


@header {
    package compiler.parser;
}

// CSS specific tokens
AT_IMPORT: '@import';
AT_MEDIA: '@media';
AT_KEYFRAMES: '@keyframes';

// Selectors
HASH: '#' [a-zA-Z0-9_-]+;
CLASS: '.' [a-zA-Z][a-zA-Z0-9_-]*;
PSEUDO: ':' [a-zA-Z][a-zA-Z0-9_-]* ('(' [^)]* ')')?;

// Important
IMPORTANT: '!important';

// Structural
LBRACE: '{' -> pushMode(DECLARATION_MODE);
RBRACE: '}';
LPAREN: '(';
RPAREN: ')';
LBRACK: '[';
RBRACK: ']';
SEMICOLON: ';';
COLON: ':';
COMMA: ',';
GT: '>';
PLUS: '+';
TILDE: '~';
STAR: '*';

// Identifiers (property names, element names, etc.)
IDENT: [a-zA-Z_-][a-zA-Z0-9_-]*;

// Whitespace
WS: [ \t\r\n]+ -> skip;

// Comments
COMMENT: '/*' .*? '*/' -> skip;

// === DECLARATION MODE (inside {}) ===
mode DECLARATION_MODE;

DECL_RBRACE: '}' -> popMode, type(RBRACE);
DECL_SEMICOLON: ';' -> type(SEMICOLON);
DECL_COLON: ':' -> type(COLON);
DECL_IMPORTANT: '!important' -> type(IMPORTANT);
DECL_COMMA: ',' -> type(COMMA);

// Values - in declaration mode, # is a color not a selector
COLOR_HEX: '#' [0-9a-fA-F]+;

// Numbers with units
NUMBER: [0-9]+ ('.' [0-9]+)?;

UNIT
    : 'px' | 'em' | 'rem' | '%' | 'vh' | 'vw' | 'pt' | 'cm' | 'mm' | 'in'
    | 'pc' | 'ex' | 'ch' | 'vmin' | 'vmax' | 's' | 'ms' | 'deg' | 'rad'
    ;

// Strings
STRING
    : '"' (~["\r\n] | '\\' .)* '"'
    | '\'' (~['\r\n] | '\\' .)* '\''
    ;

// URLs
URL: 'url(' [ \t]* STRING [ \t]* ')';

// Property names and values
DECL_IDENT: [a-zA-Z_-][a-zA-Z0-9_-]* -> type(IDENT);

// Whitespace in declarations
DECL_WS: [ \t\r\n]+ -> skip;

// Comments in declarations
DECL_COMMENT: '/*' .*? '*/' -> skip;