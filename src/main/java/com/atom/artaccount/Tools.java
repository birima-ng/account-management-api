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

import java.security.SecureRandom;

import java.util.*;
import java.util.stream.Collectors;
public class Tools {

    public static String CONS_PATH="/var/www/documents";
	//public static String CONS_PATH="/Applications/XAMPP/xamppfiles/htdocs/documents";
	
	
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
}
