package com.prime.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="employee_id_documents")
public class EmployeeIdDocument {
	
	@Id
	@Column(name="Emp_ID_DocType_ID")
	private String docTypeId;
	
	@NotEmpty(message = "required")
	@Column(name="Employee_ID_DocType")
	private String docType;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Company_ID", referencedColumnName="Company_ID")
	private CompanyMaster company;

	public String getDocTypeId() {
		return docTypeId;
	}

	public void setDocTypeId(String docTypeId) {
		this.docTypeId = docTypeId;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}
	
	public EmployeeIdDocument(String docTypeId, String docType,CompanyMaster company) {
		this.docTypeId = docTypeId;
		this.docType = docType;
		this.company = company;
	}

	public EmployeeIdDocument() {
	}
	
	

}
