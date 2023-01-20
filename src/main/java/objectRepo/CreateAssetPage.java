package objectRepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CreateAssetPage {
	@FindBy(using = "accountname", how = How.NAME)
	private WebElement orgName;
	@FindBy(using = "website", how = How.NAME)
	private WebElement website;
	

}
