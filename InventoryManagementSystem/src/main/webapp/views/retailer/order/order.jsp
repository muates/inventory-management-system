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
        .order-section {
            display: none;
        }
        .order-section.active {
            display: block;
        }
        .nav-pills {
            display: flex;
            justify-content: space-between;
            padding: 0;
            margin-bottom: 20px;
        }

        .nav-pills .nav-item {
            flex: 1;
        }

        .nav-pills .nav-link {
            background-color: #007bff;
            color: white;
            border-radius: 0.375rem 0.375rem 0 0;
            padding: 10px 0;
            text-align: center;
            display: block;
            width: 100%;
        }

        .nav-pills .nav-link.active {
            background-color: #0056b3;
            color: white;
        }

        .toggle-detail-btn {
            background-color: #28a745;
            color: white;
            border: none;
            padding: 5px 10px;
            cursor: pointer;
            border-radius: 5px;
            margin-top: 5px;
        }

        .toggle-detail-btn:hover {
            background-color: #218838;
        }

        .btn-info {
            background-color: #17a2b8;
            border-color: #17a2b8;
        }

        .btn-info:hover {
            background-color: #138496;
            border-color: #117a8b;
        }
    </style>
</head>
<body>
<div class="container mt-4">
    <h3 class="text-center mb-4">Orders</h3>

    <ul class="nav nav-pills">
        <li class="nav-item">
            <a class="nav-link active" id="pendingTab" data-target=".pending-section">Pending</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="approvedTab" data-target=".approved-section">Approved</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="rejectedTab" data-target=".rejected-section">Rejected</a>
        </li>
    </ul>

    <!-- Pending Orders Section -->
    <div class="order-section pending-section active mt-3">
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
                <c:if test="${order.bill.status == 'PENDING'}">
                    <tr>
                        <td>${order.bill.id}</td>
                        <td>${order.bill.supplierId}</td>
                        <td>${order.bill.status}</td>
                        <td>${order.bill.totalAmount}</td>
                        <td>${order.bill.date}</td>
                        <td>
                            <button class="btn toggle-detail-btn" data-target="#orderDetail-${order.bill.id}">Toggle Details</button>
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
                </c:if>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Approved Orders Section -->
    <div class="order-section approved-section mt-3">
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
                <c:if test="${order.bill.status == 'APPROVED'}">
                    <tr>
                        <td>${order.bill.id}</td>
                        <td>${order.bill.supplierId}</td>
                        <td>${order.bill.status}</td>
                        <td>${order.bill.totalAmount}</td>
                        <td>${order.bill.date}</td>
                        <td>
                            <button class="btn toggle-detail-btn" data-target="#orderDetail-${order.bill.id}">Toggle Details</button>
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
                </c:if>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Rejected Orders Section -->
    <div class="order-section rejected-section mt-3">
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
                <c:if test="${order.bill.status == 'REJECTED'}">
                    <tr>
                        <td>${order.bill.id}</td>
                        <td>${order.bill.supplierId}</td>
                        <td>${order.bill.status}</td>
                        <td>${order.bill.totalAmount}</td>
                        <td>${order.bill.date}</td>
                        <td>
                            <button class="btn toggle-detail-btn" data-target="#orderDetail-${order.bill.id}">Toggle Details</button>
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
                </c:if>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>

<script>
    $(document).ready(function() {
        $(".nav-link").click(function() {
            $(".nav-link").removeClass("active");
            $(this).addClass("active");

            var target = $(this).data("target");
            $(".order-section").removeClass("active");
            $(target).addClass("active");
        });

        $(".toggle-detail-btn").click(function() {
            var target = $(this).data("target");
            $(target).toggle();
        });
    });
</script>
</body>
</html>