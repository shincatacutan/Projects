package com.uhg.optum.ssmo.peoplesoft.twscalendar.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.CalendarJobRule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.util.CalendarUtils;

public class PSFBIL03Rule extends CalendarJobRule {

	public PSFBIL03Rule(int year, Set<Holiday> holidayList) {
		this.year = year;
		this.holidays = holidayList;
	}


	/*
	 * Runs every work day except workday 1 and workday 4 . It does not run on
	 * holidays.
	 */
	@Override
	public List<LocalDate> getResults() {
		List<LocalDate> listDays = new ArrayList<LocalDate>();
		for (int i = 1; i <= 12; i++) {
			List<LocalDate> dates = getWeekdayExceptWD1WD4(new LocalDate(year,
					i, 1));
			listDays.addAll(dates);
		}

		return listDays;
	}

	public List<LocalDate> getWeekdayExceptWD1WD4(LocalDate calendar) {
		List<LocalDate> listDays = new ArrayList<LocalDate>();

		while (calendar.getDayOfMonth() <= calendar.dayOfMonth()
				.getMaximumValue()) {

			if (!CalendarUtils.isWeekEnds(calendar)
					&& !CalendarUtils.isHoliday(calendar, holidays)) {
				listDays.add(new LocalDate(calendar.getYear(), calendar
						.getMonthOfYear(), calendar.getDayOfMonth()));
			}
			if (calendar.getDayOfMonth() == calendar.dayOfMonth()
					.getMaximumValue()) {
				break;
			}
			calendar = calendar.plusDays(1);

		}
		listDays.remove(CalendarUtils.getNthWorkDayOfMonth(1,
				calendar.getMonthOfYear(), year, holidays));
		listDays.remove(CalendarUtils.getNthWorkDayOfMonth(4,
				calendar.getMonthOfYear(), year, holidays));

		return listDays;
	}

}
