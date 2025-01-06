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
        body {
            background-color: #f8f9fa;
        }

        .container {
            max-width: 1200px;
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        }

        .table {
            margin-top: 20px;
        }

        .table th {
            background-color: #007bff;
            color: white;
            font-weight: bold;
        }

        .table tbody tr:hover {
            background-color: #f1f1f1;
        }

        .btn {
            border-radius: 0.5rem;
        }

        .btn-warning {
            background-color: #ffc107;
            border-color: #ffc107;
        }

        .btn-warning:hover {
            background-color: #e0a800;
            border-color: #d39e00;
        }

        .btn-danger {
            background-color: #dc3545;
            border-color: #dc3545;
        }

        .btn-danger:hover {
            background-color: #c82333;
            border-color: #bd2130;
        }

        .actions-btn {
            display: flex;
            gap: 10px;
        }

        .actions-btn a {
            width: 100px;
            text-align: center;
        }

        .table-responsive {
            overflow-x: auto;
        }

        .form-header {
            text-align: center;
            margin-bottom: 20px;
        }

        .form-header h2 {
            color: #007bff;
            font-weight: bold;
        }

        .form-header p {
            color: #6c757d;
            font-size: 16px;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <div class="form-header">
        <h2>Product List</h2>
        <p>View and manage your product catalog here. Update product information, stock levels, and prices as needed.</p>
    </div>
    <div class="table-responsive">
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
                    <td class="actions-btn">
                        <a href="#" class="btn btn-warning btn-sm edit-btn" data-id="${product.id}">Edit</a>
                        <a href="/supplier/product?action=delete&id=${product.id}" class="btn btn-danger btn-sm">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>