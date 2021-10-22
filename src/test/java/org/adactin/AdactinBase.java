package org.adactin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
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

public class AdactinBase {

	public static WebDriver driver;
	public WebElement element;
	public WebDriver setDriver() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		return driver;
	}
	public WebElement locator(String locator, String value) {
		if(locator.equals("id")) {
			WebElement element = driver.findElement(By.id(value));
			return element;
		}
		else if(locator.equals("name")) {
			WebElement element = driver.findElement(By.name(value));
			return element;
		}
		else if(locator.equals("xpath")) {
			WebElement element = driver.findElement(By.xpath(value));
			return element;
		}
		return element;
	}
	public String excelread(int rowindex, String string) throws IOException {
		List<LinkedHashMap<String,String>> l = new ArrayList<LinkedHashMap<String,String>>();
		File f = new File("C:\\Users\\Kishore\\Desktop\\Software testing\\Eclipse_workspace\\Framework\\src\\test\\java\\org\\adactin\\Excel Sheet\\Adactin login.xlsx");
		FileInputStream fis = new FileInputStream(f);
		Workbook wb = new XSSFWorkbook(fis);
		Sheet sh = wb.getSheet("sheet1");
		for (int i = 1; i < sh.getPhysicalNumberOfRows(); i++) {
			LinkedHashMap<String,String> mp = new LinkedHashMap<String,String>();
			String value = "";
			Row r = sh.getRow(i);
			for (int j = 0; j < r.getPhysicalNumberOfCells(); j++) {
				Cell c = r.getCell(j);
				int type = c.getCellType();
				if (type==1) {
					value = c.getStringCellValue();
					}
				else {
			
					if(DateUtil.isCellDateFormatted(c)) {
						Date d = c.getDateCellValue();
						SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yy");
						value = df.format(d);
					}
					else {
					
					double dob = c.getNumericCellValue();
					long lg = (long)dob;
					value = String.valueOf(lg);
					}											
				}

				String key = sh.getRow(0).getCell(j).getStringCellValue();
				mp.put(key, value);
				l.add(mp);
			}
			
			LinkedHashMap<String,String> op = l.get(rowindex);
		String p = op.get(string);
		return p;
		}
		return null;
	}
	public String getalloptionsbyvalue(String path, int cellindex) throws IOException {
//		List<LinkedHashMap<String,String>> l = new ArrayList<LinkedHashMap<String,String>>();
		File f = new File("C:\\Users\\Kishore\\Desktop\\Software testing\\Eclipse_workspace\\Framework\\src\\test\\java\\org\\adactin\\Excel Sheet\\Adactin login.xlsx");
		FileInputStream fis = new FileInputStream(f);
		Workbook wb = new XSSFWorkbook(fis);
		Sheet sh = wb.getSheet("sheet1");
		
		WebElement ddnl = driver.findElement(By.id(path));
		Select se = new Select(ddnl);
		List<WebElement> allopt = se.getOptions();
		
		for (int i = 0; i < allopt.size(); i++) {
			
			WebElement elem = allopt.get(i);
			String txt = elem.getText();
							
			Row row = sh.createRow(i);
		

			Cell cell = row.createCell(cellindex);
			cell.setCellValue(txt);
		}
		FileOutputStream op = new FileOutputStream(f);
		wb.write(op);
		return path;
	}		
	public void selectbyvisibletext(String path, String value) {
		WebElement ddnl = driver.findElement(By.id(path));
		Select se = new Select(ddnl);
		se.selectByVisibleText(value);
	}
				
		public void launch(String data) {
		driver.get(data);
	}

	public void maximize() {
		driver.manage().window().maximize();
	}
	
	public void sendkeysid(String path,String data) {
		WebElement element = driver.findElement(By.id(path));
		element.sendKeys(data);
	}
	
	public void selectbyindex(String path, int a) {
		WebElement ddnl = driver.findElement(By.id(path));
		Select se = new Select(ddnl);
		se.selectByIndex(a);
	}
	
	
	public void clickId(String path) {
		WebElement element = driver.findElement(By.id(path));
		element.click();
	}

	public String getvalue(String path) {
		WebElement val = driver.findElement(By.id(path));
		String data = val.getAttribute("value");
		System.out.println(data);
		return data;
	}
	
	
	
	
	
	
	
	
	
	
	
}
