package utils;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserCreation { 


	//hostName should include http://
	public static WebDriver createRemoteDriver(String browserName, String hostName, String port) throws MalformedURLException
	{
		WebDriver driver;
		browserName = browserName.toUpperCase();

		if (hostName == null) {
			hostName="http://localhost"; //assuming that hub is running on localhost
		}

		if (port == null) {
			port = "4444"; //default port of hub
		}

		//if ()
		String strURL = hostName+":"+port+"/wd/hub";
		
		System.out.println("hostname " + hostName );
		System.out.println("port " + port );
		
		System.out.println("URL =" + strURL);
		
		switch (browserName) {

		case "CHROME":

			ChromeOptions optionsChrome = new ChromeOptions();
			optionsChrome.addArguments("test-type");
			optionsChrome.addArguments("start-maximized");
			optionsChrome.addArguments("--disable-popup-blocking");
			optionsChrome.addArguments("disable-extensions");
			optionsChrome.addArguments("--disable-notifications");
			
			optionsChrome.setExperimentalOption("useAutomationExtension", false);
			
			//DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();
			//chromeCapabilities.setBrowserName("chrome");
			//chromeCapabilities.setPlatform(Platform.LINUX);
			//chromeCapabilities.chrome().
			
//			driver = new RemoteWebDriver(new URL(strURL), chromeCapabilities);
			driver = new RemoteWebDriver(new URL(strURL), optionsChrome);

			break;

		case "FIREFOX":
			
			FirefoxOptions optionsff = new FirefoxOptions();
			
			driver = new RemoteWebDriver(new URL(strURL), optionsff);
			break;
		default:
			//create chrome by default
			ChromeOptions optionsDefault = new ChromeOptions();
			optionsDefault.addArguments("test-type");
			optionsDefault.addArguments("start-maximized");
			optionsDefault.addArguments("--disable-popup-blocking");
			optionsDefault.addArguments("disable-extensions");
			//optionsDefault.setExperimentalOption("useAutomationExtension", false);
			optionsDefault.addArguments("--disable-notifications");
			driver = new RemoteWebDriver(new URL(strURL), optionsDefault);
			break;
		}

 
		return driver;
	} // end of createRemoteDriver(browserName,hostName,port)


}

