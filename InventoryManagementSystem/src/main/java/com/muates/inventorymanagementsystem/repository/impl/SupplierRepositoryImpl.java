package com.muates.inventorymanagementsystem.repository.impl;

import com.muates.inventorymanagementsystem.common.repository.impl.BaseRepositoryImpl;
import com.muates.inventorymanagementsystem.model.entity.Supplier;
import com.muates.inventorymanagementsystem.repository.SupplierRepository;

import java.sql.Connection;

public class SupplierRepositoryImpl extends BaseRepositoryImpl<Supplier, Integer> implements SupplierRepository {

    public SupplierRepositoryImpl(Connection connection) {
        super(connection, Supplier.class);
    }
}
