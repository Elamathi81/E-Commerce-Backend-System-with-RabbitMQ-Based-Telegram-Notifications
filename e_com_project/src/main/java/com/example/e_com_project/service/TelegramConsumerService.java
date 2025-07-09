package com.example.e_com_project.service;

import com.example.e_com_project.dto.OrderNotificationDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TelegramConsumerService {

    @Autowired
    private TelegramBotService telegramBotService;
    @RabbitListener(queues = "order.queue")
    public void receiveOrderNotification(OrderNotificationDto message) {
        String telegramMessage = String.format(
                "🛒 *New Order Placed!*\n\n🆔 Order ID: `%d`\n💰 Amount: ₹%.2f\n📦 Status: *%s*",
                message.getOrderId(),
                message.getTotalAmount(),
                message.getStatus()
        );
        telegramBotService.sendMessageToTelegram(telegramMessage);
    }

    @RabbitListener(queues = "product.queue")
    public void receiveProductNotification(OrderNotificationDto message) {
        String telegramMessage = String.format(
                "🆕 *New Product Added!*\n💰 Price: ₹%.2f", message.getTotalAmount()
        );
        telegramBotService.sendMessageToTelegram(telegramMessage);
    }
}
