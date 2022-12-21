package vTigerScriptsUsingUtility;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import GenericUtility.ExcelFileUtility;
import GenericUtility.JavaUtility;
import GenericUtility.PropertyFileUtility;
import GenericUtility.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactUsingOrganization {

	public static void main(String[] args) throws IOException, InterruptedException {
		 WebDriver driver = null;
		 
		//Step1-Create Objects for all utility classes
	     PropertyFileUtility pUtil = new PropertyFileUtility();
	     ExcelFileUtility eUtil = new ExcelFileUtility();
	     JavaUtility jUtil = new JavaUtility();
	     WebDriverUtility wUtil= new WebDriverUtility();
	     
	     //Step-2Fetch required data from property file and excel file	     
	     String BROWSER = PropertyFileUtility.readDataFromPropertyFile("browser");
	     String URL = PropertyFileUtility.readDataFromPropertyFile("url");
	     String USERID = PropertyFileUtility.readDataFromPropertyFile("username");
	     String PASSWORD = PropertyFileUtility.readDataFromPropertyFile("password");
	     
	     System.out.println(USERID+PASSWORD);
	     String lastName = eUtil.getDataFromExcel("Contacts", 4, 2);
	     String Organization = eUtil.getDataFromExcel("Contacts", 4, 3)+jUtil.getRandomNumber();
	     System.out.println(Organization);
	     System.out.println(lastName);
		
	     //Step-3Launch browser
	     //Run time polymorphism
	     
	     if(BROWSER.equalsIgnoreCase("chrome"))
	     {
	    	 WebDriverManager.chromedriver().setup();
	    	 driver= new ChromeDriver();	    	 
	     }
	     else if(BROWSER.equalsIgnoreCase("firefox"))
	     {
	    	 WebDriverManager.firefoxdriver().setup();
	    	 driver = new FirefoxDriver();
	     }
	     else 
	     {
	    	 throw new IllegalArgumentException("Please select valid browser");
	     }	     
	     wUtil.maximizeWindow(driver);
	     driver.get(URL);
	     
	     //Login to application
	     driver.findElement(By.name("user_name")).sendKeys(USERID);
	     driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
	     driver.findElement(By.id("submitButton")).submit();
	     
	     //Navigate to Organization
	     driver.findElement(By.linkText("Organizations")).click();
	     
	     //Click on Create Organization lookup image
	     driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
	     
	     //Create Organization
	     driver.findElement(By.name("accountname")).sendKeys(Organization);
	     driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	     
	     Thread.sleep(3000);
	     
	     //Validate Organization is created
	     String OrganizationHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	     if(OrganizationHeader.contains(Organization))
	     {
	    	 System.out.println("Organization created successfully");
	     }
	     else
	     {
	    	 System.out.println("Organization creation failed");
	     }
	     
	     //Move to Contacts
	     driver.findElement(By.linkText("Contacts")).click();
	     
	     //Click on Contacts lookUp image
	     driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
	     
	     //Create Contact with mandatory fields
	     driver.findElement(By.name("lastname")).sendKeys(lastName);
	     
	     //Click on Organization lookpUp image
	     driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img[@alt='Select']")).click();
	     
	     
	     //Switch controll to organization window
	     wUtil.switchToWindow(driver, "Accounts");
	     driver.findElement(By.name("search_text")).sendKeys(Organization);
	     driver.findElement(By.name("search")).click();
	     
	     //Dynamic x-path generated at run time
	     driver.findElement(By.xpath("//a[text()='"+Organization+"']")).click();
	     
	     //Step 14: Switch the control back to parent window
	     wUtil.switchToWindow(driver, "Contacts");
	     
	     //Save
	     driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	     
	     //Validate for contacts
	     
	     String ContactHeader = (driver.findElement(By.xpath("//span[@class='dvHeaderText']"))).getText();
	     {
	    	 if(ContactHeader.contains(lastName))
	    	 {
	    		 System.out.println(ContactHeader);
	    		 System.err.println("PASS::Contact created ");
	    	 }
	     
	         else
	         {
	    	 System.out.println("FAILED");
	         }
	     }
	     
	}
}












