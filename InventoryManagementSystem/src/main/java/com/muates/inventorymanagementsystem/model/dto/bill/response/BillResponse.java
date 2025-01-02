package com.muates.inventorymanagementsystem.model.dto.bill.response;

import com.muates.inventorymanagementsystem.model.enums.BillStatus;

import java.math.BigDecimal;
import java.util.Date;

public class BillResponse {

    private Integer id;
    private Integer supplierId;
    private Integer retailerId;
    private BillStatus status;
    private BigDecimal totalAmount;
    private Date date;

    public BillResponse(Integer id, Integer supplierId, Integer retailerId, BillStatus status, BigDecimal totalAmount, Date date) {
        this.id = id;
        this.supplierId = supplierId;
        this.retailerId = retailerId;
        this.status = status;
        this.totalAmount = totalAmount;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(Integer retailerId) {
        this.retailerId = retailerId;
    }

    public BillStatus getStatus() {
        return status;
    }

    public void setStatus(BillStatus status) {
        this.status = status;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
