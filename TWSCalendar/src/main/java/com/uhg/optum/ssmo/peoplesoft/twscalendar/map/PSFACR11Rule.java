package com.uhg.optum.ssmo.peoplesoft.twscalendar.map;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.CalendarDay;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.CalendarJobRule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.util.CalendarUtils;

public class PSFACR11Rule implements CalendarJobRule {

	private int year;
	private List<Holiday> holidayList;

	public PSFACR11Rule(int year, List<Holiday> holidayList) {
		this.year = year;
		this.holidayList = holidayList;
	}

	@Override
	public List<CalendarDay> getDates() {
		List<CalendarDay> result = new ArrayList<CalendarDay>();
		for (int i = 1; i <= 12; i++) {			
			List<LocalDate> dates = listPSFACR11(new LocalDate(year, i, 1));
			for(LocalDate d: dates){
				result.add(new CalendarDay(false, d));
			}
		}
		return result;
	}
	
	/*
	 * PSF_ACR_CL_AUTO_MAINT_ZERO_BAL PSFACR11 Runs every Friday, LWD, 2 Days
	 * Working Day Before 10 except WD1
	 */
	public List<LocalDate> listPSFACR11(LocalDate calendar){
		List<LocalDate> listDays = new ArrayList<LocalDate>();
		
		listDays.addAll(CalendarUtils.listAllFriday(new LocalDate(calendar.getYear(), calendar.getMonthOfYear(),1)));
		listDays.add(list2WorkDayBefore10(new LocalDate(calendar.getYear(), calendar.getMonthOfYear(),10)));
		listDays.add(CalendarUtils.getLastWorkDay(new LocalDate(calendar.getYear(), calendar.getMonthOfYear(),1)));
		listDays.remove(CalendarUtils.getWorkDay1(new LocalDate(calendar.getYear(), calendar.getMonthOfYear(),1)));
		listDays.removeAll(CalendarUtils.listHoliday());
//		CalendarUtils.sortCalendar(listDays);
		
		return CalendarUtils.removeDuplicate(listDays);
	}
	
	/*Part of PSFACR11*/
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
