package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrgnisationInfoPage {

	public OrgnisationInfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement Orgnizationherder;

	public WebElement getOrgnizationherder() {
		return Orgnizationherder;
	}
	public String getOrgnizationHeader()
	{
		return Orgnizationherder.getText();
	}
}
