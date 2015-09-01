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

public class PSFACR08Rule extends CalendarJobRule {

	public PSFACR08Rule(int year, Set<Holiday> holidayList) {
		this.year = year;
		this.holidays = holidayList;
	}

	@Override
	public List<CalendarDay> getDates() {
		List<CalendarDay> result = new ArrayList<CalendarDay>();
		result.addAll(listPSFACR08(year));

		CalendarUtils.addHolidaysToList(result, holidays);
		return result;
	}

	public List<CalendarDay> listPSFACR08(int year) {
		List<LocalDate> calendar = CalendarUtils.listHoliday();
		List<CalendarDay> days = new ArrayList<CalendarDay>();
		for (LocalDate result : calendar) {
			if (result.getDayOfWeek() == DateTimeConstants.SATURDAY) {

				days.add(new CalendarDay(Boolean.FALSE, result));
			}
			if (result.getDayOfMonth() > 20
					&& result.getDayOfWeek() == DateTimeConstants.THURSDAY) {
				result = result.plusDays(2);
				days.add(new CalendarDay(Boolean.FALSE, result));
			}
		}

		return days;
	}

}
