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
@Table(name = "applyleaves")
public class ApplyLeave_Entity {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private String leaveID;
	
	@Column(name = "Days")
	private String days;
	
	@Column(name = "Type")
	private String types;
	
	@Column(name = "Description")
	private String desc;
	
	@Column(name = "Time")
	private String time;
		
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "LeaveType", referencedColumnName = "leaveCode")
	private leaveClass leaveType;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "Department", referencedColumnName = "Department_ID")
	private DepartmentMaster department;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "Date", referencedColumnName = "Date")
	private CalanderEntity date;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "Name", referencedColumnName = "Employee_ID")
	private Employee name;

	public String getLeaveID() {
		return leaveID;
	}

	public void setLeaveID(String leaveID) {
		this.leaveID = leaveID;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public leaveClass getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(leaveClass leaveType) {
		this.leaveType = leaveType;
	}

	public DepartmentMaster getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentMaster department) {
		this.department = department;
	}

	public CalanderEntity getDate() {
		return date;
	}

	public void setDate(CalanderEntity date) {
		this.date = date;
	}

	public Employee getName() {
		return name;
	}

	public void setName(Employee name) {
		this.name = name;
	}

	
	
}
