package com.navitsa.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "attendance_summary")
public class AttendanceSummary {

	@Id
	@Column(name = "attendance_summary_id")
	private String attendanceSummaryId;
	
	@Column(name = "total_ot_hours")
	private double totalOtHours;
	
	@Column(name = "total_late_minutes")
	private int totalLateMinutes;
	
	@Column(name = "total_leave")
	private int totalLeave;
	
	@Column(name = "no_pay_days")
	private int noPayDays;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "employee_id", referencedColumnName = "Employee_ID")
	private Employee employee;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "pay_period_id", referencedColumnName = "Pay_Period_ID")
	private PayPeriods payPeriod;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="company_id", referencedColumnName="Company_ID")
	private CompanyMaster company;

	public String getAttendanceSummaryId() {
		return attendanceSummaryId;
	}

	public void setAttendanceSummaryId(String attendanceSummaryId) {
		this.attendanceSummaryId = attendanceSummaryId;
	}

	public double getTotalOtHours() {
		return totalOtHours;
	}

	public void setTotalOtHours(double totalOtHours) {
		this.totalOtHours = totalOtHours;
	}

	public int getTotalLateMinutes() {
		return totalLateMinutes;
	}

	public void setTotalLateMinutes(int totalLateMinutes) {
		this.totalLateMinutes = totalLateMinutes;
	}

	public int getTotalLeave() {
		return totalLeave;
	}

	public void setTotalLeave(int totalLeave) {
		this.totalLeave = totalLeave;
	}

	public int getNoPayDays() {
		return noPayDays;
	}

	public void setNoPayDays(int noPayDays) {
		this.noPayDays = noPayDays;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public PayPeriods getPayPeriod() {
		return payPeriod;
	}

	public void setPayPeriod(PayPeriods payPeriod) {
		this.payPeriod = payPeriod;
	}

	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}

	public AttendanceSummary(String attendanceSummaryId, double totalOtHours, int totalLateMinutes, int totalLeave,
			int noPayDays, Employee employee, PayPeriods payPeriod, CompanyMaster company) {
		super();
		this.attendanceSummaryId = attendanceSummaryId;
		this.totalOtHours = totalOtHours;
		this.totalLateMinutes = totalLateMinutes;
		this.totalLeave = totalLeave;
		this.noPayDays = noPayDays;
		this.employee = employee;
		this.payPeriod = payPeriod;
		this.company = company;
	}

	public AttendanceSummary() {
		super();
	}
	
}
