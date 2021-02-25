package com.prime.hrm.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class EmployeeMembershipPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "Employee_ID", referencedColumnName = "Employee_ID")
	Employee empID;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "Membership_Type_ID", referencedColumnName = "Membership_Type_ID")
	MembershipInformation membershipInformation;

	public Employee getEmpID() {
		return empID;
	}

	public void setEmpID(Employee empID) {
		this.empID = empID;
	}

	public MembershipInformation getMembershipInformation() {
		return membershipInformation;
	}

	public void setMembershipInformation(MembershipInformation membershipInformation) {
		this.membershipInformation = membershipInformation;
	}
	
	
}
