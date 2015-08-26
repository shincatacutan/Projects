package com.uhg.optum.ssmo.peoplesoft.twscalendar.rules;

import java.util.List;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.CalendarDay;

public interface CalendarJobRule {
	public List<CalendarDay> getDates();
}
