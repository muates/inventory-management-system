<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product List</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        .container {
            max-width: 1000px;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4">Your Products</h2>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Product ID</th>
            <th>Name</th>
            <th>Stock Quantity</th>
            <th>Price</th>
            <th>Discount</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${productList}">
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.stockQuantity}</td>
                <td>${product.price}</td>
                <td>${product.discount}</td>
                <td>
                    <a href="#" class="btn btn-warning btn-sm edit-btn" data-id="${product.id}">Edit</a>
                    <a href="/supplier/product?action=delete&id=${product.id}" class="btn btn-danger btn-sm">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>