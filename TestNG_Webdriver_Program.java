//W.a.maven program to create TestNG with Webdriver Program.
package com.mvn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNG_Webdriver_Program {
	WebDriver driver;
	
	@BeforeTest
	public void beforetest() throws InterruptedException{

		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.get("https://automationexercise.com/login");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		
	}
	@Test(priority = 1)
	public void password() throws InterruptedException {
		driver.findElement(By.name("password")).sendKeys("charmi@123");
		Thread.sleep(2000);

	}
	@Test(priority = 0)
	public void username() throws InterruptedException {
		driver.findElement(By.name("email")).sendKeys("charmi@gmail.com");
		Thread.sleep(2000);
		
	}
	@Test(priority = 2)
	public void login() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/button")).click();
		Thread.sleep(2000);
	}
	
	@AfterTest
	public void aftertest() throws InterruptedException{
	
	driver.close();
	}
	
}
