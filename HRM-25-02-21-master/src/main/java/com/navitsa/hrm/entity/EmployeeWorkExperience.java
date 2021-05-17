package com.navitsa.hrm.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="emp_work_experience")
public class EmployeeWorkExperience {

	@EmbeddedId
	private EmployeeWorkExperiencePK employeeWorkExperiencePK;
	
	@NotNull(message="required")
	@Column(name="Company")
	private String companyName;
	
	@NotNull(message="required")
	@Column(name="Last_Designation")
	private String designation;
	
	@Column(name="Joined_Date")
	private String joinDate;
	
	@Column(name="Resign_Date")
	private String resignDate;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Company_ID", referencedColumnName="Company_ID")
	private CompanyMaster company;
	
	public EmployeeWorkExperiencePK getEmployeeWorkExperiencePK() {
		return employeeWorkExperiencePK;
	}
	
	public void setEmployeeWorkExperiencePK(EmployeeWorkExperiencePK employeeWorkExperiencePK) {
		this.employeeWorkExperiencePK = employeeWorkExperiencePK;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public String getResignDate() {
		return resignDate;
	}

	public void setResignDate(String resignDate) {
		this.resignDate = resignDate;
	}	

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}
	
	public EmployeeWorkExperience(EmployeeWorkExperiencePK employeeWorkExperiencePK, String companyName,
			String designation, String joinDate, String resignDate, CompanyMaster company) {
		this.employeeWorkExperiencePK = employeeWorkExperiencePK;
		this.companyName = companyName;
		this.designation = designation;
		this.joinDate = joinDate;
		this.resignDate = resignDate;
		this.company = company;
	}

	public EmployeeWorkExperience() {
	}

	
}
