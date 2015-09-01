package com.uhg.optum.ssmo.peoplesoft.twscalendar.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.CalendarDay;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.CalendarJobRule;

public class PSFACR04Rule extends CalendarJobRule {

	public PSFACR04Rule(int year, Set<Holiday> holidayList) {
		this.year = year;
		this.holidays = holidayList;
	}
	
	@Override
	public List<CalendarDay> getDates() {
		List<CalendarDay> result = new ArrayList<CalendarDay>();
		for (int i = 1; i <= 12; i++) {
			result.add(new CalendarDay(Boolean.FALSE, listPSFACR04(new LocalDate(year, i, 6))));
		}
		return result;
	}

	public LocalDate listPSFACR04(LocalDate calendar) {
		return list2WorkDayBefore6(calendar);
	}

	private LocalDate list2WorkDayBefore6(LocalDate calendar) {
		// Wednesday to Saturday
		if (calendar.getDayOfWeek() == DateTimeConstants.SUNDAY) {
			calendar = calendar.minusDays(3);
		} else if (calendar.getDayOfWeek() >= 3) {
			calendar = calendar.minusDays(2);
		} else {
			calendar = calendar.minusDays(4);
		}
		
		return calendar;
	}
}
