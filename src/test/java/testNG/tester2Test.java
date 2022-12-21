package testNG;

import org.testng.annotations.Test;

public class tester2Test {
	@Test(priority = 1)
	public void createCustomer()
	{
		System.out.println("Create Customer");
	}
	

	@Test(priority = -2)
	public void updateCustomer()
	{
		System.out.println("Update Customer");
	}
	

	@Test(priority = -1)
	public void deleteCustomer()
	{
		System.out.println("delete Customer");
	}
}
