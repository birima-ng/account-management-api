package com.atom.artaccount.dto;

import java.util.List;

public class FeatureDTO {
    private String id;
    private String nom;
    private List<ActionDTO> actions;
    
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<ActionDTO> getActions() {
		return actions;
	}
	
	public void setActions(List<ActionDTO> actions) {
		this.actions = actions;
	}
    
    
}
