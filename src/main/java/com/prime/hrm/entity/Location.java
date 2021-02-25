package com.prime.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="locations")

public class Location {
	
	@Id
		
	@Column(name="Location_Code")
	private String locationcode;
	

	@Column(name="Description")
	private String description;
	

	@Column(name="Status")
	private String status;


	public String getLocationcode() {
		return locationcode;
	}


	public void setLocationcode(String locationcode) {
		this.locationcode = locationcode;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Location() 
	{
		
	}


	
	
	
}
