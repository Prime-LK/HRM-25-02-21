package com.navitsa.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="extra_activiy_types")
public class ExtraActivityType {
	
	@Id
	@Column(name="Extra_Activity_Type_ID")
	private String actTypeID;
	
	@NotEmpty(message="required")
	@Column(name="Extra_Activiy_Type")
	private String type;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Company_ID", referencedColumnName="Company_ID")
	private CompanyMaster company;

	public String getActTypeID() {
		return actTypeID;
	}

	public void setActTypeID(String actTypeID) {
		this.actTypeID = actTypeID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}

	public ExtraActivityType(String actTypeID, String type,CompanyMaster company) {
		this.actTypeID = actTypeID;
		this.type = type;
		this.company = company;
	}

	public ExtraActivityType() {
	}
	
	

}
