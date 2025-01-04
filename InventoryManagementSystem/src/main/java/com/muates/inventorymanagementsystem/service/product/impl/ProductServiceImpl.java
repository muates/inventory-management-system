package com.muates.inventorymanagementsystem.service.product.impl;

import com.muates.inventorymanagementsystem.common.ioc.Inject;
import com.muates.inventorymanagementsystem.converter.ProductConverter;
import com.muates.inventorymanagementsystem.common.mapper.UpdateMapper;
import com.muates.inventorymanagementsystem.exception.ErrorCode;
import com.muates.inventorymanagementsystem.exception.handler.GlobalExceptionHandler;
import com.muates.inventorymanagementsystem.model.dto.product.request.ProductCreateRequest;
import com.muates.inventorymanagementsystem.model.dto.product.request.ProductUpdateRequest;
import com.muates.inventorymanagementsystem.model.dto.product.response.ProductResponse;
import com.muates.inventorymanagementsystem.model.entity.Product;
import com.muates.inventorymanagementsystem.repository.ProductRepository;
import com.muates.inventorymanagementsystem.service.product.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    @Inject
    private ProductRepository productRepository;

    @Override
    public ProductResponse save(ProductCreateRequest request) {
        Product product = ProductConverter.toEntity(request);

        productRepository.save(product);

        return ProductConverter.toDto(product);
    }

    @Override
    public ProductResponse findById(Integer id) {
        Product product = productRepository.findById(id);

        if (product == null) {
            GlobalExceptionHandler.handle(ErrorCode.PRODUCT_NOT_FOUND);
        }

        return ProductConverter.toDto(product);
    }

    @Override
    public List<ProductResponse> findAll() {
        return ProductConverter.toDto(productRepository.findAll());
    }

    @Override
    public List<ProductResponse> findAllBySupplierId(Integer supplierId) {
        return ProductConverter.toDto(productRepository.findAllBySupplierId(supplierId));
    }

    @Override
    public void update(Integer id, ProductUpdateRequest request) {
        Product product = productRepository.findById(id);

        if (product == null) {
            GlobalExceptionHandler.handle(ErrorCode.PRODUCT_NOT_FOUND);
        }

        UpdateMapper.updateFields(request, product);

        productRepository.update(product);
    }

    @Override
    public void deleteById(Integer id) {
        productRepository.delete(id);
    }
}
