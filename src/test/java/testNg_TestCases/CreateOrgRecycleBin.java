package testNg_TestCases;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import genericLibraries.BaseClass;
import objectRepo.ContactsPage;
import objectRepo.CreateContactPage;
import objectRepo.CreateOrgPage;
import objectRepo.HomePage;
import objectRepo.LoginPage;
import objectRepo.OrgInfoPage;
import objectRepo.OrganizationPage;
import objectRepo.RecycleBinPage;

public class CreateOrgRecycleBin extends BaseClass {
	OrganizationPage orgPage;
	ContactsPage contactPage;
	CreateOrgPage creaOrgPage;
	OrgInfoPage orgInfoPage;
	CreateContactPage createContactPage;
	RecycleBinPage recyclePage;
	CreateOrgPage createOrgPage;

	/**
	 * this script testing creating the organization delete check it in recycle bin
	 * 
	 * @author Gowtham
	 * @throws IOException
	 * @throws InterruptedException
	 */
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
