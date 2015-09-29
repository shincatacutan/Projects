package com.uhg.optum.ssmo.peoplesoft.twscalendar.map.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.CalendarJobRule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.util.CalendarUtils;

public class DMJan1SunRule extends CalendarJobRule {

	public DMJan1SunRule(int year, Set<Holiday> holidays) {
		this.year = year;
		this.holidays = holidays;
	}

	@Override
	public List<LocalDate> getResults() {
		List<LocalDate> results = new ArrayList<LocalDate>();

		results.add(CalendarUtils.getFirstWeekDay(DateTimeConstants.SUNDAY, 1,
				year));

		return results;
	}

}
