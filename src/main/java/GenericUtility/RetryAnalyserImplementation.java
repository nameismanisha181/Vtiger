package GenericUtility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyserImplementation implements IRetryAnalyzer
{

	int Count=0;
	int retryCount=3;
	@Override
	public boolean retry(ITestResult result) {
		while(Count<retryCount)
		{
			Count++;
			return true;
		}
		return false;
	}
	
}
