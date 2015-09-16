package com.uhg.optum.ssmo.peoplesoft.twscalendar.io;

import java.util.List;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.Holiday;

public interface HolidayReader {
	
	public List<Holiday> getHolidays(int year, String holidayType);

}
