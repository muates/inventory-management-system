<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Orders</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .order-detail {
            display: none;
        }
    </style>
</head>
<body>
<div class="container mt-4">
    <h2>My Orders</h2>
    <table class="table table-bordered">
        <thead class="thead-light">
        <tr>
            <th>Order ID</th>
            <th>Supplier</th>
            <th>Status</th>
            <th>Total</th>
            <th>Date</th>
            <th>Details</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="order" items="${orders}">
            <tr>
                <td>${order.bill.id}</td>
                <td>${order.bill.supplierId}</td>
                <td>${order.bill.status}</td>
                <td>${order.bill.totalAmount}</td>
                <td>${order.bill.date}</td>
                <td>
                    <button class="btn btn-info btn-sm toggle-detail-btn" data-target="#orderDetail-${order.bill.id}">Toggle Details</button>
                </td>
            </tr>
            <tr id="orderDetail-${order.bill.id}" class="order-detail">
                <td colspan="6">
                    <div class="table-responsive">
                        <h5>Order Details</h5>
                        <table class="table table-sm table-striped">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>Product</th>
                                <th>Quantity</th>
                                <th>Unit Price</th>
                                <th>Total Price</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="detail" items="${order.bill.billDetails}">
                                <tr>
                                    <td>${detail.id}</td>
                                    <td>${detail.productId}</td>
                                    <td>${detail.quantity}</td>
                                    <td>${detail.unitPrice}</td>
                                    <td>${detail.totalPrice}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
    $(document).ready(function() {
        $(".toggle-detail-btn").on("click", function() {
            var target = $(this).data("target");
            $(target).toggleClass("order-detail");
        });
    });
</script>
</body>
</html>