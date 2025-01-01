package com.muates.inventorymanagementsystem.repository;

import com.muates.inventorymanagementsystem.common.repository.BaseRepository;
import com.muates.inventorymanagementsystem.model.entity.Product;

import java.util.List;

public interface ProductRepository extends BaseRepository<Product, Integer> {
    List<Product> findAllBySupplierId(Integer supplierId);
}
