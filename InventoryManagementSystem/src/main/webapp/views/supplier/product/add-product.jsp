<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Product</title>
    <!-- Add Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2>Add New Product</h2>
    <form action="/supplier/product" method="POST">
        <input type="hidden" name="action" value="add"/>
        <div class="form-group">
            <label for="name">Product Name</label>
            <input type="text" id="name" name="name" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="stockQuantity">Stock Quantity</label>
            <input type="number" id="stockQuantity" name="stockQuantity" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="price">Price</label>
            <input type="number" step="0.01" id="price" name="price" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="discount">Discount</label>
            <input type="number" step="0.01" id="discount" name="discount" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-primary">Save Product</button>
    </form>
</div>
</body>
</html>