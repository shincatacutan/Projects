package com.uhg.optum.ssmo.peoplesoft.twscalendar.map.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.CalendarJobRule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.util.CalendarUtils;

public class DM2nd3rdSatRule extends CalendarJobRule{

	public DM2nd3rdSatRule(int year, Set<Holiday> holidayList) {
		this.year = year;
		this.holidays = holidayList;
	}

	@Override
	public List<LocalDate> getResults() {
		List<LocalDate> listDays = new ArrayList<LocalDate>();
		for (int i = 1; i <= 12; i++) {
			LocalDate firstSat = CalendarUtils.getFirstWeekdayOfMonth(DateTimeConstants.SATURDAY,i, year);
			listDays.add(firstSat.plusWeeks(1));
			listDays.add(firstSat.plusWeeks(2));
		}
		return listDays;
	}

}
