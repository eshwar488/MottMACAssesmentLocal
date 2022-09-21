package samplepackage;

import org.testng.annotations.Test;

import utils.HelperFunctions;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;

public class SampleParameterCodeTNG {

	WebDriver driver;

	@Parameters({"browser"})
	@BeforeTest
	public void beforeTest(@Optional("chrome") String browser) {

		driver = HelperFunctions.createAppropriateDriver(browser);

		driver.get("https://www.google.com/");

	}


	@Test
	public void f() {
		
		By bySearchBox = By.name("q");
		
		// create a webelement using this locator
		
		WebElement elementSearchBox = driver.findElement(bySearchBox);
		
		//clear the search box if there is anything in there
		elementSearchBox.clear();
		
		String searchString = "CPSAT";

		// send the search string CPSAT in the google search text box using sendkeys
		elementSearchBox.sendKeys(searchString);
		
		//Send Enter Key to the search Box
		elementSearchBox.sendKeys(Keys.ENTER);
		
		// hard coded wait for 1 second using thread.sleep
		// 1000 ms = 1 second
		// this is to just wait for the search page to load
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// take screenshot of the search results
		// FOLDER is srd
		HelperFunctions.captureScreenShot(driver,"src\\test\\resources\\screenshots\\googlesearchresults.jpg");
		

	}

	@AfterTest
	public void afterTest() {

		//close the webdriver
		driver.quit();

	}

}
