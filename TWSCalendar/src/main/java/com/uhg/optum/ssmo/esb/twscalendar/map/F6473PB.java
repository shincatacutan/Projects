package com.uhg.optum.ssmo.esb.twscalendar.map;

import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.esb.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.esb.twscalendar.rules.CalendarJobRule;

public class F6473PB extends CalendarJobRule {

	public F6473PB(int year, Set<Holiday> holidayList) {
		this.year = year;
		this.holidays = holidayList;
	}

	@Override
	public List<LocalDate> getResults() {
		// TODO Auto-generated method stub
		return null;
	}

}
