package com.navitsa.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="leave_type")
public class LeaveType {
	
	
	@Id
	@Column(name="leaveCode")
	private String leaveCode;
	
	@Column(name="LeaveType")
	private String leaveType;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Company_ID", referencedColumnName="Company_ID")
	private CompanyMaster company;

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

	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}

	public LeaveType(String leaveCode, String leaveType, CompanyMaster company) {
		super();
		this.leaveCode = leaveCode;
		this.leaveType = leaveType;
		this.company = company;
	}

	public LeaveType() {
		super();
		// TODO Auto-generated constructor stub
	}
	


}