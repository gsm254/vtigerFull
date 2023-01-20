package objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import genericLibraries.WebDriverUtility;

public class createLeadPage extends WebDriverUtility{

	//declaration
	@FindBy(using = "salutationtype", how = How.NAME)
	private WebElement salute;
	@FindBy(using = "firstname", how = How.NAME)
	private WebElement firstName;
	@FindBy(using = "lastname", how = How.NAME)
	private WebElement lastName;
	@FindBy(using = "company", how = How.NAME)
	private WebElement companyName;
	@FindBy(using = "mobile", how = How.NAME)
	private WebElement mobile;
	@FindBy(using = "(//input[@value='  Save  '])[1]",how = How.XPATH)
	private WebElement saveButton;
	
	//initialization
	public createLeadPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	//utilization
	public void saluteDropdown(String value) {
		select(salute, value);
	}
	
	public void firstName(String fname) {
		firstName.sendKeys(fname);
	}
	public void lastName(String lname) {
		lastName.sendKeys(lname);
	}
	public void company(String company) {
		companyName.sendKeys(company);
	}
	public void mobile(String mobile) {
		this.mobile.sendKeys(mobile);
	}
	public void saveButton() {
		saveButton.click();
	}
	
	
}
