package pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import genericlibs.WebActionUtil;

public class OrderDetailPage extends BasePage
{
	@FindBy(xpath="//tbody/tr/td[@class='cart_product']/a")
	private List<WebElement> productIdList;
	
	String deleteProductXpath = "//tbody/tr/td/a[contains(@href,'id_product=pid')]/../..//i[@class='icon-trash']";
	final static String pageTitle = "Order - My Store";
	final static String pageUrl = "http://automationpractice.com/index.php?controller=order";
	
	public OrderDetailPage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver,webActionUtil,pageTitle, pageUrl);
	}
	
	public boolean isProductDisplayed(String productId) {
		productId="id_product="+productId;
		for(WebElement product:productIdList) {
			if(product.getAttribute("href").contains(productId)) {
				return true;
			}
		}
		return false;
	}
	
	public void deleteProduct(String productId) {
		String prodid = productId;
		productId="id_product="+productId;
		for(WebElement product:productIdList) {
			if(product.getAttribute("href").contains(productId)) {
				deleteProductXpath=deleteProductXpath.replace("pid",prodid);
				WebElement deleteIcon=driver.findElement(By.xpath(deleteProductXpath));
				webActionUtil.elementClick(deleteIcon);
				break;
			}
		}
	}
}
