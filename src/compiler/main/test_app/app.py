from flask import Flask, render_template, request, redirect, url_for

app = Flask(__name__, static_folder="resources")

products = [
    {
        "id": 1,
        "name": "Laptop",
        "price": 1200,
        "details": "High performance laptop",
        "image": "images/img.png"
    },
    {
        "id": 2,
        "name": "Phone",
        "price": 800,
        "details": "Smartphone with great camera",
        "image": "images/img.png"
    }
]

@app.route("/products")
def list_products():
    return render_template("list_products.jinja", products=products)

@app.route("/products/<int:pid>")
def product_detail(pid):
    product = next(p for p in products if p["id"] == pid)
    return render_template("product_detail.jinja", product=product)

@app.route("/add", methods=["GET", "POST"])
def add_product():
    if request.method == "POST":
        new_product = {
            "id": len(products) + 1,
            "name": request.form["name"],
            "price": request.form["price"],
            "details": request.form["details"],
            "image": "images/img.png"
        }
        products.append(new_product)
        return redirect(url_for("list_products"))
    return render_template("add_product.jinja")

@app.route("/delete/<int:pid>", methods=["POST"])
def delete_product(pid):
    global products
    products = [p for p in products if p["id"] != pid]
    return redirect(url_for("list_products"))

if __name__ == "__main__":
    app.run(debug=True)
