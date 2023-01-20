package objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import genericLibraries.WebDriverUtility;

public class LeadPage extends WebDriverUtility{
	//declaration
	@FindBy(using = "//img[@title='Create Lead...']", how = How.XPATH)
	private WebElement createLead;
	@FindBy(using = "search_text", how = How.NAME)
	private WebElement searchLead;
	@FindBy(using = "search_field", how = How.NAME)
	private WebElement searchDropdown;
	@FindBy(using = "submit", how = How.NAME)
	private WebElement searchNowButton;

	//initialization
	public LeadPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
   //utilization

	public WebElement getCreateLead() {
		return createLead;
	}

	public void searchLead(String text,String value) {
		searchLead.sendKeys(text);
		select(searchDropdown, value);
		searchNowButton.click();
	}
	
	
	
	
}
