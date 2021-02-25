package com.prime.hrm.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class LanguagePK implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "Employee_ID", referencedColumnName = "Employee_ID")
	private Employee empID;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "Language_ID", referencedColumnName = "Language_ID")
	private LanguageMaster lid;

	public Employee getEmpID() {
		return empID;
	}

	public void setEmpID(Employee empID) {
		this.empID = empID;
	}

	public LanguageMaster getLid() {
		return lid;
	}

	public void setLid(LanguageMaster lid) {
		this.lid = lid;
	}
	
	
}
