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
@Table(name="asset_classes")

public class Assestclass {
	
	@Id
	@NotEmpty(message="Please enter class code")
	@Column(name="Class_Code")
	private String class_Code;
	
	@NotEmpty(message="Please enter description")
	@Column(name="Description")
	private String description;
	
	
	@Column(name="Status")
	private String status;
	

	@Column(name="GL_Account_Code")
	private String glaccountcode;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Depreciation_Type_Code", referencedColumnName ="Deprec_Group_Code")
	
	private Depreciationgroup  depreciationtypeCode;

	public String getClass_Code() 
	{
		return class_Code;
	}


	public void setClass_Code(String class_Code) 
	{
		this.class_Code = class_Code;
	}


	public String getDescription() 
	{
		return description;
	}


	public void setDescription(String description) 
	{
		this.description = description;
	}


	public String getStatus() 
	{
		return status;
	}


	public void setStatus(String status)
	{
		this.status = status;
	}


	public String getGlaccountcode() 
	{
		return glaccountcode;
	}


	public void setGlaccountcode(String glaccountcode) 
	{
		this.glaccountcode = glaccountcode;
	}






	public Depreciationgroup getDepreciationtypeCode()
	{
		return depreciationtypeCode;
	}


	public void setDepreciationtypeCode(Depreciationgroup depreciationtypeCode) 
	{
		this.depreciationtypeCode = depreciationtypeCode;
	}


	public Assestclass( ) 
	{
		
		
	}


	
}