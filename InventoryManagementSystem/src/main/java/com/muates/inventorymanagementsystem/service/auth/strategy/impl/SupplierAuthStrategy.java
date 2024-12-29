package com.muates.inventorymanagementsystem.service.auth.strategy.impl;

import com.muates.inventorymanagementsystem.common.ioc.Inject;
import com.muates.inventorymanagementsystem.converter.SupplierConverter;
import com.muates.inventorymanagementsystem.model.dto.supplier.request.SupplierCreateRequest;
import com.muates.inventorymanagementsystem.model.entity.Supplier;
import com.muates.inventorymanagementsystem.repository.SupplierRepository;
import com.muates.inventorymanagementsystem.service.auth.helper.PasswordHelper;
import com.muates.inventorymanagementsystem.service.auth.strategy.AuthStrategy;

public class SupplierAuthStrategy implements AuthStrategy {

    @Inject
    private SupplierRepository supplierRepository;

    @Override
    public boolean authenticate(String email, String password) {
        Supplier supplier = supplierRepository.findByEmailAddress(email);
        return supplier != null && PasswordHelper.checkPassword(password, supplier.getPassword());
    }

    @Override
    public void register(Object request) {
        SupplierCreateRequest supplierCreateRequest = (SupplierCreateRequest) request;
        supplierCreateRequest.setPassword(PasswordHelper.hashPassword(supplierCreateRequest.getPassword()));
        supplierRepository.save(SupplierConverter.toEntity(supplierCreateRequest));
    }
}
