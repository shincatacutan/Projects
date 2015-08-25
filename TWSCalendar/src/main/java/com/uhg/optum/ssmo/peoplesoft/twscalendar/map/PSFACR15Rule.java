package com.uhg.optum.ssmo.peoplesoft.twscalendar.map;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.CalendarJobRule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.util.CalendarUtils;

public class PSFACR15Rule implements CalendarJobRule {

	private int year;
	private List<LocalDate> holidayList;
	
	public PSFACR15Rule(int year, List<LocalDate> holidayList) {
		this.year = year;
		this.holidayList = holidayList;
	}

	@Override
	public List<LocalDate> getDates() {
		List<LocalDate> result = new ArrayList<LocalDate>();
		for (int i = 1; i <= 12; i++) {
			result.add(getAllAfterWorkDay1(new LocalDate(year, i, 1)));
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
