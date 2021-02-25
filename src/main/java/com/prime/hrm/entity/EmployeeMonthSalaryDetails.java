package com.prime.hrm.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="emp_month_salary_details")
public class EmployeeMonthSalaryDetails {

	@EmbeddedId
	private EmployeeMonthSalaryDetailsPK monthDePk;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="Process_Year")
	private String pYear;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="Process_Month")
	private String pMonth;
	
	@Column(name="Amount")
	private int amount;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Company_ID", referencedColumnName="Company_ID")
	private CompanyMaster company;

	public EmployeeMonthSalaryDetailsPK getMonthDePk() {
		return monthDePk;
	}

	public void setMonthDePk(EmployeeMonthSalaryDetailsPK monthDePk) {
		this.monthDePk = monthDePk;
	}

	public String getpYear() {
		return pYear;
	}

	public void setpYear(String pYear) {
		this.pYear = pYear;
	}

	public String getpMonth() {
		return pMonth;
	}

	public void setpMonth(String pMonth) {
		this.pMonth = pMonth;
	}
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}

	public EmployeeMonthSalaryDetails(EmployeeMonthSalaryDetailsPK monthDePk, String pYear, String pMonth, int amount,
			CompanyMaster company) {
		this.monthDePk = monthDePk;
		this.pYear = pYear;
		this.pMonth = pMonth;
		this.amount = amount;
		this.company = company;
	}


	public EmployeeMonthSalaryDetails() {
	
	}
	
	
}
