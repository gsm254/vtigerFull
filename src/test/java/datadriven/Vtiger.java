package datadriven;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Vtiger {

	public static void main(String[] args) throws IOException {
		System.setProperty("webdriver.chrome.driver", "./softwares/chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		
		

		FileInputStream fis = new FileInputStream("./src/test/java/datadriven/common.properties");

		Properties prop = new Properties();

		prop.load(fis);

		String url = prop.getProperty("url");
		String user = prop.getProperty("user");
		String pwd = prop.getProperty("pwd");

		FileInputStream fie = new FileInputStream("C:\\Users\\G\\Desktop\\data.xlsx");

		Workbook book = WorkbookFactory.create(fie);

	 Sheet sheet = book.getSheet("Sheet2");

		driver.get(url);

		driver.findElement(By.name("user_name")).sendKeys(user);
		driver.findElement(By.name("user_password")).sendKeys(pwd);
		driver.findElement(By.id("submitButton")).click();

		driver.findElement(By.xpath("//a[.='Organizations'][1]")).click();

		for (int i = 1; i <= sheet.getLastRowNum(); i++) 
		{

			Cell cell = sheet.getRow(i).getCell(0);

			String orgname = cell.getStringCellValue();
			
			driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
			driver.findElement(By.name("accountname")).sendKeys(orgname);
			driver.findElement(By.xpath("(//input[@value='  Save  '])[1]")).click();
		}
		
		driver.findElement(By.xpath("//a[.='Organizations'][1]")).click();

	}

}
