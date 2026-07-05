package com.karthick.Wholesale_distribution_B2B_orderiing_System.entity;

import jakarta.persistence.*;

@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private String category;
    private String brand;
    private String packSize;
    private String hsnCode;
    private Double gstRate;
    private Double price;
    private String description;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getPackSize() {
        return packSize;
    }
    public void setPackSize(String packSize) {
        this.packSize = packSize;
    }
    public String getHsnCode() {
        return hsnCode;
    }
    public void setHsnCode(String hsnCode) {
        this.hsnCode = hsnCode;
    }
    public Double getGstRate() {
        return gstRate;
    }
    public void setGstRate(Double gstRate) {
        this.gstRate = gstRate;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
