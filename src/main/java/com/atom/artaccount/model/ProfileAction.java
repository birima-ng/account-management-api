package com.atom.artaccount.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.persistence.Entity;

@Entity(name="profile_action")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProfileAction extends com.atom.artaccount.model.Entity{

	private static final long serialVersionUID = -8091879091924046844L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profile", nullable = false)
    private Profile profile;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "action", nullable = false)
    private Action action;

    @Column(name="allowed")
    private boolean allowed;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public boolean isAllowed() {
        return allowed;
    }

    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }
    
}
