package com.prime.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "empentitlements")
public class EmpEntitlementsClass {

	@Id
	@Column(name = "Ent_ID")
	private String ent_ID;
	// @GeneratedValue (strategy = GenerationType.IDENTITY)

	@Column(name = "LeaveAmount")
	private String leaveAmount;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "Emp_Category", referencedColumnName = "Emp_Category_ID")
	private EmployeeCategory empCategory;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "LeaveType", referencedColumnName = "leaveCode")
	private leaveClass leaveType;

	public String getEnt_ID() {
		return ent_ID;
	}

	public void setEnt_ID(String ent_ID) {
		this.ent_ID = ent_ID;
	}

	public String getLeaveAmount() {
		return leaveAmount;
	}

	public void setLeaveAmount(String leaveAmount) {
		this.leaveAmount = leaveAmount;
	}

	public EmployeeCategory getEmpCategory() {
		return empCategory;
	}

	public void setEmpCategory(EmployeeCategory empCategory) {
		this.empCategory = empCategory;
	}

	public leaveClass getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(leaveClass leaveType) {
		this.leaveType = leaveType;
	}

	/*
	 * @ManyToOne(optional=false, fetch = FetchType.EAGER)
	 * 
	 * @JoinColumn(name="Emp_Category_ID", referencedColumnName="Emp_Category_ID")
	 * private EmployeeCategory category_ID;
	 */

}
