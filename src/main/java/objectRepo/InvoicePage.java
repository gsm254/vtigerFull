package objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import genericLibraries.WebDriverUtility;

public class InvoicePage extends WebDriverUtility{
	@FindBy(using = "//img[@title='Create Invoice...']", how = How.XPATH)
	private WebElement createInvoice;
	@FindBy(using = "search_text", how = How.NAME)
	private WebElement searchInvoice;
	@FindBy(using = "search_field", how = How.NAME)
	private WebElement searchDropdown;
	@FindAll({@FindBy(using = "submit", how = How.NAME),@FindBy(using = "search", how = How.NAME)})
	private WebElement searchNowButton;

	// initialization
	public InvoicePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	// utilization

	public WebElement getCreateInvoice() {
		return createInvoice;
	}

	public void searchOrg(String text, String visibleText) {
		searchInvoice.sendKeys(text);
		select(visibleText,searchDropdown);
		searchNowButton.click();
	}

}
