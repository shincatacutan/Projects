package com.uhg.optum.ssmo.peoplesoft.twscalendar.map;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.CalendarDay;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.CalendarJobRule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.util.CalendarUtils;

public class PSFACR15Rule implements CalendarJobRule {

	private int year;
	private List<Holiday> holidayList;
	
	public PSFACR15Rule(int year, List<Holiday> holidayList) {
		this.year = year;
		this.holidayList = holidayList;
	}

	@Override
	public List<CalendarDay> getDates() {
		List<CalendarDay> result = new ArrayList<CalendarDay>();
		for (int i = 1; i <= 12; i++) {
			LocalDate d = getAllAfterWorkDay1(new LocalDate(year, i, 1));
			result.add(new CalendarDay(false, d));
		}
		return result;
	}
	
	private LocalDate getAllAfterWorkDay1(LocalDate calendar) {
		if(CalendarUtils.isHoliday(calendar)){	
			calendar = calendar.plusDays(1);
			return getAllAfterWorkDay1(calendar);
		}
		if (!CalendarUtils.isWeekEnds(calendar)) {
			calendar = calendar.plusDays(1);
		} else if (calendar.getDayOfWeek() == DateTimeConstants.SATURDAY) {
			calendar = calendar.plusDays(3);
		} else {
			calendar = calendar.plusDays(2);
		}
		return calendar;
	}

}
