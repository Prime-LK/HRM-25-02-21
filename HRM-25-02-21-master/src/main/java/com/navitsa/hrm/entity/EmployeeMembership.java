package com.navitsa.hrm.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="emp_membership")
public class EmployeeMembership {

	@EmbeddedId
	private EmployeeMembershipPK employeeMembershipPK;
	@NotEmpty(message="required")
	
	@Column(name="Description")
	private String description;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Company_ID", referencedColumnName="Company_ID")
	private CompanyMaster company;
	
	@Column(name="Membership_Since")
	private String since;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public EmployeeMembershipPK getEmployeeMembershipPK() {
		return employeeMembershipPK;
	}

	public void setEmployeeMembershipPK(EmployeeMembershipPK employeeMembershipPK) {
		this.employeeMembershipPK = employeeMembershipPK;
	}

	public String getSince() {
		return since;
	}

	public void setSince(String since) {
		this.since = since;
	}

	public EmployeeMembership() {
		
	}
	
	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}

	public EmployeeMembership(EmployeeMembershipPK employeeMembershipPK, String description,
			String since,CompanyMaster company) {
		this.employeeMembershipPK = employeeMembershipPK;
		this.description = description;
		this.since = since;
		this.company = company;
	}	
	
}
