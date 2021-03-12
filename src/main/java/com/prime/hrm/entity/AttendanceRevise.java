package com.prime.hrm.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "attendance_revise")
public class AttendanceRevise {

	@Id
	@Column(name = "revise_id")
	private String reviseId;

	@Column(name = "submit_date")
	private Date submitDate;

	@Column(name = "attendance_id")
	private String attendanceId;

	@Column(name = "shift_id")
	private String shiftId;

	@Column(name = "employee_id")
	private String employeeId;

	@Column(name = "date")
	private Date date;

	@Column(name = "on_time")
	private String onTime;

	@Column(name = "off_time")
	private String offTime;

	@Column(name = "new_on_time")
	private String newOnTime;

	@Column(name = "new_off_time")
	private String newOffTime;

	@Column(name = "remark")
	private String remark;

	@Column(name = "approved")
	private boolean approved;

	@Column(name = "company_id")
	private String companyId;

	public AttendanceRevise() {

	}

	public AttendanceRevise(String reviseId, Date submitDate, String shiftId, String employeeId, Date date,
			String onTime, String offTime, String newOnTime, String newOffTime, String remark, boolean approved,
			String companyId) {
		super();
		this.reviseId = reviseId;
		this.submitDate = submitDate;
		this.shiftId = shiftId;
		this.employeeId = employeeId;
		this.date = date;
		this.onTime = onTime;
		this.offTime = offTime;
		this.newOnTime = newOnTime;
		this.newOffTime = newOffTime;
		this.remark = remark;
		this.approved = approved;
		this.companyId = companyId;
	}

	public String getReviseId() {
		return reviseId;
	}

	public void setReviseId(String reviseId) {
		this.reviseId = reviseId;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public String getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(String attendanceId) {
		this.attendanceId = attendanceId;
	}

	public String getShiftId() {
		return shiftId;
	}

	public void setShiftId(String shiftId) {
		this.shiftId = shiftId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
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

	public String getNewOnTime() {
		return newOnTime;
	}

	public void setNewOnTime(String newOnTime) {
		this.newOnTime = newOnTime;
	}

	public String getNewOffTime() {
		return newOffTime;
	}

	public void setNewOffTime(String newOffTime) {
		this.newOffTime = newOffTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
		return "AttendanceRevise [reviseId=" + reviseId + ", submitDate=" + submitDate + ", attendanceId="
				+ attendanceId + ", shiftId=" + shiftId + ", employeeId=" + employeeId + ", date=" + date + ", onTime="
				+ onTime + ", offTime=" + offTime + ", newOnTime=" + newOnTime + ", newOffTime=" + newOffTime
				+ ", remark=" + remark + ", approved=" + approved + ", companyId=" + companyId + "]";
	}
}