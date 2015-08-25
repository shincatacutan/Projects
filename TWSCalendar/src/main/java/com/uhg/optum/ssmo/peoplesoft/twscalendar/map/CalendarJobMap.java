package com.uhg.optum.ssmo.peoplesoft.twscalendar.map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.CalendarJobRule;

public class CalendarJobMap {
	public static CalendarJobRule getJobRule(String jobName,
			List<LocalDate> holidayList, int year) {
		Map<String, CalendarJobRule> ruleMap = new HashMap<String, CalendarJobRule>();

		ruleMap.put("PSFBIL05", new PSFBIL05Rule(year, holidayList));
		ruleMap.put("PSFACR15", new PSFACR15Rule(year, holidayList));
		ruleMap.put("PSFBIL02", new PSFBIL02Rule(year, holidayList));
		ruleMap.put("PSFACR14", new PSFACR14Rule(year, holidayList));
		ruleMap.put("PSFBIL03", new PSFBIL03Rule(year, holidayList));

		ruleMap.put("PSFACR08", null);
		ruleMap.put("PSFACR16", new PSFACR16Rule(year, holidayList));
		ruleMap.put("PSFACR17", null);
		ruleMap.put("PSFACR00", null);
		ruleMap.put("PSFACR02", null);

		ruleMap.put("PSFACR03", null);
		ruleMap.put("PSFACR04", null);
		ruleMap.put("PSFACR06", null);
		ruleMap.put("PSFACR01", null);
		ruleMap.put("PSFACR10", null);

		ruleMap.put("PSFACR09", null);
		ruleMap.put("PSFACR11", new PSFACR11Rule(year, holidayList));
		ruleMap.put("PSFACR05", null);
		ruleMap.put("PSFACR13", null);

		return ruleMap.get(jobName);
	}
}
