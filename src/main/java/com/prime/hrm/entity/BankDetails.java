package com.prime.hrm.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="emp_bank_details")
public class BankDetails {

	@EmbeddedId
	private BankDetailsPK bPK;

	@Column(name="Bank_ID")
	private String bankid;
	
	@Column(name="Branch_ID")
	private String branch;
	
	public BankDetailsPK getbPK() {
		return bPK;
	}

	public void setbPK(BankDetailsPK bPK) {
		this.bPK = bPK;
	}

	public String getBankid() {
		return bankid;
	}

	public void setBankid(String bankid) {
		this.bankid = bankid;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public BankDetails() {
	}

	public BankDetails(BankDetailsPK bPK, String bankid, String branch) {
		this.bPK = bPK;
		this.bankid = bankid;
		this.branch = branch;
	}
	
	
	
}
