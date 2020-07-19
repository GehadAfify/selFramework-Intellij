package tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utility.Helper;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class TestBase  extends AbstractTestNGCucumberTests{
	public static WebDriver driver ;
	public static String downloadPath = System.getProperty("user.dir")+"\\Downloads\\";
	
	public static FirefoxOptions firefoxoption() 
	{
		FirefoxOptions option = new FirefoxOptions();
		option.addPreference("browser.download.folderList",2);
		option.addPreference("browser.download.dir", downloadPath);
		option.addPreference("browser.helperApps.neverAsk.SaveToDisk","application/pdf");
		option.addPreference("browser.download.manager.showWhenStarting",false);
		option.addPreference("pdfjs.disabled", true);
		return option;
	}
	public static ChromeOptions chromeOption() {
		ChromeOptions options = new ChromeOptions();
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default.content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadPath);
		options.setExperimentalOption("prefs", chromePrefs);
		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		return options;
	}


	@BeforeSuite
	@Parameters({"browser"})
	public void startDriver(@Optional ("chrome")String browserName)
	{
		
		if (browserName.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\Drivers\\geckodriver.exe");
			driver  = new FirefoxDriver(firefoxoption());
		}
		else if (browserName.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
			driver  = new ChromeDriver(chromeOption());
		}
		else if(browserName.equalsIgnoreCase("ie")) 
		{
			System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\Drivers\\IEDriverServer.exe");
			driver  = new InternetExplorerDriver();
		}
		else if (browserName.equalsIgnoreCase("headless")) 
		{
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setJavascriptEnabled(true);
			caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
					System.getProperty("user.dir")+"\\Drivers\\phantomjs.exe" );
			String [] phantomJsArgs = {"--web-security=no","--ignore-ssl-errors=yes"};
			caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, phantomJsArgs);
			driver = new PhantomJSDriver(caps);
		}
		else if (browserName.equalsIgnoreCase("chrome-headless")) 
		{
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
			ChromeOptions option = new ChromeOptions();
			option.addArguments("--headless");
			option.addArguments("--window-size=1920,1080");
			driver = new ChromeDriver(option);
		} 
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(120,TimeUnit.SECONDS);
		driver.navigate().to("https://demo.nopcommerce.com/");
	}
	
	@AfterSuite
	public void stopDriver(){ driver.quit(); }
	//take screenshoot when test cas fail and add it in the screenshots folder
	@AfterMethod
	public void ScreenshotOnFailure(ITestResult result) //itresult interface de eli btrga3 b kol el  results 
	{
		if(result.getStatus()== ITestResult.FAILURE) 
		{
			System.out.println("Failed");
			System.out.println("Taking Screenshot .....");
			Helper.captureScreenshot(driver,result.getName());

		}
	}
}
