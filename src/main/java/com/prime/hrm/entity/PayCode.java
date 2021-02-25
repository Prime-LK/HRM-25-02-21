package com.prime.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="pay_codes")
public class PayCode {
	
	@Id
	@Column(name="Pay_Code_ID")
	private String payCodeID;
	
	@Column(name="Pay_Code")
	private String payCode;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Pay_Period_ID", referencedColumnName ="Pay_Period_ID")
	private PayPeriods periodID;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="Start_Date")
	private String startDate;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="End_Date")
	private String endDate;
	
	@Column(name="Status")
	private String status;
	
	@Column(name="Remarks")
	private String remarks;

	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Company_ID", referencedColumnName="Company_ID")
	private CompanyMaster company;
	
	public String getPayCodeID() {
		return payCodeID;
	}

	public void setPayCodeID(String payCodeID) {
		this.payCodeID = payCodeID;
	}

	public String getPayCode() {
		return payCode;
	}

	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}

	public PayPeriods getPeriodID() {
		return periodID;
	}

	public void setPeriodID(PayPeriods periodID) {
		this.periodID = periodID;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}

	public PayCode(String payCodeID, String payCode, PayPeriods periodID, String startDate, String endDate,
			String status, String remarks,CompanyMaster company) {
		
		this.payCodeID = payCodeID;
		this.payCode = payCode;
		this.periodID = periodID;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.remarks = remarks;
		this.company = company;
	}

	public PayCode() {
		
	}

	public PayCode(String payCodeID) {
		this.payCodeID = payCodeID;
	}
	
	
	
}
