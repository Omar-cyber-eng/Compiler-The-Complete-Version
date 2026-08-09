lexer grammar TemplateLexer;

@header {
    package compiler.parser;
}

// ==========================================================================
// DEFAULT MODE
// ==========================================================================

JINJA_BLOCK_START  : '{%' -> pushMode(JINJA_MODE);
JINJA_VAR_START    : '{{' -> pushMode(JINJA_MODE);
JINJA_COMMENT_START: '{#' -> pushMode(COMMENT_MODE);

HTML_COMMENT: '<!--' .*? '-->' -> skip;
DOCTYPE     : '<!DOCTYPE' .*? '>';

// ⭐ Void elements أولاً (قبل HTML_OPEN العادي)
VOID_OPEN: '<' ('area'|'base'|'br'|'col'|'embed'|'hr'|'img'|'input'
               |'link'|'meta'|'param'|'source'|'track'|'wbr')
           -> pushMode(TAG_MODE);

HTML_OPEN : '<' [a-zA-Z][a-zA-Z0-9]* -> pushMode(TAG_MODE);
HTML_CLOSE: '</' [a-zA-Z][a-zA-Z0-9]* [ \t\r\n]* '>';

HTML_TEXT: (~[<{] | '{' ~[{%#])+;
HTML_WS  : [ \t\r\n]+ -> skip;

// ==========================================================================
// TAG_MODE
// ==========================================================================

mode TAG_MODE;

TAG_CLOSE      : '>'  -> popMode;
TAG_SLASH_CLOSE: '/>' -> popMode;
TAG_EQUALS     : '=';
TAG_NAME       : [a-zA-Z][a-zA-Z0-9_-]*;

// قيمة عادية بدون Jinja
TAG_VALUE_PLAIN: '"' (~["{<>])* '"'
               | '\'' (~['{<>])* '\''
               ;

// قيمة تحتوي Jinja - double quotes
TAG_ATTR_JINJA_DQ_START: '"' -> pushMode(TAG_ATTR_DQ_MODE);
// قيمة تحتوي Jinja - single quotes  
TAG_ATTR_JINJA_SQ_START: '\'' -> pushMode(TAG_ATTR_SQ_MODE);

TAG_WS: [ \t\r\n]+ -> skip;

// ==========================================================================
// TAG_ATTR_DQ_MODE - داخل " ... "
// ==========================================================================

mode TAG_ATTR_DQ_MODE;

TAG_ATTR_DQ_JINJA_START: '{{' -> pushMode(JINJA_MODE);
TAG_ATTR_DQ_END        : '"'  -> popMode;
TAG_ATTR_DQ_TEXT       : (~["{] | '{' ~[{%#])+;

// ==========================================================================
// TAG_ATTR_SQ_MODE - داخل ' ... '
// ==========================================================================

mode TAG_ATTR_SQ_MODE;

TAG_ATTR_SQ_JINJA_START: '{{' -> pushMode(JINJA_MODE);
TAG_ATTR_SQ_END        : '\'' -> popMode;
TAG_ATTR_SQ_TEXT       : (~['{] | '{' ~[{%#])+;

// ==========================================================================
// JINJA_MODE
// ==========================================================================

mode JINJA_MODE;

JINJA_BLOCK_END: '%}' -> popMode;
JINJA_VAR_END  : '}}' -> popMode;

FOR   : 'for';
IN    : 'in';
IF    : 'if';
ELIF  : 'elif';
ELSE  : 'else';
ENDIF : 'endif';
ENDFOR: 'endfor';
NOT   : 'not';
AND   : 'and';
OR    : 'or';

COMPARE: '==' | '!=' | '<=' | '>=' | '<' | '>';

DOT   : '.';
LPAREN: '(';
RPAREN: ')';
LBRACK: '[';
RBRACK: ']';
COMMA : ',';
EQUAL : '=';
PIPE  : '|';

JINJA_STRING: '"' (~["\r\n] | '\\"')* '"'
            | '\'' (~['\r\n] | '\\\'')* '\'';
JINJA_NUMBER: [0-9]+ ('.' [0-9]+)?;
JINJA_NAME  : [a-zA-Z_][a-zA-Z0-9_]*;
JINJA_WS    : [ \t\r\n]+ -> skip;

// ==========================================================================
// COMMENT_MODE
// ==========================================================================

mode COMMENT_MODE;

JINJA_COMMENT_END : '#}' -> popMode;
JINJA_COMMENT_TEXT: .+?;