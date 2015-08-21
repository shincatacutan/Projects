package com.uhg.optum.ssmo.peoplesoft.twscalendar.util;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

public class CalendarUtils {
	public static void main(String[] args) {
		for(int i = 1; i<=12;i++){
			LocalDate d = getNthWorkDayOfMonth( 4, i, 2015);
			 System.out.println(d);
		}
		 
	}

	public static LocalDate getNthWorkDayOfMonth(int nthday, int month, int year) {
		LocalDate d = new LocalDate(year, month, 1);
		int dayCtr = 1;
		
		if (d.getMonthOfYear() != month)
			d = d.plusWeeks(1);

		while (dayCtr < nthday) {
			
			if (d.getDayOfWeek() == DateTimeConstants.SATURDAY
					|| d.getDayOfWeek() == DateTimeConstants.SUNDAY) {
				d = d.plusDays(1);
				continue;
			}
			d = d.plusDays(1);
			++dayCtr;
		}
		
		if(d.getDayOfWeek() == DateTimeConstants.SATURDAY){
			d = d.plusDays(2);
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
}
