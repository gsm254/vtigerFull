package scriptsUsingLibraries;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import genericLibraries.DatabaseUtility;
import genericLibraries.ExcelUtility;
import genericLibraries.FileUtility;
import genericLibraries.IPathConstants;
import genericLibraries.JavaUtility;
import genericLibraries.WebDriverUtility;

public class TS02 {

	public static void main(String[] args) throws IOException {
		System.setProperty("webdriver.chrome.driver", "./softwares/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		DatabaseUtility dbLib = new DatabaseUtility();
		ExcelUtility eLib = new ExcelUtility(driver);
		FileUtility fLib = new FileUtility();
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility(driver);
		
		wLib.maximize();
		driver.get(fLib.readDataFromProperty("url"));

		driver.findElement(By.name("user_name")).sendKeys(fLib.readDataFromProperty("username"));
		driver.findElement(By.name("user_password")).sendKeys(fLib.readDataFromProperty("password"));
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.xpath("//a[.='Leads'][1]")).click();
		driver.findElement(By.xpath("//img[@title='Create Lead...']")).click();
		
		wLib.select(driver.findElement(By.name("salutationtype")), "Mr.");
		
		eLib.getList("Sheet6", 0, 1);
		driver.findElement(By.xpath("(//input[@value='  Save  '])[1]")).click();
		
		driver.findElement(By.name("Edit")).click();
		
		WebElement mm = driver.findElement(By.name("mobile"));
		mm.clear();
		String nmob = eLib.readDataFromExcel("Sheet6", 0, 5);
		mm.sendKeys(nmob);
		
		driver.findElement(By.xpath("(//input[@value='  Save  '])[1]")).click();

		WebElement m = driver.findElement(By.id("dtlview_Mobile"));

		String vmob = m.getText();
		
		if (nmob.equals(vmob)) {
			System.out.println("testcase passed");
		} else {
			System.out.println("testcase failed");
		}
		
		wLib.mouseHover(driver.findElement(By.xpath("//span[.=' Administrator']/../following-sibling::td[1]")));
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();  

		driver.close();
	}

}
