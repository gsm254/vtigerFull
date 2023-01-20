package scriptsUsingLibraries;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import genericLibraries.DatabaseUtility;
import genericLibraries.ExcelUtility;
import genericLibraries.FileUtility;
import genericLibraries.JavaUtility;
import genericLibraries.WebDriverUtility;

public class TS01 {

	public static void main(String[] args) throws IOException, InterruptedException {

		System.setProperty("webdriver.chrome.driver", "./softwares/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		DatabaseUtility dbLib = new DatabaseUtility();
		ExcelUtility eLib = new ExcelUtility(driver);
		FileUtility fLib = new FileUtility();
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility(driver);

		wLib.maximize();

		driver.get(fLib.readDataFromProperty("url"));
		wLib.waitForPageLoad();

		driver.findElement(By.name("user_name")).sendKeys(fLib.readDataFromProperty("password"), Keys.TAB,
				fLib.readDataFromProperty("username"), Keys.TAB, Keys.ENTER);

		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//create organization
		eLib.getList("Sheet5", 0, 1);
		
		/*
		 * for (Entry<String, String> e : map.entrySet()) { String key = e.getKey();
		 * String value = e.getValue();
		 * 
		 * driver.findElement(By.name(key)).sendKeys(value); }
		 */
		
		driver.findElement(By.xpath("(//input[@value='  Save  '])[1]")).click();
		String orgNa = driver.findElement(By.id("dtlview_Organization Name")).getText();
		
		Thread.sleep(3000);
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//create contact
		eLib.getList("Sheet5", 4, 5);
		
		driver.findElement(By.xpath("//input[@name='account_id']/following-sibling::img")).click();
		
		wLib.switchToWindow("Accounts&action");
		
		driver.findElement(By.id("search_txt")).sendKeys(orgNa);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.linkText(orgNa)).click();
		
		wLib.switchToWindow("Contacts&action");
		
		wLib.select(driver.findElement(By.name("leadsource")), "Employee");
		
		driver.findElement(By.xpath("(//input[@value='  Save  '])[1]")).click();
		
		String con = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if (con.contains("Anup")) {
			System.out.println("contact created");
		} else {
			System.out.println("contact not created");
		}
		
		wLib.mouseHover(driver.findElement(By.xpath("//span[.=' Administrator']/../following-sibling::td[1]")));
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();      
		
		driver.close();

		
		
	}

}
