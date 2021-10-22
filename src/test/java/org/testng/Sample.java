package org.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Sample {
	
	public static WebDriver driver;
	@BeforeClass
	public void beforeClass() {
		
			WebDriverManager.chromedriver().setup();
			
			driver = new ChromeDriver();
			
			driver.manage().window().maximize();
			driver.get("https://adactinhotelapp.com/");
			
	}
	@Test
	public void test() {
		driver.findElement(By.id("username")).sendKeys("Shambhavi2204");
		driver.findElement(By.id("password")).sendKeys("shambhavi36");
		driver.findElement(By.id("login")).click();
	}

}
