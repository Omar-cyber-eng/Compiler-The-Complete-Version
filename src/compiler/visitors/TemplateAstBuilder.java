package compiler.visitors;

import compiler.ast.core.AstNode;
import compiler.ast.nodes.*;
import compiler.ast.nodes.html.HtmlElementNode;
import compiler.ast.nodes.jinja.JinjaExprNode;
import compiler.ast.nodes.jinja.JinjaStmtNode;
import compiler.ast.nodes.python.*;
import compiler.parser.TemplateParser.*;
import compiler.parser.TemplateParserBaseVisitor;

public class TemplateAstBuilder extends TemplateParserBaseVisitor<AstNode> {

    // =========================================================================
    // ROOT
    // =========================================================================

    @Override
    public AstNode visitTemplate(TemplateContext ctx) {
        int line = ctx.getStart().getLine();
        TemplateNode templateNode = new TemplateNode(line);
        for (ContentContext c : ctx.content()) {
            AstNode child = visit(c);
            if (child != null)
                templateNode.addChild(child);
        }
        return templateNode;
    }

    // =========================================================================
    // CONTENT DISPATCHERS
    // =========================================================================

    @Override
    public AstNode visitHtmlContent(HtmlContentContext ctx) {
        return visit(ctx.html_element());
    }

    @Override
    public AstNode visitTextContent(TextContentContext ctx) {
        return visit(ctx.text_content());
    }

    @Override
    public AstNode visitJinjaVariableContent(JinjaVariableContentContext ctx) {
        return visit(ctx.jinja_variable());
    }

    @Override
    public AstNode visitJinjaForContent(JinjaForContentContext ctx) {
        return visit(ctx.jinja_for());
    }

    @Override
    public AstNode visitJinjaIfContent(JinjaIfContentContext ctx) {
        return visit(ctx.jinja_if());
    }

    @Override
    public AstNode visitJinjaCommentContent(JinjaCommentContentContext ctx) {
        return null;
    }

    @Override
    public AstNode visitDoctypeContent(DoctypeContentContext ctx) {
        return new TextNode("<!DOCTYPE html>", ctx.getStart().getLine());
    }

    // =========================================================================
    // HTML ELEMENTS
    // =========================================================================

    @Override
    public AstNode visitNormalElement(NormalElementContext ctx) {
        int line = ctx.getStart().getLine();
        String tagName = ctx.HTML_OPEN().getText().substring(1).trim();
        HtmlElementNode node = new HtmlElementNode(tagName, line);

        for (AttributeContext attr : ctx.attribute()) {
            addAttribute(node, attr);
        }
        for (ContentContext child : ctx.content()) {
            AstNode childNode = visit(child);
            if (childNode != null)
                node.addChild(childNode);
        }
        return node;
    }

    @Override
    public AstNode visitSelfClosingElement(SelfClosingElementContext ctx) {
        int line = ctx.getStart().getLine();
        String tagName = ctx.HTML_OPEN().getText().substring(1).trim();
        HtmlElementNode node = new HtmlElementNode(tagName, line);

        for (AttributeContext attr : ctx.attribute()) {
            addAttribute(node, attr);
        }
        return node;
    }

    private void addAttribute(HtmlElementNode node, AttributeContext ctx) {
        if (ctx instanceof StaticAttributeContext) {
            StaticAttributeContext s = (StaticAttributeContext) ctx;
            String attrName = s.TAG_NAME().getText();

            Attr_valueContext attrValue = s.attr_value();
            String attrValueStr = extractAttrValue(attrValue);
            node.addAttribute(attrName, attrValueStr);

            // ⭐ أضف الـ JinjaExpr nodes كأطفال
            extractAndAddJinjaExprs(node, attrValue);

        } else if (ctx instanceof BooleanAttributeContext) {
            BooleanAttributeContext b = (BooleanAttributeContext) ctx;
            node.addAttribute(b.TAG_NAME().getText(), "true");
        }
    }

    // استخراج قيمة الـ attribute كنص
    private String extractAttrValue(Attr_valueContext ctx) {
        if (ctx instanceof PlainAttrValueContext) {
            String val = ((PlainAttrValueContext) ctx).TAG_VALUE_PLAIN().getText();
            return val.substring(1, val.length() - 1); // إزالة الـ quotes
        }
        if (ctx instanceof DynamicAttrValueDQContext) {
            StringBuilder sb = new StringBuilder();
            for (Attr_part_dqContext part : ((DynamicAttrValueDQContext) ctx).attr_part_dq()) {
                if (part instanceof AttrTextPartDQContext) {
                    sb.append(((AttrTextPartDQContext) part).TAG_ATTR_DQ_TEXT().getText());
                } else if (part instanceof AttrJinjaPartDQContext) {
                    sb.append("{{...}}");
                }
            }
            return sb.toString();
        }
        if (ctx instanceof DynamicAttrValueSQContext) {
            StringBuilder sb = new StringBuilder();
            for (Attr_part_sqContext part : ((DynamicAttrValueSQContext) ctx).attr_part_sq()) {
                if (part instanceof AttrTextPartSQContext) {
                    sb.append(((AttrTextPartSQContext) part).TAG_ATTR_SQ_TEXT().getText());
                } else if (part instanceof AttrJinjaPartSQContext) {
                    sb.append("{{...}}");
                }
            }
            return sb.toString();
        }
        return "";
    }

    /**
     * يستخرج JinjaExpr nodes من attr_value ويضيفها كأطفال للـ node
     */
    private void extractAndAddJinjaExprs(HtmlElementNode node, Attr_valueContext ctx) {
        if (ctx instanceof DynamicAttrValueDQContext) {
            for (Attr_part_dqContext part : ((DynamicAttrValueDQContext) ctx).attr_part_dq()) {
                if (part instanceof AttrJinjaPartDQContext) {
                    AttrJinjaPartDQContext jinjaPart = (AttrJinjaPartDQContext) part;
                    AstNode expr = visit(jinjaPart.jinja_expr());
                    if (expr != null) {
                        node.addChild(expr);
                    }
                }
            }
        } else if (ctx instanceof DynamicAttrValueSQContext) {
            for (Attr_part_sqContext part : ((DynamicAttrValueSQContext) ctx).attr_part_sq()) {
                if (part instanceof AttrJinjaPartSQContext) {
                    AttrJinjaPartSQContext jinjaPart = (AttrJinjaPartSQContext) part;
                    AstNode expr = visit(jinjaPart.jinja_expr());
                    if (expr != null) {
                        node.addChild(expr);
                    }
                }
            }
        }
    }

    // =========================================================================
    // TEXT
    // =========================================================================

    @Override
    public AstNode visitText_content(Text_contentContext ctx) {
        String text = ctx.HTML_TEXT().getText().trim();
        if (text.isEmpty())
            return null;
        return new TextNode(text, ctx.getStart().getLine());
    }

    // =========================================================================
    // JINJA VARIABLE
    // =========================================================================

    @Override
    public AstNode visitJinja_variable(Jinja_variableContext ctx) {
        int line = ctx.getStart().getLine();
        JinjaExprNode node = new JinjaExprNode(line);

        AstNode expr = visit(ctx.jinja_expr());
        if (expr != null)
            node.addChild(expr);

        for (FilterContext f : ctx.filter()) {
            node.addChild(new NameNode("filter:" + f.JINJA_NAME().getText(),
                    f.getStart().getLine()));
        }
        return node;
    }

    // =========================================================================
    // JINJA FOR
    // =========================================================================

    @Override
    public AstNode visitJinja_for(Jinja_forContext ctx) {
        int line = ctx.getStart().getLine();
        JinjaStmtNode forNode = new JinjaStmtNode(line, "for");

        forNode.addChild(new NameNode(ctx.JINJA_NAME().getText(), line));

        AstNode iterable = visit(ctx.jinja_expr());
        if (iterable != null)
            forNode.addChild(iterable);

        for (ContentContext c : ctx.content()) {
            AstNode child = visit(c);
            if (child != null)
                forNode.addChild(child);
        }
        return forNode;
    }

    // =========================================================================
    // JINJA IF
    // =========================================================================

    @Override
    public AstNode visitJinja_if(Jinja_ifContext ctx) {
        int line = ctx.getStart().getLine();
        JinjaStmtNode ifNode = new JinjaStmtNode(line, "if");

        // الشرط الرئيسي
        AstNode cond = visit(ctx.jinja_expr());
        if (cond != null)
            ifNode.addChild(cond);

        // if body
        addContentBlock(ifNode, ctx.if_body);

        // elif
        for (Elif_clauseContext elif : ctx.elif_clause()) {
            JinjaStmtNode elifNode = new JinjaStmtNode(elif.getStart().getLine(), "elif");
            AstNode elifCond = visit(elif.jinja_expr());
            if (elifCond != null)
                elifNode.addChild(elifCond);
            addContentBlock(elifNode, elif.content_block());
            ifNode.addChild(elifNode);
        }

        // else
        if (ctx.else_clause() != null) {
            JinjaStmtNode elseNode = new JinjaStmtNode(
                    ctx.else_clause().getStart().getLine(), "else");
            addContentBlock(elseNode, ctx.else_clause().content_block());
            ifNode.addChild(elseNode);
        }

        return ifNode;
    }

    private void addContentBlock(AstNode parent, Content_blockContext block) {
        if (block == null)
            return;
        for (ContentContext c : block.content()) {
            AstNode child = visit(c);
            if (child != null)
                parent.addChild(child);
        }
    }

    // =========================================================================
    // JINJA EXPRESSIONS
    // =========================================================================

    @Override
    public AstNode visitJinjaName(JinjaNameContext ctx) {
        return new NameNode(ctx.JINJA_NAME().getText(), ctx.getStart().getLine());
    }

    @Override
    public AstNode visitJinjaString(JinjaStringContext ctx) {
        String text = ctx.JINJA_STRING().getText();
        text = text.substring(1, text.length() - 1);
        return new StringNode(text, ctx.getStart().getLine());
    }

    @Override
    public AstNode visitJinjaNumber(JinjaNumberContext ctx) {
        return new NumberNode(ctx.JINJA_NUMBER().getText(), ctx.getStart().getLine());
    }

    @Override
    public AstNode visitJinjaAttributeAccess(JinjaAttributeAccessContext ctx) {
        int line = ctx.getStart().getLine();
        AttrAccessNode node = new AttrAccessNode(line);
        AstNode obj = visit(ctx.jinja_expr());
        if (obj != null)
            node.addChild(obj);
        node.addChild(new NameNode(ctx.JINJA_NAME().getText(), line));
        return node;
    }

    @Override
    public AstNode visitJinjaSubscript(JinjaSubscriptContext ctx) {
        int line = ctx.getStart().getLine();
        AstNode node = new AstNode("Subscript", line) {
            @Override
            public <R> R accept(compiler.ast.visitors.AstVisitor<R> v) {
                return null;
            }
        };
        AstNode obj = visit(ctx.jinja_expr(0));
        AstNode idx = visit(ctx.jinja_expr(1));
        if (obj != null)
            node.addChild(obj);
        if (idx != null)
            node.addChild(idx);
        return node;
    }

    @Override
    public AstNode visitJinjaCompare(JinjaCompareContext ctx) {
        int line = ctx.getStart().getLine();
        BinOpNode node = new BinOpNode(ctx.op.getText(), line);
        AstNode left = visit(ctx.left);
        AstNode right = visit(ctx.right);
        if (left != null)
            node.addChild(left);
        if (right != null)
            node.addChild(right);
        return node;
    }

    @Override
    public AstNode visitJinjaNot(JinjaNotContext ctx) {
        int line = ctx.getStart().getLine();
        AstNode node = new AstNode("Not", line) {
            @Override
            public <R> R accept(compiler.ast.visitors.AstVisitor<R> v) {
                return null;
            }
        };
        AstNode child = visit(ctx.jinja_expr());
        if (child != null)
            node.addChild(child);
        return node;
    }

    @Override
    public AstNode visitJinjaAnd(JinjaAndContext ctx) {
        int line = ctx.getStart().getLine();
        BinOpNode node = new BinOpNode("and", line);
        AstNode left = visit(ctx.left);
        AstNode right = visit(ctx.right);
        if (left != null)
            node.addChild(left);
        if (right != null)
            node.addChild(right);
        return node;
    }

    @Override
    public AstNode visitJinjaOr(JinjaOrContext ctx) {
        int line = ctx.getStart().getLine();
        BinOpNode node = new BinOpNode("or", line);
        AstNode left = visit(ctx.left);
        AstNode right = visit(ctx.right);
        if (left != null)
            node.addChild(left);
        if (right != null)
            node.addChild(right);
        return node;
    }

    @Override
    public AstNode visitJinjaFunctionCall(JinjaFunctionCallContext ctx) {
        int line = ctx.getStart().getLine();
        CallNode node = new CallNode(line);
        node.addChild(new NameNode(ctx.JINJA_NAME().getText(), line));
        if (ctx.jinja_args() != null) {
            for (Jinja_argContext arg : ctx.jinja_args().jinja_arg()) {
                AstNode argNode = visit(arg);
                if (argNode != null)
                    node.addChild(argNode);
            }
        }
        return node;
    }

    @Override
    public AstNode visitJinjaPositionalArg(JinjaPositionalArgContext ctx) {
        return visit(ctx.jinja_expr());
    }

    @Override
    public AstNode visitJinjaKeywordArg(JinjaKeywordArgContext ctx) {
        int line = ctx.getStart().getLine();
        AstNode node = new AstNode("KwArg:" + ctx.JINJA_NAME().getText(), line) {
            @Override
            public <R> R accept(compiler.ast.visitors.AstVisitor<R> v) {
                return null;
            }
        };
        AstNode val = visit(ctx.jinja_expr());
        if (val != null)
            node.addChild(val);
        return node;
    }

    @Override
    public AstNode visitVoidElement(VoidElementContext ctx) {
        int line = ctx.getStart().getLine();
        String tagName = ctx.VOID_OPEN().getText().substring(1).trim();
        HtmlElementNode node = new HtmlElementNode(tagName, line);
        for (AttributeContext attr : ctx.attribute()) {
            addAttribute(node, attr);
        }
        return node;
    }

    @Override
    public AstNode visitVoidElementSlash(VoidElementSlashContext ctx) {
        int line = ctx.getStart().getLine();
        String tagName = ctx.VOID_OPEN().getText().substring(1).trim();
        HtmlElementNode node = new HtmlElementNode(tagName, line);
        for (AttributeContext attr : ctx.attribute()) {
            addAttribute(node, attr);
        }
        return node;
    }

    @Override
    public AstNode visitJinjaParenExpr(JinjaParenExprContext ctx) {
        return visit(ctx.jinja_expr());
    }

    // =========================================================================
    // JINJA COMMENT
    // =========================================================================

    @Override
    public AstNode visitJinja_comment(Jinja_commentContext ctx) {
        return null;
    }
}