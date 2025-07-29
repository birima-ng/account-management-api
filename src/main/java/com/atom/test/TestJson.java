package com.atom.test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
public class TestJson {

	public static void main(String[] args) {
		
	       ObjectMapper objectMapper = new ObjectMapper();
	        try {
	            // Lire une liste d'objets JSON
	            List<Message> people = objectMapper.readValue(new File("/Users/prose/Messages_2024_09_12_19_20_22.json"), new TypeReference<List<Message>>() {});

	            // Parcourir les objets
	            int n =0;
	            for (Message person : people) {
	            	n=n+1;
	            	if(person.getBody()!=null&&person.getBody().contains("Heures")) {
	                System.out.println("" + person.getAddress());
	                //System.out.println("getBody: " + person.getBody());
	                //System.out.println("-----"+n);
	            	}
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}

}
