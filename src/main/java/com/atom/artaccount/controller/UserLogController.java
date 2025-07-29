package com.atom.artaccount.controller;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.atom.artaccount.log.UserActionLog;
import com.atom.artaccount.service.UserLogService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@RestController
@CrossOrigin
public class UserLogController {
	private static final Logger logger = LoggerFactory.getLogger(UserLogController.class);
	
	@Autowired
    private UserLogService userlogService;

	 @RequestMapping(value="/api/user-log", method=RequestMethod.GET)
	    public MappingJacksonValue listUserActionLog() {
		    Iterable<UserActionLog> UserActionLogs = userlogService.getAllUserActionLogs();
	        MappingJacksonValue UserActionLogsFiltres = new MappingJacksonValue(UserActionLogs);
	        return UserActionLogsFiltres;
	    }
	 
	    @GetMapping(value="/api/userlog/{id}")
	    public UserActionLog searchUserActionLogById(@PathVariable String id) {
	        return userlogService.getUserActionLogById(id);
	    }
	  
		@RequestMapping(value="/api/userlog", method = RequestMethod.POST)
		public ResponseEntity <UserActionLog> createUserActionLog(@RequestBody UserActionLog userlog) throws URISyntaxException {
		
			logger.info("Start UserActionLog");
			//userlog.setDatesave(new Date());
			//userlog.setDateupdate(new Date());
			userlogService.saveUserActionLog(userlog);
			
		   return ResponseEntity.ok().body(userlog);
		}
		
		@DeleteMapping(value = "/api/userlog/{id}")
		public @ResponseBody UserActionLog deleteUserActionLog(@PathVariable("id") String id) {
			logger.info("Start UserActionLog");
			UserActionLog userlog=userlogService.getUserActionLogById(id);
			userlogService.deleteUserActionLog(userlog);
			return userlog;
		}
		
		
	
}
