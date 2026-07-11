package com.karthick.Wholesale_distribution_B2B_orderiing_System.dto;

public class DealerResponseDTO {
    private Long id;
    private String dealerName;
    private String shopName;
    private String mobile;
    private String email;
    private String gstNumber;
    private String address;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDealerName() {
        return dealerName;
    }
    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }
    public String getShopName() {
        return shopName;
    }
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getGstNumber() {
        return gstNumber;
    }
    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
