package com.prime.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="dependent_type_master")
public class DependentTypeMaster {

	@Id
	@Column(name="Dependent_Type_ID")
	private String dTypeID;
	
	@NotEmpty(message = "required")
	@Column(name="Dependent_Type")
	private String dType;

	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Company_ID", referencedColumnName="Company_ID")
	private CompanyMaster company;
	
	public String getdTypeID() {
		return dTypeID;
	}

	public void setdTypeID(String dTypeID) {
		this.dTypeID = dTypeID;
	}

	public String getdType() {
		return dType;
	}

	public void setdType(String dType) {
		this.dType = dType;
	}
	
	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}

	public DependentTypeMaster(String dTypeID, String dType,CompanyMaster company) {
		this.dTypeID = dTypeID;
		this.dType = dType;
		this.company = company;
	}

	public DependentTypeMaster() {
	}
	
	
}
