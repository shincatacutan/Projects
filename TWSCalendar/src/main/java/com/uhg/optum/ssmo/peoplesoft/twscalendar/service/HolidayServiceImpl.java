package com.uhg.optum.ssmo.peoplesoft.twscalendar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.io.HolidayReader;

@Service
public class HolidayServiceImpl implements HolidayService {

	@Autowired
	private HolidayReader holidays;

	@Override
	public List<Holiday> getHolidays(int year, String holidayType) {
		return holidays.getHolidays(year, holidayType);
	}
}
