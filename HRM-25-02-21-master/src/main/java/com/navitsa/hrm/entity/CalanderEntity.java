package com.navitsa.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="calander")
public class CalanderEntity {
	
	@Id
	@Column(name="Date")
	private String date;

	@Column(name="CalanderID")
	private String calander_ID;
		
	@Column(name="Description")
	private String description;
	
	@Column(name="Types")
	private String types;
	
	@Column(name="Status")
	private String status;
	
	@Column(name="CompanyID")
	private String company_ID;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCalander_ID() {
		return calander_ID;
	}

	public void setCalander_ID(String calander_ID) {
		this.calander_ID = calander_ID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCompany_ID() {
		return company_ID;
	}

	public void setCompany_ID(String company_ID) {
		this.company_ID = company_ID;
	}
	
}
