package com.example.e_com_project.controller;

import com.example.e_com_project.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notify")
public class NotificationController {

    @Autowired
    private RabbitMQSender sender;

    @PostMapping
    public ResponseEntity<String> sendTelegramMessage(@RequestParam String message) {
        sender.sendToTelegramQueue(message);
        return ResponseEntity.ok("Message sent to RabbitMQ");
    }
}
