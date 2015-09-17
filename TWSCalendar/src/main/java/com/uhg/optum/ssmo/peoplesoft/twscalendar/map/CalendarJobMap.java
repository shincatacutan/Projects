package com.uhg.optum.ssmo.peoplesoft.twscalendar.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.CalendarJobRule;
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

		return ruleMap.get(jobName);
	}
}
