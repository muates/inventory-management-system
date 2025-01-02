package com.muates.inventorymanagementsystem.service.cart;

import com.muates.inventorymanagementsystem.model.dto.bill.response.BillResponse;
import com.muates.inventorymanagementsystem.model.dto.cart.request.CartRequest;

import java.util.List;

public interface CartService {

    List<BillResponse> processCart(CartRequest request);
}
