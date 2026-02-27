package com.atom.test;

import java.io.IOException;

import org.springframework.web.client.RestTemplate;

import com.atom.artaccount.Tools;

public class TestWhatsapp {

	
	    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate();
		  String accessToken = "EAAM4YO8Hr0sBQVLpWOiMBUhu5ZAywkUFPYK6z2FiBmvgH8rO1xBs7CxuBRaICWd8HZBrWUecxrZCxL7ZCYZB6WssyaSyh3KCyyCbkzbnmAZCuxnVYYsXrudTeKSZCLcqqrNOIW4l7Cih6UrGlQ4nZBtJT0g1CnFUZBZAZBLMpFwrgtZBOAIK4rvPSoZC6bTHyaorvHgZDZD";
		    String phoneNumberId = "920973337770658";
		
		Tools.sendFlow("774517228",  phoneNumberId,   accessToken,  restTemplate);

		/*try {
			//Tools.envoyerTemplate("774517228", "Ttest");
			Tools.fluxBienvenue("774517228");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};*/
	}

}
