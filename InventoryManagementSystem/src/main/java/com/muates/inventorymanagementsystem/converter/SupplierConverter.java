package com.muates.inventorymanagementsystem.converter;

import com.muates.inventorymanagementsystem.model.dto.supplier.request.SupplierCreateRequest;
import com.muates.inventorymanagementsystem.model.dto.supplier.response.SupplierResponse;
import com.muates.inventorymanagementsystem.model.entity.Supplier;

public class SupplierConverter {

    public static Supplier toEntity(SupplierCreateRequest request) {

        if (request == null) {
            return null;
        }

        String photo = request.getPhotoUrl() != null && !request.getPhotoUrl().isEmpty()
                ? request.getPhotoUrl()
                : "https://picsum.photos/250/250";

        return new Supplier(
                request.getName(),
                request.getPhoneNumber(),
                request.getEmailAddress(),
                request.getPassword(),
                photo
        );
    }

    public static SupplierResponse toDto(Supplier entity) {

        if (entity == null) {
            return null;
        }

        return new SupplierResponse(
                entity.getId(),
                entity.getName(),
                entity.getPhoneNumber(),
                entity.getEmailAddress(),
                entity.getPhotoUrl()
        );
    }
}
