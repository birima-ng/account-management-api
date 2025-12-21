package com.atom.artaccount.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;
import java.util.List;


import org.springframework.http.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
@Service
public class SmsService {

	private final JavaMailSender mailSender;

    public SmsService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    
	private final RestTemplate restTemplate = new RestTemplate();
	private static final String ID = "";
	private static final String AUTH_TOKEN = "";

	public void testApi() {


		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder()
		  .uri(URI.create("https://api.orange.com/oauth/v3/token"))
		  .POST(BodyPublishers.ofString("grant_type=client_credentials"))
		  .setHeader("Authorization", "Basic NktSSHljksdj7P...Jjndb6UdnlrT2lOaA==")
		  .setHeader("Content-Type", "application/x-www-form-urlencoded")
		  .setHeader("Accept", "application/json")
		  .build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public  void sendSms(String content, String number) {


			Twilio.init(ID, AUTH_TOKEN);
			Message message = Message.creator(
					new com.twilio.type.PhoneNumber("+221" + number),
					"MG689f9716cf2c332da439e7b247ab2f09",
							content)

							.create();

	        System.out.println(message.getSid());
	    }

	public void sendSms(String content, List<String> numbers) {
		Twilio.init(ID, AUTH_TOKEN);

		numbers.forEach(number -> {
			Message message = Message.creator(
					new PhoneNumber("+221" + number),
					"MG8aae77348b50a53e48a65090323b67a1", // The 'From' number must be a valid Twilio number
					content
			).create();

			System.out.println("Sent message to " + number + " with SID: " + message.getSid());
		});
	}


    public String sendSms(String accessToken, String senderNumber, String recipientNumber, String countrySenderNumber, String message) {
        try {
        	// String encodedSender = URLEncoder.encode("+" + countrySenderNumber, StandardCharsets.ISO_8859_1);//
        	 //System.out.println("encodedSender "+encodedSender);
            // 🔹 URL de l'API Orange (attention à bien encoder le numéro)
            String url = "https://api.orange.com/smsmessaging/v1/outbound/tel:+" + countrySenderNumber + "/requests";

            // 🔹 Préparer les en-têtes
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(accessToken);
System.out.println("Token "+accessToken);
            // 🔹 Corps de la requête
            Map<String, Object> messageContent = new HashMap<>();
            messageContent.put("message", message);

            Map<String, Object> outboundSMSMessageRequest = new HashMap<>();
            outboundSMSMessageRequest.put("address", "tel:+".concat(recipientNumber));
            outboundSMSMessageRequest.put("senderAddress", "tel:+".concat(countrySenderNumber));
            outboundSMSMessageRequest.put("senderName", "".concat(senderNumber));
            outboundSMSMessageRequest.put("outboundSMSTextMessage", messageContent);

            Map<String, Object> requestBody = new HashMap<>();
            System.out.println("outboundSMSMessageRequest "+outboundSMSMessageRequest);
            requestBody.put("outboundSMSMessageRequest", outboundSMSMessageRequest);

            // JSON avec senderName
            String jsonBody = "{"
                    + "\"outboundSMSMessageRequest\": {"
                    + "\"address\": \"tel:+" + recipientNumber + "\","
                    + "\"senderAddress\": \"tel:+" + countrySenderNumber + "\","
                    + "\"senderName\": \"" + senderNumber + "\","
                    + "\"outboundSMSTextMessage\": {"
                    + "\"message\": \"" + message + "\""
                    + "}"
                    + "}"
                    + "}";
            // 🔹 Construire la requête HTTP
           // HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
         // Requête HTTP
            HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);

            // 🔹 Envoyer la requête POST
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
System.out.println("response "+response.toString());
            return response.getBody();

        } catch (Exception e) {
           // e.printStackTrace();
            return "Erreur lors de l’envoi du SMS : " + e.getMessage();
        }
    }
    
    public String generateAccessToken() {
        try {
            String url = "https://api.orange.com/oauth/v3/token";
            // 🔹 Créer les en-têtes
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.setAccept(java.util.List.of(MediaType.APPLICATION_JSON));
            headers.set("Authorization", "Basic bW55OFl0ajZNbEtxdkw3UEF0WkphQmYzT1dDekpvRGY6bGltVTZuNmduZzRFak1zTlJVSW90b3dBVDdRWnNwajM3ZzZRdE81N0F1OXY=");
           // headers.set("Authorization", "Basic NHFyNnR4NzVuUWxxMkhCSG5YRlc5eFdYdVhnZUp5cXM6OEdPQWxpT01NRkVGWDhtUHRLSm5XZE1pcGwwV0xXa2pPNWw1Rm5NVDd0bHA=");
            // 🔹 Corps de la requête
            String body = "grant_type=client_credentials";

            // 🔹 Créer la requête
            HttpEntity<String> entity = new HttpEntity<>(body, headers);

            // 🔹 Appeler l’API Orange
            ResponseEntity<Map> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    entity,
                    Map.class
            );

            // 🔹 Extraire le access_token
            if (response.getBody() != null && response.getBody().containsKey("access_token")) {
                return response.getBody().get("access_token").toString();
            } else {
                return "Token non trouvé dans la réponse : " + response.getBody();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la génération du token : " + e.getMessage();
        }
    }
    
    
    

    public void sendSimpleMail(String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("reservation@govathon.sn");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);
    }

}