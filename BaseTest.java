package testscripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

/*import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter; */
import genericlibs.AutoConstants;
import genericlibs.ToTakeScreenShot;
import genericlibs.WebActionUtil;
import pom.HomePage;
import pom.LoginPage;

import genericlibs.AutoConstants;

public class BaseTest implements AutoConstants
{
	public WebDriver driver;
	public WebActionUtil webActionUtil;
	
	//public static final ExtentReports extentReports = new ExtentReports();
	
	/* @BeforeSuite
	public void createReporting() {
		String sysPath = System.getProperty("user.dir");
		ExtentSparkReporter sparkReporter= new ExtentSparkReporter(sysPath+EXTENT_FILE_PATH);
		extentReports.attachReporter(sparkReporter);
	} */
	
	
	
	@Parameters({"browserName","appURL","ito","eto"})
	@BeforeClass
	public void openApp(@Optional(DEFAULT_BROWSER)String browserName,
						@Optional(DEFAULT_URL)String appURL,
						@Optional(ITO)String ito,
						@Optional(ETO)String eto) {
		if(browserName.equalsIgnoreCase("chrome")) {
			System.setProperty(CHROME_KEY, CHROME_PATH);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);
			
		} else if(browserName.equalsIgnoreCase("firefox")){
			System.setProperty(GECKO_KEY, GECKO_PATH);
			FirefoxOptions options = new FirefoxOptions();
			options.addPreference("dom.webnotifications.enabled", false);
			driver = new FirefoxDriver(options);
		} else {
			Assert.fail("Sorry, This Browser is not Supported");
		}
		long implicit = Long.parseLong(ito);
		long explicit = Long.parseLong(eto);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(implicit, TimeUnit.SECONDS);
		driver.get(appURL);
		webActionUtil = new WebActionUtil(driver, explicit);
	}
	
	@Parameters({"username", "password"})
	@BeforeMethod
	public void loginToApp(@Optional(DEFAULT_USERNAME)String username,
						   @Optional(DEFAULT_PASSWORD)String password) {
		LoginPage lp = new LoginPage(driver, webActionUtil);
		lp.login(username, password);
	}
	
	@AfterMethod
	public void logoutFromApp(ITestResult result) {
		String testCaseName = result.getName();
		if(ITestResult.FAILURE==result.getStatus()) {
			String imageFilePath=ToTakeScreenShot.getSnapshot(driver, testCaseName);
			Reporter.log(imageFilePath, true);
		}
		try {
			HomePage hp = new HomePage(driver, webActionUtil);
			hp.logout();
		} catch (Exception e) {
		
		}
	}
	
	@AfterClass
	public void closeApp() {
		driver.quit();
	}
	
	/* @AfterSuite
	public void closeReoporter() {
		extentReports.flush();
	} */
}
