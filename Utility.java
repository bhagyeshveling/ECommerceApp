package genericlibs;

import org.openqa.selenium.WebDriver;

/*import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder; */

public class Utility 
{
	public static void sleepInSeconds(int seconds) {
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static String split(String number) {
		return number.split("\\.")[0];
	}
	
	/* public static void addPicToER(ExtentTest test,WebDriver driver, String tcName, String path) {
		path=ToTakeScreenShot.getSnapshot(driver, tcName);
		test.pass(MediaEntityBuilder.createScreenCaptureFromPath(path).build());
	} */
}
