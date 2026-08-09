// Generated from src/compiler/grammar/PythonSubsetParser.g4 by ANTLR 4.13.2

    package compiler.parser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class PythonSubsetParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		DEF=1, IF=2, ELIF=3, ELSE=4, FOR=5, IN=6, RETURN=7, IMPORT=8, FROM=9, 
		AS=10, GLOBAL=11, TRUE=12, FALSE=13, NONE=14, OR=15, AND=16, NOT=17, PLUS=18, 
		MINUS=19, STAR=20, SLASH=21, PERCENT=22, DOUBLESTAR=23, DOUBLESLASH=24, 
		EQUAL=25, PLUSEQUAL=26, MINUSEQUAL=27, EQEQUAL=28, NOTEQUAL=29, LESS=30, 
		GREATER=31, LESSEQUAL=32, GREATEREQUAL=33, LPAREN=34, RPAREN=35, LBRACK=36, 
		RBRACK=37, LBRACE=38, RBRACE=39, COMMA=40, COLON=41, DOT=42, ARROW=43, 
		AT=44, STRING=45, NUMBER=46, NAME=47, NEWLINE=48, INDENT=49, DEDENT=50, 
		WS=51, COMMENT=52;
	public static final int
		RULE_file_input = 0, RULE_stmt = 1, RULE_simple_stmt = 2, RULE_small_stmt = 3, 
		RULE_compound_stmt = 4, RULE_import_stmt = 5, RULE_from_import_stmt = 6, 
		RULE_import_as_names = 7, RULE_import_as_name = 8, RULE_dotted_name = 9, 
		RULE_function_def = 10, RULE_decorator = 11, RULE_decorator_name = 12, 
		RULE_parameters = 13, RULE_parameter = 14, RULE_suite = 15, RULE_if_stmt = 16, 
		RULE_for_stmt = 17, RULE_assign_stmt = 18, RULE_global_stmt = 19, RULE_return_stmt = 20, 
		RULE_expr_stmt = 21, RULE_test = 22, RULE_or_test = 23, RULE_and_test = 24, 
		RULE_not_test = 25, RULE_comparison = 26, RULE_comp_op = 27, RULE_expr = 28, 
		RULE_term = 29, RULE_factor = 30, RULE_power = 31, RULE_atom_expr = 32, 
		RULE_trailer = 33, RULE_atom = 34, RULE_listmaker = 35, RULE_testlist_comp = 36, 
		RULE_testlist = 37, RULE_dictorsetmaker = 38, RULE_comp_for = 39, RULE_arguments = 40, 
		RULE_argument = 41;
	private static String[] makeRuleNames() {
		return new String[] {
			"file_input", "stmt", "simple_stmt", "small_stmt", "compound_stmt", "import_stmt", 
			"from_import_stmt", "import_as_names", "import_as_name", "dotted_name", 
			"function_def", "decorator", "decorator_name", "parameters", "parameter", 
			"suite", "if_stmt", "for_stmt", "assign_stmt", "global_stmt", "return_stmt", 
			"expr_stmt", "test", "or_test", "and_test", "not_test", "comparison", 
			"comp_op", "expr", "term", "factor", "power", "atom_expr", "trailer", 
			"atom", "listmaker", "testlist_comp", "testlist", "dictorsetmaker", "comp_for", 
			"arguments", "argument"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'def'", "'if'", "'elif'", "'else'", "'for'", "'in'", "'return'", 
			"'import'", "'from'", "'as'", "'global'", "'True'", "'False'", "'None'", 
			"'or'", "'and'", "'not'", "'+'", "'-'", "'*'", "'/'", "'%'", "'**'", 
			"'//'", "'='", "'+='", "'-='", "'=='", "'!='", "'<'", "'>'", "'<='", 
			"'>='", "'('", "')'", "'['", "']'", "'{'", "'}'", "','", "':'", "'.'", 
			"'->'", "'@'", null, null, null, null, "'<<<INDENT>>>'", "'<<<DEDENT>>>'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "DEF", "IF", "ELIF", "ELSE", "FOR", "IN", "RETURN", "IMPORT", "FROM", 
			"AS", "GLOBAL", "TRUE", "FALSE", "NONE", "OR", "AND", "NOT", "PLUS", 
			"MINUS", "STAR", "SLASH", "PERCENT", "DOUBLESTAR", "DOUBLESLASH", "EQUAL", 
			"PLUSEQUAL", "MINUSEQUAL", "EQEQUAL", "NOTEQUAL", "LESS", "GREATER", 
			"LESSEQUAL", "GREATEREQUAL", "LPAREN", "RPAREN", "LBRACK", "RBRACK", 
			"LBRACE", "RBRACE", "COMMA", "COLON", "DOT", "ARROW", "AT", "STRING", 
			"NUMBER", "NAME", "NEWLINE", "INDENT", "DEDENT", "WS", "COMMENT"
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
	public String getGrammarFileName() { return "PythonSubsetParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PythonSubsetParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class File_inputContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(PythonSubsetParser.EOF, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(PythonSubsetParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(PythonSubsetParser.NEWLINE, i);
		}
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public File_inputContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file_input; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterFile_input(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitFile_input(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitFile_input(this);
			else return visitor.visitChildren(this);
		}
	}

	public final File_inputContext file_input() throws RecognitionException {
		File_inputContext _localctx = new File_inputContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_file_input);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 545718545578918L) != 0)) {
				{
				setState(86);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case NEWLINE:
					{
					setState(84);
					match(NEWLINE);
					}
					break;
				case DEF:
				case IF:
				case FOR:
				case RETURN:
				case IMPORT:
				case FROM:
				case GLOBAL:
				case TRUE:
				case FALSE:
				case NONE:
				case NOT:
				case PLUS:
				case MINUS:
				case LPAREN:
				case LBRACK:
				case LBRACE:
				case AT:
				case STRING:
				case NUMBER:
				case NAME:
					{
					setState(85);
					stmt();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(90);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(91);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StmtContext extends ParserRuleContext {
		public Simple_stmtContext simple_stmt() {
			return getRuleContext(Simple_stmtContext.class,0);
		}
		public Compound_stmtContext compound_stmt() {
			return getRuleContext(Compound_stmtContext.class,0);
		}
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_stmt);
		try {
			setState(95);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case RETURN:
			case IMPORT:
			case FROM:
			case GLOBAL:
			case TRUE:
			case FALSE:
			case NONE:
			case NOT:
			case PLUS:
			case MINUS:
			case LPAREN:
			case LBRACK:
			case LBRACE:
			case STRING:
			case NUMBER:
			case NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(93);
				simple_stmt();
				}
				break;
			case DEF:
			case IF:
			case FOR:
			case AT:
				enterOuterAlt(_localctx, 2);
				{
				setState(94);
				compound_stmt();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Simple_stmtContext extends ParserRuleContext {
		public Small_stmtContext small_stmt() {
			return getRuleContext(Small_stmtContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(PythonSubsetParser.NEWLINE, 0); }
		public Simple_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simple_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterSimple_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitSimple_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitSimple_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Simple_stmtContext simple_stmt() throws RecognitionException {
		Simple_stmtContext _localctx = new Simple_stmtContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_simple_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			small_stmt();
			setState(98);
			match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Small_stmtContext extends ParserRuleContext {
		public Expr_stmtContext expr_stmt() {
			return getRuleContext(Expr_stmtContext.class,0);
		}
		public Assign_stmtContext assign_stmt() {
			return getRuleContext(Assign_stmtContext.class,0);
		}
		public Return_stmtContext return_stmt() {
			return getRuleContext(Return_stmtContext.class,0);
		}
		public Global_stmtContext global_stmt() {
			return getRuleContext(Global_stmtContext.class,0);
		}
		public Import_stmtContext import_stmt() {
			return getRuleContext(Import_stmtContext.class,0);
		}
		public From_import_stmtContext from_import_stmt() {
			return getRuleContext(From_import_stmtContext.class,0);
		}
		public Small_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_small_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterSmall_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitSmall_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitSmall_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Small_stmtContext small_stmt() throws RecognitionException {
		Small_stmtContext _localctx = new Small_stmtContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_small_stmt);
		try {
			setState(106);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(100);
				expr_stmt();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(101);
				assign_stmt();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(102);
				return_stmt();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(103);
				global_stmt();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(104);
				import_stmt();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(105);
				from_import_stmt();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Compound_stmtContext extends ParserRuleContext {
		public Function_defContext function_def() {
			return getRuleContext(Function_defContext.class,0);
		}
		public If_stmtContext if_stmt() {
			return getRuleContext(If_stmtContext.class,0);
		}
		public For_stmtContext for_stmt() {
			return getRuleContext(For_stmtContext.class,0);
		}
		public Compound_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compound_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterCompound_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitCompound_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitCompound_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Compound_stmtContext compound_stmt() throws RecognitionException {
		Compound_stmtContext _localctx = new Compound_stmtContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_compound_stmt);
		try {
			setState(111);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DEF:
			case AT:
				enterOuterAlt(_localctx, 1);
				{
				setState(108);
				function_def();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(109);
				if_stmt();
				}
				break;
			case FOR:
				enterOuterAlt(_localctx, 3);
				{
				setState(110);
				for_stmt();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Import_stmtContext extends ParserRuleContext {
		public TerminalNode IMPORT() { return getToken(PythonSubsetParser.IMPORT, 0); }
		public Dotted_nameContext dotted_name() {
			return getRuleContext(Dotted_nameContext.class,0);
		}
		public TerminalNode AS() { return getToken(PythonSubsetParser.AS, 0); }
		public TerminalNode NAME() { return getToken(PythonSubsetParser.NAME, 0); }
		public Import_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_import_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterImport_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitImport_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitImport_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Import_stmtContext import_stmt() throws RecognitionException {
		Import_stmtContext _localctx = new Import_stmtContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_import_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			match(IMPORT);
			setState(114);
			dotted_name();
			setState(117);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(115);
				match(AS);
				setState(116);
				match(NAME);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class From_import_stmtContext extends ParserRuleContext {
		public TerminalNode FROM() { return getToken(PythonSubsetParser.FROM, 0); }
		public Dotted_nameContext dotted_name() {
			return getRuleContext(Dotted_nameContext.class,0);
		}
		public TerminalNode IMPORT() { return getToken(PythonSubsetParser.IMPORT, 0); }
		public TerminalNode STAR() { return getToken(PythonSubsetParser.STAR, 0); }
		public Import_as_namesContext import_as_names() {
			return getRuleContext(Import_as_namesContext.class,0);
		}
		public From_import_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_from_import_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterFrom_import_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitFrom_import_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitFrom_import_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final From_import_stmtContext from_import_stmt() throws RecognitionException {
		From_import_stmtContext _localctx = new From_import_stmtContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_from_import_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			match(FROM);
			setState(120);
			dotted_name();
			setState(121);
			match(IMPORT);
			setState(124);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STAR:
				{
				setState(122);
				match(STAR);
				}
				break;
			case NAME:
				{
				setState(123);
				import_as_names();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Import_as_namesContext extends ParserRuleContext {
		public List<Import_as_nameContext> import_as_name() {
			return getRuleContexts(Import_as_nameContext.class);
		}
		public Import_as_nameContext import_as_name(int i) {
			return getRuleContext(Import_as_nameContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PythonSubsetParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PythonSubsetParser.COMMA, i);
		}
		public Import_as_namesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_import_as_names; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterImport_as_names(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitImport_as_names(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitImport_as_names(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Import_as_namesContext import_as_names() throws RecognitionException {
		Import_as_namesContext _localctx = new Import_as_namesContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_import_as_names);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			import_as_name();
			setState(131);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(127);
				match(COMMA);
				setState(128);
				import_as_name();
				}
				}
				setState(133);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Import_as_nameContext extends ParserRuleContext {
		public List<TerminalNode> NAME() { return getTokens(PythonSubsetParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(PythonSubsetParser.NAME, i);
		}
		public TerminalNode AS() { return getToken(PythonSubsetParser.AS, 0); }
		public Import_as_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_import_as_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterImport_as_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitImport_as_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitImport_as_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Import_as_nameContext import_as_name() throws RecognitionException {
		Import_as_nameContext _localctx = new Import_as_nameContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_import_as_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			match(NAME);
			setState(137);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(135);
				match(AS);
				setState(136);
				match(NAME);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Dotted_nameContext extends ParserRuleContext {
		public List<TerminalNode> NAME() { return getTokens(PythonSubsetParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(PythonSubsetParser.NAME, i);
		}
		public List<TerminalNode> DOT() { return getTokens(PythonSubsetParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(PythonSubsetParser.DOT, i);
		}
		public Dotted_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dotted_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterDotted_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitDotted_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitDotted_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Dotted_nameContext dotted_name() throws RecognitionException {
		Dotted_nameContext _localctx = new Dotted_nameContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_dotted_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			match(NAME);
			setState(144);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(140);
				match(DOT);
				setState(141);
				match(NAME);
				}
				}
				setState(146);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Function_defContext extends ParserRuleContext {
		public TerminalNode DEF() { return getToken(PythonSubsetParser.DEF, 0); }
		public TerminalNode NAME() { return getToken(PythonSubsetParser.NAME, 0); }
		public TerminalNode LPAREN() { return getToken(PythonSubsetParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(PythonSubsetParser.RPAREN, 0); }
		public TerminalNode COLON() { return getToken(PythonSubsetParser.COLON, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public List<DecoratorContext> decorator() {
			return getRuleContexts(DecoratorContext.class);
		}
		public DecoratorContext decorator(int i) {
			return getRuleContext(DecoratorContext.class,i);
		}
		public ParametersContext parameters() {
			return getRuleContext(ParametersContext.class,0);
		}
		public Function_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_def; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterFunction_def(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitFunction_def(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitFunction_def(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_defContext function_def() throws RecognitionException {
		Function_defContext _localctx = new Function_defContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_function_def);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AT) {
				{
				{
				setState(147);
				decorator();
				}
				}
				setState(152);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(153);
			match(DEF);
			setState(154);
			match(NAME);
			setState(155);
			match(LPAREN);
			setState(157);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NAME) {
				{
				setState(156);
				parameters();
				}
			}

			setState(159);
			match(RPAREN);
			setState(160);
			match(COLON);
			setState(161);
			suite();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DecoratorContext extends ParserRuleContext {
		public TerminalNode AT() { return getToken(PythonSubsetParser.AT, 0); }
		public Decorator_nameContext decorator_name() {
			return getRuleContext(Decorator_nameContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(PythonSubsetParser.NEWLINE, 0); }
		public TerminalNode LPAREN() { return getToken(PythonSubsetParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(PythonSubsetParser.RPAREN, 0); }
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public DecoratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decorator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterDecorator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitDecorator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitDecorator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DecoratorContext decorator() throws RecognitionException {
		DecoratorContext _localctx = new DecoratorContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_decorator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			match(AT);
			setState(164);
			decorator_name();
			setState(170);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(165);
				match(LPAREN);
				setState(167);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 246651382820864L) != 0)) {
					{
					setState(166);
					arguments();
					}
				}

				setState(169);
				match(RPAREN);
				}
			}

			setState(172);
			match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Decorator_nameContext extends ParserRuleContext {
		public List<TerminalNode> NAME() { return getTokens(PythonSubsetParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(PythonSubsetParser.NAME, i);
		}
		public List<TerminalNode> DOT() { return getTokens(PythonSubsetParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(PythonSubsetParser.DOT, i);
		}
		public Decorator_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decorator_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterDecorator_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitDecorator_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitDecorator_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Decorator_nameContext decorator_name() throws RecognitionException {
		Decorator_nameContext _localctx = new Decorator_nameContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_decorator_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			match(NAME);
			setState(179);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(175);
				match(DOT);
				setState(176);
				match(NAME);
				}
				}
				setState(181);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParametersContext extends ParserRuleContext {
		public List<ParameterContext> parameter() {
			return getRuleContexts(ParameterContext.class);
		}
		public ParameterContext parameter(int i) {
			return getRuleContext(ParameterContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PythonSubsetParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PythonSubsetParser.COMMA, i);
		}
		public ParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitParameters(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitParameters(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParametersContext parameters() throws RecognitionException {
		ParametersContext _localctx = new ParametersContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_parameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(182);
			parameter();
			setState(187);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(183);
				match(COMMA);
				setState(184);
				parameter();
				}
				}
				setState(189);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParameterContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(PythonSubsetParser.NAME, 0); }
		public TerminalNode EQUAL() { return getToken(PythonSubsetParser.EQUAL, 0); }
		public TestContext test() {
			return getRuleContext(TestContext.class,0);
		}
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_parameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(190);
			match(NAME);
			setState(193);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EQUAL) {
				{
				setState(191);
				match(EQUAL);
				setState(192);
				test();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SuiteContext extends ParserRuleContext {
		public Simple_stmtContext simple_stmt() {
			return getRuleContext(Simple_stmtContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(PythonSubsetParser.NEWLINE, 0); }
		public TerminalNode INDENT() { return getToken(PythonSubsetParser.INDENT, 0); }
		public TerminalNode DEDENT() { return getToken(PythonSubsetParser.DEDENT, 0); }
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public SuiteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_suite; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterSuite(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitSuite(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitSuite(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SuiteContext suite() throws RecognitionException {
		SuiteContext _localctx = new SuiteContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_suite);
		int _la;
		try {
			setState(205);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case RETURN:
			case IMPORT:
			case FROM:
			case GLOBAL:
			case TRUE:
			case FALSE:
			case NONE:
			case NOT:
			case PLUS:
			case MINUS:
			case LPAREN:
			case LBRACK:
			case LBRACE:
			case STRING:
			case NUMBER:
			case NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(195);
				simple_stmt();
				}
				break;
			case NEWLINE:
				enterOuterAlt(_localctx, 2);
				{
				setState(196);
				match(NEWLINE);
				setState(197);
				match(INDENT);
				setState(199); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(198);
					stmt();
					}
					}
					setState(201); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 264243568868262L) != 0) );
				setState(203);
				match(DEDENT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class If_stmtContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(PythonSubsetParser.IF, 0); }
		public List<TestContext> test() {
			return getRuleContexts(TestContext.class);
		}
		public TestContext test(int i) {
			return getRuleContext(TestContext.class,i);
		}
		public List<TerminalNode> COLON() { return getTokens(PythonSubsetParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(PythonSubsetParser.COLON, i);
		}
		public List<SuiteContext> suite() {
			return getRuleContexts(SuiteContext.class);
		}
		public SuiteContext suite(int i) {
			return getRuleContext(SuiteContext.class,i);
		}
		public List<TerminalNode> ELIF() { return getTokens(PythonSubsetParser.ELIF); }
		public TerminalNode ELIF(int i) {
			return getToken(PythonSubsetParser.ELIF, i);
		}
		public TerminalNode ELSE() { return getToken(PythonSubsetParser.ELSE, 0); }
		public If_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterIf_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitIf_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitIf_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final If_stmtContext if_stmt() throws RecognitionException {
		If_stmtContext _localctx = new If_stmtContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_if_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(207);
			match(IF);
			setState(208);
			test();
			setState(209);
			match(COLON);
			setState(210);
			suite();
			setState(218);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ELIF) {
				{
				{
				setState(211);
				match(ELIF);
				setState(212);
				test();
				setState(213);
				match(COLON);
				setState(214);
				suite();
				}
				}
				setState(220);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(224);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(221);
				match(ELSE);
				setState(222);
				match(COLON);
				setState(223);
				suite();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class For_stmtContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(PythonSubsetParser.FOR, 0); }
		public TerminalNode NAME() { return getToken(PythonSubsetParser.NAME, 0); }
		public TerminalNode IN() { return getToken(PythonSubsetParser.IN, 0); }
		public TestContext test() {
			return getRuleContext(TestContext.class,0);
		}
		public TerminalNode COLON() { return getToken(PythonSubsetParser.COLON, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public For_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_for_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterFor_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitFor_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitFor_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final For_stmtContext for_stmt() throws RecognitionException {
		For_stmtContext _localctx = new For_stmtContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_for_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(226);
			match(FOR);
			setState(227);
			match(NAME);
			setState(228);
			match(IN);
			setState(229);
			test();
			setState(230);
			match(COLON);
			setState(231);
			suite();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Assign_stmtContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(PythonSubsetParser.NAME, 0); }
		public TerminalNode EQUAL() { return getToken(PythonSubsetParser.EQUAL, 0); }
		public TestContext test() {
			return getRuleContext(TestContext.class,0);
		}
		public Assign_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterAssign_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitAssign_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitAssign_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Assign_stmtContext assign_stmt() throws RecognitionException {
		Assign_stmtContext _localctx = new Assign_stmtContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_assign_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			match(NAME);
			setState(234);
			match(EQUAL);
			setState(235);
			test();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Global_stmtContext extends ParserRuleContext {
		public TerminalNode GLOBAL() { return getToken(PythonSubsetParser.GLOBAL, 0); }
		public List<TerminalNode> NAME() { return getTokens(PythonSubsetParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(PythonSubsetParser.NAME, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PythonSubsetParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PythonSubsetParser.COMMA, i);
		}
		public Global_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_global_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterGlobal_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitGlobal_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitGlobal_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Global_stmtContext global_stmt() throws RecognitionException {
		Global_stmtContext _localctx = new Global_stmtContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_global_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(237);
			match(GLOBAL);
			setState(238);
			match(NAME);
			setState(243);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(239);
				match(COMMA);
				setState(240);
				match(NAME);
				}
				}
				setState(245);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Return_stmtContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(PythonSubsetParser.RETURN, 0); }
		public TestContext test() {
			return getRuleContext(TestContext.class,0);
		}
		public Return_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterReturn_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitReturn_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitReturn_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Return_stmtContext return_stmt() throws RecognitionException {
		Return_stmtContext _localctx = new Return_stmtContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_return_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(246);
			match(RETURN);
			setState(248);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 246651382820864L) != 0)) {
				{
				setState(247);
				test();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Expr_stmtContext extends ParserRuleContext {
		public TestContext test() {
			return getRuleContext(TestContext.class,0);
		}
		public Expr_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterExpr_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitExpr_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitExpr_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expr_stmtContext expr_stmt() throws RecognitionException {
		Expr_stmtContext _localctx = new Expr_stmtContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_expr_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(250);
			test();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TestContext extends ParserRuleContext {
		public Or_testContext or_test() {
			return getRuleContext(Or_testContext.class,0);
		}
		public TestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_test; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterTest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitTest(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitTest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TestContext test() throws RecognitionException {
		TestContext _localctx = new TestContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_test);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252);
			or_test();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Or_testContext extends ParserRuleContext {
		public List<And_testContext> and_test() {
			return getRuleContexts(And_testContext.class);
		}
		public And_testContext and_test(int i) {
			return getRuleContext(And_testContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(PythonSubsetParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(PythonSubsetParser.OR, i);
		}
		public Or_testContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_or_test; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterOr_test(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitOr_test(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitOr_test(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Or_testContext or_test() throws RecognitionException {
		Or_testContext _localctx = new Or_testContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_or_test);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(254);
			and_test();
			setState(259);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(255);
				match(OR);
				setState(256);
				and_test();
				}
				}
				setState(261);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class And_testContext extends ParserRuleContext {
		public List<Not_testContext> not_test() {
			return getRuleContexts(Not_testContext.class);
		}
		public Not_testContext not_test(int i) {
			return getRuleContext(Not_testContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(PythonSubsetParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(PythonSubsetParser.AND, i);
		}
		public And_testContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_and_test; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterAnd_test(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitAnd_test(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitAnd_test(this);
			else return visitor.visitChildren(this);
		}
	}

	public final And_testContext and_test() throws RecognitionException {
		And_testContext _localctx = new And_testContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_and_test);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262);
			not_test();
			setState(267);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(263);
				match(AND);
				setState(264);
				not_test();
				}
				}
				setState(269);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Not_testContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(PythonSubsetParser.NOT, 0); }
		public Not_testContext not_test() {
			return getRuleContext(Not_testContext.class,0);
		}
		public ComparisonContext comparison() {
			return getRuleContext(ComparisonContext.class,0);
		}
		public Not_testContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_not_test; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterNot_test(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitNot_test(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitNot_test(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Not_testContext not_test() throws RecognitionException {
		Not_testContext _localctx = new Not_testContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_not_test);
		try {
			setState(273);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(270);
				match(NOT);
				setState(271);
				not_test();
				}
				break;
			case TRUE:
			case FALSE:
			case NONE:
			case PLUS:
			case MINUS:
			case LPAREN:
			case LBRACK:
			case LBRACE:
			case STRING:
			case NUMBER:
			case NAME:
				enterOuterAlt(_localctx, 2);
				{
				setState(272);
				comparison();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ComparisonContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<Comp_opContext> comp_op() {
			return getRuleContexts(Comp_opContext.class);
		}
		public Comp_opContext comp_op(int i) {
			return getRuleContext(Comp_opContext.class,i);
		}
		public ComparisonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparison; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterComparison(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitComparison(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitComparison(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparisonContext comparison() throws RecognitionException {
		ComparisonContext _localctx = new ComparisonContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_comparison);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(275);
			expr();
			setState(281);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 16911433792L) != 0)) {
				{
				{
				setState(276);
				comp_op();
				setState(277);
				expr();
				}
				}
				setState(283);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Comp_opContext extends ParserRuleContext {
		public TerminalNode LESS() { return getToken(PythonSubsetParser.LESS, 0); }
		public TerminalNode GREATER() { return getToken(PythonSubsetParser.GREATER, 0); }
		public TerminalNode EQEQUAL() { return getToken(PythonSubsetParser.EQEQUAL, 0); }
		public TerminalNode GREATEREQUAL() { return getToken(PythonSubsetParser.GREATEREQUAL, 0); }
		public TerminalNode LESSEQUAL() { return getToken(PythonSubsetParser.LESSEQUAL, 0); }
		public TerminalNode NOTEQUAL() { return getToken(PythonSubsetParser.NOTEQUAL, 0); }
		public TerminalNode IN() { return getToken(PythonSubsetParser.IN, 0); }
		public Comp_opContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comp_op; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterComp_op(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitComp_op(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitComp_op(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Comp_opContext comp_op() throws RecognitionException {
		Comp_opContext _localctx = new Comp_opContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_comp_op);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(284);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 16911433792L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<TerminalNode> PLUS() { return getTokens(PythonSubsetParser.PLUS); }
		public TerminalNode PLUS(int i) {
			return getToken(PythonSubsetParser.PLUS, i);
		}
		public List<TerminalNode> MINUS() { return getTokens(PythonSubsetParser.MINUS); }
		public TerminalNode MINUS(int i) {
			return getToken(PythonSubsetParser.MINUS, i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(286);
			term();
			setState(291);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PLUS || _la==MINUS) {
				{
				{
				setState(287);
				_la = _input.LA(1);
				if ( !(_la==PLUS || _la==MINUS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(288);
				term();
				}
				}
				setState(293);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TermContext extends ParserRuleContext {
		public List<FactorContext> factor() {
			return getRuleContexts(FactorContext.class);
		}
		public FactorContext factor(int i) {
			return getRuleContext(FactorContext.class,i);
		}
		public List<TerminalNode> STAR() { return getTokens(PythonSubsetParser.STAR); }
		public TerminalNode STAR(int i) {
			return getToken(PythonSubsetParser.STAR, i);
		}
		public List<TerminalNode> SLASH() { return getTokens(PythonSubsetParser.SLASH); }
		public TerminalNode SLASH(int i) {
			return getToken(PythonSubsetParser.SLASH, i);
		}
		public List<TerminalNode> PERCENT() { return getTokens(PythonSubsetParser.PERCENT); }
		public TerminalNode PERCENT(int i) {
			return getToken(PythonSubsetParser.PERCENT, i);
		}
		public List<TerminalNode> DOUBLESLASH() { return getTokens(PythonSubsetParser.DOUBLESLASH); }
		public TerminalNode DOUBLESLASH(int i) {
			return getToken(PythonSubsetParser.DOUBLESLASH, i);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(294);
			factor();
			setState(299);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 24117248L) != 0)) {
				{
				{
				setState(295);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 24117248L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(296);
				factor();
				}
				}
				setState(301);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FactorContext extends ParserRuleContext {
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public TerminalNode PLUS() { return getToken(PythonSubsetParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(PythonSubsetParser.MINUS, 0); }
		public PowerContext power() {
			return getRuleContext(PowerContext.class,0);
		}
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitFactor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitFactor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_factor);
		int _la;
		try {
			setState(305);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PLUS:
			case MINUS:
				enterOuterAlt(_localctx, 1);
				{
				setState(302);
				_la = _input.LA(1);
				if ( !(_la==PLUS || _la==MINUS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(303);
				factor();
				}
				break;
			case TRUE:
			case FALSE:
			case NONE:
			case LPAREN:
			case LBRACK:
			case LBRACE:
			case STRING:
			case NUMBER:
			case NAME:
				enterOuterAlt(_localctx, 2);
				{
				setState(304);
				power();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PowerContext extends ParserRuleContext {
		public Atom_exprContext atom_expr() {
			return getRuleContext(Atom_exprContext.class,0);
		}
		public TerminalNode DOUBLESTAR() { return getToken(PythonSubsetParser.DOUBLESTAR, 0); }
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public PowerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_power; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterPower(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitPower(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitPower(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PowerContext power() throws RecognitionException {
		PowerContext _localctx = new PowerContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_power);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(307);
			atom_expr();
			setState(310);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOUBLESTAR) {
				{
				setState(308);
				match(DOUBLESTAR);
				setState(309);
				factor();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Atom_exprContext extends ParserRuleContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public List<TrailerContext> trailer() {
			return getRuleContexts(TrailerContext.class);
		}
		public TrailerContext trailer(int i) {
			return getRuleContext(TrailerContext.class,i);
		}
		public Atom_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterAtom_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitAtom_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitAtom_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Atom_exprContext atom_expr() throws RecognitionException {
		Atom_exprContext _localctx = new Atom_exprContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_atom_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(312);
			atom();
			setState(316);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4483945857024L) != 0)) {
				{
				{
				setState(313);
				trailer();
				}
				}
				setState(318);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TrailerContext extends ParserRuleContext {
		public TrailerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_trailer; }
	 
		public TrailerContext() { }
		public void copyFrom(TrailerContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IndexTrailerContext extends TrailerContext {
		public TerminalNode LBRACK() { return getToken(PythonSubsetParser.LBRACK, 0); }
		public TestContext test() {
			return getRuleContext(TestContext.class,0);
		}
		public TerminalNode RBRACK() { return getToken(PythonSubsetParser.RBRACK, 0); }
		public IndexTrailerContext(TrailerContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterIndexTrailer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitIndexTrailer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitIndexTrailer(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CallTrailerContext extends TrailerContext {
		public TerminalNode LPAREN() { return getToken(PythonSubsetParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(PythonSubsetParser.RPAREN, 0); }
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public CallTrailerContext(TrailerContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterCallTrailer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitCallTrailer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitCallTrailer(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AttrTrailerContext extends TrailerContext {
		public TerminalNode DOT() { return getToken(PythonSubsetParser.DOT, 0); }
		public TerminalNode NAME() { return getToken(PythonSubsetParser.NAME, 0); }
		public AttrTrailerContext(TrailerContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterAttrTrailer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitAttrTrailer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitAttrTrailer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TrailerContext trailer() throws RecognitionException {
		TrailerContext _localctx = new TrailerContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_trailer);
		int _la;
		try {
			setState(330);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAREN:
				_localctx = new CallTrailerContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(319);
				match(LPAREN);
				setState(321);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 246651382820864L) != 0)) {
					{
					setState(320);
					arguments();
					}
				}

				setState(323);
				match(RPAREN);
				}
				break;
			case LBRACK:
				_localctx = new IndexTrailerContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(324);
				match(LBRACK);
				setState(325);
				test();
				setState(326);
				match(RBRACK);
				}
				break;
			case DOT:
				_localctx = new AttrTrailerContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(328);
				match(DOT);
				setState(329);
				match(NAME);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AtomContext extends ParserRuleContext {
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
	 
		public AtomContext() { }
		public void copyFrom(AtomContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TrueAtomContext extends AtomContext {
		public TerminalNode TRUE() { return getToken(PythonSubsetParser.TRUE, 0); }
		public TrueAtomContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterTrueAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitTrueAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitTrueAtom(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FalseAtomContext extends AtomContext {
		public TerminalNode FALSE() { return getToken(PythonSubsetParser.FALSE, 0); }
		public FalseAtomContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterFalseAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitFalseAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitFalseAtom(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NumberAtomContext extends AtomContext {
		public TerminalNode NUMBER() { return getToken(PythonSubsetParser.NUMBER, 0); }
		public NumberAtomContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterNumberAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitNumberAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitNumberAtom(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StringAtomContext extends AtomContext {
		public List<TerminalNode> STRING() { return getTokens(PythonSubsetParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(PythonSubsetParser.STRING, i);
		}
		public StringAtomContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterStringAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitStringAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitStringAtom(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DictAtomContext extends AtomContext {
		public TerminalNode LBRACE() { return getToken(PythonSubsetParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(PythonSubsetParser.RBRACE, 0); }
		public DictorsetmakerContext dictorsetmaker() {
			return getRuleContext(DictorsetmakerContext.class,0);
		}
		public DictAtomContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterDictAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitDictAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitDictAtom(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ListAtomContext extends AtomContext {
		public TerminalNode LBRACK() { return getToken(PythonSubsetParser.LBRACK, 0); }
		public TerminalNode RBRACK() { return getToken(PythonSubsetParser.RBRACK, 0); }
		public ListmakerContext listmaker() {
			return getRuleContext(ListmakerContext.class,0);
		}
		public ListAtomContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterListAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitListAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitListAtom(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParenAtomContext extends AtomContext {
		public TerminalNode LPAREN() { return getToken(PythonSubsetParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(PythonSubsetParser.RPAREN, 0); }
		public Testlist_compContext testlist_comp() {
			return getRuleContext(Testlist_compContext.class,0);
		}
		public ParenAtomContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterParenAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitParenAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitParenAtom(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NameAtomContext extends AtomContext {
		public TerminalNode NAME() { return getToken(PythonSubsetParser.NAME, 0); }
		public NameAtomContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterNameAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitNameAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitNameAtom(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NoneAtomContext extends AtomContext {
		public TerminalNode NONE() { return getToken(PythonSubsetParser.NONE, 0); }
		public NoneAtomContext(AtomContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterNoneAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitNoneAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitNoneAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_atom);
		int _la;
		try {
			setState(357);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAREN:
				_localctx = new ParenAtomContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(332);
				match(LPAREN);
				setState(334);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 246651382820864L) != 0)) {
					{
					setState(333);
					testlist_comp();
					}
				}

				setState(336);
				match(RPAREN);
				}
				break;
			case LBRACK:
				_localctx = new ListAtomContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(337);
				match(LBRACK);
				setState(339);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 246651382820864L) != 0)) {
					{
					setState(338);
					listmaker();
					}
				}

				setState(341);
				match(RBRACK);
				}
				break;
			case LBRACE:
				_localctx = new DictAtomContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(342);
				match(LBRACE);
				setState(344);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 246651382820864L) != 0)) {
					{
					setState(343);
					dictorsetmaker();
					}
				}

				setState(346);
				match(RBRACE);
				}
				break;
			case NAME:
				_localctx = new NameAtomContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(347);
				match(NAME);
				}
				break;
			case NUMBER:
				_localctx = new NumberAtomContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(348);
				match(NUMBER);
				}
				break;
			case STRING:
				_localctx = new StringAtomContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(350); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(349);
					match(STRING);
					}
					}
					setState(352); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==STRING );
				}
				break;
			case TRUE:
				_localctx = new TrueAtomContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(354);
				match(TRUE);
				}
				break;
			case FALSE:
				_localctx = new FalseAtomContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(355);
				match(FALSE);
				}
				break;
			case NONE:
				_localctx = new NoneAtomContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(356);
				match(NONE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ListmakerContext extends ParserRuleContext {
		public List<TestContext> test() {
			return getRuleContexts(TestContext.class);
		}
		public TestContext test(int i) {
			return getRuleContext(TestContext.class,i);
		}
		public Comp_forContext comp_for() {
			return getRuleContext(Comp_forContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(PythonSubsetParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PythonSubsetParser.COMMA, i);
		}
		public ListmakerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listmaker; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterListmaker(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitListmaker(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitListmaker(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListmakerContext listmaker() throws RecognitionException {
		ListmakerContext _localctx = new ListmakerContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_listmaker);
		int _la;
		try {
			int _alt;
			setState(373);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(359);
				test();
				setState(360);
				comp_for();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(362);
				test();
				setState(367);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(363);
						match(COMMA);
						setState(364);
						test();
						}
						} 
					}
					setState(369);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
				}
				setState(371);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(370);
					match(COMMA);
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Testlist_compContext extends ParserRuleContext {
		public List<TestContext> test() {
			return getRuleContexts(TestContext.class);
		}
		public TestContext test(int i) {
			return getRuleContext(TestContext.class,i);
		}
		public Comp_forContext comp_for() {
			return getRuleContext(Comp_forContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(PythonSubsetParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PythonSubsetParser.COMMA, i);
		}
		public Testlist_compContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_testlist_comp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterTestlist_comp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitTestlist_comp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitTestlist_comp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Testlist_compContext testlist_comp() throws RecognitionException {
		Testlist_compContext _localctx = new Testlist_compContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_testlist_comp);
		int _la;
		try {
			int _alt;
			setState(389);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(375);
				test();
				setState(376);
				comp_for();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(378);
				test();
				setState(383);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,42,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(379);
						match(COMMA);
						setState(380);
						test();
						}
						} 
					}
					setState(385);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,42,_ctx);
				}
				setState(387);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(386);
					match(COMMA);
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TestlistContext extends ParserRuleContext {
		public List<TestContext> test() {
			return getRuleContexts(TestContext.class);
		}
		public TestContext test(int i) {
			return getRuleContext(TestContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PythonSubsetParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PythonSubsetParser.COMMA, i);
		}
		public TestlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_testlist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterTestlist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitTestlist(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitTestlist(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TestlistContext testlist() throws RecognitionException {
		TestlistContext _localctx = new TestlistContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_testlist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(391);
			test();
			setState(396);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(392);
				match(COMMA);
				setState(393);
				test();
				}
				}
				setState(398);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DictorsetmakerContext extends ParserRuleContext {
		public List<TestContext> test() {
			return getRuleContexts(TestContext.class);
		}
		public TestContext test(int i) {
			return getRuleContext(TestContext.class,i);
		}
		public List<TerminalNode> COLON() { return getTokens(PythonSubsetParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(PythonSubsetParser.COLON, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PythonSubsetParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PythonSubsetParser.COMMA, i);
		}
		public Comp_forContext comp_for() {
			return getRuleContext(Comp_forContext.class,0);
		}
		public DictorsetmakerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dictorsetmaker; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterDictorsetmaker(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitDictorsetmaker(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitDictorsetmaker(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DictorsetmakerContext dictorsetmaker() throws RecognitionException {
		DictorsetmakerContext _localctx = new DictorsetmakerContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_dictorsetmaker);
		int _la;
		try {
			int _alt;
			setState(421);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(399);
				test();
				setState(400);
				match(COLON);
				setState(401);
				test();
				}
				setState(410);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,46,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(403);
						match(COMMA);
						{
						setState(404);
						test();
						setState(405);
						match(COLON);
						setState(406);
						test();
						}
						}
						} 
					}
					setState(412);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,46,_ctx);
				}
				setState(414);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(413);
					match(COMMA);
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(416);
				test();
				setState(417);
				match(COLON);
				setState(418);
				test();
				setState(419);
				comp_for();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Comp_forContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(PythonSubsetParser.FOR, 0); }
		public TerminalNode NAME() { return getToken(PythonSubsetParser.NAME, 0); }
		public TerminalNode IN() { return getToken(PythonSubsetParser.IN, 0); }
		public List<Or_testContext> or_test() {
			return getRuleContexts(Or_testContext.class);
		}
		public Or_testContext or_test(int i) {
			return getRuleContext(Or_testContext.class,i);
		}
		public TerminalNode IF() { return getToken(PythonSubsetParser.IF, 0); }
		public Comp_forContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comp_for; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterComp_for(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitComp_for(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitComp_for(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Comp_forContext comp_for() throws RecognitionException {
		Comp_forContext _localctx = new Comp_forContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_comp_for);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(423);
			match(FOR);
			setState(424);
			match(NAME);
			setState(425);
			match(IN);
			setState(426);
			or_test();
			setState(429);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IF) {
				{
				setState(427);
				match(IF);
				setState(428);
				or_test();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgumentsContext extends ParserRuleContext {
		public List<ArgumentContext> argument() {
			return getRuleContexts(ArgumentContext.class);
		}
		public ArgumentContext argument(int i) {
			return getRuleContext(ArgumentContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PythonSubsetParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PythonSubsetParser.COMMA, i);
		}
		public TestContext test() {
			return getRuleContext(TestContext.class,0);
		}
		public Comp_forContext comp_for() {
			return getRuleContext(Comp_forContext.class,0);
		}
		public ArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitArguments(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitArguments(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentsContext arguments() throws RecognitionException {
		ArgumentsContext _localctx = new ArgumentsContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_arguments);
		int _la;
		try {
			setState(442);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(431);
				argument();
				setState(436);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(432);
					match(COMMA);
					setState(433);
					argument();
					}
					}
					setState(438);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(439);
				test();
				setState(440);
				comp_for();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgumentContext extends ParserRuleContext {
		public ArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument; }
	 
		public ArgumentContext() { }
		public void copyFrom(ArgumentContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PositionalArgumentContext extends ArgumentContext {
		public TestContext test() {
			return getRuleContext(TestContext.class,0);
		}
		public PositionalArgumentContext(ArgumentContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterPositionalArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitPositionalArgument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitPositionalArgument(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class KeywordArgumentContext extends ArgumentContext {
		public TerminalNode NAME() { return getToken(PythonSubsetParser.NAME, 0); }
		public TerminalNode EQUAL() { return getToken(PythonSubsetParser.EQUAL, 0); }
		public TestContext test() {
			return getRuleContext(TestContext.class,0);
		}
		public KeywordArgumentContext(ArgumentContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).enterKeywordArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonSubsetParserListener ) ((PythonSubsetParserListener)listener).exitKeywordArgument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonSubsetParserVisitor ) return ((PythonSubsetParserVisitor<? extends T>)visitor).visitKeywordArgument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentContext argument() throws RecognitionException {
		ArgumentContext _localctx = new ArgumentContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_argument);
		try {
			setState(448);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
			case 1:
				_localctx = new KeywordArgumentContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(444);
				match(NAME);
				setState(445);
				match(EQUAL);
				setState(446);
				test();
				}
				break;
			case 2:
				_localctx = new PositionalArgumentContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(447);
				test();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u00014\u01c3\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002"+
		"(\u0007(\u0002)\u0007)\u0001\u0000\u0001\u0000\u0005\u0000W\b\u0000\n"+
		"\u0000\f\u0000Z\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0003\u0001`\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003"+
		"k\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004p\b\u0004\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005v\b\u0005\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006}\b"+
		"\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007\u0082\b\u0007\n"+
		"\u0007\f\u0007\u0085\t\u0007\u0001\b\u0001\b\u0001\b\u0003\b\u008a\b\b"+
		"\u0001\t\u0001\t\u0001\t\u0005\t\u008f\b\t\n\t\f\t\u0092\t\t\u0001\n\u0005"+
		"\n\u0095\b\n\n\n\f\n\u0098\t\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n"+
		"\u009e\b\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0003\u000b\u00a8\b\u000b\u0001\u000b\u0003\u000b\u00ab"+
		"\b\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0005\f\u00b2"+
		"\b\f\n\f\f\f\u00b5\t\f\u0001\r\u0001\r\u0001\r\u0005\r\u00ba\b\r\n\r\f"+
		"\r\u00bd\t\r\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u00c2\b\u000e"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0004\u000f\u00c8\b\u000f"+
		"\u000b\u000f\f\u000f\u00c9\u0001\u000f\u0001\u000f\u0003\u000f\u00ce\b"+
		"\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0005\u0010\u00d9\b\u0010\n"+
		"\u0010\f\u0010\u00dc\t\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003"+
		"\u0010\u00e1\b\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0005\u0013\u00f2"+
		"\b\u0013\n\u0013\f\u0013\u00f5\t\u0013\u0001\u0014\u0001\u0014\u0003\u0014"+
		"\u00f9\b\u0014\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0005\u0017\u0102\b\u0017\n\u0017\f\u0017\u0105"+
		"\t\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0005\u0018\u010a\b\u0018"+
		"\n\u0018\f\u0018\u010d\t\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0003"+
		"\u0019\u0112\b\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0005"+
		"\u001a\u0118\b\u001a\n\u001a\f\u001a\u011b\t\u001a\u0001\u001b\u0001\u001b"+
		"\u0001\u001c\u0001\u001c\u0001\u001c\u0005\u001c\u0122\b\u001c\n\u001c"+
		"\f\u001c\u0125\t\u001c\u0001\u001d\u0001\u001d\u0001\u001d\u0005\u001d"+
		"\u012a\b\u001d\n\u001d\f\u001d\u012d\t\u001d\u0001\u001e\u0001\u001e\u0001"+
		"\u001e\u0003\u001e\u0132\b\u001e\u0001\u001f\u0001\u001f\u0001\u001f\u0003"+
		"\u001f\u0137\b\u001f\u0001 \u0001 \u0005 \u013b\b \n \f \u013e\t \u0001"+
		"!\u0001!\u0003!\u0142\b!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001"+
		"!\u0003!\u014b\b!\u0001\"\u0001\"\u0003\"\u014f\b\"\u0001\"\u0001\"\u0001"+
		"\"\u0003\"\u0154\b\"\u0001\"\u0001\"\u0001\"\u0003\"\u0159\b\"\u0001\""+
		"\u0001\"\u0001\"\u0001\"\u0004\"\u015f\b\"\u000b\"\f\"\u0160\u0001\"\u0001"+
		"\"\u0001\"\u0003\"\u0166\b\"\u0001#\u0001#\u0001#\u0001#\u0001#\u0001"+
		"#\u0005#\u016e\b#\n#\f#\u0171\t#\u0001#\u0003#\u0174\b#\u0003#\u0176\b"+
		"#\u0001$\u0001$\u0001$\u0001$\u0001$\u0001$\u0005$\u017e\b$\n$\f$\u0181"+
		"\t$\u0001$\u0003$\u0184\b$\u0003$\u0186\b$\u0001%\u0001%\u0001%\u0005"+
		"%\u018b\b%\n%\f%\u018e\t%\u0001&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001"+
		"&\u0001&\u0001&\u0005&\u0199\b&\n&\f&\u019c\t&\u0001&\u0003&\u019f\b&"+
		"\u0001&\u0001&\u0001&\u0001&\u0001&\u0003&\u01a6\b&\u0001\'\u0001\'\u0001"+
		"\'\u0001\'\u0001\'\u0001\'\u0003\'\u01ae\b\'\u0001(\u0001(\u0001(\u0005"+
		"(\u01b3\b(\n(\f(\u01b6\t(\u0001(\u0001(\u0001(\u0003(\u01bb\b(\u0001)"+
		"\u0001)\u0001)\u0001)\u0003)\u01c1\b)\u0001)\u0000\u0000*\u0000\u0002"+
		"\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e"+
		" \"$&(*,.02468:<>@BDFHJLNPR\u0000\u0003\u0002\u0000\u0006\u0006\u001c"+
		"!\u0001\u0000\u0012\u0013\u0002\u0000\u0014\u0016\u0018\u0018\u01da\u0000"+
		"X\u0001\u0000\u0000\u0000\u0002_\u0001\u0000\u0000\u0000\u0004a\u0001"+
		"\u0000\u0000\u0000\u0006j\u0001\u0000\u0000\u0000\bo\u0001\u0000\u0000"+
		"\u0000\nq\u0001\u0000\u0000\u0000\fw\u0001\u0000\u0000\u0000\u000e~\u0001"+
		"\u0000\u0000\u0000\u0010\u0086\u0001\u0000\u0000\u0000\u0012\u008b\u0001"+
		"\u0000\u0000\u0000\u0014\u0096\u0001\u0000\u0000\u0000\u0016\u00a3\u0001"+
		"\u0000\u0000\u0000\u0018\u00ae\u0001\u0000\u0000\u0000\u001a\u00b6\u0001"+
		"\u0000\u0000\u0000\u001c\u00be\u0001\u0000\u0000\u0000\u001e\u00cd\u0001"+
		"\u0000\u0000\u0000 \u00cf\u0001\u0000\u0000\u0000\"\u00e2\u0001\u0000"+
		"\u0000\u0000$\u00e9\u0001\u0000\u0000\u0000&\u00ed\u0001\u0000\u0000\u0000"+
		"(\u00f6\u0001\u0000\u0000\u0000*\u00fa\u0001\u0000\u0000\u0000,\u00fc"+
		"\u0001\u0000\u0000\u0000.\u00fe\u0001\u0000\u0000\u00000\u0106\u0001\u0000"+
		"\u0000\u00002\u0111\u0001\u0000\u0000\u00004\u0113\u0001\u0000\u0000\u0000"+
		"6\u011c\u0001\u0000\u0000\u00008\u011e\u0001\u0000\u0000\u0000:\u0126"+
		"\u0001\u0000\u0000\u0000<\u0131\u0001\u0000\u0000\u0000>\u0133\u0001\u0000"+
		"\u0000\u0000@\u0138\u0001\u0000\u0000\u0000B\u014a\u0001\u0000\u0000\u0000"+
		"D\u0165\u0001\u0000\u0000\u0000F\u0175\u0001\u0000\u0000\u0000H\u0185"+
		"\u0001\u0000\u0000\u0000J\u0187\u0001\u0000\u0000\u0000L\u01a5\u0001\u0000"+
		"\u0000\u0000N\u01a7\u0001\u0000\u0000\u0000P\u01ba\u0001\u0000\u0000\u0000"+
		"R\u01c0\u0001\u0000\u0000\u0000TW\u00050\u0000\u0000UW\u0003\u0002\u0001"+
		"\u0000VT\u0001\u0000\u0000\u0000VU\u0001\u0000\u0000\u0000WZ\u0001\u0000"+
		"\u0000\u0000XV\u0001\u0000\u0000\u0000XY\u0001\u0000\u0000\u0000Y[\u0001"+
		"\u0000\u0000\u0000ZX\u0001\u0000\u0000\u0000[\\\u0005\u0000\u0000\u0001"+
		"\\\u0001\u0001\u0000\u0000\u0000]`\u0003\u0004\u0002\u0000^`\u0003\b\u0004"+
		"\u0000_]\u0001\u0000\u0000\u0000_^\u0001\u0000\u0000\u0000`\u0003\u0001"+
		"\u0000\u0000\u0000ab\u0003\u0006\u0003\u0000bc\u00050\u0000\u0000c\u0005"+
		"\u0001\u0000\u0000\u0000dk\u0003*\u0015\u0000ek\u0003$\u0012\u0000fk\u0003"+
		"(\u0014\u0000gk\u0003&\u0013\u0000hk\u0003\n\u0005\u0000ik\u0003\f\u0006"+
		"\u0000jd\u0001\u0000\u0000\u0000je\u0001\u0000\u0000\u0000jf\u0001\u0000"+
		"\u0000\u0000jg\u0001\u0000\u0000\u0000jh\u0001\u0000\u0000\u0000ji\u0001"+
		"\u0000\u0000\u0000k\u0007\u0001\u0000\u0000\u0000lp\u0003\u0014\n\u0000"+
		"mp\u0003 \u0010\u0000np\u0003\"\u0011\u0000ol\u0001\u0000\u0000\u0000"+
		"om\u0001\u0000\u0000\u0000on\u0001\u0000\u0000\u0000p\t\u0001\u0000\u0000"+
		"\u0000qr\u0005\b\u0000\u0000ru\u0003\u0012\t\u0000st\u0005\n\u0000\u0000"+
		"tv\u0005/\u0000\u0000us\u0001\u0000\u0000\u0000uv\u0001\u0000\u0000\u0000"+
		"v\u000b\u0001\u0000\u0000\u0000wx\u0005\t\u0000\u0000xy\u0003\u0012\t"+
		"\u0000y|\u0005\b\u0000\u0000z}\u0005\u0014\u0000\u0000{}\u0003\u000e\u0007"+
		"\u0000|z\u0001\u0000\u0000\u0000|{\u0001\u0000\u0000\u0000}\r\u0001\u0000"+
		"\u0000\u0000~\u0083\u0003\u0010\b\u0000\u007f\u0080\u0005(\u0000\u0000"+
		"\u0080\u0082\u0003\u0010\b\u0000\u0081\u007f\u0001\u0000\u0000\u0000\u0082"+
		"\u0085\u0001\u0000\u0000\u0000\u0083\u0081\u0001\u0000\u0000\u0000\u0083"+
		"\u0084\u0001\u0000\u0000\u0000\u0084\u000f\u0001\u0000\u0000\u0000\u0085"+
		"\u0083\u0001\u0000\u0000\u0000\u0086\u0089\u0005/\u0000\u0000\u0087\u0088"+
		"\u0005\n\u0000\u0000\u0088\u008a\u0005/\u0000\u0000\u0089\u0087\u0001"+
		"\u0000\u0000\u0000\u0089\u008a\u0001\u0000\u0000\u0000\u008a\u0011\u0001"+
		"\u0000\u0000\u0000\u008b\u0090\u0005/\u0000\u0000\u008c\u008d\u0005*\u0000"+
		"\u0000\u008d\u008f\u0005/\u0000\u0000\u008e\u008c\u0001\u0000\u0000\u0000"+
		"\u008f\u0092\u0001\u0000\u0000\u0000\u0090\u008e\u0001\u0000\u0000\u0000"+
		"\u0090\u0091\u0001\u0000\u0000\u0000\u0091\u0013\u0001\u0000\u0000\u0000"+
		"\u0092\u0090\u0001\u0000\u0000\u0000\u0093\u0095\u0003\u0016\u000b\u0000"+
		"\u0094\u0093\u0001\u0000\u0000\u0000\u0095\u0098\u0001\u0000\u0000\u0000"+
		"\u0096\u0094\u0001\u0000\u0000\u0000\u0096\u0097\u0001\u0000\u0000\u0000"+
		"\u0097\u0099\u0001\u0000\u0000\u0000\u0098\u0096\u0001\u0000\u0000\u0000"+
		"\u0099\u009a\u0005\u0001\u0000\u0000\u009a\u009b\u0005/\u0000\u0000\u009b"+
		"\u009d\u0005\"\u0000\u0000\u009c\u009e\u0003\u001a\r\u0000\u009d\u009c"+
		"\u0001\u0000\u0000\u0000\u009d\u009e\u0001\u0000\u0000\u0000\u009e\u009f"+
		"\u0001\u0000\u0000\u0000\u009f\u00a0\u0005#\u0000\u0000\u00a0\u00a1\u0005"+
		")\u0000\u0000\u00a1\u00a2\u0003\u001e\u000f\u0000\u00a2\u0015\u0001\u0000"+
		"\u0000\u0000\u00a3\u00a4\u0005,\u0000\u0000\u00a4\u00aa\u0003\u0018\f"+
		"\u0000\u00a5\u00a7\u0005\"\u0000\u0000\u00a6\u00a8\u0003P(\u0000\u00a7"+
		"\u00a6\u0001\u0000\u0000\u0000\u00a7\u00a8\u0001\u0000\u0000\u0000\u00a8"+
		"\u00a9\u0001\u0000\u0000\u0000\u00a9\u00ab\u0005#\u0000\u0000\u00aa\u00a5"+
		"\u0001\u0000\u0000\u0000\u00aa\u00ab\u0001\u0000\u0000\u0000\u00ab\u00ac"+
		"\u0001\u0000\u0000\u0000\u00ac\u00ad\u00050\u0000\u0000\u00ad\u0017\u0001"+
		"\u0000\u0000\u0000\u00ae\u00b3\u0005/\u0000\u0000\u00af\u00b0\u0005*\u0000"+
		"\u0000\u00b0\u00b2\u0005/\u0000\u0000\u00b1\u00af\u0001\u0000\u0000\u0000"+
		"\u00b2\u00b5\u0001\u0000\u0000\u0000\u00b3\u00b1\u0001\u0000\u0000\u0000"+
		"\u00b3\u00b4\u0001\u0000\u0000\u0000\u00b4\u0019\u0001\u0000\u0000\u0000"+
		"\u00b5\u00b3\u0001\u0000\u0000\u0000\u00b6\u00bb\u0003\u001c\u000e\u0000"+
		"\u00b7\u00b8\u0005(\u0000\u0000\u00b8\u00ba\u0003\u001c\u000e\u0000\u00b9"+
		"\u00b7\u0001\u0000\u0000\u0000\u00ba\u00bd\u0001\u0000\u0000\u0000\u00bb"+
		"\u00b9\u0001\u0000\u0000\u0000\u00bb\u00bc\u0001\u0000\u0000\u0000\u00bc"+
		"\u001b\u0001\u0000\u0000\u0000\u00bd\u00bb\u0001\u0000\u0000\u0000\u00be"+
		"\u00c1\u0005/\u0000\u0000\u00bf\u00c0\u0005\u0019\u0000\u0000\u00c0\u00c2"+
		"\u0003,\u0016\u0000\u00c1\u00bf\u0001\u0000\u0000\u0000\u00c1\u00c2\u0001"+
		"\u0000\u0000\u0000\u00c2\u001d\u0001\u0000\u0000\u0000\u00c3\u00ce\u0003"+
		"\u0004\u0002\u0000\u00c4\u00c5\u00050\u0000\u0000\u00c5\u00c7\u00051\u0000"+
		"\u0000\u00c6\u00c8\u0003\u0002\u0001\u0000\u00c7\u00c6\u0001\u0000\u0000"+
		"\u0000\u00c8\u00c9\u0001\u0000\u0000\u0000\u00c9\u00c7\u0001\u0000\u0000"+
		"\u0000\u00c9\u00ca\u0001\u0000\u0000\u0000\u00ca\u00cb\u0001\u0000\u0000"+
		"\u0000\u00cb\u00cc\u00052\u0000\u0000\u00cc\u00ce\u0001\u0000\u0000\u0000"+
		"\u00cd\u00c3\u0001\u0000\u0000\u0000\u00cd\u00c4\u0001\u0000\u0000\u0000"+
		"\u00ce\u001f\u0001\u0000\u0000\u0000\u00cf\u00d0\u0005\u0002\u0000\u0000"+
		"\u00d0\u00d1\u0003,\u0016\u0000\u00d1\u00d2\u0005)\u0000\u0000\u00d2\u00da"+
		"\u0003\u001e\u000f\u0000\u00d3\u00d4\u0005\u0003\u0000\u0000\u00d4\u00d5"+
		"\u0003,\u0016\u0000\u00d5\u00d6\u0005)\u0000\u0000\u00d6\u00d7\u0003\u001e"+
		"\u000f\u0000\u00d7\u00d9\u0001\u0000\u0000\u0000\u00d8\u00d3\u0001\u0000"+
		"\u0000\u0000\u00d9\u00dc\u0001\u0000\u0000\u0000\u00da\u00d8\u0001\u0000"+
		"\u0000\u0000\u00da\u00db\u0001\u0000\u0000\u0000\u00db\u00e0\u0001\u0000"+
		"\u0000\u0000\u00dc\u00da\u0001\u0000\u0000\u0000\u00dd\u00de\u0005\u0004"+
		"\u0000\u0000\u00de\u00df\u0005)\u0000\u0000\u00df\u00e1\u0003\u001e\u000f"+
		"\u0000\u00e0\u00dd\u0001\u0000\u0000\u0000\u00e0\u00e1\u0001\u0000\u0000"+
		"\u0000\u00e1!\u0001\u0000\u0000\u0000\u00e2\u00e3\u0005\u0005\u0000\u0000"+
		"\u00e3\u00e4\u0005/\u0000\u0000\u00e4\u00e5\u0005\u0006\u0000\u0000\u00e5"+
		"\u00e6\u0003,\u0016\u0000\u00e6\u00e7\u0005)\u0000\u0000\u00e7\u00e8\u0003"+
		"\u001e\u000f\u0000\u00e8#\u0001\u0000\u0000\u0000\u00e9\u00ea\u0005/\u0000"+
		"\u0000\u00ea\u00eb\u0005\u0019\u0000\u0000\u00eb\u00ec\u0003,\u0016\u0000"+
		"\u00ec%\u0001\u0000\u0000\u0000\u00ed\u00ee\u0005\u000b\u0000\u0000\u00ee"+
		"\u00f3\u0005/\u0000\u0000\u00ef\u00f0\u0005(\u0000\u0000\u00f0\u00f2\u0005"+
		"/\u0000\u0000\u00f1\u00ef\u0001\u0000\u0000\u0000\u00f2\u00f5\u0001\u0000"+
		"\u0000\u0000\u00f3\u00f1\u0001\u0000\u0000\u0000\u00f3\u00f4\u0001\u0000"+
		"\u0000\u0000\u00f4\'\u0001\u0000\u0000\u0000\u00f5\u00f3\u0001\u0000\u0000"+
		"\u0000\u00f6\u00f8\u0005\u0007\u0000\u0000\u00f7\u00f9\u0003,\u0016\u0000"+
		"\u00f8\u00f7\u0001\u0000\u0000\u0000\u00f8\u00f9\u0001\u0000\u0000\u0000"+
		"\u00f9)\u0001\u0000\u0000\u0000\u00fa\u00fb\u0003,\u0016\u0000\u00fb+"+
		"\u0001\u0000\u0000\u0000\u00fc\u00fd\u0003.\u0017\u0000\u00fd-\u0001\u0000"+
		"\u0000\u0000\u00fe\u0103\u00030\u0018\u0000\u00ff\u0100\u0005\u000f\u0000"+
		"\u0000\u0100\u0102\u00030\u0018\u0000\u0101\u00ff\u0001\u0000\u0000\u0000"+
		"\u0102\u0105\u0001\u0000\u0000\u0000\u0103\u0101\u0001\u0000\u0000\u0000"+
		"\u0103\u0104\u0001\u0000\u0000\u0000\u0104/\u0001\u0000\u0000\u0000\u0105"+
		"\u0103\u0001\u0000\u0000\u0000\u0106\u010b\u00032\u0019\u0000\u0107\u0108"+
		"\u0005\u0010\u0000\u0000\u0108\u010a\u00032\u0019\u0000\u0109\u0107\u0001"+
		"\u0000\u0000\u0000\u010a\u010d\u0001\u0000\u0000\u0000\u010b\u0109\u0001"+
		"\u0000\u0000\u0000\u010b\u010c\u0001\u0000\u0000\u0000\u010c1\u0001\u0000"+
		"\u0000\u0000\u010d\u010b\u0001\u0000\u0000\u0000\u010e\u010f\u0005\u0011"+
		"\u0000\u0000\u010f\u0112\u00032\u0019\u0000\u0110\u0112\u00034\u001a\u0000"+
		"\u0111\u010e\u0001\u0000\u0000\u0000\u0111\u0110\u0001\u0000\u0000\u0000"+
		"\u01123\u0001\u0000\u0000\u0000\u0113\u0119\u00038\u001c\u0000\u0114\u0115"+
		"\u00036\u001b\u0000\u0115\u0116\u00038\u001c\u0000\u0116\u0118\u0001\u0000"+
		"\u0000\u0000\u0117\u0114\u0001\u0000\u0000\u0000\u0118\u011b\u0001\u0000"+
		"\u0000\u0000\u0119\u0117\u0001\u0000\u0000\u0000\u0119\u011a\u0001\u0000"+
		"\u0000\u0000\u011a5\u0001\u0000\u0000\u0000\u011b\u0119\u0001\u0000\u0000"+
		"\u0000\u011c\u011d\u0007\u0000\u0000\u0000\u011d7\u0001\u0000\u0000\u0000"+
		"\u011e\u0123\u0003:\u001d\u0000\u011f\u0120\u0007\u0001\u0000\u0000\u0120"+
		"\u0122\u0003:\u001d\u0000\u0121\u011f\u0001\u0000\u0000\u0000\u0122\u0125"+
		"\u0001\u0000\u0000\u0000\u0123\u0121\u0001\u0000\u0000\u0000\u0123\u0124"+
		"\u0001\u0000\u0000\u0000\u01249\u0001\u0000\u0000\u0000\u0125\u0123\u0001"+
		"\u0000\u0000\u0000\u0126\u012b\u0003<\u001e\u0000\u0127\u0128\u0007\u0002"+
		"\u0000\u0000\u0128\u012a\u0003<\u001e\u0000\u0129\u0127\u0001\u0000\u0000"+
		"\u0000\u012a\u012d\u0001\u0000\u0000\u0000\u012b\u0129\u0001\u0000\u0000"+
		"\u0000\u012b\u012c\u0001\u0000\u0000\u0000\u012c;\u0001\u0000\u0000\u0000"+
		"\u012d\u012b\u0001\u0000\u0000\u0000\u012e\u012f\u0007\u0001\u0000\u0000"+
		"\u012f\u0132\u0003<\u001e\u0000\u0130\u0132\u0003>\u001f\u0000\u0131\u012e"+
		"\u0001\u0000\u0000\u0000\u0131\u0130\u0001\u0000\u0000\u0000\u0132=\u0001"+
		"\u0000\u0000\u0000\u0133\u0136\u0003@ \u0000\u0134\u0135\u0005\u0017\u0000"+
		"\u0000\u0135\u0137\u0003<\u001e\u0000\u0136\u0134\u0001\u0000\u0000\u0000"+
		"\u0136\u0137\u0001\u0000\u0000\u0000\u0137?\u0001\u0000\u0000\u0000\u0138"+
		"\u013c\u0003D\"\u0000\u0139\u013b\u0003B!\u0000\u013a\u0139\u0001\u0000"+
		"\u0000\u0000\u013b\u013e\u0001\u0000\u0000\u0000\u013c\u013a\u0001\u0000"+
		"\u0000\u0000\u013c\u013d\u0001\u0000\u0000\u0000\u013dA\u0001\u0000\u0000"+
		"\u0000\u013e\u013c\u0001\u0000\u0000\u0000\u013f\u0141\u0005\"\u0000\u0000"+
		"\u0140\u0142\u0003P(\u0000\u0141\u0140\u0001\u0000\u0000\u0000\u0141\u0142"+
		"\u0001\u0000\u0000\u0000\u0142\u0143\u0001\u0000\u0000\u0000\u0143\u014b"+
		"\u0005#\u0000\u0000\u0144\u0145\u0005$\u0000\u0000\u0145\u0146\u0003,"+
		"\u0016\u0000\u0146\u0147\u0005%\u0000\u0000\u0147\u014b\u0001\u0000\u0000"+
		"\u0000\u0148\u0149\u0005*\u0000\u0000\u0149\u014b\u0005/\u0000\u0000\u014a"+
		"\u013f\u0001\u0000\u0000\u0000\u014a\u0144\u0001\u0000\u0000\u0000\u014a"+
		"\u0148\u0001\u0000\u0000\u0000\u014bC\u0001\u0000\u0000\u0000\u014c\u014e"+
		"\u0005\"\u0000\u0000\u014d\u014f\u0003H$\u0000\u014e\u014d\u0001\u0000"+
		"\u0000\u0000\u014e\u014f\u0001\u0000\u0000\u0000\u014f\u0150\u0001\u0000"+
		"\u0000\u0000\u0150\u0166\u0005#\u0000\u0000\u0151\u0153\u0005$\u0000\u0000"+
		"\u0152\u0154\u0003F#\u0000\u0153\u0152\u0001\u0000\u0000\u0000\u0153\u0154"+
		"\u0001\u0000\u0000\u0000\u0154\u0155\u0001\u0000\u0000\u0000\u0155\u0166"+
		"\u0005%\u0000\u0000\u0156\u0158\u0005&\u0000\u0000\u0157\u0159\u0003L"+
		"&\u0000\u0158\u0157\u0001\u0000\u0000\u0000\u0158\u0159\u0001\u0000\u0000"+
		"\u0000\u0159\u015a\u0001\u0000\u0000\u0000\u015a\u0166\u0005\'\u0000\u0000"+
		"\u015b\u0166\u0005/\u0000\u0000\u015c\u0166\u0005.\u0000\u0000\u015d\u015f"+
		"\u0005-\u0000\u0000\u015e\u015d\u0001\u0000\u0000\u0000\u015f\u0160\u0001"+
		"\u0000\u0000\u0000\u0160\u015e\u0001\u0000\u0000\u0000\u0160\u0161\u0001"+
		"\u0000\u0000\u0000\u0161\u0166\u0001\u0000\u0000\u0000\u0162\u0166\u0005"+
		"\f\u0000\u0000\u0163\u0166\u0005\r\u0000\u0000\u0164\u0166\u0005\u000e"+
		"\u0000\u0000\u0165\u014c\u0001\u0000\u0000\u0000\u0165\u0151\u0001\u0000"+
		"\u0000\u0000\u0165\u0156\u0001\u0000\u0000\u0000\u0165\u015b\u0001\u0000"+
		"\u0000\u0000\u0165\u015c\u0001\u0000\u0000\u0000\u0165\u015e\u0001\u0000"+
		"\u0000\u0000\u0165\u0162\u0001\u0000\u0000\u0000\u0165\u0163\u0001\u0000"+
		"\u0000\u0000\u0165\u0164\u0001\u0000\u0000\u0000\u0166E\u0001\u0000\u0000"+
		"\u0000\u0167\u0168\u0003,\u0016\u0000\u0168\u0169\u0003N\'\u0000\u0169"+
		"\u0176\u0001\u0000\u0000\u0000\u016a\u016f\u0003,\u0016\u0000\u016b\u016c"+
		"\u0005(\u0000\u0000\u016c\u016e\u0003,\u0016\u0000\u016d\u016b\u0001\u0000"+
		"\u0000\u0000\u016e\u0171\u0001\u0000\u0000\u0000\u016f\u016d\u0001\u0000"+
		"\u0000\u0000\u016f\u0170\u0001\u0000\u0000\u0000\u0170\u0173\u0001\u0000"+
		"\u0000\u0000\u0171\u016f\u0001\u0000\u0000\u0000\u0172\u0174\u0005(\u0000"+
		"\u0000\u0173\u0172\u0001\u0000\u0000\u0000\u0173\u0174\u0001\u0000\u0000"+
		"\u0000\u0174\u0176\u0001\u0000\u0000\u0000\u0175\u0167\u0001\u0000\u0000"+
		"\u0000\u0175\u016a\u0001\u0000\u0000\u0000\u0176G\u0001\u0000\u0000\u0000"+
		"\u0177\u0178\u0003,\u0016\u0000\u0178\u0179\u0003N\'\u0000\u0179\u0186"+
		"\u0001\u0000\u0000\u0000\u017a\u017f\u0003,\u0016\u0000\u017b\u017c\u0005"+
		"(\u0000\u0000\u017c\u017e\u0003,\u0016\u0000\u017d\u017b\u0001\u0000\u0000"+
		"\u0000\u017e\u0181\u0001\u0000\u0000\u0000\u017f\u017d\u0001\u0000\u0000"+
		"\u0000\u017f\u0180\u0001\u0000\u0000\u0000\u0180\u0183\u0001\u0000\u0000"+
		"\u0000\u0181\u017f\u0001\u0000\u0000\u0000\u0182\u0184\u0005(\u0000\u0000"+
		"\u0183\u0182\u0001\u0000\u0000\u0000\u0183\u0184\u0001\u0000\u0000\u0000"+
		"\u0184\u0186\u0001\u0000\u0000\u0000\u0185\u0177\u0001\u0000\u0000\u0000"+
		"\u0185\u017a\u0001\u0000\u0000\u0000\u0186I\u0001\u0000\u0000\u0000\u0187"+
		"\u018c\u0003,\u0016\u0000\u0188\u0189\u0005(\u0000\u0000\u0189\u018b\u0003"+
		",\u0016\u0000\u018a\u0188\u0001\u0000\u0000\u0000\u018b\u018e\u0001\u0000"+
		"\u0000\u0000\u018c\u018a\u0001\u0000\u0000\u0000\u018c\u018d\u0001\u0000"+
		"\u0000\u0000\u018dK\u0001\u0000\u0000\u0000\u018e\u018c\u0001\u0000\u0000"+
		"\u0000\u018f\u0190\u0003,\u0016\u0000\u0190\u0191\u0005)\u0000\u0000\u0191"+
		"\u0192\u0003,\u0016\u0000\u0192\u019a\u0001\u0000\u0000\u0000\u0193\u0194"+
		"\u0005(\u0000\u0000\u0194\u0195\u0003,\u0016\u0000\u0195\u0196\u0005)"+
		"\u0000\u0000\u0196\u0197\u0003,\u0016\u0000\u0197\u0199\u0001\u0000\u0000"+
		"\u0000\u0198\u0193\u0001\u0000\u0000\u0000\u0199\u019c\u0001\u0000\u0000"+
		"\u0000\u019a\u0198\u0001\u0000\u0000\u0000\u019a\u019b\u0001\u0000\u0000"+
		"\u0000\u019b\u019e\u0001\u0000\u0000\u0000\u019c\u019a\u0001\u0000\u0000"+
		"\u0000\u019d\u019f\u0005(\u0000\u0000\u019e\u019d\u0001\u0000\u0000\u0000"+
		"\u019e\u019f\u0001\u0000\u0000\u0000\u019f\u01a6\u0001\u0000\u0000\u0000"+
		"\u01a0\u01a1\u0003,\u0016\u0000\u01a1\u01a2\u0005)\u0000\u0000\u01a2\u01a3"+
		"\u0003,\u0016\u0000\u01a3\u01a4\u0003N\'\u0000\u01a4\u01a6\u0001\u0000"+
		"\u0000\u0000\u01a5\u018f\u0001\u0000\u0000\u0000\u01a5\u01a0\u0001\u0000"+
		"\u0000\u0000\u01a6M\u0001\u0000\u0000\u0000\u01a7\u01a8\u0005\u0005\u0000"+
		"\u0000\u01a8\u01a9\u0005/\u0000\u0000\u01a9\u01aa\u0005\u0006\u0000\u0000"+
		"\u01aa\u01ad\u0003.\u0017\u0000\u01ab\u01ac\u0005\u0002\u0000\u0000\u01ac"+
		"\u01ae\u0003.\u0017\u0000\u01ad\u01ab\u0001\u0000\u0000\u0000\u01ad\u01ae"+
		"\u0001\u0000\u0000\u0000\u01aeO\u0001\u0000\u0000\u0000\u01af\u01b4\u0003"+
		"R)\u0000\u01b0\u01b1\u0005(\u0000\u0000\u01b1\u01b3\u0003R)\u0000\u01b2"+
		"\u01b0\u0001\u0000\u0000\u0000\u01b3\u01b6\u0001\u0000\u0000\u0000\u01b4"+
		"\u01b2\u0001\u0000\u0000\u0000\u01b4\u01b5\u0001\u0000\u0000\u0000\u01b5"+
		"\u01bb\u0001\u0000\u0000\u0000\u01b6\u01b4\u0001\u0000\u0000\u0000\u01b7"+
		"\u01b8\u0003,\u0016\u0000\u01b8\u01b9\u0003N\'\u0000\u01b9\u01bb\u0001"+
		"\u0000\u0000\u0000\u01ba\u01af\u0001\u0000\u0000\u0000\u01ba\u01b7\u0001"+
		"\u0000\u0000\u0000\u01bbQ\u0001\u0000\u0000\u0000\u01bc\u01bd\u0005/\u0000"+
		"\u0000\u01bd\u01be\u0005\u0019\u0000\u0000\u01be\u01c1\u0003,\u0016\u0000"+
		"\u01bf\u01c1\u0003,\u0016\u0000\u01c0\u01bc\u0001\u0000\u0000\u0000\u01c0"+
		"\u01bf\u0001\u0000\u0000\u0000\u01c1S\u0001\u0000\u0000\u00005VX_jou|"+
		"\u0083\u0089\u0090\u0096\u009d\u00a7\u00aa\u00b3\u00bb\u00c1\u00c9\u00cd"+
		"\u00da\u00e0\u00f3\u00f8\u0103\u010b\u0111\u0119\u0123\u012b\u0131\u0136"+
		"\u013c\u0141\u014a\u014e\u0153\u0158\u0160\u0165\u016f\u0173\u0175\u017f"+
		"\u0183\u0185\u018c\u019a\u019e\u01a5\u01ad\u01b4\u01ba\u01c0";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}