package genericlibs;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ToTakeScreenShot implements AutoConstants
{
	public static String getSnapshot(WebDriver driver, String testCaseName) {
		String timeStamp=LocalDateTime.now().toString().replace(':','-');
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		String sysPath=System.getProperty("user.dir");
		String imageFilePath=sysPath+IMAGE_PATH+testCaseName+timeStamp+".png";
		File dest=new File(imageFilePath);
		try {
			FileUtils.copyFile(src,dest);
			return imageFilePath;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
