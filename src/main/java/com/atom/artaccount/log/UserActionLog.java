package com.atom.artaccount.log;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity(name="user_action_log")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserActionLog extends com.atom.artaccount.model.Entity{
	private static final long serialVersionUID = -8091879091924046844L;
	
    public UserActionLog() {
		super();
	}
	public UserActionLog(String username, String action, LocalDateTime timestamp) {
		super();
		this.username = username;
		this.action = action;
		this.timestamp = timestamp;
	}
	private String username;
    private String action;
    private LocalDateTime timestamp;
    
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getAction() {
		return action;
	}
	
	public void setAction(String action) {
		this.action = action;
	}
	
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}


    // getters and setters
}
