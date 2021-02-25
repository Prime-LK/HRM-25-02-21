package com.prime.hrm.entity;

import java.io.IOException;
import java.util.Base64;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;


@Entity
@Table(name = "emp_id")
public class EmployeeID {

	
	@EmbeddedId
	EmployeeIDPK employeeIDPK;
	
	@Column(name = "Document")
	byte[] document ;
	
	@Column(name = "Description")
	String description ;

	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Company_ID", referencedColumnName="Company_ID")
	private CompanyMaster company;

	public EmployeeIDPK getEmployeeIDPK() {
		return employeeIDPK;
	}

	public void setEmployeeIDPK(EmployeeIDPK employeeIDPK) {
		this.employeeIDPK = employeeIDPK;
	}

	public byte[] getDocument() {
		return document;
	}

	public void setDocument(MultipartFile document) throws IOException {
		if(document.isEmpty()) {
			document=null;
		}else {
		this.document = document.getBytes();
		}
	}

	public String getdocumentView() {
		return Base64.getEncoder().encodeToString(this.document);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}
	
	public EmployeeID(EmployeeIDPK employeeIDPK, 
		MultipartFile document,String description
		,CompanyMaster company) throws IOException {
		this.employeeIDPK = employeeIDPK;
		this.document = document.getBytes();
		this.description=description;
		this.company = company;
	}

	public EmployeeID() {
		
	}
	
}
