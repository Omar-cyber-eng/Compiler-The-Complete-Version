// Generated from C:/Users/dell/IdeaProjects/TemplateCompiler2/src/compiler/grammar/HTMLLexer.g4 by ANTLR 4.13.2

    package compiler.parser;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class HTMLLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		HTML_DOCTYPE=1, HTML_TAG_OPEN=2, HTML_TAG_CLOSE=3, HTML_COMMENT=4, HTML_TEXT=5, 
		HTML_WS=6, TAG_GT=7, TAG_SLASH_GT=8, TAG_ATTR_NAME=9, TAG_EQ=10, TAG_ATTR_VALUE=11, 
		TAG_WS=12;
	public static final int
		TAG_MODE=1;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE", "TAG_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"HTML_DOCTYPE", "HTML_TAG_OPEN", "HTML_TAG_CLOSE", "HTML_COMMENT", "HTML_TEXT", 
			"HTML_WS", "TAG_GT", "TAG_SLASH_GT", "TAG_ATTR_NAME", "TAG_EQ", "TAG_ATTR_VALUE", 
			"TAG_WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, "'>'", "'/>'", null, "'='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "HTML_DOCTYPE", "HTML_TAG_OPEN", "HTML_TAG_CLOSE", "HTML_COMMENT", 
			"HTML_TEXT", "HTML_WS", "TAG_GT", "TAG_SLASH_GT", "TAG_ATTR_NAME", "TAG_EQ", 
			"TAG_ATTR_VALUE", "TAG_WS"
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


	public HTMLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "HTMLLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\f\u0088\u0006\uffff\uffff\u0006\uffff\uffff\u0002\u0000\u0007"+
		"\u0000\u0002\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007"+
		"\u0003\u0002\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007"+
		"\u0006\u0002\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n"+
		"\u0007\n\u0002\u000b\u0007\u000b\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0005\u0000&\b\u0000\n\u0000\f\u0000)\t\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u00010\b"+
		"\u0001\n\u0001\f\u00013\t\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0005\u0002<\b\u0002\n\u0002"+
		"\f\u0002?\t\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003I\b\u0003\n\u0003"+
		"\f\u0003L\t\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0004\u0004\u0004S\b\u0004\u000b\u0004\f\u0004T\u0001\u0005\u0004\u0005"+
		"X\b\u0005\u000b\u0005\f\u0005Y\u0001\u0005\u0001\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\b\u0001\b\u0005\bi\b\b\n\b\f\bl\t\b\u0001\t\u0001"+
		"\t\u0001\n\u0001\n\u0005\nr\b\n\n\n\f\nu\t\n\u0001\n\u0001\n\u0001\n\u0005"+
		"\nz\b\n\n\n\f\n}\t\n\u0001\n\u0003\n\u0080\b\n\u0001\u000b\u0004\u000b"+
		"\u0083\b\u000b\u000b\u000b\f\u000b\u0084\u0001\u000b\u0001\u000b\u0002"+
		"\'J\u0000\f\u0002\u0001\u0004\u0002\u0006\u0003\b\u0004\n\u0005\f\u0006"+
		"\u000e\u0007\u0010\b\u0012\t\u0014\n\u0016\u000b\u0018\f\u0002\u0000\u0001"+
		"\u0007\u0002\u0000AZaz\u0003\u000009AZaz\u0001\u0000<<\u0003\u0000\t\n"+
		"\r\r  \u0005\u0000--09AZ__az\u0001\u0000\"\"\u0001\u0000\'\'\u0091\u0000"+
		"\u0002\u0001\u0000\u0000\u0000\u0000\u0004\u0001\u0000\u0000\u0000\u0000"+
		"\u0006\u0001\u0000\u0000\u0000\u0000\b\u0001\u0000\u0000\u0000\u0000\n"+
		"\u0001\u0000\u0000\u0000\u0000\f\u0001\u0000\u0000\u0000\u0001\u000e\u0001"+
		"\u0000\u0000\u0000\u0001\u0010\u0001\u0000\u0000\u0000\u0001\u0012\u0001"+
		"\u0000\u0000\u0000\u0001\u0014\u0001\u0000\u0000\u0000\u0001\u0016\u0001"+
		"\u0000\u0000\u0000\u0001\u0018\u0001\u0000\u0000\u0000\u0002\u001a\u0001"+
		"\u0000\u0000\u0000\u0004,\u0001\u0000\u0000\u0000\u00066\u0001\u0000\u0000"+
		"\u0000\bB\u0001\u0000\u0000\u0000\nR\u0001\u0000\u0000\u0000\fW\u0001"+
		"\u0000\u0000\u0000\u000e]\u0001\u0000\u0000\u0000\u0010a\u0001\u0000\u0000"+
		"\u0000\u0012f\u0001\u0000\u0000\u0000\u0014m\u0001\u0000\u0000\u0000\u0016"+
		"\u007f\u0001\u0000\u0000\u0000\u0018\u0082\u0001\u0000\u0000\u0000\u001a"+
		"\u001b\u0005<\u0000\u0000\u001b\u001c\u0005!\u0000\u0000\u001c\u001d\u0005"+
		"D\u0000\u0000\u001d\u001e\u0005O\u0000\u0000\u001e\u001f\u0005C\u0000"+
		"\u0000\u001f \u0005T\u0000\u0000 !\u0005Y\u0000\u0000!\"\u0005P\u0000"+
		"\u0000\"#\u0005E\u0000\u0000#\'\u0001\u0000\u0000\u0000$&\t\u0000\u0000"+
		"\u0000%$\u0001\u0000\u0000\u0000&)\u0001\u0000\u0000\u0000\'(\u0001\u0000"+
		"\u0000\u0000\'%\u0001\u0000\u0000\u0000(*\u0001\u0000\u0000\u0000)\'\u0001"+
		"\u0000\u0000\u0000*+\u0005>\u0000\u0000+\u0003\u0001\u0000\u0000\u0000"+
		",-\u0005<\u0000\u0000-1\u0007\u0000\u0000\u0000.0\u0007\u0001\u0000\u0000"+
		"/.\u0001\u0000\u0000\u000003\u0001\u0000\u0000\u00001/\u0001\u0000\u0000"+
		"\u000012\u0001\u0000\u0000\u000024\u0001\u0000\u0000\u000031\u0001\u0000"+
		"\u0000\u000045\u0006\u0001\u0000\u00005\u0005\u0001\u0000\u0000\u0000"+
		"67\u0005<\u0000\u000078\u0005/\u0000\u000089\u0001\u0000\u0000\u00009"+
		"=\u0007\u0000\u0000\u0000:<\u0007\u0001\u0000\u0000;:\u0001\u0000\u0000"+
		"\u0000<?\u0001\u0000\u0000\u0000=;\u0001\u0000\u0000\u0000=>\u0001\u0000"+
		"\u0000\u0000>@\u0001\u0000\u0000\u0000?=\u0001\u0000\u0000\u0000@A\u0005"+
		">\u0000\u0000A\u0007\u0001\u0000\u0000\u0000BC\u0005<\u0000\u0000CD\u0005"+
		"!\u0000\u0000DE\u0005-\u0000\u0000EF\u0005-\u0000\u0000FJ\u0001\u0000"+
		"\u0000\u0000GI\t\u0000\u0000\u0000HG\u0001\u0000\u0000\u0000IL\u0001\u0000"+
		"\u0000\u0000JK\u0001\u0000\u0000\u0000JH\u0001\u0000\u0000\u0000KM\u0001"+
		"\u0000\u0000\u0000LJ\u0001\u0000\u0000\u0000MN\u0005-\u0000\u0000NO\u0005"+
		"-\u0000\u0000OP\u0005>\u0000\u0000P\t\u0001\u0000\u0000\u0000QS\b\u0002"+
		"\u0000\u0000RQ\u0001\u0000\u0000\u0000ST\u0001\u0000\u0000\u0000TR\u0001"+
		"\u0000\u0000\u0000TU\u0001\u0000\u0000\u0000U\u000b\u0001\u0000\u0000"+
		"\u0000VX\u0007\u0003\u0000\u0000WV\u0001\u0000\u0000\u0000XY\u0001\u0000"+
		"\u0000\u0000YW\u0001\u0000\u0000\u0000YZ\u0001\u0000\u0000\u0000Z[\u0001"+
		"\u0000\u0000\u0000[\\\u0006\u0005\u0001\u0000\\\r\u0001\u0000\u0000\u0000"+
		"]^\u0005>\u0000\u0000^_\u0001\u0000\u0000\u0000_`\u0006\u0006\u0002\u0000"+
		"`\u000f\u0001\u0000\u0000\u0000ab\u0005/\u0000\u0000bc\u0005>\u0000\u0000"+
		"cd\u0001\u0000\u0000\u0000de\u0006\u0007\u0002\u0000e\u0011\u0001\u0000"+
		"\u0000\u0000fj\u0007\u0000\u0000\u0000gi\u0007\u0004\u0000\u0000hg\u0001"+
		"\u0000\u0000\u0000il\u0001\u0000\u0000\u0000jh\u0001\u0000\u0000\u0000"+
		"jk\u0001\u0000\u0000\u0000k\u0013\u0001\u0000\u0000\u0000lj\u0001\u0000"+
		"\u0000\u0000mn\u0005=\u0000\u0000n\u0015\u0001\u0000\u0000\u0000os\u0005"+
		"\"\u0000\u0000pr\b\u0005\u0000\u0000qp\u0001\u0000\u0000\u0000ru\u0001"+
		"\u0000\u0000\u0000sq\u0001\u0000\u0000\u0000st\u0001\u0000\u0000\u0000"+
		"tv\u0001\u0000\u0000\u0000us\u0001\u0000\u0000\u0000v\u0080\u0005\"\u0000"+
		"\u0000w{\u0005\'\u0000\u0000xz\b\u0006\u0000\u0000yx\u0001\u0000\u0000"+
		"\u0000z}\u0001\u0000\u0000\u0000{y\u0001\u0000\u0000\u0000{|\u0001\u0000"+
		"\u0000\u0000|~\u0001\u0000\u0000\u0000}{\u0001\u0000\u0000\u0000~\u0080"+
		"\u0005\'\u0000\u0000\u007fo\u0001\u0000\u0000\u0000\u007fw\u0001\u0000"+
		"\u0000\u0000\u0080\u0017\u0001\u0000\u0000\u0000\u0081\u0083\u0007\u0003"+
		"\u0000\u0000\u0082\u0081\u0001\u0000\u0000\u0000\u0083\u0084\u0001\u0000"+
		"\u0000\u0000\u0084\u0082\u0001\u0000\u0000\u0000\u0084\u0085\u0001\u0000"+
		"\u0000\u0000\u0085\u0086\u0001\u0000\u0000\u0000\u0086\u0087\u0006\u000b"+
		"\u0003\u0000\u0087\u0019\u0001\u0000\u0000\u0000\r\u0000\u0001\'1=JTY"+
		"js{\u007f\u0084\u0004\u0005\u0001\u0000\u0000\u0001\u0000\u0004\u0000"+
		"\u0000\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}