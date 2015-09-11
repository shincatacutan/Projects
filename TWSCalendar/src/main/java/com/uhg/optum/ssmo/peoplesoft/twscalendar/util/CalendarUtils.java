package com.uhg.optum.ssmo.peoplesoft.twscalendar.util;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.CalendarDay;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.Holiday;

public class CalendarUtils {

	public static LocalDate getNthWorkDayOfMonth(int nthday, int month,
			int year, Set<Holiday> holidays) {

		LocalDate d = new LocalDate(year, month, 1);
		int dayCtr = 1;

		if (d.getMonthOfYear() != month)
			d = d.plusWeeks(1);

		// for first day holiday
		if (dayCtr == nthday && (isHoliday(d, holidays) || isWeekEnds(d))) {
			d = dayAfterHoliday(d, holidays);
		}

		while (dayCtr < nthday) {
			if (isHoliday(d, holidays)) {
				d = d.plusDays(1);
				continue;
			}
			if (d.getDayOfWeek() == DateTimeConstants.SATURDAY
					|| d.getDayOfWeek() == DateTimeConstants.SUNDAY) {
				d = d.plusDays(1);
				continue;
			}

			d = d.plusDays(1);
			++dayCtr;
		}

		if (d.getDayOfWeek() == DateTimeConstants.SATURDAY) {
			d = d.plusDays(2);
		}
		return d;
	}

	public static LocalDate dayAfterHoliday(LocalDate day, Set<Holiday> holidays) {
		LocalDate d = day;
		if (d.getDayOfWeek() == DateTimeConstants.SATURDAY
				|| d.getDayOfWeek() == DateTimeConstants.SUNDAY) {
			d = d.plusDays(1);
			return dayAfterHoliday(d, holidays);
		}

		if (isHoliday(d, holidays)) {
			d = d.plusDays(1);
			return dayAfterHoliday(d, holidays);
		}

		return d;
	}

	public static LocalDate dayBeforeHoliday(LocalDate day,
			Set<Holiday> holidays) {
		LocalDate d = day;

		if (d.getDayOfWeek() == DateTimeConstants.SATURDAY
				|| d.getDayOfWeek() == DateTimeConstants.SUNDAY) {
			d = d.minusDays(1);
			return dayBeforeHoliday(d, holidays);
		}

		if (isHoliday(d, holidays)) {
			d = d.minusDays(1);
			return dayBeforeHoliday(d, holidays);
		}

		return d;
	}

	public static LocalDate getLastWeekdayOfMonth(int dayweek, int month,
			int year) {
		LocalDate d = new LocalDate(year, month, 1).plusMonths(1)
				.withDayOfWeek(dayweek);
		if (d.getMonthOfYear() != month)
			d = d.minusWeeks(1);
		return d;
	}

	public static LocalDate getLastDayOfMonth(LocalDate calendar) {

		return new LocalDate(calendar.getYear(), calendar.getMonthOfYear(),
				calendar.dayOfMonth().getMaximumValue());
	}

	public static Boolean isWeekEnds(LocalDate calendar) {
		Boolean result = Boolean.FALSE;
		if (calendar.getDayOfWeek() == DateTimeConstants.SUNDAY
				|| calendar.getDayOfWeek() == DateTimeConstants.SATURDAY) {
			result = Boolean.TRUE;
		}
		return result;
	}

	public static LocalDate getFirstSunday(LocalDate calendar) {
		int diff = 0;
		if (!(calendar.getDayOfWeek() == 1)) {
			diff = 8 - calendar.getDayOfWeek();
			calendar = calendar.plusDays(diff);
		}
		return calendar;
	}

	public static String toString(LocalDate calendar) {
		String result = calendar.getMonthOfYear() + 1 + "/"
				+ calendar.getDayOfMonth() + "/" + calendar.getYear();
		return result;
	}

	public static Boolean isHoliday(LocalDate calendar, Set<Holiday> holidays) {
		for (Holiday h : holidays) {
			if (h.getDate().equals(calendar)) {
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}

	public static LocalDate getLastWorkDay(LocalDate calendar) {

		calendar = getLastDayOfMonth(calendar);
		for (int i = calendar.getDayOfMonth(); i > 1; calendar = calendar
				.minusDays(1)) {
			if (!isWeekEnds(calendar)) {
				break;
			}
		}

		return calendar;
	}

	public static LocalDate getWorkDay1(LocalDate calendar,
			Set<Holiday> holidays) {

		if (!isWeekEnds(calendar)) {

		} else if (calendar.getDayOfWeek() == DateTimeConstants.SATURDAY) {
			calendar = calendar.plusDays(2);
		} else {
			calendar = calendar.plusDays(1);
		}
		if (isHoliday(calendar, holidays)) {
			calendar = new LocalDate(calendar.getYear(),
					calendar.getMonthOfYear(), (calendar.getDayOfMonth() + 1));
			return getWorkDay1(calendar, holidays);
		}
		return calendar;
	}

	public static List<LocalDate> listAllFriday(LocalDate calendar) {
		List<LocalDate> fridays = new ArrayList<LocalDate>();
		while (calendar.getDayOfMonth() < calendar.dayOfMonth()
				.getMaximumValue()) {
			if (calendar.getDayOfWeek() == DateTimeConstants.FRIDAY) {
				fridays.add(new LocalDate(calendar.getYear(), calendar
						.getMonthOfYear(), (calendar.getDayOfMonth())));

			}
			calendar = calendar.plusDays(1);
		}

		return fridays;
	}

	public static List<LocalDate> removeDuplicate(
			List<LocalDate> duplicateCalendar) {
		List<LocalDate> removedDuplicate = new ArrayList<>(new LinkedHashSet<>(
				duplicateCalendar));

		return removedDuplicate;
	}

	public static void removeHolidays(List<LocalDate> listDays,
			Set<Holiday> holidays) {
		for (Holiday h : holidays) {
			listDays.remove(h.getDate());
		}
	}

	public static void addHolidaysToList(List<CalendarDay> result,
			Set<Holiday> holidays) {
		for (Holiday h : holidays) {
			result.add(new CalendarDay(Boolean.TRUE, h.getDate()));
		}
	}

	public static List<LocalDate> getHolidays(Set<Holiday> holidays) {
		List<LocalDate> result = new ArrayList<LocalDate>();
		for (Holiday h : holidays) {
			result.add(h.getDate());
		}
		return result;
	}

	public static LocalDate getNthBusDayBeforeSettleDay(int nthday,
			int settlement, int month, int year, Set<Holiday> holidays) {

		LocalDate d = new LocalDate(year, month, settlement);

		int dayCtr = settlement;

		if (isHoliday(d, holidays) || isWeekEnds(d)) {
			d = CalendarUtils.dayAfterHoliday(d, holidays);
		}

		while (dayCtr < settlement + nthday) {

			if (isHoliday(d, holidays)) {
				d = d.minusDays(1);
				continue;
			}
			if (d.getDayOfWeek() == DateTimeConstants.SATURDAY
					|| d.getDayOfWeek() == DateTimeConstants.SUNDAY) {
				d = d.minusDays(1);
				continue;
			}
			d = d.minusDays(1);
			if (isHoliday(d, holidays) || isWeekEnds(d)) {
				d = CalendarUtils.dayBeforeHoliday(d, holidays);
			}
			++dayCtr;
		}

		return d;
	}

	public static List<LocalDate> getAllWorkDays(int month, int year,
			Set<Holiday> holidays) {
		List<LocalDate> result = new ArrayList<LocalDate>();
		LocalDate d = new LocalDate(year, month, 1);
		int dayCtr = 1;
		int monthMax = d.dayOfMonth().getMaximumValue();
		while(dayCtr <= monthMax) {
			if (isHoliday(d, holidays) || isWeekEnds(d)) {
				d = d.plusDays(1);
				dayCtr++;
				continue;
			}
			
			result.add(d);
			d = d.plusDays(1);
			dayCtr++;
		}

		return result;
	}

}
