package com.atom.artaccount.dto;

import lombok.Data;

import java.util.List;

@Data
public class DivisionAdministrativeDto {
    private String id;
    private String nom;
    private String parentId;
    private List<DivisionAdministrativeDto> children;
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
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public List<DivisionAdministrativeDto> getChildren() {
		return children;
	}
	public void setChildren(List<DivisionAdministrativeDto> children) {
		this.children = children;
	}
    
}