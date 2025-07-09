package com.example.e_com_project.service;

import com.example.e_com_project.dto.OrderNotificationDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.e_com_project.RabbitmqConfig.RabbitMQconfig.*;

@Service
public class TelegramPublisherService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendProductNotification(OrderNotificationDto dto) {
        rabbitTemplate.convertAndSend(EXCHANGE, PRODUCT_ROUTING_KEY, dto);
    }

    public void sendOrderNotification(OrderNotificationDto dto) {
        rabbitTemplate.convertAndSend(EXCHANGE, ORDER_ROUTING_KEY, dto);
    }
}
