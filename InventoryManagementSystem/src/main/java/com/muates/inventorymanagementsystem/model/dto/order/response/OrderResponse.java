package com.muates.inventorymanagementsystem.model.dto.order.response;

import com.muates.inventorymanagementsystem.model.dto.bill.response.BillResponse;

public class OrderResponse {

    private BillResponse bill;

    public OrderResponse() {
    }

    public OrderResponse(BillResponse bill) {
        this.bill = bill;
    }

    public BillResponse getBill() {
        return bill;
    }

    public void setBill(BillResponse bill) {
        this.bill = bill;
    }
}
