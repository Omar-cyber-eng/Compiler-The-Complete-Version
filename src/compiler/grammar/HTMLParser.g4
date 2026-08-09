parser grammar HTMLParser;


@header {
    package compiler.parser;
}

options {
    tokenVocab = HTMLLexer;
}

// HTML document
html_document: doctype? html_content* EOF;

doctype: HTML_DOCTYPE;

html_content
    : html_element     #HtmlElementContent
    | HTML_TEXT        #HtmlTextContent
    | HTML_COMMENT     #HtmlCommentContent
    ;

html_element
    : HTML_TAG_OPEN html_attribute* TAG_GT html_content* HTML_TAG_CLOSE    #NormalHtmlElement
    | HTML_TAG_OPEN html_attribute* TAG_SLASH_GT                           #SelfClosingHtmlElement
    ;

html_attribute
    : TAG_ATTR_NAME TAG_EQ TAG_ATTR_VALUE    #HtmlAttributeWithValue
    | TAG_ATTR_NAME                           #HtmlAttributeNoValue
    ;