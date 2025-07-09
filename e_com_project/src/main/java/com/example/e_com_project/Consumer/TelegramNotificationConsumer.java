package com.example.e_com_project.Consumer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TelegramNotificationConsumer {

    @Value("${telegram.bot.token}")
    private String botToken;

    @Value("${telegram.chat.id}")
    private String chatId;

    private final RestTemplate restTemplate = new RestTemplate();

    public void receiveMessage(String message) {
        String telegramApiUrl = String.format(
                "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s",
                botToken, chatId, message.replace(" ", "%20") // encode spaces
        );

        try {
            restTemplate.getForObject(telegramApiUrl, String.class);
            System.out.println("✅ Telegram message sent: " + message);
        } catch (Exception e) {
            System.out.println("❌ Failed to send Telegram message: " + e.getMessage());
        }
    }
}
