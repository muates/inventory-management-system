package com.muates.inventorymanagementsystem.model.dto.auth.request;

public class LoginRequest {

    private String email;
    private String password;
    private boolean isSupplier;

    public LoginRequest() {
    }

    public LoginRequest(String email, String password, boolean isSupplier) {
        this.email = email;
        this.password = password;
        this.isSupplier = isSupplier;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isSupplier() {
        return isSupplier;
    }

    public void setSupplier(boolean supplier) {
        isSupplier = supplier;
    }
}
