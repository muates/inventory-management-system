package com.muates.inventorymanagementsystem.service.auth.strategy.impl;

import com.muates.inventorymanagementsystem.common.ioc.Inject;
import com.muates.inventorymanagementsystem.converter.RetailerConverter;
import com.muates.inventorymanagementsystem.model.dto.retailer.request.RetailerCreateRequest;
import com.muates.inventorymanagementsystem.model.entity.Retailer;
import com.muates.inventorymanagementsystem.repository.RetailerRepository;
import com.muates.inventorymanagementsystem.service.auth.helper.PasswordHelper;
import com.muates.inventorymanagementsystem.service.auth.strategy.AuthStrategy;

public class RetailerAuthStrategy implements AuthStrategy {

    @Inject
    private RetailerRepository retailerRepository;

    @Override
    public Integer authenticate(String email, String password) {
        Retailer retailer = retailerRepository.findByEmailAddress(email);
        if (retailer != null && PasswordHelper.checkPassword(password, retailer.getPassword())) {
            return retailer.getId();
        }

        return null;
    }

    @Override
    public void register(Object request) {
        RetailerCreateRequest retailerCreateRequest = (RetailerCreateRequest) request;
        retailerCreateRequest.setPassword(PasswordHelper.hashPassword(retailerCreateRequest.getPassword()));
        retailerRepository.save(RetailerConverter.toEntity(retailerCreateRequest));
    }
}
