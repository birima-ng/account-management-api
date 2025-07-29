package com.atom.artaccount.dto;

public class ChangePasswordDTO {
    private String id;
    private String password;
    private String confirmPassword;
    // Constructeurs, getters, setters
    public ChangePasswordDTO() {
    	
    }
    
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
