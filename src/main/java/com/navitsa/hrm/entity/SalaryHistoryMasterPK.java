package com.navitsa.hrm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class SalaryHistoryMasterPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name="SalaryHisMasterID")
	private String id;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Pay_Code_ID", referencedColumnName ="Pay_Code_ID")
	private PayCode payCode;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PayCode getPayCode() {
		return payCode;
	}

	public void setPayCode(PayCode payCode) {
		this.payCode = payCode;
	}
	
}
