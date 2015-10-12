package com.uhg.optum.ssmo.esb.twscalendar.domain;

import org.joda.time.LocalDate;

public class CalendarDay {

	private boolean isHoliday;
	private LocalDate calDay;
	private String specialJob;

	public CalendarDay(boolean isHoliday, LocalDate calDay) {
		super();
		this.isHoliday = isHoliday;
		this.calDay = calDay;
	}
	
	public CalendarDay(String specialJob, LocalDate calDay){
		super();
		this.specialJob = specialJob;
		this.calDay = calDay;
	}

	public boolean isHoliday() {
		return isHoliday;
	}

	public void setHoliday(boolean isHoliday) {
		this.isHoliday = isHoliday;
	}

	public LocalDate getCalDay() {
		return calDay;
	}

	public void setCalDay(LocalDate calDay) {
		this.calDay = calDay;
	}

	public String getSpecialJob() {
		return specialJob;
	}

	public void setSpecialJob(String specialJob) {
		this.specialJob = specialJob;
	}

}
