package scriptsUsingPom;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;

import genericLibraries.BrowserLaunch;
import genericLibraries.ExcelUtility;
import genericLibraries.FileUtility;
import genericLibraries.JavaUtility;
import genericLibraries.WebDriverUtility;
import objectRepo.HomePage;
import objectRepo.LeadInfoPage;
import objectRepo.LeadPage;
import objectRepo.LoginPage;
import objectRepo.createLeadPage;

public class createLead_test {

	public static void main(String[] args) throws IOException {

		WebDriver driver = BrowserLaunch.chrome();

		ExcelUtility eLib = new ExcelUtility(driver);
		FileUtility fLib = new FileUtility();
		WebDriverUtility wLib = new WebDriverUtility(driver);
		// DatabaseUtility dLib = new DatabaseUtility();
		JavaUtility jLib = new JavaUtility();

		wLib.maximize();
		driver.get(fLib.readDataFromProperty("url"));

		// loginpage
		LoginPage lp = new LoginPage(driver);
		lp.Login(fLib.readDataFromProperty("username"), fLib.readDataFromProperty("password"));

		// homepage
		HomePage hp = new HomePage(driver);
		hp.getLeadLink().click();

		Sheet sh = eLib.getSheet("leads");

		// lead page
		LeadPage leadpage = new LeadPage(driver);

		// create lead page
		createLeadPage clp = new createLeadPage(driver);

		// lead info page
		LeadInfoPage lip = new LeadInfoPage(driver);
		String leadinfo;
		String leadid;
		String fname;

		// to create multiple leads
		for (int j = 1; j <= sh.getLastRowNum(); j++) {

			
			int ran = jLib.getRandomNum();
			leadpage.getCreateLead().click();
			clp.saluteDropdown(sh.getRow(j).getCell(0).toString());
			fname = sh.getRow(j).getCell(1).toString() + ran;
			clp.firstName(fname);
			clp.lastName(sh.getRow(j).getCell(2).toString() + ran);
			clp.company(sh.getRow(j).getCell(3).toString() + ran);
			clp.saveButton();

			leadinfo = lip.getLeadInfo();
			leadid = lip.getLeadId();
			System.out.println(leadinfo);
			System.out.println(leadid);
			
			// verifying the created lead
			if (leadinfo.contains(fname)) {
				System.out.println(fname + " is created");
			} else {
				System.out.println(fname + " is not created");
			}

		}

		// sign out from application
		hp.signout(wLib);

		// close browser
		driver.close();

	}

}
