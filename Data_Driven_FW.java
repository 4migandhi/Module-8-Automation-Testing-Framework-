/*W.a.framework program for data driven framework-to get value from the excel 
and check into your website (http://automationpractice.com/index.php?controller
 * =authentication&b ack=my-account) Direct check with login which emaild through
 *  login successful or not)*/
package com.Framework;

import java.io.File;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.zip.DataFormatException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.ClickAction;
import org.testng.annotations.Test;


public class Data_Driven_FW {
	
	public String[][] readData() throws IOException, InvalidFormatException {
		String[][] data = null;
		
		String filepath ="D:\\Automation_Testing\\selenium\\data1.xlsx";
		
		File file = new File(filepath);
		
		XSSFWorkbook workbook=new XSSFWorkbook(file);
		
		Sheet sheet = workbook.getSheet("sheet1");
		
		int nrow=sheet.getPhysicalNumberOfRows();
		
		System.out.println("no of rows is" +nrow);
		
		data=new String[nrow][];
		
		for (int i = 0; i < data.length; i++) {
			
			Row row= sheet.getRow(i);
			
			int ncell = row.getPhysicalNumberOfCells();
			System.out.println("No of col is :"+ncell);
			
			data[i]=new String[ncell];
			
			for (int j = 0; j < data[i].length; j++) {
				
				Cell cell=row.getCell(j);
				
				cell.setCellType(CellType.STRING);
				
				data[i][j] = cell.getStringCellValue();				
			}
		}
		
		return data;
	}
	
	WebDriver driver;
	@Test
	public void test() throws InterruptedException, 
							IOException, InvalidFormatException {
		
		String[][] data = readData();
		
	System.setProperty("webdriver.edge.driver", 
			"D:\\Automation_Testing\\selenium\\msedgedriver.exe");
	
	for(int i=0;i<data.length;i++){
	
	driver = new EdgeDriver();
	
	driver.get("https://secure1.inmotionhosting.com/index/login");
	
	driver.manage().window().maximize();
	Thread.sleep(2000);
	
	//For Implicitly wait
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	
	driver.findElement(By.id("username")).sendKeys(data[i][0]);
	Thread.sleep(2000);
	
	driver.findElement(By.id("password")).sendKeys(data[i][1]);
	Thread.sleep(2000);
	
	//checkbox coding
	/*driver.findElement(By.xpath("//*[@id=\"recaptcha-anchor\"]/div[1]")).click();
	Thread.sleep(2000);*/
	
	driver.findElement(By.xpath("//div[@class='recaptcha-checkbox-border']")).click();
	Thread.sleep(2000);
	
	driver.findElement(By.id("login-submit")).click();
	Thread.sleep(10000);
	
	driver.close();
	}
	}
}

