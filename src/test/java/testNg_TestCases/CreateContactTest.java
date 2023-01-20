package testNg_TestCases;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;
import objectRepo.ContactsPage;
import objectRepo.CreateContactPage;
import objectRepo.CreateOrgPage;
import objectRepo.OrgInfoPage;
import objectRepo.OrganizationPage;
import objectRepo.RecycleBinPage;

@Listeners(genericLibraries.ListenerImplementation.class)
public class CreateContactTest extends BaseClass {

	OrganizationPage orgPage;
	ContactsPage contactPage;
	CreateOrgPage creaOrgPage;
	OrgInfoPage orgInfoPage;
	CreateContactPage createContactPage;

	RecycleBinPage recyclePage;
	CreateOrgPage createOrgPage;
	

	/**
	 * this script testing creating the organization and contact
	 * 
	 * @author Gowtham
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	@Test(groups = "smoke", retryAnalyzer = genericLibraries.RetryImplementaion.class)
	public void createContact() throws NumberFormatException, IOException {

		orgPage = new OrganizationPage(driver);
		contactPage = new ContactsPage(driver);
		creaOrgPage = new CreateOrgPage(driver);
		orgInfoPage = new OrgInfoPage(driver);
		createContactPage = new CreateContactPage(driver);

		Sheet orgN = eLib.getSheet("organization");
		Sheet contacts = eLib.getSheet("contacts");
		int ran = jLib.getRandomNum();

		// wLib.maximize();
		wLib.waitForPageLoad(fLib);

		homePage.getOrgLink().click();
		SoftAssert sa = new SoftAssert();
		sa.fail();
		sa.assertAll();

		// Assert.assertEquals(false, true);

		orgPage.getCreateOrg().click();

		String org = orgN.getRow(1).getCell(0).toString() + ran;
		creaOrgPage.orgName(org);
		creaOrgPage.website(orgN.getRow(1).getCell(1).toString());
		creaOrgPage.saveButton();

		wLib.elementVisibility(orgInfoPage.getheaderText(),fLib);

		homePage.getContactsLink().click();

		contactPage.getCreateContact().click();

		// create contact page
		createContactPage.saluteDropdown(contacts.getRow(1).getCell(0).toString());
		createContactPage.firstName(contacts.getRow(1).getCell(1).toString() + ran);
		createContactPage.lastName(contacts.getRow(1).getCell(2).toString() + ran);
		// select organization
		createContactPage.organization().click();
		wLib.switchToWindow("Accounts");
		orgPage.searchOrg(org, eLib.readDataFromExcel("contacts", 0, 3));
		driver.findElement(By.linkText(org)).click();
		wLib.switchToWindow("Contacts");
		createContactPage.leadSource(contacts.getRow(1).getCell(4).toString());
		createContactPage.saveButton();
		//Assert.assertEquals(contact, null);

	}

	@Test(groups = "smoke")
	public void createOrgRecycleBin() throws IOException, InterruptedException {
		orgPage = new OrganizationPage(driver);
		recyclePage = new RecycleBinPage(driver);
		createOrgPage = new CreateOrgPage(driver);
		orgInfoPage = new OrgInfoPage(driver);

		Sheet orgN = eLib.getSheet("organization");
		int ran = jLib.getRandomNum();

		wLib.maximize();

		homePage.getOrgLink().click();
		orgPage.getCreateOrg().click();

		String org = orgN.getRow(1).getCell(0).toString() + ran;
		createOrgPage.orgName(org);
		createOrgPage.website(orgN.getRow(1).getCell(1).toString());
		createOrgPage.saveButton();

		Thread.sleep(1000);

		orgInfoPage.delete().click();
		wLib.acceptAlert();

		homePage.more(wLib);
		homePage.getRecycleBin().click();

		recyclePage.searchOrg(org, eLib.readDataFromExcel("recycleBin", 1, 1));
		Thread.sleep(2000);

		String actual = driver.findElement(By.linkText(org)).getText();

		Assert.assertEquals(actual, org,"--Deleted organization not present in recycle bin!!!--");
		/*
		 * if (actual.equals(org)) { System.out.println("testcase passed"); } else {
		 * System.out.println("testcase failed"); }
		 */

	}

}
