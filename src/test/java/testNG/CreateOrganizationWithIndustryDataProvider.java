package testNG;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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

public class CreateOrganizationWithIndustryDataProvider {
	//Creating objects of classes from utility      
			ExcelFileUtility eUtil= new ExcelFileUtility();
		    PropertyFileUtility pUtil = new PropertyFileUtility();
		    JavaUtility jUtil= new JavaUtility();
		    WebDriverUtility wUtil= new WebDriverUtility();
	
	@DataProvider(name="OrgData")
	public Object[][] getData() throws EncryptedDocumentException, IOException
	{
		return eUtil.readMultilpleDataIntoDataProvider("MultipleOrganization");
	}
	@Test(dataProvider = "OrgData")
	public void createOrganization(String Org,String IndustryDropDown) throws IOException
	{
		WebDriver driver = null;
		
		String Orginisation = Org+jUtil.getRandomNumber();
	   //Read data from property file
	    String BROWSER=PropertyFileUtility.readDataFromPropertyFile("browser");
	    String URL=pUtil.readDataFromPropertyFile("url");
	    String USERNAME=pUtil.readDataFromPropertyFile("username");
	    String PASSWORD=pUtil.readDataFromPropertyFile("password");
	    
	    System.out.println(USERNAME);
	    System.out.println(PASSWORD);
	    
	    
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
		COP.CreateOrgnization(Orginisation, IndustryDropDown);
	    
		//Validation
		OrgnisationInfoPage OIFO= new OrgnisationInfoPage(driver);
		String orgHeader = OIFO.getOrgnizationHeader();
		if(orgHeader.contains(Orginisation))
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
