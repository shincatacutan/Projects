package com.uhg.optum.ssmo.esb.twscalendar.rules.peoplesoft;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;
import org.joda.time.Period;

import com.uhg.optum.ssmo.esb.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.esb.twscalendar.rules.CalendarJobRule;
import com.uhg.optum.ssmo.esb.twscalendar.util.CalendarUtils;

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
			compareMinus2Days(listDays, i, year);
		}
		compareMinus2Days(listDays, 1, ++year);
		return CalendarUtils.removeDuplicate(listDays);
	}

	private void compareMinus2Days(List<LocalDate> listDays, int i, int y) {
		LocalDate firstCalDay = new LocalDate(y, i, 1);
		int settleday = 1;
		if (i == 6 && y == 2016) {
			firstCalDay = new LocalDate(y, i, 2);
			settleday = 2;
		}

		firstCalDay = CalendarUtils.dayAfterWeekend(firstCalDay, holidays);
		LocalDate minusTwoBusDays = CalendarUtils.getNthBusDayBeforeSettleDay(
				2, settleday, i, y, holidays);
		LocalDate actual = firstCalDay.minus(Period.days(2));
		if (actual.equals(minusTwoBusDays)) {
			listDays.add(actual);
		}
	}
}
