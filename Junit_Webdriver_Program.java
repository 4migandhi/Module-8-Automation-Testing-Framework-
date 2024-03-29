//W.a.maven program to create Junit with webdriver Program
package com.mvn;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Junit_Webdriver_Program {
	WebDriver driver;
	
	@Before
	public void before() throws InterruptedException{

		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.get("https://automationexercise.com/login");
		driver.manage().window().maximize();
		Thread.sleep(2000);
	}
	@Test
	public void test() throws InterruptedException{
		
		driver.findElement(By.name("email")).sendKeys("charmi@gmail.com");
		Thread.sleep(2000);
		
		driver.findElement(By.name("password")).sendKeys("charmi@123");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/button")).click();
		Thread.sleep(2000);
		
	}
	@After
	public void after() throws InterruptedException{
	
	driver.close();
	}
}
