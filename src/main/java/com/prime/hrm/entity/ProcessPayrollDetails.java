package com.prime.hrm.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="process_payroll_details")
public class ProcessPayrollDetails {

	@EmbeddedId
	private ProcessPayrollDetailsPK proPayDePK;
	
	private String amount;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Company_ID", referencedColumnName="Company_ID")
	private CompanyMaster company;

	public ProcessPayrollDetailsPK getProPayDePK() {
		return proPayDePK;
	}

	public void setProPayDePK(ProcessPayrollDetailsPK proPayDePK) {
		this.proPayDePK = proPayDePK;
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

	public ProcessPayrollDetails(ProcessPayrollDetailsPK proPayDePK, String amount, CompanyMaster company) {
		this.proPayDePK = proPayDePK;
		this.amount = amount;
		this.company = company;
	}

	public ProcessPayrollDetails() {
	
	}
	
	
	
	
}
