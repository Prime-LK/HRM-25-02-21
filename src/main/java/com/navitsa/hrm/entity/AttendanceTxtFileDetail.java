package com.navitsa.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "attendance_log_detail")
public class AttendanceTxtFileDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "attendance_log_detail_id")
	private int detailId;
	
	@Column(name = "attendance_log_header_id")
	private String headerId;
	
	@Column(name = "employee_id")
	private String employeeId;
	
	@Column(name = "inout_date")
	private String inoutDate;
	
	@Column(name = "inout_time")
	private String inoutTime;

	public AttendanceTxtFileDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AttendanceTxtFileDetail(int detailId, String headerId, String employeeId, String inoutDate,
			String inoutTime) {
		super();
		this.detailId = detailId;
		this.headerId = headerId;
		this.employeeId = employeeId;
		this.inoutDate = inoutDate;
		this.inoutTime = inoutTime;
	}

	public AttendanceTxtFileDetail(String headerId, String inoutDate, String inoutTime) {
		this.headerId = headerId;
		this.inoutDate = inoutDate;
		this.inoutTime = inoutTime;
	}

	public int getDetailId() {
		return detailId;
	}

	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}

	public String getHeaderId() {
		return headerId;
	}

	public void setHeaderId(String headerId) {
		this.headerId = headerId;
	}

	public String getInoutDate() {
		return inoutDate;
	}

	public void setInoutDate(String inoutDate) {
		this.inoutDate = inoutDate;
	}

	public String getInoutTime() {
		return inoutTime;
	}

	public void setInoutTime(String inoutTime) {
		this.inoutTime = inoutTime;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}	

}
