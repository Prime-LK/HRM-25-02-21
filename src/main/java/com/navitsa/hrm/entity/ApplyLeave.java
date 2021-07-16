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
@Table(name = "apply_leave")
public class ApplyLeave {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "apply_leave_id")
	private String leaveID;
	
	@Column(name = "date")
	private String date;
	
	@Column(name = "full_half")
	private String fullHalf;
	
	@Column(name = "remark")
	private String remark;
	
	@Column(name = "approved")
	private Boolean approved;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="company_id", referencedColumnName="Company_ID")
	private CompanyMaster company;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "department_id", referencedColumnName = "Department_ID")
	private DepartmentMaster department;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "employee_id", referencedColumnName = "Employee_ID")
	private Employee employee;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "leave_type_id", referencedColumnName = "leaveTypeID")
	private LeaveType leaveType;

	public ApplyLeave() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ApplyLeave(String leaveID, String date, String fullHalf, String remark, Boolean approved,
			CompanyMaster company, DepartmentMaster department, Employee employee, LeaveType leaveType) {
		super();
		this.leaveID = leaveID;
		this.date = date;
		this.fullHalf = fullHalf;
		this.remark = remark;
		this.approved = approved;
		this.company = company;
		this.department = department;
		this.employee = employee;
		this.leaveType = leaveType;
	}

	public String getLeaveID() {
		return leaveID;
	}

	public void setLeaveID(String leaveID) {
		this.leaveID = leaveID;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getFullHalf() {
		return fullHalf;
	}

	public void setFullHalf(String fullHalf) {
		this.fullHalf = fullHalf;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
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
	
	
}
