package com.atom.artaccount.controller;

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
import com.atom.artaccount.model.ConfigFicheCollecte;
import com.atom.artaccount.model.User;
import com.atom.artaccount.service.ConfigFicheCollecteService;
import com.atom.artaccount.service.UserService;

@RestController
@CrossOrigin
public class ConfigFicheCollecteController {
    @Autowired
    private ConfigFicheCollecteService configfichecollecteService;

    @Autowired
    private UserService userService;
	 
	 @GetMapping("/api/config-fiche-collecte-page")
	 public Page<ConfigFicheCollecte> getConfigFicheCollectePages(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size) {
	     Pageable pageable = PageRequest.of(page, size);
	     return configfichecollecteService.getAll(pageable);
	 }
	 
    @GetMapping("/api/config-fiche-collecte")
    public List<ConfigFicheCollecte> getAllConfigFicheCollectes() {
        return configfichecollecteService.getAllConfigFicheCollectes();
    }

    @GetMapping("/api/config-fiche-collecte/{id}")
    public ResponseEntity<ConfigFicheCollecte> getConfigFicheCollecteById(@PathVariable String id) {
        Optional<ConfigFicheCollecte> configfichecollecte = configfichecollecteService.getConfigFicheCollecteById(id);
        if (configfichecollecte.isPresent()) {
            return ResponseEntity.ok(configfichecollecte.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/config-fiche-collecte")
    public ConfigFicheCollecte createConfigFicheCollecte(@RequestBody ConfigFicheCollecte configfichecollecte) {
        return configfichecollecteService.createConfigFicheCollecte(configfichecollecte);
    }

    @PutMapping("/api/config-fiche-collecte/{id}")
    public ResponseEntity<ConfigFicheCollecte> updateConfigFicheCollecte(@PathVariable String id, @RequestBody ConfigFicheCollecte configfichecollecteDetails) {
    	ConfigFicheCollecte updatedConfigFicheCollecte = configfichecollecteService.updateConfigFicheCollecte(id, configfichecollecteDetails);
        return ResponseEntity.ok(updatedConfigFicheCollecte);
    }

    @DeleteMapping("/api/config-fiche-collecte/{id}")
    public ResponseEntity<Void> deleteConfigFicheCollecte(@PathVariable String id) {
        configfichecollecteService.deleteConfigFicheCollecte(id);
        return ResponseEntity.noContent().build();
    }
    
	 @GetMapping("/api/config-fiche-collecte-page/{id}/type-fiche-collecte")
	 public Page<ConfigFicheCollecte> getConfigFicheCollectePages(@RequestParam(defaultValue = "id") String id, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size) {
	     Pageable pageable = PageRequest.of(page, size);
	     User user = Tools.getUser(userService);
	     return configfichecollecteService.findByPaysIdAndSystemeIdAndTypeficheId(user.getPays().getId(), user.getSysteme().getId(), id, pageable);
	 }
    
}
