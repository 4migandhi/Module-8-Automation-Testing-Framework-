package com.Framework;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Hybrid_Driven_FW {
	@DataProvider(name = "charmi")
	public Object[][] readData() throws InvalidFormatException, IOException {
		Object[][] data = null;
		
		//1. to give file path
		String filepath = "D:\\Automation_Testing\\selenium\\data1.xlsx";
		
		//2. to make file
		File file = new File(filepath);
		
		//3. to open excle file
		XSSFWorkbook workbook=new XSSFWorkbook(file);
		
		//4. to open sheet
		Sheet sheet=workbook.getSheet("sheet3");
		
		//5. to get row
		int nrow=sheet.getPhysicalNumberOfRows();
		
		System.out.println("no of rows is" + nrow);
		
		data=new Object[nrow][];
		
		for (int i = 0; i < data.length; i++) {
			
			//6. to select particular row
			Row row= sheet.getRow(i);
			
			//7. to get col
			
			int ncell = row.getPhysicalNumberOfCells();
			System.out.println("No of col is :"+ncell);
			
			data[i]=new Object[ncell];
			
			for (int j = 0; j < data[i].length; j++) {
				
				//8. to select a particular cell
				Cell cell=row.getCell(j);
				
				//9.to set all value of string
				
				cell.setCellType(CellType.STRING);
				
				//10.to get cell value
				
				data[i][j] = cell.getStringCellValue();				
			}
			
		}		
	
		return data;
	}
	
	WebDriver driver;
	
	@Test(dataProvider = "charmi")
	public void test(String keyword,String data) throws 
				InterruptedException, InvalidFormatException, IOException {
		
		
	System.setProperty("webdriver.edge.driver", 
			"D:\\Automation_Testing\\selenium\\msedgedriver.exe");
	
	
	if(keyword.equals("open browser")) {
		driver = new EdgeDriver();
	}else if (keyword.equals("enter url")) {
		driver.get(data);
		
		driver.manage().window().maximize();
		Thread.sleep(2000);
	}else if (keyword.equals("click signin")){
		driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[4]/a")).click();
		Thread.sleep(2000);
	}else if (keyword.equals("enter email")) {

		driver.findElement(By.name("email")).sendKeys(data);
		Thread.sleep(2000);
	}else if (keyword.equals("enter password")) {
		
		driver.findElement(By.name("password")).sendKeys(data);
		Thread.sleep(2000);
	}else if (keyword.equals("click login")) {
		
		driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/button")).click();
		Thread.sleep(2000);
	}else if (keyword.equals("close browser")) {
		
		Thread.sleep(2000);
		driver.close();
	}
	}

}
