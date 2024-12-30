package com.muates.inventorymanagementsystem.service.product;

import com.muates.inventorymanagementsystem.model.dto.product.request.ProductCreateRequest;
import com.muates.inventorymanagementsystem.model.dto.product.request.ProductUpdateRequest;
import com.muates.inventorymanagementsystem.model.dto.product.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse save(ProductCreateRequest request);
    ProductResponse findById(Integer id);
    List<ProductResponse> findAll();
    void update(Integer id, ProductUpdateRequest request);
    void deleteById(Integer id);
}
