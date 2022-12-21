package GenericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This is generic utility to read data from excel file
 * @author manis
 *
 */
public class ExcelFileUtility implements autoConstants{
/**
 * This method is accepting sheename,row and column and return data present in excel sheet
 * @param sheetName
 * @param row
 * @param col
 * @return
 * @throws EncryptedDocumentException
 * @throws IOException
 */
	public String getDataFromExcel(String sheetName,int row,int col) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis = new FileInputStream(autoConstants.EXCELFILEPATH);
		Sheet sheet = WorkbookFactory.create(fis).getSheet(sheetName);
		String data=sheet.getRow(row).getCell(col).getStringCellValue();
		return data;		
	}	
	
	public int getTotalNumberRows(String sheetName) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis = new FileInputStream(autoConstants.EXCELFILEPATH);
		Sheet sheet = WorkbookFactory.create(fis).getSheet(sheetName);
		int totalRows = sheet.getLastRowNum();
		return totalRows;
	}
	public void writeDataIntoExcel(String sheetName,int row,int cell,String data) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis = new FileInputStream(autoConstants.EXCELFILEPATH);
		Workbook wb = WorkbookFactory.create(fis);
		
		Sheet sh = wb.getSheet(sheetName);
		sh.getRow(row).getCell(cell).setCellValue(data);
		FileOutputStream fis1 = new FileOutputStream(autoConstants.EXCELFILEPATH);
		wb.write(fis1);
		wb.close();
		fis1.close();		
	}
	/**
	 * This method will return multiple data into data provider
	 * @param SheetName
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	
	public Object[][] readMultilpleDataIntoDataProvider(String SheetName) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis = new FileInputStream(autoConstants.EXCELFILEPATH);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(SheetName);
		int lastRow=sh.getLastRowNum(); //-----row
		int lastCell=sh.getRow(0).getLastCellNum();//......cell
		
		Object[][] data = new Object[lastRow][lastCell];
		
		for(int i=0;i<lastRow;i++)
		{
			for(int j=0;j<lastCell;j++)
			{
				data[i][j]=sh.getRow(i+1).getCell(j).getStringCellValue();
			}
		}
		return data;
	}
	
}

