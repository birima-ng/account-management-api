package com.atom.artaccount.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.atom.artaccount.Tools;
import com.atom.artaccount.model.Profile;
import com.atom.artaccount.model.User;
import com.atom.artaccount.service.ProfileService;
import com.atom.artaccount.service.UserService;

@RestController
@CrossOrigin
public class ProfileController {
    @Autowired
    private ProfileService profileService;
    
    @Autowired
    private UserService userService;

    @GetMapping("/api/profil")
    public List<Profile> getAllProfiles() {
    	User user = Tools.getUser(userService);
    	System.out.println("####################################@ user "+user.getPays().getId());
    	System.out.println("####################################@ service "+user.getSysteme().getId());
        return profileService.findByPaysIdAndSystemeId(user.getPays().getId(), user.getSysteme().getId());
    }

    @GetMapping("/api/profil/{id}")
    public ResponseEntity<Profile> getProfileById(@PathVariable String id) {
        Optional<Profile> profile = profileService.getProfileById(id);
        if (profile.isPresent()) {
            return ResponseEntity.ok(profile.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/profil")
    public Profile createProfile(@RequestBody Profile profile) {
    	if(profileService.existsByNomOrCode(profile.getNom(), profile.getCode())) {
    		return null;
    	}
        return profileService.createProfile(profile);
    }

    @PutMapping("/api/profil/{id}")
    public ResponseEntity<Profile> updateProfile(@PathVariable String id, @RequestBody Profile profileDetails) {
    	Optional<Profile> profilsearch=profileService.findByNom(profileDetails.getNom());
    	System.out.println("################## "+profilsearch.isPresent());
    	if(profilsearch.isPresent()) {
    		if(!((profilsearch.get().getId()).equals(id))) {
    			return null;
    		}
    	}
    	
    	 profilsearch=profileService.findByCode(profileDetails.getCode());
    	if(profilsearch.isPresent()) {
    		if(!((profilsearch.get().getId()).equals(id))) {
    			return null;
    		}
    	}
    	
    	Profile updatedProfile = profileService.updateProfile(id, profileDetails);
    	
        return ResponseEntity.ok(updatedProfile);
    }

    @DeleteMapping("/api/profil/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable String id) {
        profileService.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }
}
