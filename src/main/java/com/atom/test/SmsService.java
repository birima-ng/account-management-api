package com.atom.test;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SmsService {

    private final String smsGatewayUrl = "http://192.168.1.5:8082/send"; // URL de votre passerelle SMS

    public void sendSms(String phoneNumber, String message) {
        RestTemplate restTemplate = new RestTemplate();
System.out.println("########### entre");
        // Créer le corps de la requête
        String requestBody = "{ \"phone\": \"" + phoneNumber + "\", \"message\": \"" + message + "\" }";

        // Créer les headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // Envoyer la requête
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.exchange(smsGatewayUrl, HttpMethod.POST, request, String.class);

        System.out.println("SMS envoyé avec succès : " + response.getBody());
    }
}
