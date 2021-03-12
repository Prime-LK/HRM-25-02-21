package com.prime.hrm.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee_attendance")
public class EmployeeAttendance {

	@Id
	@Column(name = "attendance_id")
	private String attendanceId;

	@Column(name = "employee_id")
	private String employeeId;

	@Column(name = "shift_id")
	private String shiftId;

	@Column(name = "date")
	private Date date;

	@Column(name = "on_time")
	private String onTime;

	@Column(name = "off_time")
	private String offTime;

	@Column(name = "approved")
	private boolean approved;

	@Column(name = "company_id")
	private String companyId;

	public EmployeeAttendance() {

	}

	public EmployeeAttendance(String attendanceId, String employeeId, String shiftId, Date date, String onTime,
			String offTime, boolean approved, String companyId) {
		this.attendanceId = attendanceId;
		this.employeeId = employeeId;
		this.shiftId = shiftId;
		this.date = date;
		this.onTime = onTime;
		this.offTime = offTime;
		this.approved = approved;
		this.companyId = companyId;
	}

	public String getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(String attendanceId) {
		this.attendanceId = attendanceId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getShiftId() {
		return shiftId;
	}

	public void setShiftId(String shiftId) {
		this.shiftId = shiftId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "EmployeeAttendance [attendanceId=" + attendanceId + ", employeeId=" + employeeId + ", shiftId="
				+ shiftId + ", date=" + date + ", onTime=" + onTime + ", offTime=" + offTime + ", approved=" + approved
				+ ", companyId=" + companyId + "]";
	}
}
