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
@Table(name = "ot_type")
public class OtType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ot_type_id")
	private String otTypeId;
	
	@Column(name = "day_type")
	private String dayType;
	
	@Column(name = "ot_type")
	private String desc;
	
	@Column(name = "condition")
	private String condition;
	
	@Column(name = "rate")
	private double rate;
	
	@Column(name = "total_work_hours")
	private double totalWorkHours;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Company_ID", referencedColumnName="Company_ID")
	private CompanyMaster company;

	public OtType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OtType(String otTypeId, String dayType, String desc, String condition, double rate, double totalWorkHours,
			CompanyMaster company) {
		super();
		this.otTypeId = otTypeId;
		this.dayType = dayType;
		this.desc = desc;
		this.condition = condition;
		this.rate = rate;
		this.totalWorkHours = totalWorkHours;
		this.company = company;
	}

	public String getOtTypeId() {
		return otTypeId;
	}

	public void setOtTypeId(String otTypeId) {
		this.otTypeId = otTypeId;
	}

	public String getDayType() {
		return dayType;
	}

	public void setDayType(String dayType) {
		this.dayType = dayType;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getTotalWorkHours() {
		return totalWorkHours;
	}

	public void setTotalWorkHours(double totalWorkHours) {
		this.totalWorkHours = totalWorkHours;
	}

	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}
	
	
	
}
