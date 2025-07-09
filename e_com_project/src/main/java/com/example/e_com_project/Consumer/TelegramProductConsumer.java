package com.example.e_com_project.Consumer;

import com.example.e_com_project.service.TelegramBotService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TelegramProductConsumer {

    @Autowired
    private TelegramBotService telegramBotService;

    @RabbitListener(queues = "${rabbitmq.product.queue}")
    public void receiveProductMessage(String message) {
        System.out.println("📥 [ProductQueue] Message: " + message);
        telegramBotService.sendMessageToTelegram(message);
    }
}
