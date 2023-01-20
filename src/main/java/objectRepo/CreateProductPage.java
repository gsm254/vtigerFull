package objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CreateProductPage {

	@FindBy(using = "productname", how = How.NAME)
	private WebElement productName;
	@FindBy(using = "unit_price", how = How.NAME)
	private WebElement unitPrice;
	@FindBy(using = "qtyinstock", how = How.NAME)
	private WebElement qtystock;

	@FindBy(using = "(//input[@value='  Save  '])[1]", how = How.XPATH)
	private WebElement saveButton;

	// initialization
	public CreateProductPage(WebDriver driver) {
		super();
		PageFactory.initElements(driver, this);
	}

	public WebElement getProductName() {
		return productName;
	}

	public WebElement getUnitPrice() {
		return unitPrice;
	}

	public WebElement getQtystock() {
		return qtystock;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}
	
	

}
