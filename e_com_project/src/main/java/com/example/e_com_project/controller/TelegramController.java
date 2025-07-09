package com.example.e_com_project.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/telegram")
public class TelegramController {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.product.routing.key}")
    private String productRoutingKey;

    @Value("${rabbitmq.order.routing.key}")
    private String orderRoutingKey;

    public TelegramController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    // Endpoint to test product notification
    @PostMapping("/product")
    public ResponseEntity<String> sendProductMessage(@RequestBody String message) {
        rabbitTemplate.convertAndSend(exchangeName, productRoutingKey, message);
        return ResponseEntity.ok("✅ Product message sent to queue: " + message);
    }

    // Endpoint to test order notification
    @PostMapping("/order")
    public ResponseEntity<String> sendOrderMessage(@RequestBody String message) {
        rabbitTemplate.convertAndSend(exchangeName, orderRoutingKey, message);
        return ResponseEntity.ok("✅ Order message sent to queue: " + message);
    }
}
