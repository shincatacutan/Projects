package com.uhg.optum.ssmo.peoplesoft.twscalendar.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.CalendarJobRule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.util.CalendarUtils;

public class PSFBIL02Rule extends CalendarJobRule {

	public PSFBIL02Rule(int year, Set<Holiday> holidayList) {
		this.year = year;
		this.holidays = holidayList;
	}

	/*
	 * Runs every workday plus 2nd and 3rd Saturdays of the month. However it
	 * does not on corporate holidays.
	 */
	@Override
	public List<LocalDate> getResults() {
		List<LocalDate> listDays = new ArrayList<LocalDate>();
		for (int i = 1; i <= 12; i++) {
			List<LocalDate> d = getWeekdaySecondThirdSaturday(i);
			listDays.addAll(d);
		}
		return listDays;
	}

	private List<LocalDate> getWeekdaySecondThirdSaturday(int month) {
		List<LocalDate> listDays = new ArrayList<LocalDate>();
		for (LocalDate date : CalendarUtils.getAllWorkDaysWithoutHoliday(month,
				year)) {
			date = date.plusDays(1);
			listDays.add(date);
		}

		CalendarUtils.removeHolidays(listDays, holidays);

		return listDays;
	}

}
