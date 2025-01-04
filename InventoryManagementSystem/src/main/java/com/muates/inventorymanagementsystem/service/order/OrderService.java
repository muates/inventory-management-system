package com.muates.inventorymanagementsystem.service.order;

import com.muates.inventorymanagementsystem.model.dto.order.response.OrderResponse;

import java.util.List;

public interface OrderService {
    List<OrderResponse> findOrders(Integer userId, boolean isSupplier);
    void approveOrder(Integer billId);
    void rejectOrder(Integer billId);
}
