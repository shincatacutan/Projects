package com.uhg.optum.ssmo.peoplesoft.twscalendar.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.CalendarJobRule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.util.CalendarUtils;

public class PSFACR00Rule extends CalendarJobRule {

	public PSFACR00Rule(int year, Set<Holiday> holidayList) {
		super();
		this.year = year;
		this.holidays = holidayList;
	}

	/*
	 * This is for scheduled direct debit jobs for Medica group. The jobs will
	 * run 2 business days prior to settlement date of calendar day 1. If
	 * calendar day 1 is a holiday or weekend, the next business day is treated
	 * as calendar day 1. The jobs will not run on WD 1.
	 */

	@Override
	public List<LocalDate> getResults() {
		List<LocalDate> listDays = new ArrayList<LocalDate>();
		for (int i = 1; i <= 12; i++) {
			listDays.add(CalendarUtils.getNthBusDayBeforeSettleDay(2, 1, i,
					year, holidays));
		}
		return CalendarUtils.removeDuplicate(listDays);
	}

}
