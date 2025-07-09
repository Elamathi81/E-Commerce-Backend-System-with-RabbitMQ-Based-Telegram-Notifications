package com.example.e_com_project.listeners;

import com.example.e_com_project.service.TelegramBotService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TelegramQueueListener {

    @Autowired
    private TelegramBotService telegramBotService;

    @RabbitListener(queues = "telegramQueue")
    public void consumeTelegramQueue(String message) {
        System.out.println("📩 Received from RabbitMQ: " + message);
        telegramBotService.sendMessageToTelegram(message);
    }
}
