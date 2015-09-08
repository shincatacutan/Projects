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

public class PSFACR06Rule extends CalendarJobRule {

	public PSFACR06Rule(int year, Set<Holiday> holidayList) {
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
	 * This is for scheduled direct debit jobs for Medica group. The jobs will
	 * run 4 business days prior to settlement date which is calendar day 1. If
	 * calendar day 1 is a holiday or weekend, the next business day is treated
	 * as calendar day 1. This is used if there are more than 2 days from the
	 * run date and the settlement date (i.e. weekends, holidays, etc).The jobs
	 * will not run on WD 1.
	 */
	
	@Override
	public List<LocalDate> getResults() {
		List<LocalDate> listDays = new ArrayList<LocalDate>();
		for (int i = 1; i <= 12; i++) {
			listDays.add(CalendarUtils.getNthBusDayBeforeSettleDay(4, 1, i,
					year, holidays));
		}
		return CalendarUtils.removeDuplicate(listDays);

	}

}
