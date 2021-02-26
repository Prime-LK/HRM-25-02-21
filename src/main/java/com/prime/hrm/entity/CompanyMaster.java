package com.prime.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="CompanyMaster")
@Table(name="company_master")
public class CompanyMaster {
	
	@Id
	@Column(name="Company_ID")
	private String comID;
	
	@Column(name="Company_Name")
	private String comName;
	
	@Column(name="Address")
	private String address;
	
	@Column(name="Contact_No")
	private String conNo;

	public String getComID() {
		return comID;
	}

	public void setComID(String comID) {
		this.comID = comID;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getConNo() {
		return conNo;
	}

	public void setConNo(String conNo) {
		this.conNo = conNo;
	}

	public CompanyMaster(String comID, String comName, String address, String conNo) {
		this.comID = comID;
		this.comName = comName;
		this.address = address;
		this.conNo = conNo;
	}

	public CompanyMaster() {
	}

	public CompanyMaster(String comID) {
		this.comID = comID;
	}

}
