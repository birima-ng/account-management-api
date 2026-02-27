package com.atom.artaccount;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.atom.artaccount.dto.MontantDTO;
import com.atom.artaccount.log.UserActionLog;
import com.atom.artaccount.log.UserActionLogRepository;
import com.atom.artaccount.model.User;
import com.atom.artaccount.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.security.SecureRandom;

import java.util.*;
import java.util.stream.Collectors;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class Tools {

	 
    public static String CONS_PATH="/var/www/documents";
	//public static String CONS_PATH="/Applications/XAMPP/xamppfiles/htdocs/documents";
	
	private static final String ACCESS_TOKEN = "EAAM4YO8Hr0sBQVLpWOiMBUhu5ZAywkUFPYK6z2FiBmvgH8rO1xBs7CxuBRaICWd8HZBrWUecxrZCxL7ZCYZB6WssyaSyh3KCyyCbkzbnmAZCuxnVYYsXrudTeKSZCLcqqrNOIW4l7Cih6UrGlQ4nZBtJT0g1CnFUZBZAZBLMpFwrgtZBOAIK4rvPSoZC6bTHyaorvHgZDZD";
    private static final String PHONE_NUMBER_ID = "920973337770658";//1 agent 
	
	public static String getChaine() {
	    int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();

	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();

	    return generatedString;
	}
	
	public static Date localDateTimeToDate(LocalDateTime localDateTime) {
		
		// Obtenir la date et l'heure locales actuelles
        
        // Convertir LocalDateTime en ZonedDateTime en utilisant le fuseau horaire par défaut
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        
        // Convertir ZonedDateTime en Instant
        Instant instant = zonedDateTime.toInstant();
        
        // Convertir Instant en Date
        Date date = Date.from(instant);
        
        // Afficher la date et l'heure locales et la Date
        System.out.println("Date et heure locales : " + localDateTime);
        System.out.println("Date (java.util.Date) : " + date);
		return date;
	}
	
	public static void logAction (String username, String action, UserActionLogRepository userActionLogRepository){
	    UserActionLog log = new UserActionLog();
	    log.setUsername(username);
	    log.setAction(action);
	    log.setTimestamp(LocalDateTime.now());

	    userActionLogRepository.save(log);
	}
	
	public static String getUsername() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	    String username;
	    if (principal instanceof UserDetails) {
	        username = ((UserDetails) principal).getUsername();
	    } else {
	        username = principal.toString();
	    }
	return username;
	}
	
	public static User getUser(UserService userService) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	    String username;
	    if (principal instanceof UserDetails) {
	        username = ((UserDetails) principal).getUsername();
	    } else {
	        username = principal.toString();
	    }
	return userService.getUserByUsername(username);
	}
	
	
	public static String getCodeActivation() {
	    int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 6;
	    Random random = new Random();

	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();

	    System.out.println(generatedString.toUpperCase());
	    return generatedString.toUpperCase();
	}
	
	public static String getCodeActivationNumber() {
		SecureRandom secureRandom = new SecureRandom();
        // Générer un nombre entre 100000 et 999999 (inclus)
        int sixDigitNumber = 100000 + secureRandom.nextInt(900000);
        System.out.println("Nombre aléatoire de 6 chiffres: " + sixDigitNumber);
	    return sixDigitNumber+"";
	}
	
	public static MontantDTO montantDto(List<Double> prices) {
		
		MontantDTO montantDTO = new MontantDTO();
		
	       if (prices == null || prices.isEmpty()) {
	            throw new IllegalArgumentException("La liste des prix ne peut pas être vide.");
	        }

	        double minPrice = Collections.min(prices);
	        double maxPrice = Collections.max(prices);

	        // Trouver le prix dominant (le plus fréquent)
	        Map<Double, Long> frequencyMap = prices.stream()
	                .collect(Collectors.groupingBy(price -> price, Collectors.counting()));

	        double dominantPrice = frequencyMap.entrySet().stream()
	                .max(Comparator.comparing(Map.Entry::getValue))
	                .get()
	                .getKey();

	        // Retourner les résultats dans une map
	        Map<String, Double> result = new HashMap<>();
	        result.put("minPrice", minPrice);
	        result.put("maxPrice", maxPrice);
	        result.put("dominantPrice", dominantPrice);
	        
	        montantDTO.setPrixdominant(dominantPrice);
	        montantDTO.setPrixmaximum(maxPrice);
	        montantDTO.setPrixminimum(minPrice);;
		
		return montantDTO;
		
	}
	
	  public static void envoyerTemplate(
	            String telephone,
	            String message
	    ) throws IOException {

	        String endpoint = "https://graph.facebook.com/v19.0/"
	                + PHONE_NUMBER_ID + "/messages";

	        URL url = new URL(endpoint);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Authorization", "Bearer " + ACCESS_TOKEN);
	        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	        conn.setDoOutput(true);
	        conn.setConnectTimeout(15000);
	        conn.setReadTimeout(15000);

	        // ===== Construction du JSON avec Gson =====
	        JsonObject payload = new JsonObject();
	        payload.addProperty("messaging_product", "whatsapp");
	        payload.addProperty("to", telephone);
	        payload.addProperty("type", "template");

	        JsonObject template = new JsonObject();
	        template.addProperty("name", "envoyer_message_simple");

	        JsonObject language = new JsonObject();
	        language.addProperty("code", "fr");
	        template.add("language", language);

	        JsonArray parameters = new JsonArray();

	        JsonObject p1 = new JsonObject();
	        p1.addProperty("type", "text");
	        p1.addProperty("text", message);
	        parameters.add(p1);

	        JsonObject body = new JsonObject();
	        body.addProperty("type", "body");
	        body.add("parameters", parameters);

	        JsonArray components = new JsonArray();
	        components.add(body);

	        template.add("components", components);
	        payload.add("template", template);
	        // =========================================

	        // Envoi du JSON
	        try (OutputStream os = conn.getOutputStream()) {
	            os.write(payload.toString().getBytes("UTF-8"));
	        }

	        int status = conn.getResponseCode();
	        InputStream is = (status < 400)
	                ? conn.getInputStream()
	                : conn.getErrorStream();

	        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        StringBuilder response = new StringBuilder();
	        String line;
	        while ((line = br.readLine()) != null) {
	            response.append(line);
	        }

	        conn.disconnect();

	        if (status >= 400) {
	            throw new IOException(
	                    "Erreur WhatsApp API HTTP " + status + " : " + response
	            );
	        }

	        System.out.println("Message envoyé avec succès : " + response);
	    }
	  
	  
	  public static void fluxBienvenue(
	            String telephone,
	            String message
	    ) throws IOException {

	        String endpoint = "https://graph.facebook.com/v19.0/"
	                + PHONE_NUMBER_ID + "/messages";

	        URL url = new URL(endpoint);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Authorization", "Bearer " + ACCESS_TOKEN);
	        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	        conn.setDoOutput(true);
	        conn.setConnectTimeout(15000);
	        conn.setReadTimeout(15000);

	        // ===== Construction du JSON avec Gson =====
	        JsonObject payload = new JsonObject();
	        payload.addProperty("messaging_product", "whatsapp");
	        payload.addProperty("to", telephone);
	        payload.addProperty("type", "template");

	        JsonObject template = new JsonObject();
	        template.addProperty("name", "envoyer_message_simple");

	        JsonObject language = new JsonObject();
	        language.addProperty("code", "fr");
	        template.add("language", language);

	        JsonArray parameters = new JsonArray();

	        JsonObject p1 = new JsonObject();
	        p1.addProperty("type", "text");
	        p1.addProperty("text", message);
	        parameters.add(p1);

	        JsonObject body = new JsonObject();
	        body.addProperty("type", "body");
	        body.add("parameters", parameters);

	        JsonArray components = new JsonArray();
	        components.add(body);

	        template.add("components", components);
	        payload.add("template", template);
	        // =========================================

	        // Envoi du JSON
	        try (OutputStream os = conn.getOutputStream()) {
	            os.write(payload.toString().getBytes("UTF-8"));
	        }

	        int status = conn.getResponseCode();
	        InputStream is = (status < 400)
	                ? conn.getInputStream()
	                : conn.getErrorStream();

	        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        StringBuilder response = new StringBuilder();
	        String line;
	        while ((line = br.readLine()) != null) {
	            response.append(line);
	        }

	        conn.disconnect();

	        if (status >= 400) {
	            throw new IOException(
	                    "Erreur WhatsApp API HTTP " + status + " : " + response
	            );
	        }

	        System.out.println("Message envoyé avec succès : " + response);
	    }
	  
	  
	

	    public static String sendFlow(String to, String phoneNumberId, String  accessToken, RestTemplate restTemplate) {

	        String url = "https://graph.facebook.com/v19.0/" 
	                     + phoneNumberId + "/messages";

	        String flowToken = UUID.randomUUID().toString();

	        Map<String, Object> action = Map.of(
	                "flow_token", flowToken
	        );

	        Map<String, Object> parameter = Map.of(
	                "type", "action",
	                "action", action
	        );

	        Map<String, Object> button = Map.of(
	                "type", "button",
	                "sub_type", "flow",
	                "index", "0",
	                "parameters", List.of(parameter)
	        );

	        Map<String, Object> template = Map.of(
	                "name", "flux_bienvenue",
	                "language", Map.of("code", "fr_FR"),
	                "components", List.of(button)
	        );

	        Map<String, Object> body = Map.of(
	                "messaging_product", "whatsapp",
	                "to", to,
	                "type", "template",
	                "template", template
	        );

	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        headers.setBearerAuth(accessToken);

	        HttpEntity<Map<String, Object>> request =
	                new HttpEntity<>(body, headers);

	        ResponseEntity<String> response =
	                restTemplate.postForEntity(url, request, String.class);

	        return response.getBody();
	    }
}
