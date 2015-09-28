package com.uhg.optum.ssmo.peoplesoft.twscalendar.rules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.CalendarDay;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.CalendarJobRule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.util.CalendarDayComparator;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.util.CalendarUtils;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.util.ProjectConstants;

public class PSFBIL02Rule extends CalendarJobRule {

	public PSFBIL02Rule(int year, Set<Holiday> holidayList) {
		this.year = year;
		this.holidays = holidayList;
	}

	@Override
	public List<CalendarDay> getFinalDates() {
		List<CalendarDay> result = new ArrayList<CalendarDay>();

		for (LocalDate d : getResults()) {
			if (CalendarUtils.isHoliday(d, holidays)) {
				result.add(new CalendarDay(ProjectConstants.SPCL_B, d));
			} else {
				result.add(new CalendarDay(Boolean.FALSE, d));
			}

		}

		// CalendarUtils.addHolidaysToList(result, holidays);
		for (Holiday h : holidays) {
			LocalDate d = h.getDate();
			if (!getResults().contains(d)) {
				result.add(new CalendarDay(Boolean.TRUE, d));
			}
		}
		Collections.sort(result, new CalendarDayComparator());
		return result;
	}

	/*
	 * Runs every workday plus 2nd and 3rd Saturdays of the month. However it
	 * does not on corporate holidays.
	 */
	@Override
	public List<LocalDate> getResults() {
		List<LocalDate> listDays = new ArrayList<LocalDate>();
		for (int i = 1; i <= 12; i++) {
			List<LocalDate> d = getTuesToSat(i);
			listDays.addAll(d);
		}
		return CalendarUtils.removeDuplicate(listDays);
	}

	private List<LocalDate> getTuesToSat(int month) {
		List<LocalDate> listDays = new ArrayList<LocalDate>();
		LocalDate firstDay = new LocalDate(year, month, 1);
		if (firstDay.getDayOfWeek() != DateTimeConstants.MONDAY
				&& firstDay.getDayOfWeek() != DateTimeConstants.SUNDAY) {
			listDays.add(firstDay);
		}
		for (LocalDate date : CalendarUtils.getAllWorkDaysWithoutHoliday(month,
				year)) {

			date = date.plusDays(1);

			listDays.add(date);
		}

		for (Holiday h : holidays) {
			LocalDate d = h.getDate();
			d = d.plusDays(1);
			listDays.remove(d);
		}

		return listDays;
	}

}
