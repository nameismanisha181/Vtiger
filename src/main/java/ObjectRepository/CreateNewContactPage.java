package ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtility.WebDriverUtility;

public class CreateNewContactPage extends WebDriverUtility {

	@FindBy(name = "lastname")
	private WebElement LastName;
	
	@FindBy(name = "leadsource")
	private WebElement leadSourceDropDown;
	
	@FindBy(xpath = "//input[@name='account_name']/following-sibling::img[@title='Select']")
	private WebElement OrgnizationLookUpImage;
	
	@FindBy(name = "search_text")
	private WebElement SearchEdt;
	
	@FindBy(name = "search")
	private WebElement SearchBtn;
	
	@FindBy(xpath =  "//input[@title='Save [Alt+S]']")
	private WebElement SaveBtn;

	public CreateNewContactPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	public WebElement getLastName() {
		return LastName;
	}

	public WebElement getLeadSourceDropDown() {
		return leadSourceDropDown;
	}

	public WebElement getOrgnizationLookUpImage() {
		return OrgnizationLookUpImage;
	}

	public WebElement getSearchEdt() {
		return SearchEdt;
	}

	public WebElement getSearchBtn() {
		return SearchBtn;
	}

	public WebElement getSaveBtn() {
		return SaveBtn;
	};
	/**
	 * This method will create contact with mandatory fields
	 * @param lastName
	 */
	
	public void CreateNewContact(String lastName)
	{
		LastName.sendKeys(lastName);
		SaveBtn.click();
	}
	
	public void CreateNewContact(String lastName,String OrgName,WebDriver driver)
	{
		LastName.sendKeys(lastName);
		OrgnizationLookUpImage.click();
		switchToWindow(driver, "Accounts");
		SearchEdt.sendKeys(OrgName);
		SearchBtn.click();
		driver.findElement(By.xpath("//a[text()='"+OrgName+"']")).click();
		switchToWindow(driver, "Contacts");
		SaveBtn.click();			
	}
	
	
	
	
	
}
