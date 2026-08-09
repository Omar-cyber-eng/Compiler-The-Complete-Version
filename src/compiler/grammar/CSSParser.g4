parser grammar CSSParser;


@header {
    package compiler.parser;
}

options {
    tokenVocab = CSSLexer;
}

// Root rule - a CSS stylesheet
stylesheet: cssRule* EOF;

// CSS rule: selector { declarations }
cssRule
    : selector_group LBRACE declaration* RBRACE    #StyleRule
    | AT_IMPORT STRING SEMICOLON                   #ImportRule
    | AT_MEDIA media_query LBRACE cssRule* RBRACE  #MediaRule
    ;

// Selector groups: selector, selector, ...
selector_group: selector (COMMA selector)*;

// Selector: element, .class, #id, etc.
selector
    : selector combinator simple_selector    #CombinedSelector
    | simple_selector                         #SimpleSelectorRule
    ;

combinator
    : GT                            #ChildCombinator
    | PLUS                          #AdjacentSiblingCombinator
    | TILDE                         #GeneralSiblingCombinator
    |                               #DescendantCombinator
    ;

simple_selector
    : IDENT selector_modifier*                 #ElementSelector
    | selector_modifier+                       #ModifierOnlySelector
    | STAR                                     #UniversalSelector
    ;

selector_modifier
    : CLASS                                    #ClassModifier
    | HASH                                     #IdModifier
    | PSEUDO                                   #PseudoModifier
    | LBRACK attribute_selector RBRACK        #AttributeModifier
    ;

attribute_selector: IDENT (COLON IDENT)?;

media_query: IDENT (COLON IDENT)?;

// CSS declaration: property: value;
declaration
    : IDENT COLON value SEMICOLON              #PropertyDeclaration
    | IDENT COLON value                        #PropertyDeclarationNoSemicolon
    ;

// CSS values (simplified - just collect all tokens until semicolon or close brace)
value: value_item+;

value_item
    : NUMBER UNIT?           #NumberValue
    | NUMBER                 #NumberOnlyValue
    | COLOR_HEX              #ColorValue
    | STRING                 #StringValue
    | IDENT                  #IdentValue
    | URL                    #UrlValue
    | COMMA                  #CommaValue
    | IMPORTANT              #ImportantValue
    | COLON                  #ColonValue
    ;