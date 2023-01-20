package genericLibraries;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility extends JavaUtility {
	
	WebDriver driver;

	/**
	 * 
	 * @param driver
	 */
	public WebDriverUtility(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * this method is maximize the window
	 */
	public void maximize() {
		driver.manage().window().maximize();
	}

	/**
	 * this method is for implicit wait
	 * 
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public void waitForPageLoad(FileUtility fLib) throws NumberFormatException, IOException {
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(fLib.readDataFromProperty("implicit.wait")),
				TimeUnit.SECONDS);
	}

	/**
	 * explicit wait till visibility
	 * 
	 * @param element
	 */
	public WebElement elementVisibility(WebElement element,FileUtility fLib) throws IOException{
		WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(fLib.readDataFromProperty("explicit.wait")));
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * select options by value
	 * 
	 * @param element
	 * @param value
	 */
	public void select(WebElement element, String value) {
		Select s = new Select(element);
		s.selectByValue(value);
	}

	/**
	 * select options by visible text
	 * 
	 * @param element
	 * @param value
	 */

	public void select(String visible, WebElement element) {
		Select s = new Select(element);
		s.selectByVisibleText(visible);
	}

	/**
	 * select options by index
	 * 
	 * @param element
	 * @param value
	 */

	public void select(WebElement element, int index) {
		Select s = new Select(element);
		s.selectByIndex(index);
	}

	/**
	 * mouse hover action
	 * 
	 * @param element
	 */
	public void mouseHover(WebElement element) {
		Actions act = new Actions(driver);

		act.moveToElement(element).perform();
	}

	/**
	 * drag and drop action
	 * 
	 * @param src
	 * @param dest
	 */

	public void dragAndDrop(WebElement src, WebElement dest) {
		Actions act = new Actions(driver);

		act.dragAndDrop(src, dest).perform();
	}

	/**
	 * right click action
	 * 
	 * @param element
	 */
	public void rightClick(WebElement element) {
		Actions act = new Actions(driver);

		act.contextClick(element).perform();
	}

	/**
	 * double click action
	 * 
	 * @param element
	 */

	public void doubleClick(WebElement element) {
		Actions act = new Actions(driver);

		act.doubleClick(element).perform();
	}

	/**
	 * enter press key using action class
	 */
	public void pressEnterKey() {
		Actions act = new Actions(driver);
		act.sendKeys(Keys.ENTER).perform();
	}

	/**
	 * switch to frame using name or id
	 * 
	 * @param NameOrId
	 */
	public void switchToFrame(String NameOrId) {
		driver.switchTo().frame(NameOrId);
	}

	/**
	 * switch to frame using index
	 * 
	 * @param index
	 */
	public void switchToFrame(int index) {
		driver.switchTo().frame(index);
	}

	/**
	 * switch to frame using element address
	 * 
	 * @param element
	 */
	public void switchToFrame(WebElement element) {
		driver.switchTo().frame(element);
	}
	/**
	 * switch to parent frame from child frame
	 * 
	 * @param element
	 */
	public void switchToParentFrame() {
		driver.switchTo().parentFrame();
	}
	/**
	 * switch to main frame from the any frame
	 * 
	 * @param element
	 */
	public void switchToMainFrame() {
		driver.switchTo().defaultContent();
	}
	

	/**
	 * to accept in alert pop up
	 */
	public void acceptAlert() {
		driver.switchTo().alert().accept();
	}

	/**
	 * to dismiss the pop up
	 */
	public void dismissAlert() {
		driver.switchTo().alert().dismiss();
	}

	/**
	 * to send data to the pop up
	 * 
	 * @param data
	 */
	public void sendkeysAlert(String data) {
		driver.switchTo().alert().sendKeys(data);
	}

	/**
	 * to get the information from the pop up
	 * 
	 * @return
	 */
	public String getAlertText() {
		String info = driver.switchTo().alert().getText();
		return info;
	}

	/**
	 * to switch the window by comparing the title
	 * 
	 * @param title
	 */
	public void switchToWindow(String title) {
		Set<String> windows = driver.getWindowHandles();
		// String pwindow = driver.getWindowHandle();

		Iterator<String> it = windows.iterator();

		while (it.hasNext()) {
			String winId = it.next();

			String currentTitle = driver.switchTo().window(winId).getTitle();

			if (currentTitle.contains(title)) {
				break;
			}
		}
	}

	public static final String FAIL = "Failed";

	/**
	 * this method to get the screenshot 
	 * @param methodName
	 * @return
	 * @throws IOException
	 */
	public String getScreenshot(String methodName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;

		String folder = "./screenshot/";
		String date = getSystemDate();
		String name = folder + methodName + FAIL + date + ".png";

		File src = ts.getScreenshotAs(OutputType.FILE);
		File dst = new File(name);
		FileHandler.copy(src, dst);
		return dst.getAbsolutePath();

	}

	public void scrollIntoView(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}

	public void scrollIntoViewAndClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
		element.click();
	}

	public void scrollBy(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		int y = element.getLocation().getY();
		js.executeScript("window.scrollBy(0," + y + ")", element);
	}

}
