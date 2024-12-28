package com.muates.inventorymanagementsystem.repository.impl;

import com.muates.inventorymanagementsystem.common.repository.impl.BaseRepositoryImpl;
import com.muates.inventorymanagementsystem.model.entity.BillDetail;
import com.muates.inventorymanagementsystem.repository.BillDetailRepository;

import java.sql.Connection;

public class BillDetailRepositoryImpl extends BaseRepositoryImpl<BillDetail, Integer> implements BillDetailRepository {

    public BillDetailRepositoryImpl(Connection connection) {
        super(connection, BillDetail.class);
    }
}
