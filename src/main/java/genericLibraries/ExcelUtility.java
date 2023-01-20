package genericLibraries;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ExcelUtility extends JavaUtility{
	
	
	Workbook book;

	

	{
		try {
			FileInputStream fis = new FileInputStream(IPathConstants.EXCELPATH);
			book = WorkbookFactory.create(fis);

		} catch (Exception e) {

		}
	}

	public String readDataFromExcel(String sheet, int row, int column) {
		Sheet sh = book.getSheet(sheet);
		Row ro = sh.getRow(row);
		Cell cel = ro.getCell(column);
		String value = cel.toString();

		return value;

	}

	public void writeDataIntoExcel(String sheet, int row, int column, String data) throws IOException {
		Sheet sh = book.getSheet(sheet);
		Row ro = sh.createRow(row);
		Cell cel = ro.createCell(column);
		cel.setCellValue(data);

		FileOutputStream fo = new FileOutputStream(IPathConstants.EXCELPATH);
		book.write(fo);
		book.close();

	}
	
	public Sheet getSheet(String sheet) {
		return book.getSheet(sheet);
	}

	public int getLastRowNo(String sheet) {
		Sheet sh = book.getSheet(sheet);
		int count = sh.getLastRowNum();
		return count;
	}

	public void getList(String sheet, int keyCell, int valueCell,WebDriver driver) {
		Sheet sh = book.getSheet(sheet);
		int count = sh.getLastRowNum();
		//System.out.println(count);

		Map<String, String> map = new HashMap<String, String>();

		for (int i = 0; i <= count; i++) {

			String key = sh.getRow(i).getCell(keyCell).getStringCellValue();
			String value = sh.getRow(i).getCell(valueCell).toString();

			map.put(key, value);
		}
		int ran = getRandomNum();
		for (Entry<String, String> e : map.entrySet()) {
			String key = e.getKey();
			String value = e.getValue();

			driver.findElement(By.name(key)).sendKeys(value+ran);
		}
		//return map;
	}

}
