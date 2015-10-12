package com.uhg.optum.ssmo.esb.twscalendar.map;

import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.esb.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.esb.twscalendar.rules.CalendarJobRule;

public class F6473P4 extends CalendarJobRule {

	public F6473P4(int year, Set<Holiday> holidayList) {
		this.year = year;
		this.holidays = holidayList;
	}

	@Override
	public List<LocalDate> getResults() {
		// TODO Auto-generated method stub
		return null;
	}

}
