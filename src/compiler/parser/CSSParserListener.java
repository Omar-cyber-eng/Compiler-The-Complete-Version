// Generated from C:/Users/dell/IdeaProjects/TemplateCompiler2/src/compiler/grammar/CSSParser.g4 by ANTLR 4.13.2

    package compiler.parser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CSSParser}.
 */
public interface CSSParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CSSParser#stylesheet}.
	 * @param ctx the parse tree
	 */
	void enterStylesheet(CSSParser.StylesheetContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSSParser#stylesheet}.
	 * @param ctx the parse tree
	 */
	void exitStylesheet(CSSParser.StylesheetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StyleRule}
	 * labeled alternative in {@link CSSParser#cssRule}.
	 * @param ctx the parse tree
	 */
	void enterStyleRule(CSSParser.StyleRuleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StyleRule}
	 * labeled alternative in {@link CSSParser#cssRule}.
	 * @param ctx the parse tree
	 */
	void exitStyleRule(CSSParser.StyleRuleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ImportRule}
	 * labeled alternative in {@link CSSParser#cssRule}.
	 * @param ctx the parse tree
	 */
	void enterImportRule(CSSParser.ImportRuleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ImportRule}
	 * labeled alternative in {@link CSSParser#cssRule}.
	 * @param ctx the parse tree
	 */
	void exitImportRule(CSSParser.ImportRuleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MediaRule}
	 * labeled alternative in {@link CSSParser#cssRule}.
	 * @param ctx the parse tree
	 */
	void enterMediaRule(CSSParser.MediaRuleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MediaRule}
	 * labeled alternative in {@link CSSParser#cssRule}.
	 * @param ctx the parse tree
	 */
	void exitMediaRule(CSSParser.MediaRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSSParser#selector_group}.
	 * @param ctx the parse tree
	 */
	void enterSelector_group(CSSParser.Selector_groupContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSSParser#selector_group}.
	 * @param ctx the parse tree
	 */
	void exitSelector_group(CSSParser.Selector_groupContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CombinedSelector}
	 * labeled alternative in {@link CSSParser#selector}.
	 * @param ctx the parse tree
	 */
	void enterCombinedSelector(CSSParser.CombinedSelectorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CombinedSelector}
	 * labeled alternative in {@link CSSParser#selector}.
	 * @param ctx the parse tree
	 */
	void exitCombinedSelector(CSSParser.CombinedSelectorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SimpleSelectorRule}
	 * labeled alternative in {@link CSSParser#selector}.
	 * @param ctx the parse tree
	 */
	void enterSimpleSelectorRule(CSSParser.SimpleSelectorRuleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SimpleSelectorRule}
	 * labeled alternative in {@link CSSParser#selector}.
	 * @param ctx the parse tree
	 */
	void exitSimpleSelectorRule(CSSParser.SimpleSelectorRuleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ChildCombinator}
	 * labeled alternative in {@link CSSParser#combinator}.
	 * @param ctx the parse tree
	 */
	void enterChildCombinator(CSSParser.ChildCombinatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ChildCombinator}
	 * labeled alternative in {@link CSSParser#combinator}.
	 * @param ctx the parse tree
	 */
	void exitChildCombinator(CSSParser.ChildCombinatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AdjacentSiblingCombinator}
	 * labeled alternative in {@link CSSParser#combinator}.
	 * @param ctx the parse tree
	 */
	void enterAdjacentSiblingCombinator(CSSParser.AdjacentSiblingCombinatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AdjacentSiblingCombinator}
	 * labeled alternative in {@link CSSParser#combinator}.
	 * @param ctx the parse tree
	 */
	void exitAdjacentSiblingCombinator(CSSParser.AdjacentSiblingCombinatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code GeneralSiblingCombinator}
	 * labeled alternative in {@link CSSParser#combinator}.
	 * @param ctx the parse tree
	 */
	void enterGeneralSiblingCombinator(CSSParser.GeneralSiblingCombinatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code GeneralSiblingCombinator}
	 * labeled alternative in {@link CSSParser#combinator}.
	 * @param ctx the parse tree
	 */
	void exitGeneralSiblingCombinator(CSSParser.GeneralSiblingCombinatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DescendantCombinator}
	 * labeled alternative in {@link CSSParser#combinator}.
	 * @param ctx the parse tree
	 */
	void enterDescendantCombinator(CSSParser.DescendantCombinatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DescendantCombinator}
	 * labeled alternative in {@link CSSParser#combinator}.
	 * @param ctx the parse tree
	 */
	void exitDescendantCombinator(CSSParser.DescendantCombinatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ElementSelector}
	 * labeled alternative in {@link CSSParser#simple_selector}.
	 * @param ctx the parse tree
	 */
	void enterElementSelector(CSSParser.ElementSelectorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ElementSelector}
	 * labeled alternative in {@link CSSParser#simple_selector}.
	 * @param ctx the parse tree
	 */
	void exitElementSelector(CSSParser.ElementSelectorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ModifierOnlySelector}
	 * labeled alternative in {@link CSSParser#simple_selector}.
	 * @param ctx the parse tree
	 */
	void enterModifierOnlySelector(CSSParser.ModifierOnlySelectorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ModifierOnlySelector}
	 * labeled alternative in {@link CSSParser#simple_selector}.
	 * @param ctx the parse tree
	 */
	void exitModifierOnlySelector(CSSParser.ModifierOnlySelectorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UniversalSelector}
	 * labeled alternative in {@link CSSParser#simple_selector}.
	 * @param ctx the parse tree
	 */
	void enterUniversalSelector(CSSParser.UniversalSelectorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UniversalSelector}
	 * labeled alternative in {@link CSSParser#simple_selector}.
	 * @param ctx the parse tree
	 */
	void exitUniversalSelector(CSSParser.UniversalSelectorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ClassModifier}
	 * labeled alternative in {@link CSSParser#selector_modifier}.
	 * @param ctx the parse tree
	 */
	void enterClassModifier(CSSParser.ClassModifierContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ClassModifier}
	 * labeled alternative in {@link CSSParser#selector_modifier}.
	 * @param ctx the parse tree
	 */
	void exitClassModifier(CSSParser.ClassModifierContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IdModifier}
	 * labeled alternative in {@link CSSParser#selector_modifier}.
	 * @param ctx the parse tree
	 */
	void enterIdModifier(CSSParser.IdModifierContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IdModifier}
	 * labeled alternative in {@link CSSParser#selector_modifier}.
	 * @param ctx the parse tree
	 */
	void exitIdModifier(CSSParser.IdModifierContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PseudoModifier}
	 * labeled alternative in {@link CSSParser#selector_modifier}.
	 * @param ctx the parse tree
	 */
	void enterPseudoModifier(CSSParser.PseudoModifierContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PseudoModifier}
	 * labeled alternative in {@link CSSParser#selector_modifier}.
	 * @param ctx the parse tree
	 */
	void exitPseudoModifier(CSSParser.PseudoModifierContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AttributeModifier}
	 * labeled alternative in {@link CSSParser#selector_modifier}.
	 * @param ctx the parse tree
	 */
	void enterAttributeModifier(CSSParser.AttributeModifierContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AttributeModifier}
	 * labeled alternative in {@link CSSParser#selector_modifier}.
	 * @param ctx the parse tree
	 */
	void exitAttributeModifier(CSSParser.AttributeModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSSParser#attribute_selector}.
	 * @param ctx the parse tree
	 */
	void enterAttribute_selector(CSSParser.Attribute_selectorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSSParser#attribute_selector}.
	 * @param ctx the parse tree
	 */
	void exitAttribute_selector(CSSParser.Attribute_selectorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSSParser#media_query}.
	 * @param ctx the parse tree
	 */
	void enterMedia_query(CSSParser.Media_queryContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSSParser#media_query}.
	 * @param ctx the parse tree
	 */
	void exitMedia_query(CSSParser.Media_queryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PropertyDeclaration}
	 * labeled alternative in {@link CSSParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterPropertyDeclaration(CSSParser.PropertyDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PropertyDeclaration}
	 * labeled alternative in {@link CSSParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitPropertyDeclaration(CSSParser.PropertyDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PropertyDeclarationNoSemicolon}
	 * labeled alternative in {@link CSSParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterPropertyDeclarationNoSemicolon(CSSParser.PropertyDeclarationNoSemicolonContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PropertyDeclarationNoSemicolon}
	 * labeled alternative in {@link CSSParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitPropertyDeclarationNoSemicolon(CSSParser.PropertyDeclarationNoSemicolonContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSSParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(CSSParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSSParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(CSSParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumberValue}
	 * labeled alternative in {@link CSSParser#value_item}.
	 * @param ctx the parse tree
	 */
	void enterNumberValue(CSSParser.NumberValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumberValue}
	 * labeled alternative in {@link CSSParser#value_item}.
	 * @param ctx the parse tree
	 */
	void exitNumberValue(CSSParser.NumberValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumberOnlyValue}
	 * labeled alternative in {@link CSSParser#value_item}.
	 * @param ctx the parse tree
	 */
	void enterNumberOnlyValue(CSSParser.NumberOnlyValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumberOnlyValue}
	 * labeled alternative in {@link CSSParser#value_item}.
	 * @param ctx the parse tree
	 */
	void exitNumberOnlyValue(CSSParser.NumberOnlyValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ColorValue}
	 * labeled alternative in {@link CSSParser#value_item}.
	 * @param ctx the parse tree
	 */
	void enterColorValue(CSSParser.ColorValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ColorValue}
	 * labeled alternative in {@link CSSParser#value_item}.
	 * @param ctx the parse tree
	 */
	void exitColorValue(CSSParser.ColorValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StringValue}
	 * labeled alternative in {@link CSSParser#value_item}.
	 * @param ctx the parse tree
	 */
	void enterStringValue(CSSParser.StringValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringValue}
	 * labeled alternative in {@link CSSParser#value_item}.
	 * @param ctx the parse tree
	 */
	void exitStringValue(CSSParser.StringValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IdentValue}
	 * labeled alternative in {@link CSSParser#value_item}.
	 * @param ctx the parse tree
	 */
	void enterIdentValue(CSSParser.IdentValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IdentValue}
	 * labeled alternative in {@link CSSParser#value_item}.
	 * @param ctx the parse tree
	 */
	void exitIdentValue(CSSParser.IdentValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UrlValue}
	 * labeled alternative in {@link CSSParser#value_item}.
	 * @param ctx the parse tree
	 */
	void enterUrlValue(CSSParser.UrlValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UrlValue}
	 * labeled alternative in {@link CSSParser#value_item}.
	 * @param ctx the parse tree
	 */
	void exitUrlValue(CSSParser.UrlValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CommaValue}
	 * labeled alternative in {@link CSSParser#value_item}.
	 * @param ctx the parse tree
	 */
	void enterCommaValue(CSSParser.CommaValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CommaValue}
	 * labeled alternative in {@link CSSParser#value_item}.
	 * @param ctx the parse tree
	 */
	void exitCommaValue(CSSParser.CommaValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ImportantValue}
	 * labeled alternative in {@link CSSParser#value_item}.
	 * @param ctx the parse tree
	 */
	void enterImportantValue(CSSParser.ImportantValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ImportantValue}
	 * labeled alternative in {@link CSSParser#value_item}.
	 * @param ctx the parse tree
	 */
	void exitImportantValue(CSSParser.ImportantValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ColonValue}
	 * labeled alternative in {@link CSSParser#value_item}.
	 * @param ctx the parse tree
	 */
	void enterColonValue(CSSParser.ColonValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ColonValue}
	 * labeled alternative in {@link CSSParser#value_item}.
	 * @param ctx the parse tree
	 */
	void exitColonValue(CSSParser.ColonValueContext ctx);
}