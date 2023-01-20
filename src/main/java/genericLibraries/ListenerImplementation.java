package genericLibraries;

import java.io.IOException;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerImplementation implements ITestListener {

	ExtentReports report;
	ExtentTest test;

	/**
	 * execution start from here
	 */
	public void onTestStart(ITestResult result) {
		String methodName = result.getName();
		test = report.createTest(methodName);
		Reporter.log(methodName + "--TestScript execution started");

	}

	/**
	 * 
	 */
	public void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		test.log(Status.PASS, methodName + "--Passed");
		Reporter.log(methodName + "--TestScript execution successful");
	}

	/**
	 * report when skipped
	 */
	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		test.log(Status.SKIP, methodName + "--Skipped");
		Reporter.log("--TestScript Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
	}

	/**
	 * Configure the extent report
	 */
	public void onStart(ITestContext context) {
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter("./ExtentReports/report.html");
		htmlReporter.config().setDocumentTitle("SDET-45 extent report");
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setReportName("Vtiger report");

		report = new ExtentReports();
		report.attachReporter(htmlReporter);
		//report.setSystemInfo("Base Browser", "Chrome");
		report.setSystemInfo("Base-Url", "http://rmgtestingserver:8888");
		report.setSystemInfo("Reporter Name", "Gowtham");
		

	}

	/**
	 * Consolidate the report
	 */
	public void onFinish(ITestContext context) {
		RemoteWebDriver rdriver = (RemoteWebDriver) BaseClass.driver;
		Capabilities capa = rdriver.getCapabilities();
		report.setSystemInfo("Base Browser", capa.getBrowserName()+capa.getVersion());
		report.setSystemInfo("OS",System.getProperty("os.version"));

		report.flush();
	}

	public void onTestFailure(ITestResult result) {
		String path = null;
		try {
			String meth = result.getMethod().getMethodName();
			System.out.println(meth);
			path = BaseClass.wLib.getScreenshot(meth);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(path);
		test.log(Status.FAIL, result.getThrowable());
		Reporter.log("--TestScript Failed");

	}

}
