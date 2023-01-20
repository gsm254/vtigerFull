package dataProviderAnnotation;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class DataProviderDemo {
	@ExcelDataSourceInfo(sheetName = 1, filePath = "")
	@Test(dataProvider = "dp")
	public void test1(String data) {
		System.err.println("String received " + data);
	}

	@ExcelDataSourceInfo(sheetName = 2, filePath = "")
	@Test(dataProvider = "dp")
	public void test2(String data) {
		System.err.println("Integer received " + data);
	}
	
	@DataProvider(name = "dp")
	public Object[][] genericDataProvider(Method method) {
		ExcelDataSourceInfo info = method.getAnnotation(ExcelDataSourceInfo.class);
		int sheetName = info.sheetName();
		switch(sheetName) {
		case 1:
		{
			Object obj[][] = {{"testng","junit","junit4"}};
			return  obj;
		}
			
			
		case 2:
			Object obj[][] = {{"testng","junit","junit4"}};
			return  obj;
		
		}
		
			Object obj[][] = {{"testng","junit","junit4"}};
			return  obj;
		
	}
}
