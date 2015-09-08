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

public class PSFACR01Rule extends CalendarJobRule {

	public PSFACR01Rule(int year, Set<Holiday> holidayList) {
		super();
		this.year = year;
		this.holidays = holidayList;
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

	/*
	 * This is for scheduled direct debit jobs for the 10th of the month
	 * scheduled direct group. This jobs under this calendar runs 2 bus days
	 * prior to settlement date of calendar day 10. If calendar day 10 is a
	 * holiday or weekend, the next business day is treated as calendar day 10.
	 */

	@Override
	public List<LocalDate> getResults() {
		List<LocalDate> listDays = new ArrayList<LocalDate>();
		for (int i = 1; i <= 12; i++) {
			listDays.add(CalendarUtils.getNthBusDayBeforeSettleDay(2, 10, i,
					year, holidays));
		}
		return CalendarUtils.removeDuplicate(listDays);
	}
}
