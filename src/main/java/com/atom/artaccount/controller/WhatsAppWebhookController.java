package com.atom.artaccount.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.atom.artaccount.Tools;
import com.atom.artaccount.model.Message;
import com.atom.artaccount.service.MessageService;

@RestController
@RequestMapping("/webhook")
public class WhatsAppWebhookController {

    private static final String VERIFY_TOKEN = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA70Toi";
   
    @Autowired
    private MessageService messageService;
    // Vérification du webhook
    @GetMapping
    public ResponseEntity<String> verify(
            @RequestParam("hub.mode") String mode,
            @RequestParam("hub.challenge") String challenge,
            @RequestParam("hub.verify_token") String token) {

        if ("subscribe".equals(mode) && VERIFY_TOKEN.equals(token)) {
            return ResponseEntity.ok(challenge);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    // Réception des messages
    /*@PostMapping
    public ResponseEntity<Void> receiveMessage(@RequestBody Map<String,Object> payload) {
        System.out.println("Message reçu: " + payload);
        Message message = new Message();
        //message.setTelephone(payload.toString());
        //message.setMessage(payload+"");
        messageService.createMessage(message);
        // Tu peux traiter et répondre ici
        return ResponseEntity.ok().build();
        
    }
    */
    @PostMapping
    public ResponseEntity<Void> receiveMessage(@RequestBody Map<String,Object> payload) {
    	RestTemplate restTemplate = new RestTemplate();
		String accessToken = "EAAM4YO8Hr0sBQVLpWOiMBUhu5ZAywkUFPYK6z2FiBmvgH8rO1xBs7CxuBRaICWd8HZBrWUecxrZCxL7ZCYZB6WssyaSyh3KCyyCbkzbnmAZCuxnVYYsXrudTeKSZCLcqqrNOIW4l7Cih6UrGlQ4nZBtJT0g1CnFUZBZAZBLMpFwrgtZBOAIK4rvPSoZC6bTHyaorvHgZDZD";
		String phoneNumberId1 = "920973337770658";
        System.out.println("Message reçu: " + payload);
        String API_URL ="https://graph.facebook.com/v19.0/" + phoneNumberId1 + "/messages";
        
       
        
        // Exemple : récupérer le numéro de l’expéditeur
        List<Map<String,Object>> entries = (List<Map<String,Object>>) payload.get("entry");
        for (Map<String,Object> entry : entries) {
        	String wabaId = entry.get("id").toString();
            List<Map<String,Object>> changes = (List<Map<String,Object>>) entry.get("changes");
            for (Map<String,Object> change : changes) {
            	
                Map<String,Object> value = (Map<String,Object>) change.get("value");
                Map<String,Object> metadata = (Map<String,Object>) value.get("metadata");

                String phoneNumberId = metadata.get("phone_number_id").toString();
                
                List<Map<String,Object>> messages = (List<Map<String,Object>>) value.get("messages");
                if (messages != null) {
                    for (Map<String,Object> message : messages) {
                        String from = (String) message.get("from"); // numéro WhatsApp
                        String type = (String) message.get("type");
                        if ("interactive".equals(type)) {
                        	
                        	 Map<String,Object> interactive = (Map<String,Object>) payload.get("interactive");
                             if(interactive != null && "button_reply".equals(interactive.get("type"))) {
                                 String buttonId = ((Map<String,String>)interactive.get("button_reply")).get("id");

                                 // Réagir selon l'option choisie
                                 switch(buttonId) {
                                     case "opt1":
                                    	 Tools.sendText(from, "Vous avez choisi Option 1 !", restTemplate,  accessToken,  API_URL);
                                         break;
                                     case "opt2":
                                    	 Tools.sendText(from, "Vous avez choisi Option 2 !", restTemplate,  accessToken,  API_URL);
                                         break;
                                 }
                             }
                        	
                        } else if ("interactive".equals(type)) {
                        String text = ((Map<String,Object>) message.get("text")).get("body").toString();
                        String messageId = message.get("id").toString();

                        System.out.println("Message de " + from + ": " + text);
                        Message message1 = new Message();
                        message1.setTelephone(from);
                        message1.setMessage(text);
                        message1.setWabaid(wabaId);
                        message1.setPhonenumberid(phoneNumberId);
                       
                        messageService.createMessage(message1);
                        
                        if(from.equals("221774517228")||from.equals("221779094470")||from.equals("221762931313")||from.equals("221762931313")) {
                        	   // Appel async
                            CompletableFuture.runAsync(() -> {
                            
                            Tools.sendFlowTexte(from, accessToken, API_URL);
                      		//Tools.sendFlow(from,  phoneNumberId1,   accessToken,  restTemplate);
                              
                            });

                        }
                        
                    }//else type
                        
                        // Réponse automatique
                        //sendMessage(from, "Bonjour, j'ai reçu ton message: " + text);
                    }
                }
            }
        }

        return ResponseEntity.ok().build();
    }
    
    /*@PostMapping("/webhook")
    public ResponseEntity<Void> receive(@RequestBody Map<String,Object> payload) {

        List<Map<String,Object>> entries =
                (List<Map<String,Object>>) payload.get("entry");

        for (Map<String,Object> entry : entries) {

            String wabaId = entry.get("id").toString(); // WhatsApp Business Account ID

            List<Map<String,Object>> changes =
                    (List<Map<String,Object>>) entry.get("changes");

            for (Map<String,Object> change : changes) {

                Map<String,Object> value =
                        (Map<String,Object>) change.get("value");

                Map<String,Object> metadata =
                        (Map<String,Object>) value.get("metadata");

                String phoneNumberId =
                        metadata.get("phone_number_id").toString();

                String displayPhoneNumber =
                        metadata.get("display_phone_number").toString();

                List<Map<String,Object>> messages =
                        (List<Map<String,Object>>) value.get("messages");

                if (messages != null) {
                    for (Map<String,Object> message : messages) {

                        String messageId = message.get("id").toString();
                        String from = message.get("from").toString();

                        String text = null;

                        if (message.containsKey("text")) {
                            text = ((Map<String,Object>) message.get("text"))
                                    .get("body")
                                    .toString();
                        }

                        System.out.println("WABA ID: " + wabaId);
                        System.out.println("Phone Number ID: " + phoneNumberId);
                        System.out.println("Mon numéro: " + displayPhoneNumber);
                        System.out.println("Message ID: " + messageId);
                        System.out.println("From: " + from);
                        System.out.println("Text: " + text);
                       
                        
                        Message message1 = new Message();
                        
                        //message1.setWabaid(wabaId);
                        //message1.setPhonenumberid(phoneNumberId);
                        //message1.setDisplayphonenumber(displayPhoneNumber);
                        //message1.setMessageid(messageId);
                        message1.setTelephone(from);
                        message1.setMessage(text);
                        
                        messageService.createMessage(message1);
                        
                    }
                }
            }
        }

        return ResponseEntity.ok().build();
    }*/
}
