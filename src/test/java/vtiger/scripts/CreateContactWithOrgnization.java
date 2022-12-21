package vtiger.scripts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactWithOrgnization {
	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriver driver = null;
		// Fetch Common data from Property file
		FileInputStream fis = new FileInputStream("./resources/commonData.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String BROWSER = prop.getProperty("browser");
		String URL = prop.getProperty("url");
		String ID = prop.getProperty("username");
		String PWD = prop.getProperty("password");

		// Fetch data from excel
		FileInputStream fis1 = new FileInputStream("./resources/testData.xlsx");
		Sheet Contacts = WorkbookFactory.create(fis1).getSheet("Contacts");
		String OrgName = Contacts.getRow(4).getCell(3).getStringCellValue();

		String LastName = Contacts.getRow(4).getCell(2).getStringCellValue();

		System.out.println(LastName);
		// String LastName =
		// WorkbookFactory.create(fis1).getSheet("Contacts").getRow(4).getCell(2).getStringCellValue();
		// Launching browser
		if (BROWSER.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else {
			throw new IllegalArgumentException("Enter valid browser");
		}
		
		//Login to application
		driver.manage().window().maximize();
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(ID);
		driver.findElement(By.name("user_password")).sendKeys(PWD);
		driver.findElement(By.id("submitButton")).click();

		// Create Organization
		driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(OrgName);
		driver.findElement(By.xpath("//input[@name='assigntype' and @value='T']")).click();
		driver.findElement(By.id("phone")).sendKeys("111111111");
		// driver.findElement(By.id("email1")).sendKeys("tester1.gmail.com");
		WebElement RatingDropdown = driver.findElement(By.name("rating"));
		Select S1 = new Select(RatingDropdown);
		S1.selectByValue("Active");
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]' and @name='button'])[1]")).click();

		Thread.sleep(3000);

		// Create Contact
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(LastName);

		driver.findElement(By.xpath("(//img[@alt='Select' and @title='Select'])[1]")).click();

		Thread.sleep(3000);
		String pid = driver.getWindowHandle();
		Set<String> ids = driver.getWindowHandles();
		for (String S : ids) 
		{
			if(!S.equals(pid))
			{
			driver.switchTo().window(S);
			System.out.println(S);
			driver.switchTo().activeElement().sendKeys(OrgName);
			driver.findElement(By.name("search")).click();
			}
		}
			
		

	}
}
