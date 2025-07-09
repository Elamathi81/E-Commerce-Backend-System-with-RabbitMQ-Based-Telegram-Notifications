package com.example.e_com_project.Consumer;
import com.example.e_com_project.service.TelegramBotService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TelegramConsumer {

    @Autowired
    private TelegramBotService telegramBotService;
  public void receiveMessage(String message) {
        System.out.println("Received from RabbitMQ: " + message);
        telegramBotService.sendMessageToTelegram(message);
    }
}

