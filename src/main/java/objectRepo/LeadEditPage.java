package objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LeadEditPage {
	@FindBy(using = "mobile", how = How.NAME)
	private WebElement mobileEdit;
	@FindBy(using = "(//input[@value='  Save  '])[1]", how = How.XPATH)
	private WebElement saveBtn;
	
	public LeadEditPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void mobileEdit(String mNo) {
		mobileEdit.clear();
		mobileEdit.sendKeys(mNo);
	}
	
	public WebElement saveBtn() {
		return saveBtn;
	}
	

}
