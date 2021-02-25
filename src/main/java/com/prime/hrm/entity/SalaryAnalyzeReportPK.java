package com.prime.hrm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class SalaryAnalyzeReportPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name="Year")
	private String year;
	
	@Column(name="Month")
	private String month;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Department_ID", referencedColumnName="Department_ID")
	private DepartmentMaster depatment;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Pay_Add_Deduct_Type_Code", referencedColumnName ="Pay_Add_Deduct_Type_Code")
	private PayAddDeductTypes addDedType;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public DepartmentMaster getDepatment() {
		return depatment;
	}

	public void setDepatment(DepartmentMaster depatment) {
		this.depatment = depatment;
	}

	public PayAddDeductTypes getAddDedType() {
		return addDedType;
	}

	public void setAddDedType(PayAddDeductTypes addDedType) {
		this.addDedType = addDedType;
	}
	
	
}
