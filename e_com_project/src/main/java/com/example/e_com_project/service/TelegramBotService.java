package com.example.e_com_project.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@Service
public class TelegramBotService {

    @Value("${telegram.bot.token}")
    private String botToken;

    @Value("${telegram.chat.id}")
    private String chatId;

    public void sendMessageToTelegram(String message) {
        RestTemplate restTemplate = new RestTemplate();
        String telegramApiUrl = "https://api.telegram.org/bot" + botToken + "/sendMessage";

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("chat_id", chatId);
        requestBody.put("text", message);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(telegramApiUrl, entity, String.class);
    }
}
