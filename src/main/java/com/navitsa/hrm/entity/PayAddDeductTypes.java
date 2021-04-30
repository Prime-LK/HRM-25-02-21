package com.navitsa.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="pay_add_deduct_types")
public class PayAddDeductTypes {
	
	@Id
	@Column(name="Pay_Add_Deduct_Type_Code")
	private String deductTypeCode;
	
	@Column(name="Description")
	private String desc;
	
	@Column(name="Add_Deduct_Status")
	private String addDeStatus;
	
	@Column(name="Is_Percentage")
	private String isPercentage;
	
	@Column(name="Add_Deduct_Value")
	private int addDeValue;
	
	@Column(name="Multipler_Value")
	private String multipiyValue;
	
	@Column(name="Is_On_Basic_Salary")
	private String onBaSalary;
	
	@Column(name="Is_On_Gross_Salary")
	private String onGrSalary;

	@Column(name="Add_Deduct_Period")
	private String addDePeriod;
	
	@Column(name="Add_Deduct_Type")
	private String addDeType;
	
	@Column(name="Calculation_Priority_Seq")
	private String calPriSeq;
	
	@Column(name="Ledger_Code")
	private String ledgerCode;
	
	@Column(name="Print_Priority_Seq")
	private String printPriSeq;
	
	@Column(name="Is_Active")
	private String isActive;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Company_ID", referencedColumnName="Company_ID")
	private CompanyMaster company;

	public String getDeductTypeCode() {
		return deductTypeCode;
	}

	public void setDeductTypeCode(String deductTypeCode) {
		this.deductTypeCode = deductTypeCode;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getAddDeStatus() {
		return addDeStatus;
	}

	public void setAddDeStatus(String addDeStatus) {
		this.addDeStatus = addDeStatus;
	}

	public String getIsPercentage() {
		return isPercentage;
	}

	public void setIsPercentage(String isPercentage) {
		this.isPercentage = isPercentage;
	}
	
	public int getAddDeValue() {
		return addDeValue;
	}

	public void setAddDeValue(int addDeValue) {
		this.addDeValue = addDeValue;
	}

	public String getMultipiyValue() {
		return multipiyValue;
	}

	public void setMultipiyValue(String multipiyValue) {
		this.multipiyValue = multipiyValue;
	}

	public String getOnBaSalary() {
		return onBaSalary;
	}

	public void setOnBaSalary(String onBaSalary) {
		this.onBaSalary = onBaSalary;
	}

	public String getOnGrSalary() {
		return onGrSalary;
	}

	public void setOnGrSalary(String onGrSalary) {
		this.onGrSalary = onGrSalary;
	}

	public String getAddDePeriod() {
		return addDePeriod;
	}

	public void setAddDePeriod(String addDePeriod) {
		this.addDePeriod = addDePeriod;
	}

	public String getAddDeType() {
		return addDeType;
	}

	public void setAddDeType(String addDeType) {
		this.addDeType = addDeType;
	}

	public String getCalPriSeq() {
		return calPriSeq;
	}

	public void setCalPriSeq(String calPriSeq) {
		this.calPriSeq = calPriSeq;
	}

	public String getLedgerCode() {
		return ledgerCode;
	}

	public void setLedgerCode(String ledgerCode) {
		this.ledgerCode = ledgerCode;
	}

	public String getPrintPriSeq() {
		return printPriSeq;
	}

	public void setPrintPriSeq(String printPriSeq) {
		this.printPriSeq = printPriSeq;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}

	public PayAddDeductTypes(String deductTypeCode, String desc, String addDeStatus, String isPercentage,
			int addDeValue, String multipiyValue, String onBaSalary, String onGrSalary, String addDePeriod,
			String addDeType, String calPriSeq, String ledgerCode, String printPriSeq, String isActive,
			CompanyMaster company) {
		this.deductTypeCode = deductTypeCode;
		this.desc = desc;
		this.addDeStatus = addDeStatus;
		this.isPercentage = isPercentage;
		this.addDeValue = addDeValue;
		this.multipiyValue = multipiyValue;
		this.onBaSalary = onBaSalary;
		this.onGrSalary = onGrSalary;
		this.addDePeriod = addDePeriod;
		this.addDeType = addDeType;
		this.calPriSeq = calPriSeq;
		this.ledgerCode = ledgerCode;
		this.printPriSeq = printPriSeq;
		this.isActive = isActive;
		this.company = company;
	}

	public PayAddDeductTypes() {
	}

	public PayAddDeductTypes(String deductTypeCode) {
		this.deductTypeCode = deductTypeCode;
	}
	
	
}
