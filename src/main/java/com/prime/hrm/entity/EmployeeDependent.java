package com.prime.hrm.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "emp_dependents")
public class EmployeeDependent {

	@EmbeddedId
	private DependentPK dependentPK;
	
	@NotNull(message="required")
	@Column(name = "Name")
	private String name;
	
	@Column(name = "DOB")
	private String dob;
	
	@NotNull(message="required")
	@Column(name = "Contact_No")
	private String conNo;

	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Company_ID", referencedColumnName="Company_ID")
	private CompanyMaster company;
	
	public DependentPK getDependentPK() {
		return dependentPK;
	}

	public void setDependentPK(DependentPK dependentPK) {
		this.dependentPK = dependentPK;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getConNo() {
		return conNo;
	}

	public void setConNo(String conNo) {
		this.conNo = conNo;
	}
	
	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}

	public EmployeeDependent(DependentPK dependentPK, String name, String dob, String conNo
			,CompanyMaster company) {
		this.dependentPK = dependentPK;
		this.name = name;
		this.dob = dob;
		this.conNo = conNo;
		this.company = company;
	}

	public EmployeeDependent() {
	}
	
	
	
}
