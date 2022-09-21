package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HelperFunctions {
	/*
	 * This function takes two parameters
	 * driver to look at the existing windows
	 * title - title of the window where you want to switch to
	 * it returns false if there is no window with this title
	 * it returns true if the window is found and the switch is successful
	 */

	// please ensure that proper drivers are there in the resource\driver folder 
	// if it is not there it may give an exception

	public static boolean switchToWindow(WebDriver driver, String title)
	{
		boolean flag = false;

		Set<String> iter = driver.getWindowHandles();

		int numHandles = iter.size();

		Object str[] = iter.toArray();


		for (int i=0;i<numHandles;i++)
		{
			System.out.println("window" + i + " handle name" + str[i]);

			String handle = (String)str[i];

			driver.switchTo().window(handle);

			String strTitle = driver.getTitle();
			if (strTitle.contains(title))
			{
				System.out.println("we are right window");
				flag = true;
				break;
			}

		}
		return flag;

	}



	public static WebDriver createAppropriateDriver(String browserName)
	{
		WebDriver driver;
		browserName = browserName.toUpperCase();



		switch (browserName) {

		case "CHROME":

			System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();	
			break;

		case "FIREFOX":
			System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();	
			break;
		case "EDGE":
	//		System.setProperty("webdriver.edge.driver", "src\\test\\resources\\drivers\\MicrosoftWebDriver.exe");
			driver = new EdgeDriver();	
			break;			
		default:
			//create chrome by default
		//	System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();	
			break;
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return driver;
	} // end of createAppropriateDriver(browserName)

	public static WebDriver createAppropriateDriver(String browserName, boolean headless)
	{
		WebDriver driver;
		ChromeOptions chromeOptions = new ChromeOptions();

		browserName = browserName.toUpperCase();

		switch (browserName) {

		case "CHROME":

			chromeOptions = new ChromeOptions();
			System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\drivers\\chromedriver.exe");

			chromeOptions.setHeadless(headless);

			driver = new ChromeDriver(chromeOptions);	
			break;

		case "FIREFOX":
			FirefoxOptions ffOptions = new FirefoxOptions();
		//	System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\drivers\\geckodriver.exe");		
			ffOptions.setHeadless(headless);
			driver = new FirefoxDriver(ffOptions);	
			break;
		case "EDGE":
			//EdgeOptions edgeoptions = new EdgeOptions();
		//	System.setProperty("webdriver.edge.driver", "src\\test\\resources\\drivers\\MicrosoftWebDriver.exe");
			// EdgeDriver Does not support headless
			// https://developer.microsoft.com/en-us/microsoft-edge/platform/issues/14057655/
			// edgeoptions.setHeadless(headless);
			// run the test in non-headless mode
			System.out.println("Running Edge Browser in head mode, as headless is not supported");
			driver = new EdgeDriver();
			break;
		default:
			//create chrome by default
			chromeOptions = new ChromeOptions();
		//	System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\drivers\\chromedriver.exe");
			chromeOptions.setHeadless(headless);

			driver = new ChromeDriver(chromeOptions);	
			break;
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return driver;
	} // end of createAppropriateDriver(browserName,headless)

	public static void captureScreenShot(WebDriver driver, String filename)
	{
		
		
		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File(filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	// Following method returns the browser name
	// other values are being printed on the console

	public static String getBrowserName (WebDriver driver) {

		String strBrowserName = null;

		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		strBrowserName = cap.getBrowserName().toLowerCase();

		System.out.println(strBrowserName);
		String os = cap.getPlatform().toString();
		System.out.println(os);
		String v = cap.getVersion();
		System.out.println(v);
		return strBrowserName;
	}

}

