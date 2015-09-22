package com.uhg.optum.ssmo.peoplesoft.twscalendar.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.util.CalendarUtils;

public class PSFACR06Rule extends CalendarJobRule {

	public PSFACR06Rule(int year, Set<Holiday> holidayList) {
		this.year = year;
		this.holidays = holidayList;
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
			compareMinus4CalDays(listDays, i, year);
		}
		compareMinus4CalDays(listDays, 1, ++year);
		return CalendarUtils.removeDuplicate(listDays);

	}

	private void compareMinus4CalDays(List<LocalDate> listDays, int i, int y) {
		LocalDate d = new LocalDate(y, i, 1);
		d = CalendarUtils.dayAfterWeekend(d, holidays);
		int ctr = 0;
		boolean is2ndDayWeekday = false;
		while (ctr < 4) {

			d = d.minusDays(1);
			if (ctr == 1 && !CalendarUtils.isHoliday(d, holidays)
					&& !CalendarUtils.isWeekEnds(d)) {
				is2ndDayWeekday = true;
			}
			ctr++;
		}
		if (!is2ndDayWeekday) {
			listDays.add(d);
		}
	}

}
