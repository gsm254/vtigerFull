package objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LeadInfoPage {
	
	// declaration
	@FindBy(using = "dvHeaderText", how = How.CLASS_NAME)
	private WebElement headerText;
	@FindBy(using = "Edit", how = How.NAME)
	private WebElement editBtn;

	@FindBy(using = "//td[.='Lead No']/following-sibling::td", how = How.XPATH)
	private WebElement leadId;
	
	

	// initialization
	public LeadInfoPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}
	
	
	//utilization
	public WebElement getheaderText() {
		return headerText;
	}
	public WebElement editBtn() {
		return editBtn;
	}
	
	public String getLeadId() {
		return leadId.getText();
	}
	
	public String getLeadInfo() {
		return getheaderText().getText();
	}
	

}
