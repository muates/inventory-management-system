<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Cart</title>
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
        .quantity-input {
            width: 70px;
        }
        .table tbody tr:hover {
            background-color: #f8f9fa;
        }
        .remove-item {
            font-size: 14px;
            transition: background-color 0.2s, transform 0.2s;
        }
        .remove-item:hover {
            background-color: #dc3545;
            transform: scale(1.1);
        }
        .total-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-top: 20px;
        }
        .total-price {
            font-size: 1.5rem;
            font-weight: bold;
        }
        .btn-primary {
            background-color: #007bff;
            border: none;
            transition: background-color 0.2s;
        }
        .btn-primary:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

<div class="container mt-5">
    <h3 class="text-center mb-4">Your Cart</h3>

    <c:if test="${empty cart}">
        <div class="alert alert-warning text-center" role="alert">
            Your cart is empty.
        </div>
    </c:if>

    <form action="${pageContext.request.contextPath}/cart/checkout" method="post" id="checkout-form">
        <div class="table-responsive">
            <table class="table table-hover table-bordered">
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
                            <input type="number" class="form-control quantity-input"
                                   name="items[${status.index}].quantity"
                                   value="${item.quantity}"
                                   min="1" onchange="updatePrice(this)">
                        </td>
                        <td class="unit-price">${item.unitPrice}</td>
                        <td class="total-price">${item.unitPrice * item.quantity}</td>
                        <td>
                            <button type="button" class="btn btn-danger remove-item">
                                <i class="fas fa-trash-alt"></i> Remove
                            </button>
                        </td>
                        <input type="hidden" name="items[${status.index}].productId" value="${item.productId}">
                        <input type="hidden" name="items[${status.index}].supplierId" value="${item.supplierId}">
                        <input type="hidden" name="items[${status.index}].discount" value="${item.discount}">
                        <input type="hidden" name="items[${status.index}].unitPrice" value="${item.unitPrice}">
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="total-container">
            <h4>Total: <span id="total-price">${totalPrice}</span></h4>
            <button type="submit" class="btn btn-primary btn-lg">Proceed to Checkout</button>
        </div>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
<script>
    function updatePrice(input) {
        const row = $(input).closest('tr');
        const unitPrice = parseFloat(row.find('.unit-price').text());
        const quantity = parseInt($(input).val());
        const totalPrice = unitPrice * quantity;

        row.find('.total-price').text(totalPrice.toFixed(2));

        updateTotalPrice();
    }

    function updateTotalPrice() {
        let total = 0;
        $('.cart-item').each(function () {
            const itemTotal = parseFloat($(this).find('.total-price').text());
            total += itemTotal;
        });
        $('#total-price').text(total.toFixed(2));
    }
</script>

</body>
</html>