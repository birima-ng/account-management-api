package com.atom.artaccount.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.atom.artaccount.model.User;
import com.atom.artaccount.service.UserService;

@Service
public class MyUserDetailsService implements UserDetailsService {

    // Vous pouvez injecter un repository pour obtenir les utilisateurs de votre base de données
     @Autowired
     private UserService userService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Chargez l'utilisateur de la base de données ou autre source
         User user = userService.getUserByUsername(username);
         
         System.out.println("############################################# "+user);

       if(user==null){
            throw new UsernameNotFoundException("User not found");
        }
        
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword()) // {noop} pour éviter le cryptage du mot de passe
                .authorities("ADMIN")
                .build();
    }
}