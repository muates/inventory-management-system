package com.muates.inventorymanagementsystem.model.entity;

import java.util.Objects;

public class Retailer {

    private Integer id;
    private String name;
    private String phoneNumber;
    private String emailAddress;
    private String password;
    private String photoUrl;

    public Retailer() {
    }

    public Retailer(String name, String phoneNumber, String emailAddress, String password) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public Retailer(String name, String phoneNumber, String emailAddress, String password, String photoUrl) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.password = password;
        this.photoUrl = photoUrl;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Retailer retailer = (Retailer) o;
        return Objects.equals(id, retailer.id)
                && Objects.equals(name, retailer.name)
                && Objects.equals(phoneNumber, retailer.phoneNumber)
                && Objects.equals(emailAddress, retailer.emailAddress)
                && Objects.equals(password, retailer.password)
                && Objects.equals(photoUrl, retailer.photoUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phoneNumber, emailAddress, password, photoUrl);
    }

    @Override
    public String toString() {
        return "Retailer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                '}';
    }
}
