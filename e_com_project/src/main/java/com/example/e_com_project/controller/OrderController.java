package com.example.e_com_project.controller;

import com.example.e_com_project.dto.OrderRequest;
import com.example.e_com_project.entity.Order;
import com.example.e_com_project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody Map<Long, Integer> productQuantities) {
        Order order = orderService.placeOrder(productQuantities);
        return ResponseEntity.ok(order);
    }
}
