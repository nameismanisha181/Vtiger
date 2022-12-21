package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfo {

	public ContactInfo(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement ContactHeaderTxt;
	
	public WebElement getContactHeaderTxt() {
		return ContactHeaderTxt;
	}
	
	public String getContactHeader()
	{
		return ContactHeaderTxt.getText();
	}
	
}
