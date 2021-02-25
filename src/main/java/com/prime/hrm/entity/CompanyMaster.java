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

	public CompanyMaster(String comID, String comName) {
		this.comID = comID;
		this.comName = comName;
	}

	public CompanyMaster() {
	}

	public CompanyMaster(String comID) {
		this.comID = comID;
	}

}
