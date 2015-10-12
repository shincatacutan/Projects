package com.uhg.optum.ssmo.esb.twscalendar.rules.datamart;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.esb.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.esb.twscalendar.rules.CalendarJobRule;
import com.uhg.optum.ssmo.esb.twscalendar.util.CalendarUtils;

public class ESBDMT01Rule extends CalendarJobRule {

	public ESBDMT01Rule(int year, Set<Holiday> holidays) {
		this.year = year;
		this.holidays = holidays;
	}

	/*
	 * The Saturday before the last Friday of each month
	 * */
	@Override
	public List<LocalDate> getResults() {
		List<LocalDate> results = new ArrayList<LocalDate>();
		for(int i = 1; i<=12; i++){
			results.add(getSaturdayBeforeLastFriday(i));
		}
		return results;
	}
	
	private LocalDate getSaturdayBeforeLastFriday(int m){
		LocalDate d = CalendarUtils.getLastWeekdayOfMonth(5, m, year);
		d = d.minusDays(6);
		return d;
	}

}
