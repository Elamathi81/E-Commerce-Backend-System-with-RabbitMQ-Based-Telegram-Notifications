package com.example.e_com_project.dto;

public class PaymentNotificationDto {
    private Long orderId;
    private String status;

    // Constructors
    public PaymentNotificationDto() {}
    public PaymentNotificationDto(Long orderId, String status) {
        this.orderId = orderId;
        this.status = status;
    }

    // Getters & Setters
    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
