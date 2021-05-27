package com.navitsa.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "applyleaves")
public class ApplyLeave_Entity {
	
	@Id
	@Column(name = "ID")
	private String leaveID;
	
	@Column(name = "Type")
	private String type;
	
	@Column(name = "Days")
	private String days;
	
	@Column(name = "Time")
	private String time;
	
	@Column(name = "Description")
	private String desc;
	
	@Column(name = "Approved")
	private Boolean approved;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "Department_ID", referencedColumnName = "Department_ID")
	private DepartmentMaster department;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "Employee_ID", referencedColumnName = "Employee_ID")
	private Employee employee;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "leaveCode", referencedColumnName = "leaveCode")
	private leaveClass leaveType;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="CompanyID", referencedColumnName="Company_ID")
	private CompanyMaster company;

	public String getLeaveID() {
		return leaveID;
	}

	public void setLeaveID(String leaveID) {
		this.leaveID = leaveID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public DepartmentMaster getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentMaster department) {
		this.department = department;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public leaveClass getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(leaveClass leaveType) {
		this.leaveType = leaveType;
	}

	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public ApplyLeave_Entity(String leaveID, String type, String days, String time, String desc, Boolean approved,
			DepartmentMaster department, Employee employee, leaveClass leaveType, CompanyMaster company) {
		super();
		this.leaveID = leaveID;
		this.type = type;
		this.days = days;
		this.time = time;
		this.desc = desc;
		this.approved = approved;
		this.department = department;
		this.employee = employee;
		this.leaveType = leaveType;
		this.company = company;
	}

	public ApplyLeave_Entity() {
		super();

	}
	
}
