package com.uhg.optum.ssmo.esb.twscalendar.rules.peoplesoft;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.esb.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.esb.twscalendar.rules.CalendarJobRule;
import com.uhg.optum.ssmo.esb.twscalendar.util.CalendarUtils;

public class PSFACR04Rule extends CalendarJobRule {

	public PSFACR04Rule(int year, Set<Holiday> holidayList) {
		this.year = year;
		this.holidays = holidayList;
	}

	/*
	 * This is for scheduled direct debit jobs for the 6th of the month
	 * scheduled direct debit group. This jobs under this calendar runs 2
	 * business days prior to settlement date of calendar day 6. If calendar day
	 * 6 is a holiday or weekend, the next business day is treated as calendar
	 * day 6.
	 */
	@Override
	public List<LocalDate> getResults() {
		List<LocalDate> listDays = new ArrayList<LocalDate>();
		for (int i = 1; i <= 12; i++) {
			listDays.add(CalendarUtils.getNthBusDayBeforeSettleDay(2, 6, i,
					year, holidays));
		}
		return CalendarUtils.removeDuplicate(listDays);
	}

}
