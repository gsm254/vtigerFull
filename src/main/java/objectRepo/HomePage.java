package objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import genericLibraries.WebDriverUtility;

public class HomePage {

	// declaration
	@FindBy(using = "Leads", how = How.LINK_TEXT)
	private WebElement leadLink;
	@FindBy(using = "Organizations", how = How.LINK_TEXT)
	private WebElement orgLink;
	@FindBy(using = "Opportunities", how = How.LINK_TEXT)
	private WebElement oppoLink;
	@FindBy(using = "Contacts", how = How.LINK_TEXT)
	private WebElement contactsLink;
	@FindBy(using = "//span[.=' Administrator']/../following-sibling::td[1]", how = How.XPATH)
	private WebElement adminImage;
	@FindBy(using = "Sign Out", how = How.LINK_TEXT)
	private WebElement signOutLink;
	@FindBy(using = "More", how = How.LINK_TEXT)
	private WebElement more;
	@FindBy(using = "Recycle Bin", how = How.LINK_TEXT)
	private WebElement recyclebin;
	@FindBy(using = "Products", how = How.LINK_TEXT)
	private WebElement productLink;
	@FindBy(using = "Invoice", how = How.LINK_TEXT)
	private WebElement invoiceLink;
	@FindBy(using = "Assets", how = How.LINK_TEXT)
	private WebElement assetsLink;
	@FindBy(using = "//span[@class='userName']/../following-sibling::td[3]", how = How.XPATH)
	private WebElement settingImg;
	@FindBy(using = "CRM Settings", how = How.LINK_TEXT)
	private WebElement crmSettings;
	

	// initialization
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);

	}

	// utilization
	public WebElement getLeadLink() {
		return leadLink;
	}
	public WebElement getProductLink() {
		return productLink;
	}
	

	public WebElement getInvoiceLink() {
		return invoiceLink;
	}

	public WebElement getAssetsLink() {
		return assetsLink;
	}

	public WebElement getOrgLink() {
		return orgLink;
	}

	public WebElement getOppoLink() {
		return oppoLink;
	}

	public WebElement getContactsLink() {
		return contactsLink;
	}

	// business libraries
	public void signout(WebDriverUtility wlib) {
		wlib.mouseHover(adminImage);
		signOutLink.click();
	}
	public void crmSettings(WebDriverUtility wlib) {
		wlib.mouseHover(settingImg);
		crmSettings.click();
	}
	
	public void more(WebDriverUtility wlib) {
		wlib.mouseHover(more);
		
	}
	public WebElement getRecycleBin() {
		return recyclebin;
	}

}
