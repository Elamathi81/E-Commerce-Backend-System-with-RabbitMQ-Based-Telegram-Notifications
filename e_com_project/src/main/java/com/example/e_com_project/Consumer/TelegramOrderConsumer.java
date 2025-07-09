package com.example.e_com_project.Consumer;

import com.example.e_com_project.service.TelegramBotService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TelegramOrderConsumer {

    @Autowired
    private TelegramBotService telegramBotService;

    @RabbitListener(queues = "${rabbitmq.order.queue}")
    public void receiveOrderMessage(String message) {
        System.out.println("📥 [OrderQueue] Message: " + message);
        telegramBotService.sendMessageToTelegram(message);
    }
}
