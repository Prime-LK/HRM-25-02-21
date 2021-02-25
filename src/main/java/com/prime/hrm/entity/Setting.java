package com.prime.hrm.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="setting")
public class Setting {
	
	@Id
	@Column(name="company_id")
	private String companyid;
	
	@Column(name="multi_pay_period")
	private String multipayperiod;
	
	@Column(name="multi_pay_code")
	private String multipaycode;

	public String getCompanyid() {
		return companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}

	public String getMultipayperiod() {
		return multipayperiod;
	}

	public void setMultipayperiod(String multipayperiod) {
		this.multipayperiod = multipayperiod;
	}

	public String getMultipaycode() {
		return multipaycode;
	}

	public void setMultipaycode(String multipaycode) {
		this.multipaycode = multipaycode;
	}

	public Setting() {
		
	}	

}
