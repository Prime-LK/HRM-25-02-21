package com.prime.hrm.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name="SalaryHistoryDetails")
@Table(name="emp_salary_history_details")
public class SalaryHistoryDetails {

	@EmbeddedId
	private SalaryHistoryDetailsPK saHiDePK;
	
	@Column(name="Process_Year")
	private String processYear;
	
	@Column(name="Process_Month")
	private String processMonth;
	
	@Column(name="Amount")
	private String amount;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Company_ID", referencedColumnName="Company_ID")
	private CompanyMaster company;

	public SalaryHistoryDetailsPK getSaHiDePK() {
		return saHiDePK;
	}

	public void setSaHiDePK(SalaryHistoryDetailsPK saHiDePK) {
		this.saHiDePK = saHiDePK;
	}

	public String getProcessYear() {
		return processYear;
	}

	public void setProcessYear(String processYear) {
		this.processYear = processYear;
	}

	public String getProcessMonth() {
		return processMonth;
	}

	public void setProcessMonth(String processMonth) {
		this.processMonth = processMonth;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}
	
	public SalaryHistoryDetails(SalaryHistoryDetailsPK saHiDePK, String processYear, String processMonth, String amount
			, CompanyMaster company) {
		this.saHiDePK = saHiDePK;
		this.processYear = processYear;
		this.processMonth = processMonth;
		this.amount = amount;
		this.company = company;
	}

	public SalaryHistoryDetails() {
		
	}
}
