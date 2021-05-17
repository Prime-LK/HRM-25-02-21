package com.navitsa.hrm.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "shift_allocation")
public class ShiftAllocation {

	@EmbeddedId
	private ShiftAllocationPK shiftAllocationPK;

	@Column(name = "shift_name")
	private String shiftName;

	@Column(name = "start_time")
	private String startTime;

	@Column(name = "end_time")
	private String endTime;

	@Column(name = "employee_name")
	private String employeeName;

	@Column(name = "department_id")
	private String departmentId;

	@Column(name = "department_name")
	private String departmentName;

	@Column(name = "company_id")
	private String companyId;

	public ShiftAllocation() {

	}

	public ShiftAllocation(ShiftAllocationPK shiftAllocationPK, String shiftName, String startTime, String endTime,
			String employeeName, String departmentId, String departmentName, String companyId) {

		this.shiftAllocationPK = shiftAllocationPK;
		this.shiftName = shiftName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.employeeName = employeeName;
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.companyId = companyId;
	}

	public ShiftAllocation(Date date, String employeeId, String shiftId, String dayType, String shiftName,
			String startTime, String endTime, String employeeName, String departmentId, String departmentName,
			String companyId) {

		this.shiftAllocationPK = new ShiftAllocationPK(date, employeeId, shiftId);
		this.shiftName = shiftName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.employeeName = employeeName;
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.companyId = companyId;
	}

	public ShiftAllocationPK getShiftAllocationPK() {
		return shiftAllocationPK;
	}

	public void setShiftAllocationPK(ShiftAllocationPK shiftAllocationPK) {
		this.shiftAllocationPK = shiftAllocationPK;
	}

	public String getShiftName() {
		return shiftName;
	}

	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "ShiftAllocation [shiftAllocationPK=" + shiftAllocationPK + ", shiftName=" + shiftName + ", startTime="
				+ startTime + ", endTime=" + endTime + ", employeeName=" + employeeName + ", departmentId="
				+ departmentId + ", departmentName=" + departmentName + ", companyId=" + companyId + "]";
	}
}
