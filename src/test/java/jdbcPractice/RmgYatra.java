package jdbcPractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;


public class RmgYatra {

	public static void main(String[] args) {
		
//		//WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver", "./softwares/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get("http://rmgtestingserver:8084/");
		
		
		driver.findElement(By.id("usernmae")).sendKeys("rmgyantra");
		
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[.='Sign in']")).click();
		driver.findElement(By.xpath("//a[.='Projects']")).click();
		driver.findElement(By.xpath("//span[.='Create Project']")).click();
		driver.findElement(By.name("projectName")).sendKeys("blmmmm");
		driver.findElement(By.name("createdBy")).sendKeys("deepak");
		
		WebElement status = driver.findElement(By.name("status"));
		
		Select sel = new Select(status);
		
		sel.selectByValue("Created");
		
		driver.findElement(By.xpath("//input[@value='Add Project']")).click();
//		
		Connection con = null;
		
		
		ResultSet result;
		try {
			Driver drive = new Driver();
			
			DriverManager.registerDriver(drive);
			
			con = DriverManager.getConnection("jdbc:mysql://rmgtestingserver:3333/projects","root@%","root");
			
			Statement st = con.createStatement();
			String query = "select * from project where project_Name = 'blmmmm';";
			
			result = st.executeQuery(query);
			
			while(result.next()) {
				if(result.getString(4).equals("blmmmm")) {
					System.out.println("project created");
				}
				else {
					System.out.println("project not created");
				}
			}
		}
		catch(Exception e) {
			
		}
		
		
		
		
		
		
		
	}

}
