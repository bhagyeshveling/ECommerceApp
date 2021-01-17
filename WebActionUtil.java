package genericlibs;

import java.util.Set;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

public class WebActionUtil 
{
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	Actions actions;
	
	/**
	 * 
	 * @param driver WebDriver Reference
	 * @param ETO Explicit TimeOut
	 */
	public WebActionUtil(WebDriver driver, long ETO) {
		this.driver=driver;
		wait = new WebDriverWait(driver,ETO);
		js = (JavascriptExecutor)driver;
		actions = new Actions(driver);
	}
	
	/**
	 * 
	 * @param targetElement to enter the keys for
	 * @param text to be entered
	 * 
	 */
	public void enterData(WebElement targetElement, String text) {
		targetElement.clear();
		targetElement.sendKeys(text);
	}
	
	/**
	 * 
	 * @param targetElement to click
	 * 
	 */
	public void elementClick(WebElement targetElement) {
		wait.until(ExpectedConditions.elementToBeClickable(targetElement));
		targetElement.click();
	}
	
	/**
	 * 
	 * @param targetElement to click
	 * 
	 */
	public void jsClick(WebElement targetElement) {
		js.executeScript("arguments[0].click();", targetElement);
	}
	
	/**
	 * Scrolls Down to the End of the Page
	 */
	public void scrollToEnd() {
		js.executeScript("window.scrollTo(0,docoment.body.scrollHeight);");
	}
	
	/**
	 * Scrolls Up to the Top of the Page
	 */
	public void scrollToTop() {
		js.executeScript("window.scrollTo(0,-docoment.body.scrollHeight);");
	}
	
	/**
	 * @param source element to drag
	 * @param target element to drop
	 */
	public void dragAndDrop(WebElement source, WebElement target) {
		actions.dragAndDrop(source, target).perform();
	}
	
	/**
	 * @param targetElement to hover the mouse onto
	 */
	public void moveToElement(WebElement targetElement) {
		actions.moveToElement(targetElement).perform();
	}
	
	/**
	 * @param targetListBox to select the text
	 * @param text to be selected
	 */
	public void selectByText(WebElement targetListBox, String text) {
		Select s = new Select(targetListBox);
		s.selectByVisibleText(text);
	}
	
	/**
	 * 
	 * @param nameIdOrIndex String Name or String Id or String index
	 */
	public void switchToFrame(String nameIdOrIndex) {
		try {
			int index = Integer.parseInt(nameIdOrIndex);
			driver.switchTo().frame(index);
		} catch(Exception e) {
			driver.switchTo().frame(nameIdOrIndex);
		}
	}
	
	/**
	 * Closes all the Child Window Ids
	 * Assuming that the current window is the Parent Window
	 */
	public void closeAllChildWindows() {
		Set<String> allWindowIds=driver.getWindowHandles();
		String parentWid=driver.getWindowHandle();
		allWindowIds.remove(parentWid);
		for(String wid:allWindowIds) {
			driver.switchTo().window(wid);
			driver.close();
		}
		driver.switchTo().window(parentWid);
	}
	/**
	 * 
	 * @param wid to be Switched To
	 */
	public void swithToAWindow(String wid) {
		driver.switchTo().window(wid);
	}	
}
