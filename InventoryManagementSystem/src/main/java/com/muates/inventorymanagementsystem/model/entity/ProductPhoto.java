package com.muates.inventorymanagementsystem.model.entity;

import java.util.Objects;

public class ProductPhoto {

    private Integer id;
    private Integer productId;
    private String photoUrl;
    private Boolean isPrimary;

    public ProductPhoto() {
    }

    public ProductPhoto(Integer productId, String photoUrl) {
        this.productId = productId;
        this.photoUrl = photoUrl;
    }

    public ProductPhoto(Integer productId, String photoUrl, Boolean isPrimary) {
        this.productId = productId;
        this.photoUrl = photoUrl;
        this.isPrimary = isPrimary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Boolean getPrimary() {
        return isPrimary;
    }

    public void setPrimary(Boolean primary) {
        isPrimary = primary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductPhoto that = (ProductPhoto) o;
        return Objects.equals(id, that.id)
                && Objects.equals(productId, that.productId)
                && Objects.equals(photoUrl, that.photoUrl)
                && Objects.equals(isPrimary, that.isPrimary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productId, photoUrl, isPrimary);
    }

    @Override
    public String toString() {
        return "ProductPhoto{" +
                "id=" + id +
                ", productId=" + productId +
                ", photoUrl='" + photoUrl + '\'' +
                ", isPrimary=" + isPrimary +
                '}';
    }
}
