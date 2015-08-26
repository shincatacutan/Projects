package com.uhg.optum.ssmo.peoplesoft.twscalendar.domain;

import org.joda.time.LocalDate;

public class CalendarDay {

	private boolean isHoliday;
	private LocalDate calDay;

	public CalendarDay(boolean isHoliday, LocalDate calDay) {
		super();
		this.isHoliday = isHoliday;
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

}
