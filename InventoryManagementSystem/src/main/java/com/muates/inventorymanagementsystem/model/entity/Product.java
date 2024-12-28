package com.muates.inventorymanagementsystem.model.entity;

import java.util.Objects;

public class Product {

    private Integer id;
    private String name;
    private Integer stockQuantity;
    private Double price;
    private Double discount;

    public Product() {
    }

    public Product(String name, Integer stockQuantity, Double price) {
        this.name = name;
        this.stockQuantity = stockQuantity;
        this.price = price;
    }

    public Product(String name, Integer stockQuantity, Double price, Double discount) {
        this.name = name;
        this.stockQuantity = stockQuantity;
        this.price = price;
        this.discount = discount;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id)
                && Objects.equals(name, product.name)
                && Objects.equals(stockQuantity, product.stockQuantity)
                && Objects.equals(price, product.price)
                && Objects.equals(discount, product.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, stockQuantity, price, discount);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stockQuantity=" + stockQuantity +
                ", price=" + price +
                ", discount=" + discount +
                '}';
    }
}
