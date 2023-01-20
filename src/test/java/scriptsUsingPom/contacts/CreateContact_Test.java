package scriptsUsingPom.contacts;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import genericLibraries.BrowserLaunch;
import genericLibraries.ExcelUtility;
import genericLibraries.FileUtility;
import genericLibraries.JavaUtility;
import genericLibraries.WebDriverUtility;
import objectRepo.ContactsPage;
import objectRepo.CreateContactPage;
import objectRepo.CreateOrgPage;
import objectRepo.HomePage;
import objectRepo.LoginPage;
import objectRepo.OrgInfoPage;
import objectRepo.OrganizationPage;

public class CreateContact_Test {

	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriver driver = BrowserLaunch.chrome();

		ExcelUtility eLib = new ExcelUtility(driver);
		FileUtility fLib = new FileUtility();
		WebDriverUtility wLib = new WebDriverUtility(driver);
		// DatabaseUtility dLib = new DatabaseUtility();
		JavaUtility jLib = new JavaUtility();
		
	 
		Sheet orgN = eLib.getSheet("Sheet3");
		Sheet contacts = eLib.getSheet("contacts");
		int ran = jLib.getRandomNum();
		
		
		wLib.maximize();
		driver.get(fLib.readDataFromProperty("url"));
		wLib.waitForPageLoad();
		
		

		// loginpage
		LoginPage lp = new LoginPage(driver);
		lp.Login(fLib.readDataFromProperty("username"), fLib.readDataFromProperty("password"));

		// homepage
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		
		//organization page
		OrganizationPage op =new OrganizationPage(driver);
		op.getCreateOrg().click();
		
		//create organization page
		CreateOrgPage cop = new CreateOrgPage(driver);
		String org = orgN.getRow(1).getCell(0).toString()+ran;
		cop.orgName(org);
		cop.website(orgN.getRow(1).getCell(1).toString());
		cop.saveButton();
		OrgInfoPage oip = new OrgInfoPage(driver);
		wLib.elementVisibility(oip.getheaderText());
		//Thread.sleep(1000);
		
		hp.getContactsLink().click();
		
		
		//contact page
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateContact().click();

		//create contact page
		CreateContactPage ccp = new CreateContactPage(driver);
		ccp.saluteDropdown(contacts.getRow(1).getCell(0).toString());
		ccp.firstName(contacts.getRow(1).getCell(1).toString()+ran);
		ccp.lastName(contacts.getRow(1).getCell(2).toString()+ran);
		
		//organization
		ccp.organization().click();
		wLib.switchToWindow("Accounts");
		op.searchOrg(org, eLib.readDataFromExcel("contacts", 0, 3));
		//Thread.sleep(1000);
		driver.findElement(By.linkText(org)).click();
		wLib.switchToWindow("Contacts");
		
		ccp.leadSource(contacts.getRow(1).getCell(4).toString());
		ccp.saveButton();
		
		//sign out
		hp.signout(wLib);
		
		//close browser
		driver.close();
	}

}
