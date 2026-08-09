// Generated from C:/Users/dell/IdeaProjects/TemplateCompiler2/src/compiler/grammar/HTMLParser.g4 by ANTLR 4.13.2

    package compiler.parser;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link HTMLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface HTMLParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link HTMLParser#html_document}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHtml_document(HTMLParser.Html_documentContext ctx);
	/**
	 * Visit a parse tree produced by {@link HTMLParser#doctype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoctype(HTMLParser.DoctypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code HtmlElementContent}
	 * labeled alternative in {@link HTMLParser#html_content}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHtmlElementContent(HTMLParser.HtmlElementContentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code HtmlTextContent}
	 * labeled alternative in {@link HTMLParser#html_content}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHtmlTextContent(HTMLParser.HtmlTextContentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code HtmlCommentContent}
	 * labeled alternative in {@link HTMLParser#html_content}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHtmlCommentContent(HTMLParser.HtmlCommentContentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NormalHtmlElement}
	 * labeled alternative in {@link HTMLParser#html_element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNormalHtmlElement(HTMLParser.NormalHtmlElementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SelfClosingHtmlElement}
	 * labeled alternative in {@link HTMLParser#html_element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelfClosingHtmlElement(HTMLParser.SelfClosingHtmlElementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code HtmlAttributeWithValue}
	 * labeled alternative in {@link HTMLParser#html_attribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHtmlAttributeWithValue(HTMLParser.HtmlAttributeWithValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code HtmlAttributeNoValue}
	 * labeled alternative in {@link HTMLParser#html_attribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHtmlAttributeNoValue(HTMLParser.HtmlAttributeNoValueContext ctx);
}