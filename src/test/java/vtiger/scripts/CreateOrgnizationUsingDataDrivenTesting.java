package vtiger.scripts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrgnizationUsingDataDrivenTesting {
	public static void main(String[] args) throws IOException, InterruptedException {

		// Read common data from property file
		WebDriver driver = null;
		FileInputStream fis = new FileInputStream("./resources/commonData.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String BROSWSER = prop.getProperty("browser");
		String URL = prop.getProperty("url");
		String UN = prop.getProperty("username");
		String PWD = prop.getProperty("password");

		// Read Data from Excel

		FileInputStream fis1 = new FileInputStream("./resources/testData.xlsx");
		Sheet Sheet = WorkbookFactory.create(fis1).getSheet("Organization");
		String Orgnization = Sheet.getRow(1).getCell(2).getStringCellValue();
		System.out.println(Orgnization);

		if (BROSWSER.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (BROSWSER.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else {
			throw new IllegalArgumentException("Enter valid browser");
		}

		driver.manage().window().maximize();

		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(UN);
		driver.findElement(By.name("user_password")).sendKeys(PWD);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		driver.findElement(By.name("accountname")).sendKeys(Orgnization);
		driver.findElement(By.name("website")).sendKeys("www.tester1.com");
		driver.findElement(By.xpath("//input[@name='assigntype' and @value='T']")).click();
		driver.findElement(By.id("phone")).sendKeys("111111111");

		WebElement RatingDropdown = driver.findElement(By.name("rating"));
		Select S1 = new Select(RatingDropdown);
		S1.selectByValue("Active");
		driver.findElement(By.name("bill_street")).sendKeys("23456 Dr test test ");
		driver.findElement(By.id("bill_city")).sendKeys("miami");
		driver.findElement(By.id("bill_state")).sendKeys("Florida");
		driver.findElement(By.name("description")).sendKeys("This is to test create orgnization flow");
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]' and @name='button'])[1]")).click();

		Thread.sleep(2000);
		driver.close();

	}
}