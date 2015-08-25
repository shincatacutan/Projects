package com.uhg.optum.ssmo.peoplesoft.twscalendar.rules;

import java.util.List;

import org.joda.time.LocalDate;

public interface CalendarJobRule {
	public List<LocalDate> getDates();
}
