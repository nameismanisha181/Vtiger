package testNG;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import GenericUtility.BaseClass;
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

public class CreateOrginizationUsingBaseClass extends BaseClass {

	@Test(groups = "regressionSuite")
	public void CreateOrganization() throws EncryptedDocumentException, IOException
	{   	   
	    
	    //Reading data from excel file    
	    String Orgnization = eUtil.getDataFromExcel("Organization", 1, 2)+jUtil.getRandomNumber();
	  
		//Click on Organization
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
		
		
	}
	@Test(groups = "regressionSuite")
	public void demo()
	{
		System.out.println("Demo method for regression");
	}
}
