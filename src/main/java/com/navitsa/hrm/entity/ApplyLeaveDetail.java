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
@Table(name = "apply_leave_detail")
public class ApplyLeaveDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "apply_leave_detail_id")
	private String applyLeaveDetailId;
	
	@Column(name = "date")
	private String date;
	
	@Column(name = "approved")
	private String approved;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="apply_leave_header_id", referencedColumnName="ID")
	private ApplyLeave_Entity applyLeaveHeader;

	public String getApplyLeaveDetailId() {
		return applyLeaveDetailId;
	}

	public void setApplyLeaveDetailId(String applyLeaveDetailId) {
		this.applyLeaveDetailId = applyLeaveDetailId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getApproved() {
		return approved;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	public ApplyLeave_Entity getApplyLeaveHeader() {
		return applyLeaveHeader;
	}

	public void setApplyLeaveHeader(ApplyLeave_Entity applyLeaveHeader) {
		this.applyLeaveHeader = applyLeaveHeader;
	}
	


}
