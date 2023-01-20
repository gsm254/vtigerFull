package objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class OrgInfoPage {
	
	// declaration
	@FindBy(using = "dvHeaderText", how = How.CLASS_NAME)
	private WebElement headerText;
	@FindBy(using = "//td[.='Organization No']/following-sibling::td", how = How.XPATH)
	private WebElement orgNo;
	@FindBy(using = "Contacts", how = How.LINK_TEXT)
	private WebElement contactsLink;
	@FindBy(using = "(//input[@name='Delete'])[1]",how = How.XPATH)
private WebElement delete;

	// initialization
	public OrgInfoPage(WebDriver driver) {
				PageFactory.initElements(driver, this);
			}

	// utilization
	public WebElement getheaderText() {
		return headerText;
	}
	public WebElement delete() {
		return delete;
	}

	public String getOrgNo() {
		return orgNo.getText();
	}

	public String getOrgInfo() {
		return getheaderText().getText();
	}
	
	

}
