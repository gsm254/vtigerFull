package testscripts;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Testcript_5 {

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
		
		Random ran = new Random();
		int n = ran.nextInt(200);
		String orgName = "Raja"+n;
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get(url);

		driver.findElement(By.name("user_name")).sendKeys(user, Keys.TAB, pwd, Keys.TAB, Keys.ENTER);
		
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.xpath("(//input[@value='  Save  '])[1]")).click();
		driver.findElement(By.xpath("(//input[@name='Delete'])[1]")).click();
		Alert pop = driver.switchTo().alert();
		
		pop.accept();
		
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//a[.='More']"))).perform();
		driver.findElement(By.xpath("//a[.='Recycle Bin']")).click();
		driver.findElement(By.name("search_text")).sendKeys(orgName);
		Select s = new Select(driver.findElement(By.xpath("//select[@id='bas_searchfield']")));
		s.selectByValue("accountname");
		driver.findElement(By.name("submit")).click();
		
		String actual =driver.findElement(By.xpath("//a[.='"+orgName+"']")).getText();
		if(orgName.equals(actual)) {
			System.out.println("testcase passed");
		}
		else
		{
			System.out.println("testcase failed");
		}
		
		//logout
		act.moveToElement(driver.findElement(By.xpath("//span[.=' Administrator']/../following-sibling::td[1]"))).perform();
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();      
		
		driver.close();
		
		
		

	}

}
