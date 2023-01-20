package tasks;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import genericLibraries.FileUtility;
import genericLibraries.WebDriverUtility;

public class TestMic {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		System.setProperty("webdriver.chrome.driver", "./softwares/chromedriver.exe");
		
		ChromeOptions option = new ChromeOptions();
		option.addArguments("use-fake-device-for-media-stream");
		option.addArguments("use-fake-ui-for-media-stream");
		WebDriver driver = new ChromeDriver(option);
		
		WebDriverUtility wlib = new WebDriverUtility(driver);
		FileUtility fLib = new FileUtility();
		driver.get("https://mictests.com/");
		
		wlib.elementVisibility(driver.findElement(By.id("mic-launcher")), fLib).click();
		Thread.sleep(10000);
		String msg = wlib.elementVisibility(driver.findElement(By.xpath("//li[contains(.,'Testing was completed')]")),fLib).getText();
		System.out.println(msg);
		driver.findElement(By.xpath("//button[@data-action='stopMic']")).click();
		driver.close();
		//driver.findElement(By.xpath("//button[.='Click here to allow access to microphone identifiers']")).click();
		
		
	}

}
