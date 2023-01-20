package objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CreateInvoicePage {
	@FindBy(using = "subject", how = How.NAME)
	private WebElement subjectName;
	@FindBy(using = "qty1", how = How.NAME)
	private WebElement stockQty;
	@FindBy(using = "//input[@name='account_id']/following-sibling::img", how = How.XPATH)
	private WebElement orgnizationImg;
	@FindBy(using = "//textarea[@name='bill_street']", how = How.XPATH)
	private WebElement billingAddress;
	@FindBy(using = "//b[.='Copy Billing address']/../input", how = How.XPATH)
	private WebElement copyBillAddressRadioBtn;
	@FindBy(using = "//input[@name='productName1']/../img", how = How.XPATH)
	private WebElement productImg;
	@FindBy(using = "(//input[@value='  Save  '])[2]", how = How.XPATH)
	private WebElement saveBtn;
	
	public CreateInvoicePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		
	}

	public WebElement getSubjectName() {
		return subjectName;
	}

	public WebElement getOrgnizationImg() {
		return orgnizationImg;
	}

	public WebElement getBillingAddress() {
		return billingAddress;
	}

	public WebElement getCopyBillAddressRadioBtn() {
		return copyBillAddressRadioBtn;
	}

	public WebElement getProductImg() {
		return productImg;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public WebElement getStockQty() {
		return stockQty;
	}

	
	
	
	
}
