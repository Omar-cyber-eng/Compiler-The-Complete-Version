parser grammar TemplateParser;

@header {
    package compiler.parser;
}

options { tokenVocab = TemplateLexer; }

// ==========================================================================
// ROOT
// ==========================================================================

template: content* EOF;

// ==========================================================================
// CONTENT
// ==========================================================================

content
    : html_element        # HtmlContent
    | text_content        # TextContent
    | jinja_variable      # JinjaVariableContent
    | jinja_for           # JinjaForContent
    | jinja_if            # JinjaIfContent
    | jinja_comment       # JinjaCommentContent
    | DOCTYPE             # DoctypeContent
    ;

// ==========================================================================
// HTML ELEMENTS - الآن يدعم 3 أنواع
// ==========================================================================

html_element
    : HTML_OPEN  attribute* TAG_CLOSE content* HTML_CLOSE   # NormalElement
    | HTML_OPEN  attribute* TAG_SLASH_CLOSE                 # SelfClosingElement
    | VOID_OPEN  attribute* TAG_CLOSE                       # VoidElement
    | VOID_OPEN  attribute* TAG_SLASH_CLOSE                 # VoidElementSlash
    ;

// ==========================================================================
// ATTRIBUTES
// ==========================================================================

attribute
    : TAG_NAME TAG_EQUALS attr_value    # StaticAttribute
    | TAG_NAME                          # BooleanAttribute
    ;

attr_value
    : TAG_VALUE_PLAIN                                              # PlainAttrValue
    | TAG_ATTR_JINJA_DQ_START attr_part_dq* TAG_ATTR_DQ_END       # DynamicAttrValueDQ
    | TAG_ATTR_JINJA_SQ_START attr_part_sq* TAG_ATTR_SQ_END       # DynamicAttrValueSQ
    ;

attr_part_dq
    : TAG_ATTR_DQ_TEXT                                    # AttrTextPartDQ
    | TAG_ATTR_DQ_JINJA_START jinja_expr JINJA_VAR_END   # AttrJinjaPartDQ
    ;

attr_part_sq
    : TAG_ATTR_SQ_TEXT                                    # AttrTextPartSQ
    | TAG_ATTR_SQ_JINJA_START jinja_expr JINJA_VAR_END   # AttrJinjaPartSQ
    ;

text_content: HTML_TEXT;

// ==========================================================================
// JINJA VARIABLE
// ==========================================================================

jinja_variable
    : JINJA_VAR_START jinja_expr filter* JINJA_VAR_END
    ;

filter
    : PIPE JINJA_NAME (LPAREN jinja_args? RPAREN)?
    ;

// ==========================================================================
// JINJA FOR
// ==========================================================================

jinja_for
    : JINJA_BLOCK_START FOR JINJA_NAME IN jinja_expr JINJA_BLOCK_END
      content*
      JINJA_BLOCK_START ENDFOR JINJA_BLOCK_END
    ;

// ==========================================================================
// JINJA IF
// ==========================================================================

jinja_if
    : JINJA_BLOCK_START IF jinja_expr JINJA_BLOCK_END
      if_body=content_block
      elif_clause*
      else_clause?
      JINJA_BLOCK_START ENDIF JINJA_BLOCK_END
    ;

elif_clause
    : JINJA_BLOCK_START ELIF jinja_expr JINJA_BLOCK_END
      content_block
    ;

else_clause
    : JINJA_BLOCK_START ELSE JINJA_BLOCK_END
      content_block
    ;

content_block: content*;

// ==========================================================================
// JINJA COMMENT
// ==========================================================================

jinja_comment
    : JINJA_COMMENT_START JINJA_COMMENT_TEXT? JINJA_COMMENT_END
    ;

// ==========================================================================
// JINJA EXPRESSIONS
// ==========================================================================

jinja_expr
    : left=jinja_expr op=COMPARE right=jinja_expr    # JinjaCompare
    | NOT jinja_expr                                  # JinjaNot
    | left=jinja_expr AND right=jinja_expr            # JinjaAnd
    | left=jinja_expr OR  right=jinja_expr            # JinjaOr
    | jinja_expr DOT JINJA_NAME                       # JinjaAttributeAccess
    | jinja_expr LBRACK jinja_expr RBRACK             # JinjaSubscript
    | JINJA_NAME LPAREN jinja_args? RPAREN            # JinjaFunctionCall
    | JINJA_NAME                                      # JinjaName
    | JINJA_STRING                                    # JinjaString
    | JINJA_NUMBER                                    # JinjaNumber
    | LPAREN jinja_expr RPAREN                        # JinjaParenExpr
    ;

jinja_args
    : jinja_arg (COMMA jinja_arg)*
    ;

jinja_arg
    : JINJA_NAME EQUAL jinja_expr    # JinjaKeywordArg
    | jinja_expr                     # JinjaPositionalArg
    ;