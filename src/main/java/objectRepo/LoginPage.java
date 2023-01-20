package objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	// declaration of elements
	@FindBy(using = "user_name", how = How.NAME)
	private WebElement username;
	@FindBy(using = "user_password", how = How.NAME)
	private WebElement password;
	@FindBy(using = "submitButton", how = How.ID)
	private WebElement submitButton;

	// initialization of elements
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// utilization
	public void Login(String username, String password) {
		this.username.sendKeys(username);
		this.password.sendKeys(password);
		this.submitButton.click();
	}
}
