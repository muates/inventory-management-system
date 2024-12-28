package com.muates.inventorymanagementsystem.repository.impl;

import com.muates.inventorymanagementsystem.common.repository.impl.BaseRepositoryImpl;
import com.muates.inventorymanagementsystem.model.entity.ProductPhoto;
import com.muates.inventorymanagementsystem.repository.ProductPhotoRepository;

import java.sql.Connection;

public class ProductPhotoRepositoryImpl extends BaseRepositoryImpl<ProductPhoto, Integer> implements ProductPhotoRepository {

    public ProductPhotoRepositoryImpl(Connection connection) {
        super(connection, ProductPhoto.class);
    }
}
