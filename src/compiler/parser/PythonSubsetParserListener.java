// Generated from src/compiler/grammar/PythonSubsetParser.g4 by ANTLR 4.13.2

    package compiler.parser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PythonSubsetParser}.
 */
public interface PythonSubsetParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PythonSubsetParser#file_input}.
	 * @param ctx the parse tree
	 */
	void enterFile_input(PythonSubsetParser.File_inputContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonSubsetParser#file_input}.
	 * @param ctx the parse tree
	 */
	void exitFile_input(PythonSubsetParser.File_inputContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonSubsetParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(PythonSubsetParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonSubsetParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(PythonSubsetParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonSubsetParser#simple_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSimple_stmt(PythonSubsetParser.Simple_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonSubsetParser#simple_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSimple_stmt(PythonSubsetParser.Simple_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonSubsetParser#small_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSmall_stmt(PythonSubsetParser.Small_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonSubsetParser#small_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSmall_stmt(PythonSubsetParser.Small_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonSubsetParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void enterCompound_stmt(PythonSubsetParser.Compound_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonSubsetParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void exitCompound_stmt(PythonSubsetParser.Compound_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonSubsetParser#import_stmt}.
	 * @param ctx the parse tree
	 */
	void enterImport_stmt(PythonSubsetParser.Import_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonSubsetParser#import_stmt}.
	 * @param ctx the parse tree
	 */
	void exitImport_stmt(PythonSubsetParser.Import_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonSubsetParser#from_import_stmt}.
	 * @param ctx the parse tree
	 */
	void enterFrom_import_stmt(PythonSubsetParser.From_import_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonSubsetParser#from_import_stmt}.
	 * @param ctx the parse tree
	 */
	void exitFrom_import_stmt(PythonSubsetParser.From_import_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonSubsetParser#import_as_names}.
	 * @param ctx the parse tree
	 */
	void enterImport_as_names(PythonSubsetParser.Import_as_namesContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonSubsetParser#import_as_names}.
	 * @param ctx the parse tree
	 */
	void exitImport_as_names(PythonSubsetParser.Import_as_namesContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonSubsetParser#import_as_name}.
	 * @param ctx the parse tree
	 */
	void enterImport_as_name(PythonSubsetParser.Import_as_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonSubsetParser#import_as_name}.
	 * @param ctx the parse tree
	 */
	void exitImport_as_name(PythonSubsetParser.Import_as_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonSubsetParser#dotted_name}.
	 * @param ctx the parse tree
	 */
	void enterDotted_name(PythonSubsetParser.Dotted_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonSubsetParser#dotted_name}.
	 * @param ctx the parse tree
	 */
	void exitDotted_name(PythonSubsetParser.Dotted_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonSubsetParser#function_def}.
	 * @param ctx the parse tree
	 */
	void enterFunction_def(PythonSubsetParser.Function_defContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonSubsetParser#function_def}.
	 * @param ctx the parse tree
	 */
	void exitFunction_def(PythonSubsetParser.Function_defContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonSubsetParser#decorator}.
	 * @param ctx the parse tree
	 */
	void enterDecorator(PythonSubsetParser.DecoratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonSubsetParser#decorator}.
	 * @param ctx the parse tree
	 */
	void exitDecorator(PythonSubsetParser.DecoratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonSubsetParser#decorator_name}.
	 * @param ctx the parse tree
	 */
	void enterDecorator_name(PythonSubsetParser.Decorator_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonSubsetParser#decorator_name}.
	 * @param ctx the parse tree
	 */
	void exitDecorator_name(PythonSubsetParser.Decorator_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonSubsetParser#parameters}.
	 * @param ctx the parse tree
	 */
	void enterParameters(PythonSubsetParser.ParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonSubsetParser#parameters}.
	 * @param ctx the parse tree
	 */
	void exitParameters(PythonSubsetParser.ParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonSubsetParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(PythonSubsetParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonSubsetParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(PythonSubsetParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonSubsetParser#suite}.
	 * @param ctx the parse tree
	 */
	void enterSuite(PythonSubsetParser.SuiteContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonSubsetParser#suite}.
	 * @param ctx the parse tree
	 */
	void exitSuite(PythonSubsetParser.SuiteContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonSubsetParser#if_stmt}.
	 * @param ctx the parse tree
	 */
	void enterIf_stmt(PythonSubsetParser.If_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonSubsetParser#if_stmt}.
	 * @param ctx the parse tree
	 */
	void exitIf_stmt(PythonSubsetParser.If_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonSubsetParser#for_stmt}.
	 * @param ctx the parse tree
	 */
	void enterFor_stmt(PythonSubsetParser.For_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonSubsetParser#for_stmt}.
	 * @param ctx the parse tree
	 */
	void exitFor_stmt(PythonSubsetParser.For_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonSubsetParser#assign_stmt}.
	 * @param ctx the parse tree
	 */
	void enterAssign_stmt(PythonSubsetParser.Assign_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonSubsetParser#assign_stmt}.
	 * @param ctx the parse tree
	 */
	void exitAssign_stmt(PythonSubsetParser.Assign_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonSubsetParser#global_stmt}.
	 * @param ctx the parse tree
	 */
	void enterGlobal_stmt(PythonSubsetParser.Global_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonSubsetParser#global_stmt}.
	 * @param ctx the parse tree
	 */
	void exitGlobal_stmt(PythonSubsetParser.Global_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonSubsetParser#return_stmt}.
	 * @param ctx the parse tree
	 */
	void enterReturn_stmt(PythonSubsetParser.Return_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonSubsetParser#return_stmt}.
	 * @param ctx the parse tree
	 */
	void exitReturn_stmt(PythonSubsetParser.Return_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonSubsetParser#expr_stmt}.
	 * @param ctx the parse tree
	 */
	void enterExpr_stmt(PythonSubsetParser.Expr_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonSubsetParser#expr_stmt}.
	 * @param ctx the parse tree
	 */
	void exitExpr_stmt(PythonSubsetParser.Expr_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonSubsetParser#test}.
	 * @param ctx the parse tree
	 */
	void enterTest(PythonSubsetParser.TestContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonSubsetParser#test}.
	 * @param ctx the parse tree
	 */
	void exitTest(PythonSubsetParser.TestContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonSubsetParser#or_test}.
	 * @param ctx the parse tree
	 */
	void enterOr_test(PythonSubsetParser.Or_testContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonSubsetParser#or_test}.
	 * @param ctx the parse tree
	 */
	void exitOr_test(PythonSubsetParser.Or_testContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonSubsetParser#and_test}.
	 * @param ctx the parse tree
	 */
	void enterAnd_test(PythonSubsetParser.And_testContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonSubsetParser#and_test}.
	 * @param ctx the parse tree
	 */
	void exitAnd_test(PythonSubsetParser.And_testContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonSubsetParser#not_test}.
	 * @param ctx the parse tree
	 */
	void enterNot_test(PythonSubsetParser.Not_testContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonSubsetParser#not_test}.
	 * @param ctx the parse tree
	 */
	void exitNot_test(PythonSubsetParser.Not_testContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonSubsetParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterComparison(PythonSubsetParser.ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonSubsetParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitComparison(PythonSubsetParser.ComparisonContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonSubsetParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void enterComp_op(PythonSubsetParser.Comp_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonSubsetParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void exitComp_op(PythonSubsetParser.Comp_opContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonSubsetParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(PythonSubsetParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonSubsetParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(PythonSubsetParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonSubsetParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(PythonSubsetParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonSubsetParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(PythonSubsetParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonSubsetParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(PythonSubsetParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonSubsetParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(PythonSubsetParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonSubsetParser#power}.
	 * @param ctx the parse tree
	 */
	void enterPower(PythonSubsetParser.PowerContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonSubsetParser#power}.
	 * @param ctx the parse tree
	 */
	void exitPower(PythonSubsetParser.PowerContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonSubsetParser#atom_expr}.
	 * @param ctx the parse tree
	 */
	void enterAtom_expr(PythonSubsetParser.Atom_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonSubsetParser#atom_expr}.
	 * @param ctx the parse tree
	 */
	void exitAtom_expr(PythonSubsetParser.Atom_exprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CallTrailer}
	 * labeled alternative in {@link PythonSubsetParser#trailer}.
	 * @param ctx the parse tree
	 */
	void enterCallTrailer(PythonSubsetParser.CallTrailerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CallTrailer}
	 * labeled alternative in {@link PythonSubsetParser#trailer}.
	 * @param ctx the parse tree
	 */
	void exitCallTrailer(PythonSubsetParser.CallTrailerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IndexTrailer}
	 * labeled alternative in {@link PythonSubsetParser#trailer}.
	 * @param ctx the parse tree
	 */
	void enterIndexTrailer(PythonSubsetParser.IndexTrailerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IndexTrailer}
	 * labeled alternative in {@link PythonSubsetParser#trailer}.
	 * @param ctx the parse tree
	 */
	void exitIndexTrailer(PythonSubsetParser.IndexTrailerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AttrTrailer}
	 * labeled alternative in {@link PythonSubsetParser#trailer}.
	 * @param ctx the parse tree
	 */
	void enterAttrTrailer(PythonSubsetParser.AttrTrailerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AttrTrailer}
	 * labeled alternative in {@link PythonSubsetParser#trailer}.
	 * @param ctx the parse tree
	 */
	void exitAttrTrailer(PythonSubsetParser.AttrTrailerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParenAtom}
	 * labeled alternative in {@link PythonSubsetParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterParenAtom(PythonSubsetParser.ParenAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParenAtom}
	 * labeled alternative in {@link PythonSubsetParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitParenAtom(PythonSubsetParser.ParenAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ListAtom}
	 * labeled alternative in {@link PythonSubsetParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterListAtom(PythonSubsetParser.ListAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ListAtom}
	 * labeled alternative in {@link PythonSubsetParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitListAtom(PythonSubsetParser.ListAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DictAtom}
	 * labeled alternative in {@link PythonSubsetParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterDictAtom(PythonSubsetParser.DictAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DictAtom}
	 * labeled alternative in {@link PythonSubsetParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitDictAtom(PythonSubsetParser.DictAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NameAtom}
	 * labeled alternative in {@link PythonSubsetParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterNameAtom(PythonSubsetParser.NameAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NameAtom}
	 * labeled alternative in {@link PythonSubsetParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitNameAtom(PythonSubsetParser.NameAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumberAtom}
	 * labeled alternative in {@link PythonSubsetParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterNumberAtom(PythonSubsetParser.NumberAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumberAtom}
	 * labeled alternative in {@link PythonSubsetParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitNumberAtom(PythonSubsetParser.NumberAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StringAtom}
	 * labeled alternative in {@link PythonSubsetParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterStringAtom(PythonSubsetParser.StringAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringAtom}
	 * labeled alternative in {@link PythonSubsetParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitStringAtom(PythonSubsetParser.StringAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TrueAtom}
	 * labeled alternative in {@link PythonSubsetParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterTrueAtom(PythonSubsetParser.TrueAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TrueAtom}
	 * labeled alternative in {@link PythonSubsetParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitTrueAtom(PythonSubsetParser.TrueAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FalseAtom}
	 * labeled alternative in {@link PythonSubsetParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterFalseAtom(PythonSubsetParser.FalseAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FalseAtom}
	 * labeled alternative in {@link PythonSubsetParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitFalseAtom(PythonSubsetParser.FalseAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NoneAtom}
	 * labeled alternative in {@link PythonSubsetParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterNoneAtom(PythonSubsetParser.NoneAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NoneAtom}
	 * labeled alternative in {@link PythonSubsetParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitNoneAtom(PythonSubsetParser.NoneAtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonSubsetParser#listmaker}.
	 * @param ctx the parse tree
	 */
	void enterListmaker(PythonSubsetParser.ListmakerContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonSubsetParser#listmaker}.
	 * @param ctx the parse tree
	 */
	void exitListmaker(PythonSubsetParser.ListmakerContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonSubsetParser#testlist_comp}.
	 * @param ctx the parse tree
	 */
	void enterTestlist_comp(PythonSubsetParser.Testlist_compContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonSubsetParser#testlist_comp}.
	 * @param ctx the parse tree
	 */
	void exitTestlist_comp(PythonSubsetParser.Testlist_compContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonSubsetParser#testlist}.
	 * @param ctx the parse tree
	 */
	void enterTestlist(PythonSubsetParser.TestlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonSubsetParser#testlist}.
	 * @param ctx the parse tree
	 */
	void exitTestlist(PythonSubsetParser.TestlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonSubsetParser#dictorsetmaker}.
	 * @param ctx the parse tree
	 */
	void enterDictorsetmaker(PythonSubsetParser.DictorsetmakerContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonSubsetParser#dictorsetmaker}.
	 * @param ctx the parse tree
	 */
	void exitDictorsetmaker(PythonSubsetParser.DictorsetmakerContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonSubsetParser#comp_for}.
	 * @param ctx the parse tree
	 */
	void enterComp_for(PythonSubsetParser.Comp_forContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonSubsetParser#comp_for}.
	 * @param ctx the parse tree
	 */
	void exitComp_for(PythonSubsetParser.Comp_forContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonSubsetParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(PythonSubsetParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonSubsetParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(PythonSubsetParser.ArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code KeywordArgument}
	 * labeled alternative in {@link PythonSubsetParser#argument}.
	 * @param ctx the parse tree
	 */
	void enterKeywordArgument(PythonSubsetParser.KeywordArgumentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code KeywordArgument}
	 * labeled alternative in {@link PythonSubsetParser#argument}.
	 * @param ctx the parse tree
	 */
	void exitKeywordArgument(PythonSubsetParser.KeywordArgumentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PositionalArgument}
	 * labeled alternative in {@link PythonSubsetParser#argument}.
	 * @param ctx the parse tree
	 */
	void enterPositionalArgument(PythonSubsetParser.PositionalArgumentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PositionalArgument}
	 * labeled alternative in {@link PythonSubsetParser#argument}.
	 * @param ctx the parse tree
	 */
	void exitPositionalArgument(PythonSubsetParser.PositionalArgumentContext ctx);
}