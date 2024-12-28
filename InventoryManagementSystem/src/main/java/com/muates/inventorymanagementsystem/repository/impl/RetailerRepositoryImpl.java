package com.muates.inventorymanagementsystem.repository.impl;

import com.muates.inventorymanagementsystem.common.repository.impl.BaseRepositoryImpl;
import com.muates.inventorymanagementsystem.model.entity.Retailer;
import com.muates.inventorymanagementsystem.repository.RetailerRepository;

import java.sql.Connection;

public class RetailerRepositoryImpl extends BaseRepositoryImpl<Retailer, Integer> implements RetailerRepository {

    public RetailerRepositoryImpl(Connection connection) {
        super(connection, Retailer.class);
    }
}
