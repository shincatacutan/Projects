package com.uhg.optum.ssmo.esb.twscalendar.rules.duncan;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.esb.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.esb.twscalendar.rules.CalendarJobRule;
import com.uhg.optum.ssmo.esb.twscalendar.util.CalendarUtils;

public class F6473PC extends CalendarJobRule {

	public F6473PC(int year, Set<Holiday> holidayList) {
		this.year = year;
		this.holidays = holidayList;
	}

	/*
	 * All Weekdays except for Holidays.
	 */
	@Override
	public List<LocalDate> getResults() {
		List<LocalDate> results = new ArrayList<LocalDate>();

		for (int m = 1; m <= 12; m++) {
			results.addAll(CalendarUtils.getAllWorkDays(m, year, holidays));
		}

		CalendarUtils.removeHolidays(results, holidays);

		return CalendarUtils.removeDuplicate(results);
	}

}
