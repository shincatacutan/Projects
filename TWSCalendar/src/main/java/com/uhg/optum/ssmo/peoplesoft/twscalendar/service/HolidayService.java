package com.uhg.optum.ssmo.peoplesoft.twscalendar.service;

import java.util.List;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.Holiday;

public interface HolidayService {
	public List<Holiday> getHolidays(int year, String holidayType);
}
