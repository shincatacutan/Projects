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
		System.out.println(d);
		if (d.getDayOfWeek() == DateTimeConstants.SATURDAY
				|| d.getDayOfWeek() == DateTimeConstants.SUNDAY) {
			System.out.println(d.getDayOfWeek());
			d = d.plusDays(1);
			return dayAfterHoliday(d, holidays);
		}
		
		if(isHoliday(d, holidays)){
			d = d.plusDays(1);
			return dayAfterHoliday(d, holidays);
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
		System.out.println(result);
		return result;
	}

//	public static Boolean isHoliday(LocalDate calendar) {
//		Boolean holiday = Boolean.FALSE;
//		if (listHoliday().contains(calendar) && calendar != null) {
//			System.out.println(calendar + " is holiday");
//			holiday = Boolean.TRUE;
//		}
//		return holiday;
//	}

	public static Boolean isHoliday(LocalDate calendar, Set<Holiday> holidays) {
		for (Holiday h : holidays) {
			if (h.getDate().equals(calendar)) {
				System.out.println(calendar +" is a holiday");
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

	/*
	 * public List<LocalDate> sortCalendar(List<LocalDate> listCalendar){
	 * listCalendar.sort(new Comparator<LocalDate>(){
	 * 
	 * @Override public int compare(LocalDate o1, LocalDate o2) {
	 * 
	 * return o1.getDayOfMonth() > o2.getDayOfMonth() ? 1:-1; }
	 * 
	 * });
	 * 
	 * return listCalendar; }
	 */

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

	public static LocalDate list2WorkDayBefore10(LocalDate calendar,
			Set<Holiday> holidays) {

		if (calendar.getDayOfWeek() == DateTimeConstants.SUNDAY) {
			calendar = calendar.minusDays(3);
		} else if (calendar.getDayOfWeek() >= 3) {
			calendar = calendar.minusDays(2);
		} else {
			calendar = calendar.minusDays(4);
		}

		if (isHoliday(calendar, holidays)) {
			calendar = calendar.minusDays(1);
			return list2WorkDayBefore10(calendar, holidays);
		}

		return calendar;
	}

	public static List<LocalDate> getHolidays(Set<Holiday> holidays) {
		List<LocalDate> result = new ArrayList<LocalDate>();
		for (Holiday h : holidays) {
			result.add(h.getDate());
		}
		return result;
	}

	public static LocalDate listNthBusDayBeforeSettleDay(int nthday,
			int settlement, int month, int year, Set<Holiday> holidays) {
		System.out.println("settlement day: " + settlement);
		LocalDate d = new LocalDate(year, month, settlement);

		if (isWeekEnds(d)) {
			d = d.plusDays(1);
			return listNthBusDayBeforeSettleDay(nthday, d.getDayOfMonth(),
					month, year, holidays);
		}
		int dayCtr = 0;

		while (dayCtr < nthday) {

			System.out.println(d);
			if (isHoliday(d.minusDays(1), holidays)) {
				// d = d.minusDays(1);
				continue;
			}
			if (d.getDayOfWeek() == DateTimeConstants.SUNDAY) {
				d = d.minusDays(2);
				continue;
			}
			if (d.getDayOfWeek() == DateTimeConstants.SATURDAY) {
				d = d.minusDays(1);
				continue;
			}

			d = d.minusDays(1);
			dayCtr++;
		}

		return d;
	}

}
