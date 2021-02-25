//package com.prime.hrm.entity;
//
//import java.io.Serializable;
//
//import javax.persistence.Column;
//import javax.persistence.Embeddable;
//import javax.persistence.FetchType;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.validation.constraints.NotEmpty;
//
//@Embeddable
//public class BankPK implements Serializable{
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//	
//	@ManyToOne(optional=false, fetch = FetchType.EAGER)
//	@JoinColumn(name="Bank_ID", referencedColumnName ="Bank_ID")
//	private BankMaster bankid;
//	
//	
//	
//	@NotEmpty(message="required")
//	@Column(name="Branch_ID")
//	private String branchid;
//
//	
//
//	public BankMaster getBankid() {
//		return bankid;
//	}
//
//	public void setBankid(BankMaster bankid) {
//		this.bankid = bankid;
//	}
//
//	public String getBranchid() {
//		return branchid;
//	}
//
//	public void setBranchid(String branchid) {
//		this.branchid = branchid;
//	}
//	
//	
//	
//}
