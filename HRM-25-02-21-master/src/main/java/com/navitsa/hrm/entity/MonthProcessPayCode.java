package com.navitsa.hrm.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="month_process_paycodes")
public class MonthProcessPayCode {

	@EmbeddedId
	private MonthProcessPayCodePK moProPCpk;
	
	@Column(name="Process_Date")
	private String processDate;
	
	@Column(name="User_ID")
	private String processUser;
	
	@Column(name="Employees")
	private String emps;
	
	@Column(name="Total_Basicsalary")
	private String totBasicSa;
	
	@Column(name="Total_Addition")
	private String totAdd;
	
	@Column(name="Total_Deduction")
	private String totDed;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Company_ID", referencedColumnName="Company_ID")
	private CompanyMaster company;

	public MonthProcessPayCodePK getMoProPCpk() {
		return moProPCpk;
	}

	public void setMoProPCpk(MonthProcessPayCodePK moProPCpk) {
		this.moProPCpk = moProPCpk;
	}

	public String getProcessDate() {
		return processDate;
	}

	public void setProcessDate(String processDate) {
		this.processDate = processDate;
	}

	public String getProcessUser() {
		return processUser;
	}

	public void setProcessUser(String processUser) {
		this.processUser = processUser;
	}

	public String getEmps() {
		return emps;
	}

	public void setEmps(String emps) {
		this.emps = emps;
	}

	public String getTotBasicSa() {
		return totBasicSa;
	}

	public void setTotBasicSa(String totBasicSa) {
		this.totBasicSa = totBasicSa;
	}

	public String getTotAdd() {
		return totAdd;
	}

	public void setTotAdd(String totAdd) {
		this.totAdd = totAdd;
	}

	public String getTotDed() {
		return totDed;
	}

	public void setTotDed(String totDed) {
		this.totDed = totDed;
	}

	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}
	
	public MonthProcessPayCode(MonthProcessPayCodePK moProPCpk, String processDate, String processUser, String emps,
			String totBasicSa, String totAdd, String totDed,CompanyMaster company) {
		this.moProPCpk = moProPCpk;
		this.processDate = processDate;
		this.processUser = processUser;
		this.emps = emps;
		this.totBasicSa = totBasicSa;
		this.totAdd = totAdd;
		this.totDed = totDed;
		this.company = company;
	}

	public MonthProcessPayCode() {
	}
	
	
}
