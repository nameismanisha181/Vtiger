package testNG;

import org.testng.Assert;
import org.testng.annotations.Test;

import GenericUtility.BaseClass;

public class listerner2 extends BaseClass{
	@Test
	public void demo1()
	{
		System.out.println("Listner1");
		Assert.fail();
	}
}
