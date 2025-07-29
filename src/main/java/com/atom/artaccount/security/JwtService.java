package com.atom.artaccount.security;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.atom.artaccount.Tools;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Service
public class JwtService {

    private final String secretKey = "t4hsRoqyYPF8x9L4/mBJ7FNasHMlr/XEoueeQMhYDv8="; // Utilisez une clé secrète forte
    private final long jwtExpirationInMs = 1000 * 60 * 60 * 1000; // 10 heures add 1000

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        
        
        LocalDateTime now11 = LocalDateTime.now();
        
        // Ajouter 24 heures
        LocalDateTime futureDateTime = now11.plus(Duration.ofHours(24));
        
        // Afficher la date et l'heure actuelles et futures
        System.out.println("Date et heure actuelles : " + now11);
        System.out.println("Date et heure futures : " + futureDateTime);
    	
        // Obtenir l'instant actuel
        Instant now = Instant.now();
        
        // Nombre de millisecondes à ajouter
        long millisToAdd = 10000; // par exemple, 10 secondes

        // Ajouter les millisecondes
        Instant futureInstant = now.plusMillis(millisToAdd);
        
        

        // Afficher l'instant actuel et l'instant futur
        System.out.println("Instant actuel : " + now);
        System.out.println("Instant futur : " + futureInstant);
    	Date d=new Date(System.currentTimeMillis());
    	System.out.println("################# date "+d);
    	System.out.println("################# new Date(System.currentTimeMillis() + jwtExpirationInMs) "+new Date(System.currentTimeMillis() + jwtExpirationInMs));
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(Tools.localDateTimeToDate(now11))
                .setExpiration(Tools.localDateTimeToDate(futureDateTime))
                //.setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}