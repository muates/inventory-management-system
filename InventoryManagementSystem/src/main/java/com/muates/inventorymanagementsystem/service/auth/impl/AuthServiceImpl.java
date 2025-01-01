package com.muates.inventorymanagementsystem.service.auth.impl;

import com.muates.inventorymanagementsystem.common.ioc.DependencyManager;
import com.muates.inventorymanagementsystem.model.dto.auth.request.LoginRequest;
import com.muates.inventorymanagementsystem.model.dto.retailer.request.RetailerCreateRequest;
import com.muates.inventorymanagementsystem.model.dto.supplier.request.SupplierCreateRequest;
import com.muates.inventorymanagementsystem.service.auth.AuthService;
import com.muates.inventorymanagementsystem.service.auth.context.AuthContext;
import com.muates.inventorymanagementsystem.service.auth.strategy.AuthStrategy;

public class AuthServiceImpl implements AuthService {

    @Override
    public void register(Object request) {
        AuthStrategy authStrategy;

        if (request instanceof SupplierCreateRequest) {
            authStrategy = DependencyManager.getContainer().resolve(AuthStrategy.class, "supplier");
        } else if (request instanceof RetailerCreateRequest) {
            authStrategy = DependencyManager.getContainer().resolve(AuthStrategy.class, "retailer");
        } else {
            throw new IllegalArgumentException("Invalid request type");
        }

        AuthContext authContext = AuthContext.getInstance(authStrategy);
        authContext.register(request);
    }


    @Override
    public Integer login(LoginRequest loginRequest, boolean isSupplier) {
        AuthStrategy authStrategy;

        if (isSupplier) {
            authStrategy = DependencyManager.getContainer().resolve(AuthStrategy.class, "supplier");
        } else {
            authStrategy = DependencyManager.getContainer().resolve(AuthStrategy.class, "retailer");
        }

        AuthContext authContext = AuthContext.getInstance(authStrategy);
        return authContext.authenticate(loginRequest.getEmailAddress(), loginRequest.getPassword());
    }

}
