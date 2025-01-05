package com.muates.inventorymanagementsystem.model.dto.product.response;

import com.muates.inventorymanagementsystem.model.dto.productphoto.response.ProductPhotoResponse;

import java.math.BigDecimal;
import java.util.List;

public class ProductResponse {

    private Integer id;
    private String name;
    private Integer stockQuantity;
    private BigDecimal price;
    private BigDecimal discount;
    private Integer supplierId;
    List<ProductPhotoResponse> photos;

    public ProductResponse() {
    }

    public ProductResponse(Integer id, String name, Integer stockQuantity, BigDecimal price, BigDecimal discount, Integer supplierId) {
        this.id = id;
        this.name = name;
        this.stockQuantity = stockQuantity;
        this.price = price;
        this.discount = discount;
        this.supplierId = supplierId;
    }

    public ProductResponse(Integer id, String name, Integer stockQuantity, BigDecimal price, BigDecimal discount, Integer supplierId, List<ProductPhotoResponse> photos) {
        this.id = id;
        this.name = name;
        this.stockQuantity = stockQuantity;
        this.price = price;
        this.discount = discount;
        this.supplierId = supplierId;
        this.photos = photos;
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

    public List<ProductPhotoResponse> getPhotos() {
        return photos;
    }

    public void setPhotos(List<ProductPhotoResponse> photos) {
        this.photos = photos;
    }
}
