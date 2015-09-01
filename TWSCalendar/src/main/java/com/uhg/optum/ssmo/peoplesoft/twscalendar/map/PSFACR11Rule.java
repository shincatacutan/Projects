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

public class PSFACR11Rule extends CalendarJobRule {
	
	public PSFACR11Rule(int year, Set<Holiday> holidayList) {
		this.year = year;
		this.holidays = holidayList;
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
		CalendarUtils.addHolidaysToList(result, holidays);
		return result;
	}
	
	/*
	 * PSF_ACR_CL_AUTO_MAINT_ZERO_BAL PSFACR11 Runs every Friday, LWD, 2 Days
	 * Working Day Before 10 except WD1
	 */
	public List<LocalDate> listPSFACR11(LocalDate calendar){
		List<LocalDate> listDays = new ArrayList<LocalDate>();
		
		listDays.addAll(CalendarUtils.listAllFriday(new LocalDate(calendar.getYear(), calendar.getMonthOfYear(),1)));
		listDays.add(CalendarUtils.list2WorkDayBefore10(new LocalDate(calendar.getYear(), calendar.getMonthOfYear(),10)));
		listDays.add(CalendarUtils.getLastWorkDay(new LocalDate(calendar.getYear(), calendar.getMonthOfYear(),1)));
		listDays.remove(CalendarUtils.getNthWorkDayOfMonth(1, calendar.getMonthOfYear(), year, holidays));
		listDays.removeAll(CalendarUtils.listHoliday());
//		CalendarUtils.sortCalendar(listDays);
		
		return CalendarUtils.removeDuplicate(listDays);
	}
}
