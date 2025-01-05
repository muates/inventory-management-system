package com.muates.inventorymanagementsystem.converter;

import com.muates.inventorymanagementsystem.model.dto.productphoto.response.ProductPhotoResponse;
import com.muates.inventorymanagementsystem.model.entity.ProductPhoto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ProductPhotoConverter {

    public static ProductPhotoResponse toDto(ProductPhoto entity) {
        if (entity == null) {
            return null;
        }

        return new ProductPhotoResponse(
                entity.getId(),
                entity.getProductId(),
                entity.getPhotoUrl(),
                entity.getPrimary()
        );
    }

    public static List<ProductPhotoResponse> toDto(List<ProductPhoto> entities) {
        if (entities.isEmpty()) {
            return Collections.emptyList();
        }

        return entities.stream().map(ProductPhotoConverter::toDto).collect(Collectors.toList());
    }
}
