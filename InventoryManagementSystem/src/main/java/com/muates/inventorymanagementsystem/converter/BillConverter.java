package com.muates.inventorymanagementsystem.converter;

import com.muates.inventorymanagementsystem.model.dto.bill.response.BillResponse;
import com.muates.inventorymanagementsystem.model.entity.Bill;
import com.muates.inventorymanagementsystem.model.enums.BillStatus;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public class BillConverter {

    public static BillResponse toDto(Bill entity) {
        if (entity == null) {
            return null;
        }

        return new BillResponse(
                entity.getId(),
                entity.getSupplierId(),
                entity.getRetailerId(),
                entity.getStatus(),
                entity.getTotalAmount(),
                entity.getDate()
        );
    }

    public static List<BillResponse> toDto(List<Bill> entities) {
        return entities.stream().map(BillConverter::toDto).collect(Collectors.toList());
    }

    public static Bill toEntity(Integer supplierId, Integer retailerId) {
        if (supplierId == null || retailerId == null) {
            return null;
        }

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        return new Bill(
                supplierId,
                retailerId,
                BillStatus.PENDING,
                timestamp
        );
    }
}
