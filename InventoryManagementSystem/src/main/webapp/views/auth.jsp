<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Registration and Login</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f1f1f1;
            font-family: 'Arial', sans-serif;
        }

        .container {
            max-width: 600px;
            margin-top: 50px;
        }

        h3 {
            text-align: center;
            font-weight: bold;
            margin-bottom: 30px;
            color: #007bff;
        }

        .form-container {
            margin-bottom: 20px;
            border: 1px solid #ddd;
            padding: 20px;
            border-radius: 10px;
            background-color: white;
            box-shadow: 0 8px 15px rgba(0, 0, 0, 0.1);
        }

        .form-title {
            cursor: pointer;
            font-weight: bold;
            font-size: 1.5rem;
            text-align: center;
            color: #007bff;
            display: block;
            margin: 0 auto;
        }

        .form-title:hover {
            text-decoration: underline;
        }

        .form-group {
            margin-bottom: 1.5rem;
        }

        .btn {
            width: 100%;
            padding: 12px;
            font-size: 1.1rem;
            border-radius: 50px;
            transition: background-color 0.3s;
        }

        .btn-primary {
            background-color: #007bff;
            border: none;
        }

        .btn-primary:hover {
            background-color: #0056b3;
        }

        .btn-success {
            background-color: #28a745;
            border: none;
        }

        .btn-success:hover {
            background-color: #218838;
        }

        .form-control {
            border-radius: 0.25rem;
            height: 45px;
            font-size: 1rem;
        }

        .form-control:focus {
            box-shadow: none;
            border-color: #007bff;
        }

        .form-header {
            text-align: center;
            font-weight: bold;
            font-size: 1.2rem;
            color: #007bff;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <h3>Inventory Management System</h3>

    <!-- Login Section -->
    <div class="form-container">
        <div id="loginTitle" class="form-title mb-3" data-toggle="collapse" data-target="#loginForm">
            <i class="fa fa-sign-in-alt"></i> Login
        </div>
        <div id="loginForm" class="collapse show">
            <form action="${pageContext.request.contextPath}/auth" method="POST">
                <input type="hidden" name="action" value="login" />

                <div class="form-group">
                    <label for="isSupplierLogin">Are you a supplier?</label>
                    <select class="form-control" id="isSupplierLogin" name="isSupplier">
                        <option value="true">Yes, I am a Supplier</option>
                        <option value="false">No, I am a Retailer</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="loginEmail">Email Address</label>
                    <input type="email" class="form-control" id="loginEmail" name="emailAddress" required />
                </div>

                <div class="form-group">
                    <label for="loginPassword">Password</label>
                    <input type="password" class="form-control" id="loginPassword" name="password" required />
                </div>

                <button type="submit" class="btn btn-success">Login</button>
            </form>
        </div>
    </div>

    <!-- Register Section -->
    <div class="form-container">
        <div id="registerTitle" class="form-title mb-3" data-toggle="collapse" data-target="#registerForm">
            <i class="fa fa-user-plus"></i> Register
        </div>
        <div id="registerForm" class="collapse">
            <form action="${pageContext.request.contextPath}/auth" method="POST">
                <input type="hidden" name="action" value="register" />

                <div class="form-group">
                    <label for="isSupplier">Are you a supplier?</label>
                    <select class="form-control" id="isSupplier" name="isSupplier">
                        <option value="true">Yes, I am a Supplier</option>
                        <option value="false">No, I am a Retailer</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="name">Full Name</label>
                    <input type="text" class="form-control" id="name" name="name" required />
                </div>

                <div class="form-group">
                    <label for="phoneNumber">Phone Number</label>
                    <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" required />
                </div>

                <div class="form-group">
                    <label for="emailAddress">Email Address</label>
                    <input type="email" class="form-control" id="emailAddress" name="emailAddress" required />
                </div>

                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" id="password" name="password" required />
                </div>

                <div class="form-group">
                    <label for="photoUrl">Profile Photo URL</label>
                    <input type="text" class="form-control" id="photoUrl" name="photoUrl" />
                </div>

                <button type="submit" class="btn btn-primary">Register</button>
            </form>
        </div>
    </div>

</div>

<!-- Bootstrap JS and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    $(document).ready(function() {
        $('#loginTitle').on('click', function() {
            $('#loginForm').collapse('toggle');
            $('#registerForm').collapse('hide');
        });

        $('#registerTitle').on('click', function() {
            $('#registerForm').collapse('toggle');
            $('#loginForm').collapse('hide');
        });
    });
</script>

</body>
</html>