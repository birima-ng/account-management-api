package com.atom.artaccount.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity(name="message_bienvenue")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MessageBienvenue extends com.atom.artaccount.model.Entity {
	
	private static final long serialVersionUID = -8091879091924046844L;

	@Column(name="telephone")
    private String telephone;

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	
    
}
