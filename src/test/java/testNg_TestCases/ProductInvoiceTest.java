package testNg_TestCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericLibraries.BaseClass;
import genericLibraries.BrowserLaunch;
import genericLibraries.ExcelUtility;
import genericLibraries.FileUtility;
import genericLibraries.JavaUtility;
import genericLibraries.WebDriverUtility;
import objectRepo.CreateInvoicePage;
import objectRepo.CreateProductPage;
import objectRepo.HomePage;
import objectRepo.InvoiceInfoPage;
import objectRepo.InvoicePage;
import objectRepo.LoginPage;
import objectRepo.OrganizationPage;
import objectRepo.ProductsPage;
@Listeners(genericLibraries.ListenerImplementation.class)
public class ProductInvoiceTest extends BaseClass {

	static ProductsPage productPage;
	static CreateProductPage createProductPage;
	static InvoicePage invoicePage;
	static CreateInvoicePage createinvoicePage;
	static InvoiceInfoPage invoiceInfoPage;

	

	@Test
	public void createProduct() throws NumberFormatException, IOException {

		productPage = new ProductsPage(driver);
		createProductPage = new CreateProductPage(driver);

		int random = jLib.getRandomNum();
		String product = eLib.readDataFromExcel("TS05", 0, 1) + random;
		String unitprice = eLib.readDataFromExcel("TS05", 1, 1);
		String stock = eLib.readDataFromExcel("TS05", 2, 1);

		wLib.maximize();

		homePage.getProductLink().click();

		productPage.getCreateProduct().click();

		createProductPage.getProductName().sendKeys(product);
		createProductPage.getUnitPrice().sendKeys(unitprice);
		createProductPage.getQtystock().sendKeys(stock);

		wLib.scrollIntoViewAndClick(createProductPage.getSaveButton());

		homePage.more(wLib);
		homePage.getInvoiceLink().click();

		String subject = eLib.readDataFromExcel("TS05", 0, 5) + random;
		String org = eLib.readDataFromExcel("TS05", 1, 5);
		String orgType = eLib.readDataFromExcel("TS05", 1, 4);
		String billAddress = eLib.readDataFromExcel("TS05", 2, 5);
		String qty = eLib.readDataFromExcel("TS05", 3, 5);

		invoicePage = new InvoicePage(driver);
		createinvoicePage = new CreateInvoicePage(driver);
		invoiceInfoPage = new InvoiceInfoPage(driver);

		invoicePage.getCreateInvoice().click();
		createinvoicePage.getSubjectName().sendKeys(subject);
		createinvoicePage.getOrgnizationImg().click();
		wLib.switchToWindow("Accounts");
		OrganizationPage op = new OrganizationPage(driver);
		op.searchOrg(org, orgType);
		wLib.waitForPageLoad(fLib);
		driver.findElement(By.linkText(org)).click();
		wLib.acceptAlert();

		wLib.switchToWindow("Invoice");

		createinvoicePage.getBillingAddress().sendKeys(billAddress);
		wLib.scrollIntoViewAndClick(createinvoicePage.getCopyBillAddressRadioBtn());

		createinvoicePage.getProductImg().click();
		wLib.switchToWindow("Products");

		productPage.searchOrg(product, "Product Name");
		driver.findElement(By.linkText(product)).click();

		wLib.switchToWindow("Invoice");

		createinvoicePage.getStockQty().sendKeys(qty);
		createinvoicePage.getSaveBtn().click();
		String invoiceInfo = invoiceInfoPage.getInvoiceInfo();
		Assert.assertTrue(invoiceInfo.contains(subject));
		/*
		 * if (invoiceInfoPage.getInvoiceInfo().contains(subject)) {
		 * System.out.println("invoice generated for " + subject); } else {
		 * System.out.println("invoice not generated generated for " + subject); }
		 */

	}
}
