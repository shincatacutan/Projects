package com.uhg.optum.ssmo.esb.twscalendar.io;

import java.util.List;

import com.uhg.optum.ssmo.esb.twscalendar.domain.Holiday;

public interface HolidayReader {
	
	public List<Holiday> getHolidays(int year, String holidayType);

}
