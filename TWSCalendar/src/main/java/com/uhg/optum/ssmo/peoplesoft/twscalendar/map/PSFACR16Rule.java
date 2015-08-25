package com.uhg.optum.ssmo.peoplesoft.twscalendar.map;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.CalendarJobRule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.util.CalendarUtils;

public class PSFACR16Rule implements CalendarJobRule {

	
	private int year;
	private List<LocalDate> holidayList;
	
	public PSFACR16Rule(int year, List<LocalDate> holidayList) {
		this.year = year;
		this.holidayList = holidayList;
	}

	@Override
	public List<LocalDate> getDates() {
		List<LocalDate> result = new ArrayList<LocalDate>();
		for (int i = 1; i <= 12; i++) {
			result.addAll(getWeekdayExceptWD1LWDFriday(new LocalDate(year, i, 1)));
		}
		return result;
	}
	
	/*
	 * PSF_ACR_CL_WEEKDAYS PSFACR16 Runs on weekday except {{PSFACR11}} holiday, wd1 , lwd,
	 * friday and 2 days working day before 10
	 */
	public List<LocalDate> getWeekdayExceptWD1LWDFriday(LocalDate calendar) {
		List<LocalDate> listDays = new ArrayList<LocalDate>();
		while (calendar.getDayOfMonth() <= calendar.dayOfMonth().getMaximumValue()) {
			if (!CalendarUtils.isWeekEnds(calendar) && calendar.getDayOfWeek() != DateTimeConstants.FRIDAY && !CalendarUtils.isHoliday(calendar)) {
				listDays.add(new LocalDate(calendar.getYear(), calendar.getMonthOfYear(),calendar.getDayOfMonth()));
			}
			if(calendar.getDayOfMonth()==calendar.dayOfMonth().getMaximumValue()){
				break;
			}
			calendar = calendar.plusDays(1);
		}
		
		listDays.remove(CalendarUtils.getLastWorkDay(new LocalDate(calendar.getYear(), calendar.getMonthOfYear(),1)));
		listDays.remove(CalendarUtils.getWorkDay1(new LocalDate(calendar.getYear(), calendar.getMonthOfYear(),1)));
		listDays.remove(list2WorkDayBefore10((new LocalDate(calendar.getYear(), calendar.getMonthOfYear(),10))));
		return listDays;
	}

	private LocalDate list2WorkDayBefore10(LocalDate calendar){
		// Wednesday to Saturday
		if(calendar.getDayOfWeek()==DateTimeConstants.SUNDAY){
			calendar = calendar.minusDays(3);
		}else if(calendar.getDayOfWeek()>=3){
			calendar = calendar.minusDays(2);
		}else {
			calendar = calendar.minusDays(4);
		}

	return calendar;
	}
}
