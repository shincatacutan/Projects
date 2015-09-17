package com.uhg.optum.ssmo.peoplesoft.twscalendar.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.CalendarJobRule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.util.CalendarUtils;

public class PSFACR11Rule extends CalendarJobRule {

	public PSFACR11Rule(int year, Set<Holiday> holidayList) {
		this.year = year;
		this.holidays = holidayList;
	}

	/*
	 * PSF_ACR_CL_AUTO_MAINT_ZERO_BAL PSFACR11 Runs every Friday, LWD, 2 Days
	 * Working Day Before 10 except WD1
	 */
	@Override
	public List<LocalDate> getResults() {
		List<LocalDate> listDays = new ArrayList<LocalDate>();
		for (int i = 1; i <= 12; i++) {
			listDays.addAll(CalendarUtils.listAllFriday(new LocalDate(year, i,
					1)));
			listDays.add(CalendarUtils
					.getLastWorkDay(new LocalDate(year, i, 1)));
			listDays.add(CalendarUtils.getNthBusDayBeforeSettleDay(2, 10, i,
					year, holidays));
			listDays.remove(CalendarUtils.getNthWorkDayOfMonth(1, i, year,
					holidays));
		}

		CalendarUtils.removeHolidays(listDays, holidays);

		return CalendarUtils.removeDuplicate(listDays);
	}

}
