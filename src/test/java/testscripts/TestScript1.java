package testscripts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TestScript1 {

	public static void main(String[] args) throws IOException {
		System.setProperty("webdriver.chrome.driver", "./softwares/chromedriver.exe");
		
		

		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		FileInputStream fis = new FileInputStream("./src/test/java/datadriven/common.properties");

		Properties prop = new Properties();

		prop.load(fis);

		String url = prop.getProperty("url");
		String user = prop.getProperty("user");
		String pwd = prop.getProperty("pwd");

		driver.get(url);

		driver.findElement(By.name("user_name")).sendKeys(user);
		driver.findElement(By.name("user_password")).sendKeys(pwd);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.xpath("//a[.='Leads'][1]")).click();
		driver.findElement(By.xpath("//img[@title='Create Lead...']")).click();
		WebElement salute =  driver.findElement(By.name("salutationtype"));
		
		Select s = new Select(salute);
		
		s.selectByValue("Mr.");
		
		driver.findElement(By.name("firstname")).sendKeys("dilip");
		driver.findElement(By.name("lastname")).sendKeys("kumar");
		driver.findElement(By.name("company")).sendKeys("plm");
		
		driver.findElement(By.xpath("(//input[@value='  Save  '])[1]")).click();
		
		
		driver.findElement(By.name("Edit")).click();
		
		WebElement fn =  driver.findElement(By.name("firstname"));
		
		String firstn = fn.getAttribute("value");
		
		if("dilip".equals(firstn)) {
			System.out.println("test case passed");
		}
		else {
			System.out.println("test case failed");
		}
		
		
		
		driver.close();
		
		
		
	}

}
