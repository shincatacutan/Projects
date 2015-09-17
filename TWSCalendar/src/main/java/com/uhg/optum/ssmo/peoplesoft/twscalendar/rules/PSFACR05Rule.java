package com.uhg.optum.ssmo.peoplesoft.twscalendar.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.CalendarJobRule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.util.CalendarUtils;

public class PSFACR05Rule extends CalendarJobRule {

	public PSFACR05Rule(int year, Set<Holiday> holidayList) {
		this.year = year;
		this.holidays = holidayList;
	}

	/*
	 * Runs all weekdays other than: Holidays, workday 1 and the days that the
	 * following calendars run: PSFACR00, PSFACR01, PSFACR02 PSFACR04 ,
	 * PSFACR06, PSFACR03
	 */

	@Override
	public List<LocalDate> getResults() {
		List<LocalDate> listDays = new ArrayList<LocalDate>();
		for (int i = 1; i <= 12; i++) {
			listDays.addAll(CalendarUtils.getAllWorkDays(i, year, holidays));
			listDays.remove(CalendarUtils.getNthWorkDayOfMonth(1, i, year,
					holidays));
		}

		CalendarUtils.removeHolidays(listDays, holidays);

		listDays.removeAll(new PSFACR00Rule(year, holidays).getResults());
		listDays.removeAll(new PSFACR01Rule(year, holidays).getResults());
		listDays.removeAll(new PSFACR02Rule(year, holidays).getResults());
		listDays.removeAll(new PSFACR03Rule(year, holidays).getResults());
		listDays.removeAll(new PSFACR04Rule(year, holidays).getResults());
		listDays.removeAll(new PSFACR06Rule(year, holidays).getResults());

		return CalendarUtils.removeDuplicate(listDays);
	}

}
