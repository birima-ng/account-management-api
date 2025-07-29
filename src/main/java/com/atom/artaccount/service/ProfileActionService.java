package com.atom.artaccount.service;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atom.artaccount.model.Action;
import com.atom.artaccount.model.Profile;
import com.atom.artaccount.model.ProfileAction;
import com.atom.artaccount.repository.ActionRepository;
import com.atom.artaccount.repository.ProfileActionRepository;
import com.atom.artaccount.repository.ProfileRepository;

@Service
public class ProfileActionService {

    @Autowired
    private ProfileActionRepository profileactionRepository;
    
    @Autowired
    private ModelMapper modelMapper; // Injectez le bean ModelMapper

    
    @Autowired
    private ActionRepository actionRepository;
    
    @Autowired
    private ProfileRepository profileRepository;

    public ProfileAction saveProfileAction(ProfileAction profileaction) {
        return profileactionRepository.save(profileaction);
    }

    public List<ProfileAction> getAllProfileActions() {
        return profileactionRepository.findAll();
    }

    public ProfileAction getProfileActionById(String id) {
        return profileactionRepository.findById(id).orElse(null);
    }
    
    public void deleteProfileAction(ProfileAction profileaction) {
         profileactionRepository.delete(profileaction);
    }
    
    public List<ProfileAction> getProfileActionsByActionId(String actionId) {
        Optional<Action> action = actionRepository.findById(actionId);
        return action.map(value -> profileactionRepository.findByActionId(actionId)).orElse(null);
    }
    
    public List<ProfileAction> getProfileActionsByProfileId(String profileId) {
        Optional<Profile> profile = profileRepository.findById(profileId);
        return profile.map(value -> profileactionRepository.findByProfileId(profileId)).orElse(null);
    }
    
    public boolean existsByProfileIdAndActionIdAndAllowed(String profileId, String actionId, boolean allowed) {
    	return  profileactionRepository.existsByProfileIdAndActionIdAndAllowed(profileId, actionId, allowed);
    }
    
    public List<String> findAllByProfileIdAndAllowed(String profileId, boolean allowed){
    	return  profileactionRepository.findAllByProfileIdAndAllowed(profileId, allowed);
    }
    
    public ProfileAction updateProfileAction(String id, ProfileAction profileactionDetails) {
    	Optional<ProfileAction> optionalUser = profileactionRepository.findById(id);
    	if (optionalUser.isPresent()) {
    		ProfileAction existingUser = optionalUser.get();

            // Configurer ModelMapper pour ignorer les propriétés null
            modelMapper.getConfiguration().setSkipNullEnabled(true);

            // Appliquer les modifications de userDetails à existingUser
            modelMapper.map(profileactionDetails, existingUser);
            existingUser.setDateupdate();
            return profileactionRepository.save(existingUser);
        } else {
            // Gérer le cas où l'utilisateur n'existe pas
           // throw new RuntimeException("User not found with id: " + id);
            return null;
        }
    	
    }
    
   public boolean existsByProfileIdAndActionId(String profileId, String actionId) {
    	return profileactionRepository.existsByProfileIdAndActionId(profileId, actionId);
    }
    // Other service methods as needed
}