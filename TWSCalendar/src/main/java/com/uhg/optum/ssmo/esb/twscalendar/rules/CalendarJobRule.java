package com.uhg.optum.ssmo.esb.twscalendar.rules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.esb.twscalendar.domain.CalendarDay;
import com.uhg.optum.ssmo.esb.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.esb.twscalendar.util.CalendarDayComparator;
import com.uhg.optum.ssmo.esb.twscalendar.util.CalendarUtils;

public abstract class CalendarJobRule {

	protected int year;
	protected Set<Holiday> holidays;
	
	public List<CalendarDay> getFinalDates(){
		List<CalendarDay> result = new ArrayList<CalendarDay>();

		for (LocalDate d : getResults()) {
			result.add(new CalendarDay(Boolean.FALSE, d));
		}

		CalendarUtils.addHolidaysToList(result, holidays);
		Collections.sort(result, new CalendarDayComparator());
		return result;	
	}
	
	public abstract List<LocalDate> getResults();
}
