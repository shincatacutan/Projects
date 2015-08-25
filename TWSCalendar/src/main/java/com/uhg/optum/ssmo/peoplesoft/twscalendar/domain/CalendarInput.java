package com.uhg.optum.ssmo.peoplesoft.twscalendar.domain;

public class CalendarInput {

	private String jobCode;
	private String holidayList;
	private String year;
	private String filetype;

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getHolidayList() {
		return holidayList;
	}

	public void setHolidayList(String holidayList) {
		this.holidayList = holidayList;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

}
