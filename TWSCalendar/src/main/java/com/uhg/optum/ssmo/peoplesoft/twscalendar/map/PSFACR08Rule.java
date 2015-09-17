package com.uhg.optum.ssmo.peoplesoft.twscalendar.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.io.HolidayReader;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.io.HolidayReaderImpl;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.CalendarJobRule;

public class PSFACR08Rule extends CalendarJobRule {

	public PSFACR08Rule(int year, Set<Holiday> holidayList) {
		this.year = year;
		this.holidays = holidayList;
	}

	/*
	 * Calendar is use to process lockbox files when banks are open but UHG is
	 * closed. It runs the Saturday after Thanksgiving and also may run if any
	 * other UHC Holidays fall on a banking day.
	 */
	@Override
	public List<LocalDate> getResults() {
		List<LocalDate> listDays = new ArrayList<LocalDate>();

		HolidayReader holidayService = new HolidayReaderImpl();
		List<Holiday> uhgHolidays = holidayService.getHolidays(year, "uhg");
		List<Holiday> federalHolidays = holidayService.getHolidays(year,
				"federal");
		Map<String, LocalDate> uhgmap = new HashMap<String, LocalDate>();
		Map<String, LocalDate> federalmap = new HashMap<String, LocalDate>();

		for (Holiday i : uhgHolidays)
			uhgmap.put(i.getName(), i.getDate());
		for (Holiday i : federalHolidays)
			federalmap.put(i.getName(), i.getDate());

		String thanksGiving = "Thanksgiving Day";
		LocalDate thanksgivingDay = uhgmap.get(thanksGiving);
		
		getSaturdayAfterHoliday(listDays, thanksgivingDay, thanksgivingDay.getDayOfWeek());

		for (Entry<String, LocalDate> entry : uhgmap.entrySet()) {

			if (federalmap.get(entry.getKey()) != null) {
				if (!entry.getValue().equals(federalmap.get(entry.getKey()))) {
					getSaturdayAfterHoliday(listDays, entry.getValue(), entry
							.getValue().getDayOfWeek());
				}
			}
		}

		return listDays;
	}

	private void getSaturdayAfterHoliday(List<LocalDate> listDays,
			LocalDate batchforTD, int day) {
		while (day < DateTimeConstants.SATURDAY) {
			batchforTD = batchforTD.plusDays(1);
			day++;
		}
		listDays.add(batchforTD);
	}
}
