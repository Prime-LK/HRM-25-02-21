package com.navitsa.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="process_payroll")
public class ProcessPayroll {
	
	@Id
	@Column(name="Process_Payroll_ID")
	private String processPayrollID;
	
	@Column(name="Pay_Period_ID")
	private String periodID;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Pay_Code_ID", referencedColumnName ="Pay_Code_ID")
	private PayCode payCode;
	
	@Column(name="Start_Date")
	private String startDate;
	
	@Column(name="End_Date")
	private String endDate;
	
	
	public String getProcessPayrollID() {
		return processPayrollID;
	}

	public void setProcessPayrollID(String processPayrollID) {
		this.processPayrollID = processPayrollID;
	}

	public String getPeriodID() {
		return periodID;
	}

	public void setPeriodID(String periodID) {
		this.periodID = periodID;
	}

	public PayCode getPayCode() {
		return payCode;
	}

	public void setPayCode(PayCode payCode) {
		this.payCode = payCode;
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

	public ProcessPayroll(String processPayrollID, String periodID, PayCode payCode, String startDate, String endDate) {
		
		this.processPayrollID = processPayrollID;
		this.periodID = periodID;
		this.payCode = payCode;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public ProcessPayroll() {
	
	}
	
	
	
}
