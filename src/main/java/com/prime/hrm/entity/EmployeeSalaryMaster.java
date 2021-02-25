package com.prime.hrm.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="employee_salary_master")
public class EmployeeSalaryMaster {
	
	@EmbeddedId
	EmployeeSalaryMasterPK empSalaryPK;
	
	@Column(name="Basic_Salary")
	private String basicSalary;
	
	@Column(name="Process_Date")
	private String processDate;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Company_ID", referencedColumnName="Company_ID")
	private CompanyMaster company;

	public EmployeeSalaryMasterPK getEmpSalaryPK() {
		return empSalaryPK;
	}

	public void setEmpSalaryPK(EmployeeSalaryMasterPK empSalaryPK) {
		this.empSalaryPK = empSalaryPK;
	}

	public String getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(String basicSalary) {
		this.basicSalary = basicSalary;
	}

	public String getProcessDate() {
		return processDate;
	}

	public void setProcessDate(String processDate) {
		this.processDate = processDate;
	}

	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}
	
	public EmployeeSalaryMaster(EmployeeSalaryMasterPK empSalaryPK, String basicSalary, String processDate, CompanyMaster company) {
		this.empSalaryPK = empSalaryPK;
		this.basicSalary = basicSalary;
		this.processDate = processDate;
		this.company = company;
	}

	public EmployeeSalaryMaster() {
	}
	
	

}
