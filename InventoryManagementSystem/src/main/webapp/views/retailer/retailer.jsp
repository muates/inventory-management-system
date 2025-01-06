<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Supplier Dashboard</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" rel="stylesheet">
    <style>
        #sidebar {
            height: 100vh;
            position: fixed;
            top: 0;
            left: 0;
            z-index: 1;
            width: 250px;
            background-color: #343a40;
            color: #fff;
            box-shadow: 2px 0 10px rgba(0,0,0,0.1);
            border-radius: 10px;
            margin-top: 20px;
            transition: all 0.3s ease;
        }

        #sidebar .nav-item {
            margin-bottom: 15px;
        }

        #sidebar .btn-sidebar {
            width: 100%;
            padding: 12px;
            margin-bottom: 10px;
            text-align: left;
            border-radius: 8px;
            background-color: #495057;
            color: #fff;
            border: none;
            transition: background-color 0.3s ease;
        }

        #sidebar .btn-sidebar:hover {
            background-color: #007bff;
        }

        #sidebar .btn-sidebar i {
            margin-right: 10px;
        }

        #contentArea {
            margin-left: 270px;
            padding: 20px;
            max-width: 1000px;
            margin-right: auto;
            margin-left: auto;
        }

        .sidebar-sticky {
            position: sticky;
            top: 0;
        }

        .main-content {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            padding: 20px;
        }

        .btn-sidebar.active {
            background-color: #007bff;
            color: white;
        }

        #sidebar:hover {
            width: 300px;
        }
    </style>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <!-- Sidebar -->
        <nav id="sidebar" class="col-md-3 col-lg-2 d-md-block bg-light sidebar">
                <div class="sidebar-sticky">
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <button class="btn btn-primary btn-sidebar" id="productListBtn"><i class="fas fa-list"></i> Product List</button>
                    </li>
                    <li class="nav-item">
                        <button class="btn btn-primary btn-sidebar" id="cartBtn"><i class="fas fa-shopping-cart"></i> Cart</button>
                    </li>
                    <li class="nav-item">
                        <button class="btn btn-primary btn-sidebar" id="ordersBtn"><i class="fas fa-box"></i> Orders</button>
                    </li>
                    <li class="nav-item">
                        <button class="btn btn-danger btn-sidebar" id="logoutBtn"><i class="fas fa-sign-out-alt"></i> Logout</button>
                    </li>
                </ul>
            </div>
        </nav>

        <!-- Main Content Area -->
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4" id="contentArea">
            <div class="main-content" id="contentContainer">
                <h2>Welcome to Your Retailer Dashboard</h2>
                <p>Select an option from the left menu to get started.</p>
            </div>
        </main>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    $(document).ready(function () {
        const defaultActionId = "productListBtn";
        $("#" + defaultActionId).addClass("active");

        $.ajax({
            url: "/retailer/product?action=product-list",
            success: function (response) {
                $('#contentContainer').html(response);
            },
            error: function (xhr, status, error) {
                console.error("AJAX request failed", status, error);
            }
        });

        $(document).on("click", ".btn-sidebar", function () {
            const actionId = $(this).attr("id");
            let targetUrl = "";

            $(".btn-sidebar").removeClass("active");
            $(this).addClass("active");

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