package testNG;

import org.testng.Assert;
import org.testng.annotations.Test;

public class assertionPractice {

	@Test
	public void assertPractice()
	{
		System.out.println(" Step 1");
		System.out.println(" Step 2");
		System.out.println(" Step 3");
		Assert.assertEquals(true, true);
		System.out.println(" Step 5");
		System.out.println(" Step 6");
		System.out.println(" Step 7");
		
	}
}
