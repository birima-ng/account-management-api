package com.atom.artaccount.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity(name="reservation_send")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Reservation extends com.atom.artaccount.model.Entity {
	
	private static final long serialVersionUID = -8091879091924046844L;

	@Column(name="sender")
    private int sender;
	
	
	@Column(name="send")
    private boolean send;


	public boolean isSend() {
		return send;
	}

	public void setSend(boolean send) {
		this.send = send;
	}

	public int getSender() {
		return sender;
	}

	public void setSender(int sender) {
		this.sender = sender;
	}

    
}
