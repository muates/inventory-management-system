<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Product</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }

        .container {
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        .form-group {
            margin-bottom: 1.5rem;
        }

        .form-control {
            border-radius: 0.5rem;
        }

        .btn {
            border-radius: 0.5rem;
            padding: 10px 20px;
            font-size: 16px;
        }

        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
        }

        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #004085;
        }

        .image-preview {
            display: flex;
            gap: 10px;
            flex-wrap: wrap;
            margin-top: 10px;
        }

        .image-preview img {
            width: 100px;
            height: 100px;
            object-fit: cover;
            border-radius: 5px;
            border: 2px solid #ddd;
        }

        .image-preview img:hover {
            border-color: #007bff;
        }

        .form-header {
            text-align: center;
            margin-bottom: 20px;
        }

        .form-header h2 {
            color: #007bff;
            font-weight: bold;
        }

        .form-header p {
            color: #6c757d;
            font-size: 16px;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <div class="form-header">
        <h2>Add New Product</h2>
        <p>Fill in the details below to add a new product to your catalog</p>
    </div>
    <form action="/supplier/product" method="POST" enctype="multipart/form-data">
        <input type="hidden" name="action" value="add"/>

        <div class="form-group">
            <label for="name">Product Name</label>
            <input type="text" id="name" name="name" class="form-control" required placeholder="Enter product name">
        </div>

        <div class="form-group">
            <label for="stockQuantity">Stock Quantity</label>
            <input type="number" id="stockQuantity" name="stockQuantity" class="form-control" required placeholder="Enter stock quantity">
        </div>

        <div class="form-group">
            <label for="price">Price</label>
            <input type="number" step="0.01" id="price" name="price" class="form-control" required placeholder="Enter price">
        </div>

        <div class="form-group">
            <label for="discount">Discount</label>
            <input type="number" step="0.01" id="discount" name="discount" class="form-control" required placeholder="Enter discount">
        </div>

        <div class="form-group">
            <label for="photos">Product Photos</label>
            <input type="file" id="photos" name="photos" class="form-control" multiple accept="image/*" onchange="previewImages()">
        </div>

        <div class="form-group">
            <label>Image Preview</label>
            <div class="image-preview" id="imagePreview"></div>
        </div>

        <button type="submit" class="btn btn-primary btn-block">Save Product</button>
    </form>
</div>

<script>
    function previewImages() {
        var preview = document.getElementById('imagePreview');
        preview.innerHTML = '';
        var files = document.getElementById('photos').files;

        for (var i = 0; i < files.length; i++) {
            var file = files[i];
            var reader = new FileReader();

            reader.onload = function (e) {
                var img = document.createElement('img');
                img.src = e.target.result;
                preview.appendChild(img);
            }

            reader.readAsDataURL(file);
        }
    }
</script>
</body>
</html>