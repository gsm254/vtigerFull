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

public class TS05 {

	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriver driver = BrowserLaunch.chrome();

		ExcelUtility eLib = new ExcelUtility(driver);
		FileUtility fLib = new FileUtility();
		WebDriverUtility wLib = new WebDriverUtility(driver);
		// DatabaseUtility dLib = new DatabaseUtility();
		JavaUtility jLib = new JavaUtility();

		int random = jLib.getRandomNum();
		wLib.maximize();

		// opening url and login to application
		driver.get(fLib.readDataFromProperty("url"));

		driver.findElement(By.name("user_name")).sendKeys(fLib.readDataFromProperty("username"), Keys.TAB,
				fLib.readDataFromProperty("password"), Keys.TAB, Keys.ENTER);
		driver.findElement(By.linkText("Products")).click();
		driver.findElement(By.xpath("//img[@title='Create Product...']")).click();
		
		String product = eLib.readDataFromExcel("TS05", 0, 1)+random;
		String unitprice = eLib.readDataFromExcel("TS05", 1, 1);
		String stock = eLib.readDataFromExcel("TS05", 2, 1);
		driver.findElement(By.name("productname")).sendKeys(product);
		driver.findElement(By.name("unit_price")).sendKeys(unitprice);
		driver.findElement(By.name("qtyinstock")).sendKeys(stock);
		wLib.scrollIntoViewAndClick(driver.findElement(By.xpath("(//input[@value='  Save  '])[1]"))) ;
Thread.sleep(1000);
		wLib.mouseHover(driver.findElement(By.xpath("//a[.='More']")));
		driver.findElement(By.xpath("//a[.='Invoice']")).click();

		driver.findElement(By.xpath("//img[@title='Create Invoice...']")).click();

		driver.findElement(By.name("subject")).sendKeys(eLib.readDataFromExcel("TS05", 0, 5)+random);
		
		driver.findElement(By.xpath("//input[@name='account_id']/following-sibling::img")).click();
		wLib.switchToWindow("Accounts&action");
		String orgName = eLib.readDataFromExcel("TS05", 1, 5);
		driver.findElement(By.id("search_txt")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText(orgName)).click();
		
		wLib.acceptAlert();
		wLib.switchToWindow("Invoice");
		Thread.sleep(2000);
		
		
		driver.findElement(By.xpath("//textarea[@name='bill_street']")).sendKeys(eLib.readDataFromExcel("TS05", 2, 5)+random);
		
		wLib.scrollIntoViewAndClick(driver.findElement(By.xpath("//b[.='Copy Billing address']/../input")));
		
		wLib.scrollIntoViewAndClick(driver.findElement(By.xpath("//input[@name='productName1']/../img")));
		
		wLib.switchToWindow("Products");
		
		driver.findElement(By.id("search_txt")).sendKeys(product);
		driver.findElement(By.name("search")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText(product)).click();
		wLib.switchToWindow("Invoice");
		
		driver.findElement(By.name("qty1")).sendKeys(eLib.readDataFromExcel("TS05", 3, 5));
		//driver.findElement(By.name("listPrice1")).sendKeys(eLib.readDataFromExcel("TS05", 4, 5));
		
		driver.findElement(By.xpath("(//input[@value='  Save  '])[2]")).click();
		
		wLib.mouseHover(driver.findElement(By.xpath("//a[.='More']")));
		driver.findElement(By.xpath("//a[.='Assets']")).click();
		
		driver.findElement(By.xpath("//img[@title='Create Asset...']")).click();
		
		driver.findElement(By.name("serialnumber")).sendKeys(eLib.readDataFromExcel("TS05", 0, 9)+random);
		
		driver.findElement(By.xpath("//input[@name='product']/following-sibling::img")).click();
		
		wLib.switchToWindow("Accounts&action");
		driver.findElement(By.id("search_txt")).sendKeys(product);
		driver.findElement(By.name("search")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText(product)).click();
		
		driver.findElement(By.xpath("//input[@name='product_display']/following-sibling::img[1]")).click();
		wLib.switchToWindow("Products&action");
		driver.findElement(By.id("search_txt")).sendKeys(product);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.linkText(product)).click();
		
		driver.findElement(By.name("assetname")).sendKeys(eLib.readDataFromExcel("TS05", 1, 9)+random);
		driver.findElement(By.xpath("(//input[@value='  Save  '])[2]")).click();
		
		wLib.mouseHover(driver.findElement(By.xpath("//span[.=' Administrator']/../following-sibling::td[1]")));
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		driver.close();

	}

}
