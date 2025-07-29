package com.atom.artaccount.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atom.artaccount.model.Profile;
import com.atom.artaccount.repository.ProfileRepository;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    public Optional<Profile> getProfileById(String id) {
        return profileRepository.findById(id);
    }

    public Profile createProfile(Profile profile) {
    	profile.setDatesave();
    	profile.setDateupdate();
        return profileRepository.save(profile);
    }

    public Profile updateProfile(String id, Profile profileDetails) {
        Profile profile = profileRepository.findById(id).orElseThrow();
        profile.setNom(profileDetails.getNom());
        profile.setDateupdate();
        return profileRepository.save(profile);
    }

    public void deleteProfile(String id) {
        profileRepository.deleteById(id);
    }
    
    public boolean existsByNomOrCode(String nom, String code) {
    	return profileRepository.existsByNomOrCode(nom, code);
    }
    
    public Optional<Profile> findByNomOrCode(String nom, String code){
    	return profileRepository.findByNomOrCode(nom, code);
    }
    
    public Optional<Profile> findByNom(String nom){
    	return profileRepository.findByNom(nom);
    }
    
    public Optional<Profile> findByCode(String code){
    	return profileRepository.findByCode( code);
    }
    
    public List<Profile> findByPaysIdAndSystemeId(String paysId, String systemeId){
    	return profileRepository.findByPaysIdAndSystemeId(paysId ,systemeId);
    }
}
