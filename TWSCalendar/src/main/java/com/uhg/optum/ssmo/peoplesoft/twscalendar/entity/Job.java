package com.uhg.optum.ssmo.peoplesoft.twscalendar.entity;

import java.util.List;

import org.joda.time.LocalDate;

public class Job {
	private String name;
	private List<LocalDate> rundates;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<LocalDate> getRundates() {
		return rundates;
	}
	public void setRundates(List<LocalDate> rundates) {
		this.rundates = rundates;
	}
	

}
