package testNG;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

public class ReadDataFromPro {

	@Test
	public void readData() throws IOException
	{
		FileInputStream fis = new FileInputStream("./resources/commonData.properties");
		
		Properties prop = new Properties();
		prop.load(fis);
		
		String BROWSER=prop.getProperty("browser");
		System.out.println(BROWSER);
		
		String UN=prop.getProperty("username");
		System.out.println(UN);
	}
}
