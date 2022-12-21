
package vtiger.scripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

//Create New Organization 

public class CreateOrgnization {

public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
	//	driver.manage().timeouts().dura(20,TimeUnit.SECONDS);
		driver.get("http://localhost:8888/index.php?action=Login&module=Users");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		driver.findElement(By.name("accountname")).sendKeys("Organization6");
		driver.findElement(By.name("website")).sendKeys("www.tester1.com");
		driver.findElement(By.xpath("//input[@name='assigntype' and @value='T']")).click();
		driver.findElement(By.id("phone")).sendKeys("111111111");
		//driver.findElement(By.id("email1")).sendKeys("tester1.gmail.com");
		
		WebElement RatingDropdown = driver.findElement(By.name("rating"));
		Select S1= new Select(RatingDropdown);
		S1.selectByValue("Active");
		driver.findElement(By.name("bill_street")).sendKeys("23456 Dr test test ");
		driver.findElement(By.id("bill_city")).sendKeys("miami");
		driver.findElement(By.id("bill_state")).sendKeys("Florida");
		driver.findElement(By.name("description")).sendKeys("This is to test create orgnization flow");
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]' and @name='button'])[1]")).click();
		//driver.findElement(By.xpath("//td[@class='genHeaderSmall']/following-sibling::td[1]")).click();
		//driver.findElement(By.xpath("/html/body/table[1]/tbody/tr/td[3]/table/tbody/tr/td[2]/img")).click();
		Thread.sleep(2000);
		driver.close();
		
}
}
