package com.uhg.optum.ssmo.esb.twscalendar.rules.peoplesoft;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.esb.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.esb.twscalendar.map.misc.PSF_ACR_CL_BI_NON_WD1;
import com.uhg.optum.ssmo.esb.twscalendar.rules.CalendarJobRule;

public class PSFACR13Rule extends CalendarJobRule {

	public PSFACR13Rule(int year, Set<Holiday> holidayList) {
		super();
		this.year = year;
		this.holidays = holidayList;
	}

	@Override
	public List<LocalDate> getResults() {
		List<LocalDate> listDays = new ArrayList<LocalDate>();
		CalendarJobRule job = new PSF_ACR_CL_BI_NON_WD1(year, holidays);
		for(LocalDate d: job.getResults()){
			d = d.plusDays(1);
			listDays.add(d);
		}
		return listDays;
	}

}
