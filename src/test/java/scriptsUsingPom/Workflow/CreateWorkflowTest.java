package scriptsUsingPom.Workflow;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import genericLibraries.BrowserLaunch;
import genericLibraries.ExcelUtility;
import genericLibraries.FileUtility;
import genericLibraries.JavaUtility;
import genericLibraries.WebDriverUtility;
import objectRepo.CrmSettingsPage;
import objectRepo.HomePage;
import objectRepo.LoginPage;
import objectRepo.WorkflowPage;

public class CreateWorkflowTest {

	public static void main(String[] args) throws IOException {
		WebDriver driver = BrowserLaunch.chrome();

		ExcelUtility eLib = new ExcelUtility(driver);
		FileUtility fLib = new FileUtility();
		WebDriverUtility wLib = new WebDriverUtility(driver);
		// DatabaseUtility dLib = new DatabaseUtility();
		JavaUtility jLib = new JavaUtility();

		wLib.maximize();
		driver.get(fLib.readDataFromProperty("url"));
		String edata = eLib.readDataFromExcel("TS03", 0, 0) + jLib.getRandomNum();

		// loginpage
		LoginPage lp = new LoginPage(driver);
		lp.Login(fLib.readDataFromProperty("username"), fLib.readDataFromProperty("password"));

		// homepage
		HomePage hp = new HomePage(driver);
		hp.crmSettings(wLib);
		
		CrmSettingsPage csp  = new CrmSettingsPage(driver);
		csp.getWorkflowLink().click();
		
		WorkflowPage wfp = new WorkflowPage(driver);
		wfp.getNewWorkflowBtn().click();
		wfp.getCreatePopupBtn().click();
		wLib.elementVisibility(wfp.getDescriptionField()) .sendKeys(edata);
		wLib.elementVisibility(wfp.getSaveBtn()).click();
		
		csp.getWorkflowLink().click();
		
		String actual = driver.findElement(By.xpath("//table[@id='expressionlist']//td[.='" + edata + "']")).getText();

		if (edata.equals(actual)) {
			System.out
			.println("workflow created");
		} else {
			System.out.println("workflow not created");
		}
		
		hp.signout(wLib);
		
		driver.close();
		
		
		

	}

}
