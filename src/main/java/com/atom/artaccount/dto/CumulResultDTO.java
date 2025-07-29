package com.atom.artaccount.dto;

import java.util.List;

public class CumulResultDTO {
	
	private String typeproduit;
	private Integer nbre;
	private List<CumulDTO> configs;
	
	public Integer getNbre() {
		return nbre;
	}
	
	public void setNbre(Integer nbre) {
		this.nbre = nbre;
	}
	
	public List<CumulDTO> getConfigs() {
		return configs;
	}
	
	public void setConfigs(List<CumulDTO> configs) {
		this.configs = configs;
	}

	public String getTypeproduit() {
		return typeproduit;
	}

	public void setTypeproduit(String typeproduit) {
		this.typeproduit = typeproduit;
	}
	
}
