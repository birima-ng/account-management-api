package com.atom.artaccount.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="config_speculation_systeme")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ConfigSpeculationSysteme extends com.atom.artaccount.model.Entity{

	private static final long serialVersionUID = -8091879091924046844L;
	
	public ConfigSpeculationSysteme() {
		
	}
	
	@Column(name = "configure", columnDefinition = "BIT")
	private Boolean configure;
	
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categorie_speculation", nullable = false)
    private CategorieSpeculation categoriespeculation;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "systeme", nullable = false)
    private Systeme systeme;

	public CategorieSpeculation getCategoriespeculation() {
		return categoriespeculation;
	}

	public void setCategoriespeculation(CategorieSpeculation categoriespeculation) {
		this.categoriespeculation = categoriespeculation;
	}

	public Systeme getSysteme() {
		return systeme;
	}

	public void setSysteme(Systeme systeme) {
		this.systeme = systeme;
	}

	public Boolean getConfigure() {
		return configure;
	}

	public void setConfigure(Boolean configure) {
		this.configure = configure;
	}


	
}
