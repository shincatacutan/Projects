package com.uhg.optum.ssmo.esb.twscalendar.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.esb.twscalendar.domain.CalendarDay;
import com.uhg.optum.ssmo.esb.twscalendar.domain.Holiday;
import com.uhg.optum.ssmo.esb.twscalendar.rules.peoplesoft.PSFACR00Rule;

public class Runner {

	public static void main(String[] args) {
		PSFACR00Rule rule = new PSFACR00Rule(2016, parseHolidays(""));
		System.out.println(rule.getResults());
	}

	public static void printCalendar(List<CalendarDay> list, String jobName,
			int year) {

		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create a blank sheet
		XSSFSheet writableSheet = workbook.createSheet(jobName);

		int Y = year;

		XSSFCellStyle titleStyle = workbook.createCellStyle();

		XSSFFont titleFont = CalendarPrinter.boldHeaderFont(workbook);
		titleFont.setFontHeightInPoints((short) 16);

		titleStyle.setFont(titleFont);

		// Year
		Row titleRow = writableSheet.createRow(1);
		Cell titleCell = titleRow.createCell(1);
		titleCell.setCellValue("Calendar " + Y);
		titleCell.setCellStyle(titleStyle);

		// Merge cells of title header
		writableSheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 7 * 4 + 3));

		for (int i = 1; i <= 12; i++) {
			CalendarPrinter.printMonth(workbook, writableSheet, i, Y, list);
		}
		for (int i = 0; i < 7 * 4 * +3; i++) {
			writableSheet.autoSizeColumn(i);
		}

		try {
			FileOutputStream out = new FileOutputStream(new File("test.xlsx"));
			workbook.write(out);
			out.close();
			System.out.println("Excel written successfully..");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static Set<Holiday> parseHolidays(String holidayList) {
		Set<Holiday> holidays = new HashSet<Holiday>();
		holidays.add(new Holiday("New Year's Day", new LocalDate(2015, 1, 1)));
		holidays.add(new Holiday("Birthday of Martin Luther King, Jr.",
				new LocalDate(2015, 1, 19)));
		holidays.add(new Holiday("Memorial Day", new LocalDate(2015, 5, 25)));
		holidays.add(new Holiday("Independence Day", new LocalDate(2015, 7, 3)));
		holidays.add(new Holiday("Labor Day", new LocalDate(2015, 9, 7)));
		holidays.add(new Holiday("Thanksgiving Day",
				new LocalDate(2015, 11, 26)));
		holidays.add(new Holiday("Day after Thanksgiving ", new LocalDate(2015,
				11, 27)));
		holidays.add(new Holiday("Christmas Day", new LocalDate(2015, 12, 25)));
		holidays.add(new Holiday("2016 New Year", new LocalDate(2016, 1, 1)));

		holidays.add(new Holiday("New Year's Day", new LocalDate(2016, 1, 1)));
		holidays.add(new Holiday("Birthday of Martin Luther King, Jr.",
				new LocalDate(2016, 1, 18)));
		holidays.add(new Holiday("Memorial Day", new LocalDate(2016, 5, 30)));
		holidays.add(new Holiday("Independence Day", new LocalDate(2016, 7, 4)));
		holidays.add(new Holiday("Labor Day", new LocalDate(2016, 9, 5)));
		holidays.add(new Holiday("Thanksgiving Day",
				new LocalDate(2016, 11, 24)));
		holidays.add(new Holiday("Day after Thanksgiving ", new LocalDate(2016,
				11, 25)));
		holidays.add(new Holiday("Christmas Day", new LocalDate(2016, 12, 26)));
		holidays.add(new Holiday("New YEar", new LocalDate(2017, 1, 1)));
		return holidays;
	}
}
