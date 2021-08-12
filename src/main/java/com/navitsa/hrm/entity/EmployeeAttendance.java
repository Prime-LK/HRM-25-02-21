package com.navitsa.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "employee_attendance")
public class EmployeeAttendance {

	@Id
	@Column(name = "attendance_id")
	private String attendanceId;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "employee_id", referencedColumnName = "Employee_ID")
	private Employee employee;

	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "shift_id", referencedColumnName = "shift_id")
	private ShiftMaster shiftmaster;

	@Column(name = "date")
	private String date;

	@Column(name = "on_time")
	private String onTime;

	@Column(name = "off_time")
	private String offTime;

	@Column(name = "approved")
	private boolean approved;
	
	@Column(name = "department_id")
	private String departmentId;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "company_id", referencedColumnName = "Company_ID")
	private CompanyMaster company;

	public EmployeeAttendance() {

	}

	public EmployeeAttendance(String attendanceId, Employee employee, ShiftMaster shiftmaster, String date,
			String onTime, String offTime, boolean approved, String departmentId, CompanyMaster company) {
		this.attendanceId = attendanceId;
		this.employee = employee;
		this.shiftmaster = shiftmaster;
		this.date = date;
		this.onTime = onTime;
		this.offTime = offTime;
		this.approved = approved;
		this.departmentId = departmentId;
		this.company = company;
	}

	public String getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(String attendanceId) {
		this.attendanceId = attendanceId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public ShiftMaster getShiftmaster() {
		return shiftmaster;
	}

	public void setShiftmaster(ShiftMaster shiftmaster) {
		this.shiftmaster = shiftmaster;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getOnTime() {
		return onTime;
	}

	public void setOnTime(String onTime) {
		this.onTime = onTime;
	}

	public String getOffTime() {
		return offTime;
	}

	public void setOffTime(String offTime) {
		this.offTime = offTime;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "EmployeeAttendance [attendanceId=" + attendanceId + ", employee=" + employee + ", shiftmaster="
				+ shiftmaster + ", date=" + date + ", onTime=" + onTime + ", offTime=" + offTime + ", approved="
				+ approved + ", departmentId=" + departmentId + ", company=" + company + "]";
	}
}