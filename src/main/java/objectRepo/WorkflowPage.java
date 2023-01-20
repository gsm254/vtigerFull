package objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class WorkflowPage {
	@FindBy(using = "new_workflow", how = How.ID)
	private WebElement newWorkflowBtn;
	@FindBy(using = "new_workflow_popup_save", how = How.ID)
	private WebElement createPopupBtn;
	
	@FindBy(using = "save_description", how = How.ID)
	private WebElement descriptionField;
	@FindBy(using = "save_submit", how = How.ID)
	private WebElement saveBtn;
	
	
	

	public WorkflowPage(WebDriver driver) {
		PageFactory.initElements(driver, this);

	}	
	
	public WebElement getNewWorkflowBtn() {
		return newWorkflowBtn;
	}


	


	public WebElement getCreatePopupBtn() {
		return createPopupBtn;
	}

	


	public WebElement getDescriptionField() {
		return descriptionField;
	}


	public WebElement getSaveBtn() {
		return saveBtn;
	}


}
