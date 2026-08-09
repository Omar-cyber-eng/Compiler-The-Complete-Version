parser grammar PythonSubsetParser;

@header {
    package compiler.parser;
}

options {
	tokenVocab = PythonSubsetLexer;
}

// Program
file_input: (NEWLINE | stmt)* EOF;

// Statements
stmt: simple_stmt | compound_stmt;

simple_stmt: small_stmt NEWLINE;

small_stmt:
	expr_stmt
	| assign_stmt
	| return_stmt
	| global_stmt
	| import_stmt
	| from_import_stmt;

compound_stmt: function_def | if_stmt | for_stmt;

// Import
import_stmt: IMPORT dotted_name (AS NAME)?;
from_import_stmt:
	FROM dotted_name IMPORT (STAR | import_as_names);
import_as_names: import_as_name (COMMA import_as_name)*;
import_as_name: NAME (AS NAME)?;
dotted_name: NAME (DOT NAME)*;

// ✅ FIX 1: Decorator يقبل @name.name.name(...)
function_def:
	decorator* DEF NAME LPAREN parameters? RPAREN COLON suite;

decorator:
	AT decorator_name (LPAREN arguments? RPAREN)? NEWLINE;

decorator_name: NAME (DOT NAME)*;

parameters: parameter (COMMA parameter)*;
parameter: NAME (EQUAL test)?;

suite: simple_stmt | NEWLINE INDENT stmt+ DEDENT;

// Control flow
if_stmt:
	IF test COLON suite (ELIF test COLON suite)* (
		ELSE COLON suite
	)?;

for_stmt: FOR NAME IN test COLON suite;

// Assignment
assign_stmt: NAME EQUAL test;

global_stmt: GLOBAL NAME (COMMA NAME)*;

return_stmt: RETURN test?;

expr_stmt: test;

// Test expression
test: or_test;

or_test: and_test (OR and_test)*;

and_test: not_test (AND not_test)*;

not_test: NOT not_test | comparison;

comparison: expr (comp_op expr)*;

comp_op:
	LESS
	| GREATER
	| EQEQUAL
	| GREATEREQUAL
	| LESSEQUAL
	| NOTEQUAL
	| IN;

expr: term ((PLUS | MINUS) term)*;

term: factor ((STAR | SLASH | PERCENT | DOUBLESLASH) factor)*;

factor: (PLUS | MINUS) factor | power;

power: atom_expr (DOUBLESTAR factor)?;

atom_expr: atom trailer*;

trailer:
	LPAREN arguments? RPAREN	# CallTrailer
	| LBRACK test RBRACK		# IndexTrailer
	| DOT NAME					# AttrTrailer;

atom:
	LPAREN testlist_comp? RPAREN	# ParenAtom
	| LBRACK listmaker? RBRACK		# ListAtom
	| LBRACE dictorsetmaker? RBRACE	# DictAtom
	| NAME							# NameAtom
	| NUMBER						# NumberAtom
	| STRING+						# StringAtom
	| TRUE							# TrueAtom
	| FALSE							# FalseAtom
	| NONE							# NoneAtom;

// ✅ FIX 2: List comprehension داخل []
listmaker:
	test comp_for // [x for x in y if ...]
	| test (COMMA test)* COMMA?; // [x, y, z]

// ✅ FIX 3: Generator expression داخل ()
testlist_comp:
	test comp_for // (x for x in y if ...)
	| test (COMMA test)* COMMA?; // (x, y, z)

testlist: test (COMMA test)*;

// Dictionary
dictorsetmaker: (test COLON test) (COMMA (test COLON test))* COMMA? // {k:v, k:v}
	| test COLON test comp_for; // {k:v for ...}

// ✅ FIX 4: comp_for يدعم شرط IF اختياري
comp_for: FOR NAME IN or_test (IF or_test)?;

// Function arguments - يدعم generator expression
arguments:
	argument (COMMA argument)*
	| test comp_for; // next(x for x in y)

argument:
	NAME EQUAL test	# KeywordArgument
	| test			# PositionalArgument;