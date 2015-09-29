package com.uhg.optum.ssmo.peoplesoft.twscalendar.map.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.CalendarJobRule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.util.CalendarUtils;

public class DMCal15Rule extends CalendarJobRule {

	public DMCal15Rule(int year, Set<Holiday> holidayList) {
		this.year = year;
		this.holidays = holidayList;
	}

	@Override
	public List<LocalDate> getResults() {
		List<LocalDate> listDays = new ArrayList<LocalDate>();
		for (int i = 1; i <= 12; i++) {
			listDays.add(getWorkday15(new LocalDate(year, i, 15)));
		}
		return listDays;
	}

	private LocalDate getWorkday15(LocalDate localDate) {
		if (localDate.getDayOfWeek() == DateTimeConstants.SATURDAY) {
			localDate = localDate.plusDays(2);
		} else if (localDate.getDayOfWeek() == DateTimeConstants.SUNDAY) {
			localDate = localDate.plusDays(1);
		}
		if (CalendarUtils.isHoliday(localDate, holidays)) {
			localDate = new LocalDate(new LocalDate(localDate.getYear(),
					localDate.getMonthOfYear(), localDate.getDayOfMonth() + 1));
			return getWorkday15(localDate);
		}
		return localDate;
	}

}
