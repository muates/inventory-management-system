package com.muates.inventorymanagementsystem.model.dto.response;

public class ProductResponse {

    private Integer id;
    private String name;
    private Integer stockQuantity;
    private Double price;
    private Double discount;
    private Integer supplierId;

    public ProductResponse() {
    }

    public ProductResponse(Integer id, String name, Integer stockQuantity, Double price, Double discount, Integer supplierId) {
        this.id = id;
        this.name = name;
        this.stockQuantity = stockQuantity;
        this.price = price;
        this.discount = discount;
        this.supplierId = supplierId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }
}
