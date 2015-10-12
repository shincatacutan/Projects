package com.uhg.optum.ssmo.esb.twscalendar.rules.datamart;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.esb.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.esb.twscalendar.rules.CalendarJobRule;
import com.uhg.optum.ssmo.esb.twscalendar.util.CalendarUtils;

public class CL_LSTWDRule extends CalendarJobRule {

	public CL_LSTWDRule(int year, Set<Holiday> holidays) {
		this.year = year;
		this.holidays = holidays;
	}

	@Override
	public List<LocalDate> getResults() {
		List<LocalDate> results = new ArrayList<LocalDate>();
		for (int i = 1; i <= 12; i++) {
			results.add(CalendarUtils.getLastWorkDay(new LocalDate(year, i, 1), holidays));
		}
		return results;
	}

}
