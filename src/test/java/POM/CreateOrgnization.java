package POM;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import GenericUtility.ExcelFileUtility;
import GenericUtility.JavaUtility;
import GenericUtility.PropertyFileUtility;
import GenericUtility.WebDriverUtility;
import ObjectRepository.CreateOrganizationPage;
import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;
import ObjectRepository.OrganizationPage;
import ObjectRepository.OrgnisationInfoPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrgnization {
public static void main(String[] args) throws EncryptedDocumentException, IOException {
	
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
	LoginPage lp = new LoginPage(driver);
	lp.Login(USERNAME, PASSWORD);
	//Click on Organization link
	HomePage hp = new HomePage(driver);
	hp.ClickOnOrgnization();
	
	//Click on Create Organization lookup link 
	OrganizationPage OP= new OrganizationPage(driver);
	OP.ClickCreateOrganizationImg();
	
	//Create Organization with mandatory fields
	CreateOrganizationPage COP= new CreateOrganizationPage(driver);
	COP.CreateOrgnization(Orgnization);
    
	//Validation
	OrgnisationInfoPage OIFO= new OrgnisationInfoPage(driver);
	String orgHeader = OIFO.getOrgnizationHeader();
	if(orgHeader.contains(Orgnization))
	{
		System.out.println("PASSED:: Organisation created");
	}
	else
	{
		System.out.println("FAILED");
	}
	//Logout
	hp.Logout(driver);
	
    
}
}
