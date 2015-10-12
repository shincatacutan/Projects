package com.uhg.optum.ssmo.esb.twscalendar.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.LocalDate;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uhg.optum.ssmo.esb.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.esb.twscalendar.exception.GenericException;
import com.uhg.optum.ssmo.esb.twscalendar.io.ExcelGenerator;
import com.uhg.optum.ssmo.esb.twscalendar.io.FileStreamGenerator;
import com.uhg.optum.ssmo.esb.twscalendar.io.TextFileGenerator;
import com.uhg.optum.ssmo.esb.twscalendar.map.CalendarJobMap;
import com.uhg.optum.ssmo.esb.twscalendar.rules.CalendarJobRule;



@Controller
public class FileDownloadController {
	private static final String XLSX_FILETYPE = "xlsx";

	@Autowired
	ServletContext context;

	private final static Logger logger = LoggerFactory
			.getLogger(FileDownloadController.class);

	@ExceptionHandler(GenericException.class)
	public ModelAndView handleCustomException(GenericException ex) {

		ModelAndView model = new ModelAndView("error/generic_error");
		model.addObject("errCode", ex.getErrCode());
		model.addObject("errMsg", ex.getErrMsg());

		return model;
	}
	
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
		CalendarJobRule rule = CalendarJobMap.getJobRule(jobname, holidays,
				yearInt);
		if(null==rule){
			throw new GenericException("E003",
					"Calendar is not available.");
		}
		if (XLSX_FILETYPE.equals(fileType)) {
			fileName = new ExcelGenerator().generate(rule.getFinalDates(),
					jobname, yearInt);
			logger.debug("[generateExcel] generated fileName: " + fileName);
		} else {
			fileName = new TextFileGenerator().generate(jobname,
					rule.getFinalDates());
		}

		String fullPath = "C:\\TWSCalendar\\" + fileName;

		FileStreamGenerator.generate(response, fullPath, context);

	}

	private Set<Holiday> parseHolidays(String holidayList) {
		Set<Holiday> holidays = new HashSet<Holiday>();
		try {
			JSONArray jsonArray = (JSONArray) new JSONParser()
					.parse(holidayList);
			Iterator<?> i = jsonArray.iterator();
			while (i.hasNext()) {
				JSONObject obj = (JSONObject) i.next();
				String name = (String) obj.get("name");
				String dateString[] = ((String) obj.get("date")).split("/");
				LocalDate d = new LocalDate(Integer.parseInt(dateString[2]),
						Integer.parseInt(dateString[0]),
						Integer.parseInt(dateString[1]));
				holidays.add(new Holiday(name, d));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return holidays;
	}

}