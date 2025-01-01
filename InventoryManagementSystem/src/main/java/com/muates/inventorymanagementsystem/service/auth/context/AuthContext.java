package com.muates.inventorymanagementsystem.service.auth.context;

import com.muates.inventorymanagementsystem.service.auth.strategy.AuthStrategy;

public class AuthContext {

    private static AuthContext instance;
    private AuthStrategy authStrategy;

    private AuthContext(AuthStrategy authStrategy) {
        this.authStrategy = authStrategy;
    }

    public static AuthContext getInstance(AuthStrategy authStrategy) {
        if (instance == null) {
            instance = new AuthContext(authStrategy);
        } else {
            instance.authStrategy = authStrategy;
        }
        return instance;
    }

    public Integer authenticate(String email, String password) {
        return authStrategy.authenticate(email, password);
    }

    public void register(Object request) {
        authStrategy.register(request);
    }
}
