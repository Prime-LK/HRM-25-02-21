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
@Table(name = "attendance_summary_detail")
public class AttendanceSummaryDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "attendance_summary_detail_id")
	private int attendanceSummaryDetailId;
	
	@Column(name = "day_type")
	private String dayType;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "ot_type_id", referencedColumnName = "ot_type_id")
	private OtType otType;
	
	@Column(name = "ot_hours")
	private double otHours;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "attendance_summary_id", referencedColumnName = "attendance_summary_id")
	private AttendanceSummary attendanceSummaryHeaderId;

	public int getAttendanceSummaryDetailId() {
		return attendanceSummaryDetailId;
	}

	public void setAttendanceSummaryDetailId(int attendanceSummaryDetailId) {
		this.attendanceSummaryDetailId = attendanceSummaryDetailId;
	}

	public String getDayType() {
		return dayType;
	}

	public void setDayType(String dayType) {
		this.dayType = dayType;
	}

	public OtType getOtType() {
		return otType;
	}

	public void setOtType(OtType otType) {
		this.otType = otType;
	}

	public double getOtHours() {
		return otHours;
	}

	public void setOtHours(double otHours) {
		this.otHours = otHours;
	}

	public AttendanceSummary getAttendanceSummaryHeaderId() {
		return attendanceSummaryHeaderId;
	}

	public void setAttendanceSummaryHeaderId(AttendanceSummary attendanceSummaryHeaderId) {
		this.attendanceSummaryHeaderId = attendanceSummaryHeaderId;
	}

	public AttendanceSummaryDetail(int attendanceSummaryDetailId, String dayType, OtType otType, double otHours,
			AttendanceSummary attendanceSummaryHeaderId) {
		super();
		this.attendanceSummaryDetailId = attendanceSummaryDetailId;
		this.dayType = dayType;
		this.otType = otType;
		this.otHours = otHours;
		this.attendanceSummaryHeaderId = attendanceSummaryHeaderId;
	}

	public AttendanceSummaryDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	
}
