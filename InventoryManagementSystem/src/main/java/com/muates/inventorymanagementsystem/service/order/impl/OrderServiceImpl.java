package com.muates.inventorymanagementsystem.service.order.impl;

import com.muates.inventorymanagementsystem.common.ioc.Inject;
import com.muates.inventorymanagementsystem.converter.BillConverter;
import com.muates.inventorymanagementsystem.converter.BillDetailConverter;
import com.muates.inventorymanagementsystem.exception.ErrorCode;
import com.muates.inventorymanagementsystem.exception.handler.GlobalExceptionHandler;
import com.muates.inventorymanagementsystem.model.dto.bill.response.BillResponse;
import com.muates.inventorymanagementsystem.model.dto.order.response.OrderResponse;
import com.muates.inventorymanagementsystem.model.entity.Bill;
import com.muates.inventorymanagementsystem.model.entity.BillDetail;
import com.muates.inventorymanagementsystem.model.entity.Product;
import com.muates.inventorymanagementsystem.model.enums.BillStatus;
import com.muates.inventorymanagementsystem.repository.BillDetailRepository;
import com.muates.inventorymanagementsystem.repository.BillRepository;
import com.muates.inventorymanagementsystem.repository.ProductRepository;
import com.muates.inventorymanagementsystem.service.order.OrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

    @Inject
    private BillRepository billRepository;

    @Inject
    private BillDetailRepository billDetailRepository;

    @Inject
    private ProductRepository productRepository;

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

    @Override
    public void approveOrder(Integer billId) {
        Bill existBill = billRepository.findById(billId);

        if (existBill != null) {
            List<BillDetail> existBillDetails = billDetailRepository.findByBillId(billId);

            List<Integer> productIds = existBillDetails.stream()
                    .map(BillDetail::getProductId)
                    .collect(Collectors.toList());

            List<Product> existProducts = productRepository.findAllByIds(productIds);

            Map<Integer, Product> productMap = existProducts.stream()
                    .collect(Collectors.toMap(Product::getId, product -> product));

            existBillDetails.forEach(billDetail -> {
                Product product = productMap.get(billDetail.getProductId());

                if (product != null) {
                    if (billDetail.getQuantity() > product.getStockQuantity()) {
                        GlobalExceptionHandler.handle(ErrorCode.PRODUCT_OUT_OF_STOCK);
                    }
                } else {
                    GlobalExceptionHandler.handle(ErrorCode.PRODUCT_NOT_FOUND);
                }
            });

            existBill.setStatus(BillStatus.APPROVED);
            billRepository.update(existBill);
        } else {
            GlobalExceptionHandler.handle(ErrorCode.BILL_NOT_FOUND);
        }
    }

    @Override
    public void rejectOrder(Integer billId) {
        Bill existBill = billRepository.findById(billId);

        if (existBill != null) {
            existBill.setStatus(BillStatus.REJECTED);
            billRepository.update(existBill);
        } else {
            GlobalExceptionHandler.handle(ErrorCode.BILL_NOT_FOUND);
        }
    }
}
