package com.atom.artaccount.dto;

public class ApiResponse {
	String resetLink;
	String message;
	int status;
	
	public String getResetLink() {
		return resetLink;
	}
	
	public void setResetLink(String resetLink) {
		this.resetLink = resetLink;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}

	
}
