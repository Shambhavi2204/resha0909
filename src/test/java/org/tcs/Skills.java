package org.tcs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Skills {
	
	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://demo.automationtesting.in/Register.html");
		
		
		WebElement dDnSkills = driver.findElement(By.id("Skills"));
		Select select = new Select(dDnSkills);

		List<WebElement> opt = select.getOptions();
		
		File file = new File("C:\\Users\\Kishore\\Desktop\\Software testing\\Eclipse_workspace\\Framework\\src\\test\\java\\org\\tcs\\Excel sheets\\ExcelShhet\\AutoSkillFile.xlsx");

		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet();
		
		for (int i = 0; i < opt.size(); i++) {
			
			WebElement elem = opt.get(i);
			String txt = elem.getText();
							
			Row row = sheet.createRow(i);
		

			Cell cell = row.createCell(0);
			cell.setCellValue(txt);

		}	
			
		FileOutputStream op = new FileOutputStream(file);
		wb.write(op);
		
		
		System.out.println("Done......");
	}

}
