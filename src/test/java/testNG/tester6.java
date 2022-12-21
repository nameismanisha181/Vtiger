package testNG;


import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class tester6 {
	@Test
	public void createCustomer()
	{
		System.out.println("Create Customer");
	}
	
    @Ignore
	@Test()
	public void updateCustomer()
	{
		System.out.println("Update Customer");
	}
	

	@Test(enabled = false)
	public void deleteCustomer()
	{
		System.out.println("delete Customer");
	}
}
