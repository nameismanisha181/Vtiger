package ObjectRepository;

import javax.management.loading.PrivateClassLoader;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtility.WebDriverUtility;

public class HomePage extends WebDriverUtility {

	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	@FindBy(linkText = "Organizations")
	private WebElement OrganizationLnk;
	
	@FindBy(linkText = "Contacts")
	private WebElement Contacts;
	
	@FindBy(linkText = "Opportunities")
	private WebElement Opportunities;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement AdministratorImg;
	
	@FindBy(linkText = "Sign Out")
	private WebElement SignOut;

	public WebElement getOrganizationLnk() {
		return OrganizationLnk;
	}

	public WebElement getContacts() {
		return Contacts;
	}

	public WebElement getOpportunities() {
		return Opportunities;
	}

	public WebElement getAdministratorImg() {
		return AdministratorImg;
	}
	/**
	 * This method will click on Organization
	 */
	public void ClickOnOrgnization()
	{
		OrganizationLnk.click();
	}
	/**
	 * This method will click on Organization
	 */
	
	public void ClickOnContacts()
	{
		Contacts.click();
	}
	/**
	 * This method will sign out from application
	 * @param driver
	 */
	public void Logout(WebDriver driver)
	{
		mouseOverAction(driver,AdministratorImg);
		SignOut.click();		
	}

}
