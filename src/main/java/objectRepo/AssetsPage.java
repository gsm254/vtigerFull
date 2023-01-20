package objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import genericLibraries.WebDriverUtility;

public class AssetsPage extends WebDriverUtility {
	
	// declaration
		@FindBy(using = "//img[@title='Create Asset...']", how = How.XPATH)
		private WebElement createAsset;
		@FindBy(using = "search_text", how = How.NAME)
		private WebElement searchAsset;
		@FindBy(using = "search_field", how = How.NAME)
		private WebElement searchDropdown;
		@FindAll({@FindBy(using = "submit", how = How.NAME),@FindBy(using = "search", how = How.NAME)})
		private WebElement searchNowButton;
		
		public AssetsPage(WebDriver driver) {
			super(driver);
			PageFactory.initElements(driver, this);
		}

		public WebElement getCreateAsset() {
			return createAsset;
		}

		public void searchAsset(String text, String visibleText) {
			searchAsset.sendKeys(text);
			select(visibleText,searchDropdown);
			searchNowButton.click();
		}

}
