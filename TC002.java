package testscripts;

import org.testng.Assert;
import org.testng.annotations.Test;

/*import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status; */
import genericlibs.GenericXLLibrary;
import genericlibs.Utility;
import pom.HomePage;
import pom.OrderDetailPage;
import pom.ProductDetailsPage;
import pom.ProductsListPage;

//Taking Data From ExcelSheet and Verifying whether
//the deleted product from ODP is not displayed in the ODP Page

public class TC002 extends BaseTest
{
	@Test(description="Test case to verify if the deleted product is not displayed in ODP page")
	public void testToDeletAddedProductFromODP() {
		
		String path="";
		String tcName="TC002";
	//	ExtentTest test = extentReports.createTest(tcName);
		HomePage hp = new HomePage(driver,webActionUtil);
	/*	test.log(Status.PASS, "Home Page Displayed");
		Utility.addPicToER(test, driver, tcName, path); */

		//Getting testdata from XL
		String sheetName = "TC002";
		String menuName = GenericXLLibrary.getData(XL_PATH, sheetName, 1, 0);
		String productId = GenericXLLibrary.getData(XL_PATH, sheetName, 1, 1);
		productId = Utility.split(productId);
		String increaseQuantity = GenericXLLibrary.getData(XL_PATH, sheetName, 1, 2);
		int inQ=Integer.parseInt(Utility.split(increaseQuantity));
		String decreaseQuantity = GenericXLLibrary.getData(XL_PATH, sheetName, 1, 3);
		int deQ=Integer.parseInt(Utility.split(decreaseQuantity));
		String size = GenericXLLibrary.getData(XL_PATH, sheetName, 1, 4);
		String color = GenericXLLibrary.getData(XL_PATH, sheetName, 1, 5);		
		
		
		
		ProductsListPage productListPage = hp.clickOnMenu(menuName);
	/*	test.log(Status.PASS, "Clicked on "+menuName);
		Utility.addPicToER(test, driver, tcName, path); */
		
		ProductDetailsPage pdp = productListPage.selectProduct(productId);
	/*	test.log(Status.PASS, "Clicked on Product with PID "+productId);
		Utility.addPicToER(test, driver, tcName, path); */
		
		OrderDetailPage odp = pdp.addSelectedItemToKart(inQ, deQ, size, color);
	/*	test.log(Status.PASS, "Added Items to Cart");
		Utility.addPicToER(test, driver, tcName, path); */
		
		Assert.assertEquals(odp.isProductDisplayed(productId), true);	
	/*	test.log(Status.PASS, "Product is displayed in ODP");
		Utility.addPicToER(test, driver, tcName, path); */
		
		odp.deleteProduct(productId);
	/*	test.log(Status.PASS, "Deleted Product");
		Utility.addPicToER(test, driver, tcName, path); */
		
		Utility.sleepInSeconds(5);
		Assert.assertEquals(odp.isProductDisplayed(productId), false);
	/*	test.log(Status.PASS, "Product is not Displayed");
		Utillity.addPicToER(test, driver, tcName, path); */
		
	}
}
