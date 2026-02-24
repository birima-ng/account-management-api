package com.atom.artaccount.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
        System.out.println("Message reçu: " + payload);

        // Exemple : récupérer le numéro de l’expéditeur
        List<Map<String,Object>> entries = (List<Map<String,Object>>) payload.get("entry");
        for (Map<String,Object> entry : entries) {
            List<Map<String,Object>> changes = (List<Map<String,Object>>) entry.get("changes");
            for (Map<String,Object> change : changes) {
                Map<String,Object> value = (Map<String,Object>) change.get("value");
                List<Map<String,Object>> messages = (List<Map<String,Object>>) value.get("messages");
                if (messages != null) {
                    for (Map<String,Object> message : messages) {
                        String from = (String) message.get("from"); // numéro WhatsApp
                        String text = ((Map<String,Object>) message.get("text")).get("body").toString();
                        String messageId = message.get("id").toString();

                        System.out.println("Message de " + from + ": " + text);
                        Message message1 = new Message();
                        message1.setTelephone(from);
                        message1.setMessage(text);
                        message1.setIdwhatsapp(messageId);
                        messageService.createMessage(message1);
                        
                        // Réponse automatique
                        //sendMessage(from, "Bonjour, j'ai reçu ton message: " + text);
                    }
                }
            }
        }

        return ResponseEntity.ok().build();
    }
}
