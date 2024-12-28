package com.muates.inventorymanagementsystem.common.model.dto.response;

public class CustomResponse<T> {

    private String status;
    private T data;
    private String errorMessage;
    private int statusCode;

    public CustomResponse(String status, T data, String errorMessage, int statusCode) {
        this.status = status;
        this.data = data;
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
    }

    public static <T> CustomResponse<T> success(T data) {
        return new CustomResponse<>("success", data, "Operation was successful", 200);
    }

    public static <T> CustomResponse<T> error(String message, int code) {
        return new CustomResponse<>("error", null, message, code);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
