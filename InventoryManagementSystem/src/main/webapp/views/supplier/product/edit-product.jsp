<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form id="editProductForm" action="/supplier/product" method="POST">
    <input type="hidden" name="action" value="update"/>
    <input type="hidden" name="id" value="${product.id}"/>

    <div class="form-group">
        <label for="name">Product Name</label>
        <input type="text" id="name" name="name" class="form-control" value="${product.name}" required>
    </div>
    <div class="form-group">
        <label for="stockQuantity">Stock Quantity</label>
        <input type="number" id="stockQuantity" name="stockQuantity" class="form-control" value="${product.stockQuantity}" required>
    </div>
    <div class="form-group">
        <label for="price">Price</label>
        <input type="number" step="0.01" id="price" name="price" class="form-control" value="${product.price}" required>
    </div>
    <div class="form-group">
        <label for="discount">Discount</label>
        <input type="number" step="0.01" id="discount" name="discount" class="form-control" value="${product.discount}" required>
    </div>
    <button type="submit" class="btn btn-primary">Update Product</button>
</form>