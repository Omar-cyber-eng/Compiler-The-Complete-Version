# مخطط بنية الـ AST — مشروع المترجمات

هذا المستند يوضّح بنية الأشجار المجردة (Abstract Syntax Trees) في المشروع:
1. **مخطط بنية العقد (OOP)** — الوراثة وتعدد الأشكال.
2. **شجرة Python AST** — مثال من `app.py`.
3. **شجرة Jinja AST** — مثال من `list_products.jinja`.
4. **تمرير البيانات** من شجرة Python إلى شجرة Jinja (مرحلة التوليد).

> المخططات بصيغة Mermaid — تظهر رسوماً تلقائياً في GitHub و VS Code. توجد نسخة نصية (ASCII) أسفل كل مخطط.

---

## 1) مخطط بنية العقد (OOP: Inheritance + Polymorphism)

كل العقد ترث من `AstNode` المجردة التي تخزّن **اسم العقدة** و**رقم السطر** وقائمة **الأبناء**، وتفرض تابع `accept()` (تعدد الأشكال عبر نمط Visitor).

```mermaid
classDiagram
    class AstNode {
        <<abstract>>
        #String nodeName
        #int line
        #List~AstNode~ children
        +getNodeName() String
        +getLine() int
        +getChildren() List
        +accept(visitor)*
    }
    class ExprNode { <<abstract>> }
    class StmtNode { <<abstract>> }

    AstNode <|-- ExprNode
    AstNode <|-- StmtNode

    ExprNode <|-- NameNode
    ExprNode <|-- NumberNode
    ExprNode <|-- StringNode
    ExprNode <|-- BinOpNode
    ExprNode <|-- CallNode
    ExprNode <|-- AttrAccessNode
    ExprNode <|-- JinjaExprNode

    StmtNode <|-- AssignNode
    StmtNode <|-- IfNode
    StmtNode <|-- ForNode
    StmtNode <|-- DefNode
    StmtNode <|-- JinjaStmtNode

    AstNode <|-- PythonFileNode
    AstNode <|-- SuiteNode
    AstNode <|-- ReturnNode
    AstNode <|-- ImportNode
    AstNode <|-- DecoratorNode
    AstNode <|-- GlobalNode
    AstNode <|-- ListNode
    AstNode <|-- DictNode
    AstNode <|-- TemplateNode
    AstNode <|-- TextNode
    AstNode <|-- HtmlElementNode
    AstNode <|-- CssStylesheetNode
    AstNode <|-- CssRuleNode
    AstNode <|-- CssDeclarationNode
```

**ASCII:**
```
AstNode (abstract)  ── nodeName, line, children[], accept()
├── ExprNode (abstract)   ← تعابير
│     ├── NameNode, NumberNode, StringNode
│     ├── BinOpNode, CallNode, AttrAccessNode
│     └── JinjaExprNode                 ({{ ... }})
├── StmtNode (abstract)   ← جُمَل
│     ├── AssignNode, IfNode, ForNode, DefNode
│     └── JinjaStmtNode                 ({% for %} / {% if %})
└── عقد أخرى: PythonFileNode, SuiteNode, ReturnNode, ImportNode,
    DecoratorNode, GlobalNode, ListNode, DictNode,
    TemplateNode, TextNode, HtmlElementNode,
    CssStylesheetNode, CssRuleNode, CssDeclarationNode
```

---

## 2) شجرة Python AST (مثال من `app.py`)

تمثيل لإسناد قائمة `products` ودالة `list_products` (المصدر الكامل في `compiler_output/ast_python.json`).

```mermaid
flowchart TD
    PF["PythonFile (line 1)"]
    PF --> IMP["Import 'from flask' (1)"]
    PF --> ASGapp["Assign (3)"]
    PF --> ASGprod["Assign (5)"]
    PF --> DEF["Def 'list_products' (22)"]

    %% products = [ {..}, {..} ]
    ASGprod --> Npr["Name 'products' (5)"]
    ASGprod --> LST["List (5)"]
    LST --> D1["Dict (6)"]
    LST --> D2["Dict (13)"]
    D1 --> k1["String 'id'"]
    D1 --> v1["Number 1"]
    D1 --> k2["String 'name'"]
    D1 --> v2["String 'Laptop'"]
    D1 --> k3["String 'price'"]
    D1 --> v3["Number 1200"]

    %% @app.route('/products') def list_products(): return render_template(...)
    DEF --> DEC["Decorator 'app.route' (22)"]
    DEF --> SUI["Suite (23)"]
    DEC --> ARGS["Args → String '/products'"]
    SUI --> RET["Return (24)"]
    RET --> CALL["Call (24)"]
    CALL --> Nrt["Name 'render_template'"]
    CALL --> Stpl["String 'list_products.jinja'"]
    CALL --> KW["KeywordArg (24)"]
    KW --> kwk["Name 'products'"]
    KW --> kwv["Name 'products'"]
```

**ASCII:**
```
PythonFile (1)
├── Import "from flask" (1)
├── Assign (3)                         # app = Flask(...)
├── Assign (5)                         # products = [ ... ]
│   ├── Name "products" (5)
│   └── List (5)
│       ├── Dict (6)                   # {id:1, name:"Laptop", price:1200, ...}
│       │   ├── String "id"   / Number 1
│       │   ├── String "name" / String "Laptop"
│       │   └── String "price"/ Number 1200
│       └── Dict (13)                  # {id:2, name:"Phone", ...}
└── Def "list_products" (22)
    ├── Decorator "app.route" (22)
    │   └── Args → String "/products"
    └── Suite (23)
        └── Return (24)
            └── Call (24)              # render_template("list_products.jinja", products=products)
                ├── Name "render_template"
                ├── String "list_products.jinja"
                └── KeywordArg (24)
                    ├── Name "products"   (المفتاح)
                    └── Name "products"   (القيمة)
```

---

## 3) شجرة Jinja AST (مثال من `list_products.jinja`)

(المصدر الكامل في `compiler_output/ast_jinja.json`.)

```mermaid
flowchart TD
    T["Template (1)"]
    T --> DOC["Text '<!DOCTYPE html>'"]
    T --> HTML["HtmlElement html (2)"]
    HTML --> HEAD["HtmlElement head (3)"]
    HTML --> BODY["HtmlElement body (7)"]

    HEAD --> LINK["HtmlElement link (5)"]
    LINK --> C1["Call url_for (5)"]
    C1 --> u1["Name 'url_for'"]
    C1 --> u2["String 'static'"]
    C1 --> u3["KwArg filename → 'css/style.css'"]

    BODY --> H1["HtmlElement h1 → Text 'Products'"]
    BODY --> UL["HtmlElement ul (11)"]
    UL --> FOR["JinjaStmt kind=for (12)"]
    FOR --> lv["Name 'product' (loop var)"]
    FOR --> it["Name 'products' (iterable)"]
    FOR --> LI["HtmlElement li (13)"]
    LI --> A["HtmlElement a (href /products/ + product.id)"]
    A --> AA1["AttrAccess product.id"]
    A --> JE1["JinjaExpr product.name"]
    LI --> JE2["JinjaExpr product.price"]
    LI --> FORM["HtmlElement form (action /delete/ + product.id)"]
```

**ASCII:**
```
Template (1)
├── Text "<!DOCTYPE html>"
└── HtmlElement <html> (2)
    ├── HtmlElement <head> (3)
    │   └── HtmlElement <link> (5)      attrs=[href="{{...}}"]
    │       └── Call url_for → 'static', filename='css/style.css'
    └── HtmlElement <body> (7)
        ├── HtmlElement <h1> → Text "Products"
        └── HtmlElement <ul> (11)
            └── JinjaStmt kind=for (12)         # {% for product in products %}
                ├── Name "product"   (متغير الحلقة)
                ├── Name "products"  (المُكرَّر عليه)
                └── HtmlElement <li> (13)
                    ├── HtmlElement <a> → AttrAccess product.id + {{ product.name }}
                    ├── JinjaExpr {{ product.price }}
                    └── HtmlElement <form> → /delete/{{ product.id }}
```

---

## 4) تمرير البيانات: من شجرة Python ← إلى ← شجرة Jinja

في مرحلة التوليد (`CodeGenerator`)، يُستخرج المتغير `products` من **شجرة Python**، ثم يُحقن في **شجرة Jinja** لتوليد HTML نهائي:

```mermaid
flowchart LR
    subgraph PY["شجرة Python AST"]
        A["Assign: products"] --> L["List → Dict, Dict"]
    end
    subgraph GEN["CodeGenerator"]
        E["extractFromPythonAST()"] --> CTX["Context:<br/>products = [Laptop, Phone]"]
    end
    subgraph JJ["شجرة Jinja AST"]
        F["JinjaStmt: for product in products"]
    end
    L --> E
    CTX --> F
    F --> HTML["HTML نهائي:<br/>li Laptop - 1200$<br/>li Phone - 800$"]
```

**الفكرة باختصار:**
```
products (شجرة Python)  ──extract──►  [ {Laptop,1200}, {Phone,800} ]
                                              │  (الـ Context)
{% for product in products %}  ◄──inject──────┘
        │
        ▼
   <li>Laptop - 1200$</li>
   <li>Phone - 800$</li>
```

> هذا يحقق متطلب المشروع: «التابع المولّد يمرّر البيانات من مصفوفة البيانات في كود Python إلى الشجرة الثانية (Jinja)».
