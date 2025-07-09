package com.example.e_com_project.dto;

import java.math.BigDecimal;

public class orderdto {
    private Long orderId;
    private String email;
    private BigDecimal amount;
    private String status;

    public orderdto(Long orderId, String email, BigDecimal amount, String status) {
        this.orderId = orderId;
        this.email = email;
        this.amount = amount;
        this.status = status;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
