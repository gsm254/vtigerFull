package scriptsUsingPom;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import genericLibraries.BrowserLaunch;
import genericLibraries.ExcelUtility;
import genericLibraries.FileUtility;
import genericLibraries.JavaUtility;
import genericLibraries.WebDriverUtility;
import objectRepo.CreateOrgPage;
import objectRepo.HomePage;
import objectRepo.LoginPage;
import objectRepo.OrgInfoPage;
import objectRepo.OrganizationPage;
import objectRepo.RecycleBinPage;

public class CreateOrgRecycleBinTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriver driver = BrowserLaunch.chrome();

		ExcelUtility eLib = new ExcelUtility(driver);
		FileUtility fLib = new FileUtility();
		WebDriverUtility wLib = new WebDriverUtility(driver);
		// DatabaseUtility dLib = new DatabaseUtility();
		JavaUtility jLib = new JavaUtility();

		Sheet orgN = eLib.getSheet("Sheet3");
		// Sheet contacts = eLib.getSheet("Sheet4");
		int ran = jLib.getRandomNum();

		wLib.maximize();
		driver.get(fLib.readDataFromProperty("url"));

		// loginpage
		LoginPage loginPg = new LoginPage(driver);
		loginPg.Login(fLib.readDataFromProperty("username"), fLib.readDataFromProperty("password"));

		// homepage
		HomePage homePg = new HomePage(driver);
		homePg.getOrgLink().click();

		// organization page
		OrganizationPage orgPg = new OrganizationPage(driver);
		orgPg.getCreateOrg().click();

		// create organization page
		CreateOrgPage createOrgPg = new CreateOrgPage(driver);
		String org = orgN.getRow(1).getCell(0).toString() + ran;
		createOrgPg.orgName(org);
		createOrgPg.website(orgN.getRow(1).getCell(1).toString());
		createOrgPg.saveButton();
		Thread.sleep(1000);

		OrgInfoPage orgInfoPg = new OrgInfoPage(driver);
		orgInfoPg.delete().click();
		wLib.acceptAlert();

		homePg.more(wLib);
		homePg.getRecycleBin().click();

		RecycleBinPage recycleBPg = new RecycleBinPage(driver);

		recycleBPg.searchOrg(org, eLib.readDataFromExcel("recycleBin", 1, 1));
		Thread.sleep(2000);
		String actual = driver.findElement(By.linkText(org)).getText();

		if (actual.equals(org)) {
			System.out.println("testcase passed");
		} else {
			System.out.println("testcase failed");
		}

		// sign out
		homePg.signout(wLib);

		// close browser
		driver.close();

	}

}
