package com.uhg.optum.ssmo.peoplesoft.twscalendar.service;

import java.util.List;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.JobCode;

public interface TWSCalendarService {
	List<JobCode> listPSJobCodes();
	List<JobCode> listDMJobCodes();
}
