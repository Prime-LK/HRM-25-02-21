package com.prime.hrm.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class MonthProcessPayCodePK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Pay_Period_ID", referencedColumnName ="Pay_Period_ID")
	private PayPeriods payPeriod;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Pay_Code_ID", referencedColumnName ="Pay_Code_ID")
	private PayCode payCode;

	public PayPeriods getPayPeriod() {
		return payPeriod;
	}

	public void setPayPeriod(PayPeriods payPeriod) {
		this.payPeriod = payPeriod;
	}

	public PayCode getPayCode() {
		return payCode;
	}

	public void setPayCode(PayCode payCode) {
		this.payCode = payCode;
	}
	
	

}
