package testNg.practice;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import genericLibraries.BrowserLaunch;
import genericLibraries.FileUtility;
import genericLibraries.WebDriverUtility;

public class AmazonSuggestion {
	
	@Test
	public void amazon() throws IOException {
		WebDriver driver = BrowserLaunch.chrome();
		driver.get("https://www.amazon.in/");
		WebDriverUtility wlib = new WebDriverUtility(driver);
		FileUtility flib = new FileUtility();
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("pi");
		wlib.elementVisibility(driver.findElement(By.xpath("//div[contains(@id,'searchAjax')]")), flib);
		List<WebElement> sugg = driver.findElements(By.xpath("//span[contains(@class,'heavy')]"));
		System.out.println(sugg.size());
		for(WebElement s:sugg) {
			System.out.println("hi");
			System.out.println("pi"+s.getText());
		}
	}

}
