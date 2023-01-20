package objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import genericLibraries.WebDriverUtility;

public class RecycleBinPage extends WebDriverUtility {
	// declaration
	
	@FindBy(using = "search_text", how = How.NAME)
	private WebElement searchOrg;
	@FindBy(using = "search_field", how = How.NAME)
	private WebElement searchDropdown;
	@FindAll({ @FindBy(using = "submit", how = How.NAME), @FindBy(using = "search", how = How.NAME) })
	private WebElement searchNowButton;

	// initialization
	public RecycleBinPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	// utilization

	
	public void searchOrg(String text, String visibleText) {
		searchOrg.sendKeys(text);
		select(visibleText, searchDropdown);
		searchNowButton.click();
	}
	

}
