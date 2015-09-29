package com.uhg.optum.ssmo.peoplesoft.twscalendar.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.map.misc.CL_FSTWDRule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.map.misc.CL_LSTWDRule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.map.misc.DMJan1SunRule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.map.misc.DMLastCalDayRule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.map.misc.DMTuesdaysRule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.map.misc.DMWorkday2Rule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.map.misc.DMWorkday3Rule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.map.misc.DMWorkdayRule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.map.misc.ESBDMT01Rule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.map.misc.DM2nd3rdSatRule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.map.misc.DMCal15Rule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.map.misc.HolidayCalendar;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.CalendarJobRule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.DirectDebitRule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.PSFACR00Rule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.PSFACR01Rule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.PSFACR02Rule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.PSFACR03Rule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.PSFACR04Rule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.PSFACR05Rule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.PSFACR06Rule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.PSFACR08Rule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.PSFACR09Rule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.PSFACR10Rule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.PSFACR11Rule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.PSFACR13Rule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.PSFACR14Rule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.PSFACR15Rule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.PSFACR16Rule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.PSFACR17Rule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.PSFBIL02Rule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.PSFBIL03Rule;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.PSFBIL05Rule;

public class CalendarJobMap {
	public static CalendarJobRule getJobRule(String jobName,
			Set<Holiday> holidayList, int year) {
		Map<String, CalendarJobRule> ruleMap = new HashMap<String, CalendarJobRule>();
		//PeopleSoft Calendars
		ruleMap.put("PSFBIL05", new PSFBIL05Rule(year, holidayList));
		ruleMap.put("PSFACR15", new PSFACR15Rule(year, holidayList));
		ruleMap.put("PSFBIL02", new PSFBIL02Rule(year, holidayList));
		ruleMap.put("PSFACR14", new PSFACR14Rule(year, holidayList));
		ruleMap.put("PSFBIL03", new PSFBIL03Rule(year, holidayList));

		ruleMap.put("PSFACR08", new PSFACR08Rule(year, holidayList));
		ruleMap.put("PSFACR16", new PSFACR16Rule(year, holidayList));
		ruleMap.put("PSFACR17", new PSFACR17Rule(year, holidayList));
		ruleMap.put("PSFACR00", new PSFACR00Rule(year, holidayList));
		ruleMap.put("PSFACR02", new PSFACR02Rule(year, holidayList));

		ruleMap.put("PSFACR03", new PSFACR03Rule(year, holidayList));
		ruleMap.put("PSFACR04", new PSFACR04Rule(year, holidayList));
		ruleMap.put("PSFACR06", new PSFACR06Rule(year, holidayList));
		ruleMap.put("PSFACR01", new PSFACR01Rule(year, holidayList));
		ruleMap.put("PSFACR10", new PSFACR10Rule(year, holidayList));

		ruleMap.put("PSFACR09", new PSFACR09Rule(year, holidayList));
		ruleMap.put("PSFACR11", new PSFACR11Rule(year, holidayList));
		ruleMap.put("PSFACR05", new PSFACR05Rule(year, holidayList));
		ruleMap.put("PSFACR13", new PSFACR13Rule(year, holidayList));
		ruleMap.put("DD", new DirectDebitRule(year, holidayList));

		//Datamart Calendars
		ruleMap.put("HOLIDAYS", new HolidayCalendar(year, holidayList));
		ruleMap.put("ESBDMT01", new ESBDMT01Rule(year, holidayList));
		ruleMap.put("CL_LSTWD", new CL_LSTWDRule(year, holidayList));
		ruleMap.put("CL_FSTWD", new CL_FSTWDRule(year, holidayList));
		ruleMap.put("CL_1SJAN", new DMJan1SunRule(year, holidayList));
		
		ruleMap.put("ESB_DMT_CL_Calendar_Day_15", new DMCal15Rule(year, holidayList));
		ruleMap.put("ESB_DMT_CL_2nd_3rd_SAT", new DM2nd3rdSatRule(year, holidayList));
		ruleMap.put("ESB_DMT_CL_LAST_CAL_DAY", new DMLastCalDayRule(year, holidayList));
		ruleMap.put("ESB_DMT_CL_WEEKLY_TUES", new DMTuesdaysRule(year, holidayList));
		ruleMap.put("ESB_DMT_CL_WORKDAY", new DMWorkdayRule(year, holidayList));
		ruleMap.put("ESB_DMT_CL_WORKDAY_2", new DMWorkday2Rule(year, holidayList));
		ruleMap.put("ESB_DMT_CL_WORKDAY_3", new DMWorkday3Rule(year, holidayList));
	
		return ruleMap.get(jobName);
	}
}
