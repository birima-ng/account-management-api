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
import com.atom.artaccount.model.TypeFicheCollecte;
import com.atom.artaccount.model.User;
import com.atom.artaccount.service.TypeFicheCollecteService;
import com.atom.artaccount.service.UserService;

@RestController
@CrossOrigin
public class TypeFicheCollecteController {
	
    @Autowired
    private TypeFicheCollecteService typefichecollecteService;
    
    @Autowired
    private UserService userService;

	 @GetMapping("/api/type-fiche-collecte-page")
	 public Page<TypeFicheCollecte> getTypeFicheCollecte(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size) {
		 User user = Tools.getUser(userService);
		 Pageable pageable = PageRequest.of(page, size);
	     return typefichecollecteService.findBySystemeId(user.getSysteme().getId(),  pageable);
	 }
	 
    @GetMapping("/api/type-fiche-collecte")
    public List<TypeFicheCollecte> getAllTypeFicheCollectes() {
    	 User user = Tools.getUser(userService);
	     return typefichecollecteService.findBySystemeId(user.getSysteme().getId());
    }

    @GetMapping("/api/type-fiche-collecte/{id}")
    public ResponseEntity<TypeFicheCollecte> getTypeFicheCollecteById(@PathVariable String id) {
        Optional<TypeFicheCollecte> typefichecollecte = typefichecollecteService.getTypeFicheCollecteById(id);
        if (typefichecollecte.isPresent()) {
            return ResponseEntity.ok(typefichecollecte.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/type-fiche-collecte")
    public TypeFicheCollecte createTypeFicheCollecte(@RequestBody TypeFicheCollecte typefichecollecte) {

    	return typefichecollecteService.createTypeFicheCollecte(typefichecollecte);
    }

    @PutMapping("/api/type-fiche-collecte/{id}")
    public ResponseEntity<TypeFicheCollecte> updateTypeFicheCollecte(@PathVariable String id, @RequestBody TypeFicheCollecte typefichecollecteDetails) {
    	TypeFicheCollecte updatedTypeFicheCollecte = typefichecollecteService.updateTypeFicheCollecte(id, typefichecollecteDetails);
        return ResponseEntity.ok(updatedTypeFicheCollecte);
    }

    @DeleteMapping("/api/type-fiche-collecte/{id}")
    public ResponseEntity<Void> deleteTypeFicheCollecte(@PathVariable String id) {
        typefichecollecteService.deleteTypeFicheCollecte(id);
        return ResponseEntity.noContent().build();
    }
    
}
