package com.navitsa.hrm.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ShiftAllocationPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date date;
	@Column(name = "employee_id")
	private String employeeId;
	@Column(name = "shift_id")
	private String shiftId;

	public ShiftAllocationPK() {

	}

	public ShiftAllocationPK(Date date, String employeeId, String shiftId) {
		this.date = date;
		this.employeeId = employeeId;
		this.shiftId = shiftId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	@Override
	public String toString() {
		return "ShiftAllocationPK [date=" + date + ", employeeId=" + employeeId + ", shiftId=" + shiftId + "]";
	}

}