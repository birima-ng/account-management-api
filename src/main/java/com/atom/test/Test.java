package com.atom.test;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class Test {

	public static void main(String[] args) {
	 String smsGatewayUrl = "http://192.168.1.8:8082/send"; // URL de votre SMS Gateway
     String token = "dc00b3bf-bbe2-430a-b6d5-48e4d1faff12"; // Remplacez par le token vu sur le Gateway

     RestTemplate restTemplate = new RestTemplate();
     String phoneNumber ="774517228";
     String message ="774517228";

     // Créer le corps de la requête
     String requestBody = "{ \"phone\": \"" + phoneNumber + "\", \"message\": \"" + message + "\" }";

     // Créer les headers avec le token d'authentification
     HttpHeaders headers = new HttpHeaders();
     headers.set("Authorization", "Bearer " + token); // Vérifiez que le token est correct
     headers.set("Content-Type", "application/json");

     // Logs pour vérifier le contenu des headers et du body
     System.out.println("Headers envoyés : " + headers.toString());
     System.out.println("Body de la requête : " + requestBody);

     HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

     try {
         // Envoyer la requête POST
         ResponseEntity<String> response = restTemplate.exchange(smsGatewayUrl, HttpMethod.POST, request, String.class);
         System.out.println("Réponse du serveur : " + response.getBody());
     } catch (HttpClientErrorException e) {
         System.out.println("Erreur HTTP : " + e.getStatusCode() + " " + e.getResponseBodyAsString());
     } catch (Exception e) {
         e.printStackTrace();
     }
    
	}

}
