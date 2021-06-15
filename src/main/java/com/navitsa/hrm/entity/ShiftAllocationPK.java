package com.navitsa.hrm.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

@Embeddable
public class ShiftAllocationPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "date", referencedColumnName = "date"),
			@JoinColumn(name = "company_id", referencedColumnName = "company_id") })
	private CalanderEntity calander;
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "employee_id", referencedColumnName = "Employee_ID")
	private Employee employee;
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "shift_id", referencedColumnName = "shift_id")
	private ShiftMaster shiftmaster;

	public ShiftAllocationPK() {

	}

	public ShiftAllocationPK(CalanderEntity calander, Employee employee, ShiftMaster shiftmaster) {
		this.calander = calander;
		this.employee = employee;
		this.shiftmaster = shiftmaster;
	}

	public CalanderEntity getCalander() {
		return calander;
	}

	public void setCalander(CalanderEntity calander) {
		this.calander = calander;
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
		return "ShiftAllocationPK [calander=" + calander + ", employee=" + employee + ", shiftmaster=" + shiftmaster
				+ "]";
	}
}