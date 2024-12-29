package com.muates.inventorymanagementsystem.converter;

import com.muates.inventorymanagementsystem.model.dto.product.request.ProductCreateRequest;
import com.muates.inventorymanagementsystem.model.dto.product.response.ProductResponse;
import com.muates.inventorymanagementsystem.model.entity.Product;

public class ProductConverter {

    public static Product toEntity(ProductCreateRequest request) {

        if (request == null) {
            return null;
        }

        return new Product(
                request.getName(),
                request.getStockQuantity(),
                request.getPrice(),
                request.getDiscount(),
                request.getSupplierId()
        );
    }

    public static ProductResponse toDto(Product entity) {

        if (entity == null) {
            return null;
        }

        return new ProductResponse(
                entity.getId(),
                entity.getName(),
                entity.getStockQuantity(),
                entity.getPrice(),
                entity.getDiscount(),
                entity.getSupplierId()
        );
    }
}
