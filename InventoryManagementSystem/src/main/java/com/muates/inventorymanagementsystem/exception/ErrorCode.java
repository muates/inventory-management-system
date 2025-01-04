package com.muates.inventorymanagementsystem.exception;

public enum ErrorCode {

    // Retailer Errors
    RETAILER_NOT_FOUND(404, "Retailer not found"),
    RETAILER_ALREADY_EXISTS(409, "Retailer already exists"),
    INVALID_RETAILER_CREDENTIALS(401, "Invalid retailer credentials"),

    // Supplier Errors
    SUPPLIER_NOT_FOUND(404, "Supplier not found"),
    SUPPLIER_ALREADY_EXISTS(409, "Supplier already exists"),
    INVALID_SUPPLIER_CREDENTIALS(401, "Invalid supplier credentials"),

    // Product Errors
    PRODUCT_NOT_FOUND(404, "Product not found"),
    PRODUCT_ALREADY_EXISTS(409, "Product already exists"),
    PRODUCT_OUT_OF_STOCK(400, "Product out of stock"),
    INVALID_PRODUCT_PRICE(400, "Invalid product price"),

    // Bill Errors
    BILL_NOT_FOUND(404, "Bill not found"),
    BILL_ALREADY_APPROVED(400, "Bill has already been approved"),
    INVALID_BILL_AMOUNT(400, "Invalid bill amount"),

    // General Errors
    DATABASE_ERROR(500, "Database error occurred"),
    UNAUTHORIZED_ACCESS(403, "Unauthorized access"),
    VALIDATION_ERROR(400, "Validation error"),
    UNKNOWN_ERROR(500, "An unknown error occurred");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
