package com.uhg.optum.ssmo.peoplesoft.twscalendar.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.CalendarDay;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.CalendarJobRule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.util.CalendarUtils;

public class PSFBIL03Rule extends CalendarJobRule {

	public PSFBIL03Rule(int year, Set<Holiday> holidayList) {
		this.year = year;
		this.holidays = holidayList;
	}

	@Override
	public List<CalendarDay> getDates() {
		List<CalendarDay> result = new ArrayList<CalendarDay>();
		for (int i = 1; i <= 12; i++) {
			List<LocalDate> dates = getWeekdayExceptWD1WD4(new LocalDate(year, i, 1));
			for(LocalDate d: dates){
				result.add(new CalendarDay(false, d));
			}
		}
		return result;
	}

	public List<LocalDate> getWeekdayExceptWD1WD4(LocalDate calendar) {
		List<LocalDate> listDays = new ArrayList<LocalDate>();
		
		while (calendar.getDayOfMonth() <= calendar.dayOfMonth().getMaximumValue()) {
			
			if (!CalendarUtils.isWeekEnds(calendar) && !CalendarUtils.isHoliday(calendar)) {
				listDays.add(new LocalDate(calendar.getYear(), calendar.getMonthOfYear(),
						calendar.getDayOfMonth()));
			}
			if(calendar.getDayOfMonth()==calendar.dayOfMonth().getMaximumValue()){
				break;
			}
			calendar = calendar.plusDays(1);
			
		}
		listDays.remove(getWorkDay4(new LocalDate(calendar.getYear(), calendar.getMonthOfYear(),1)));
		listDays.remove(CalendarUtils.getWorkDay1(new LocalDate(calendar.getYear(), calendar.getMonthOfYear(),1)));

		return listDays;
	}
	
	private LocalDate getWorkDay4(LocalDate calendar) {
		if(CalendarUtils.isHoliday(calendar)){	
			calendar = calendar.plusDays(1);
			return getWorkDay4(calendar);
		}
		if (calendar.getDayOfWeek() == DateTimeConstants.MONDAY
				|| calendar.getDayOfWeek() == DateTimeConstants.TUESDAY) {
			calendar = calendar.plusDays(3);
		} else if (calendar.getDayOfWeek() == DateTimeConstants.SUNDAY) {
			calendar = calendar.plusDays(4);
		} else {
			calendar = calendar.plusDays(5);
		}

		return calendar;
	}
}
