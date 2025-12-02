package com.atom.artaccount.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atom.artaccount.model.Unite;
import com.atom.artaccount.service.SmsService;
import com.atom.artaccount.service.UniteService;
import com.atom.artaccount.service.WhatsAppService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
public class UniteController {
    @Autowired
    private UniteService uniteService;
    
    @Autowired
    private SmsService smsService;
    
    @Autowired
    private WhatsAppService whatsAppService;
    
    
   

	 @RequestMapping(value="/api/unite1", method=RequestMethod.GET)
	    public MappingJacksonValue listAcheminement() {
		    Iterable<Unite> acheminements = uniteService.getAllUnites();
	        MappingJacksonValue acheminementsFiltres = new MappingJacksonValue(acheminements);
	        return acheminementsFiltres;
	    }
	 
	 @GetMapping("/api/unite-page")
	 public Page<Unite> getUnitePages(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size) {
	     Pageable pageable = PageRequest.of(page, size);
	     return uniteService.getAll(pageable);
	 }
	 
    @GetMapping("/api/unite")
    public List<Unite> getAllUnites() {
    	
    	   ObjectMapper objectMapper = new ObjectMapper();
           try {
               String json = objectMapper.writeValueAsString(uniteService.getAllUnites());
       
               System.out.println(json);
           } catch (JsonProcessingException e) {
               e.printStackTrace();
           }
        return uniteService.getAllUnites();
    }

    @GetMapping("/api/unite/{id}")
    public ResponseEntity<Unite> getUniteById(@PathVariable String id) {
        Optional<Unite> unite = uniteService.getUniteById(id);
        if (unite.isPresent()) {
            return ResponseEntity.ok(unite.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/unite")
    public Unite createUnite(@RequestBody Unite unite) {
    	if(uniteService.existsByCode(unite.getCode())) {
    		return null;
    	}
        return uniteService.createUnite(unite);
    }

    @PutMapping("/api/unite/{id}")
    public ResponseEntity<Unite> updateUnite(@PathVariable String id, @RequestBody Unite uniteDetails) {
    	boolean result =uniteService.existsByCodeOrLibelle(uniteDetails.getCode(), uniteDetails.getLibelle());
    	if(result)
    		return null;
    	
    	Unite updatedUnite = uniteService.updateUnite(id, uniteDetails);
    	
        return ResponseEntity.ok(updatedUnite);
    }

    @DeleteMapping("/api/unite/{id}")
    public ResponseEntity<Void> deleteUnite(@PathVariable String id) {
        uniteService.deleteUnite(id);
        return ResponseEntity.noContent().build();
    }
    

    
    @GetMapping("/api/unite/getoken")
    public String getToken() {
    	
    	whatsAppService.sendMessage("221774517228", "Message test birima");
    	
    	String token  = smsService.generateAccessToken();
    	//String  token ="eyJ0eXAiOiJKV1QiLCJ2ZXIiOiIxLjAiLCJhbGciOiJFUzM4NCIsImtpZCI6Ikg1RkdUNXhDUlJWU0NseG5vTXZCWEtUM1AyckhTRVZUNV9VdE16UFdCYTQifQ.eyJpc3MiOiJodHRwczovL2FwaS5vcmFuZ2UuY29tL29hdXRoL3YzIiwiYXVkIjpbIm9wZSJdLCJleHAiOjE3NjI0MzcyMzAsImlhdCI6MTc2MjQzMzYzMCwianRpIjoidldCRk52V3FTN0p6WVNSZTduRkVFam1XYzB2M05sWlhLNzZjQk1qb1Z6dEJmUFkwNHNEWDJ5OWFYMktiMEZETnVnaXdqTFBJMVZlRWxmWGs4bHBSWVJSTTQ0STFwdUFINVI2TiIsImNsaWVudF9pZCI6IlpOQkZtQVBLUXBFeEw5NHl4c2J5SW4wYnQzdWRRd2dzIiwic3ViIjoiWk5CRm1BUEtRcEV4TDk0eXhzYnlJbjBidDN1ZFF3Z3MiLCJjbGllbnRfbmFtZSI6eyJkZWZhdWx0IjoiYmlyaW1hIn0sImNsaWVudF90YWciOiJsVXJLR3A0U3h0NU92cDliIiwic2NvcGUiOlsib3BlOnNtc19hZG1pbjp2MTphY2Nlc3MiLCJvcGU6c21zbWVzc2FnaW5nOnYxOmFjY2VzcyIsIm9wZTpjYW1hcmFfZ2VvZmVuY2luZ19vcmFuZ2UtbGFiOnYwOmFjY2VzcyJdLCJtY28iOiJTRUtBUEkifQ.Gjbefcx-tJpTDcl40Ote26qzu1B5UAUcjwZP9NmkcKIqwqM01lwsn3Z5vnZI67Ngo5tJdDx44g3l841k_ULeD9JAWVpNg-eM7hyvFBCb-15FpBmARWWX1TQB8pDwbP38";
        return smsService.sendSms(token, "GOVATHON", "221774517228","2210000", "Waw! test govathon");
        //return smsService.sendSms(token, "GOVATHON", "221774517228","2210000", "Waw! Feliccitation vous avez gagne le prix GOVATHON 2025. Tests");
        
    }
}
