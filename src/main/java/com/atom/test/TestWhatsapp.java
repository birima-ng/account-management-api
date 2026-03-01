package com.atom.test;

import java.io.IOException;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.atom.artaccount.Tools;
import com.atom.artaccount.dto.ApiResponse;
import com.google.gson.Gson;

public class TestWhatsapp {

	
	    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tools.disableSslVerificationNew();
		
		RestTemplate restTemplate = new RestTemplate();
		  String accessToken = "EAAM4YO8Hr0sBQVLpWOiMBUhu5ZAywkUFPYK6z2FiBmvgH8rO1xBs7CxuBRaICWd8HZBrWUecxrZCxL7ZCYZB6WssyaSyh3KCyyCbkzbnmAZCuxnVYYsXrudTeKSZCLcqqrNOIW4l7Cih6UrGlQ4nZBtJT0g1CnFUZBZAZBLMpFwrgtZBOAIK4rvPSoZC6bTHyaorvHgZDZD";
		    String phoneNumberId = "920973337770658";
		     String API_URL = "https://graph.facebook.com/v19.0/" + phoneNumberId + "/messages";

		//Tools.sendFlow("774517228",  phoneNumberId,   accessToken,  restTemplate);
		
		String url = "https://e-carriere.sec.gouv.sn/account-management-fudpe/users/{id}/reset-password-by-telephone";
		
        //RestTemplate restTemplate = new RestTemplate();

       // String url = "http://localhost:8080/api/users/{id}";

		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
		    @Override
		    public boolean hasError(ClientHttpResponse response) {
		        return false; // Désactive les exceptions automatiques
		    }
		});
        HttpEntity<Void> request = new HttpEntity<>(null);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                request,
                String.class,
                "774517228"
        );

        System.out.println("Status: " + response.getStatusCode());
        System.out.println("Body: " + response.getBody());
        System.out.println("response.getStatusCodeValue(): " + response.getStatusCodeValue());
        
        Gson gson = new Gson();
        ApiResponse response1 = gson.fromJson(response.getBody(), ApiResponse.class);

        System.out.println("response1.getStatus()  "+response1.getStatus());
        System.out.println("response1.getMessage() "+response1.getMessage());
        System.out.println("response1.getResetLink() "+response1.getResetLink());
        
        Tools.sendMessage("774517228", response1.getResetLink(), restTemplate,  phoneNumberId,  accessToken);
        
		
		//Tools.sendFlowTexte("774517228", accessToken, API_URL);
		
	  //Tools.sendWelcomeMessage("774517228",  restTemplate,  phoneNumberId,  accessToken);

		/*try {
			//Tools.envoyerTemplate("774517228", "Ttest");
			Tools.fluxBienvenue("774517228");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};*/
	}

}
