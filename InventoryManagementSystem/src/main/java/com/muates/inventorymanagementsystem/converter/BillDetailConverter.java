package com.muates.inventorymanagementsystem.converter;

import com.muates.inventorymanagementsystem.model.dto.billdetail.response.BillDetailResponse;
import com.muates.inventorymanagementsystem.model.dto.cart.request.CartItem;
import com.muates.inventorymanagementsystem.model.entity.BillDetail;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class BillDetailConverter {

    public static BillDetail toEntity(CartItem item, Integer billId, BigDecimal totalPrice) {
        if (item == null || billId == null) {
            return null;
        }

        return new BillDetail(
                billId,
                item.getProductId(),
                item.getQuantity(),
                item.getUnitPrice(),
                totalPrice
        );
    }

    public static BillDetailResponse toDto(BillDetail entity) {
        if (entity == null) {
            return null;
        }

        return new BillDetailResponse(
                entity.getId(),
                entity.getBillId(),
                entity.getProductId(),
                entity.getQuantity(),
                entity.getUnitPrice(),
                entity.getTotalPrice()
        );
    }

    public static List<BillDetailResponse> toDto(List<BillDetail> entities) {
        return entities.stream().map(BillDetailConverter::toDto).collect(Collectors.toList());
    }
}
