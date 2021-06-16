package com.navitsa.hrm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

@Embeddable
public class ShiftAllocationPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "date")
	private String date;
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "employee_id", referencedColumnName = "Employee_ID")
	private Employee employee;
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "shift_id", referencedColumnName = "shift_id")
	private ShiftMaster shiftmaster;

	public ShiftAllocationPK() {

	}

	public ShiftAllocationPK(String date, Employee employee, ShiftMaster shiftmaster) {
		this.date = date;
		this.employee = employee;
		this.shiftmaster = shiftmaster;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	@Override
	public String toString() {
		return "ShiftAllocationPK [date=" + date + ", employee=" + employee + ", shiftmaster=" + shiftmaster + "]";
	}
}