package testNG;

import org.junit.Assert;
import org.testng.annotations.Test;

public class retryAnalyser {

	@Test(retryAnalyzer = GenericUtility.RetryAnalyserImplementation.class)
	public void demo1()
	{
		System.out.println("From demo1");
		Assert.fail();
	}
}
