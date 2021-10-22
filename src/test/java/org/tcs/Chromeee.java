package org.tcs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Chromeee {

	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		
//		System.setProperty("webdriver.chrome.driver", 
//				"C:\\Users\\Kishore\\Desktop\\Software testing\\Eclipse_workspace\\Framework\\Chrome94\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://en-gb.facebook.com/");
		
		WebElement txtUser = driver.findElement(By.id("email"));
		txtUser.sendKeys("monisha.shambhavi@gmail.com");
		
		WebElement txtpass = driver.findElement(By.id("pass"));
		txtpass.sendKeys("shambhavi36");

		WebElement btnlgn = driver.findElement(By.name("login"));
		btnlgn.click();
	
	
	}
}
