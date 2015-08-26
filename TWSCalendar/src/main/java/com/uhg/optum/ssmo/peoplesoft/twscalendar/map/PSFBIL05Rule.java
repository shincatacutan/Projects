package com.uhg.optum.ssmo.peoplesoft.twscalendar.map;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.CalendarDay;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.CalendarJobRule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.util.CalendarUtils;

public class PSFBIL05Rule implements CalendarJobRule {

	private int year;
	private List<Holiday> holidays;

	public PSFBIL05Rule(int year, List<Holiday> holidays) {
		this.year = year;
		this.holidays = holidays;
	}

	@Override
	public List<CalendarDay> getDates() {
		List<CalendarDay> result = new ArrayList<CalendarDay>();
		for (int i = 1; i <= 12; i++) {
			LocalDate d = CalendarUtils.getNthWorkDayOfMonth(4, i, year);
			result.add(new CalendarDay(false, d));
		}
		return result;
	}

}
