package com.atom.artaccount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.atom.artaccount.service.EmailService;

@RestController
@RequestMapping("/api")
public class MailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/mail-send")
    public String sendMail() {
        emailService.sendEmail("ng.birima@gmail.com",
                "Test Zimbra Spring Boot",
                "Bonjour, ceci est un test d'envoi via Zimbra !");
        return "Email envoyé avec succès !";
    }
}
