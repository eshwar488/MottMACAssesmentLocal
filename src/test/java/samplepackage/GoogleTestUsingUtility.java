package samplepackage;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import utils.HelperFunctions;

public class GoogleTestUsingUtility {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver;

		// Use HelperFunctions to create appropriate drivers
		// we can create chrome or firefox by just passing the appropriate strings
		// appropriate drivers should be there in the src/test/resources/drivers/ folder
		
		// for invoking firefox
		// driver = HelperFunctions.createAppropriateDriver("firefox");
		
		// forinvoking chrome
		 driver = HelperFunctions.createAppropriateDriver("chrome");
		
		driver.get("https://www.google.com/");
	/*	Thread.sleep(5000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(0,250)");
		driver.findElement(By.xpath("//*[contains(text(),'Accept all')]")).click(); */
	
		// driver.findElement(By.)
		// locate the search box on the google page
		
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
		
		//close the webdriver
		driver.quit();
	}

}
