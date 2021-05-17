package com.navitsa.hrm.report;

public class AttendanceSubReportBean {

	private String date;
	private String weekday;
	private String day_type;
	private String on_time;
	private String off_time;
	private String worked_time;
	private String over_time;
	private String short_time;
	private String attendance_description;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getWeekday() {
		return weekday;
	}

	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}

	public String getDay_type() {
		return day_type;
	}

	public void setDay_type(String day_type) {
		this.day_type = day_type;
	}

	public String getOn_time() {
		return on_time;
	}

	public void setOn_time(String on_time) {
		this.on_time = on_time;
	}

	public String getOff_time() {
		return off_time;
	}

	public void setOff_time(String off_time) {
		this.off_time = off_time;
	}

	public String getWorked_time() {
		return worked_time;
	}

	public void setWorked_time(String worked_time) {
		this.worked_time = worked_time;
	}

	public String getOver_time() {
		return over_time;
	}

	public void setOver_time(String over_time) {
		this.over_time = over_time;
	}

	public String getShort_time() {
		return short_time;
	}

	public void setShort_time(String short_time) {
		this.short_time = short_time;
	}

	public String getAttendance_description() {
		return attendance_description;
	}

	public void setAttendance_description(String attendance_description) {
		this.attendance_description = attendance_description;
	}
}
