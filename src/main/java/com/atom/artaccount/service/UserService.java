package com.atom.artaccount.service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atom.artaccount.Tools;
import com.atom.artaccount.model.User;
import com.atom.artaccount.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
   // @Autowired
    //private OrganisationnelleRepository organisationnelleRepository;
    
    @Autowired
    private ModelMapper modelMapper; // Injectez le bean ModelMapper
    
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private UserService userService;
    
    //@Autowired
    //private SmsService smsService;
  
    public User saveUser(User user) {
    	 User currentUser = Tools.getUser(userService);
    	 String token = UUID.randomUUID().toString();
    	 String codeactivation = Tools.getCodeActivationNumber();
    	 System.out.println("########################@ save");
    	 System.out.println("########################@ codeactivation "+codeactivation);
         user.setEnabled(false);
         user.setDatesave();
         user.setDateupdate();
         user.setCodeactivation(codeactivation);
         user.setToken(token);
         
         //pays et systeme user
         user.setPays(currentUser.getPays());
         user.setSysteme(currentUser.getSysteme());
         userRepository.save(user);
         //envoi mail
        // emailService.sendActivationEmail(user.getEmail(), token,user.getNom()+", votre idetifiant est : "+user.getUsername());
       //  smsService.sendSms("Bonjour "+user.getNom()+", votre code d'activation est : "+codeactivation, user.getTelephone());
         //envoi sms
         return user;
    }

    public User saveUserAndFlush(User user) {
        return userRepository.saveAndFlush(user);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }
    
    public void deleteUser(User user) {
         userRepository.delete(user);
    }
    

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public User getUserByUsernameAndPassword(String username,String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
    
    public void activateUser(String token) {
        // Find the token in the database, get the associated user, and enable the user
        // ...
    }
    
 	public List<User> findByPaysIdAndSystemeId(String paysId, String systemeId){
 		return userRepository.findByPaysIdAndSystemeId(paysId,systemeId);
 	}

    public User updateUser(String id, User userDetails) {
    	Optional<User> optionalUser = userRepository.findById(id);
    	if (optionalUser.isPresent()) {
    		User existingUser = optionalUser.get();

            // Configurer ModelMapper pour ignorer les propriétés null
            modelMapper.getConfiguration().setSkipNullEnabled(true);

            // Appliquer les modifications de userDetails à existingUser
            modelMapper.map(userDetails, existingUser);
            existingUser.setDateupdate();
            return userRepository.save(existingUser);
        } else {
            // Gérer le cas où l'utilisateur n'existe pas
           // throw new RuntimeException("User not found with id: " + id);
            return null;
        }
    	
    }
    
    public User findByCodeactivationAndToken(String codeactivation, String token) {
    	return userRepository.findByCodeactivationAndToken(codeactivation,token);
    }
    
    public User reinitialiseUser(User user) {
   	 String token = UUID.randomUUID().toString();
   	 String codeactivation = Tools.getCodeActivation();
   	 System.out.println("########################@ save");
   	 System.out.println("########################@ codeactivation "+codeactivation);
        user.setEnabled(false);
       // user.setDatesave(new Date());
        user.setDateupdate();
        user.setCodeactivation(codeactivation);
        user.setToken(token);
        userRepository.save(user);
        //envoi mail
        emailService.sendActivationEmail(user.getEmail(), token,user.getNom()+", votre compte est réinitialisé "+user.getUsername());
        //envoi sms
        return user;
   }

    // Other service methods as needed
}