package com.atom.artaccount.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;

@Entity(name="message_whatsapp")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Message extends com.atom.artaccount.model.Entity {
	
	private static final long serialVersionUID = -8091879091924046844L;

	@Column(name="telephone")
    private String telephone;
	

	@Column(name="wabaid")
	private String wabaid;
	
	@Column(name="phonenumberid")
	private String phonenumberid;
	
	@Column(name="displayphonenumber")
	private String displayphonenumber;
	
	@Column(name="messageid")
	private String messageid;
    
	@Lob
	@Column(name="message", columnDefinition = "LONGTEXT")
	private String message;

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getWabaid() {
		return wabaid;
	}

	public void setWabaid(String wabaid) {
		this.wabaid = wabaid;
	}

	public String getPhonenumberid() {
		return phonenumberid;
	}

	public void setPhonenumberid(String phonenumberid) {
		this.phonenumberid = phonenumberid;
	}

	public String getDisplayphonenumber() {
		return displayphonenumber;
	}

	public void setDisplayphonenumber(String displayphonenumber) {
		this.displayphonenumber = displayphonenumber;
	}

	public String getMessageid() {
		return messageid;
	}

	public void setMessageid(String messageid) {
		this.messageid = messageid;
	}

    
}
