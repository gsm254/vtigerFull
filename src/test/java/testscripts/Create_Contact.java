package testscripts;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Create_Contact {

	public static void main(String[] args) throws IOException {

		// to generate random number
		Random ran = new Random();
		int n = ran.nextInt(200);

		// reading the common data
		FileInputStream fis = new FileInputStream("./src/test/java/datadriven/common.properties");

		Properties prop = new Properties();
		prop.load(fis);

		String url = prop.getProperty("url");
		String user = prop.getProperty("user");
		String pwd = prop.getProperty("pwd");

		// reading data from excel
		FileInputStream fie = new FileInputStream("C:\\Users\\G\\Desktop\\data.xlsx");

		Workbook book = WorkbookFactory.create(fie);
		Sheet sh = book.getSheet("Sheet3");
		Row r = sh.getRow(0);

		// launching browser
		System.setProperty("webdriver.chrome.driver", "./softwares/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get(url);

		// login
		driver.findElement(By.name("user_name")).sendKeys(user, Keys.TAB, pwd, Keys.TAB, Keys.ENTER);

		// create organizations

		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		ArrayList<String> al = new ArrayList<String>();
		al.add("accountname");
		al.add("website");

		for (int i = 0; i < r.getLastCellNum(); i++) {
			String data = sh.getRow(1).getCell(i).getStringCellValue();
			driver.findElement(By.name(al.get(i))).sendKeys(data + n);
		}

		driver.findElement(By.xpath("(//input[@value='  Save  '])[1]")).click();
		String orgNa = driver.findElement(By.id("dtlview_Organization Name")).getText();

		// create contact
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		driver.findElement(By.name("firstname")).sendKeys("Manju");
		driver.findElement(By.name("lastname")).sendKeys("Manju");

		String pid = driver.getWindowHandle();

		driver.findElement(By.xpath("//input[@name='account_id']/following-sibling::img")).click();

		Set<String> windows = driver.getWindowHandles();

		Iterator<String> wind = windows.iterator();

		while (wind.hasNext()) {
			String w = wind.next();
			String title = driver.switchTo().window(w).getTitle();

			if (title.contains("Accounts&action")) {
				break;
			}

		}

		driver.findElement(By.id("search_txt")).sendKeys(orgNa);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.linkText(orgNa)).click();

		driver.switchTo().window(pid);

		Select s = new Select(driver.findElement(By.name("leadsource")));

		s.selectByValue("Employee");

		driver.findElement(By.xpath("(//input[@value='  Save  '])[1]")).click();

		String con = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();

		if (con.contains("Manju")) {
			System.out.println("contact created");
		} else {
			System.out.println("contact not created");
		}

	}

}
