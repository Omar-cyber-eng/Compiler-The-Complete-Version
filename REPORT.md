# Project Report — A Compiler for Flask / Jinja2 / HTML / CSS

**Repository:** https://github.com/Omar-cyber-eng/Compiler-The-Complete-Version
**Course:** Compilers (مشروع المترجمات) — 2025/2026
**Language of implementation:** Java · **Parser generator:** ANTLR 4.13.2

---

## 1. What the project is

The project is a compiler (translator) that reads the source files of a small
Flask web application and produces a working static web interface from them.

It takes three kinds of input:

| Input | Example | Role |
| --- | --- | --- |
| A Flask back-end file | `app.py` | Holds the data (`products`) and the routes |
| Jinja2 templates | `templates/*.jinja` | The page layout with `{{ }}` and `{% %}` |
| Styling files | `resources/css/style.css`, `resources/js/script.js` | Presentation, copied unprocessed |

and it runs them through the classical compiler stages:

```
       app.py                       templates/*.jinja
          |                                  |
   Python Lexer  (+ indentation)      Template Lexer (multi-mode)
          |                                  |
   Python Parser                      Template Parser
          |                                  |
   Python AST  ──────────┐            Jinja AST
          |              │                   |
   Symbol Table          │            Jinja Semantic Analysis
          |              │                   |
   Python Semantic       │                   |
   Analysis              │                   |
          |              │                   |
          └──── Generator (context data) ────┘
                         |
                    HTML pages
                         |
                     Browser
```

The essential idea of the project is the arrow in the middle: the **data array
defined in the Python tree is passed into the Jinja tree**, and the generator
replaces `{{ product.name }}` with the real values, producing final HTML.

Two families of output are produced:

* `src/compiler/main/test_app/output/` — the generated pages
  (`list_products.html`, `add_product.html`, `product_detail.html`) plus the
  support files copied unchanged (`app.py`, `style.css`, `script.js`).
* `compiler_output/` — the artifacts of the analysis stages:
  `ast_python.json`, `ast_jinja.json`, `semantic_report.txt`,
  `generation_log.txt`.

---

## 2. Technologies used

### 2.1 Java

The whole compiler is written in Java. It was built and tested on **Temurin
JDK 25**, and it needs **JDK 17 or newer** (it uses pattern matching for
`instanceof`, `var`, `List.of`, and `Files.writeString`).

Java was a natural fit for this project because the compiler is built almost
entirely out of class hierarchies and the visitor pattern — the AST is an
inheritance tree, and every stage (printing, JSON export, symbol collection,
semantic analysis) is a different visitor over the same tree.

### 2.2 ANTLR 4.13.2

ANTLR (ANother Tool for Language Recognition) is the parser generator used for
all four input languages. The complete jar is vendored in `lib/`, so the
project builds with no downloads and no package manager.

For every grammar file `X.g4`, ANTLR generates a lexer or parser class, plus a
**visitor** and a **listener** interface. This project uses the *visitor* mode:
each `XBaseVisitor` is subclassed by a hand-written AST builder, which
translates ANTLR's parse tree into our own AST.

Regenerating the parsers after a grammar change:

```bash
java -jar lib/antlr-4.13.2-complete.jar -o src/compiler/parser \
  -lib src/compiler/parser -visitor src/compiler/grammar/TemplateLexer.g4
```

### 2.3 `com.sun.net.httpserver`

The runtime web application is served by the HTTP server built into the JDK.
No Tomcat, no Spring, no external library — the project has **zero runtime
dependencies** besides the ANTLR jar.

### 2.4 Supporting tools

* **Git / GitHub** — version control and submission link.
* **Mermaid** — the AST diagrams in `AST_DIAGRAM.md` and `docs/ast/*.mmd`
  render directly on GitHub and in VS Code.
* **Plain `javac`** — no Maven or Gradle; a single compile command builds the
  72 source files.

### 2.5 Input languages the compiler understands

| Language | Grammar files | Notes |
| --- | --- | --- |
| Python (subset) | `PythonSubsetLexer.g4`, `PythonSubsetParser.g4` | Flask-oriented subset |
| Jinja2 + HTML | `TemplateLexer.g4`, `TemplateParser.g4` | One grammar handles both, since they interleave |
| HTML (standalone) | `HTMLLexer.g4`, `HTMLParser.g4` | Kept from the first semester |
| CSS | `CSSLexer.g4`, `CSSParser.g4` | Rules, selectors, declarations, `@media`, `@import` |

---

## 3. Repository layout

| Package | Files | Role |
| --- | --- | --- |
| `compiler/grammar` | 8 | The `.g4` grammar definitions |
| `compiler/parser` | 32 | ANTLR-generated lexers, parsers, visitors, listeners |
| `compiler/lexer` | 1 | `PythonIndentingLexer` — indentation handling |
| `compiler/ast` | 32 | `AstNode` hierarchy (29 classes) + 3 tree visitors |
| `compiler/visitors` | 3 | AST builders: Python, Template, CSS |
| `compiler/symbol` | 3 | `Symbol`, `Scope`, `SymbolTableVisitor` |
| `compiler/semantic` | 3 | Python analyzer, Jinja analyzer, template-context builder |
| `compiler/codegen` | 1 | `CodeGenerator` — data extraction + HTML generation |
| `compiler/main` | 20 | Entry points, report writer, demo application |
| `compiler/test` | 1 | `CompilerTests` — the verification suite |

---

## 4. Stage 1 — Lexical analysis (التحليل اللفظي)

### 4.1 The Python indentation problem

Python has no braces: block structure is expressed by indentation. A
context-free grammar cannot describe that, so ANTLR alone is not enough.

`compiler/lexer/PythonIndentingLexer.java` wraps the generated
`PythonSubsetLexer` and synthesises the missing tokens:

* It keeps an **indentation stack** initialised to `0`.
* At the start of every logical line it compares the new indentation with the
  top of the stack, and emits an **`INDENT`** token when it grows or one or
  more **`DEDENT`** tokens when it shrinks.
* At end of file it emits a `DEDENT` for every level still open, so all blocks
  are closed.
* It tracks bracket depth for `(`, `[`, `{` so that **implicit line joining**
  works — a list literal spread over many lines produces no `NEWLINE` tokens.
* Blank lines and comment-only lines do not affect indentation.

The parser can then write the familiar rule:

```antlr
suite: simple_stmt | NEWLINE INDENT stmt+ DEDENT;
```

### 4.2 Lexer modes for templates

A `.jinja` file is three languages at once — HTML markup, Jinja expressions,
and attribute values that may contain Jinja. The same character means
different things in each. `TemplateLexer.g4` solves this with **six lexical
contexts**: the default mode plus five explicit ones.

| Mode | Entered by | Purpose |
| --- | --- | --- |
| *(default)* | — | Text, `<tag`, `{{`, `{%`, `{#`, `<!DOCTYPE` |
| `TAG_MODE` | `<div`, `<img` | Attribute names, `=`, `>`, `/>` |
| `TAG_ATTR_DQ_MODE` | `"` inside a tag | Attribute value in double quotes |
| `TAG_ATTR_SQ_MODE` | `'` inside a tag | Attribute value in single quotes |
| `JINJA_MODE` | `{{` or `{%` | Keywords, names, operators, filters |
| `COMMENT_MODE` | `{#` | Comment text up to `#}` |

This is what allows `href="/products/{{ product.id }}"` to be tokenised
correctly: the lexer is inside the attribute mode, sees `{{`, pushes the Jinja
mode, and pops back when it reads `}}`.

Void elements (`img`, `br`, `input`, `link`, …) are recognised by a dedicated
`VOID_OPEN` token, so the parser never expects a closing tag for them.

---

## 5. Stage 2 — Syntax analysis (التحليل النحوي)

### 5.1 The Python subset

`PythonSubsetParser.g4` covers what a Flask application actually uses:

* **Statements** — assignment, `return`, `global`, `import`, `from … import`,
  `def` with decorators, `if / elif / else`, `for`.
* **Decorators** with dotted names and arguments, i.e. `@app.route("/products",
  methods=["GET", "POST"])`.
* **Expressions** with a full precedence chain: `or` → `and` → `not` →
  comparison → `+ -` → `* / % //` → unary → `**` → atoms with trailers.
* **Trailers** — calls `f(x)`, indexing `a[i]`, attribute access `a.b`.
* **Literals** — numbers, strings, `True`, `False`, `None`, lists, dicts.
* **Comprehensions** — list comprehensions, generator expressions, and dict
  comprehensions, each with an optional `if` condition. These matter because
  the demo app uses `[p for p in products if p["id"] != pid]` and
  `next(p for p in products if p["id"] == pid)`.
* **Keyword arguments**, labelled `KeywordArgument`, which is precisely how
  `render_template("x.jinja", products=products)` is later understood.

### 5.2 The template grammar

```antlr
content
    : html_element  | text_content | jinja_variable
    | jinja_for     | jinja_if     | jinja_comment | DOCTYPE ;
```

* **Elements** in three shapes: normal (`<p>…</p>`), self-closing, and void.
* **Attributes** either static, or dynamic with an embedded `{{ … }}` in
  double or single quotes.
* **`{{ expr | filter | filter }}`** with chained filters that may take
  arguments.
* **`{% for x in expr %}` … `{% endfor %}`**.
* **`{% if %}` / `{% elif %}` / `{% else %}` / `{% endif %}`**, where the
  bodies are captured as `content_block` so nesting works.
* **`{# comment #}`**.
* A Jinja expression grammar with comparison, `and`, `or`, `not`, attribute
  access, subscripting, function calls, parentheses, and literals.

### 5.3 CSS

`CSSParser.g4` handles rule sets, selector groups, the four combinators
(descendant, child `>`, adjacent `+`, general sibling `~`), class / id /
pseudo / attribute modifiers, declarations with or without a trailing
semicolon, `@import`, and `@media`.

### 5.4 Error reporting

After every parse the driver checks `parser.getNumberOfSyntaxErrors()` and
reports the file as failing rather than building a tree from broken input.

---

## 6. Stage 3 — The abstract syntax trees (بناء الأشجار)

### 6.1 Node design — OOP, inheritance, polymorphism

Every node inherits from the abstract class `AstNode`, which stores exactly
what the project requires of a node: **its name, its line number, and its
children**.

```java
public abstract class AstNode {
    protected final String nodeName;
    protected final int line;
    protected final List<AstNode> children = new ArrayList<>();

    public abstract <R> R accept(AstVisitor<R> visitor);   // polymorphism
}
```

Two abstract classes refine it — `ExprNode` (expressions) and `StmtNode`
(statements) — and **26 concrete node classes** extend those:

* **Python expressions:** `NameNode`, `NumberNode`, `StringNode`, `BinOpNode`,
  `CallNode`, `AttrAccessNode`, `ListNode`, `DictNode`
* **Python statements:** `AssignNode`, `ForNode`, `IfNode`, `DefNode`,
  `ReturnNode`, `SuiteNode`, `ImportNode`, `GlobalNode`, `DecoratorNode`,
  `PythonFileNode`
* **Template:** `TemplateNode`, `TextNode`, `HtmlElementNode`,
  `JinjaExprNode`, `JinjaStmtNode`
* **CSS:** `CssStylesheetNode`, `CssRuleNode`, `CssDeclarationNode`

`accept()` is the polymorphic entry point: the caller holds an `AstNode`
reference, calls `accept(visitor)`, and the correct `visitXxx` method runs —
one interface, `AstVisitor<R>`, with **26 visit methods**, serves printing,
JSON export, symbol collection, and semantic analysis alike.

### 6.2 The two trees

* **Python AST** — built by `PythonAstBuilder` from `app.py`. Its root is
  `PythonFileNode`; assignments, functions and their suites hang beneath it.
* **Jinja AST** — built by `TemplateAstBuilder`, one tree per template. Its
  root is `TemplateNode`, and HTML elements, text, `{{ }}` expressions and
  `{% %}` statements nest inside it.

A third tree is built for CSS by `CssAstBuilder`.

A few Jinja constructs (`Subscript`, `Not`, keyword arguments) are represented
as anonymous `AstNode` subclasses identified by their node name; the printer
detects them with `getClass().isAnonymousClass()` and prints them through a
generic path.

### 6.3 Printing (المطلوب السابع)

* `PrintVisitor` prints any tree as an indented outline, each line carrying the
  node's name, its line number, and its distinguishing detail (tag name and
  attributes for elements, selector for CSS rules, operator for binary
  operations). `printTree()` writes to the console, `getTreeString()` returns
  it as text.
* `JsonExporter` writes the same trees as JSON — this is what produces
  `ast_python.json` and `ast_jinja.json`.
* `SymbolTableVisitor.printSymbolTable()` prints the scope tree with the
  symbols of each scope.

Example of the printed template tree:

```
Template (line 1)
  Text (line 1) "<!DOCTYPE html>"
  HtmlElement <html> (line 2)
    HtmlElement <head> (line 3)
      HtmlElement <link> (line 5) attrs=[rel="stylesheet" href="{{...}}" ]
        Call (line 5)
          Name "url_for" (line 5)
          String "static" (line 5)
          KwArg:filename (line 5)
            String "css/style.css" (line 5)
```

---

## 7. Stage 4 — The symbol table (جدول الرموز)

`Scope` is a node in a tree of scopes: it has a name, a parent, an ordered map
of symbols, and child scopes. `resolve()` searches the scope and then its
parents; `resolveLocal()` searches only the current scope; `getFullPath()`
returns a dotted path such as `global.delete_product`.

`Symbol` records a **name, a kind, a line number**, and an optional value. The
kinds used are `import`, `function`, `parameter`, `variable`,
`global_variable`, `global_ref` and `loop_variable`.

`SymbolTableVisitor` walks the Python AST and fills the table: functions are
declared in the enclosing scope and open a new scope for their body, parameters
are declared inside it, assignments declare variables, and a `global x`
statement records a reference in the function while leaving the original global
definition — and its line number — untouched.

Result for the demo application:

```
Scope: global
Symbols:
  from.flask: import (line 1)
  app: variable (line 3)
  products: variable (line 5)
  list_products: function (line 22)
  product_detail: function (line 26)
  add_product: function (line 31)
  delete_product: function (line 45)

  Scope: delete_product
  Symbols:
    pid: parameter (line 45)
    products: global_ref (line 47)
```

---

## 8. Stage 5 — Semantic analysis (التحليل الدلالي)

The requirement is at least five semantic errors handled **in both parts**.
The project detects **nine kinds in Python and five in Jinja**, and the demo
files exercise 10 Python findings across 8 kinds, plus all 5 Jinja kinds — 15
errors in total, written to `compiler_output/semantic_report.txt`.

### 8.1 Python — `SemanticAnalyzer`

It implements `AstVisitor<Void>` and works in two passes: a first pass over the
top level collects function names, their parameter counts, and imports (so a
function may be called before its definition), then a full traversal analyses
the body, entering and leaving a `Scope` for each function.

| # | Error | Triggered by |
| --- | --- | --- |
| 1 | `UNDEFINED_VAR` | A name that is not a local, a parameter, a global, an import, or a builtin |
| 2 | `UNDEFINED_FUNC` | A call to a function that was never defined or imported |
| 3 | `REDEFINE_FUNC` | Two `def`s with the same name |
| 4 | `WRONG_ARG_COUNT` | Call arity ≠ the definition's parameter count |
| 5 | `MISSING_RETURN` | A function with no `return` on any path |
| 6 | `REDEFINE_VAR` | A variable assigned twice in the same function scope |
| 7 | `UNUSED_VAR` | A variable declared in a function and never read |
| 8 | `DEAD_CODE` | Any statement after a `return` in the same block |
| 9 | `INVALID_ASSIGN` | An assignment whose target is not a name or an attribute |

Flask names (`request`, `render_template`, `redirect`, `url_for`, …) are
pre-registered so the real `app.py` analyses clean, which it does.

### 8.2 Jinja — `JinjaSemanticAnalyzer`

Templates are analysed against the set of variables the Python side actually
passes them.

| # | Error | Triggered by |
| --- | --- | --- |
| 1 | `UNDEFINED_VARIABLE` | `{{ username }}` when nothing passes `username` |
| 2 | `UNDEFINED_ITERABLE` | `{% for item in missing_list %}` |
| 3 | `LOOP_VAR_OUT_OF_SCOPE` | Using the loop variable after `{% endfor %}` |
| 4 | `UNKNOWN_FILTER` | `{{ x | wrongfilter }}` |
| 5 | `UNDEFINED_FUNCTION` | `{{ calculate_total() }}` |

The analyzer keeps a stack of active loop variables so that scoping is exact:
a name is in scope inside its `{% for %}` and out of scope after it. It also
checks the expressions that live **inside attribute values**, e.g.
`<a href="/products/{{ ghost.id }}">`, and the arguments of filters.

### 8.3 Connecting the two parts — `TemplateContextBuilder`

This class is the bridge that makes the Jinja analysis meaningful. It walks the
Python AST looking for `render_template("name.jinja", key=value, …)` calls and
records, per template, the set of variable names it receives. So
`product_detail.jinja` legitimately knows `product`, while a template that is
rendered with no arguments does not — and the analyzer reports accordingly.

---

## 9. Stage 6 — Code generation (تولید الکود)

`CodeGenerator` performs the two halves of the generation stage.

### 9.1 Extracting the data from the Python tree

`extractFromPythonAST` visits the top-level assignments and evaluates their
right-hand sides into ordinary Java values: strings, numbers, booleans, `null`,
`ArrayList` for list literals, `LinkedHashMap` for dict literals, and simple
arithmetic for binary operations. The `products` list of the demo application
becomes a `List<Map<String, Object>>` — the *context data*.

### 9.2 Evaluating the Jinja tree against that context

`generateForTemplate` merges the global context with any per-page extras (for
instance the single `product` shown on the detail page) and walks the template
tree:

* **`TextNode`** — emitted as-is.
* **`HtmlElementNode`** — the tag is re-emitted with its attributes in source
  order; void elements are closed correctly; each dynamic attribute resolves
  its own expressions in order.
* **`JinjaExprNode`** — the expression is evaluated, the chained filters are
  applied, and the result is **HTML-escaped**, exactly as Jinja2 does by
  default. `| safe` opts out.
* **`JinjaStmtNode`** — dispatched on its kind: `for` iterates the collection
  and exposes a `loop` object (`index`, `index0`, `revindex`, `first`, `last`,
  `length`); `if` evaluates its condition, then its `elif` clauses in order,
  then `else`.
* **Expressions** — names, attribute access (`product.name`), subscripting
  (`product["name"]`), comparisons, `and` / `or` / `not`, and function calls.
  `url_for('static', filename=…)` is resolved to a real `/static/...` path.

**27 filter cases** are implemented, including `upper`, `lower`, `capitalize`,
`title`, `trim`, `length`/`count`, `default`, `int`, `float`, `round`, `abs`,
`sum`, `join`, `replace`, `truncate`, `striptags`, `first`, `last`, `reverse`,
`sort`, `list`, `string`, `escape`/`e` and `safe`.

### 9.3 What generation produces

Given the two products in `app.py`, the list template generates:

```html
<ul><li><img src="/static/images/img.png" width="100">
<a href="/products/1">Laptop</a>
 - 1200 $ <form action="/delete/1" method="post" style="display:inline;">
<button type="submit">Delete</button>
</form>
</li>
```

`ReportGenerator` then writes the four files of `compiler_output/` and copies
the support files next to the generated pages.

---

## 10. The generated application

`compiler.main.WebServer` runs the result as a real site on port 8080, keeping
the product list in memory and regenerating every page from the Jinja AST on
each request.

| Route | Method | Behaviour |
| --- | --- | --- |
| `/` | GET | Redirects to `/products` |
| `/products` | GET | The product list |
| `/products/{id}` | GET | One product's details, 404 when it does not exist |
| `/add` | GET / POST | The form, and the validated insert |
| `/delete/{id}` | POST | Removes a product, then redirects |
| `/static/...` | GET | CSS, images and JavaScript, confined to `resources/` |

Adding a product validates that the name and details are present and that the
price is a number greater than zero, re-displaying the form with the messages
and the previous values when they are not.

**Regeneration.** Java is what listens for data changes: every successful add
or delete re-runs the generator and rewrites the files in `output/`, so the
generated pages always match the current data. The console shows it:

```
[REGEN] output/ regenerated (added product: Monitor) - products: 3
[REGEN] output/ regenerated (deleted product id=3) - products: 2
```

`compiler.main.WebServerJS` is the same server plus the browser-side answer to
the same question: it exposes `GET /api/products` and injects `script.js`,
which polls that endpoint and reloads the page as soon as the data changes.

---

## 11. Testing

`compiler.test.CompilerTests` is a self-contained suite of **70 assertions**
grouped by requirement — parsers and ASTs, node structure, symbol table, Python
semantics, Jinja semantics, data passing and generation, the generated pages,
and printing. It asserts both directions: that the error files produce the
expected diagnostics, and that the real application files produce **none**.

```bash
java -cp "out;lib/antlr-4.13.2-complete.jar" compiler.test.CompilerTests
# PASSED: 70 | FAILED: 0
```

---

## 12. Requirements coverage

| # | Requirement | Where it is satisfied |
| --- | --- | --- |
| 1 | Lexer & Parser for CSS, HTML, Jinja2, Python | 8 grammars in `compiler/grammar`, plus `PythonIndentingLexer` |
| 2 | Two ASTs, generator passing the data array | `PythonAstBuilder` + `TemplateAstBuilder`; `CodeGenerator.extractFromPythonAST` and `TemplateContextBuilder` |
| 3 | OOP nodes: inheritance, polymorphism, name + line | `AstNode` / `ExprNode` / `StmtNode` and 26 concrete classes; `accept()` with a 26-method visitor |
| 4 | At least 5 semantic errors in both parts | 9 Python kinds + 5 Jinja kinds; 15 findings in `semantic_report.txt` |
| 5 | Generated parts work together | `WebServer` serves the generated pages with the CSS, images and JavaScript |
| 6 | List, add, detail, delete + navigation | The six routes above; every page links to the others |
| 7 | Printing nodes, the whole tree, the symbol table | `PrintVisitor`, `JsonExporter`, `SymbolTableVisitor.printSymbolTable()` |

---

## 13. Known limitations

* Jinja arithmetic (`{{ a + b }}`) is not in the expression grammar; the
  evaluator supports `+` if the tokens are added.
* `{% for %}` has no tuple unpacking and no `{% for %} … {% else %}`.
* Boolean HTML attributes are re-emitted as `checked="true"` — valid HTML5,
  but not byte-identical to the source.
* `WebServer` and `WebServerJS` share a large amount of code; a shared base
  class would remove the duplication.
* The standalone HTML grammar is generated but unused — HTML inside templates
  is handled by the template grammar.

---

## 14. Building and running

```bash
# Build (72 source files)
javac -encoding UTF-8 -d out -cp "lib/antlr-4.13.2-complete.jar" $(find src -name "*.java")

# Run the compiler: parsing, symbol table, semantic analysis, generation
java -cp "out;lib/antlr-4.13.2-complete.jar" compiler.main.TestMain

# Run the verification suite
java -cp "out;lib/antlr-4.13.2-complete.jar" compiler.test.CompilerTests

# Run the application → http://localhost:8080/products
java -cp "out;lib/antlr-4.13.2-complete.jar" compiler.main.WebServer
```

On Windows separate classpath entries with `;`, on Linux and macOS with `:`.

---

## 15. Project statistics

| Measure | Value |
| --- | --- |
| Grammar files | 8 (`.g4`), 701 lines |
| Generated parser code | 17,281 lines |
| Hand-written Java | 7,425 lines across 48 files |
| AST classes | 29 (3 abstract + 26 concrete) |
| Visitor methods | 26 |
| Lexical modes in the template lexer | 6 |
| Semantic checks | 9 Python + 5 Jinja |
| Jinja filters implemented | 27 cases |
| Tests | 70 assertions |
| Runtime dependencies | ANTLR 4.13.2 only |

---

## Appendix — corrections made during the final review

The project was reviewed end to end before submission and the following defects
were found and fixed:

1. `{% if %}` was dispatched by tree shape instead of node kind, so
   `{% if products %}` was mistaken for a loop and rendered nothing;
   `{% elif %}` and `{% else %}` were never rendered at all.
2. Statements nested inside an `if` body were dropped.
3. Values injected into pages were not HTML-escaped.
4. An element with two dynamic attributes gave both the first one's value.
5. Filters were parsed and checked but never applied during generation.
6. `loop.index` and friends were accepted but produced nothing.
7. `not`, `and` and `or` always evaluated to false.
8. Whitespace around expressions was trimmed away (`Price:1200$`).
9. `{# Jinja comments #}` did not parse at all.
10. Expressions inside HTML attributes escaped semantic analysis.
11. `global x` overwrote the variable's real definition line and created a
    phantom local in the symbol table.
12. `/static/../app.py` could read files outside the resources folder.
13. `POST /delete/abc` returned a 500 error page instead of redirecting.
14. `output/` was only written once and went stale after any add or delete.
15. The generation log listed copied files that did not match reality.
