package testNG;

import org.testng.annotations.Test;

public class tester4Test {
	@Test
	public void createCustomer()
	{
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
