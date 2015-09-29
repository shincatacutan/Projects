package com.uhg.optum.ssmo.peoplesoft.twscalendar.map.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.CalendarJobRule;

public class HolidayCalendar extends CalendarJobRule {

	public HolidayCalendar(int year, Set<Holiday> holidayList) {
		this.year = year;
		this.holidays = holidayList;
	}

	@Override
	public List<LocalDate> getResults() {
		return new ArrayList<LocalDate>();
	}

}
