package objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import genericLibraries.WebDriverUtility;

public class ContactsPage extends WebDriverUtility{
	// declaration
	@FindBy(using = "//img[@title='Create Contact...']", how = How.XPATH)
	private WebElement createContact;
	@FindBy(using = "search_text", how = How.NAME)
	private WebElement searchContact;
	@FindBy(using = "search_field", how = How.NAME)
	private WebElement searchDropdown;
	@FindBy(using = "submit", how = How.NAME)
	private WebElement searchNowButton;

	// initialization
	public ContactsPage(WebDriver driver) {
			super(driver);
			PageFactory.initElements(driver, this);
		}
	// utilization

	public WebElement getCreateContact() {
		return createContact;
	}

	public void searchContact(String text, String value) {
		searchContact.sendKeys(text);
		select(searchDropdown, value);
		searchNowButton.click();
	}

}
