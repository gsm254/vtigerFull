package scriptsUsingLibraries;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import genericLibraries.BrowserLaunch;
import genericLibraries.DatabaseUtility;
import genericLibraries.ExcelUtility;
import genericLibraries.FileUtility;
import genericLibraries.IPathConstants;
import genericLibraries.JavaUtility;
import genericLibraries.WebDriverUtility;

public class TS03 {

	public static void main(String[] args) throws IOException {

		// launching browser
		WebDriver driver = BrowserLaunch.chrome();

		ExcelUtility eLib = new ExcelUtility(driver);
		FileUtility fLib = new FileUtility();
		WebDriverUtility wLib = new WebDriverUtility(driver);
		// DatabaseUtility dLib = new DatabaseUtility();
		JavaUtility jLib = new JavaUtility();

		// maximize the window
		wLib.maximize();

		// opening url and login to application
		driver.get(fLib.readDataFromProperty("url"));

		driver.findElement(By.name("user_name")).sendKeys(fLib.readDataFromProperty("username"), Keys.TAB,
				fLib.readDataFromProperty("password"), Keys.TAB, Keys.ENTER);

		wLib.mouseHover(driver.findElement(By.xpath("//span[@class='userName']/../following-sibling::td[3]")));
		driver.findElement(By.linkText("CRM Settings")).click();

		wLib.scrollIntoViewAndClick(driver.findElement(By.xpath("//a[contains(.,'Workflows')]")));

		// creating the workflow
		driver.findElement(By.id("new_workflow")).click();

		driver.findElement(By.id("new_workflow_popup_save")).click();
		String edata = eLib.readDataFromExcel("TS03", 0, 0) + jLib.getRandomNum();
		driver.findElement(By.id("save_description")).sendKeys(edata);

		wLib.elementVisibility(driver.findElement(By.id("save_submit"))).click();

		wLib.scrollIntoViewAndClick(driver.findElement(By.xpath("//a[contains(.,'Workflows')]")));

		String actual = driver.findElement(By.xpath("//table[@id='expressionlist']//td[.='" + edata + "']")).getText();

		if (edata.equals(actual)) {
			System.out
			.println("workflow created");
		} else {
			System.out.println("workflow not created");
		}
		/*
		 * //deleting the workflow wLib.scrollIntoViewAndClick(
		 * driver.findElement(By.xpath("//table[@id='expressionlist']//td[.='" + edata +
		 * "']/..//a[2]"))); wLib.acceptAlert();
		 * 
		 * System.out.println("workflow is successfully deleted");
		 */
		wLib.mouseHover(driver.findElement(By.xpath("//span[.=' Administrator']/../following-sibling::td[1]")));
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();

		driver.close();
	}

}
