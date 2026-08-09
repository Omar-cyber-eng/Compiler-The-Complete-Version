// Generated from src/compiler/grammar/TemplateParser.g4 by ANTLR 4.13.2
package compiler.parser;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TemplateParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 *            operations with no return type.
 */
public interface TemplateParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TemplateParser#template}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplate(TemplateParser.TemplateContext ctx);

	/**
	 * Visit a parse tree produced by the {@code HtmlContent}
	 * labeled alternative in {@link TemplateParser#content}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHtmlContent(TemplateParser.HtmlContentContext ctx);

	/**
	 * Visit a parse tree produced by the {@code TextContent}
	 * labeled alternative in {@link TemplateParser#content}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTextContent(TemplateParser.TextContentContext ctx);

	/**
	 * Visit a parse tree produced by the {@code JinjaVariableContent}
	 * labeled alternative in {@link TemplateParser#content}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaVariableContent(TemplateParser.JinjaVariableContentContext ctx);

	/**
	 * Visit a parse tree produced by the {@code JinjaForContent}
	 * labeled alternative in {@link TemplateParser#content}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaForContent(TemplateParser.JinjaForContentContext ctx);

	/**
	 * Visit a parse tree produced by the {@code JinjaIfContent}
	 * labeled alternative in {@link TemplateParser#content}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaIfContent(TemplateParser.JinjaIfContentContext ctx);

	/**
	 * Visit a parse tree produced by the {@code JinjaCommentContent}
	 * labeled alternative in {@link TemplateParser#content}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaCommentContent(TemplateParser.JinjaCommentContentContext ctx);

	/**
	 * Visit a parse tree produced by the {@code DoctypeContent}
	 * labeled alternative in {@link TemplateParser#content}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoctypeContent(TemplateParser.DoctypeContentContext ctx);

	/**
	 * Visit a parse tree produced by the {@code NormalElement}
	 * labeled alternative in {@link TemplateParser#html_element}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNormalElement(TemplateParser.NormalElementContext ctx);

	/**
	 * Visit a parse tree produced by the {@code SelfClosingElement}
	 * labeled alternative in {@link TemplateParser#html_element}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelfClosingElement(TemplateParser.SelfClosingElementContext ctx);

	/**
	 * Visit a parse tree produced by the {@code VoidElement}
	 * labeled alternative in {@link TemplateParser#html_element}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVoidElement(TemplateParser.VoidElementContext ctx);

	/**
	 * Visit a parse tree produced by the {@code VoidElementSlash}
	 * labeled alternative in {@link TemplateParser#html_element}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVoidElementSlash(TemplateParser.VoidElementSlashContext ctx);

	/**
	 * Visit a parse tree produced by the {@code StaticAttribute}
	 * labeled alternative in {@link TemplateParser#attribute}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStaticAttribute(TemplateParser.StaticAttributeContext ctx);

	/**
	 * Visit a parse tree produced by the {@code BooleanAttribute}
	 * labeled alternative in {@link TemplateParser#attribute}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanAttribute(TemplateParser.BooleanAttributeContext ctx);

	/**
	 * Visit a parse tree produced by the {@code PlainAttrValue}
	 * labeled alternative in {@link TemplateParser#attr_value}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlainAttrValue(TemplateParser.PlainAttrValueContext ctx);

	/**
	 * Visit a parse tree produced by the {@code DynamicAttrValueDQ}
	 * labeled alternative in {@link TemplateParser#attr_value}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDynamicAttrValueDQ(TemplateParser.DynamicAttrValueDQContext ctx);

	/**
	 * Visit a parse tree produced by the {@code DynamicAttrValueSQ}
	 * labeled alternative in {@link TemplateParser#attr_value}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDynamicAttrValueSQ(TemplateParser.DynamicAttrValueSQContext ctx);

	/**
	 * Visit a parse tree produced by the {@code AttrTextPartDQ}
	 * labeled alternative in {@link TemplateParser#attr_part_dq}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttrTextPartDQ(TemplateParser.AttrTextPartDQContext ctx);

	/**
	 * Visit a parse tree produced by the {@code AttrJinjaPartDQ}
	 * labeled alternative in {@link TemplateParser#attr_part_dq}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttrJinjaPartDQ(TemplateParser.AttrJinjaPartDQContext ctx);

	/**
	 * Visit a parse tree produced by the {@code AttrTextPartSQ}
	 * labeled alternative in {@link TemplateParser#attr_part_sq}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttrTextPartSQ(TemplateParser.AttrTextPartSQContext ctx);

	/**
	 * Visit a parse tree produced by the {@code AttrJinjaPartSQ}
	 * labeled alternative in {@link TemplateParser#attr_part_sq}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttrJinjaPartSQ(TemplateParser.AttrJinjaPartSQContext ctx);

	/**
	 * Visit a parse tree produced by {@link TemplateParser#text_content}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitText_content(TemplateParser.Text_contentContext ctx);

	/**
	 * Visit a parse tree produced by {@link TemplateParser#jinja_variable}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinja_variable(TemplateParser.Jinja_variableContext ctx);

	/**
	 * Visit a parse tree produced by {@link TemplateParser#filter}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilter(TemplateParser.FilterContext ctx);

	/**
	 * Visit a parse tree produced by {@link TemplateParser#jinja_for}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinja_for(TemplateParser.Jinja_forContext ctx);

	/**
	 * Visit a parse tree produced by {@link TemplateParser#jinja_if}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinja_if(TemplateParser.Jinja_ifContext ctx);

	/**
	 * Visit a parse tree produced by {@link TemplateParser#elif_clause}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElif_clause(TemplateParser.Elif_clauseContext ctx);

	/**
	 * Visit a parse tree produced by {@link TemplateParser#else_clause}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElse_clause(TemplateParser.Else_clauseContext ctx);

	/**
	 * Visit a parse tree produced by {@link TemplateParser#content_block}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContent_block(TemplateParser.Content_blockContext ctx);

	/**
	 * Visit a parse tree produced by {@link TemplateParser#jinja_comment}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinja_comment(TemplateParser.Jinja_commentContext ctx);

	/**
	 * Visit a parse tree produced by the {@code JinjaNot}
	 * labeled alternative in {@link TemplateParser#jinja_expr}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaNot(TemplateParser.JinjaNotContext ctx);

	/**
	 * Visit a parse tree produced by the {@code JinjaOr}
	 * labeled alternative in {@link TemplateParser#jinja_expr}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaOr(TemplateParser.JinjaOrContext ctx);

	/**
	 * Visit a parse tree produced by the {@code JinjaFunctionCall}
	 * labeled alternative in {@link TemplateParser#jinja_expr}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaFunctionCall(TemplateParser.JinjaFunctionCallContext ctx);

	/**
	 * Visit a parse tree produced by the {@code JinjaString}
	 * labeled alternative in {@link TemplateParser#jinja_expr}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaString(TemplateParser.JinjaStringContext ctx);

	/**
	 * Visit a parse tree produced by the {@code JinjaParenExpr}
	 * labeled alternative in {@link TemplateParser#jinja_expr}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaParenExpr(TemplateParser.JinjaParenExprContext ctx);

	/**
	 * Visit a parse tree produced by the {@code JinjaCompare}
	 * labeled alternative in {@link TemplateParser#jinja_expr}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaCompare(TemplateParser.JinjaCompareContext ctx);

	/**
	 * Visit a parse tree produced by the {@code JinjaSubscript}
	 * labeled alternative in {@link TemplateParser#jinja_expr}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaSubscript(TemplateParser.JinjaSubscriptContext ctx);

	/**
	 * Visit a parse tree produced by the {@code JinjaAttributeAccess}
	 * labeled alternative in {@link TemplateParser#jinja_expr}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaAttributeAccess(TemplateParser.JinjaAttributeAccessContext ctx);

	/**
	 * Visit a parse tree produced by the {@code JinjaNumber}
	 * labeled alternative in {@link TemplateParser#jinja_expr}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaNumber(TemplateParser.JinjaNumberContext ctx);

	/**
	 * Visit a parse tree produced by the {@code JinjaAnd}
	 * labeled alternative in {@link TemplateParser#jinja_expr}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaAnd(TemplateParser.JinjaAndContext ctx);

	/**
	 * Visit a parse tree produced by the {@code JinjaName}
	 * labeled alternative in {@link TemplateParser#jinja_expr}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaName(TemplateParser.JinjaNameContext ctx);

	/**
	 * Visit a parse tree produced by {@link TemplateParser#jinja_args}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinja_args(TemplateParser.Jinja_argsContext ctx);

	/**
	 * Visit a parse tree produced by the {@code JinjaKeywordArg}
	 * labeled alternative in {@link TemplateParser#jinja_arg}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaKeywordArg(TemplateParser.JinjaKeywordArgContext ctx);

	/**
	 * Visit a parse tree produced by the {@code JinjaPositionalArg}
	 * labeled alternative in {@link TemplateParser#jinja_arg}.
	 * 
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJinjaPositionalArg(TemplateParser.JinjaPositionalArgContext ctx);
}