package pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import genericlibs.WebActionUtil;

public class ProductsListPage extends BasePage
{
	//Creating WebELements
		@FindBy(xpath="//li//a[@class='product_img_link']")
		private List<WebElement> productIdList;
		
		final static String pageTitle = "Dresses - My Store";
		final static String pageUrl = "http://automationpractice.com/index.php?id_category=8&controller=category";
		
		public ProductsListPage(WebDriver driver, WebActionUtil webActionUtil) {
			super(driver,webActionUtil,pageTitle, pageUrl);
		}
		
		/**
		 * Checks Whether the ProductId is Avaliable in the List of Products of the Application
		 * If Available clicks on that Product
		 * @param productId
		 * @return next Page Object i.e PDP Object
		 */
		public ProductDetailsPage selectProduct(String productId) {
			productId="id_product="+productId;
			for(WebElement product:productIdList) {
				if(product.getAttribute("href").contains(productId)) {
					//webActionUtil.elementClick(product);
					webActionUtil.jsClick(product);
					break;
				}
			}
			return new ProductDetailsPage(driver,webActionUtil);
		}
}
