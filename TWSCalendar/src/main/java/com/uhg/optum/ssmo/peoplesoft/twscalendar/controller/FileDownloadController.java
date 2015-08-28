package com.uhg.optum.ssmo.peoplesoft.twscalendar.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.io.ExcelGenerator;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.io.FileStreamGenerator;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.io.TextFileGenerator;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.map.CalendarJobMap;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.rules.CalendarJobRule;

@Controller
public class FileDownloadController {
	private static final String XLSX_FILETYPE = "xlsx";

	@Autowired
	ServletContext context;

	private final static Logger logger = LoggerFactory
			.getLogger(FileDownloadController.class);

	@RequestMapping(value = "/generateFile", method = RequestMethod.POST)
	public void doDownload(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String jobname,
			@RequestParam String holidayList, @RequestParam String year,
			@RequestParam String fileType) throws IOException {

		logger.debug("[generateFile] passed jobname: " + jobname);
		logger.debug("[generateFile] passed holidayList: " + holidayList);
		logger.debug("[generateFile] passed year: " + year);
		logger.debug("[generateFile] passed fileType: " + fileType);
		
		String fileName = "";
		
		Set<Holiday> holidays = parseHolidays(holidayList);
		
		int yearInt = Integer.parseInt(year);
		
		if (XLSX_FILETYPE.equals(fileType)) {
			CalendarJobRule rule = CalendarJobMap.getJobRule(jobname, holidays,
					yearInt);
			fileName = new ExcelGenerator().generate(rule.getDates(),jobname,yearInt);
			logger.debug("[generateExcel] generated fileName: " + fileName);
		} else {
			CalendarJobRule rule = CalendarJobMap.getJobRule(jobname, holidays,
					yearInt);
			fileName = new TextFileGenerator().generate(jobname,
					rule.getDates());
		}

		String fullPath = "C:\\TWSCalendar\\" + fileName;

		FileStreamGenerator.generate(response, fullPath, context);

	}

	private Set<Holiday> parseHolidays(String holidayList) {
		Set<Holiday> holidays = new HashSet<Holiday>();
		holidays.add(new Holiday("New Year's Day", new LocalDate(2015, 1, 1)));
		holidays.add(new Holiday("Birthday of Martin Luther King, Jr.", new LocalDate(2015, 1, 19)));
		holidays.add(new Holiday("Memorial Day", new LocalDate(2015, 5, 25)));
		holidays.add(new Holiday("Independence Day", new LocalDate(2015, 7, 3)));
		holidays.add(new Holiday("Labor Day", new LocalDate(2015, 9, 7)));
		holidays.add(new Holiday("Thanksgiving Day", new LocalDate(2015, 11, 26)));
		holidays.add(new Holiday("Day after Thanksgiving ", new LocalDate(2015, 11, 27)));
		holidays.add(new Holiday("Christmas Day", new LocalDate(2015, 12, 25)));
		return holidays;
	}

}