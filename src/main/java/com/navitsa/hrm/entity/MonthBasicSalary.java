package com.navitsa.hrm.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="month_basic_salary")
public class MonthBasicSalary {
	
	@EmbeddedId
	private MonthBasicSalaryPK monthBasicSalaryPK;
	
	@Column(name = "basic_Sal")
	private double basicSalary;
	
	@Column(name = "totalBasicSal")
	private double totalBasicSal;
	
	@Column(name = "totalEpfSal")
	private double totalEpfSal;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "Company_ID", referencedColumnName = "Company_ID")
	private CompanyMaster companyID;



	public MonthBasicSalaryPK getMonthBasicSalaryPK() {
		return monthBasicSalaryPK;
	}

	public void setMonthBasicSalaryPK(MonthBasicSalaryPK monthBasicSalaryPK) {
		this.monthBasicSalaryPK = monthBasicSalaryPK;
	}

	public double getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(double basicSalary) {
		this.basicSalary = basicSalary;
	}

	public double getTotalBasicSal() {
		return totalBasicSal;
	}

	public void setTotalBasicSal(double totalBasicSal) {
		this.totalBasicSal = totalBasicSal;
	}

	public double getTotalEpfSal() {
		return totalEpfSal;
	}

	public void setTotalEpfSal(double totalEpfSal) {
		this.totalEpfSal = totalEpfSal;
	}

	public CompanyMaster getCompanyID() {
		return companyID;
	}

	public void setCompanyID(CompanyMaster companyID) {
		this.companyID = companyID;
	}
	
	
	
	

}
