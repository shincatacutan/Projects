package com.uhg.optum.ssmo.peoplesoft.twscalendar.rules;

import java.util.List;
import java.util.Set;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.CalendarDay;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.Holiday;

public abstract class CalendarJobRule {

	protected int year;
	protected Set<Holiday> holidays;
	
	public abstract List<CalendarDay> getDates();
}
