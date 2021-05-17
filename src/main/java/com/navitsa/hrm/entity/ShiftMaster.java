package com.navitsa.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "shift_master")
public class ShiftMaster {

	@Id
	@Column(name = "shift_id")
	private String shiftId;

	@Column(name = "description")
	private String description;

	@Column(name = "start_time")
	private String startTime;

	@Column(name = "end_time")
	private String endTime;

	@Column(name = "recurring")
	private boolean recurring;

	@Column(name = "company_id")
	private String companyId;

	public ShiftMaster() {

	}

	public ShiftMaster(String shiftId, String description, String startTime, String endTime, boolean recurring,
			String companyId) {
		super();
		this.shiftId = shiftId;
		this.description = description;
		this.startTime = startTime;
		this.endTime = endTime;
		this.recurring = recurring;
		this.companyId = companyId;
	}

	public String getShiftId() {
		return shiftId;
	}

	public void setShiftId(String shiftId) {
		this.shiftId = shiftId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public boolean getRecurring() {
		return recurring;
	}

	public void setRecurring(boolean recurring) {
		this.recurring = recurring;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "ShiftMaster [shiftId=" + shiftId + ", description=" + description + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", recurring=" + recurring + ", companyId=" + companyId + "]";
	}

}
