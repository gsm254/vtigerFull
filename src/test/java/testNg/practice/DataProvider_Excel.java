package testNg.practice;

import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import genericLibraries.ExcelUtility;

public class DataProvider_Excel {
	WebDriver driver;

	@DataProvider
	public Object[][] readDataFromExcel() {

		ExcelUtility eLib = new ExcelUtility();
		Sheet sh = eLib.getSheet("leads");
		int lastrow = sh.getLastRowNum();
		int lastcell = sh.getRow(0).getLastCellNum();
		//System.out.println(lastcell);
		Object[][] obj = new Object[lastrow][lastcell];
		
		  for (int i = 0; i <= lastrow; i++) { for (int j = 0; j < lastcell; j++) {
		  obj[i][j] = sh.getRow(i).getCell(j).toString(); }
		  
		  }
		 
		return obj;
	}

	@Test(dataProvider = "readDataFromExcel")
	public void dpTest(String sl, String fn, String ln, String cy, String mob) {
		System.out.println(sl + " " + fn + " " + ln + " " + cy + " " + mob);
	}
}
