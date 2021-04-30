package com.navitsa.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("unused")
@Entity
@Table(name="leaves")
public class leaveClass {
	
	
	@Id
	@Column(name="leaveCode")
	//@GeneratedValue (strategy = GenerationType.IDENTITY)
	private String leaveCode;
	
	@Column(name="LeaveType")
	private String leaveType;

	public String getLeaveCode() {
		return leaveCode;
	}

	public void setLeaveCode(String leaveCode) {
		this.leaveCode = leaveCode;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}




	
	
}