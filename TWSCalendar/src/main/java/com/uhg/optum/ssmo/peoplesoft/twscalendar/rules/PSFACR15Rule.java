package com.uhg.optum.ssmo.peoplesoft.twscalendar.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.CalendarJobRule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.util.CalendarUtils;

public class PSFACR15Rule extends CalendarJobRule {

	public PSFACR15Rule(int year, Set<Holiday> holidayList) {
		this.year = year;
		this.holidays = holidayList;
	}

	// Runs the day after workday 1

	@Override
	public List<LocalDate> getResults() {
		List<LocalDate> listDays = new ArrayList<LocalDate>();

		for (int i = 1; i <= 12; i++) {
			LocalDate d = CalendarUtils.getNthWorkDayOfMonth(1, i, year,
					holidays);
			d = d.plusDays(1);
			listDays.add(d);
		}

		return CalendarUtils.removeDuplicate(listDays);
	}
}
