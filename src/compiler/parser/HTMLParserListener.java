// Generated from C:/Users/dell/IdeaProjects/TemplateCompiler2/src/compiler/grammar/HTMLParser.g4 by ANTLR 4.13.2

    package compiler.parser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HTMLParser}.
 */
public interface HTMLParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HTMLParser#html_document}.
	 * @param ctx the parse tree
	 */
	void enterHtml_document(HTMLParser.Html_documentContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#html_document}.
	 * @param ctx the parse tree
	 */
	void exitHtml_document(HTMLParser.Html_documentContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLParser#doctype}.
	 * @param ctx the parse tree
	 */
	void enterDoctype(HTMLParser.DoctypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#doctype}.
	 * @param ctx the parse tree
	 */
	void exitDoctype(HTMLParser.DoctypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code HtmlElementContent}
	 * labeled alternative in {@link HTMLParser#html_content}.
	 * @param ctx the parse tree
	 */
	void enterHtmlElementContent(HTMLParser.HtmlElementContentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code HtmlElementContent}
	 * labeled alternative in {@link HTMLParser#html_content}.
	 * @param ctx the parse tree
	 */
	void exitHtmlElementContent(HTMLParser.HtmlElementContentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code HtmlTextContent}
	 * labeled alternative in {@link HTMLParser#html_content}.
	 * @param ctx the parse tree
	 */
	void enterHtmlTextContent(HTMLParser.HtmlTextContentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code HtmlTextContent}
	 * labeled alternative in {@link HTMLParser#html_content}.
	 * @param ctx the parse tree
	 */
	void exitHtmlTextContent(HTMLParser.HtmlTextContentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code HtmlCommentContent}
	 * labeled alternative in {@link HTMLParser#html_content}.
	 * @param ctx the parse tree
	 */
	void enterHtmlCommentContent(HTMLParser.HtmlCommentContentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code HtmlCommentContent}
	 * labeled alternative in {@link HTMLParser#html_content}.
	 * @param ctx the parse tree
	 */
	void exitHtmlCommentContent(HTMLParser.HtmlCommentContentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NormalHtmlElement}
	 * labeled alternative in {@link HTMLParser#html_element}.
	 * @param ctx the parse tree
	 */
	void enterNormalHtmlElement(HTMLParser.NormalHtmlElementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NormalHtmlElement}
	 * labeled alternative in {@link HTMLParser#html_element}.
	 * @param ctx the parse tree
	 */
	void exitNormalHtmlElement(HTMLParser.NormalHtmlElementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SelfClosingHtmlElement}
	 * labeled alternative in {@link HTMLParser#html_element}.
	 * @param ctx the parse tree
	 */
	void enterSelfClosingHtmlElement(HTMLParser.SelfClosingHtmlElementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SelfClosingHtmlElement}
	 * labeled alternative in {@link HTMLParser#html_element}.
	 * @param ctx the parse tree
	 */
	void exitSelfClosingHtmlElement(HTMLParser.SelfClosingHtmlElementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code HtmlAttributeWithValue}
	 * labeled alternative in {@link HTMLParser#html_attribute}.
	 * @param ctx the parse tree
	 */
	void enterHtmlAttributeWithValue(HTMLParser.HtmlAttributeWithValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code HtmlAttributeWithValue}
	 * labeled alternative in {@link HTMLParser#html_attribute}.
	 * @param ctx the parse tree
	 */
	void exitHtmlAttributeWithValue(HTMLParser.HtmlAttributeWithValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code HtmlAttributeNoValue}
	 * labeled alternative in {@link HTMLParser#html_attribute}.
	 * @param ctx the parse tree
	 */
	void enterHtmlAttributeNoValue(HTMLParser.HtmlAttributeNoValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code HtmlAttributeNoValue}
	 * labeled alternative in {@link HTMLParser#html_attribute}.
	 * @param ctx the parse tree
	 */
	void exitHtmlAttributeNoValue(HTMLParser.HtmlAttributeNoValueContext ctx);
}