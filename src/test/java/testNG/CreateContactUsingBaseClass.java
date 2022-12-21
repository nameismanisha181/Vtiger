package testNG;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import GenericUtility.BaseClass;
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

@Listeners(GenericUtility.ListenerImplementationClass.class)
public class CreateContactUsingBaseClass extends BaseClass {

	
	@Test(groups = "SmokeSuite")
	public void CreateContactWithOrganization() throws EncryptedDocumentException, IOException
	{
	    
	    //Reading data from excel file   
	   String LastName = eUtil.getDataFromExcel("Contacts", 4, 2);
	   String OrgName=eUtil.getDataFromExcel("Contacts", 4, 3)+jUtil.getRandomNumber();
	   System.out.println(LastName);
	   System.out.println(OrgName);	   
	     		
		//Navigate to Organization link
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
		
	}
	@Test(groups = "SmokeSuite")
	public void demoSmoke()
	{
		System.out.println("Test for smoke");
	}
}
