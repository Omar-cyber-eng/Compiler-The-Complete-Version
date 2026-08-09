// Generated from src/compiler/grammar/TemplateParser.g4 by ANTLR 4.13.2
package compiler.parser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({ "all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape" })
public class TemplateParser extends Parser {
	static {
		RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION);
	}

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache = new PredictionContextCache();
	public static final int JINJA_BLOCK_START = 1, JINJA_VAR_START = 2, JINJA_COMMENT_START = 3, HTML_COMMENT = 4,
			DOCTYPE = 5, VOID_OPEN = 6, HTML_OPEN = 7, HTML_CLOSE = 8, HTML_TEXT = 9, HTML_WS = 10,
			TAG_CLOSE = 11, TAG_SLASH_CLOSE = 12, TAG_EQUALS = 13, TAG_NAME = 14, TAG_VALUE_PLAIN = 15,
			TAG_ATTR_JINJA_DQ_START = 16, TAG_ATTR_JINJA_SQ_START = 17, TAG_WS = 18, TAG_ATTR_DQ_JINJA_START = 19,
			TAG_ATTR_DQ_END = 20, TAG_ATTR_DQ_TEXT = 21, TAG_ATTR_SQ_JINJA_START = 22, TAG_ATTR_SQ_END = 23,
			TAG_ATTR_SQ_TEXT = 24, JINJA_BLOCK_END = 25, JINJA_VAR_END = 26, FOR = 27, IN = 28,
			IF = 29, ELIF = 30, ELSE = 31, ENDIF = 32, ENDFOR = 33, NOT = 34, AND = 35, OR = 36, COMPARE = 37,
			DOT = 38, LPAREN = 39, RPAREN = 40, LBRACK = 41, RBRACK = 42, COMMA = 43, EQUAL = 44,
			PIPE = 45, JINJA_STRING = 46, JINJA_NUMBER = 47, JINJA_NAME = 48, JINJA_WS = 49,
			JINJA_COMMENT_END = 50, JINJA_COMMENT_TEXT = 51;
	public static final int RULE_template = 0, RULE_content = 1, RULE_html_element = 2, RULE_attribute = 3,
			RULE_attr_value = 4, RULE_attr_part_dq = 5, RULE_attr_part_sq = 6, RULE_text_content = 7,
			RULE_jinja_variable = 8, RULE_filter = 9, RULE_jinja_for = 10, RULE_jinja_if = 11,
			RULE_elif_clause = 12, RULE_else_clause = 13, RULE_content_block = 14,
			RULE_jinja_comment = 15, RULE_jinja_expr = 16, RULE_jinja_args = 17, RULE_jinja_arg = 18;

	private static String[] makeRuleNames() {
		return new String[] {
				"template", "content", "html_element", "attribute", "attr_value", "attr_part_dq",
				"attr_part_sq", "text_content", "jinja_variable", "filter", "jinja_for",
				"jinja_if", "elif_clause", "else_clause", "content_block", "jinja_comment",
				"jinja_expr", "jinja_args", "jinja_arg"
		};
	}

	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
				null, "'{%'", null, "'{#'", null, null, null, null, null, null, null,
				"'>'", "'/>'", null, null, null, null, null, null, null, null, null,
				null, null, null, "'%}'", "'}}'", "'for'", "'in'", "'if'", "'elif'",
				"'else'", "'endif'", "'endfor'", "'not'", "'and'", "'or'", null, "'.'",
				"'('", "')'", "'['", "']'", "','", null, "'|'", null, null, null, null,
				"'#}'"
		};
	}

	private static final String[] _LITERAL_NAMES = makeLiteralNames();

	private static String[] makeSymbolicNames() {
		return new String[] {
				null, "JINJA_BLOCK_START", "JINJA_VAR_START", "JINJA_COMMENT_START",
				"HTML_COMMENT", "DOCTYPE", "VOID_OPEN", "HTML_OPEN", "HTML_CLOSE", "HTML_TEXT",
				"HTML_WS", "TAG_CLOSE", "TAG_SLASH_CLOSE", "TAG_EQUALS", "TAG_NAME",
				"TAG_VALUE_PLAIN", "TAG_ATTR_JINJA_DQ_START", "TAG_ATTR_JINJA_SQ_START",
				"TAG_WS", "TAG_ATTR_DQ_JINJA_START", "TAG_ATTR_DQ_END", "TAG_ATTR_DQ_TEXT",
				"TAG_ATTR_SQ_JINJA_START", "TAG_ATTR_SQ_END", "TAG_ATTR_SQ_TEXT", "JINJA_BLOCK_END",
				"JINJA_VAR_END", "FOR", "IN", "IF", "ELIF", "ELSE", "ENDIF", "ENDFOR",
				"NOT", "AND", "OR", "COMPARE", "DOT", "LPAREN", "RPAREN", "LBRACK", "RBRACK",
				"COMMA", "EQUAL", "PIPE", "JINJA_STRING", "JINJA_NUMBER", "JINJA_NAME",
				"JINJA_WS", "JINJA_COMMENT_END", "JINJA_COMMENT_TEXT"
		};
	}

	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() {
		return "TemplateParser.g4";
	}

	@Override
	public String[] getRuleNames() {
		return ruleNames;
	}

	@Override
	public String getSerializedATN() {
		return _serializedATN;
	}

	@Override
	public ATN getATN() {
		return _ATN;
	}

	public TemplateParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TemplateContext extends ParserRuleContext {
		public TerminalNode EOF() {
			return getToken(TemplateParser.EOF, 0);
		}

		public List<ContentContext> content() {
			return getRuleContexts(ContentContext.class);
		}

		public ContentContext content(int i) {
			return getRuleContext(ContentContext.class, i);
		}

		public TemplateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_template;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterTemplate(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitTemplate(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitTemplate(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final TemplateContext template() throws RecognitionException {
		TemplateContext _localctx = new TemplateContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_template);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(41);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 750L) != 0)) {
					{
						{
							setState(38);
							content();
						}
					}
					setState(43);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(44);
				match(EOF);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ContentContext extends ParserRuleContext {
		public ContentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_content;
		}

		public ContentContext() {
		}

		public void copyFrom(ContentContext ctx) {
			super.copyFrom(ctx);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class JinjaVariableContentContext extends ContentContext {
		public Jinja_variableContext jinja_variable() {
			return getRuleContext(Jinja_variableContext.class, 0);
		}

		public JinjaVariableContentContext(ContentContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterJinjaVariableContent(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitJinjaVariableContent(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitJinjaVariableContent(this);
			else
				return visitor.visitChildren(this);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class HtmlContentContext extends ContentContext {
		public Html_elementContext html_element() {
			return getRuleContext(Html_elementContext.class, 0);
		}

		public HtmlContentContext(ContentContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterHtmlContent(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitHtmlContent(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitHtmlContent(this);
			else
				return visitor.visitChildren(this);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class JinjaCommentContentContext extends ContentContext {
		public Jinja_commentContext jinja_comment() {
			return getRuleContext(Jinja_commentContext.class, 0);
		}

		public JinjaCommentContentContext(ContentContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterJinjaCommentContent(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitJinjaCommentContent(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitJinjaCommentContent(this);
			else
				return visitor.visitChildren(this);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class JinjaIfContentContext extends ContentContext {
		public Jinja_ifContext jinja_if() {
			return getRuleContext(Jinja_ifContext.class, 0);
		}

		public JinjaIfContentContext(ContentContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterJinjaIfContent(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitJinjaIfContent(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitJinjaIfContent(this);
			else
				return visitor.visitChildren(this);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class JinjaForContentContext extends ContentContext {
		public Jinja_forContext jinja_for() {
			return getRuleContext(Jinja_forContext.class, 0);
		}

		public JinjaForContentContext(ContentContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterJinjaForContent(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitJinjaForContent(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitJinjaForContent(this);
			else
				return visitor.visitChildren(this);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TextContentContext extends ContentContext {
		public Text_contentContext text_content() {
			return getRuleContext(Text_contentContext.class, 0);
		}

		public TextContentContext(ContentContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterTextContent(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitTextContent(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitTextContent(this);
			else
				return visitor.visitChildren(this);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DoctypeContentContext extends ContentContext {
		public TerminalNode DOCTYPE() {
			return getToken(TemplateParser.DOCTYPE, 0);
		}

		public DoctypeContentContext(ContentContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterDoctypeContent(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitDoctypeContent(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitDoctypeContent(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final ContentContext content() throws RecognitionException {
		ContentContext _localctx = new ContentContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_content);
		try {
			setState(53);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 1, _ctx)) {
				case 1:
					_localctx = new HtmlContentContext(_localctx);
					enterOuterAlt(_localctx, 1); {
					setState(46);
					html_element();
				}
					break;
				case 2:
					_localctx = new TextContentContext(_localctx);
					enterOuterAlt(_localctx, 2); {
					setState(47);
					text_content();
				}
					break;
				case 3:
					_localctx = new JinjaVariableContentContext(_localctx);
					enterOuterAlt(_localctx, 3); {
					setState(48);
					jinja_variable();
				}
					break;
				case 4:
					_localctx = new JinjaForContentContext(_localctx);
					enterOuterAlt(_localctx, 4); {
					setState(49);
					jinja_for();
				}
					break;
				case 5:
					_localctx = new JinjaIfContentContext(_localctx);
					enterOuterAlt(_localctx, 5); {
					setState(50);
					jinja_if();
				}
					break;
				case 6:
					_localctx = new JinjaCommentContentContext(_localctx);
					enterOuterAlt(_localctx, 6); {
					setState(51);
					jinja_comment();
				}
					break;
				case 7:
					_localctx = new DoctypeContentContext(_localctx);
					enterOuterAlt(_localctx, 7); {
					setState(52);
					match(DOCTYPE);
				}
					break;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Html_elementContext extends ParserRuleContext {
		public Html_elementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_html_element;
		}

		public Html_elementContext() {
		}

		public void copyFrom(Html_elementContext ctx) {
			super.copyFrom(ctx);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VoidElementContext extends Html_elementContext {
		public TerminalNode VOID_OPEN() {
			return getToken(TemplateParser.VOID_OPEN, 0);
		}

		public TerminalNode TAG_CLOSE() {
			return getToken(TemplateParser.TAG_CLOSE, 0);
		}

		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}

		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class, i);
		}

		public VoidElementContext(Html_elementContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterVoidElement(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitVoidElement(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitVoidElement(this);
			else
				return visitor.visitChildren(this);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SelfClosingElementContext extends Html_elementContext {
		public TerminalNode HTML_OPEN() {
			return getToken(TemplateParser.HTML_OPEN, 0);
		}

		public TerminalNode TAG_SLASH_CLOSE() {
			return getToken(TemplateParser.TAG_SLASH_CLOSE, 0);
		}

		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}

		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class, i);
		}

		public SelfClosingElementContext(Html_elementContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterSelfClosingElement(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitSelfClosingElement(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitSelfClosingElement(this);
			else
				return visitor.visitChildren(this);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VoidElementSlashContext extends Html_elementContext {
		public TerminalNode VOID_OPEN() {
			return getToken(TemplateParser.VOID_OPEN, 0);
		}

		public TerminalNode TAG_SLASH_CLOSE() {
			return getToken(TemplateParser.TAG_SLASH_CLOSE, 0);
		}

		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}

		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class, i);
		}

		public VoidElementSlashContext(Html_elementContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterVoidElementSlash(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitVoidElementSlash(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitVoidElementSlash(this);
			else
				return visitor.visitChildren(this);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NormalElementContext extends Html_elementContext {
		public TerminalNode HTML_OPEN() {
			return getToken(TemplateParser.HTML_OPEN, 0);
		}

		public TerminalNode TAG_CLOSE() {
			return getToken(TemplateParser.TAG_CLOSE, 0);
		}

		public TerminalNode HTML_CLOSE() {
			return getToken(TemplateParser.HTML_CLOSE, 0);
		}

		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}

		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class, i);
		}

		public List<ContentContext> content() {
			return getRuleContexts(ContentContext.class);
		}

		public ContentContext content(int i) {
			return getRuleContext(ContentContext.class, i);
		}

		public NormalElementContext(Html_elementContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterNormalElement(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitNormalElement(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitNormalElement(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final Html_elementContext html_element() throws RecognitionException {
		Html_elementContext _localctx = new Html_elementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_html_element);
		int _la;
		try {
			setState(94);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 7, _ctx)) {
				case 1:
					_localctx = new NormalElementContext(_localctx);
					enterOuterAlt(_localctx, 1); {
					setState(55);
					match(HTML_OPEN);
					setState(59);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la == TAG_NAME) {
						{
							{
								setState(56);
								attribute();
							}
						}
						setState(61);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(62);
					match(TAG_CLOSE);
					setState(66);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 750L) != 0)) {
						{
							{
								setState(63);
								content();
							}
						}
						setState(68);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(69);
					match(HTML_CLOSE);
				}
					break;
				case 2:
					_localctx = new SelfClosingElementContext(_localctx);
					enterOuterAlt(_localctx, 2); {
					setState(70);
					match(HTML_OPEN);
					setState(74);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la == TAG_NAME) {
						{
							{
								setState(71);
								attribute();
							}
						}
						setState(76);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(77);
					match(TAG_SLASH_CLOSE);
				}
					break;
				case 3:
					_localctx = new VoidElementContext(_localctx);
					enterOuterAlt(_localctx, 3); {
					setState(78);
					match(VOID_OPEN);
					setState(82);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la == TAG_NAME) {
						{
							{
								setState(79);
								attribute();
							}
						}
						setState(84);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(85);
					match(TAG_CLOSE);
				}
					break;
				case 4:
					_localctx = new VoidElementSlashContext(_localctx);
					enterOuterAlt(_localctx, 4); {
					setState(86);
					match(VOID_OPEN);
					setState(90);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la == TAG_NAME) {
						{
							{
								setState(87);
								attribute();
							}
						}
						setState(92);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(93);
					match(TAG_SLASH_CLOSE);
				}
					break;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AttributeContext extends ParserRuleContext {
		public AttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_attribute;
		}

		public AttributeContext() {
		}

		public void copyFrom(AttributeContext ctx) {
			super.copyFrom(ctx);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BooleanAttributeContext extends AttributeContext {
		public TerminalNode TAG_NAME() {
			return getToken(TemplateParser.TAG_NAME, 0);
		}

		public BooleanAttributeContext(AttributeContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterBooleanAttribute(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitBooleanAttribute(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitBooleanAttribute(this);
			else
				return visitor.visitChildren(this);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StaticAttributeContext extends AttributeContext {
		public TerminalNode TAG_NAME() {
			return getToken(TemplateParser.TAG_NAME, 0);
		}

		public TerminalNode TAG_EQUALS() {
			return getToken(TemplateParser.TAG_EQUALS, 0);
		}

		public Attr_valueContext attr_value() {
			return getRuleContext(Attr_valueContext.class, 0);
		}

		public StaticAttributeContext(AttributeContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterStaticAttribute(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitStaticAttribute(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitStaticAttribute(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final AttributeContext attribute() throws RecognitionException {
		AttributeContext _localctx = new AttributeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_attribute);
		try {
			setState(100);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 8, _ctx)) {
				case 1:
					_localctx = new StaticAttributeContext(_localctx);
					enterOuterAlt(_localctx, 1); {
					setState(96);
					match(TAG_NAME);
					setState(97);
					match(TAG_EQUALS);
					setState(98);
					attr_value();
				}
					break;
				case 2:
					_localctx = new BooleanAttributeContext(_localctx);
					enterOuterAlt(_localctx, 2); {
					setState(99);
					match(TAG_NAME);
				}
					break;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Attr_valueContext extends ParserRuleContext {
		public Attr_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_attr_value;
		}

		public Attr_valueContext() {
		}

		public void copyFrom(Attr_valueContext ctx) {
			super.copyFrom(ctx);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PlainAttrValueContext extends Attr_valueContext {
		public TerminalNode TAG_VALUE_PLAIN() {
			return getToken(TemplateParser.TAG_VALUE_PLAIN, 0);
		}

		public PlainAttrValueContext(Attr_valueContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterPlainAttrValue(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitPlainAttrValue(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitPlainAttrValue(this);
			else
				return visitor.visitChildren(this);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DynamicAttrValueSQContext extends Attr_valueContext {
		public TerminalNode TAG_ATTR_JINJA_SQ_START() {
			return getToken(TemplateParser.TAG_ATTR_JINJA_SQ_START, 0);
		}

		public TerminalNode TAG_ATTR_SQ_END() {
			return getToken(TemplateParser.TAG_ATTR_SQ_END, 0);
		}

		public List<Attr_part_sqContext> attr_part_sq() {
			return getRuleContexts(Attr_part_sqContext.class);
		}

		public Attr_part_sqContext attr_part_sq(int i) {
			return getRuleContext(Attr_part_sqContext.class, i);
		}

		public DynamicAttrValueSQContext(Attr_valueContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterDynamicAttrValueSQ(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitDynamicAttrValueSQ(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitDynamicAttrValueSQ(this);
			else
				return visitor.visitChildren(this);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DynamicAttrValueDQContext extends Attr_valueContext {
		public TerminalNode TAG_ATTR_JINJA_DQ_START() {
			return getToken(TemplateParser.TAG_ATTR_JINJA_DQ_START, 0);
		}

		public TerminalNode TAG_ATTR_DQ_END() {
			return getToken(TemplateParser.TAG_ATTR_DQ_END, 0);
		}

		public List<Attr_part_dqContext> attr_part_dq() {
			return getRuleContexts(Attr_part_dqContext.class);
		}

		public Attr_part_dqContext attr_part_dq(int i) {
			return getRuleContext(Attr_part_dqContext.class, i);
		}

		public DynamicAttrValueDQContext(Attr_valueContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterDynamicAttrValueDQ(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitDynamicAttrValueDQ(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitDynamicAttrValueDQ(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final Attr_valueContext attr_value() throws RecognitionException {
		Attr_valueContext _localctx = new Attr_valueContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_attr_value);
		int _la;
		try {
			setState(119);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case TAG_VALUE_PLAIN:
					_localctx = new PlainAttrValueContext(_localctx);
					enterOuterAlt(_localctx, 1); {
					setState(102);
					match(TAG_VALUE_PLAIN);
				}
					break;
				case TAG_ATTR_JINJA_DQ_START:
					_localctx = new DynamicAttrValueDQContext(_localctx);
					enterOuterAlt(_localctx, 2); {
					setState(103);
					match(TAG_ATTR_JINJA_DQ_START);
					setState(107);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la == TAG_ATTR_DQ_JINJA_START || _la == TAG_ATTR_DQ_TEXT) {
						{
							{
								setState(104);
								attr_part_dq();
							}
						}
						setState(109);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(110);
					match(TAG_ATTR_DQ_END);
				}
					break;
				case TAG_ATTR_JINJA_SQ_START:
					_localctx = new DynamicAttrValueSQContext(_localctx);
					enterOuterAlt(_localctx, 3); {
					setState(111);
					match(TAG_ATTR_JINJA_SQ_START);
					setState(115);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la == TAG_ATTR_SQ_JINJA_START || _la == TAG_ATTR_SQ_TEXT) {
						{
							{
								setState(112);
								attr_part_sq();
							}
						}
						setState(117);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(118);
					match(TAG_ATTR_SQ_END);
				}
					break;
				default:
					throw new NoViableAltException(this);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Attr_part_dqContext extends ParserRuleContext {
		public Attr_part_dqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_attr_part_dq;
		}

		public Attr_part_dqContext() {
		}

		public void copyFrom(Attr_part_dqContext ctx) {
			super.copyFrom(ctx);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AttrTextPartDQContext extends Attr_part_dqContext {
		public TerminalNode TAG_ATTR_DQ_TEXT() {
			return getToken(TemplateParser.TAG_ATTR_DQ_TEXT, 0);
		}

		public AttrTextPartDQContext(Attr_part_dqContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterAttrTextPartDQ(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitAttrTextPartDQ(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitAttrTextPartDQ(this);
			else
				return visitor.visitChildren(this);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AttrJinjaPartDQContext extends Attr_part_dqContext {
		public TerminalNode TAG_ATTR_DQ_JINJA_START() {
			return getToken(TemplateParser.TAG_ATTR_DQ_JINJA_START, 0);
		}

		public Jinja_exprContext jinja_expr() {
			return getRuleContext(Jinja_exprContext.class, 0);
		}

		public TerminalNode JINJA_VAR_END() {
			return getToken(TemplateParser.JINJA_VAR_END, 0);
		}

		public AttrJinjaPartDQContext(Attr_part_dqContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterAttrJinjaPartDQ(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitAttrJinjaPartDQ(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitAttrJinjaPartDQ(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final Attr_part_dqContext attr_part_dq() throws RecognitionException {
		Attr_part_dqContext _localctx = new Attr_part_dqContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_attr_part_dq);
		try {
			setState(126);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case TAG_ATTR_DQ_TEXT:
					_localctx = new AttrTextPartDQContext(_localctx);
					enterOuterAlt(_localctx, 1); {
					setState(121);
					match(TAG_ATTR_DQ_TEXT);
				}
					break;
				case TAG_ATTR_DQ_JINJA_START:
					_localctx = new AttrJinjaPartDQContext(_localctx);
					enterOuterAlt(_localctx, 2); {
					setState(122);
					match(TAG_ATTR_DQ_JINJA_START);
					setState(123);
					jinja_expr(0);
					setState(124);
					match(JINJA_VAR_END);
				}
					break;
				default:
					throw new NoViableAltException(this);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Attr_part_sqContext extends ParserRuleContext {
		public Attr_part_sqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_attr_part_sq;
		}

		public Attr_part_sqContext() {
		}

		public void copyFrom(Attr_part_sqContext ctx) {
			super.copyFrom(ctx);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AttrTextPartSQContext extends Attr_part_sqContext {
		public TerminalNode TAG_ATTR_SQ_TEXT() {
			return getToken(TemplateParser.TAG_ATTR_SQ_TEXT, 0);
		}

		public AttrTextPartSQContext(Attr_part_sqContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterAttrTextPartSQ(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitAttrTextPartSQ(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitAttrTextPartSQ(this);
			else
				return visitor.visitChildren(this);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AttrJinjaPartSQContext extends Attr_part_sqContext {
		public TerminalNode TAG_ATTR_SQ_JINJA_START() {
			return getToken(TemplateParser.TAG_ATTR_SQ_JINJA_START, 0);
		}

		public Jinja_exprContext jinja_expr() {
			return getRuleContext(Jinja_exprContext.class, 0);
		}

		public TerminalNode JINJA_VAR_END() {
			return getToken(TemplateParser.JINJA_VAR_END, 0);
		}

		public AttrJinjaPartSQContext(Attr_part_sqContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterAttrJinjaPartSQ(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitAttrJinjaPartSQ(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitAttrJinjaPartSQ(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final Attr_part_sqContext attr_part_sq() throws RecognitionException {
		Attr_part_sqContext _localctx = new Attr_part_sqContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_attr_part_sq);
		try {
			setState(133);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case TAG_ATTR_SQ_TEXT:
					_localctx = new AttrTextPartSQContext(_localctx);
					enterOuterAlt(_localctx, 1); {
					setState(128);
					match(TAG_ATTR_SQ_TEXT);
				}
					break;
				case TAG_ATTR_SQ_JINJA_START:
					_localctx = new AttrJinjaPartSQContext(_localctx);
					enterOuterAlt(_localctx, 2); {
					setState(129);
					match(TAG_ATTR_SQ_JINJA_START);
					setState(130);
					jinja_expr(0);
					setState(131);
					match(JINJA_VAR_END);
				}
					break;
				default:
					throw new NoViableAltException(this);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Text_contentContext extends ParserRuleContext {
		public TerminalNode HTML_TEXT() {
			return getToken(TemplateParser.HTML_TEXT, 0);
		}

		public Text_contentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_text_content;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterText_content(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitText_content(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitText_content(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final Text_contentContext text_content() throws RecognitionException {
		Text_contentContext _localctx = new Text_contentContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_text_content);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(135);
				match(HTML_TEXT);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Jinja_variableContext extends ParserRuleContext {
		public TerminalNode JINJA_VAR_START() {
			return getToken(TemplateParser.JINJA_VAR_START, 0);
		}

		public Jinja_exprContext jinja_expr() {
			return getRuleContext(Jinja_exprContext.class, 0);
		}

		public TerminalNode JINJA_VAR_END() {
			return getToken(TemplateParser.JINJA_VAR_END, 0);
		}

		public List<FilterContext> filter() {
			return getRuleContexts(FilterContext.class);
		}

		public FilterContext filter(int i) {
			return getRuleContext(FilterContext.class, i);
		}

		public Jinja_variableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_jinja_variable;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterJinja_variable(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitJinja_variable(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitJinja_variable(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final Jinja_variableContext jinja_variable() throws RecognitionException {
		Jinja_variableContext _localctx = new Jinja_variableContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_jinja_variable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(137);
				match(JINJA_VAR_START);
				setState(138);
				jinja_expr(0);
				setState(142);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == PIPE) {
					{
						{
							setState(139);
							filter();
						}
					}
					setState(144);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(145);
				match(JINJA_VAR_END);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FilterContext extends ParserRuleContext {
		public TerminalNode PIPE() {
			return getToken(TemplateParser.PIPE, 0);
		}

		public TerminalNode JINJA_NAME() {
			return getToken(TemplateParser.JINJA_NAME, 0);
		}

		public TerminalNode LPAREN() {
			return getToken(TemplateParser.LPAREN, 0);
		}

		public TerminalNode RPAREN() {
			return getToken(TemplateParser.RPAREN, 0);
		}

		public Jinja_argsContext jinja_args() {
			return getRuleContext(Jinja_argsContext.class, 0);
		}

		public FilterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_filter;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterFilter(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitFilter(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitFilter(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final FilterContext filter() throws RecognitionException {
		FilterContext _localctx = new FilterContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_filter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(147);
				match(PIPE);
				setState(148);
				match(JINJA_NAME);
				setState(154);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == LPAREN) {
					{
						setState(149);
						match(LPAREN);
						setState(151);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 493148144926720L) != 0)) {
							{
								setState(150);
								jinja_args();
							}
						}

						setState(153);
						match(RPAREN);
					}
				}

			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Jinja_forContext extends ParserRuleContext {
		public List<TerminalNode> JINJA_BLOCK_START() {
			return getTokens(TemplateParser.JINJA_BLOCK_START);
		}

		public TerminalNode JINJA_BLOCK_START(int i) {
			return getToken(TemplateParser.JINJA_BLOCK_START, i);
		}

		public TerminalNode FOR() {
			return getToken(TemplateParser.FOR, 0);
		}

		public TerminalNode JINJA_NAME() {
			return getToken(TemplateParser.JINJA_NAME, 0);
		}

		public TerminalNode IN() {
			return getToken(TemplateParser.IN, 0);
		}

		public Jinja_exprContext jinja_expr() {
			return getRuleContext(Jinja_exprContext.class, 0);
		}

		public List<TerminalNode> JINJA_BLOCK_END() {
			return getTokens(TemplateParser.JINJA_BLOCK_END);
		}

		public TerminalNode JINJA_BLOCK_END(int i) {
			return getToken(TemplateParser.JINJA_BLOCK_END, i);
		}

		public TerminalNode ENDFOR() {
			return getToken(TemplateParser.ENDFOR, 0);
		}

		public List<ContentContext> content() {
			return getRuleContexts(ContentContext.class);
		}

		public ContentContext content(int i) {
			return getRuleContext(ContentContext.class, i);
		}

		public Jinja_forContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_jinja_for;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterJinja_for(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitJinja_for(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitJinja_for(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final Jinja_forContext jinja_for() throws RecognitionException {
		Jinja_forContext _localctx = new Jinja_forContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_jinja_for);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(156);
				match(JINJA_BLOCK_START);
				setState(157);
				match(FOR);
				setState(158);
				match(JINJA_NAME);
				setState(159);
				match(IN);
				setState(160);
				jinja_expr(0);
				setState(161);
				match(JINJA_BLOCK_END);
				setState(165);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 17, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						{
							{
								setState(162);
								content();
							}
						}
					}
					setState(167);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 17, _ctx);
				}
				setState(168);
				match(JINJA_BLOCK_START);
				setState(169);
				match(ENDFOR);
				setState(170);
				match(JINJA_BLOCK_END);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Jinja_ifContext extends ParserRuleContext {
		public Content_blockContext if_body;

		public List<TerminalNode> JINJA_BLOCK_START() {
			return getTokens(TemplateParser.JINJA_BLOCK_START);
		}

		public TerminalNode JINJA_BLOCK_START(int i) {
			return getToken(TemplateParser.JINJA_BLOCK_START, i);
		}

		public TerminalNode IF() {
			return getToken(TemplateParser.IF, 0);
		}

		public Jinja_exprContext jinja_expr() {
			return getRuleContext(Jinja_exprContext.class, 0);
		}

		public List<TerminalNode> JINJA_BLOCK_END() {
			return getTokens(TemplateParser.JINJA_BLOCK_END);
		}

		public TerminalNode JINJA_BLOCK_END(int i) {
			return getToken(TemplateParser.JINJA_BLOCK_END, i);
		}

		public TerminalNode ENDIF() {
			return getToken(TemplateParser.ENDIF, 0);
		}

		public Content_blockContext content_block() {
			return getRuleContext(Content_blockContext.class, 0);
		}

		public List<Elif_clauseContext> elif_clause() {
			return getRuleContexts(Elif_clauseContext.class);
		}

		public Elif_clauseContext elif_clause(int i) {
			return getRuleContext(Elif_clauseContext.class, i);
		}

		public Else_clauseContext else_clause() {
			return getRuleContext(Else_clauseContext.class, 0);
		}

		public Jinja_ifContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_jinja_if;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterJinja_if(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitJinja_if(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitJinja_if(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final Jinja_ifContext jinja_if() throws RecognitionException {
		Jinja_ifContext _localctx = new Jinja_ifContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_jinja_if);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(172);
				match(JINJA_BLOCK_START);
				setState(173);
				match(IF);
				setState(174);
				jinja_expr(0);
				setState(175);
				match(JINJA_BLOCK_END);
				setState(176);
				((Jinja_ifContext) _localctx).if_body = content_block();
				setState(180);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 18, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						{
							{
								setState(177);
								elif_clause();
							}
						}
					}
					setState(182);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 18, _ctx);
				}
				setState(184);
				_errHandler.sync(this);
				switch (getInterpreter().adaptivePredict(_input, 19, _ctx)) {
					case 1: {
						setState(183);
						else_clause();
					}
						break;
				}
				setState(186);
				match(JINJA_BLOCK_START);
				setState(187);
				match(ENDIF);
				setState(188);
				match(JINJA_BLOCK_END);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Elif_clauseContext extends ParserRuleContext {
		public TerminalNode JINJA_BLOCK_START() {
			return getToken(TemplateParser.JINJA_BLOCK_START, 0);
		}

		public TerminalNode ELIF() {
			return getToken(TemplateParser.ELIF, 0);
		}

		public Jinja_exprContext jinja_expr() {
			return getRuleContext(Jinja_exprContext.class, 0);
		}

		public TerminalNode JINJA_BLOCK_END() {
			return getToken(TemplateParser.JINJA_BLOCK_END, 0);
		}

		public Content_blockContext content_block() {
			return getRuleContext(Content_blockContext.class, 0);
		}

		public Elif_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_elif_clause;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterElif_clause(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitElif_clause(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitElif_clause(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final Elif_clauseContext elif_clause() throws RecognitionException {
		Elif_clauseContext _localctx = new Elif_clauseContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_elif_clause);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(190);
				match(JINJA_BLOCK_START);
				setState(191);
				match(ELIF);
				setState(192);
				jinja_expr(0);
				setState(193);
				match(JINJA_BLOCK_END);
				setState(194);
				content_block();
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Else_clauseContext extends ParserRuleContext {
		public TerminalNode JINJA_BLOCK_START() {
			return getToken(TemplateParser.JINJA_BLOCK_START, 0);
		}

		public TerminalNode ELSE() {
			return getToken(TemplateParser.ELSE, 0);
		}

		public TerminalNode JINJA_BLOCK_END() {
			return getToken(TemplateParser.JINJA_BLOCK_END, 0);
		}

		public Content_blockContext content_block() {
			return getRuleContext(Content_blockContext.class, 0);
		}

		public Else_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_else_clause;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterElse_clause(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitElse_clause(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitElse_clause(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final Else_clauseContext else_clause() throws RecognitionException {
		Else_clauseContext _localctx = new Else_clauseContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_else_clause);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(196);
				match(JINJA_BLOCK_START);
				setState(197);
				match(ELSE);
				setState(198);
				match(JINJA_BLOCK_END);
				setState(199);
				content_block();
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Content_blockContext extends ParserRuleContext {
		public List<ContentContext> content() {
			return getRuleContexts(ContentContext.class);
		}

		public ContentContext content(int i) {
			return getRuleContext(ContentContext.class, i);
		}

		public Content_blockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_content_block;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterContent_block(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitContent_block(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitContent_block(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final Content_blockContext content_block() throws RecognitionException {
		Content_blockContext _localctx = new Content_blockContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_content_block);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(204);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 20, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						{
							{
								setState(201);
								content();
							}
						}
					}
					setState(206);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 20, _ctx);
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Jinja_commentContext extends ParserRuleContext {
		public TerminalNode JINJA_COMMENT_START() {
			return getToken(TemplateParser.JINJA_COMMENT_START, 0);
		}

		public TerminalNode JINJA_COMMENT_END() {
			return getToken(TemplateParser.JINJA_COMMENT_END, 0);
		}

		public TerminalNode JINJA_COMMENT_TEXT() {
			return getToken(TemplateParser.JINJA_COMMENT_TEXT, 0);
		}

		public Jinja_commentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_jinja_comment;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterJinja_comment(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitJinja_comment(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitJinja_comment(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final Jinja_commentContext jinja_comment() throws RecognitionException {
		Jinja_commentContext _localctx = new Jinja_commentContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_jinja_comment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(207);
				match(JINJA_COMMENT_START);
				setState(209);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == JINJA_COMMENT_TEXT) {
					{
						setState(208);
						match(JINJA_COMMENT_TEXT);
					}
				}

				setState(211);
				match(JINJA_COMMENT_END);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Jinja_exprContext extends ParserRuleContext {
		public Jinja_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_jinja_expr;
		}

		public Jinja_exprContext() {
		}

		public void copyFrom(Jinja_exprContext ctx) {
			super.copyFrom(ctx);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class JinjaNotContext extends Jinja_exprContext {
		public TerminalNode NOT() {
			return getToken(TemplateParser.NOT, 0);
		}

		public Jinja_exprContext jinja_expr() {
			return getRuleContext(Jinja_exprContext.class, 0);
		}

		public JinjaNotContext(Jinja_exprContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterJinjaNot(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitJinjaNot(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitJinjaNot(this);
			else
				return visitor.visitChildren(this);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class JinjaOrContext extends Jinja_exprContext {
		public Jinja_exprContext left;
		public Jinja_exprContext right;

		public TerminalNode OR() {
			return getToken(TemplateParser.OR, 0);
		}

		public List<Jinja_exprContext> jinja_expr() {
			return getRuleContexts(Jinja_exprContext.class);
		}

		public Jinja_exprContext jinja_expr(int i) {
			return getRuleContext(Jinja_exprContext.class, i);
		}

		public JinjaOrContext(Jinja_exprContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterJinjaOr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitJinjaOr(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitJinjaOr(this);
			else
				return visitor.visitChildren(this);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class JinjaFunctionCallContext extends Jinja_exprContext {
		public TerminalNode JINJA_NAME() {
			return getToken(TemplateParser.JINJA_NAME, 0);
		}

		public TerminalNode LPAREN() {
			return getToken(TemplateParser.LPAREN, 0);
		}

		public TerminalNode RPAREN() {
			return getToken(TemplateParser.RPAREN, 0);
		}

		public Jinja_argsContext jinja_args() {
			return getRuleContext(Jinja_argsContext.class, 0);
		}

		public JinjaFunctionCallContext(Jinja_exprContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterJinjaFunctionCall(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitJinjaFunctionCall(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitJinjaFunctionCall(this);
			else
				return visitor.visitChildren(this);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class JinjaStringContext extends Jinja_exprContext {
		public TerminalNode JINJA_STRING() {
			return getToken(TemplateParser.JINJA_STRING, 0);
		}

		public JinjaStringContext(Jinja_exprContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterJinjaString(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitJinjaString(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitJinjaString(this);
			else
				return visitor.visitChildren(this);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class JinjaParenExprContext extends Jinja_exprContext {
		public TerminalNode LPAREN() {
			return getToken(TemplateParser.LPAREN, 0);
		}

		public Jinja_exprContext jinja_expr() {
			return getRuleContext(Jinja_exprContext.class, 0);
		}

		public TerminalNode RPAREN() {
			return getToken(TemplateParser.RPAREN, 0);
		}

		public JinjaParenExprContext(Jinja_exprContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterJinjaParenExpr(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitJinjaParenExpr(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitJinjaParenExpr(this);
			else
				return visitor.visitChildren(this);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class JinjaCompareContext extends Jinja_exprContext {
		public Jinja_exprContext left;
		public Token op;
		public Jinja_exprContext right;

		public List<Jinja_exprContext> jinja_expr() {
			return getRuleContexts(Jinja_exprContext.class);
		}

		public Jinja_exprContext jinja_expr(int i) {
			return getRuleContext(Jinja_exprContext.class, i);
		}

		public TerminalNode COMPARE() {
			return getToken(TemplateParser.COMPARE, 0);
		}

		public JinjaCompareContext(Jinja_exprContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterJinjaCompare(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitJinjaCompare(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitJinjaCompare(this);
			else
				return visitor.visitChildren(this);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class JinjaSubscriptContext extends Jinja_exprContext {
		public List<Jinja_exprContext> jinja_expr() {
			return getRuleContexts(Jinja_exprContext.class);
		}

		public Jinja_exprContext jinja_expr(int i) {
			return getRuleContext(Jinja_exprContext.class, i);
		}

		public TerminalNode LBRACK() {
			return getToken(TemplateParser.LBRACK, 0);
		}

		public TerminalNode RBRACK() {
			return getToken(TemplateParser.RBRACK, 0);
		}

		public JinjaSubscriptContext(Jinja_exprContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterJinjaSubscript(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitJinjaSubscript(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitJinjaSubscript(this);
			else
				return visitor.visitChildren(this);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class JinjaAttributeAccessContext extends Jinja_exprContext {
		public Jinja_exprContext jinja_expr() {
			return getRuleContext(Jinja_exprContext.class, 0);
		}

		public TerminalNode DOT() {
			return getToken(TemplateParser.DOT, 0);
		}

		public TerminalNode JINJA_NAME() {
			return getToken(TemplateParser.JINJA_NAME, 0);
		}

		public JinjaAttributeAccessContext(Jinja_exprContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterJinjaAttributeAccess(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitJinjaAttributeAccess(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitJinjaAttributeAccess(this);
			else
				return visitor.visitChildren(this);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class JinjaNumberContext extends Jinja_exprContext {
		public TerminalNode JINJA_NUMBER() {
			return getToken(TemplateParser.JINJA_NUMBER, 0);
		}

		public JinjaNumberContext(Jinja_exprContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterJinjaNumber(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitJinjaNumber(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitJinjaNumber(this);
			else
				return visitor.visitChildren(this);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class JinjaAndContext extends Jinja_exprContext {
		public Jinja_exprContext left;
		public Jinja_exprContext right;

		public TerminalNode AND() {
			return getToken(TemplateParser.AND, 0);
		}

		public List<Jinja_exprContext> jinja_expr() {
			return getRuleContexts(Jinja_exprContext.class);
		}

		public Jinja_exprContext jinja_expr(int i) {
			return getRuleContext(Jinja_exprContext.class, i);
		}

		public JinjaAndContext(Jinja_exprContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterJinjaAnd(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitJinjaAnd(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitJinjaAnd(this);
			else
				return visitor.visitChildren(this);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class JinjaNameContext extends Jinja_exprContext {
		public TerminalNode JINJA_NAME() {
			return getToken(TemplateParser.JINJA_NAME, 0);
		}

		public JinjaNameContext(Jinja_exprContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterJinjaName(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitJinjaName(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitJinjaName(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final Jinja_exprContext jinja_expr() throws RecognitionException {
		return jinja_expr(0);
	}

	private Jinja_exprContext jinja_expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Jinja_exprContext _localctx = new Jinja_exprContext(_ctx, _parentState);
		Jinja_exprContext _prevctx = _localctx;
		int _startState = 32;
		enterRecursionRule(_localctx, 32, RULE_jinja_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(229);
				_errHandler.sync(this);
				switch (getInterpreter().adaptivePredict(_input, 23, _ctx)) {
					case 1: {
						_localctx = new JinjaNotContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;

						setState(214);
						match(NOT);
						setState(215);
						jinja_expr(10);
					}
						break;
					case 2: {
						_localctx = new JinjaFunctionCallContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(216);
						match(JINJA_NAME);
						setState(217);
						match(LPAREN);
						setState(219);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 493148144926720L) != 0)) {
							{
								setState(218);
								jinja_args();
							}
						}

						setState(221);
						match(RPAREN);
					}
						break;
					case 3: {
						_localctx = new JinjaNameContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(222);
						match(JINJA_NAME);
					}
						break;
					case 4: {
						_localctx = new JinjaStringContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(223);
						match(JINJA_STRING);
					}
						break;
					case 5: {
						_localctx = new JinjaNumberContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(224);
						match(JINJA_NUMBER);
					}
						break;
					case 6: {
						_localctx = new JinjaParenExprContext(_localctx);
						_ctx = _localctx;
						_prevctx = _localctx;
						setState(225);
						match(LPAREN);
						setState(226);
						jinja_expr(0);
						setState(227);
						match(RPAREN);
					}
						break;
				}
				_ctx.stop = _input.LT(-1);
				setState(250);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 25, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						if (_parseListeners != null)
							triggerExitRuleEvent();
						_prevctx = _localctx;
						{
							setState(248);
							_errHandler.sync(this);
							switch (getInterpreter().adaptivePredict(_input, 24, _ctx)) {
								case 1: {
									_localctx = new JinjaCompareContext(
											new Jinja_exprContext(_parentctx, _parentState));
									((JinjaCompareContext) _localctx).left = _prevctx;
									pushNewRecursionContext(_localctx, _startState, RULE_jinja_expr);
									setState(231);
									if (!(precpred(_ctx, 11)))
										throw new FailedPredicateException(this, "precpred(_ctx, 11)");
									setState(232);
									((JinjaCompareContext) _localctx).op = match(COMPARE);
									setState(233);
									((JinjaCompareContext) _localctx).right = jinja_expr(12);
								}
									break;
								case 2: {
									_localctx = new JinjaAndContext(new Jinja_exprContext(_parentctx, _parentState));
									((JinjaAndContext) _localctx).left = _prevctx;
									pushNewRecursionContext(_localctx, _startState, RULE_jinja_expr);
									setState(234);
									if (!(precpred(_ctx, 9)))
										throw new FailedPredicateException(this, "precpred(_ctx, 9)");
									setState(235);
									match(AND);
									setState(236);
									((JinjaAndContext) _localctx).right = jinja_expr(10);
								}
									break;
								case 3: {
									_localctx = new JinjaOrContext(new Jinja_exprContext(_parentctx, _parentState));
									((JinjaOrContext) _localctx).left = _prevctx;
									pushNewRecursionContext(_localctx, _startState, RULE_jinja_expr);
									setState(237);
									if (!(precpred(_ctx, 8)))
										throw new FailedPredicateException(this, "precpred(_ctx, 8)");
									setState(238);
									match(OR);
									setState(239);
									((JinjaOrContext) _localctx).right = jinja_expr(9);
								}
									break;
								case 4: {
									_localctx = new JinjaAttributeAccessContext(
											new Jinja_exprContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_jinja_expr);
									setState(240);
									if (!(precpred(_ctx, 7)))
										throw new FailedPredicateException(this, "precpred(_ctx, 7)");
									setState(241);
									match(DOT);
									setState(242);
									match(JINJA_NAME);
								}
									break;
								case 5: {
									_localctx = new JinjaSubscriptContext(
											new Jinja_exprContext(_parentctx, _parentState));
									pushNewRecursionContext(_localctx, _startState, RULE_jinja_expr);
									setState(243);
									if (!(precpred(_ctx, 6)))
										throw new FailedPredicateException(this, "precpred(_ctx, 6)");
									setState(244);
									match(LBRACK);
									setState(245);
									jinja_expr(0);
									setState(246);
									match(RBRACK);
								}
									break;
							}
						}
					}
					setState(252);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 25, _ctx);
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Jinja_argsContext extends ParserRuleContext {
		public List<Jinja_argContext> jinja_arg() {
			return getRuleContexts(Jinja_argContext.class);
		}

		public Jinja_argContext jinja_arg(int i) {
			return getRuleContext(Jinja_argContext.class, i);
		}

		public List<TerminalNode> COMMA() {
			return getTokens(TemplateParser.COMMA);
		}

		public TerminalNode COMMA(int i) {
			return getToken(TemplateParser.COMMA, i);
		}

		public Jinja_argsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_jinja_args;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterJinja_args(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitJinja_args(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitJinja_args(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final Jinja_argsContext jinja_args() throws RecognitionException {
		Jinja_argsContext _localctx = new Jinja_argsContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_jinja_args);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(253);
				jinja_arg();
				setState(258);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == COMMA) {
					{
						{
							setState(254);
							match(COMMA);
							setState(255);
							jinja_arg();
						}
					}
					setState(260);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Jinja_argContext extends ParserRuleContext {
		public Jinja_argContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_jinja_arg;
		}

		public Jinja_argContext() {
		}

		public void copyFrom(Jinja_argContext ctx) {
			super.copyFrom(ctx);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class JinjaPositionalArgContext extends Jinja_argContext {
		public Jinja_exprContext jinja_expr() {
			return getRuleContext(Jinja_exprContext.class, 0);
		}

		public JinjaPositionalArgContext(Jinja_argContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterJinjaPositionalArg(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitJinjaPositionalArg(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitJinjaPositionalArg(this);
			else
				return visitor.visitChildren(this);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class JinjaKeywordArgContext extends Jinja_argContext {
		public TerminalNode JINJA_NAME() {
			return getToken(TemplateParser.JINJA_NAME, 0);
		}

		public TerminalNode EQUAL() {
			return getToken(TemplateParser.EQUAL, 0);
		}

		public Jinja_exprContext jinja_expr() {
			return getRuleContext(Jinja_exprContext.class, 0);
		}

		public JinjaKeywordArgContext(Jinja_argContext ctx) {
			copyFrom(ctx);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).enterJinjaKeywordArg(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof TemplateParserListener)
				((TemplateParserListener) listener).exitJinjaKeywordArg(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if (visitor instanceof TemplateParserVisitor)
				return ((TemplateParserVisitor<? extends T>) visitor).visitJinjaKeywordArg(this);
			else
				return visitor.visitChildren(this);
		}
	}

	public final Jinja_argContext jinja_arg() throws RecognitionException {
		Jinja_argContext _localctx = new Jinja_argContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_jinja_arg);
		try {
			setState(265);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 27, _ctx)) {
				case 1:
					_localctx = new JinjaKeywordArgContext(_localctx);
					enterOuterAlt(_localctx, 1); {
					setState(261);
					match(JINJA_NAME);
					setState(262);
					match(EQUAL);
					setState(263);
					jinja_expr(0);
				}
					break;
				case 2:
					_localctx = new JinjaPositionalArgContext(_localctx);
					enterOuterAlt(_localctx, 2); {
					setState(264);
					jinja_expr(0);
				}
					break;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
			case 16:
				return jinja_expr_sempred((Jinja_exprContext) _localctx, predIndex);
		}
		return true;
	}

	private boolean jinja_expr_sempred(Jinja_exprContext _localctx, int predIndex) {
		switch (predIndex) {
			case 0:
				return precpred(_ctx, 11);
			case 1:
				return precpred(_ctx, 9);
			case 2:
				return precpred(_ctx, 8);
			case 3:
				return precpred(_ctx, 7);
			case 4:
				return precpred(_ctx, 6);
		}
		return true;
	}

	public static final String _serializedATN = "\u0004\u00013\u010c\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"
			+
			"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002" +
			"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002" +
			"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002" +
			"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f" +
			"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012" +
			"\u0001\u0000\u0005\u0000(\b\u0000\n\u0000\f\u0000+\t\u0000\u0001\u0000" +
			"\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001" +
			"\u0001\u0001\u0001\u0001\u0003\u00016\b\u0001\u0001\u0002\u0001\u0002" +
			"\u0005\u0002:\b\u0002\n\u0002\f\u0002=\t\u0002\u0001\u0002\u0001\u0002" +
			"\u0005\u0002A\b\u0002\n\u0002\f\u0002D\t\u0002\u0001\u0002\u0001\u0002" +
			"\u0001\u0002\u0005\u0002I\b\u0002\n\u0002\f\u0002L\t\u0002\u0001\u0002" +
			"\u0001\u0002\u0001\u0002\u0005\u0002Q\b\u0002\n\u0002\f\u0002T\t\u0002" +
			"\u0001\u0002\u0001\u0002\u0001\u0002\u0005\u0002Y\b\u0002\n\u0002\f\u0002" +
			"\\\t\u0002\u0001\u0002\u0003\u0002_\b\u0002\u0001\u0003\u0001\u0003\u0001" +
			"\u0003\u0001\u0003\u0003\u0003e\b\u0003\u0001\u0004\u0001\u0004\u0001" +
			"\u0004\u0005\u0004j\b\u0004\n\u0004\f\u0004m\t\u0004\u0001\u0004\u0001" +
			"\u0004\u0001\u0004\u0005\u0004r\b\u0004\n\u0004\f\u0004u\t\u0004\u0001" +
			"\u0004\u0003\u0004x\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001" +
			"\u0005\u0001\u0005\u0003\u0005\u007f\b\u0005\u0001\u0006\u0001\u0006\u0001" +
			"\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u0086\b\u0006\u0001\u0007\u0001" +
			"\u0007\u0001\b\u0001\b\u0001\b\u0005\b\u008d\b\b\n\b\f\b\u0090\t\b\u0001" +
			"\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0003\t\u0098\b\t\u0001\t\u0003" +
			"\t\u009b\b\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0005" +
			"\n\u00a4\b\n\n\n\f\n\u00a7\t\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b" +
			"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0005\u000b" +
			"\u00b3\b\u000b\n\u000b\f\u000b\u00b6\t\u000b\u0001\u000b\u0003\u000b\u00b9" +
			"\b\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001" +
			"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001" +
			"\r\u0001\u000e\u0005\u000e\u00cb\b\u000e\n\u000e\f\u000e\u00ce\t\u000e" +
			"\u0001\u000f\u0001\u000f\u0003\u000f\u00d2\b\u000f\u0001\u000f\u0001\u000f" +
			"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010" +
			"\u0003\u0010\u00dc\b\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010" +
			"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u00e6\b\u0010" +
			"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010" +
			"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010" +
			"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0005\u0010" +
			"\u00f9\b\u0010\n\u0010\f\u0010\u00fc\t\u0010\u0001\u0011\u0001\u0011\u0001" +
			"\u0011\u0005\u0011\u0101\b\u0011\n\u0011\f\u0011\u0104\t\u0011\u0001\u0012" +
			"\u0001\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u010a\b\u0012\u0001\u0012" +
			"\u0000\u0001 \u0013\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014" +
			"\u0016\u0018\u001a\u001c\u001e \"$\u0000\u0000\u0123\u0000)\u0001\u0000" +
			"\u0000\u0000\u00025\u0001\u0000\u0000\u0000\u0004^\u0001\u0000\u0000\u0000" +
			"\u0006d\u0001\u0000\u0000\u0000\bw\u0001\u0000\u0000\u0000\n~\u0001\u0000" +
			"\u0000\u0000\f\u0085\u0001\u0000\u0000\u0000\u000e\u0087\u0001\u0000\u0000" +
			"\u0000\u0010\u0089\u0001\u0000\u0000\u0000\u0012\u0093\u0001\u0000\u0000" +
			"\u0000\u0014\u009c\u0001\u0000\u0000\u0000\u0016\u00ac\u0001\u0000\u0000" +
			"\u0000\u0018\u00be\u0001\u0000\u0000\u0000\u001a\u00c4\u0001\u0000\u0000" +
			"\u0000\u001c\u00cc\u0001\u0000\u0000\u0000\u001e\u00cf\u0001\u0000\u0000" +
			"\u0000 \u00e5\u0001\u0000\u0000\u0000\"\u00fd\u0001\u0000\u0000\u0000" +
			"$\u0109\u0001\u0000\u0000\u0000&(\u0003\u0002\u0001\u0000\'&\u0001\u0000" +
			"\u0000\u0000(+\u0001\u0000\u0000\u0000)\'\u0001\u0000\u0000\u0000)*\u0001" +
			"\u0000\u0000\u0000*,\u0001\u0000\u0000\u0000+)\u0001\u0000\u0000\u0000" +
			",-\u0005\u0000\u0000\u0001-\u0001\u0001\u0000\u0000\u0000.6\u0003\u0004" +
			"\u0002\u0000/6\u0003\u000e\u0007\u000006\u0003\u0010\b\u000016\u0003\u0014" +
			"\n\u000026\u0003\u0016\u000b\u000036\u0003\u001e\u000f\u000046\u0005\u0005" +
			"\u0000\u00005.\u0001\u0000\u0000\u00005/\u0001\u0000\u0000\u000050\u0001" +
			"\u0000\u0000\u000051\u0001\u0000\u0000\u000052\u0001\u0000\u0000\u0000" +
			"53\u0001\u0000\u0000\u000054\u0001\u0000\u0000\u00006\u0003\u0001\u0000" +
			"\u0000\u00007;\u0005\u0007\u0000\u00008:\u0003\u0006\u0003\u000098\u0001" +
			"\u0000\u0000\u0000:=\u0001\u0000\u0000\u0000;9\u0001\u0000\u0000\u0000" +
			";<\u0001\u0000\u0000\u0000<>\u0001\u0000\u0000\u0000=;\u0001\u0000\u0000" +
			"\u0000>B\u0005\u000b\u0000\u0000?A\u0003\u0002\u0001\u0000@?\u0001\u0000" +
			"\u0000\u0000AD\u0001\u0000\u0000\u0000B@\u0001\u0000\u0000\u0000BC\u0001" +
			"\u0000\u0000\u0000CE\u0001\u0000\u0000\u0000DB\u0001\u0000\u0000\u0000" +
			"E_\u0005\b\u0000\u0000FJ\u0005\u0007\u0000\u0000GI\u0003\u0006\u0003\u0000" +
			"HG\u0001\u0000\u0000\u0000IL\u0001\u0000\u0000\u0000JH\u0001\u0000\u0000" +
			"\u0000JK\u0001\u0000\u0000\u0000KM\u0001\u0000\u0000\u0000LJ\u0001\u0000" +
			"\u0000\u0000M_\u0005\f\u0000\u0000NR\u0005\u0006\u0000\u0000OQ\u0003\u0006" +
			"\u0003\u0000PO\u0001\u0000\u0000\u0000QT\u0001\u0000\u0000\u0000RP\u0001" +
			"\u0000\u0000\u0000RS\u0001\u0000\u0000\u0000SU\u0001\u0000\u0000\u0000" +
			"TR\u0001\u0000\u0000\u0000U_\u0005\u000b\u0000\u0000VZ\u0005\u0006\u0000" +
			"\u0000WY\u0003\u0006\u0003\u0000XW\u0001\u0000\u0000\u0000Y\\\u0001\u0000" +
			"\u0000\u0000ZX\u0001\u0000\u0000\u0000Z[\u0001\u0000\u0000\u0000[]\u0001" +
			"\u0000\u0000\u0000\\Z\u0001\u0000\u0000\u0000]_\u0005\f\u0000\u0000^7" +
			"\u0001\u0000\u0000\u0000^F\u0001\u0000\u0000\u0000^N\u0001\u0000\u0000" +
			"\u0000^V\u0001\u0000\u0000\u0000_\u0005\u0001\u0000\u0000\u0000`a\u0005" +
			"\u000e\u0000\u0000ab\u0005\r\u0000\u0000be\u0003\b\u0004\u0000ce\u0005" +
			"\u000e\u0000\u0000d`\u0001\u0000\u0000\u0000dc\u0001\u0000\u0000\u0000" +
			"e\u0007\u0001\u0000\u0000\u0000fx\u0005\u000f\u0000\u0000gk\u0005\u0010" +
			"\u0000\u0000hj\u0003\n\u0005\u0000ih\u0001\u0000\u0000\u0000jm\u0001\u0000" +
			"\u0000\u0000ki\u0001\u0000\u0000\u0000kl\u0001\u0000\u0000\u0000ln\u0001" +
			"\u0000\u0000\u0000mk\u0001\u0000\u0000\u0000nx\u0005\u0014\u0000\u0000" +
			"os\u0005\u0011\u0000\u0000pr\u0003\f\u0006\u0000qp\u0001\u0000\u0000\u0000" +
			"ru\u0001\u0000\u0000\u0000sq\u0001\u0000\u0000\u0000st\u0001\u0000\u0000" +
			"\u0000tv\u0001\u0000\u0000\u0000us\u0001\u0000\u0000\u0000vx\u0005\u0017" +
			"\u0000\u0000wf\u0001\u0000\u0000\u0000wg\u0001\u0000\u0000\u0000wo\u0001" +
			"\u0000\u0000\u0000x\t\u0001\u0000\u0000\u0000y\u007f\u0005\u0015\u0000" +
			"\u0000z{\u0005\u0013\u0000\u0000{|\u0003 \u0010\u0000|}\u0005\u001a\u0000" +
			"\u0000}\u007f\u0001\u0000\u0000\u0000~y\u0001\u0000\u0000\u0000~z\u0001" +
			"\u0000\u0000\u0000\u007f\u000b\u0001\u0000\u0000\u0000\u0080\u0086\u0005" +
			"\u0018\u0000\u0000\u0081\u0082\u0005\u0016\u0000\u0000\u0082\u0083\u0003" +
			" \u0010\u0000\u0083\u0084\u0005\u001a\u0000\u0000\u0084\u0086\u0001\u0000" +
			"\u0000\u0000\u0085\u0080\u0001\u0000\u0000\u0000\u0085\u0081\u0001\u0000" +
			"\u0000\u0000\u0086\r\u0001\u0000\u0000\u0000\u0087\u0088\u0005\t\u0000" +
			"\u0000\u0088\u000f\u0001\u0000\u0000\u0000\u0089\u008a\u0005\u0002\u0000" +
			"\u0000\u008a\u008e\u0003 \u0010\u0000\u008b\u008d\u0003\u0012\t\u0000" +
			"\u008c\u008b\u0001\u0000\u0000\u0000\u008d\u0090\u0001\u0000\u0000\u0000" +
			"\u008e\u008c\u0001\u0000\u0000\u0000\u008e\u008f\u0001\u0000\u0000\u0000" +
			"\u008f\u0091\u0001\u0000\u0000\u0000\u0090\u008e\u0001\u0000\u0000\u0000" +
			"\u0091\u0092\u0005\u001a\u0000\u0000\u0092\u0011\u0001\u0000\u0000\u0000" +
			"\u0093\u0094\u0005-\u0000\u0000\u0094\u009a\u00050\u0000\u0000\u0095\u0097" +
			"\u0005\'\u0000\u0000\u0096\u0098\u0003\"\u0011\u0000\u0097\u0096\u0001" +
			"\u0000\u0000\u0000\u0097\u0098\u0001\u0000\u0000\u0000\u0098\u0099\u0001" +
			"\u0000\u0000\u0000\u0099\u009b\u0005(\u0000\u0000\u009a\u0095\u0001\u0000" +
			"\u0000\u0000\u009a\u009b\u0001\u0000\u0000\u0000\u009b\u0013\u0001\u0000" +
			"\u0000\u0000\u009c\u009d\u0005\u0001\u0000\u0000\u009d\u009e\u0005\u001b" +
			"\u0000\u0000\u009e\u009f\u00050\u0000\u0000\u009f\u00a0\u0005\u001c\u0000" +
			"\u0000\u00a0\u00a1\u0003 \u0010\u0000\u00a1\u00a5\u0005\u0019\u0000\u0000" +
			"\u00a2\u00a4\u0003\u0002\u0001\u0000\u00a3\u00a2\u0001\u0000\u0000\u0000" +
			"\u00a4\u00a7\u0001\u0000\u0000\u0000\u00a5\u00a3\u0001\u0000\u0000\u0000" +
			"\u00a5\u00a6\u0001\u0000\u0000\u0000\u00a6\u00a8\u0001\u0000\u0000\u0000" +
			"\u00a7\u00a5\u0001\u0000\u0000\u0000\u00a8\u00a9\u0005\u0001\u0000\u0000" +
			"\u00a9\u00aa\u0005!\u0000\u0000\u00aa\u00ab\u0005\u0019\u0000\u0000\u00ab" +
			"\u0015\u0001\u0000\u0000\u0000\u00ac\u00ad\u0005\u0001\u0000\u0000\u00ad" +
			"\u00ae\u0005\u001d\u0000\u0000\u00ae\u00af\u0003 \u0010\u0000\u00af\u00b0" +
			"\u0005\u0019\u0000\u0000\u00b0\u00b4\u0003\u001c\u000e\u0000\u00b1\u00b3" +
			"\u0003\u0018\f\u0000\u00b2\u00b1\u0001\u0000\u0000\u0000\u00b3\u00b6\u0001" +
			"\u0000\u0000\u0000\u00b4\u00b2\u0001\u0000\u0000\u0000\u00b4\u00b5\u0001" +
			"\u0000\u0000\u0000\u00b5\u00b8\u0001\u0000\u0000\u0000\u00b6\u00b4\u0001" +
			"\u0000\u0000\u0000\u00b7\u00b9\u0003\u001a\r\u0000\u00b8\u00b7\u0001\u0000" +
			"\u0000\u0000\u00b8\u00b9\u0001\u0000\u0000\u0000\u00b9\u00ba\u0001\u0000" +
			"\u0000\u0000\u00ba\u00bb\u0005\u0001\u0000\u0000\u00bb\u00bc\u0005 \u0000" +
			"\u0000\u00bc\u00bd\u0005\u0019\u0000\u0000\u00bd\u0017\u0001\u0000\u0000" +
			"\u0000\u00be\u00bf\u0005\u0001\u0000\u0000\u00bf\u00c0\u0005\u001e\u0000" +
			"\u0000\u00c0\u00c1\u0003 \u0010\u0000\u00c1\u00c2\u0005\u0019\u0000\u0000" +
			"\u00c2\u00c3\u0003\u001c\u000e\u0000\u00c3\u0019\u0001\u0000\u0000\u0000" +
			"\u00c4\u00c5\u0005\u0001\u0000\u0000\u00c5\u00c6\u0005\u001f\u0000\u0000" +
			"\u00c6\u00c7\u0005\u0019\u0000\u0000\u00c7\u00c8\u0003\u001c\u000e\u0000" +
			"\u00c8\u001b\u0001\u0000\u0000\u0000\u00c9\u00cb\u0003\u0002\u0001\u0000" +
			"\u00ca\u00c9\u0001\u0000\u0000\u0000\u00cb\u00ce\u0001\u0000\u0000\u0000" +
			"\u00cc\u00ca\u0001\u0000\u0000\u0000\u00cc\u00cd\u0001\u0000\u0000\u0000" +
			"\u00cd\u001d\u0001\u0000\u0000\u0000\u00ce\u00cc\u0001\u0000\u0000\u0000" +
			"\u00cf\u00d1\u0005\u0003\u0000\u0000\u00d0\u00d2\u00053\u0000\u0000\u00d1" +
			"\u00d0\u0001\u0000\u0000\u0000\u00d1\u00d2\u0001\u0000\u0000\u0000\u00d2" +
			"\u00d3\u0001\u0000\u0000\u0000\u00d3\u00d4\u00052\u0000\u0000\u00d4\u001f" +
			"\u0001\u0000\u0000\u0000\u00d5\u00d6\u0006\u0010\uffff\uffff\u0000\u00d6" +
			"\u00d7\u0005\"\u0000\u0000\u00d7\u00e6\u0003 \u0010\n\u00d8\u00d9\u0005" +
			"0\u0000\u0000\u00d9\u00db\u0005\'\u0000\u0000\u00da\u00dc\u0003\"\u0011" +
			"\u0000\u00db\u00da\u0001\u0000\u0000\u0000\u00db\u00dc\u0001\u0000\u0000" +
			"\u0000\u00dc\u00dd\u0001\u0000\u0000\u0000\u00dd\u00e6\u0005(\u0000\u0000" +
			"\u00de\u00e6\u00050\u0000\u0000\u00df\u00e6\u0005.\u0000\u0000\u00e0\u00e6" +
			"\u0005/\u0000\u0000\u00e1\u00e2\u0005\'\u0000\u0000\u00e2\u00e3\u0003" +
			" \u0010\u0000\u00e3\u00e4\u0005(\u0000\u0000\u00e4\u00e6\u0001\u0000\u0000" +
			"\u0000\u00e5\u00d5\u0001\u0000\u0000\u0000\u00e5\u00d8\u0001\u0000\u0000" +
			"\u0000\u00e5\u00de\u0001\u0000\u0000\u0000\u00e5\u00df\u0001\u0000\u0000" +
			"\u0000\u00e5\u00e0\u0001\u0000\u0000\u0000\u00e5\u00e1\u0001\u0000\u0000" +
			"\u0000\u00e6\u00fa\u0001\u0000\u0000\u0000\u00e7\u00e8\n\u000b\u0000\u0000" +
			"\u00e8\u00e9\u0005%\u0000\u0000\u00e9\u00f9\u0003 \u0010\f\u00ea\u00eb" +
			"\n\t\u0000\u0000\u00eb\u00ec\u0005#\u0000\u0000\u00ec\u00f9\u0003 \u0010" +
			"\n\u00ed\u00ee\n\b\u0000\u0000\u00ee\u00ef\u0005$\u0000\u0000\u00ef\u00f9" +
			"\u0003 \u0010\t\u00f0\u00f1\n\u0007\u0000\u0000\u00f1\u00f2\u0005&\u0000" +
			"\u0000\u00f2\u00f9\u00050\u0000\u0000\u00f3\u00f4\n\u0006\u0000\u0000" +
			"\u00f4\u00f5\u0005)\u0000\u0000\u00f5\u00f6\u0003 \u0010\u0000\u00f6\u00f7" +
			"\u0005*\u0000\u0000\u00f7\u00f9\u0001\u0000\u0000\u0000\u00f8\u00e7\u0001" +
			"\u0000\u0000\u0000\u00f8\u00ea\u0001\u0000\u0000\u0000\u00f8\u00ed\u0001" +
			"\u0000\u0000\u0000\u00f8\u00f0\u0001\u0000\u0000\u0000\u00f8\u00f3\u0001" +
			"\u0000\u0000\u0000\u00f9\u00fc\u0001\u0000\u0000\u0000\u00fa\u00f8\u0001" +
			"\u0000\u0000\u0000\u00fa\u00fb\u0001\u0000\u0000\u0000\u00fb!\u0001\u0000" +
			"\u0000\u0000\u00fc\u00fa\u0001\u0000\u0000\u0000\u00fd\u0102\u0003$\u0012" +
			"\u0000\u00fe\u00ff\u0005+\u0000\u0000\u00ff\u0101\u0003$\u0012\u0000\u0100" +
			"\u00fe\u0001\u0000\u0000\u0000\u0101\u0104\u0001\u0000\u0000\u0000\u0102" +
			"\u0100\u0001\u0000\u0000\u0000\u0102\u0103\u0001\u0000\u0000\u0000\u0103" +
			"#\u0001\u0000\u0000\u0000\u0104\u0102\u0001\u0000\u0000\u0000\u0105\u0106" +
			"\u00050\u0000\u0000\u0106\u0107\u0005,\u0000\u0000\u0107\u010a\u0003 " +
			"\u0010\u0000\u0108\u010a\u0003 \u0010\u0000\u0109\u0105\u0001\u0000\u0000" +
			"\u0000\u0109\u0108\u0001\u0000\u0000\u0000\u010a%\u0001\u0000\u0000\u0000" +
			"\u001c)5;BJRZ^dksw~\u0085\u008e\u0097\u009a\u00a5\u00b4\u00b8\u00cc\u00d1" +
			"\u00db\u00e5\u00f8\u00fa\u0102\u0109";
	public static final ATN _ATN = new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}