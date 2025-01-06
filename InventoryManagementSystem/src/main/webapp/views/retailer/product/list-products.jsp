<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product List</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .card {
            border: none;
            border-radius: 15px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: transform 0.2s, box-shadow 0.2s;
        }
        .card:hover {
            transform: scale(1.05);
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
        }
        .card-img-top {
            height: 200px;
            object-fit: cover;
            border-radius: 15px 15px 0 0;
        }
        .card-title {
            font-size: 1.25rem;
            font-weight: bold;
        }
        .card-text {
            font-size: 0.95rem;
        }
        .btn-success {
            background-color: #28a745;
            border: none;
            transition: background-color 0.2s;
        }
        .btn-success:hover {
            background-color: #218838;
        }
        .quantity-input {
            width: 70px;
            margin-right: 10px;
        }
    </style>
</head>
<body>

<div class="container mt-5">
    <h3 class="text-center mb-4">Product List</h3>

    <c:if test="${empty productList}">
        <div class="alert alert-warning text-center" role="alert">
            No products found.
        </div>
    </c:if>

    <div class="row">
        <c:forEach var="product" items="${productList}">
            <div class="col-md-4 col-lg-3 mb-4 d-flex">
                <div class="card flex-fill">
                    <c:set var="productImage" value="${not empty product.photos ? product.photos[0].photoUrl : '/assets/images/default-product.jpg'}"/>
                    <img src="${productImage}" class="card-img-top" alt="Product Image">
                    <div class="card-body text-center">
                        <h5 class="card-title">${product.name}</h5>
                        <p class="card-text text-muted">Price: <strong>${product.price}</strong></p>
                        <p class="card-text text-muted">Stock: <strong>${product.stockQuantity}</strong></p>
                        <form action="${pageContext.request.contextPath}/retailer/cart" method="post" class="d-flex align-items-center justify-content-center">
                            <input type="hidden" name="productId" value="${product.id}">
                            <input type="hidden" name="productName" value="${product.name}">
                            <input type="hidden" name="unitPrice" value="${product.price}">
                            <input type="hidden" name="supplierId" value="${product.supplierId}">
                            <input type="number" name="quantity" class="form-control quantity-input" value="1" min="1" max="${product.stockQuantity}" required>
                            <button type="submit" class="btn btn-success btn-sm">
                                <i class="fas fa-cart-plus"></i> Add to Cart
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

</div>

</body>
</html>