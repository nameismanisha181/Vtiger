package vTigerScriptsUsingUtility;

import java.io.IOException;

import GenericUtility.ExcelFileUtility;
import GenericUtility.PropertyFileUtility;
import GenericUtility.autoConstants;

public class GenericUtilityPractice {
public static void main(String[] args) throws IOException {
	//Accessing url using PropertyFileUtility
	
	PropertyFileUtility pUtil= new  PropertyFileUtility();
	String URL = PropertyFileUtility.readDataFromPropertyFile("url");
	System.out.println(URL);
	
	//Accessing username using PropertyFileUtility
	
	String UN = pUtil.readDataFromPropertyFile("username");
	System.out.println("UserName is " +UN);
	
	
	
	//accessing data from excel
	
	ExcelFileUtility eUtil = new ExcelFileUtility();
	String data = eUtil.getDataFromExcel("Organization", 1, 2);
	System.out.println(data);
	
	//getting total number of row
	int totalRows = eUtil.getTotalNumberRows("Organization");
	System.out.println("Total rows" +totalRows);
	
	//write To excel file
	eUtil.writeDataIntoExcel("Organization", 2, 1, "Manisha");
}
}
