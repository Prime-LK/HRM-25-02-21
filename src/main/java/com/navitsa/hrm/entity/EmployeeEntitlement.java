package com.navitsa.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employee_entitlement")
public class EmployeeEntitlement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_entitlement_id")
	private String employeeEntitlementId;

	@Column(name = "leave_amount")
	private double leaveAmount;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "employee_type_id", referencedColumnName = "Emp_Type_ID")
	private EmployeeType employeeType;	
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "leave_type_id", referencedColumnName = "leaveTypeID")
	private LeaveType leaveType;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="company_id", referencedColumnName="Company_ID")
	private CompanyMaster company;

	public EmployeeEntitlement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeEntitlement(String employeeEntitlementId, double leaveAmount, EmployeeType employeeType,
			LeaveType leaveType, CompanyMaster company) {
		super();
		this.employeeEntitlementId = employeeEntitlementId;
		this.leaveAmount = leaveAmount;
		this.employeeType = employeeType;
		this.leaveType = leaveType;
		this.company = company;
	}

	public String getEmployeeEntitlementId() {
		return employeeEntitlementId;
	}

	public void setEmployeeEntitlementId(String employeeEntitlementId) {
		this.employeeEntitlementId = employeeEntitlementId;
	}

	public double getLeaveAmount() {
		return leaveAmount;
	}

	public void setLeaveAmount(double leaveAmount) {
		this.leaveAmount = leaveAmount;
	}

	public EmployeeType getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(EmployeeType employeeType) {
		this.employeeType = employeeType;
	}

	public LeaveType getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}

	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}
	

}
