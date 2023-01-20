package tasks;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import genericLibraries.FileUtility;
import genericLibraries.WebDriverUtility;

public class Dropps {

	public static void main(String[] args) throws IOException {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/softwares/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		WebDriverUtility wlib = new WebDriverUtility(driver);
		FileUtility flib = new FileUtility();
		driver.get("file:///C:/Users/G/Desktop/drop222.html");
		driver.findElement(By.tagName("select")).click();
		wlib.elementVisibility(driver.findElement(By.xpath("//option[.='ee']")), flib).click();
		driver.findElement(By.tagName("select")).click();
	}

}
