package com.muates.inventorymanagementsystem.model.enums;

public enum BillStatus {
    PENDING("pending"),
    APPROVED("approved"),
    COMPLETED("completed"),
    REJECTED("rejected");

    private final String value;

    BillStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
