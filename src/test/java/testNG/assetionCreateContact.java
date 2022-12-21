package testNG;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Test;

import GenericUtility.BaseClass;
import ObjectRepository.ContactInfo;
import ObjectRepository.ContactPage;
import ObjectRepository.CreateNewContactPage;
import ObjectRepository.CreateOrganizationPage;
import ObjectRepository.HomePage;
import ObjectRepository.OrganizationPage;
import ObjectRepository.OrgnisationInfoPage;

public class assetionCreateContact extends BaseClass{
	@Test(groups = "SmokeSuite")
	public void CreateContactWithOrganization() throws EncryptedDocumentException, IOException
	{
	    
	    //Reading data from excel file   
	   String LastName = eUtil.getDataFromExcel("Contacts", 4, 2);
	   String OrgName=eUtil.getDataFromExcel("Contacts", 4, 3)+jUtil.getRandomNumber();
	   System.out.println(LastName);
	   System.out.println(OrgName);	   
	     		
		//Navigate to Organization link
		HomePage hp = new HomePage(driver);
		hp.ClickOnOrgnization();
		
		//click on OrganizationLoopImage		
		OrganizationPage OP= new OrganizationPage(driver);
		OP.ClickCreateOrganizationImg();
		
		//Create Organization with mandatory details
		
		CreateOrganizationPage COP= new CreateOrganizationPage(driver);
		COP.CreateOrgnization(OrgName);
		
		//Validate Organization
		OrgnisationInfoPage OIP= new OrgnisationInfoPage(driver);
		String OrgHeader = OIP.getOrgnizationHeader();
		/*if(OrgHeader.contains(OrgName))
		{
			System.out.println("Organization created");
		}
		else
		{
			System.out.println("Organization not created");
		}*/
		
		//Using assertions instead if else
		Assert.assertTrue(OrgHeader.contains(OrgName));
		
		//click on Contacts link
		hp.ClickOnContacts();
		
		//click on contactLoopImage		
		ContactPage CP = new ContactPage(driver);
		CP.ClickOnLookUp();
		
		//Create New Contact
		CreateNewContactPage CNCP= new CreateNewContactPage(driver);
		CNCP.CreateNewContact(LastName, OrgName, driver);
		
		//Validate Contact is created
		ContactInfo CI = new ContactInfo(driver);
		String contactHeader=CI.getContactHeader();
		/*if(contactHeader.contains(LastName))
		{
			System.out.println("contact created");
		}
		else
		{
			System.out.println("Contact not created");
		}*/
		
		//Assert.assertTrue(contactHeader.contains(LastName), "Contact is created with last name");
		Assert.assertEquals(contactHeader.contains(LastName), true);
		
	}
}

