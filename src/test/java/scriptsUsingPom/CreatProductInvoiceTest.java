package scriptsUsingPom;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import genericLibraries.BrowserLaunch;
import genericLibraries.ExcelUtility;
import genericLibraries.FileUtility;
import genericLibraries.JavaUtility;
import genericLibraries.WebDriverUtility;
import objectRepo.AssetsPage;
import objectRepo.CreateInvoicePage;
import objectRepo.CreateProductPage;
import objectRepo.HomePage;
import objectRepo.InvoiceInfoPage;
import objectRepo.InvoicePage;
import objectRepo.LoginPage;
import objectRepo.OrganizationPage;
import objectRepo.ProductsPage;

public class CreatProductInvoiceTest {

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
		
		String subject = eLib.readDataFromExcel("TS05", 0, 5) + random;
		String org = eLib.readDataFromExcel("TS05", 1, 5);
		String orgType = eLib.readDataFromExcel("TS05", 1, 4);
		String billAddress = eLib.readDataFromExcel("TS05", 2, 5);
		String qty = eLib.readDataFromExcel("TS05", 3, 5);

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
		cpp.getQtystock().sendKeys(stock);

		wLib.scrollIntoViewAndClick(cpp.getSaveButton());
		
		hp.more(wLib);
		hp.getInvoiceLink().click();
		
		InvoicePage ip = new InvoicePage(driver);
		ip.getCreateInvoice().click();
		
		CreateInvoicePage cip =new CreateInvoicePage(driver);
		
		cip.getSubjectName().sendKeys(subject);
		cip.getOrgnizationImg().click();
		wLib.switchToWindow("Accounts");
		OrganizationPage op = new OrganizationPage(driver);
		op.searchOrg(org, orgType);
		wLib.waitForPageLoad();
		driver.findElement(By.linkText(org)).click();
		wLib.acceptAlert();
		
		wLib.switchToWindow("Invoice");
		
		cip.getBillingAddress().sendKeys(billAddress);
		wLib.scrollIntoViewAndClick(cip.getCopyBillAddressRadioBtn());
		
		cip.getProductImg().click();
		wLib.switchToWindow("Products");
		
		pp.searchOrg(product,"Product Name" );
		driver.findElement(By.linkText(product)).click();
		
		wLib.switchToWindow("Invoice");
		
		cip.getStockQty().sendKeys(qty);
		cip.getSaveBtn().click();
		
		InvoiceInfoPage iip = new InvoiceInfoPage(driver);
		if(iip.getInvoiceInfo().contains(subject)) {
			System.out.println("invoice generated for "+subject);
		}
		else {
			System.out.println("invoice not generated generated for "+subject);
		}
		
		
		
		
		

	}

}
