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
@Table(name="designation_master")
public class DesignationMaster {

	@Id
	@Column(name="Designation_ID")
	private String did;
	
	@NotEmpty(message="required")
	@Column(name="Designation")
	private String designation;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Company_ID", referencedColumnName="Company_ID")
	private CompanyMaster company;

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public DesignationMaster(String did, String designation,CompanyMaster company) {
		this.did = did;
		this.designation = designation;
		this.company = company;
	}

	public DesignationMaster() {
	}

	public DesignationMaster(String did) {
			this.did = did;
	}
	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}
	
	
	
}
