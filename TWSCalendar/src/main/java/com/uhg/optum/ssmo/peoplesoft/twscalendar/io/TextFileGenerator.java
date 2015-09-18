package com.uhg.optum.ssmo.peoplesoft.twscalendar.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.CalendarDay;

public class TextFileGenerator {
	
	public String generate(String jobname, List<CalendarDay> dates) {

		Date date = new Date();
		String timestamp = new Timestamp(date.getTime()).toString()
				.replace(".", "-").replace(":", "-").replace(" ", "_")
				.replace("-", "");
		String fileName = "TWSCalendar_" + jobname + "_" + timestamp + ".txt";
		String path = "C:\\TWSCalendar\\";
		try {

			File file = new File(path);
			file.mkdirs();

			FileWriter writer = new FileWriter(new File(path + "" + fileName));
			BufferedWriter bw = new BufferedWriter(writer);
			for (CalendarDay str : dates) {
				if(!str.isHoliday()){
					bw.write(str.getCalDay().toString());
					bw.newLine();
				}
			}
			bw.close();
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}
}
