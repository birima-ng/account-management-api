package com.atom.artaccount.controller;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.atom.artaccount.Tools;
import com.atom.artaccount.dto.ChangePasswordDTO;
import com.atom.artaccount.model.PasswordChange;
import com.atom.artaccount.model.Result;
import com.atom.artaccount.model.User;
import com.atom.artaccount.security.JwtResponse;
import com.atom.artaccount.security.JwtService;
import com.atom.artaccount.security.MyUserDetailsService;
import com.atom.artaccount.service.UserService;


@RestController
@CrossOrigin
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
    private UserService userService;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
   // @Autowired
    //private AuthenticationManager authenticationManager;
    
    
    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService userDetailsService;
    private final JwtService jwtService;

    public UserController(AuthenticationManager authenticationManager, MyUserDetailsService userDetailsService, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }
	
	 @RequestMapping(value="/api/user", method=RequestMethod.GET)
	    public MappingJacksonValue listAllUser() {
		 User currentUser = Tools.getUser(userService);
		    Iterable<User> users = userService.findByPaysIdAndSystemeId(currentUser.getPays().getId(), currentUser.getSysteme().getId()); //userService.getAllUsers();
	        MappingJacksonValue usersFiltres = new MappingJacksonValue(users);
	        return usersFiltres;
	    }
	 
	    @GetMapping(value="/api/user/{id}")
	    public User searchUserById(@PathVariable String id) {
	        return userService.getUserById(id);
	    }
	    
		@RequestMapping(value="/api/user", method = RequestMethod.POST)
		public @ResponseBody User createUser(@RequestBody User user) {
			User user1 = userService.getUserByUsername(user.getUsername());
			if(user1!=null) {
				return null;
			}
			userService.saveUser(user);
			
			return user;
		}
		
		@DeleteMapping(value = "/api/user/{id}")
		public @ResponseBody User deleteUser(@PathVariable("id") String id) {
			logger.info("Start deleteuser.");
			User user=userService.getUserById(id);
			userService.deleteUser(user);
			return user;
		}
		
	    @GetMapping(value="/api/user/{username}/{password}")
	    public User searchUserByUsernameAndPassword(@PathVariable String username,@PathVariable String password) {
	        return userService.getUserByUsernameAndPassword(username,password);
	    }

	    @GetMapping(value="/api/user/{username}/username")
	    public User searchUserByUsername(@PathVariable String username) {
	        return userService.getUserByUsername(username);
	    }
	    
		@RequestMapping(value="/api/user/password-change", method = RequestMethod.POST)
		public @ResponseBody Result changePassword(@RequestBody PasswordChange p) {
			boolean result=true;
			try {
				System.out.println("entreee");
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(p.getUsername(), p.getOldpassword()));
			} catch (DisabledException e) {
				System.out.println("birima birima 1");
				result=false;
			//throw new Exception("USER_DISABLED", e);
			} catch (BadCredentialsException e) {
				System.out.println("birima birima 2");
				result=false;
			//throw new Exception("INVALID_CREDENTIALS", e);
			}
		
			if(result) {
				
				//logger.info("Start PasswordChange bcryptEncoder "+bcryptEncoder.encode(p.getOldpassword()));
				User u=userService.getUserByUsername(p.getUsername());
				u.setPassword(bcryptEncoder.encode(p.getNewpassword()));
				userService.saveUserAndFlush(u);
				logger.info("User "+u);
			}

			return new Result(result);
		}
		
		@RequestMapping(value="/api/user/userbyusername", method = RequestMethod.POST)
		public @ResponseBody Result userByUsername(@RequestBody User p) {
			boolean result=true;
			
			User u=userService.getUserByUsername(p.getUsername());
			if(u==null) {
				
			result=false;
			}

			return new Result(result);
		}
		
	  
	    @PostMapping("/authenticate")
	    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {
	    	System.out.println("entree ###################################################");
	        try {
	           authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
	            );
	            
	           /* Authentication authentication = authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
	            );
	            */
	        } catch (AuthenticationException e) {
	        	System.out.println("entree ################################################### 1"+e.getMessage());
	           // throw new Exception("Incorrect username or password", e);
	        	return null;
	        }
	        System.out.println("entree ################################################### 33");
	        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
	       
	        String token =jwtService.generateToken(userDetails);
	        
	        System.out.println("birima 0000000000000000000000000000000 "+token);
	        return ResponseEntity.ok(new JwtResponse(token));
	    }
	    
	    @GetMapping("/activate")
	    public ResponseEntity<?> activateUser(@RequestParam("token") String token) {
	        userService.activateUser(token);
	        return ResponseEntity.ok("User activated successfully.");
	    }
	    
	    @PutMapping("/api/user/{id}")
	    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
	    	System.out.println("######################## -- "+id);
	    	User updatedJourSemaine = userService.updateUser(id, user);
	        return ResponseEntity.ok(updatedJourSemaine);
	    }
		   
	    @GetMapping(value="/api/user/codeactivation/{codeactivation}/token/{token}")
	    public User findByCodeactivationAndToken(@PathVariable String codeactivation,@PathVariable String token) {
	        return userService.findByCodeactivationAndToken(codeactivation, token);
	    }
	    
		
	    @PostMapping(value="/api/user/change-password")
		public @ResponseBody Result userByUsername(@RequestBody ChangePasswordDTO cpassword) {
			boolean result=true;
			
			User u=userService.getUserById(cpassword.getId());
			if(u==null) {
					result=false;
			}else {
				if(!u.isEnabled()) {
					u.setPassword(bcryptEncoder.encode(cpassword.getPassword()));
					u.setToken("");
					u.setCodeactivation("");
					u.setEnabled(true);
				}else {
					result=false;
				}
			}

			return new Result(result);
		}
	    
	    @GetMapping(value="/api/user/motdepasse-oublie/{email}")
		public @ResponseBody Result userReinitialise(@PathVariable("email") String email) {
			boolean result=true;
			
			User u=userService.getUserByEmail(email);
			if(u==null) {
					result=false;
			}else {
			
			}

			return new Result(result);
		}
	    
	    @GetMapping(value="/api/user/correct")
	    public List<User> userCorrect() {
	    	
	    	List<User>  users = userService.getAllUsers();
	    	
	    	for( User user:users) {
	    		System.out.println("############ id user "+user.getId());
	    		user.setPassword(bcryptEncoder.encode("Passer111"));
	    		userService.saveUser(user);
	    	}
	        return users;
	    }
	    

}

class AuthRequest {
    private String username;
    private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

    // getters and setters
    
}
