package com.atom.artaccount.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WhatsAppService {

    @Value("${whatsapp.api.url}")
    private String apiUrl;

    @Value("${whatsapp.phone.number.id}")
    private String phoneNumberId;

    @Value("${whatsapp.token}")
    private String token;

    private final RestTemplate restTemplate = new RestTemplate();

    public String sendMessage(String to, String messageBody) {

        String url = apiUrl + phoneNumberId + "/messages";

        Map<String, Object> message = new HashMap<>();
        message.put("messaging_product", "whatsapp");
        message.put("to", to);
        message.put("type", "text");

        Map<String, Object> text = new HashMap<>();
        text.put("body", messageBody);

        message.put("text", text);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(message, headers);
        String rep = restTemplate.postForObject(url, request, String.class);
        System.out.println("############### reponse "+rep);
        return rep;
    }
}
