package vTigerScriptsUsingUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import GenericUtility.ExcelFileUtility;
import GenericUtility.JavaUtility;
import GenericUtility.PropertyFileUtility;
import GenericUtility.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrgnizationUsingUtility {
public static void main(String[] args) throws InterruptedException, IOException {
	
	WebDriver driver = null;
	//Creating objects of classes from utility      
	ExcelFileUtility eUtil= new ExcelFileUtility();
    PropertyFileUtility pUtil = new PropertyFileUtility();
    JavaUtility jUtil= new JavaUtility();
    WebDriverUtility wUtil= new WebDriverUtility();
    
   //Read data from property file
    String BROWSER=pUtil.readDataFromPropertyFile("browser");
    String URL=pUtil.readDataFromPropertyFile("url");
    String USERNAME=pUtil.readDataFromPropertyFile("username");
    String PASSWORD=pUtil.readDataFromPropertyFile("password");
    
    System.out.println(USERNAME);
    System.out.println(PASSWORD);
    
    //Reading data from excel file    
    String Orgnization = eUtil.getDataFromExcel("Organization", 1, 2)+jUtil.getRandomNumber();
    
	if (BROWSER.equalsIgnoreCase("chrome")) {//Using runtime polymorphism 
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	} else if (BROWSER.equalsIgnoreCase("firefox")) {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
	} else {
		throw new IllegalArgumentException("Enter valid browser");
	}
	wUtil.maximizeWindow(driver);
	wUtil.waitForPageLoad(driver);
	driver.get(URL);
	
	//Login to application
	driver.findElement(By.name("user_name")).sendKeys(USERNAME);
	driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
	driver.findElement(By.id("submitButton")).click();
	
	//Click on Organization link
	driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
	
	//Click on Create Organization lookup link 
	driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
	
	//Create Organization with mandatory fields
	driver.findElement(By.name("accountname")).sendKeys(Orgnization);
	driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
    
	//Logout
	WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
	wUtil.mouseOverAction(driver, ele);
	//Click on SignOut
	driver.findElement(By.xpath("//a[text()='Sign Out']")).click(); 
    
}
}