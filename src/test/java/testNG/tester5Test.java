package testNG;

import org.testng.Assert;
import org.testng.annotations.Test;



public class tester5Test {
	@Test
	public void createCustomer()
	{
	    Assert.fail();
		System.out.println("Create Customer");
	}
	

	@Test(dependsOnMethods ="createCustomer" )
	public void updateCustomer()
	{
		System.out.println("Update Customer");
	}
	

	@Test(dependsOnMethods = {"createCustomer","updateCustomer"})
	public void deleteCustomer()
	{
		System.out.println("delete Customer");
	}
}
