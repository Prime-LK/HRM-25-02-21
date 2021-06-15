package com.navitsa.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	@Column(name = "continuing")
	private boolean continuing;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "company_id", referencedColumnName = "Company_ID")
	private CompanyMaster company;

	public ShiftMaster() {

	}

	public ShiftMaster(String shiftId, String description, String startTime, String endTime, boolean continuing,
			CompanyMaster company) {
		super();
		this.shiftId = shiftId;
		this.description = description;
		this.startTime = startTime;
		this.endTime = endTime;
		this.continuing = continuing;
		this.company = company;
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

	public boolean isContinuing() {
		return continuing;
	}

	public void setContinuing(boolean continuing) {
		this.continuing = continuing;
	}

	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "ShiftMaster [shiftId=" + shiftId + ", description=" + description + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", continuing=" + continuing + ", company=" + company + "]";
	}
}
