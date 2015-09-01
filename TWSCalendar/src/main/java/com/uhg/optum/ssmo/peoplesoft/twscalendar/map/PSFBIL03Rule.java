package com.uhg.optum.ssmo.peoplesoft.twscalendar.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.CalendarDay;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.CalendarJobRule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.util.CalendarUtils;

public class PSFBIL03Rule extends CalendarJobRule {

	public PSFBIL03Rule(int year, Set<Holiday> holidayList) {
		this.year = year;
		this.holidays = holidayList;
	}

	@Override
	public List<CalendarDay> getDates() {
		List<CalendarDay> result = new ArrayList<CalendarDay>();
		for (int i = 1; i <= 12; i++) {
			List<LocalDate> dates = getWeekdayExceptWD1WD4(new LocalDate(year,
					i, 1));
			for (LocalDate d : dates) {
				result.add(new CalendarDay(false, d));
			}
		}

		CalendarUtils.addHolidaysToList(result, holidays);
		return result;
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
		listDays.remove(CalendarUtils.getWorkDay1(calendar, holidays));
		listDays.remove(CalendarUtils.getNthWorkDayOfMonth(4, calendar.getMonthOfYear(), year, holidays));

		return listDays;
	}
}
