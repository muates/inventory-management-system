<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Cart</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-4">
    <h3 class="text-center mb-4">Your Cart</h3>

    <c:if test="${empty cart}">
        <div class="alert alert-warning" role="alert">
            Your cart is empty.
        </div>
    </c:if>

    <table class="table table-bordered table-striped">
        <thead class="thead-dark">
        <tr>
            <th>Product Name</th>
            <th>Quantity</th>
            <th>Unit Price</th>
            <th>Total Price</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${cart}">
            <tr>
                <td>${item.productName}</td>
                <td>${item.quantity}</td>
                <td>${item.unitPrice}</td>
                <td>${item.unitPrice * item.quantity}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="total-price">
        <h4>Total: ${totalPrice}</h4>
    </div>

    <form action="${pageContext.request.contextPath}/checkout" method="post">
        <button type="submit" class="btn btn-primary btn-lg btn-block">Proceed to Checkout</button>
    </form>
</div>

</body>
</html>
