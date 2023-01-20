package genericLibraries;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryImplementaion implements IRetryAnalyzer {
	int count = 0;
	int limit = 3;

	public boolean retry(ITestResult result) {
		if (count < limit) {
			count++;
			return true;
		}
		return false;
	}

}
