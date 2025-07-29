package com.atom.artaccount.dto;


public class SubFeatureModuleDTO {
    private String id;
    private String nom;
    private String state;
    private String  path;
    private String title;
    private String icon;
    private String className;
    private String badge;
    private String badgeClass;
    private boolean isExternalLink;
    
    //private List<SubFeatureModuleDTO> features;
    
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getBadge() {
		return badge;
	}

	public void setBadge(String badge) {
		this.badge = badge;
	}

	public String getBadgeClass() {
		return badgeClass;
	}

	public void setBadgeClass(String badgeClass) {
		this.badgeClass = badgeClass;
	}

	public boolean isExternalLink() {
		return isExternalLink;
	}

	public void setExternalLink(boolean isExternalLink) {
		this.isExternalLink = isExternalLink;
	}
/*
	public List<SubFeatureModuleDTO> getFeatures() {
		return features;
	}

	public void setFeatures(List<SubFeatureModuleDTO> features) {
		this.features = features;
	}*/
    
}
