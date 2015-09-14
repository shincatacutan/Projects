package com.uhg.optum.ssmo.peoplesoft.twscalendar.vo;

public class HolidayVO {

	private String name;
	private String date;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public HolidayVO(String name, String date) {
		super();
		this.name = name;
		this.date = date;
	}
	
}
