package com.atom.artaccount.dto;

import java.util.Date;

import com.atom.artaccount.model.Departement;
import com.atom.artaccount.model.Region;

public class SearchCumulDTO {
	
	private Departement departement;
	private Region region;
	private Date startDate;
	private Date endDate;
	
	public Departement getDepartement() {
		return departement;
	}
	
	public void setDepartement(Departement departement) {
		this.departement = departement;
	}
	
	public Region getRegion() {
		return region;
	}
	
	public void setRegion(Region region) {
		this.region = region;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
