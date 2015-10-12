package com.uhg.optum.ssmo.esb.twscalendar.service;

import java.util.List;

import com.uhg.optum.ssmo.esb.twscalendar.domain.JobCode;

public interface TWSCalendarService {
	List<JobCode> listPSJobCodes();
	List<JobCode> listDMJobCodes();
	List<JobCode> listDuncanJobCodes();
}
