package GenericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * This class contains generic methods related to Property File
 * @author manis
 *
 */
public class PropertyFileUtility {
	/**
	 * This generic method will read key from property file and return key
	 * @throws IOException
	 */
	public static String readDataFromPropertyFile(String key) throws IOException
	{
		FileInputStream fis = new FileInputStream("./resources/commonData.properties");
		Properties prop= new Properties();
		prop.load(fis);
		String keyValue=prop.getProperty(key);
		return keyValue;
	}

}


