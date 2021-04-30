package com.navitsa.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="uom")

public class UOM {
	
	@Id
		
	@Column(name="UOM_Code")
	private String uom;
	

	@Column(name="Description")
	private String description;
	

	@Column(name="Status")
	private String status;


	public String getUom() {
		return uom;
	}


	public void setUom(String uom) {
		this.uom = uom;
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


	public UOM() {
		
	}


	
	
	
	
	
}