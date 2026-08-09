lexer grammar HTMLLexer;


@header {
    package compiler.parser;
}

// DOCTYPE
HTML_DOCTYPE: '<!DOCTYPE' .*? '>';

// HTML tags
HTML_TAG_OPEN: '<' [a-zA-Z][a-zA-Z0-9]* -> pushMode(TAG_MODE);
HTML_TAG_CLOSE: '</' [a-zA-Z][a-zA-Z0-9]* '>';

// HTML comments
HTML_COMMENT: '<!--' .*? '-->';

// Text content
HTML_TEXT: ~[<]+;

// Whitespace
HTML_WS: [ \t\r\n]+ -> channel(HIDDEN);

// === TAG MODE ===
mode TAG_MODE;

TAG_GT: '>' -> popMode;
TAG_SLASH_GT: '/>' -> popMode;

TAG_ATTR_NAME: [a-zA-Z][a-zA-Z0-9_-]*;
TAG_EQ: '=';

TAG_ATTR_VALUE
    : '"' (~["])* '"'
    | '\'' (~['])* '\''
    ;

TAG_WS: [ \t\r\n]+ -> skip;