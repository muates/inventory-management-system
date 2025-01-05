<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product List</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-4">
    <h3 class="text-center mb-4">Product List</h3>

    <c:if test="${empty productList}">
        <div class="alert alert-warning" role="alert">
            No products found.
        </div>
    </c:if>

    <div class="row">
        <c:forEach var="product" items="${productList}">
            <div class="col-md-3 mb-4">
                <div class="card">
                    <c:set var="productImage" value="${not empty product.photos ? product.photos[0].photoUrl : '/assets/images/default-product.jpg'}"/>
                    <img src="${productImage}" class="card-img-top" alt="Product Image" style="height: 200px; object-fit: cover;">
                    <div class="card-body">
                        <h5 class="card-title">${product.name}</h5>
                        <p class="card-text">Price: ${product.price}</p>
                        <p class="card-text">Quantity: ${product.stockQuantity}</p>
                        <form action="${pageContext.request.contextPath}/retailer/cart" method="post">
                            <input type="hidden" name="productId" value="${product.id}">
                            <input type="hidden" name="productName" value="${product.name}">
                            <input type="hidden" name="unitPrice" value="${product.price}">
                            <input type="hidden" name="supplierId" value="${product.supplierId}">
                            <input type="number" name="quantity" value="1" min="1" max="${product.stockQuantity}" required>
                            <button type="submit" class="btn btn-success btn-sm">Add to Cart</button>
                        </form>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

</div>

</body>
</html>
