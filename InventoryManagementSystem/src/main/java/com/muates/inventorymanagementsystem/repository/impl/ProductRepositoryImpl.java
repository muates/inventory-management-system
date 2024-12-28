package com.muates.inventorymanagementsystem.repository.impl;

import com.muates.inventorymanagementsystem.common.repository.impl.BaseRepositoryImpl;
import com.muates.inventorymanagementsystem.model.entity.Product;
import com.muates.inventorymanagementsystem.repository.ProductRepository;

import java.sql.Connection;

public class ProductRepositoryImpl extends BaseRepositoryImpl<Product, Integer> implements ProductRepository {

    public ProductRepositoryImpl(Connection connection) {
        super(connection, Product.class);
    }
}
