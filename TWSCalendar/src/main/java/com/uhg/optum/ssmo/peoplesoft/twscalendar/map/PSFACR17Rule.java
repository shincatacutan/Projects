package com.uhg.optum.ssmo.peoplesoft.twscalendar.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.CalendarJobRule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.util.CalendarUtils;

public class PSFACR17Rule extends CalendarJobRule {

	public PSFACR17Rule(int year, Set<Holiday> holidayList) {
		super.year = year;
		super.holidays = holidayList;
	}

	/*
	 * Runs every weekdays except on holidays, workday 1, workday 5 and the days
	 * that PSF_ACR_CL_CAL_DAY18 runs
	 */
	@Override
	public List<LocalDate> getResults() {
		List<LocalDate> days = new ArrayList<LocalDate>();
		for (int i = 1; i <= 12; i++) {
			List<LocalDate> dates = getWeekdayExceptWD1WD5CD18(new LocalDate(year, i, 1));
			days.addAll(dates);
		}

		return days;
	}

	/*
	 * 18th calendar day of each month - unless the 18th falls on a weekend. If
	 * 18th falls on a weekend, schedule for the next Monday.
	 */
	private List<LocalDate> getCalendarDay18() {
		List<LocalDate> result = new ArrayList<LocalDate>();
		for (int i = 1; i <= 12; i++) {
			LocalDate date = new LocalDate(year, i, 18);
			result.add(CalendarUtils.dayAfterHoliday(date,holidays));
		}
		return result;
	}

	private List<LocalDate> getWeekdayExceptWD1WD5CD18(LocalDate calendar) {
		List<LocalDate> listDays = new ArrayList<LocalDate>();

		while (calendar.getDayOfMonth() <= calendar.dayOfMonth().getMaximumValue()) {

			if (!CalendarUtils.isWeekEnds(calendar) && !CalendarUtils.isHoliday(calendar, holidays)) {
				listDays.add(new LocalDate(calendar.getYear(), calendar.getMonthOfYear(), calendar.getDayOfMonth()));
			}
			if (calendar.getDayOfMonth() == calendar.dayOfMonth().getMaximumValue()) {
				break;
			}
			calendar = calendar.plusDays(1);

		}
		listDays.remove(CalendarUtils.getNthWorkDayOfMonth(1, calendar.getMonthOfYear(), year, holidays));
		listDays.remove(CalendarUtils.getNthWorkDayOfMonth(5, calendar.getMonthOfYear(), year, holidays));
		listDays.removeAll(getCalendarDay18());
		return listDays;
	}

}
