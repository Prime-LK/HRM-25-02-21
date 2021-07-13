package com.navitsa.hrm.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "leaveOnLeave")
public class LeaveOnLeaveEntity {
	
	@EmbeddedId
	private LeaveOnLeavePK leaveOnLeavePK;
	
	@Column(name = "LeaveFrom")
	private String leaveFrom;
	
	@Column(name = "LeaveTo")
	private String leaveTo;
	
	@Column(name = "year")
	private String year;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "LeaveType", referencedColumnName = "leaveCode")
	private LeaveType leaveType;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "Name", referencedColumnName = "Employee_ID")
	private Employee name;

	@Column(name = "Day")
	private String day;
	
	@Column(name="Action")
	private String action;

	public LeaveOnLeavePK getLeaveOnLeavePK() {
		return leaveOnLeavePK;
	}

	public void setLeaveOnLeavePK(LeaveOnLeavePK leaveOnLeavePK) {
		this.leaveOnLeavePK = leaveOnLeavePK;
	}

	public String getLeaveFrom() {
		return leaveFrom;
	}

	public void setLeaveFrom(String leaveFrom) {
		this.leaveFrom = leaveFrom;
	}

	public String getLeaveTo() {
		return leaveTo;
	}

	public void setLeaveTo(String leaveTo) {
		this.leaveTo = leaveTo;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public LeaveType getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}

	public Employee getName() {
		return name;
	}

	public void setName(Employee name) {
		this.name = name;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	

	
}
