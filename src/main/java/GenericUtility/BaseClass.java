package GenericUtility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This class consists of basic configuration annotations
 * @author manis
 *
 */
public class BaseClass
{
	public WebDriver driver = null;
	public ExcelFileUtility eUtil= new ExcelFileUtility();
	public PropertyFileUtility pUtil= new PropertyFileUtility();
	public JavaUtility jUtil= new JavaUtility();
	public WebDriverUtility wUtil = new WebDriverUtility();
	public static WebDriver Sdriver=null;//Declare Sdriver as static so that can access in listeners
	
    @BeforeSuite(groups = {"regressionSuite","SmokeSuite"})
	public void bsConfig()
	{
		System.out.println("-------------DataBase Connection Sucessfull--------------");
	}
       
  @BeforeClass(groups = {"regressionSuite","SmokeSuite"})
    public void bcConfig() throws IOException
    {
    	String BROWSER = pUtil.readDataFromPropertyFile("browser");
    	String URL = pUtil.readDataFromPropertyFile("url");
    	if(BROWSER.equalsIgnoreCase("chrome"))
    	{
    		WebDriverManager.chromedriver().setup();
    		driver=new ChromeDriver();
    		System.out.println("Browser"+BROWSER+ " is Launch");    	
    		
    	}
    	else if(BROWSER.equalsIgnoreCase("firefox"))
    	{
    		WebDriverManager.firefoxdriver().setup();
    		driver=new FirefoxDriver();
    		System.out.println(" Browser "+BROWSER+ "is Launch");    	
    		
    	}
    	else
    	{
    		System.out.println("Invalid browser");
    	}
    	Sdriver=driver;//assigning driver value to listeners 
    	wUtil.maximizeWindow(driver);
    	wUtil.waitForPageLoad(driver);
    	driver.get(URL);
    }
    @BeforeMethod(groups = {"regressionSuite","SmokeSuite"})
    public void bmConfig() throws IOException
    {
    	String USERNAME = pUtil.readDataFromPropertyFile("username");
    	String PASSWORD = pUtil.readDataFromPropertyFile("password");
    	
    	LoginPage lp = new LoginPage(driver);
    	lp.Login(USERNAME, PASSWORD);
    	System.out.println("-------Login to Application successfull------");
    }
    @AfterMethod(groups = {"regressionSuite","SmokeSuite"})
    public void amConfig()
    {
    	HomePage hp = new HomePage(driver);
    	hp.Logout(driver);
    	System.out.println("------LogOut from Application is successfull--------");
    }
    @AfterClass(groups = {"regressionSuite","SmokeSuite"})
    public void acConfig()
    {
    	driver.quit();
    	System.out.println("--------Browser is closed--------");
    }
    @AfterSuite(groups = {"regressionSuite","SmokeSuite"})
    public void auConfig()
    {
    	System.out.println("Connection is closed");
    }
}
