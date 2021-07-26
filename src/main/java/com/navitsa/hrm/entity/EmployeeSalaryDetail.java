package com.navitsa.hrm.entity;



import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="employee_salary_details")
public class EmployeeSalaryDetail {
	
	@EmbeddedId
	private EmployeeSalaryDetailPK empdetailPK;
	
	@Column(name="Amount")
	private String amount;
	
	@Column(name="Is_Active")
	private String isActive;
	
	@Column(name="Added_Date")
	private String added_Date;
	
	@Column(name="Effective_Date")
	private String effective_Date;
	
	@Column(name="Added_User")
	private String added_User;
	
	@Column(name="Inactive_From")
	private String inactive_From;
	
	@Column(name="Inactive_User")
	private String inactive_User;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Company_ID", referencedColumnName="Company_ID")
	private CompanyMaster company;


	public EmployeeSalaryDetailPK getEmpdetailPK() {
		return empdetailPK;
	}

	public void setEmpdetailPK(EmployeeSalaryDetailPK empdetailPK) {
		this.empdetailPK = empdetailPK;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getAdded_Date() {
		return added_Date;
	}

	public void setAdded_Date(String added_Date) {
		this.added_Date = added_Date;
	}

	public String getEffective_Date() {
		return effective_Date;
	}

	public void setEffective_Date(String effective_Date) {
		this.effective_Date = effective_Date;
	}

	public String getAdded_User() {
		return added_User;
	}

	public void setAdded_User(String added_User) {
		this.added_User = added_User;
	}

	public String getInactive_From() {
		return inactive_From;
	}

	public void setInactive_From(String inactive_From) {
		this.inactive_From = inactive_From;
	}

	public String getInactive_User() {
		return inactive_User;
	}

	public void setInactive_User(String inactive_User) {
		this.inactive_User = inactive_User;
	}

	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}

	public EmployeeSalaryDetail() {

	}

	public EmployeeSalaryDetail( String amount, String isActive, String added_Date,
			String effective_Date, String added_User,EmployeeSalaryDetailPK empdetailPK,CompanyMaster company) {
		
		this.empdetailPK = empdetailPK;
		this.amount = amount;
		this.isActive = isActive;
		this.added_Date = added_Date;
		this.effective_Date = effective_Date;
		this.added_User = added_User;
		this.company = company;
	}

	public EmployeeSalaryDetail(EmployeeSalaryDetailPK empdetailPK, String amount, String isActive, String added_Date,
			String effective_Date, String added_User,CompanyMaster company) {
		
		this.empdetailPK = empdetailPK;
		this.amount = amount;
		this.isActive = isActive;
		this.added_Date = added_Date;
		this.effective_Date = effective_Date;
		this.added_User = added_User;
		this.company = company;
	}

	public EmployeeSalaryDetail(EmployeeSalaryDetailPK empdetailPK, String amount, String isActive, String added_Date,
			String effective_Date, String added_User, String inactive_From, String inactive_User,CompanyMaster company) {
		
		this.empdetailPK = empdetailPK;
		this.amount = amount;
		this.isActive = isActive;
		this.added_Date = added_Date;
		this.effective_Date = effective_Date;
		this.added_User = added_User;
		this.inactive_From = inactive_From;
		this.inactive_User = inactive_User;
		this.company = company;
	}
	
	
	
	
	


}
