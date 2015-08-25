package com.uhg.optum.ssmo.peoplesoft.twscalendar.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.JobCode;

@Service
public class TWSCalendarServiceImpl implements TWSCalendarService {

	@Override
	public List<JobCode> listJobCodes() {
		List<JobCode> listJobCodes = new ArrayList<JobCode>();
		JobCode jobCode = new JobCode();
		jobCode = new JobCode("PSF_ACR_CL_WD1_CHK", "PSFACR15");
		listJobCodes.add(jobCode);
		jobCode = new JobCode("PSF_BIL_CL_1ST_WD_WK", "PSFBIL05");
		listJobCodes.add(jobCode);
		jobCode = new JobCode("PSF_BIL_CL_WKD_TO_SAT_SKIP_MON", "PSFBIL02");
		listJobCodes.add(jobCode);
		jobCode = new JobCode("PSF_BIL_CL_NON_1ST_WD_WK", "PSFBIL03");
		listJobCodes.add(jobCode);
		jobCode = new JobCode("PSF_ACR_CL_BI_PIA_UNRD_CAL_18", "PSFACR14");
		listJobCodes.add(jobCode);
		jobCode = new JobCode("PSF_ACR_CL_WEEKDAYS", "PSFACR16");
		listJobCodes.add(jobCode);
		jobCode = new JobCode("PSF_ACR_CL_AUTO_MAINT_ZERO_BAL", "PSFACR11");
		listJobCodes.add(jobCode);
		return listJobCodes;
	}

}
