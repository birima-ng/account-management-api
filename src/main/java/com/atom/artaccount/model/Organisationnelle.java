package com.atom.artaccount.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name="organisationnelle")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Organisationnelle extends com.atom.artaccount.model.Entity{

	private static final long serialVersionUID = -8091879091924046844L;
	
	public Organisationnelle() {
		
	}
	
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Organisationnelle parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Organisationnelle> children = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Organisationnelle getParent() {
		return parent;
	}

	public void setParent(Organisationnelle parent) {
		this.parent = parent;
	}

	public List<Organisationnelle> getChildren() {
		return children;
	}

	public void setChildren(List<Organisationnelle> children) {
		this.children = children;
	}
	
    
}
