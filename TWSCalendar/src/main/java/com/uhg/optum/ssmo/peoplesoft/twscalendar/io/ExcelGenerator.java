package com.uhg.optum.ssmo.peoplesoft.twscalendar.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelGenerator {
	
	private final static Logger logger = LoggerFactory
			.getLogger(ExcelGenerator.class);
	public String generate(String jobname, int year) {
		 //Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();
         
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet(jobname);
          
        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put("1", new Object[] {"ID", "NAME", "LASTNAME"});
        data.put("2", new Object[] {1, "Shin", "Catacutan"});
        data.put("3", new Object[] {2, "Test", "TEST"});
        data.put("4", new Object[] {3, "Test", "TEST"});
          
        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset)
        {
            Row row = sheet.createRow(rownum++);
            Object [] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr)
            {
               Cell cell = row.createCell(cellnum++);
               if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }	
		
		Date date = new Date();
		String timestamp = new Timestamp(date.getTime()).toString()
				.replace(".", "-").replace(":", "-").replace(" ", "_")
				.replace("-", "");
		String fileName = "TWSCalendar_" + timestamp+".xlsx";
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
