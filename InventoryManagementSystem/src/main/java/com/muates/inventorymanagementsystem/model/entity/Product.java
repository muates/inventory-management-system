package com.muates.inventorymanagementsystem.model.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {

    private Integer id;
    private String name;
    private Integer stockQuantity;
    private BigDecimal price;
    private BigDecimal discount;
    private Integer supplierId;

    public Product() {
    }

    public Product(String name, Integer stockQuantity, BigDecimal price, Integer supplierId) {
        this.name = name;
        this.stockQuantity = stockQuantity;
        this.price = price;
        this.supplierId = supplierId;
    }

    public Product(String name, Integer stockQuantity, BigDecimal price, BigDecimal discount, Integer supplierId) {
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id)
                && Objects.equals(name, product.name)
                && Objects.equals(stockQuantity, product.stockQuantity)
                && Objects.equals(price, product.price)
                && Objects.equals(discount, product.discount)
                && Objects.equals(supplierId, product.supplierId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, stockQuantity, price, discount, supplierId);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stockQuantity=" + stockQuantity +
                ", price=" + price +
                ", discount=" + discount +
                ", supplierId=" + supplierId +
                '}';
    }
}
