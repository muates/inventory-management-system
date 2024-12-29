package com.muates.inventorymanagementsystem.service.auth;

import com.muates.inventorymanagementsystem.model.dto.auth.request.LoginRequest;

public interface AuthService {
    void register(Object request);
    boolean login(LoginRequest loginRequest, boolean isSupplier);
}
