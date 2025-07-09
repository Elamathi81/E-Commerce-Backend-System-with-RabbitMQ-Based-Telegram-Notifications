package com.example.e_com_project.listeners;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Component
public class TelegramMessageListener {

    private static final String BOT_TOKEN = "7677609678:AAFEIP4lwdXHsWGdQWBuEDSRanC4qac8wZ0";
    private static final String CHAT_ID = "1982851626";

    @RabbitListener(queues = "telegramQueue")
    public void receiveMessage(String message) {
        System.out.println("📥 Received Telegram text: " + message);
        sendPlainTextToTelegram(message);
    }

    private void sendPlainTextToTelegram(String message) {
        try {
            // Prepare the Telegram API URL
            String urlString = "https://api.telegram.org/bot" + BOT_TOKEN + "/sendMessage";

            String payload = String.format(
                    "{\"chat_id\":\"%s\",\"text\":\"%s\"}",
                    CHAT_ID,
                    message.replace("\"", "\\\"") // Escape quotes
            );

            // Open HTTP connection
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            // Send payload
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = payload.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                System.out.println("✅ Message sent to Telegram successfully!");
            } else {
                System.out.println("❌ Failed to send message to Telegram. Code: " + responseCode);
            }

        } catch (Exception e) {
            System.err.println("❌ Error sending message to Telegram: " + e.getMessage());
        }
    }
}
