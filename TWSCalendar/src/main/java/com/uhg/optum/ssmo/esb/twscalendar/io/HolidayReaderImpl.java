package com.uhg.optum.ssmo.esb.twscalendar.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import com.uhg.optum.ssmo.esb.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.esb.twscalendar.io.HolidayReader;
import com.uhg.optum.ssmo.esb.twscalendar.util.ProjectConstants;

@Repository
public class HolidayReaderImpl implements HolidayReader {
	
	@Override
	public List<Holiday> getHolidays(int year, String holidayType) {
		List<Holiday> holidays = new ArrayList<Holiday>();

		BufferedReader br = null;

		try {
			String folder = ProjectConstants.UHG_HOLIDAYS;
			if (ProjectConstants.FEDERAL_HOLIDAY.equals(holidayType)) {
				folder = ProjectConstants.FEDERAL_HOLIDAYS;
			}
			File file = ResourceUtils.getFile("classpath:" + folder + year
					+ ".txt");
			br = new BufferedReader(new FileReader(file));
			String sCurrentLine = "";
			while ((sCurrentLine = br.readLine()) != null) {
				String[] line = sCurrentLine.split("-");
				String[] date = line[1].split("/");
				LocalDate lineDate = new LocalDate(Integer.parseInt(date[2]),
						Integer.parseInt(date[0]), Integer.parseInt(date[1]));
				holidays.add(new Holiday(line[0], lineDate));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return holidays;
	}

}
