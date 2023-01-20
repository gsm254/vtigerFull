package testNg.practice;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class BaseClassAnnotaions {
	@BeforeSuite
	public void dbConnect() {
		System.out.println("db connected");
	}
	@BeforeTest
	public void btest() {
		System.out.println("bftest");
	}

	@BeforeClass

	public void launchBrowser() {
		System.out.println("browser lAunched");
	}

	@BeforeMethod
	public void login() {
		System.out.println("login to app");
	}

	@AfterMethod
	public void logout() {
		System.out.println("logout from app");
	}

	@AfterClass

	public void closelaunchBrowser() {
		System.out.println("browser closed");
	}
	@AfterTest
	public void atest() {
		System.out.println("aftest");
	}

	@AfterSuite
	public void dbDisconnect() {
		System.out.println("db disconnected");
	}
}
