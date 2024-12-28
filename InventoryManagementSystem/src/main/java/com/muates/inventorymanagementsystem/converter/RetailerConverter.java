package com.muates.inventorymanagementsystem.converter;

import com.muates.inventorymanagementsystem.model.dto.request.RetailerCreateRequest;
import com.muates.inventorymanagementsystem.model.dto.response.RetailerResponse;
import com.muates.inventorymanagementsystem.model.entity.Retailer;

public class RetailerConverter {

    public static Retailer toEntity(RetailerCreateRequest request) {

        if (request == null) {
            return null;
        }

        String photo = request.getPhotoUrl() != null && !request.getPhotoUrl().isEmpty()
                ? request.getPhotoUrl()
                : "https://picsum.photos/250/250";

        return new Retailer(
                request.getName(),
                request.getPhoneNumber(),
                request.getEmailAddress(),
                request.getPassword(),
                photo
        );
    }

    public static RetailerResponse toDto(Retailer entity) {

        if (entity == null) {
            return null;
        }

        return new RetailerResponse(
                entity.getId(),
                entity.getName(),
                entity.getPhoneNumber(),
                entity.getEmailAddress(),
                entity.getPhotoUrl()
        );
    }
}
