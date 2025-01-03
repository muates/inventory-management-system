package com.muates.inventorymanagementsystem.service.order.impl;

import com.muates.inventorymanagementsystem.common.ioc.Inject;
import com.muates.inventorymanagementsystem.converter.BillConverter;
import com.muates.inventorymanagementsystem.converter.BillDetailConverter;
import com.muates.inventorymanagementsystem.model.dto.bill.response.BillResponse;
import com.muates.inventorymanagementsystem.model.dto.order.response.OrderResponse;
import com.muates.inventorymanagementsystem.model.entity.Bill;
import com.muates.inventorymanagementsystem.model.entity.BillDetail;
import com.muates.inventorymanagementsystem.repository.BillDetailRepository;
import com.muates.inventorymanagementsystem.repository.BillRepository;
import com.muates.inventorymanagementsystem.service.order.OrderService;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    @Inject
    private BillRepository billRepository;

    @Inject
    private BillDetailRepository billDetailRepository;

    @Override
    public List<OrderResponse> findOrders(Integer userId, boolean isSupplier) {
        List<OrderResponse> responses = new ArrayList<>();

        if (isSupplier) {
            List<Bill> bills = billRepository.findByUserId("supplier_id", userId);

            for (Bill bill : bills) {
                List<BillDetail> billDetails = billDetailRepository.findByBillId(bill.getId());

                BillResponse billResponse = BillConverter.toDto(bill);
                billResponse.setBillDetails(BillDetailConverter.toDto(billDetails));

                responses.add(new OrderResponse(billResponse));
            }
        } else {
            List<Bill> bills = billRepository.findByUserId("retailer_id", userId);

            for (Bill bill : bills) {
                List<BillDetail> billDetails = billDetailRepository.findByBillId(bill.getId());

                BillResponse billResponse = BillConverter.toDto(bill);
                billResponse.setBillDetails(BillDetailConverter.toDto(billDetails));

                responses.add(new OrderResponse(billResponse));
            }
        }

        return responses;
    }
}
