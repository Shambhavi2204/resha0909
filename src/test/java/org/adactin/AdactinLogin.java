package org.adactin;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

public class AdactinLogin {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		AdactinBase a = new AdactinBase();
		
		WebDriver d = a.setDriver();		
		a.launch("http://adactinhotelapp.com/");
		a.sendkeysid("username",a.excelread(0, "Username"));
		a.sendkeysid("password",a.excelread(0, "Password"));
		a.clickId("login");
		a.selectbyindex("location", 2);
		a.selectbyindex("hotels", 3);
		a.selectbyindex("room_type",1);
		a.clickId("Submit");
		a.clickId("radiobutton_0");
		a.clickId("continue");
		a.sendkeysid("first_name", a.excelread(0, "First Name"));
		a.sendkeysid("last_name", a.excelread(0, "Last Name"));
		a.sendkeysid("address", a.excelread(0, "Billing Address"));
		a.sendkeysid("cc_num", a.excelread(0, "Credit Card No"));
		a.selectbyindex("cc_type", 2);
		a.selectbyindex("cc_exp_month", 4);
		a.selectbyindex("cc_exp_year", 4);
		a.sendkeysid("cc_cvv", a.excelread(0, "CVV Number"));
		a.clickId("book_now");
		Thread.sleep(5000);
		a.getvalue("order_no");
	
	
	}
}
