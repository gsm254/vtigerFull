package objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CreateOrgPage {
	// declaration
	@FindBy(using = "accountname", how = How.NAME)
	private WebElement orgName;
	@FindBy(using = "website", how = How.NAME)
	private WebElement website;

	@FindBy(using = "(//input[@value='  Save  '])[1]", how = How.XPATH)
	private WebElement saveButton;

	// initialization
	public CreateOrgPage(WebDriver driver) {
		super();
		PageFactory.initElements(driver, this);
	}

	// utilization

	public void orgName(String orgName) {
		this.orgName.sendKeys(orgName);
	}

	public void website(String website) {
		this.website.sendKeys(website);
	}

	public void saveButton() {
		saveButton.click();
	}

}
