package com.muates.inventorymanagementsystem.model.dto.cart.request;

import java.util.List;

public class CartRequest {

    private Integer retailerId;
    private List<CartItem> items;

    public Integer getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(Integer retailerId) {
        this.retailerId = retailerId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }
}
