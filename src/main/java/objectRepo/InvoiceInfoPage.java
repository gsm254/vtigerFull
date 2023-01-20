package objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class InvoiceInfoPage {

	@FindBy(using = "lvtHeaderText", how = How.CLASS_NAME)
	private WebElement headerText;
	@FindBy(using = "//td[.='Invoice No']/following-sibling::td", how = How.XPATH)
	private WebElement invoiceNo;
	
	@FindBy(using = "(//input[@name='Delete'])[1]", how = How.XPATH)
	private WebElement delete;

	// initialization
	public InvoiceInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// utilization
	public WebElement getheaderText() {
		return headerText;
	}

	public WebElement delete() {
		return delete;
	}

	public String getInvoiceNo() {
		return invoiceNo.getText();
	}

	public String getInvoiceInfo() {
		return getheaderText().getText();
	}

}
