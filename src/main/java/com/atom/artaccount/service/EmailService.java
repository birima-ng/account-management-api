package com.atom.artaccount.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendActivationEmail(String to, String token, String nom) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Activation Compte BAAC");
        message.setText("Bonjour "+nom+", votre compte est crée avec succès merci d'activer votre compte avec le code reçu par sms : " +
                "http://localhost:4200/pages/activation?token=" + token);
        mailSender.send(message);
    }
    
    public void sendActivationEmailReinitialise(String to, String token, String nom) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Activation Compte BAAC");
        message.setText("Bonjour "+nom+", merci d'activer votre compte avec le code reçu par sms : " +
                "http://localhost:4200/pages/activation?token=" + token);
        mailSender.send(message);
    }
}
