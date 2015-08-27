package com.uhg.optum.ssmo.peoplesoft.twscalendar.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CalendarPrinter {

	public int day(int M, int D, int Y) {
		int y = Y - (14 - M) / 12;
		int x = y + y / 4 - y / 100 + y / 400;
		int m = M + 12 * ((14 - M) / 12) - 2;
		int d = (D + x + (31 * m) / 12) % 7;
		return d;
	}

	// return true if the given year is a leap year
	public boolean isLeapYear(int year) {
		if ((year % 4 == 0) && (year % 100 != 0))
			return true;
		if (year % 400 == 0)
			return true;
		return false;
	}

	public void printCalendar(int year) {

		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create a blank sheet
		XSSFSheet writableSheet = workbook.createSheet("Sample");

		int Y = year; 

		XSSFCellStyle titleStyle = workbook.createCellStyle();
		
		XSSFFont titleFont = workbook.createFont();
		titleFont.setBold(true);
		titleFont.setFontHeightInPoints((short) 16);
		
		titleStyle.setFont(titleFont);
		
		// Year
		Row titleRow = writableSheet.createRow(1);
		Cell titleCell = titleRow.createCell(1);
		titleCell.setCellValue("Calendar " + Y);
		titleCell.setCellStyle(titleStyle);
		
		
		writableSheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 7 * 4 + 3));
		for (int i = 1; i <= 12; i++) {
			printMonth(workbook, writableSheet, i, Y);
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

	private void printMonth(XSSFWorkbook workbook, XSSFSheet writableSheet,
			int M, int Y) {
		// months[i] = name of month i
		String[] months = {
				"", // leave empty so that months[1] = "January"
				"January", "February", "March", "April", "May", "June", "July",
				"August", "September", "October", "November", "December" };

		// days[i] = number of days in month i
		int[] days = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		// check for leap year
		if (M == 2 && isLeapYear(Y))
			days[M] = 29;

		MonthCoordinates monthCoordinate = MonthCoordinates.valueOf(months[M]);

		XSSFCellStyle borderStyle = workbook.createCellStyle();
		borderStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		borderStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
		borderStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
		borderStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		borderStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		
		
		XSSFCellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFillForegroundColor(HSSFColor.YELLOW.index);
		headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headerStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		headerStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		headerStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
		headerStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
		headerStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		
		XSSFFont boldYellowHeader = workbook.createFont();
		boldYellowHeader.setBold(true);
		
		headerStyle.setFont(boldYellowHeader);
		
		
		
		
		int rowCtr = monthCoordinate.getX();

		int cellctr = monthCoordinate.getY();

		Row monthRow;
		// Month
		if (writableSheet.getRow(rowCtr) == null) {
			monthRow = writableSheet.createRow(rowCtr++);
		} else {
			monthRow = writableSheet.getRow(rowCtr++);
		}
		writableSheet.addMergedRegion(new CellRangeAddress(rowCtr - 1,
				rowCtr - 1, cellctr, cellctr + 6));

		Cell monthCell = monthRow.createCell(cellctr);
		monthCell.setCellValue(months[M]);
		monthCell.setCellStyle(headerStyle);

		Row weekDay;
		// Month
		if (writableSheet.getRow(rowCtr) == null) {
			weekDay = writableSheet.createRow(rowCtr++);
		} else {
			weekDay = writableSheet.getRow(rowCtr++);
		}

		String[] weekNames = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
		Cell cell;
		int ctr = cellctr;
		for (String weekName : weekNames) {
			cell = weekDay.createCell(ctr++);
			cell.setCellValue(weekName);
			cell.setCellStyle(headerStyle);
		}

//		System.out.println("   " + months[M] + " " + Y);
//		System.out.println(" S  M Tu  W Th  F  S");
		// starting day
		int d = day(M, 1, Y);

		Row dayOut;
		// Month
		if (writableSheet.getRow(rowCtr) == null) {
			dayOut = writableSheet.createRow(rowCtr++);
		} else {
			dayOut = writableSheet.getRow(rowCtr++);
		}

		// print the calendar

		for (int i = 0; i < d; i++) {
			Cell dayOutCell = dayOut.createCell(cellctr++);
			dayOutCell.setCellValue(" ");
			dayOutCell.setCellStyle(borderStyle);
//			System.out.print("   ");
		}
		// System.out.print("   ");

		for (int i = 1; i <= days[M]; i++) {
//			System.out.printf("%2d ", i);
			Cell dayOutCell = dayOut.createCell(cellctr++);
			dayOutCell.setCellValue(i);
			dayOutCell.setCellStyle(borderStyle);

			if (((i + d) % 7 == 0) || (i == days[M])) {
//				System.out.println();
				cellctr = monthCoordinate.getY();
				if (writableSheet.getRow(rowCtr) == null) {
					dayOut = writableSheet.createRow(rowCtr++);
				} else {
					dayOut = writableSheet.getRow(rowCtr++);
				}
			}

		}

		CellRangeAddress address = new CellRangeAddress(
				monthCoordinate.getX() , rowCtr-2,
				monthCoordinate.getY(), monthCoordinate.getY() + 6);
		RegionUtil.setBorderTop(XSSFCellStyle.BORDER_MEDIUM, address,
				writableSheet, workbook);
		RegionUtil.setBorderLeft(XSSFCellStyle.BORDER_MEDIUM, address,
				writableSheet, workbook);
		RegionUtil.setBorderRight(XSSFCellStyle.BORDER_MEDIUM, address,
				writableSheet, workbook);
		RegionUtil.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM, address,
				writableSheet, workbook);
	}

	public static void main(String[] args) {
		new CalendarPrinter().printCalendar(2016);
	}
}
