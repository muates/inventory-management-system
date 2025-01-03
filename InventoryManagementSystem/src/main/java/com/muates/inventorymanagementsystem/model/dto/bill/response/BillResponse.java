package com.muates.inventorymanagementsystem.model.dto.bill.response;

import com.muates.inventorymanagementsystem.model.dto.billdetail.response.BillDetailResponse;
import com.muates.inventorymanagementsystem.model.enums.BillStatus;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class BillResponse {

    private Integer id;
    private Integer supplierId;
    private Integer retailerId;
    private BillStatus status;
    private BigDecimal totalAmount;
    private Timestamp date;
    private List<BillDetailResponse> billDetails;

    public BillResponse() {
    }

    public BillResponse(Integer id, Integer supplierId, Integer retailerId, BillStatus status, BigDecimal totalAmount, Timestamp date) {
        this.id = id;
        this.supplierId = supplierId;
        this.retailerId = retailerId;
        this.status = status;
        this.totalAmount = totalAmount;
        this.date = date;
    }

    public BillResponse(Integer id, Integer supplierId, Integer retailerId, BillStatus status, BigDecimal totalAmount, Timestamp date, List<BillDetailResponse> billDetails) {
        this.id = id;
        this.supplierId = supplierId;
        this.retailerId = retailerId;
        this.status = status;
        this.totalAmount = totalAmount;
        this.date = date;
        this.billDetails = billDetails;
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

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public List<BillDetailResponse> getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(List<BillDetailResponse> billDetails) {
        this.billDetails = billDetails;
    }
}
