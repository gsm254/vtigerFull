package scriptsUsingPom.Products;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import genericLibraries.BrowserLaunch;
import genericLibraries.ExcelUtility;
import genericLibraries.FileUtility;
import genericLibraries.JavaUtility;
import genericLibraries.WebDriverUtility;
import objectRepo.CreateInvoicePage;
import objectRepo.CreateProductPage;
import objectRepo.HomePage;
import objectRepo.InvoicePage;
import objectRepo.LoginPage;
import objectRepo.OrganizationPage;
import objectRepo.ProductsPage;

public class CreatProductInvoiceAssetTest {

	public static void main(String[] args) throws IOException {
		WebDriver driver = BrowserLaunch.chrome();

		ExcelUtility eLib = new ExcelUtility(driver);
		FileUtility fLib = new FileUtility();
		WebDriverUtility wLib = new WebDriverUtility(driver);
		// DatabaseUtility dLib = new DatabaseUtility();
		JavaUtility jLib = new JavaUtility();

		int random = jLib.getRandomNum();
		String product = eLib.readDataFromExcel("TS05", 0, 1) + random;
		String unitprice = eLib.readDataFromExcel("TS05", 1, 1);
		String stock = eLib.readDataFromExcel("TS05", 2, 1);

		wLib.maximize();
		driver.get(fLib.readDataFromProperty("url"));

		// loginpage
		LoginPage lp = new LoginPage(driver);
		lp.Login(fLib.readDataFromProperty("username"), fLib.readDataFromProperty("password"));

		// homepage
		HomePage hp = new HomePage(driver);
		hp.getProductLink().click();

		ProductsPage pp = new ProductsPage(driver);
		pp.getCreateProduct().click();

		CreateProductPage cpp = new CreateProductPage(driver);
		cpp.getProductName().sendKeys(product);
		cpp.getUnitPrice().sendKeys(unitprice);
		cpp.getQtystock().sendKeys(product);

		wLib.scrollIntoViewAndClick(cpp.getSaveButton());
		
		hp.more(wLib);
		hp.getInvoiceLink().click();
		
		InvoicePage ip = new InvoicePage(driver);
		ip.getCreateInvoice().click();
		
		CreateInvoicePage cip =new CreateInvoicePage(driver);
		
		cip.getSubjectName().sendKeys(" ");
		cip.getOrgnizationImg().click();
		wLib.switchToWindow("Accounts");
		OrganizationPage op = new OrganizationPage(driver);
		op.searchOrg("","" );
		driver.findElement(By.linkText("")).click();
		
		wLib.switchToWindow("Invoice");
		
		
		
		

	}

}
