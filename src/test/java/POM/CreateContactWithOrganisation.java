package POM;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import GenericUtility.ExcelFileUtility;
import GenericUtility.JavaUtility;
import GenericUtility.PropertyFileUtility;
import GenericUtility.WebDriverUtility;
import ObjectRepository.ContactInfo;
import ObjectRepository.ContactPage;
import ObjectRepository.CreateNewContactPage;
import ObjectRepository.CreateOrganizationPage;
import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;
import ObjectRepository.OrganizationPage;
import ObjectRepository.OrgnisationInfoPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactWithOrganisation {

	//WebDriver driver=null;
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
	   String LastName = eUtil.getDataFromExcel("Contacts", 4, 2);
	   String OrgName=eUtil.getDataFromExcel("Contacts", 4, 3)+jUtil.getRandomNumber();
	   System.out.println(LastName);
	   System.out.println(OrgName);	   
	     
	   
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
		
		//Navigate to Orgnization link
		HomePage hp = new HomePage(driver);
		hp.ClickOnOrgnization();
		
		//click on OrganizationLoopImage		
		OrganizationPage OP= new OrganizationPage(driver);
		OP.ClickCreateOrganizationImg();
		
		//Create Organization with mandatory details
		
		CreateOrganizationPage COP= new CreateOrganizationPage(driver);
		COP.CreateOrgnization(OrgName);
		
		//Validate Organization
		OrgnisationInfoPage OIP= new OrgnisationInfoPage(driver);
		String OrgHeader = OIP.getOrgnizationHeader();
		if(OrgHeader.contains(OrgName))
		{
			System.out.println("Organization created");
		}
		else
		{
			System.out.println("Organization not created");
		}
		
		//click on Contacts link
		hp.ClickOnContacts();
		
		//click on contactLoopImage		
		ContactPage CP = new ContactPage(driver);
		CP.ClickOnLookUp();
		
		//Create New Contact
		CreateNewContactPage CNCP= new CreateNewContactPage(driver);
		CNCP.CreateNewContact(LastName, OrgName, driver);
		
		//Validate Contact is created
		ContactInfo CI = new ContactInfo(driver);
		String contactHeader=CI.getContactHeader();
		if(contactHeader.contains(LastName))
		{
			System.out.println("contact created");
		}
		else
		{
			System.out.println("Contact not created");
		}
		
		//Logout 
		hp.Logout(driver);
	}
}







