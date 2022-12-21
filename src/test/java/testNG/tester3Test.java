package testNG;

import org.testng.annotations.Test;

public class tester3Test {
	@Test(invocationCount = 2, priority = 1)
	public void createCustomer()
	{
		System.out.println("Create Customer");
	}
	

	@Test
	public void updateCustomer()
	{
		System.out.println("Update Customer");
	}
	

	@Test
	public void deleteCustomer()
	{
		System.out.println("delete Customer");
	}
}
