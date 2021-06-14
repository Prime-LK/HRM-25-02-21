package com.navitsa.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "attendance_log_header")
public class AttendanceTxtFileHeader {
	
	@Id
	@Column(name = "attendance_log_header_id")
	private String headerId;
	
	@Column(name = "machine")
	private char machine;
	
	@Column(name = "txt_file_name")
	private String txtFileName;
	
	@Column(name = "total_records")
	private int totalRecords;
	
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "company_id")
	private String companyId;

	public AttendanceTxtFileHeader() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AttendanceTxtFileHeader(String headerId, char machine, String txtFileName, int totalRecords,
			String userId, String companyId) {
		super();
		this.headerId = headerId;
		this.machine = machine;
		this.txtFileName = txtFileName;
		this.totalRecords = totalRecords;
		this.userId = userId;
		this.companyId = companyId;
	}

	public String getHeaderId() {
		return headerId;
	}

	public void setHeaderId(String headerId) {
		this.headerId = headerId;
	}

	public char getMachine() {
		return machine;
	}

	public void setMachine(char machine) {
		this.machine = machine;
	}

	public String getTxtFileName() {
		return txtFileName;
	}

	public void setTxtFileName(String txtFileName) {
		this.txtFileName = txtFileName;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

}
