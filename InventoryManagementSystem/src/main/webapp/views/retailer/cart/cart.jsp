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

    <form action="${pageContext.request.contextPath}/cart/checkout" method="post" id="checkout-form">
        <table class="table table-bordered table-striped">
            <thead class="thead-dark">
            <tr>
                <th>Product Name</th>
                <th>Product ID</th>
                <th>Quantity</th>
                <th>Unit Price</th>
                <th>Total Price</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${cart}" varStatus="status">
                <tr class="cart-item">
                    <td>${item.productName}</td>
                    <td>${item.productId}</td>
                    <td>
                        <input type="number" class="quantity-input" name="items[${status.index}].quantity" value="${item.quantity}" min="1">
                    </td>
                    <td class="unit-price">${item.unitPrice}</td>
                    <td class="total-price">${item.unitPrice * item.quantity}</td>
                    <td>
                        <button type="button" class="btn btn-danger remove-item">Remove</button>
                    </td>
                    <input type="hidden" name="items[${status.index}].productId" value="${item.productId}">
                    <input type="hidden" name="items[${status.index}].supplierId" value="${item.supplierId}">
                    <input type="hidden" name="items[${status.index}].discount" value="${item.discount}">
                    <input type="hidden" name="items[${status.index}].unitPrice" value="${item.unitPrice}">
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div class="total-price">
            <h4>Total: <span id="total-price">${totalPrice}</span></h4>
        </div>

        <button type="submit" class="btn btn-primary btn-lg btn-block">Proceed to Checkout</button>
    </form>
</div>

</body>
</html>