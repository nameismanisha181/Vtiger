package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtility.WebDriverUtility;

public class CreateOrganizationPage extends WebDriverUtility{

	public CreateOrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "accountname")
	private WebElement OrgNameEdt;
	
	@FindBy(xpath =  "//input[@title='Save [Alt+S]']")
	private WebElement SaveBtn;
	
	@FindBy(xpath = "//Select[@name='industry']")
	private WebElement IndustryDropDown;;
	/**
	 * This method will create organization with mandatory field
	 */
	public void CreateOrgnization(String OrgName)
	{
		OrgNameEdt.sendKeys(OrgName);
		SaveBtn.click();
	}
	/**
	 * This method will create organization with industry type dropdown
	 */
	
	public void CreateOrgnization(String OrgName,String IndustryType)
	{
		OrgNameEdt.sendKeys(OrgName);
		handleDropDown(IndustryDropDown, IndustryType);
		SaveBtn.click();
	}
	
	
}
