package testscripts;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TestScript2 {

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

		String mob = "123456";
		String nmob = "3698898990";

		driver.get(url);

		driver.findElement(By.name("user_name")).sendKeys(user);
		driver.findElement(By.name("user_password")).sendKeys(pwd);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.xpath("//a[.='Leads'][1]")).click();
		driver.findElement(By.xpath("//img[@title='Create Lead...']")).click();
		WebElement salute = driver.findElement(By.name("salutationtype"));

		Select s = new Select(salute);

		s.selectByValue("Mr.");

		driver.findElement(By.name("firstname")).sendKeys("Sanju");
		driver.findElement(By.name("lastname")).sendKeys("baba");
		driver.findElement(By.name("company")).sendKeys("Oracle");
		driver.findElement(By.name("mobile")).sendKeys(mob);

		driver.findElement(By.xpath("(//input[@value='  Save  '])[1]")).click();

		driver.findElement(By.name("Edit")).click();
		WebElement mm = driver.findElement(By.name("mobile"));
		mm.clear();
		mm.sendKeys(nmob);
		driver.findElement(By.xpath("(//input[@value='  Save  '])[1]")).click();

		WebElement m = driver.findElement(By.id("dtlview_Mobile"));

		String vmob = m.getText();

		if (nmob.equals(vmob)) {
			System.out.println("testcase passed");
		} else {
			System.out.println("testcase failed");
		}

		driver.close();

	}

}
