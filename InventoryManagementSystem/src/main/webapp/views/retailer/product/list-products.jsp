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

    <table class="table table-bordered table-striped">
        <thead class="thead-dark">
        <tr>
            <th>Product Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${productList}">
            <tr>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.stockQuantity}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/retailer/cart" method="post">
                        <input type="hidden" name="productId" value="${product.id}">
                        <input type="hidden" name="productName" value="${product.name}">
                        <input type="hidden" name="unitPrice" value="${product.price}">
                        <input type="hidden" name="supplierId" value="${product.supplierId}"> <!-- Eklenen hidden input -->
                        <input type="number" name="quantity" value="1" min="1" max="${product.stockQuantity}" required>
                        <button type="submit" class="btn btn-success btn-sm">Add to Cart</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
