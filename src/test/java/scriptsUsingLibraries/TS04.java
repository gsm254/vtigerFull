package scriptsUsingLibraries;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import genericLibraries.BrowserLaunch;
import genericLibraries.ExcelUtility;
import genericLibraries.FileUtility;
import genericLibraries.JavaUtility;
import genericLibraries.WebDriverUtility;

public class TS04 {

	public static void main(String[] args) throws IOException, InterruptedException {

		WebDriver driver = BrowserLaunch.chrome();

		ExcelUtility eLib = new ExcelUtility(driver);
		FileUtility fLib = new FileUtility();
		WebDriverUtility wLib = new WebDriverUtility(driver);
		// DatabaseUtility dLib = new DatabaseUtility();
		JavaUtility jLib = new JavaUtility();

		wLib.maximize();

		// opening url and login to application
		driver.get(fLib.readDataFromProperty("url"));

		driver.findElement(By.name("user_name")).sendKeys(fLib.readDataFromProperty("username"), Keys.TAB,
				fLib.readDataFromProperty("password"), Keys.TAB, Keys.ENTER);

		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		eLib.getList("Sheet5", 0, 1);
		driver.findElement(By.xpath("(//input[@value='  Save  '])[1]")).click();
		Thread.sleep(3000);
		String orgName = wLib.elementVisibility(driver.findElement(By.xpath("//span[@id='dtlview_Organization Name']"))).getText();
		wLib.elementVisibility(driver.findElement(By.xpath("//td[@class='dvtTabCache']/input[@name='Delete']"))).click();

		wLib.acceptAlert();
		wLib.mouseHover(driver.findElement(By.xpath("//a[.='More']")));
		driver.findElement(By.xpath("//a[.='Recycle Bin']")).click();
		
		driver.findElement(By.name("search_text")).sendKeys(orgName);

		wLib.select(driver.findElement(By.xpath("//select[@id='bas_searchfield']")), "accountname");
		driver.findElement(By.name("submit")).click();

		Thread.sleep(3000);
		String actual = wLib.elementVisibility(driver.findElement(By.xpath("//a[contains(.,'" + orgName + "')]"))).getText();
		if (orgName.equals(actual)) {
			System.out.println("testcase passed");
		} else {
			System.out.println("testcase failed");
		}

		// logout
		wLib.mouseHover(driver.findElement(By.xpath("//span[.=' Administrator']/../following-sibling::td[1]")));
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();

		driver.close();

	}

}
