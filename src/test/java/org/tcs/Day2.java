package org.tcs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Day2 {
	public static void main(String[] args) throws IOException {
		File file = new File("C:\\Users\\Kishore\\Desktop\\Software testing\\Eclipse_workspace\\Framework\\src\\test\\java\\org\\tcs\\Excel sheets\\Record.xlsx");
				
		FileInputStream stream = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(stream);
		Sheet sheet = workbook.getSheet("sheet1");
		
		for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
			Row row = sheet.getRow(i);
			System.out.println(i);
			
			for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
				
				Cell cell2 = row.getCell(j);
				
				int type = cell2.getCellType();
				if (type==1) {
					
					String data = cell2.getStringCellValue();
					System.out.println(data);
					
					}
				if (type==0) {
					double d = cell2.getNumericCellValue();
					long l = (long)d;
					
					String val = String.valueOf(l);
					System.out.println(val);
											
				}
				
				
						
			}
				
			}
	
			
	}


}
