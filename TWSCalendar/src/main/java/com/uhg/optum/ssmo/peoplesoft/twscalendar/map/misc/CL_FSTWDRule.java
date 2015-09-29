package com.uhg.optum.ssmo.peoplesoft.twscalendar.map.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.CalendarJobRule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.util.CalendarUtils;

public class CL_FSTWDRule extends CalendarJobRule {

	public CL_FSTWDRule(int year, Set<Holiday> holidays) {
		this.year = year;
		this.holidays = holidays;
	}

	@Override
	public List<LocalDate> getResults() {
		List<LocalDate> results = new ArrayList<LocalDate>();
		for (int i = 1; i <= 12; i++) {
			results.add(CalendarUtils.getNthWorkDayOfMonth(1, i, year, holidays));
		}
		return results;
	}

}
