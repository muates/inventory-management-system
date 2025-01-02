package com.muates.inventorymanagementsystem.service.cart.impl;

import com.muates.inventorymanagementsystem.common.ioc.Inject;
import com.muates.inventorymanagementsystem.converter.BillConverter;
import com.muates.inventorymanagementsystem.converter.BillDetailConverter;
import com.muates.inventorymanagementsystem.model.dto.bill.response.BillResponse;
import com.muates.inventorymanagementsystem.model.dto.cart.request.CartItem;
import com.muates.inventorymanagementsystem.model.dto.cart.request.CartRequest;
import com.muates.inventorymanagementsystem.model.entity.Bill;
import com.muates.inventorymanagementsystem.model.entity.BillDetail;
import com.muates.inventorymanagementsystem.repository.BillDetailRepository;
import com.muates.inventorymanagementsystem.repository.BillRepository;
import com.muates.inventorymanagementsystem.service.cart.CartService;

import java.math.BigDecimal;
import java.util.*;

public class CartServiceImpl implements CartService {

    @Inject
    private BillRepository billRepository;

    @Inject
    private BillDetailRepository billDetailRepository;


    @Override
    public List<BillResponse> processCart(CartRequest request) {
        Map<Integer, List<CartItem>> supplierGroupedItems = groupItemsBySupplier(request.getItems());
        List<Bill> bills = new ArrayList<>();

        for (Map.Entry<Integer, List<CartItem>> entry : supplierGroupedItems.entrySet()) {
            Integer supplierId = entry.getKey();
            List<CartItem> items = entry.getValue();

            Bill bill = createBill(request.getRetailerId(), supplierId, items);

            saveBillDetails(bill.getId(), items);

            bills.add(bill);
        }

        return BillConverter.toDto(bills);
    }

    private Map<Integer, List<CartItem>> groupItemsBySupplier(List<CartItem> items) {
        Map<Integer, List<CartItem>> supplierGroupedItems = new HashMap<>();

        for (CartItem item : items) {
            Integer supplierId = item.getSupplierId();
            supplierGroupedItems
                    .computeIfAbsent(supplierId, k -> new ArrayList<>())
                    .add(item);
        }

        return supplierGroupedItems;
    }

    private Bill createBill(Integer retailerId, Integer supplierId, List<CartItem> items) {
        Bill bill = BillConverter.toEntity(supplierId, retailerId);

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (CartItem item : items) {
            BigDecimal itemTotalPrice = item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            totalAmount = totalAmount.add(itemTotalPrice);
        }

        bill.setTotalAmount(totalAmount);

        billRepository.save(bill);

        return bill;
    }

    private void saveBillDetails(Integer billId, List<CartItem> items) {
        List<BillDetail> billDetails = new ArrayList<>();

        for (CartItem item : items) {
            BigDecimal itemTotalPrice = item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity()));

            BillDetail billDetail = BillDetailConverter.toEntity(item, billId, itemTotalPrice);
            billDetails.add(billDetail);
        }

        billDetailRepository.saveAll(billDetails);
    }
}
