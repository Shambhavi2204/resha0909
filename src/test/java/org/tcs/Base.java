package org.tcs;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
		public static WebDriver driver;
		public WebElement element;
		public void setDriver() {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			
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
		
		public void launch(String data) {
			driver.get(data);
		}
		public String url() {
			String url = driver.getCurrentUrl();
			System.out.println("Url : " + url);
			return url;
		}
		public String title() {
			String title = driver.getTitle();
			System.out.println("Title : " + title);
			return title;
		}
		public void maximize() {
			driver.manage().window().maximize();
		}
		public void sendkeys(String path,String data) {
			WebElement element = driver.findElement(By.xpath(path));
			element.sendKeys(data);
		}
		
		public void sendkeysid(String path,String data) {
			WebElement element = driver.findElement(By.id(path));
			element.sendKeys(data);
		}
		
		public void sendkeysname(String path,String data) {
			WebElement element = driver.findElement(By.name(path));
			element.sendKeys(data);
		}
		
		public void sendkeysclassname(String path,String data) {
			WebElement element = driver.findElement(By.className(path));
			element.sendKeys(data);
		}
		
		public void clear(String path) {
			WebElement sb = driver.findElement(By.xpath(path));
			sb.clear();
		}
		public void clickId(String path) {
			WebElement element = driver.findElement(By.id(path));
			element.click();
		}

		public void clickName(String path) {
			WebElement element = driver.findElement(By.name(path));
			element.click();
		}
		public void clickClassName(String path) {
			WebElement element = driver.findElement(By.className(path));
			element.click();
		}
		public void clickXpath(String path) {
			WebElement element = driver.findElement(By.xpath(path));
			element.click();
		}
		public String getvalue(String path) {
			WebElement val = driver.findElement(By.xpath(path));
			String data = val.getAttribute("value");
			System.out.println("value : " + data);
			return data;
		}
		public String gettext(String path) {
			WebElement gtext = driver.findElement(By.xpath("path"));
			String text = gtext.getText();
			System.out.println(text);
			return text;
		}
		public void moveToElement(String path) {
			Actions ac = new Actions(driver);
			WebElement moa = driver.findElement(By.xpath(path));	
			ac.moveToElement(moa).perform();
		}
		
		public void draganddrop(String spath, String dpath) {
			Actions ac = new Actions(driver);
			WebElement source = driver.findElement(By.xpath(spath));
			WebElement dest = driver.findElement(By.xpath(dpath));
			ac.dragAndDrop(source, dest).perform();
		}
		public void rightclick(String path) {
			Actions ac = new Actions(driver);
			WebElement rctarget = driver.findElement(By.xpath(path));
			ac.contextClick(rctarget).perform();
		}
		public void doubleclick(String path) {
			Actions ac = new Actions(driver);
			WebElement dctarget = driver.findElement(By.xpath(path));
			ac.doubleClick(dctarget).perform();
		}
		public void sendKeysActions(String path, String keys) {
			Actions ac = new Actions(driver);
			WebElement sktarget = driver.findElement(By.xpath(path));
			ac.sendKeys(sktarget, keys);
		}
		public void robotclass() throws AWTException {
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_A);
			r.keyRelease(KeyEvent.VK_CONTROL);
			r.keyRelease(KeyEvent.VK_A);
			r.keyPress(KeyEvent.VK_BACK_SPACE);
			r.keyRelease(KeyEvent.VK_BACK_SPACE);
		}
		public void accept() {
			Alert al = driver.switchTo().alert();
			al.accept();
		}
		public void dismiss() {
			Alert al = driver.switchTo().alert();
			al.dismiss();
		}
		public String alertText() {
			Alert al = driver.switchTo().alert();
			String altext = al.getText();
			return altext;
		}
		public void alInsert(String data) {
			Alert al = driver.switchTo().alert();
			al.sendKeys("data");
		}	
		public void screenshot(String path, String location) throws IOException {
		TakesScreenshot sss = (TakesScreenshot)driver;
			WebElement  ss = driver.findElement(By.xpath(path));
			File s = ss.getScreenshotAs(OutputType.FILE);
			File d = new File(location);
			FileUtils.copyFile(s, d);
		}
		public void javaset(String path,String value) {
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			WebElement jsSet = driver.findElement(By.xpath(path));
			executor.executeScript("arguments[0].setAttribute('value', (value) )", jsSet);
		}
		public Object javaget(String path) {
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			WebElement jsget = driver.findElement(By.xpath(path));
			Object jsgettext = executor.executeScript("return arguments[0].getAttribute('value')", jsget);
			System.out.println(jsgettext);
			return jsgettext;
		}
		public void scrollDown(String path) {
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			WebElement jssd = driver.findElement(By.xpath(path));
			executor.executeScript("arguments[0].scrollIntoView(true)", jssd);
		}
		public void scrollUp(String path) {
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			WebElement jssu = driver.findElement(By.xpath(path));
			executor.executeScript("arguments[0].scrollIntoView(false)", jssu);
		}
		public void javaClick(String path) {
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			WebElement jsclick = driver.findElement(By.xpath(path));
			executor.executeScript("arguments[0].click()", jsclick);
		}
		public void selectbyindex(String path, int a) {
			WebElement ddnl = driver.findElement(By.xpath(path));
			Select se = new Select(ddnl);
			se.selectByIndex(a);
		}
		public void selectbyvalue(String path, String value) {
			WebElement ddnl = driver.findElement(By.xpath(path));
			Select se = new Select(ddnl);
			se.selectByValue(value);
		}
		public void selectbyvisibletext(String path, String value) {
			WebElement ddnl = driver.findElement(By.xpath(path));
			Select se = new Select(ddnl);
			se.selectByVisibleText(value);
		}
		public void getoptions(String path) {
			WebElement ddnl = driver.findElement(By.xpath(path));
			Select se = new Select(ddnl);
			List<WebElement> allop = se.getOptions();
		}
		public int allSize(String path) {
			WebElement ddnl = driver.findElement(By.xpath(path));
			Select se = new Select(ddnl);
			List<WebElement> allop = se.getOptions();
			int size = allop.size();
			System.out.println("size : " + size);
			return size;
		}
		public void selectbyindex1(String path, int a) {
			WebElement ddnl = driver.findElement(By.xpath(path));
			Select se = new Select(ddnl);
			List<WebElement> allop = se.getOptions();
			se.selectByIndex(a);
		}
		public void selectbyvalue1(String path, String value) {
			WebElement ddnl = driver.findElement(By.xpath(path));
			Select se = new Select(ddnl);
			List<WebElement> allop = se.getOptions();
			se.selectByValue(value);
		}
		public void selectbyvisibletext1(String path, String value) {
			WebElement ddnl = driver.findElement(By.xpath(path));
			Select se = new Select(ddnl);
			List<WebElement> allop = se.getOptions();
			se.selectByVisibleText(value);
		}
		public String getpartitextbyinndex(String path, int a) {
			WebElement ddnl = driver.findElement(By.xpath(path));
			Select se = new Select(ddnl);
			List<WebElement> allop = se.getOptions();
			WebElement partitext = allop.get(a);
			String optext = partitext.getText();
			System.out.println("the value is : " + optext);
			return optext;
		}
		public String getpartitextbyvalue(String path, int a) {
			WebElement ddnl = driver.findElement(By.xpath(path));
			Select se = new Select(ddnl);
			List<WebElement> allop = se.getOptions();
			WebElement partitext = allop.get(a);
			String optext = partitext.getAttribute("value");
			System.out.println("the value is : " + optext);
			return optext;
		}
		public String getalltextbyindex(String path) {
			WebElement ddnl = driver.findElement(By.xpath(path));
			Select se = new Select(ddnl);
			List<WebElement> allop = se.getOptions();
			int size = allop.size();
			for (int i = 0; i < size; i++) {
				WebElement allt = allop.get(i);
				String alltext = allt.getText();
				System.out.println(alltext);
				return alltext;
			}
			return path;	
		}
		public String getalloptionsbyvalue(String path) {
			WebElement ddnl = driver.findElement(By.xpath(path));
			Select se = new Select(ddnl);
			List<WebElement> allopp = se.getOptions();
			for (WebElement x : allopp) {
				String text = x.getText();
				System.out.println(text);
				return text;
			}
			return path;
		}
		public void selectallbyindex(String path) {
			WebElement ddnl = driver.findElement(By.xpath(path));
			Select se = new Select(ddnl);
			List<WebElement> allop = se.getOptions();
			int size = allop.size();
			for (int i = 0; i < size; i++) {
				se.selectByIndex(i);
			}
		}
		public void selectallbytext(String path) {
			WebElement ddnl = driver.findElement(By.xpath(path));
			Select se = new Select(ddnl);
			List<WebElement> allop = se.getOptions();
			for (WebElement allbyv : allop) {
				String y = allbyv.getText();
				se.selectByVisibleText(y);
			}
		}
		public void selectallbyvalue(String path) {
			WebElement ddnl = driver.findElement(By.xpath(path));
			Select se = new Select(ddnl);
			List<WebElement> allop = se.getOptions();
			for (WebElement allbyv : allop) {
				String y = allbyv.getAttribute("value");
				se.selectByValue(y);
			}
		}
		public String getallselectedoptions(String path) {
			WebElement ddnl = driver.findElement(By.xpath(path));
			Select se = new Select(ddnl);
			List<WebElement> allSelectedOptions = se.getAllSelectedOptions();
			for (WebElement z : allSelectedOptions) {
				String v = z.getText();
				System.out.println(v);
				return v;
			}
			return path;
		}
		public String get1stselectoption(String path) {
			WebElement ddnl = driver.findElement(By.xpath(path));
			Select se = new Select(ddnl);
			WebElement o = se.getFirstSelectedOption();
			String p = o.getText();
			System.out.println(p);
			return p;
		}
		public void deselectbyindex(String path, int a) {
			WebElement ddnl = driver.findElement(By.xpath(path));
			Select se = new Select(ddnl);
			List<WebElement> allop = se.getOptions();
			se.deselectByIndex(a);
		}
		public void deselectbyvalue(String path, String value) {
			WebElement ddnl = driver.findElement(By.xpath(path));
			Select se = new Select(ddnl);
			List<WebElement> allop = se.getOptions();
			se.deselectByValue(value);
		}
		public void deselectbyvisibletext(String path, String value) {
			WebElement ddnl = driver.findElement(By.xpath(path));
			Select se = new Select(ddnl);
			List<WebElement> allop = se.getOptions();
			se.deselectByVisibleText(value);
		}
		public void deselectall(String path) {
			WebElement ddnl = driver.findElement(By.xpath(path));
			Select se = new Select(ddnl);
			List<WebElement> allop = se.getOptions();
			se.deselectAll();
		}
		public int framecount(String tagname) {
			List<WebElement> fc = driver.findElements(By.tagName(tagname));
			int framecount = fc.size();
			System.out.println(framecount);
			return framecount;
		}
		public void swithchtoframebynameorid(String idorname) {
			driver.switchTo().frame(idorname);
		}
		public void switchtoframebywebelement(String path) {
			WebElement frameelement = driver.findElement(By.xpath(path));
			driver.switchTo().frame(frameelement);
		}
		public void swicthtoframebyindex(int index) {
			driver.switchTo().frame(index);
		}
		public String getparentwindow() {
			String parent = driver.getWindowHandle();
			System.out.println(parent);
			return parent;
		}
		public void getallwindow() {
			Set<String> allwindow = driver.getWindowHandles();
			System.out.println(allwindow);
		}
		public void switchto() {
			String parent = driver.getWindowHandle();
			Set<String> allwindoww = driver.getWindowHandles();
			for (String each : allwindoww) {
				if(!(parent.equals(each))){
					driver.switchTo().window(each);
				}
			}
		}
		public void switchtobyindex(int index) {
			String parent = driver.getWindowHandle();
			Set<String> allwindoww = driver.getWindowHandles();
			List<String> listwindowsid = new ArrayList<String>();
			listwindowsid.addAll(allwindoww);
			String child = listwindowsid.get(index);
			driver.switchTo().window(child);
		}
		public void switchtoparent() {
//			String parent = driver.getWindowHandle();
//			driver.switchTo().window(parent);
		}
		public void table(String path, String tagname ) {
			WebElement table = driver.findElement(By.xpath(path));
			
			 if(tagname=="thead"){
				WebElement head = table.findElement(By.tagName(tagname));
			
				WebElement hr = head.findElement(By.tagName("tr"));
			
				List<WebElement> headings = hr.findElements(By.tagName("th"));
					for (WebElement x : headings) {
						String data = x.getText();
						System.out.print(data+"          ");
					}
			 	}if(tagname=="tbody") {
			 		WebElement body = table.findElement(By.tagName("tbody"));
			
			 		List<WebElement> rows = body.findElements(By.tagName("tr"));
				
			 		for (int i = 0; i < rows.size(); i++) {
						WebElement r = rows.get(i);
						List<WebElement> td = r.findElements(By.tagName("td"));
						System.out.println("\t\t");
						System.out.println("---------------------------------------------------");
						for (int j = 0; j < td.size(); j++) {
							WebElement d = td.get(j);
							String data = d.getText();
							System.out.print(data+"\t");
						}
			 		}
			 	}
			}
		public String excelread(String location, String sheetname, int rowindex, int cellindex, String dateformat) throws IOException {
			File f = new File(location);
			FileInputStream fis = new FileInputStream(f);
			Workbook wb = new XSSFWorkbook(fis);
			Sheet sh = wb.getSheet(sheetname);
			Row row = sh.getRow(rowindex);
			Cell cell = row.getCell(cellindex);
			int type = cell.getCellType();
			if(type==1) {
				String data = cell.getStringCellValue();
				System.out.println(data);
				return data;
			}
			if(type==0) {
				if(DateUtil.isCellDateFormatted(cell)) {
					Date date = cell.getDateCellValue();
					SimpleDateFormat df = new SimpleDateFormat(dateformat);
					String data = df.format(date);
					System.out.println(data);
					return data;
				}else {
					double d = cell.getNumericCellValue();
					long l = (long)d;
					String data = String.valueOf(l);
					System.out.println(data);
					return data;
				}
			}
			return sheetname;
		}
		public void close() {
			driver.close();
		}
		public void quit() {
			driver.quit();
		}
	
	
	}
	
	

