package com.uhg.optum.ssmo.peoplesoft.twscalendar.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.domain.CalendarDay;
import com.uhg.optum.ssmo.peoplesoft.twscalendar.util.CalendarPrinter;

public class ExcelGenerator {

	private static final int CALMONTHCELLS = 7;
	private final static Logger logger = LoggerFactory
			.getLogger(ExcelGenerator.class);

	public String generate(List<CalendarDay> list, String jobname, int year) {
		// Blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create a blank sheet
		XSSFSheet writableSheet = workbook.createSheet(jobname);

		int Y = year;

		XSSFCellStyle titleStyle = CalendarPrinter.titleStyle(workbook);

		//blank space for aesthetics
		Row blank = writableSheet.createRow(0);
		for(int i = 0; i<(CALMONTHCELLS * 4 + 3); i+=8){
			Cell blankCell = blank.createCell(i);
			blankCell.setCellValue("  ");
		}
	
		
		// Year
		Row titleRow = writableSheet.createRow(1);
		Cell titleCell = titleRow.createCell(1);
		titleCell.setCellValue(jobname + " Calendar " + Y);
		titleCell.setCellStyle(titleStyle);

		// Merge cells of title header
		writableSheet.addMergedRegion(new CellRangeAddress(1, 1, 1, CALMONTHCELLS * 4 + 3));

		for (int i = 1; i <= 12; i++) {
			CalendarPrinter.printMonth(workbook, writableSheet, i, Y, list);
		}
		for (int i = 0; i < CALMONTHCELLS * 4 * +3; i++) {
			writableSheet.autoSizeColumn(i);
		}

		Date date = new Date();
		String timestamp = new Timestamp(date.getTime()).toString()
				.replace(".", "-").replace(":", "-").replace(" ", "_")
				.replace("-", "");
		String fileName = "TWSCalendar_" + jobname + "_" + timestamp + ".xlsx";
		String path = "C:\\TWSCalendar\\";
		try {

			File file = new File(path);
			file.mkdirs();
			FileOutputStream out = new FileOutputStream(new File(path + ""
					+ fileName));
			workbook.write(out);
			out.close();
			logger.debug("Excel written successfully...");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}
}
