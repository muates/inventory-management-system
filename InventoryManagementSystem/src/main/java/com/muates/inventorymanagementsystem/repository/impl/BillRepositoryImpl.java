package com.muates.inventorymanagementsystem.repository.impl;

import com.muates.inventorymanagementsystem.common.repository.impl.BaseRepositoryImpl;
import com.muates.inventorymanagementsystem.model.entity.Bill;
import com.muates.inventorymanagementsystem.repository.BillRepository;

import java.sql.Connection;

public class BillRepositoryImpl extends BaseRepositoryImpl<Bill, Integer> implements BillRepository {

    public BillRepositoryImpl(Connection connection) {
        super(connection, Bill.class);
    }
}
