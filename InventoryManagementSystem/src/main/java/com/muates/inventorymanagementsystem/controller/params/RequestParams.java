package com.muates.inventorymanagementsystem.controller.params;

public class RequestParams {
    public static final String[] RETAILER_CREATE_REQUEST_PARAMS = {"name", "phoneNumber", "emailAddress", "password", "photoUrl"};
    public static final String[] SUPPLIER_CREATE_REQUEST_PARAMS = {"name", "phoneNumber", "emailAddress", "password", "photoUrl"};
    public static final String[] PRODUCT_CREATE_REQUEST_PARAMS = {"name", "stockQuantity", "price", "discount"};
    public static final String[] PRODUCT_UPDATE_REQUEST_PARAMS = {"name", "stockQuantity", "price", "discount"};
    public static final String[] AUTH_LOGIN_REQUEST = {"emailAddress", "password"};
}
