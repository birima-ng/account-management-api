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

    
}
