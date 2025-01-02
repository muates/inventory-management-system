package com.muates.inventorymanagementsystem.converter;

import com.muates.inventorymanagementsystem.model.dto.cart.request.CartItem;
import com.muates.inventorymanagementsystem.model.entity.BillDetail;

import java.math.BigDecimal;

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
}
