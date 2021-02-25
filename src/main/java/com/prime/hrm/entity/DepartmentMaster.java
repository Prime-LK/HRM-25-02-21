package com.prime.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity(name="DepartmentMaster")
@Table(name="department")
public class DepartmentMaster {

	@Id
	@Column(name="Department_ID")
	private String depID;
	
	@NotEmpty(message = "required")
	@Column(name="Department")
	private String department;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Company_ID", referencedColumnName="Company_ID")
	private CompanyMaster company;

	public String getDepID() {
		return depID;
	}

	public void setDepID(String depID) {
		this.depID = depID;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}

	public DepartmentMaster(String depID, String department,CompanyMaster company) {
		this.depID = depID;
		this.department = department;
		this.company = company;
	}

	public DepartmentMaster() {
	}

	public DepartmentMaster(String depID) {
		this.depID = depID;
	}
	
	
	
}
