package com.muates.inventorymanagementsystem.model.dto.auth.request;

public class LoginRequest {

    private String emailAddress;
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(String email, String password) {
        this.emailAddress = email;
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
