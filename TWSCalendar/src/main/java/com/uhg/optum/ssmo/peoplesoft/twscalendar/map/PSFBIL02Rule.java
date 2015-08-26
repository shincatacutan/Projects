package com.uhg.optum.ssmo.peoplesoft.twscalendar.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.CalendarDay;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.CalendarJobRule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.util.CalendarUtils;

public class PSFBIL02Rule extends CalendarJobRule {

	public PSFBIL02Rule(int year, Set<Holiday> holidayList) {
		this.year = year;
		this.holidays = holidayList;
	}

	@Override
	public List<CalendarDay> getDates() {
		List<CalendarDay> result = new ArrayList<CalendarDay>();
		for (int i = 1; i <= 12; i++) {
			List<LocalDate> d = getWeekdaySecondThirdSaturday(new LocalDate(year, i, 1));
	
			for(LocalDate date: d){
				result.add(new CalendarDay(false, date));
			}
		}
		return result;
	}
	
	private List<LocalDate> getWeekdaySecondThirdSaturday(LocalDate calendar) {
		List<LocalDate> listDays = new ArrayList<LocalDate>();
		int firstSaturday = 7 - calendar.getDayOfWeek() + 7;
		int secondSaturday = firstSaturday + 7;

		while (calendar.getDayOfMonth() <= calendar.dayOfMonth().getMaximumValue()) {
			
			if (!CalendarUtils.isWeekEnds(calendar)) {
				listDays.add(new LocalDate(calendar.getYear(), calendar.getMonthOfYear(),
						calendar.getDayOfMonth()));
			} else if (calendar.getDayOfMonth() == firstSaturday
					|| calendar.getDayOfMonth() == secondSaturday) {
				listDays.add(new LocalDate(calendar.getYear(), calendar.getMonthOfYear(),
						calendar.getDayOfMonth()));
			}
			if(calendar.getDayOfMonth()==calendar.dayOfMonth().getMaximumValue()){
				break;
			}
			calendar = calendar.plusDays(1);
		}
		listDays.removeAll(CalendarUtils.listHoliday());
		return listDays;
	}

}
