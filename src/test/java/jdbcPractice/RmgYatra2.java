package jdbcPractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;


public class RmgYatra2 {

	public static void main(String[] args) throws SQLException {
		
		//WebDriverManager.chromedriver().setup();
		Connection con = null;
		int result = 0;
		
		try {
			Driver drive = new Driver();
			
			DriverManager.registerDriver(drive);
			
			con = DriverManager.getConnection("jdbc:mysql://rmgtestingserver:3333/projects","root@%","root");
			
			Statement st = con.createStatement();
			String query = "insert into project values('TY_Project_258','vj','21/12/2022','boomra','created',3);";
			
			result = st.executeUpdate(query);
		}
		catch(Exception e) {
			
		}
		finally {
			if(result>0) {
				System.out.println("inserted successfully");
			}
			else {
				System.out.println("failed to insert");
			}
		}

		System.setProperty("webdriver.chrome.driver", "./softwares/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get("http://rmgtestingserver:8084/");
		
		
		driver.findElement(By.id("usernmae")).sendKeys("rmgyantra");
		
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[.='Sign in']")).click();
		driver.findElement(By.xpath("//a[.='Projects']")).click();
		WebElement pro = driver.findElement(By.xpath("//td[.='boomra']"));
		String pp = pro.getText();
		if(pp.equals("boomra")) {
			System.out.println("created");
		}
		else {
			System.out.println("not created");
		}
//		driver.findElement(By.xpath("//span[.='Create Project']")).click();
//		driver.findElement(By.name("projectName")).sendKeys("blm");
//		driver.findElement(By.name("createdBy")).sendKeys("deepak");
//		
//		WebElement status = driver.findElement(By.name("status"));
//		
//		Select sel = new Select(status);
//		
//		sel.selectByValue("Created");
//		
//		driver.findElement(By.xpath("//input[@value='Add Project']")).click();
		
		
		
		
		
		
		
		
		
	}

}
