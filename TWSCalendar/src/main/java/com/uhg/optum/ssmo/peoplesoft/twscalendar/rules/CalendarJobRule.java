package com.uhg.optum.ssmo.peoplesoft.twscalendar.rules;

import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.CalendarDay;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.Holiday;

public abstract class CalendarJobRule {

	protected int year;
	protected Set<Holiday> holidays;
	
	public abstract List<CalendarDay> getFinalDates();
	public abstract List<LocalDate> getResults();
}
