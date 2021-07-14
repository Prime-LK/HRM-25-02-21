package com.navitsa.hrm.entity;

import java.util.Date;

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
	private int days;
	
	@Column(name = "Dates")
	private String dates;
	
	@Column(name = "Description")
	private String desc;
	
	@Column(name = "Approved")
	private Boolean approved;
	
	@Column(name = "create_time")
	private Date createTime;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="CompanyID", referencedColumnName="Company_ID")
	private CompanyMaster company;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "Department_ID", referencedColumnName = "Department_ID")
	private DepartmentMaster department;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "Employee_ID", referencedColumnName = "Employee_ID")
	private Employee employee;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "leaveCode", referencedColumnName = "leaveCode")
	private LeaveType leaveType;
	
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

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
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

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}


	public ApplyLeave_Entity(String leaveID, String type, int days, String dates, String desc, Boolean approved,
			Date createTime, CompanyMaster company, DepartmentMaster department, Employee employee,
			LeaveType leaveType) {
		super();
		this.leaveID = leaveID;
		this.type = type;
		this.days = days;
		this.dates = dates;
		this.desc = desc;
		this.approved = approved;
		this.createTime = createTime;
		this.company = company;
		this.department = department;
		this.employee = employee;
		this.leaveType = leaveType;
	}

	public ApplyLeave_Entity() {
		super();

	}
	
}
