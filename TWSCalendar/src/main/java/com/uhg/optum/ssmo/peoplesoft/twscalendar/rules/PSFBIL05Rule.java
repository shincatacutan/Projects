package com.uhg.optum.ssmo.peoplesoft.twscalendar.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.CalendarJobRule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.util.CalendarUtils;

public class PSFBIL05Rule extends CalendarJobRule {

	public PSFBIL05Rule(int year, Set<Holiday> holidays) {
		this.year = year;
		this.holidays = holidays;
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
