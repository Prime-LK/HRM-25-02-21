package com.navitsa.hrm.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "shift_allocation")
public class ShiftAllocation {

	@EmbeddedId
	private ShiftAllocationPK shiftAllocationPK;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "company_id", referencedColumnName = "Company_ID")
	private CompanyMaster company;

	public ShiftAllocation() {

	}

	public ShiftAllocation(ShiftAllocationPK shiftAllocationPK, CompanyMaster company) {
		this.shiftAllocationPK = shiftAllocationPK;
		this.company = company;
	}

	public ShiftAllocationPK getShiftAllocationPK() {
		return shiftAllocationPK;
	}

	public void setShiftAllocationPK(ShiftAllocationPK shiftAllocationPK) {
		this.shiftAllocationPK = shiftAllocationPK;
	}

	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "ShiftAllocation [shiftAllocationPK=" + shiftAllocationPK + ", company=" + company + "]";
	}
}
