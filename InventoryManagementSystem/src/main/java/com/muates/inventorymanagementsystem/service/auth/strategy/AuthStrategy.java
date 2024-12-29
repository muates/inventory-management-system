package com.muates.inventorymanagementsystem.service.auth.strategy;

public interface AuthStrategy {
    boolean authenticate(String email, String password);
    void register(Object request);
}
