package com.uhg.optum.ssmo.peoplesoft.twscalendar.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.CalendarDay;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.CalendarJobRule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.util.CalendarDayComparator;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.util.CalendarUtils;

public class PSFBIL05Rule extends CalendarJobRule {

	public PSFBIL05Rule(int year, Set<Holiday> holidays) {
		this.year = year;
		this.holidays = holidays;
	}

	@Override
	public List<CalendarDay> getFinalDates() {
		List<CalendarDay> result = new ArrayList<CalendarDay>();

		for (LocalDate d : getResults()) {
			result.add(new CalendarDay(Boolean.FALSE, d));
		}

		CalendarUtils.addHolidaysToList(result, holidays);
		Collections.sort(result, new CalendarDayComparator());
		return result;
	}

	// This calendar runs during Workday 4

	@Override
	public List<LocalDate> getResults() {
		List<LocalDate> listDays = new ArrayList<LocalDate>();
		for (int i = 1; i <= 12; i++) {
			listDays.add(CalendarUtils.getNthWorkDayOfMonth(4, i, year,
					holidays));
		}
		return CalendarUtils.removeDuplicate(listDays);
	}

}
