package vtiger.scripts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class property {
public static void main(String[] args) throws IOException {
	
	FileInputStream fis = new FileInputStream("./src/test/java/commonData.properties");
	
	Properties prop = new Properties();
	prop.load(fis);
	
	String url=prop.getProperty("browser");
	System.out.println(url);
	
	
}
}
