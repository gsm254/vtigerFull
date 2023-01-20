package testscripts;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TestScript_3 {

	public static void main(String[] args) throws IOException, InterruptedException {

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

		driver.findElement(By.name("user_name")).sendKeys(user, Keys.TAB, pwd, Keys.TAB, Keys.ENTER);
		/*
		 * driver.findElement(By.name("user_password")).sendKeys(pwd);
		 * driver.findElement(By.id("submitButton")).click();
		 */

		/*
		 * this is to generate random number Random r = new Random(); int ranum =
		 * r.nextInt(500);
		 */

		WebElement set = driver.findElement(By.xpath("//span[@class='userName']/../following-sibling::td[3]"));
		Actions a = new Actions(driver);
		a.moveToElement(set).perform();
		Thread.sleep(2000);
		driver.findElement(By.linkText("CRM Settings")).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;

		WebElement wf = driver.findElement(By.xpath("//a[contains(.,'Workflows')]"));

		js.executeScript("arguments[0].scrollIntoView();", wf);

		wf.click();

		driver.findElement(By.id("new_workflow")).click();

		Set<String> win = driver.getWindowHandles();
		String p = null;
		for (String s : win) {
			p = s;
		}
		driver.switchTo().window(p);
		driver.findElement(By.id("new_workflow_popup_save")).click();
		String edata = "this description";
		driver.findElement(By.id("save_description")).sendKeys(edata);

		Thread.sleep(4000);
		driver.findElement(By.id("save_submit")).click();

		WebElement w = driver.findElement(By.xpath("//a[contains(.,'Workflows')]"));

		js.executeScript("arguments[0].scrollIntoView();", w);

		w.click();

		WebElement wfl = driver.findElement(By.xpath("//table[@id='expressionlist']//td[.='" + edata + "']/..//a[2]"));

		js.executeScript("arguments[0].scrollIntoView();", wfl);

		wfl.click();

		Alert alt = driver.switchTo().alert();

		alt.accept();
		/*
		 * String cdata = des.getText();
		 * 
		 * if (edata.equals(cdata)) { System.out.println("testcase is passed"); } else {
		 * System.out.println("testcase failed"); }
		 */

	}

}
