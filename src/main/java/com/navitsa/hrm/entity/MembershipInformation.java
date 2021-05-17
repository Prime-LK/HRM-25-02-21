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
@Table(name="membership_information")
public class MembershipInformation {

	@Id
	@Column(name="Membership_Type_ID")
	private String memID;
	
	@NotEmpty(message = "required")
	@Column(name="Membership_Type")
	private String memType;	
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Company_ID", referencedColumnName="Company_ID")
	private CompanyMaster company;

	public String getMemID() {
		return memID;
	}

	public void setMemID(String memID) {
		this.memID = memID;
	}

	public String getMemType() {
		return memType;
	}

	public void setMemType(String memType) {
		this.memType = memType;
	}
	
	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}

	public MembershipInformation(String memID, String memType,CompanyMaster company) {
		this.memID = memID;
		this.memType = memType;
		this.company = company;
	}

	public MembershipInformation() {
	}
	
	
	
}
