<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Supplier Dashboard</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        #sidebar {
            height: 100vh;
            position: fixed;
            top: 0;
            left: 0;
            width: 250px;
            background-color: #f8f9fa;
            box-shadow: 2px 0 10px rgba(0, 0, 0, 0.1);
        }
        #contentArea {
            margin-left: 250px;
            padding: 20px;
        }
        .btn-sidebar {
            width: 100%;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <!-- Sidebar -->
        <nav id="sidebar">
            <ul class="nav flex-column">
                <li class="nav-item">
                    <button class="btn btn-primary btn-sidebar" id="productListBtn">Product List</button>
                </li>
                <li class="nav-item">
                    <button class="btn btn-primary btn-sidebar" id="cartBtn">Cart</button>
                </li>
                <li class="nav-item">
                    <button class="btn btn-primary btn-sidebar" id="ordersBtn">Orders</button>
                </li>
                <li class="nav-item">
                    <button class="btn btn-danger btn-sidebar" id="logoutBtn">Logout</button>
                </li>
            </ul>
        </nav>

        <!-- Main Content Area -->
        <main role="main" id="contentArea" class="col">
            <div id="contentContainer">
                <h2>Welcome to Your Dashboard</h2>
                <p>Select an option from the menu.</p>
            </div>
        </main>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    $(document).ready(function () {
        $(document).on("click", ".btn-sidebar", function () {
            const actionId = $(this).attr("id");
            let targetUrl = "";

            switch (actionId) {
                case "productListBtn":
                    targetUrl = "/retailer/product?action=product-list";
                    break;
                case "cartBtn":
                    targetUrl = "/retailer/cart";
                    break;
                case "ordersBtn":
                    targetUrl = "/retailer/order";
                    break;
                case "logoutBtn":
                    window.location.href = "/logout";
                    return;
            }

            if (targetUrl) {
                $.ajax({
                    url: targetUrl,
                    success: function (response) {
                        $('#contentContainer').html(response);
                    },
                    error: function (xhr, status, error) {
                        console.error("AJAX request failed", status, error);
                    }
                });
            }
        });
    });
</script>

</body>
</html>