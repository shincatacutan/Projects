package com.uhg.optum.ssmo.peoplesoft.twscalendar.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.CalendarJobRule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.util.CalendarUtils;

public class PSFACR09Rule extends CalendarJobRule {

	public PSFACR09Rule(int year, Set<Holiday> holidayList) {
		super.year = year;
		super.holidays = holidayList;
	}

	@Override
	public List<LocalDate> getResults() {
		List<LocalDate> result = new ArrayList<LocalDate>();
		CalendarJobRule psfacr00rule = new PSFACR00Rule(year, holidays);
		CalendarJobRule psfacr06rule = new PSFACR06Rule(year, holidays);
		for (LocalDate day : psfacr00rule.getResults()) {
			result.add(CalendarUtils.getNthBusDayBeforeSettleDay(2,
					day.getDayOfMonth(), day.getMonthOfYear(), day.getYear(),
					holidays));
		}
		for (LocalDate day : psfacr06rule.getResults()) {
			result.add(CalendarUtils.getNthBusDayBeforeSettleDay(2,
					day.getDayOfMonth(), day.getMonthOfYear(), day.getYear(),
					holidays));
		}
		return result;

	}

}
