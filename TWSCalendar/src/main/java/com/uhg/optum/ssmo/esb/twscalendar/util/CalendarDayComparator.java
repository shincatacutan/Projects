package com.uhg.optum.ssmo.esb.twscalendar.util;

import java.util.Comparator;

import com.uhg.optum.ssmo.esb.twscalendar.domain.CalendarDay;

public class CalendarDayComparator implements Comparator<CalendarDay>{

	@Override
	public int compare(CalendarDay o1, CalendarDay o2) {
		 return o1.getCalDay().compareTo(o2.getCalDay());
	}
}
