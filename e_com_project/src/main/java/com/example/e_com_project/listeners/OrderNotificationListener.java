package com.example.e_com_project.listeners;

import com.example.e_com_project.dto.OrderNotificationDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Component
public class OrderNotificationListener {

    private static final String TELEGRAM_BOT_TOKEN = "7677609678:AAFEIP4lwdXHsWGdQWBuEDSRanC4qac8wZ0";
    private static final String CHAT_ID = "1982851626";

    @RabbitListener(queues = "telegramQueue")
    public void receiveOrderNotification(String message) {
        System.out.println("📥 Received from RabbitMQ: " + message);
        sendToTelegram(message);
    }

    private void sendToTelegram(String message) {
        try {
            String encodedText = URLEncoder.encode(message, "UTF-8");

            String apiUrl = String.format(
                    "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s&parse_mode=Markdown",
                    TELEGRAM_BOT_TOKEN,
                    CHAT_ID,
                    encodedText
            );

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                System.out.println("✅ Telegram message sent.");
            }
            else {
                System.out.println("❌ Failed to send Telegram message. Code: " + responseCode);
            }

        } catch (IOException e) {
            System.out.println("❌ Error sending Telegram message: " + e.getMessage());
        }
    }
}
