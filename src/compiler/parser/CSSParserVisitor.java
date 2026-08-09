// Generated from C:/Users/dell/IdeaProjects/TemplateCompiler2/src/compiler/grammar/CSSParser.g4 by ANTLR 4.13.2

    package compiler.parser;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CSSParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CSSParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CSSParser#stylesheet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStylesheet(CSSParser.StylesheetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StyleRule}
	 * labeled alternative in {@link CSSParser#cssRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStyleRule(CSSParser.StyleRuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ImportRule}
	 * labeled alternative in {@link CSSParser#cssRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportRule(CSSParser.ImportRuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MediaRule}
	 * labeled alternative in {@link CSSParser#cssRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMediaRule(CSSParser.MediaRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link CSSParser#selector_group}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelector_group(CSSParser.Selector_groupContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CombinedSelector}
	 * labeled alternative in {@link CSSParser#selector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCombinedSelector(CSSParser.CombinedSelectorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SimpleSelectorRule}
	 * labeled alternative in {@link CSSParser#selector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleSelectorRule(CSSParser.SimpleSelectorRuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ChildCombinator}
	 * labeled alternative in {@link CSSParser#combinator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChildCombinator(CSSParser.ChildCombinatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AdjacentSiblingCombinator}
	 * labeled alternative in {@link CSSParser#combinator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdjacentSiblingCombinator(CSSParser.AdjacentSiblingCombinatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code GeneralSiblingCombinator}
	 * labeled alternative in {@link CSSParser#combinator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGeneralSiblingCombinator(CSSParser.GeneralSiblingCombinatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DescendantCombinator}
	 * labeled alternative in {@link CSSParser#combinator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDescendantCombinator(CSSParser.DescendantCombinatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ElementSelector}
	 * labeled alternative in {@link CSSParser#simple_selector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementSelector(CSSParser.ElementSelectorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ModifierOnlySelector}
	 * labeled alternative in {@link CSSParser#simple_selector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModifierOnlySelector(CSSParser.ModifierOnlySelectorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UniversalSelector}
	 * labeled alternative in {@link CSSParser#simple_selector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUniversalSelector(CSSParser.UniversalSelectorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ClassModifier}
	 * labeled alternative in {@link CSSParser#selector_modifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassModifier(CSSParser.ClassModifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IdModifier}
	 * labeled alternative in {@link CSSParser#selector_modifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdModifier(CSSParser.IdModifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PseudoModifier}
	 * labeled alternative in {@link CSSParser#selector_modifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPseudoModifier(CSSParser.PseudoModifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AttributeModifier}
	 * labeled alternative in {@link CSSParser#selector_modifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttributeModifier(CSSParser.AttributeModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link CSSParser#attribute_selector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttribute_selector(CSSParser.Attribute_selectorContext ctx);
	/**
	 * Visit a parse tree produced by {@link CSSParser#media_query}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMedia_query(CSSParser.Media_queryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PropertyDeclaration}
	 * labeled alternative in {@link CSSParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPropertyDeclaration(CSSParser.PropertyDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PropertyDeclarationNoSemicolon}
	 * labeled alternative in {@link CSSParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPropertyDeclarationNoSemicolon(CSSParser.PropertyDeclarationNoSemicolonContext ctx);
	/**
	 * Visit a parse tree produced by {@link CSSParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(CSSParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumberValue}
	 * labeled alternative in {@link CSSParser#value_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberValue(CSSParser.NumberValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumberOnlyValue}
	 * labeled alternative in {@link CSSParser#value_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberOnlyValue(CSSParser.NumberOnlyValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ColorValue}
	 * labeled alternative in {@link CSSParser#value_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColorValue(CSSParser.ColorValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringValue}
	 * labeled alternative in {@link CSSParser#value_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringValue(CSSParser.StringValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IdentValue}
	 * labeled alternative in {@link CSSParser#value_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentValue(CSSParser.IdentValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UrlValue}
	 * labeled alternative in {@link CSSParser#value_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUrlValue(CSSParser.UrlValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CommaValue}
	 * labeled alternative in {@link CSSParser#value_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommaValue(CSSParser.CommaValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ImportantValue}
	 * labeled alternative in {@link CSSParser#value_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportantValue(CSSParser.ImportantValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ColonValue}
	 * labeled alternative in {@link CSSParser#value_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColonValue(CSSParser.ColonValueContext ctx);
}