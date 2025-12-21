package com.atom.artaccount.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.atom.artaccount.model.Reservation;

@Service
public class ApiCronService {

    private final WebClient webClient;
    @Autowired
    private ReservationService reservationService;
   

    public ApiCronService(WebClient.Builder builder) {
        this.webClient = builder.build();
    }

    @Scheduled(cron = "*/8 * * * * *")
    public void callPostApi() {
    	
    	Pageable pageable = PageRequest.of(0, 1);
    	Page<Reservation> list = reservationService.findBySend(false, pageable);
    	for (Reservation reservation : list.getContent()) {
    	    // traitement
    		System.out.println("########################################## reservation "+reservation.getSender());
    		
    		webClient.post()
             .uri("https://api.govathon-reservation.mfprsp.com/api/users/send-linkAndPdf/"+reservation.getSender())
             .contentType(MediaType.APPLICATION_JSON)
             .bodyValue(Map.of("key", "value"))
             .retrieve()
             .bodyToMono(String.class)
             .doOnNext(res -> System.out.println("Response : " + res))
             .doOnError(err -> System.err.println("Erreur : " + err.getMessage()))
             .subscribe();
    		
    		reservation.setSend(true);
    		reservationService.updateReservation(reservation.getId(), reservation);
    	}
    
       
    }
}
