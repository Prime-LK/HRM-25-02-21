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
@Table(name="employee_types")
public class EmployeeType {
	
	@Id
	@Column(name="Emp_Type_ID")
	private String tid;
	
	@NotEmpty(message = "required")
	@Column(name="Employee_Type")
	private String type;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Company_ID", referencedColumnName="Company_ID")
	private CompanyMaster company;

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
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
	
	public EmployeeType(String tid, String type,CompanyMaster company) {
		this.tid = tid;
		this.type = type;
		this.company = company;
	}

	public EmployeeType() {
		super();
	}
	
	

}
