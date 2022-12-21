

package GenericUtility;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerImplementationClass implements ITestListener
{

	ExtentReports report;
	ExtentTest test;
	
	@Override
	public void onTestStart(ITestResult result) {
		
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName+"Test case started");
		test=report.createTest(methodName);
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		//System.out.println(methodName+"Test case passed");
		test.log(Status.PASS, methodName+"-->Success");
		
	}
	
//We have to take screenshot upon failure.So will call screenshot method here
	@Override
	public void onTestFailure(ITestResult result) {
		WebDriverUtility wUtil= new WebDriverUtility();
		JavaUtility jUtil= new JavaUtility();
		
		String methodName = result.getMethod().getMethodName();
	//	System.out.println(methodName+"Test case failed");
		test.log(Status.FAIL, methodName+"-->Failed");
		System.out.println(result.getThrowable());//to print error exception
		String ScreenshotName = methodName+jUtil.dataStampFormat();//Screenshot updated 
				
		try 
		{
			wUtil.getScreenShot(BaseClass.Sdriver, ScreenshotName);
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Need driver as first parameter 
		//taking driver from Base class as execution begins from base class
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
	//	System.out.println(methodName+"Test case execution skipped");
		test.log(Status.SKIP, methodName+"-->Skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {
		
		System.out.println("Execution started :: Before Suite");
		//Create Object of ExtendSparkReporter
		
		ExtentSparkReporter htmpReport=new ExtentSparkReporter("./ExtendReport/Report-"+new JavaUtility().dataStampFormat());
		htmpReport.config().setTheme(Theme.DARK);
		htmpReport.config().setReportName("VtigerExecutionReport");
		//attach html report to Extent Report
		report = new ExtentReports();
		report.attachReporter(htmpReport);report.setSystemInfo("Operating System", "Windows");
		report.attachReporter(htmpReport);report.setSystemInfo("BaseBrowser", "Firefox");
		report.attachReporter(htmpReport);report.setSystemInfo("User", "Manisha");
		report.attachReporter(htmpReport);report.setSystemInfo("url", "http://localhost:8888/index.php?action=index&module=Home");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Execution finished::After Suite");
		report.flush();//working like assertAll
	}

	
}
