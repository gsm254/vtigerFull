package genericLibraries;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import objectRepo.HomePage;
import objectRepo.LoginPage;

public class BaseClass {

	public static WebDriver driver;
	public static WebDriverUtility wLib;
	public static ExcelUtility eLib = new ExcelUtility();
	public static FileUtility fLib = new FileUtility();
	public DatabaseUtility dLib = new DatabaseUtility();
	public static JavaUtility jLib = new JavaUtility();

	public static LoginPage loginPage;
	public static HomePage homePage;

	@BeforeSuite
	public void connectDB() throws SQLException {
		dLib.connectToDb();
		Reporter.log("--connected to DB!!!--", true);

	}

	/*
	 * @BeforeTest public void btest() { System.out.println("bftest"); }
	 */
	// @Parameters({"browser"})
	@BeforeClass(groups = "smoke")
	public void launchBrowser(/* String browser */) throws IOException {

		String browser = fLib.readDataFromProperty("browser");

		if (browser.equalsIgnoreCase("firefox")) {
			driver = BrowserLaunch.firefox();

		} else {
			driver = BrowserLaunch.chrome();
			driver.manage().window().maximize();
		}

		if (driver != null)
			Reporter.log("--browser launched!!!--", true);
		wLib = new WebDriverUtility(driver);

		driver.get(fLib.readDataFromProperty("url"));
		Reporter.log("--open the application!!!--", true);

	}

	@BeforeMethod(groups = "smoke")
	public static void login() throws IOException {
		String username = fLib.readDataFromProperty("username");
		String password = fLib.readDataFromProperty("password");

		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);

		loginPage.Login(username, password);
		Reporter.log("--login to application!!!--", true);

	}

	@AfterMethod(groups = "smoke")
	public void logout() {

		homePage.signout(wLib);
		Reporter.log("--logout from application!!!--", true);
	}

	@AfterClass(groups = "smoke")
	public void closelaunchBrowser() {
		driver.quit();
		Reporter.log("--browser closed!!!--", true);
	}

	/*
	 * @AfterTest public void atest() { System.out.println("aftest"); }
	 */

	@AfterSuite
	public void dbDisconnect() throws SQLException {
		dLib.closeDb();
		Reporter.log("--db disconnected!!!--", true);
	}

}
