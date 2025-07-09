package com.example.e_com_project.controller;

import com.example.e_com_project.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired PaymentService paymentService;

    @PostMapping("/{orderId}")
    public ResponseEntity<String> pay(@PathVariable Long orderId) {
        paymentService.payForOrder(orderId);
        return ResponseEntity.ok("Payment processed for Order ID: " + orderId);
    }
}
