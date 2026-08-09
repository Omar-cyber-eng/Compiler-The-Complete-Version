package compiler.visitors;

import compiler.ast.core.AstNode;
import compiler.ast.nodes.css.*;
import compiler.parser.CSSParser.*;
import compiler.parser.CSSParserBaseVisitor;

public class CssAstBuilder extends CSSParserBaseVisitor<AstNode> {

    @Override
    public AstNode visitStylesheet(StylesheetContext ctx) {
        int line = ctx.getStart().getLine();
        CssStylesheetNode stylesheet = new CssStylesheetNode(line);

        for (CssRuleContext rule : ctx.cssRule()) {
            AstNode ruleNode = visit(rule);
            if (ruleNode != null) {
                stylesheet.addChild(ruleNode);
            }
        }

        return stylesheet;
    }

    @Override
    public AstNode visitStyleRule(StyleRuleContext ctx) {
        int line = ctx.getStart().getLine();

        // Build selector string
        String selector = buildSelectorString(ctx.selector_group());

        CssRuleNode ruleNode = new CssRuleNode(selector, line);

        // Add declarations
        for (DeclarationContext decl : ctx.declaration()) {
            AstNode declNode = visit(decl);
            if (declNode != null) {
                ruleNode.addChild(declNode);
            }
        }

        return ruleNode;
    }

    @Override
    public AstNode visitImportRule(ImportRuleContext ctx) {
        int line = ctx.getStart().getLine();
        String url = ctx.STRING().getText();
        url = url.substring(1, url.length() - 1);

        CssRuleNode importNode = new CssRuleNode("@import " + url, line);
        return importNode;
    }

    @Override
    public AstNode visitMediaRule(MediaRuleContext ctx) {
        int line = ctx.getStart().getLine();

        String mediaQuery = "@media";
        if (ctx.media_query() != null) {
            mediaQuery += " " + ctx.media_query().getText();
        }

        CssRuleNode mediaNode = new CssRuleNode(mediaQuery, line);

        // Add nested rules
        for (CssRuleContext rule : ctx.cssRule()) {
            AstNode ruleNode = visit(rule);
            if (ruleNode != null) {
                mediaNode.addChild(ruleNode);
            }
        }

        return mediaNode;
    }

    @Override
    public AstNode visitPropertyDeclaration(PropertyDeclarationContext ctx) {
        int line = ctx.getStart().getLine();

        String property = ctx.IDENT().getText();
        String value = buildValueString(ctx.value());

        return new CssDeclarationNode(property, value, line);
    }

    @Override
    public AstNode visitPropertyDeclarationNoSemicolon(PropertyDeclarationNoSemicolonContext ctx) {
        int line = ctx.getStart().getLine();

        String property = ctx.IDENT().getText();
        String value = buildValueString(ctx.value());

        return new CssDeclarationNode(property, value, line);
    }

    // ========== HELPER METHODS ==========

    private String buildSelectorString(Selector_groupContext ctx) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < ctx.selector().size(); i++) {
            sb.append(buildSingleSelector(ctx.selector(i)));
            if (i < ctx.selector().size() - 1) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }

    private String buildSingleSelector(SelectorContext ctx) {
        if (ctx instanceof SimpleSelectorRuleContext) {
            SimpleSelectorRuleContext simple = (SimpleSelectorRuleContext) ctx;
            return buildSimpleSelector(simple.simple_selector());
        } else if (ctx instanceof CombinedSelectorContext) {
            CombinedSelectorContext combined = (CombinedSelectorContext) ctx;
            String left = buildSingleSelector(combined.selector());
            String combinator = buildCombinator(combined.combinator());
            String right = buildSimpleSelector(combined.simple_selector());
            return left + combinator + right;
        }
        return "";
    }

    private String buildCombinator(CombinatorContext ctx) {
        if (ctx instanceof DescendantCombinatorContext) {
            return " ";
        } else if (ctx instanceof ChildCombinatorContext) {
            return " > ";
        } else if (ctx instanceof AdjacentSiblingCombinatorContext) {
            return " + ";
        } else if (ctx instanceof GeneralSiblingCombinatorContext) {
            return " ~ ";
        }
        return " ";
    }

    private String buildSimpleSelector(Simple_selectorContext ctx) {
        if (ctx instanceof ElementSelectorContext) {
            ElementSelectorContext elem = (ElementSelectorContext) ctx;
            StringBuilder sb = new StringBuilder();
            sb.append(elem.IDENT().getText());

            if (elem.selector_modifier() != null) {
                for (Selector_modifierContext mod : elem.selector_modifier()) {
                    sb.append(buildSelectorModifier(mod));
                }
            }

            return sb.toString();
        } else if (ctx instanceof ModifierOnlySelectorContext) {
            ModifierOnlySelectorContext modOnly = (ModifierOnlySelectorContext) ctx;
            StringBuilder sb = new StringBuilder();

            for (Selector_modifierContext mod : modOnly.selector_modifier()) {
                sb.append(buildSelectorModifier(mod));
            }

            return sb.toString();
        } else if (ctx instanceof UniversalSelectorContext) {
            return "*";
        }

        return "";
    }

    private String buildSelectorModifier(Selector_modifierContext ctx) {
        if (ctx instanceof ClassModifierContext) {
            return ((ClassModifierContext) ctx).CLASS().getText();
        } else if (ctx instanceof IdModifierContext) {
            return ((IdModifierContext) ctx).HASH().getText();
        } else if (ctx instanceof PseudoModifierContext) {
            return ((PseudoModifierContext) ctx).PSEUDO().getText();
        } else if (ctx instanceof AttributeModifierContext) {
            AttributeModifierContext attr = (AttributeModifierContext) ctx;
            return "[" + attr.attribute_selector().getText() + "]";
        }
        return "";
    }

    private String buildValueString(ValueContext ctx) {
        StringBuilder sb = new StringBuilder();

        for (Value_itemContext item : ctx.value_item()) {
            if (item instanceof NumberValueContext) {
                NumberValueContext num = (NumberValueContext) item;
                sb.append(num.NUMBER().getText());
                if (num.UNIT() != null) {
                    sb.append(num.UNIT().getText());
                }
                sb.append(" ");
            } else if (item instanceof NumberOnlyValueContext) {
                NumberOnlyValueContext num = (NumberOnlyValueContext) item;
                sb.append(num.NUMBER().getText()).append(" ");
            } else if (item instanceof ColorValueContext) {
                ColorValueContext color = (ColorValueContext) item;
                sb.append(color.COLOR_HEX().getText()).append(" ");
            } else if (item instanceof StringValueContext) {
                StringValueContext str = (StringValueContext) item;
                sb.append(str.STRING().getText()).append(" ");
            } else if (item instanceof IdentValueContext) {
                IdentValueContext ident = (IdentValueContext) item;
                sb.append(ident.IDENT().getText()).append(" ");
            } else if (item instanceof UrlValueContext) {
                UrlValueContext url = (UrlValueContext) item;
                sb.append(url.URL().getText()).append(" ");
            } else if (item instanceof CommaValueContext) {
                sb.append(", ");
            } else if (item instanceof ImportantValueContext) {
                sb.append("!important ");
            } else if (item instanceof ColonValueContext) {
                sb.append(":");
            }
        }

        return sb.toString().trim();
    }
}