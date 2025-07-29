package com.atom.artaccount.model;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;


@Entity(name="feature")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Feature extends com.atom.artaccount.model.Entity{

	private static final long serialVersionUID = -8091879091924046844L;
	
	@Column(name="nom")
    private String nom;
	
	@Column(name="nom_en")
    private String nomen;
	
	@Column(name="nom_es")
    private String nomes;
	
	@Column(name="displayable")
    private int displayable;
	
	@Column(name="parent")
    private boolean parent;
    
    @Column(name="code")
    private String code;
    
    @Column(name="codeparent")
    private String codeparent;
    
    @Column(name="path", nullable = true)
    private String path;
    
    @Column(name="title", nullable = true)
    private String title;
    
    @Column(name="icon", nullable = true)
    private String icon;
    
    @Column(name="sclass", nullable = true)
    private String sclass;
    
    @Column(name="badge", nullable = true)
    private String badge;
    
    @Column(name="badgeClass", nullable = true)
    private String badgeClass;
    
    @Column(name="externalLink")
    @ColumnDefault("false")
    private boolean externalLink;
    
    
    @Column(name="indice")
    private Integer indice;
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "module", nullable = false)
    private Module module;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "systeme", nullable = false)
    private Systeme systeme;
	
	@Lob
	@Column(name="description", columnDefinition = "LONGTEXT")
	private String description;
	
	@Lob
	@Column(name="description_en", columnDefinition = "LONGTEXT")
	private String description_en;
	

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
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

	public String getSclass() {
		return sclass;
	}

	public void setSclass(String sclass) {
		this.sclass = sclass;
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
		return externalLink;
	}

	public void setExternalLink(boolean externalLink) {
		this.externalLink = externalLink;
	}

	public boolean isParent() {
		return parent;
	}

	public void setParent(boolean parent) {
		this.parent = parent;
	}

	public String getCodeparent() {
		return codeparent;
	}

	public void setCodeparent(String codeparent) {
		this.codeparent = codeparent;
	}

	public Integer getIndice() {
		return indice;
	}

	public void setIndice(Integer indice) {
		this.indice = indice;
	}

	public String getNomen() {
		return nomen;
	}

	public void setNomen(String nomen) {
		this.nomen = nomen;
	}

	public String getNomes() {
		return nomes;
	}

	public void setNomes(String nomes) {
		this.nomes = nomes;
	}

	public int getDisplayable() {
		return displayable;
	}

	public void setDisplayable(int displayable) {
		this.displayable = displayable;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription_en() {
		return description_en;
	}

	public void setDescription_en(String description_en) {
		this.description_en = description_en;
	}

	public Systeme getSysteme() {
		return systeme;
	}

	public void setSysteme(Systeme systeme) {
		this.systeme = systeme;
	}

}
