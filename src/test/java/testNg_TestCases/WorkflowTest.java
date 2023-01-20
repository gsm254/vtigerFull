package testNg_TestCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import genericLibraries.BaseClass;
import genericLibraries.BrowserLaunch;
import genericLibraries.ExcelUtility;
import genericLibraries.FileUtility;
import genericLibraries.JavaUtility;
import genericLibraries.WebDriverUtility;
import objectRepo.CrmSettingsPage;
import objectRepo.HomePage;
import objectRepo.LoginPage;
import objectRepo.WorkflowPage;

public class WorkflowTest extends BaseClass {
	CrmSettingsPage crmSettingPage;
	WorkflowPage workflowPage;

	@Test
	public void createWorkflow() throws IOException {

		crmSettingPage = new CrmSettingsPage(driver);
		workflowPage = new WorkflowPage(driver);

		String description = eLib.readDataFromExcel("TS03", 0, 0) + jLib.getRandomNum();

		homePage.crmSettings(wLib);
		crmSettingPage.getWorkflowLink().click();
		workflowPage.getNewWorkflowBtn().click();
		workflowPage.getCreatePopupBtn().click();
		wLib.elementVisibility(workflowPage.getDescriptionField(),fLib).sendKeys(description);
		wLib.elementVisibility(workflowPage.getSaveBtn(),fLib).click();
		crmSettingPage.getWorkflowLink().click();

		String actual = driver.findElement(By.xpath("//table[@id='expressionlist']//td[.='" + description + "']"))
				.getText();
		Assert.assertEquals(actual, description,"--Workflow is not created!!!--");
		
		/*
		 * if (description.equals(actual)) { Reporter.log("--workflow created!!!--",
		 * true); } else { Reporter.log("--workflow not created!!!--", true); }
		 */
	}

}
