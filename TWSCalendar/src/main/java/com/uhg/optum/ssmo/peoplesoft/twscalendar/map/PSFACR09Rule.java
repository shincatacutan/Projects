package com.uhg.optum.ssmo.peoplesoft.twscalendar.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.CalendarDay;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.CalendarJobRule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.util.CalendarDayComparator;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.util.CalendarUtils;

public class PSFACR09Rule extends CalendarJobRule{

	public PSFACR09Rule(int year, Set<Holiday> holidayList) {
		super.year = year;
		super.holidays = holidayList;
	}
	
	@Override
	public List<CalendarDay> getFinalDates() {
		List<CalendarDay> result = new ArrayList<CalendarDay>();

		for (LocalDate d : getResults()) {
			result.add(new CalendarDay(Boolean.FALSE, d));
		}

		CalendarUtils.addHolidaysToList(result, holidays);
		Collections.sort(result, new CalendarDayComparator());
		return result;
	}

	@Override
	public List<LocalDate> getResults() {
		List<LocalDate> result = new ArrayList<LocalDate>();
		PSFACR00Rule psfacr00rule = new PSFACR00Rule(year, holidays);
		for(CalendarDay day : psfacr00rule.getFinalDates()){
			if(!day.isHoliday()){
					result.add(CalendarUtils.getNthBusDayBeforeSettleDay(2, day.getCalDay().getDayOfMonth(),day.getCalDay().getMonthOfYear(),day.getCalDay().getYear(), holidays));
					result.add(CalendarUtils.getNthBusDayBeforeSettleDay(4, day.getCalDay().getDayOfMonth(), day.getCalDay().getMonthOfYear(),day.getCalDay().getYear(), holidays));				
			}
		}
		
		return result;
		
	}

}
