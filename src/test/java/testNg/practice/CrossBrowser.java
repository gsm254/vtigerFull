package testNg.practice;

import org.testng.Reporter;
import org.testng.annotations.Test;

import genericLibraries.BaseClass;

public class CrossBrowser extends BaseClass{

	@Test
	public void chrome() {
		Reporter.log("--chrome abba!!!--",true);
	}
	@Test
	public void firefox() {
		Reporter.log("--firefox abba!!!--",true);
	}
}
