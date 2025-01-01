<%--
  Created by IntelliJ IDEA.
  User: W10
  Date: 12/29/2024
  Time: 6:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Registration and Login</title>
    <!-- Link to CSS -->
    <link href="${pageContext.request.contextPath}/assets/css/auth.css" rel="stylesheet">
    <!-- Bootstrap CDN -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h3>Inventory Management System</h3>

    <!-- Login Section -->
    <div class="form-container">
        <div id="loginTitle" class="form-title mb-3" data-toggle="collapse" data-target="#loginForm">
            Login
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
            Register
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

<!-- Link to JS -->
<script src="${pageContext.request.contextPath}/assets/js/auth.js"></script>
<!-- Bootstrap JS and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
