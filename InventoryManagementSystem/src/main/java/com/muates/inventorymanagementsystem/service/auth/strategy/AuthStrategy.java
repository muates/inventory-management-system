package com.muates.inventorymanagementsystem.service.auth.strategy;

public interface AuthStrategy {
    Integer authenticate(String email, String password);
    void register(Object request);
}
