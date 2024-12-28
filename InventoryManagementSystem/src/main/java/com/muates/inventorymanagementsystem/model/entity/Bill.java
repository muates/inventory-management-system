package com.muates.inventorymanagementsystem.model.entity;

import com.muates.inventorymanagementsystem.model.enums.BillStatus;

import java.util.Date;
import java.util.Objects;

public class Bill {

    private Integer id;
    private Integer supplierId;
    private Integer retailerId;
    private BillStatus status;
    private Double totalAmount;
    private Date date;

    public Bill() {
    }

    public Bill(Integer supplierId, Integer retailerId, BillStatus status, Double totalAmount, Date date) {
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

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
        return Objects.equals(id, bill.id)
                && Objects.equals(supplierId, bill.supplierId)
                && Objects.equals(retailerId, bill.retailerId)
                && status == bill.status
                && Objects.equals(totalAmount, bill.totalAmount)
                && Objects.equals(date, bill.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, supplierId, retailerId, status, totalAmount, date);
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", supplierId=" + supplierId +
                ", retailerId=" + retailerId +
                ", status=" + status +
                ", totalAmount=" + totalAmount +
                ", date=" + date +
                '}';
    }
}
