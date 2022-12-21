package GenericUtility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {

	public void maximizeWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	}

	public void minimizeWindow(WebDriver driver)
	{
		driver.manage().window().minimize();
	}
	/**
	 * This method will wait entire page load for 20 seconds
	 */
	public void waitForPageLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	/**
	 * This method will wait for element to be visible
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeVissible(WebDriver driver,WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}
	
	/**
	 * This method will wait for element to be clickable
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeClickable(WebDriver driver,WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/**
	 * This is customer wait for second to wait for element to be clickable 
	 * @param element
	 * @throws InterruptedException
	 */
	
	public void customeWaitForSeconds(WebElement element) throws InterruptedException
	{
		int count=0;
		while(count<5)
		{
			try
			{
				element.click();
				break;
			}
			catch (Exception e) 
			{
				Thread.sleep(1000);
				count++;
			}
		}
	}
	
	public void handleDropDown(WebElement element,int index)
	{
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}
	public void handleDropDown(WebElement element,String value)
	{
		Select sel = new Select(element);
		sel.selectByValue(value);
	}
	
	public void handleDropDown(String vissibleText,WebElement element)
	{
		Select sel = new Select(element);
		sel.selectByVisibleText(vissibleText);
	}
	public void mouseOverAction(WebDriver driver,WebElement element)
	{
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}
	/**
	 * This method will perform mouse hover action based on offset
	 * @param driver
	 * @param element
	 * @param x
	 * @param y
	 */
	public void mouseOverAction(WebDriver driver,WebElement element,int x,int y)
	{
		Actions action = new Actions(driver);
		action.moveToElement(element, x, y).perform();
	}
	public void rightclickAction(WebDriver driver)
	{
		Actions action = new Actions(driver);
		action.contextClick().perform();
	}
	public void rightClickAction(WebDriver driver,WebElement element)
	{
		Actions action = new Actions(driver);
		action.contextClick(element).perform();
	}
	
	public void doubleClick(WebDriver driver)
	{
		Actions action = new Actions(driver);
		action.doubleClick().perform();
	}
	
	public void doubleClick(WebDriver driver,WebElement element)
	{
		Actions action = new Actions(driver);
		action.doubleClick(element);
	}
	public void pressEnterKey() throws AWTException
	{
		Robot rv= new Robot();
		rv.keyPress(KeyEvent.VK_ENTER);
		rv.keyRelease(KeyEvent.VK_ENTER);
	}
	public void switchToFrame(WebDriver driver,int index)
	{
		driver.switchTo().frame(index);
	}
	public void switchToFrame(WebDriver driver,String nameOrID)
	{
		driver.switchTo().frame(nameOrID);
	}
	public void switchToFrame(WebDriver driver,WebElement frameElement)
	{
		driver.switchTo().frame(frameElement);
	}
	public void switchToDefaultFrmame(WebDriver driver)
	{
		driver.switchTo().defaultContent();
	}
	public void scrollActions(WebDriver driver)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,500)","");
	}
	public void scrollActions(WebDriver driver,WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		 int y = element.getLocation().getX();
		 js.executeScript("window.scrollBy(0,"+y+")", element);
	}
	
	public String getScreenShot(WebDriver driver,String screenShotName) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./ScreenShot/"+screenShotName+".png");	
		FileUtils.copyFile(src, dest);
		
		return dest.getAbsolutePath();
		
	}
	/**
	 * This method will switch to particular window base on title
	 * @param driver
	 * @param ParticularwindowTitle
	 */
	
	public void switchToWindow(WebDriver driver ,String ParticularwindowTitle)
	{
		Set<String> allwindows = driver.getWindowHandles();
		for(String IndividualWindow:allwindows)
		{
			String currentTitle=driver.switchTo().window(IndividualWindow).getTitle();
			if(currentTitle.contains(ParticularwindowTitle))
			{
				break;
			}
		}
	}
		
}