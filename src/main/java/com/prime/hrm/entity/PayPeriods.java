package com.prime.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="pay_periods")
public class PayPeriods {
		
	@Id
	@Column(name="Pay_Period_ID")
	private String payPeriodID;
	
	@Column(name="Start_Date")
	private String startDate;
	
	@Column(name="End_Date")
	private String endDate;
	
	@Column(name="Pay_Date")
	private String payDate;
	
	@Column(name="Status")
	private String status;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Company_ID", referencedColumnName="Company_ID")
	private CompanyMaster company;

	public String getPayPeriodID() {
		return payPeriodID;
	}

	public void setPayPeriodID(String payPeriodID) {
		this.payPeriodID = payPeriodID;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}
	
	public PayPeriods(String payPeriodID, String startDate, String endDate, String payDate, String status,
			CompanyMaster company) {
		this.payPeriodID = payPeriodID;
		this.startDate = startDate;
		this.endDate = endDate;
		this.payDate = payDate;
		this.status = status;
		this.company = company;
	}

	public PayPeriods() {
		
	}
}
