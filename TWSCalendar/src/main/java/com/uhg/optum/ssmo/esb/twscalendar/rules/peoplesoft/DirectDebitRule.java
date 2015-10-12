package com.uhg.optum.ssmo.esb.twscalendar.rules.peoplesoft;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.esb.twscalendar.domain.CalendarDay;
import com.uhg.optum.ssmo.esb.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.esb.twscalendar.rules.CalendarJobRule;
import com.uhg.optum.ssmo.esb.twscalendar.util.CalendarDayComparator;
import com.uhg.optum.ssmo.esb.twscalendar.util.CalendarUtils;
import com.uhg.optum.ssmo.esb.twscalendar.util.ProjectConstants;

public class DirectDebitRule extends CalendarJobRule {

	public DirectDebitRule(int year, Set<Holiday> holidayList) {
		super();
		this.year = year;
		this.holidays = holidayList;
	}

	@Override
	public List<LocalDate> getResults() {
		List<LocalDate> result = new ArrayList<LocalDate>();
		CalendarJobRule psfacr00rule = new PSFACR00Rule(year, holidays);
		CalendarJobRule psfacr06rule = new PSFACR06Rule(year, holidays);
		result.addAll(psfacr00rule.getResults());
		result.addAll(psfacr06rule.getResults());
		return result;
	}
	@Override
	public List<CalendarDay> getFinalDates(){
		List<CalendarDay> result = new ArrayList<CalendarDay>();
		CalendarJobRule psfacr00rule = new PSFACR00Rule(year, holidays);
		CalendarJobRule psfacr06rule = new PSFACR06Rule(year, holidays);
		for (LocalDate d : psfacr00rule.getResults()) {
			result.add(new CalendarDay(ProjectConstants.SPCL_A, d));
		}
		for (LocalDate d : psfacr06rule.getResults()) {
			result.add(new CalendarDay(ProjectConstants.SPCL_B, d));
		}
		
		CalendarUtils.addHolidaysToList(result, holidays);
		Collections.sort(result, new CalendarDayComparator());
		return result;	
	}
	

}
