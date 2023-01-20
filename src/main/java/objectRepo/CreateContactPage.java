package objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import genericLibraries.WebDriverUtility;

public class CreateContactPage extends WebDriverUtility{
	// declaration
	@FindBy(using = "salutationtype", how = How.NAME)
	private WebElement salute;
	@FindBy(using = "firstname", how = How.NAME)
	private WebElement firstName;
	@FindBy(using = "lastname", how = How.NAME)
	private WebElement lastName;
	@FindBy(using = "leadsource", how = How.NAME)
	private WebElement leadSource;
	@FindBy(using = "//input[@name='account_id']/following-sibling::img", how = How.XPATH)
	private WebElement orgName;
	@FindBy(using = "(//input[@value='  Save  '])[1]", how = How.XPATH)
	private WebElement saveButton;

	// initialization
	public CreateContactPage(WebDriver driver) {
			super(driver);
			PageFactory.initElements(driver, this);
		}

	// utilization
	public void saluteDropdown(String value) {
		select(salute, value);
	}

	public void firstName(String fname) {
		firstName.sendKeys(fname);
	}

	public void lastName(String lname) {
		lastName.sendKeys(lname);
	}

	public WebElement organization() {
		
		return orgName;
	}
	
	public void leadSource(String value) {
		select(leadSource,value);
	}

	public void saveButton() {
		saveButton.click();
	}

}
