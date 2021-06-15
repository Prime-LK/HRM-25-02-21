package com.navitsa.hrm.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "shift_allocation")
public class ShiftAllocation {

	@EmbeddedId
	private ShiftAllocationPK shiftAllocationPK;

	public ShiftAllocation() {

	}

	public ShiftAllocation(ShiftAllocationPK shiftAllocationPK) {
		this.shiftAllocationPK = shiftAllocationPK;
	}

	public ShiftAllocationPK getShiftAllocationPK() {
		return shiftAllocationPK;
	}

	public void setShiftAllocationPK(ShiftAllocationPK shiftAllocationPK) {
		this.shiftAllocationPK = shiftAllocationPK;
	}

	@Override
	public String toString() {
		return "ShiftAllocation [shiftAllocationPK=" + shiftAllocationPK + "]";
	}
}
