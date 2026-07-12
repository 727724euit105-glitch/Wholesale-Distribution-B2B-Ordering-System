package com.karthick.Wholesale_distribution_B2B_orderiing_System.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class DealerRequestDTO {
    @NotBlank
    private String dealerName;
    @NotBlank
    private String shopName;
    @Pattern(regexp = "^[0-9][10]$",
    message = "Mobile number must contain exactly 10 digits")
    private String mobile;
    @Email
    private String email;
    @NotBlank
    private String gstNumber;
    @NotBlank
    private String address;

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
