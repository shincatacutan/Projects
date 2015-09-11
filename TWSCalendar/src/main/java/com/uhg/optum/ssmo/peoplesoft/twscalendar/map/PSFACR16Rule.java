package com.uhg.optum.ssmo.peoplesoft.twscalendar.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.CalendarJobRule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.util.CalendarUtils;

public class PSFACR16Rule extends CalendarJobRule {

	public PSFACR16Rule(int year, Set<Holiday> holidayList) {
		this.year = year;
		this.holidays = holidayList;
	}

	/*
	 * Runs every weekdays except holidays, workday 1, last work day and the
	 * days that PSFACR11 runs
	 */
	
	@Override
	public List<LocalDate> getResults() {
		List<LocalDate> listDays = new ArrayList<LocalDate>();
		for (int i = 1; i <= 12; i++) {
			List<LocalDate> dates = getWeekdayExceptWD1LWDFriday(new LocalDate(
					year, i, 1));
			listDays.addAll(dates);
		}
		return listDays;
	}
	
	private List<LocalDate> getWeekdayExceptWD1LWDFriday(LocalDate calendar) {
		List<LocalDate> listDays = new ArrayList<LocalDate>();
		listDays = CalendarUtils.getAllWorkDays(calendar.getMonthOfYear(),
				year, holidays);
		listDays.removeAll(new PSFACR11Rule(year, holidays).getResults());
		listDays.remove(CalendarUtils.getNthWorkDayOfMonth(1,
				calendar.getMonthOfYear(), year, holidays));

		CalendarUtils.removeHolidays(listDays, holidays);
		return listDays;
	}

	
}
