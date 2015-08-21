package com.uhg.optum.ssmo.peoplesoft.twscalendar.entity;

import org.joda.time.LocalDate;

public class Holiday {
	private String name;
	private LocalDate date;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
}
