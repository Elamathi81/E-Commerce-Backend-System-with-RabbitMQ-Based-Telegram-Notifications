package com.example.e_com_project.dto;

import java.io.Serializable;
import java.math.BigDecimal;


public class OrderNotificationDto implements Serializable {
    private Long orderId;
    private String userEmail;
    private BigDecimal totalAmount;
    private String status;

    public OrderNotificationDto() {}

    public OrderNotificationDto(Long orderId, String userEmail, BigDecimal totalAmount, String status) {
        this.orderId = orderId;
        this.userEmail = userEmail;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderNotificationDto{" +
                "orderId=" + orderId +
                ", userEmail='" + userEmail + '\'' +
                ", totalAmount=" + totalAmount +
                ", status='" + status + '\'' +
                '}';
    }
}
