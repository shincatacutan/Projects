package com.uhg.optum.ssmo.peoplesoft.twscalendar.map;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.CalendarJobRule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.util.CalendarUtils;

public class PSFACR14Rule implements CalendarJobRule {

	private int year;
	private List<LocalDate> holidayList;
	
	public PSFACR14Rule(int year, List<LocalDate> holidayList) {
		this.year = year;
		this.holidayList = holidayList;
	}


	@Override
	public List<LocalDate> getDates() {
		List<LocalDate> result = new ArrayList<LocalDate>();
		for (int i = 1; i <= 12; i++) {
			result.add(getWorkday18(new LocalDate(year, i, 18)));
		}
		return result;
	}

	/*
	 * PSF_ACR_CL_BI_PIA_UNRD_CAL_18 PSFACR14 Runs on calendar Day 18 - if the
	 * 18th falls on a Saturday, then it runs on the 17th. If the 18th falls on
	 * a Sunday, then it runs on the 19th.
	 */
	public LocalDate getWorkday18(LocalDate calendar) {
		if (calendar.getDayOfWeek() == DateTimeConstants.SATURDAY) {
			calendar = calendar.minusDays(1);
		} else if (calendar.getDayOfWeek() == DateTimeConstants.SUNDAY) {
			calendar = calendar.plusDays(1);
		}
		if (CalendarUtils.isHoliday(calendar)) {
			calendar = new LocalDate(new LocalDate(calendar.getYear(),
					calendar.getMonthOfYear(), calendar.getDayOfMonth() + 1));
			return getWorkday18(calendar);
		}
		return calendar;
	}

}
