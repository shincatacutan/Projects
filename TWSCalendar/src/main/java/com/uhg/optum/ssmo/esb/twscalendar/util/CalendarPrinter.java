package com.uhg.optum.ssmo.esb.twscalendar.util;

import java.util.List;

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
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import com.uhg.optum.ssmo.esb.twscalendar.domain.CalendarDay;

public class CalendarPrinter {

	public static void printMonth(XSSFWorkbook workbook,
			XSSFSheet writableSheet, int M, int Y, List<CalendarDay> list) {
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

		MonthCoordinates monthCoordinates = MonthCoordinates.valueOf(months[M]);

		XSSFCellStyle borderStyle = normalCell(workbook);
		XSSFCellStyle headerStyle = headerBorder(workbook);
		XSSFCellStyle runDayBorder = runDayCell(workbook);
		XSSFCellStyle holidayBorder = holidayCell(workbook);
		XSSFCellStyle sundayBorder = sundayCell(workbook);
		XSSFCellStyle headerSundayStyle = sundayHeaderCell(workbook);

		XSSFCellStyle specialCellA = specialCellStyleA(workbook);
		XSSFCellStyle specialCellB = specialCellStyleB(workbook);

		int rowCtr = monthCoordinates.getX();
		int cellctr = monthCoordinates.getY();

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
		// Week
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
			if (weekName.equals("Sun")) {
				cell.setCellStyle(headerSundayStyle);
			} else {
				cell.setCellStyle(headerStyle);
			}
		}

		// starting day
		int d = day(M, 1, Y);

		Row dayOut;
		// days
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
		}

		for (int i = 1; i <= days[M]; i++) {
			Cell dayOutCell = dayOut.createCell(cellctr++);
			dayOutCell.setCellValue(i);

			LocalDate thisDate = new LocalDate(Y, M, i);

			for (CalendarDay cd : list) {
				if (cd.getCalDay().equals(thisDate)) {
					if (cd.isHoliday()) {
						dayOutCell.setCellStyle(holidayBorder);
					} else if (ProjectConstants.SPCL_A.equals(cd.getSpecialJob())) {
						dayOutCell.setCellStyle(specialCellA);
					} else if (ProjectConstants.SPCL_B.equals(cd.getSpecialJob())) {
						dayOutCell.setCellStyle(specialCellB);
					} else {
						dayOutCell.setCellStyle(runDayBorder);
					}

					break;
				} else {
					if (thisDate.getDayOfWeek() == DateTimeConstants.SUNDAY) {
						dayOutCell.setCellStyle(sundayBorder);
					} else {
						dayOutCell.setCellStyle(borderStyle);
					}
				}

			}

			if (((i + d) % 7 == 0) || (i == days[M])) {
				cellctr = monthCoordinates.getY();
				if (writableSheet.getRow(rowCtr) == null) {
					dayOut = writableSheet.createRow(rowCtr++);
				} else {
					dayOut = writableSheet.getRow(rowCtr++);
				}
			}
		}

		CellRangeAddress address = new CellRangeAddress(
				monthCoordinates.getX(), rowCtr - 2, monthCoordinates.getY(),
				monthCoordinates.getY() + 6);
		RegionUtil.setBorderTop(XSSFCellStyle.BORDER_MEDIUM, address,
				writableSheet, workbook);
		RegionUtil.setBorderLeft(XSSFCellStyle.BORDER_MEDIUM, address,
				writableSheet, workbook);
		RegionUtil.setBorderRight(XSSFCellStyle.BORDER_MEDIUM, address,
				writableSheet, workbook);
		RegionUtil.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM, address,
				writableSheet, workbook);
	}

	public static XSSFFont boldHeaderFont(XSSFWorkbook workbook) {
		XSSFFont boldHeader = workbook.createFont();
		boldHeader.setBold(true);
		return boldHeader;
	}

	public static XSSFFont runDayFont(XSSFWorkbook workbook) {
		XSSFFont runday = workbook.createFont();
		runday.setBold(true);
		runday.setColor(HSSFColor.LIGHT_GREEN.index);

		return runday;
	}
	
	public static XSSFFont specialDayFont(XSSFWorkbook workbook) {
		XSSFFont runday = workbook.createFont();
		runday.setBold(true);
		runday.setColor(HSSFColor.GOLD.index);

		return runday;
	}

	public static XSSFFont holidayFont(XSSFWorkbook workbook, boolean isHoliday) {
		XSSFFont holidayFont = workbook.createFont();
		holidayFont.setBold(isHoliday);
		holidayFont.setColor(HSSFColor.RED.index);
		return holidayFont;
	}

	public static XSSFFont titleFont(XSSFWorkbook workbook) {
		XSSFFont titleFont = workbook.createFont();
		titleFont.setBold(true);
		titleFont.setColor(HSSFColor.WHITE.index);
		titleFont.setFontHeightInPoints((short) 18);

		return titleFont;
	}

	public static XSSFCellStyle holidayCell(XSSFWorkbook workbook) {
		XSSFCellStyle cell = workbook.createCellStyle();
		cell.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
		cell.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cell.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cell.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cell.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cell.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cell.setAlignment(XSSFCellStyle.ALIGN_CENTER);

		cell.setFont(holidayFont(workbook, true));
		return cell;
	}

	public static XSSFCellStyle headerBorder(XSSFWorkbook workbook) {
		XSSFCellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headerStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		headerStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		headerStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
		headerStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
		headerStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);

		headerStyle.setFont(boldHeaderFont(workbook));
		return headerStyle;
	}

	public static XSSFCellStyle sundayHeaderCell(XSSFWorkbook workbook) {
		XSSFCellStyle cell = workbook.createCellStyle();
		cell.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		cell.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cell.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cell.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cell.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cell.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cell.setBorderLeft(XSSFCellStyle.BORDER_THIN);

		cell.setFont(holidayFont(workbook, true));
		return cell;
	}

	public static XSSFCellStyle specialCellStyleA(XSSFWorkbook workbook) {
		XSSFCellStyle cell = workbook.createCellStyle();
		cell.setFillForegroundColor(HSSFColor.RED.index);
		cell.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cell.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cell.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cell.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cell.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cell.setBorderLeft(XSSFCellStyle.BORDER_THIN);

		cell.setFont(specialDayFont(workbook));
		return cell;
	}
	
	public static XSSFCellStyle specialCellStyleB(XSSFWorkbook workbook) {
		XSSFCellStyle cell = workbook.createCellStyle();
		cell.setFillForegroundColor(HSSFColor.INDIGO.index);
		cell.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cell.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cell.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cell.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cell.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cell.setBorderLeft(XSSFCellStyle.BORDER_THIN);

		cell.setFont(specialDayFont(workbook));
		return cell;
	}

	public static XSSFCellStyle normalCell(XSSFWorkbook workbook) {
		XSSFCellStyle cell = workbook.createCellStyle();
		cell.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cell.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cell.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cell.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cell.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		return cell;
	}

	public static XSSFCellStyle runDayCell(XSSFWorkbook workbook) {
		XSSFCellStyle cell = workbook.createCellStyle();
		cell.setFillForegroundColor(HSSFColor.GREEN.index);
		cell.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cell.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cell.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cell.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cell.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cell.setAlignment(XSSFCellStyle.ALIGN_CENTER);

		cell.setFont(runDayFont(workbook));
		return cell;
	}

	public static XSSFCellStyle sundayCell(XSSFWorkbook workbook) {
		XSSFCellStyle cell = workbook.createCellStyle();
		cell.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cell.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cell.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cell.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cell.setAlignment(XSSFCellStyle.ALIGN_CENTER);

		cell.setFont(holidayFont(workbook, false));
		return cell;
	}

	public static XSSFCellStyle titleStyle(XSSFWorkbook workbook) {
		XSSFCellStyle cell = workbook.createCellStyle();
		cell.setFillForegroundColor(HSSFColor.BLACK.index);
		cell.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cell.setFont(titleFont(workbook));
		return cell;
	}

	public static int day(int M, int D, int Y) {
		int y = Y - (14 - M) / 12;
		int x = y + y / 4 - y / 100 + y / 400;
		int m = M + 12 * ((14 - M) / 12) - 2;
		int d = (D + x + (31 * m) / 12) % 7;
		return d;
	}

	// return true if the given year is a leap year
	public static boolean isLeapYear(int year) {
		if ((year % 4 == 0) && (year % 100 != 0))
			return true;
		if (year % 400 == 0)
			return true;
		return false;
	}

}
