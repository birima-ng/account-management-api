package com.atom.artaccount.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atom.artaccount.Tools;
import com.atom.artaccount.model.Annee;
import com.atom.artaccount.model.Decoupage1;
import com.atom.artaccount.model.Indicateur;
import com.atom.artaccount.model.User;
import com.atom.artaccount.model.ValeurIndicateur;
import com.atom.artaccount.service.AnneeService;
import com.atom.artaccount.service.Decoupage1Service;
import com.atom.artaccount.service.IndicateurService;
import com.atom.artaccount.service.UserService;
import com.atom.artaccount.service.ValeurIndicateurService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
public class ValeurIndicateurController {
	
    @Autowired
    private ValeurIndicateurService valeurindicateurService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private Decoupage1Service decoupage1Service;
    
    @Autowired
    private AnneeService anneeService;
    
    @Autowired
    private IndicateurService indicateurService;

	 
	 @GetMapping("/api/valeur-indicateur-page")
	 public Page<ValeurIndicateur> getValeurIndicateurPages(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
	     Pageable pageable = PageRequest.of(page, size);
	     return valeurindicateurService.getAll(pageable);
	 }
	 
    @GetMapping("/api/valeur-indicateur")
    public List<ValeurIndicateur> getAllValeurIndicateurs() {
    	
    	   ObjectMapper objectMapper = new ObjectMapper();
           try {
               String json = objectMapper.writeValueAsString(valeurindicateurService.getAllValeurIndicateurs());
       
               System.out.println(json);
           } catch (JsonProcessingException e) {
               e.printStackTrace();
           }
        return valeurindicateurService.getAllValeurIndicateurs();
    }

    @GetMapping("/api/valeur-indicateur/{id}")
    public ResponseEntity<ValeurIndicateur> getValeurIndicateurById(@PathVariable String id) {
        Optional<ValeurIndicateur> valeurindicateur = valeurindicateurService.getValeurIndicateurById(id);
        if (valeurindicateur.isPresent()) {
            return ResponseEntity.ok(valeurindicateur.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/valeur-indicateur")
    public ValeurIndicateur createValeurIndicateur(@RequestBody ValeurIndicateur valeurindicateur) {
    	
        return valeurindicateurService.createValeurIndicateur(valeurindicateur);
    }

    @PutMapping("/api/valeur-indicateur/{id}")
    public ResponseEntity<ValeurIndicateur> updateValeurIndicateur(@PathVariable String id, @RequestBody ValeurIndicateur valeurindicateurDetails) {

    	ValeurIndicateur updatedValeurIndicateur = valeurindicateurService.updateValeurIndicateur(id, valeurindicateurDetails);
    	
        return ResponseEntity.ok(updatedValeurIndicateur);
    }

    @DeleteMapping("/api/valeur-indicateur/{id}")
    public ResponseEntity<Void> deleteValeurIndicateur(@PathVariable String id) {
        valeurindicateurService.deleteValeurIndicateur(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/api/valeur-indicateur/indicateur/{indicateurId}/periode/{periode}/campagneagricole/{campagneagricoleId}/pays/{paysId}")
    public List<ValeurIndicateur> getAllValeurIndicateurs(@PathVariable String indicateurId, @PathVariable String periode, @PathVariable String campagneagricoleId, @PathVariable String paysId) {
    	User user = Tools.getUser(userService);
    	System.out.println("############################### indicateurId "+indicateurId);
        System.out.println("############################### periode "+periode);
        System.out.println("############################### campagneagricoleId "+campagneagricoleId);
        System.out.println("############################### paysId "+paysId);
    	
        List<ValeurIndicateur> valeurindicateurs = valeurindicateurService.findByIndicateurIdAndPeriodeAndCampagneagricoleIdAndPaysId(indicateurId, periode, campagneagricoleId, user.getPays().getId());
        
        if(valeurindicateurs.size()==0) {
        	valeurindicateurs = new ArrayList<>();
        	List<Decoupage1> decoupage1s = decoupage1Service.findByPaysId(user.getPays().getId());
        	
        	for(Decoupage1 Decoupage1:decoupage1s) {
        		ValeurIndicateur v= new ValeurIndicateur();
        		v.setRegion(Decoupage1);
        		v.setValeur(new BigDecimal(0));
        		valeurindicateurs.add(v);
        	}
        }
        
        return valeurindicateurs;
    }
    
    @GetMapping("/api/valeur-indicateur/indicateur/{indicateurId}/periode/{periode}")
    public List<ValeurIndicateur> getAllValeurIndicateurs(@PathVariable String indicateurId, @PathVariable String periode) {
    	User user = Tools.getUser(userService);
    	System.out.println("############################### indicateurId "+indicateurId);
        System.out.println("############################### periode "+periode);
    	
        List<ValeurIndicateur> valeurindicateurs = new ArrayList<>();
        
        if(valeurindicateurs.size()==0) {
        	valeurindicateurs = new ArrayList<>();
        	List<Decoupage1> decoupage1s = decoupage1Service.findByPaysId(user.getPays().getId());
        	
        	for(Decoupage1 decoupage1:decoupage1s) {
        		List<ValeurIndicateur> v1 = valeurindicateurService.findByPeriodeAndIndicateurIdAndRegionIdAndPaysIdAndNiveau(periode, indicateurId, decoupage1.getId(), user.getPays().getId(), 1);
        		if(v1.size()>0) {
        			valeurindicateurs.add(v1.get(0));
        		}else {
        		    ValeurIndicateur v= new ValeurIndicateur();
        		    v.setRegion(decoupage1);
        		    v.setValeur(new BigDecimal(0));
        		    valeurindicateurs.add(v);
        		}
        	}
        }
        
        return valeurindicateurs;
    }
    
    
    @GetMapping("/api/valeur-indicateur/test")
    public void getAllValeurIndicateursTest() {
        
        List<Annee> annees = anneeService.getAllAnnees();
        List<Indicateur> indicateurs = indicateurService.getAllIndicateurs();
        	
        for(Annee annee: annees) {
        	
        	System.out.println("############################### periode "+annee.getLibelle());
        	
        	for(Indicateur indicateur: indicateurs) {
        		System.out.println("############################### indicateurId "+indicateur.getId());
        	List<Decoupage1> decoupage1s = decoupage1Service.findByPaysId("10");
        	
        	for(Decoupage1 decoupage1:decoupage1s) {
        		List<ValeurIndicateur> list = valeurindicateurService.findByPeriodeAndIndicateurIdAndRegionIdAndPaysIdAndNiveau(annee.getLibelle()+"", indicateur.getId(), decoupage1.getId(), "1", 1);
        		for(int i=1; i<list.size(); i++) {
        			valeurindicateurService.deleteValeurIndicateur(list.get(i).getId());
        		}
        	}
        	
        	}
        }
        
    }
}
