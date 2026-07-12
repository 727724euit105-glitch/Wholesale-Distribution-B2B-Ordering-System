package com.karthick.Wholesale_distribution_B2B_orderiing_System.dto;

import java.time.LocalDate;

//the request was used to store the request from client by using react ,here we are not use the dealer object because it does not knows
public class OrderRequestDTO {
    private Long dealerId;
    private String status;
    private LocalDate orderDate;

    public Long getDealerId() {
        return dealerId;
    }

    public void setDealerId(Long dealerId) {
        this.dealerId = dealerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
}
