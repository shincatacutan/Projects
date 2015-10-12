package com.uhg.optum.ssmo.esb.twscalendar.service;

import java.util.List;

import com.uhg.optum.ssmo.esb.twscalendar.domain.Holiday;

public interface HolidayService {
	public List<Holiday> getHolidays(int year, String holidayType);
}
