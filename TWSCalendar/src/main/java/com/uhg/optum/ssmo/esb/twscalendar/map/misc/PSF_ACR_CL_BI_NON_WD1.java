package com.uhg.optum.ssmo.esb.twscalendar.map.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.esb.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.esb.twscalendar.rules.CalendarJobRule;
import com.uhg.optum.ssmo.esb.twscalendar.util.CalendarUtils;

public class PSF_ACR_CL_BI_NON_WD1 extends CalendarJobRule {

	public PSF_ACR_CL_BI_NON_WD1(int year, Set<Holiday> holidayList) {
		super();
		this.year = year;
		this.holidays = holidayList;
	}

	@Override
	public List<LocalDate> getResults() {
		List<LocalDate> listDays = new ArrayList<LocalDate>();
		for (int i = 1; i <= 12; i++) {
			listDays.addAll(CalendarUtils.getAllWorkDays(i, year, holidays));
			listDays.remove(CalendarUtils.getNthWorkDayOfMonth(1, i, year,
					holidays));
		}
		return CalendarUtils.removeDuplicate(listDays);
	}
}
