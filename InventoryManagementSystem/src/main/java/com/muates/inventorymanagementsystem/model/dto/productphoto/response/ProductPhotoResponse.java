package com.muates.inventorymanagementsystem.model.dto.productphoto.response;

public class ProductPhotoResponse {

    private Integer id;
    private Integer productId;
    private String photoUrl;
    private Boolean isPrimary;

    public ProductPhotoResponse() {
    }

    public ProductPhotoResponse(Integer id, Integer productId, String photoUrl, Boolean isPrimary) {
        this.id = id;
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
}
