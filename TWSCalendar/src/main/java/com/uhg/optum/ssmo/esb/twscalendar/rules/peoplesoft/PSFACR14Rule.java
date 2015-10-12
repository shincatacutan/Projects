package com.uhg.optum.ssmo.esb.twscalendar.rules.peoplesoft;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.esb.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.esb.twscalendar.rules.CalendarJobRule;
import com.uhg.optum.ssmo.esb.twscalendar.util.CalendarUtils;

public class PSFACR14Rule extends CalendarJobRule {

	public PSFACR14Rule(int year, Set<Holiday> holidayList) {
		this.year = year;
		this.holidays = holidayList;
	}

	/*
	 * PSF_ACR_CL_BI_PIA_UNRD_CAL_18 PSFACR14 Runs on calendar Day 18 - if the
	 * 18th falls on a Saturday, then it runs on the 17th. If the 18th falls on
	 * a Sunday, then it runs on the 19th.
	 */

	@Override
	public List<LocalDate> getResults() {
		List<LocalDate> listDays = new ArrayList<LocalDate>();
		for (int i = 1; i <= 12; i++) {
			listDays.add(getWorkday18(new LocalDate(year, i, 18)));
		}
		return listDays;
	}

	public LocalDate getWorkday18(LocalDate calendar) {
		if (calendar.getDayOfWeek() == DateTimeConstants.SATURDAY) {
			calendar = calendar.minusDays(1);
		} else if (calendar.getDayOfWeek() == DateTimeConstants.SUNDAY) {
			calendar = calendar.plusDays(1);
		}
		if (CalendarUtils.isHoliday(calendar, holidays)) {
			calendar = new LocalDate(new LocalDate(calendar.getYear(),
					calendar.getMonthOfYear(), calendar.getDayOfMonth() + 1));
			return getWorkday18(calendar);
		}
		return calendar;
	}

}
