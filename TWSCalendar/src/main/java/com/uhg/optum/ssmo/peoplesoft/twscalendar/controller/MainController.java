package com.uhg.optum.ssmo.peoplesoft.twscalendar.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.JobCode;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.exception.GenericException;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.service.HolidayService;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.service.TWSCalendarService;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.vo.HolidayVO;

@Controller
public class MainController {

	private final static Logger logger = LoggerFactory
			.getLogger(MainController.class);

	private static final String VIEW_INDEX = "index";

	@Autowired
	private TWSCalendarService twsCalendarService;

	@Autowired
	private HolidayService holidayService;

	@ExceptionHandler(GenericException.class)
	public ModelAndView handleCustomException(GenericException ex) {

		ModelAndView model = new ModelAndView("error/generic_error");
		model.addObject("errCode", ex.getErrCode());
		model.addObject("errMsg", ex.getErrMsg());

		return model;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String defaultHome(ModelMap model) {
		logger.debug("success");
		return VIEW_INDEX;
	}

	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public String welcomeName(@PathVariable String name, ModelMap model) {
		logger.debug("success");
		return VIEW_INDEX;
	}

	@RequestMapping(value = "/getJobCodes", method = RequestMethod.GET)
	public @ResponseBody List<JobCode> getJobCodes() {
		return twsCalendarService.listJobCodes();
	}

	@RequestMapping(value = "/getHolidayList", method = RequestMethod.GET)
	public @ResponseBody List<HolidayVO> getHolidayList(@RequestParam String year) {
		List<Holiday> holidays = holidayService.getHolidays(Integer.parseInt(year), "uhg");
		List<HolidayVO> results = new ArrayList<HolidayVO>();
		logger.debug("holiday list size: "+holidays.size());
		for(Holiday h: holidays){
			results.add(new HolidayVO(h.getName(), h.getDate().toString("MM/dd/yyyy")));
		}
		return results;
	}
}
