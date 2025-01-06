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
            padding-top: 20px;
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
                        <button class="btn btn-sidebar" id="addProductBtn"><i class="fas fa-plus"></i> Add Product</button>
                    </li>
                    <li class="nav-item">
                        <button class="btn btn-sidebar" id="productListBtn"><i class="fas fa-list"></i> Product List</button>
                    </li>
                    <li class="nav-item">
                        <button class="btn btn-sidebar" id="ordersBtn"><i class="fas fa-box"></i> Orders</button>
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
                <h2>Welcome to Your Supplier Dashboard</h2>
                <p>Select an option from the left menu to get started.</p>
            </div>
        </main>
    </div>
</div>

<!-- Modal HTML -->
<div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editModalLabel">Edit Product</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body" id="editModalContent">

            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    $(document).ready(function() {
        const defaultActionId = "ordersBtn";
        $("#" + defaultActionId).addClass("active");

        $.ajax({
            url: "/supplier/order",
            success: function(response) {
                $('#contentContainer').html(response);
            },
            error: function(xhr, status, error) {
                console.error("AJAX request failed", status, error);
            }
        });

        $(document).on("click", ".btn-sidebar", function() {
            const actionId = $(this).attr("id");
            let targetUrl = "";

            $(".btn-sidebar").removeClass("active");
            $(this).addClass("active");

            switch (actionId) {
                case "addProductBtn":
                    targetUrl = "/supplier/product?action=add-product";
                    break;
                case "productListBtn":
                    targetUrl = "/supplier/product?action=product-list";
                    break;
                case "ordersBtn":
                    targetUrl = "/supplier/order";
                    break;
                case "logoutBtn":
                    targetUrl = "/logout";
                    break;
            }

            if (targetUrl) {
                if (actionId === "logoutBtn") {
                    window.location.href = targetUrl;
                } else {
                    $.ajax({
                        url: targetUrl,
                        success: function(response) {
                            $('#contentContainer').html(response);
                        },
                        error: function(xhr, status, error) {
                            console.error("AJAX request failed", status, error);
                        }
                    });
                }
            }
        });

        $(document).on("click", ".edit-btn", function() {
            const productId = $(this).data("id");
            console.log("Product ID:", productId);

            const url = "/supplier/product?action=edit-product&id=" + encodeURIComponent(productId);

            $.ajax({
                url: url,
                success: function(response) {
                    $('#editModalContent').html(response);
                    $('#editModal').modal('show');
                },
                error: function(xhr, status, error) {
                    console.error("AJAX request failed", status, error);
                    alert("Error loading product details.");
                }
            });
        });
    });
</script>

</body>
</html>