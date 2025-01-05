package com.muates.inventorymanagementsystem.model.dto.product.request;

import javax.servlet.http.Part;
import java.math.BigDecimal;
import java.util.List;

public class ProductCreateRequest {

    private String name;
    private Integer stockQuantity;
    private BigDecimal price;
    private BigDecimal discount;
    private Integer supplierId;
    private List<Part> photos;

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

    public List<Part> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Part> photos) {
        this.photos = photos;
    }
}
