package com.atom.artaccount.dto;

import java.util.List;

public class FeatureProfileActionDTO {
    private String id;
    private String nom;
    private List<ProfileActionDTO> profilactions;
    
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

	public List<ProfileActionDTO> getProfilactions() {
		return profilactions;
	}

	public void setProfilactions(List<ProfileActionDTO> profilactions) {
		this.profilactions = profilactions;
	}
    
}
