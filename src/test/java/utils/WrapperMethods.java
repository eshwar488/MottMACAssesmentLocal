package utils;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WrapperMethods {

	static int waitTimeinSec = 20;

	/*
	 * Following function is wrapper around for click
	 * it does two things
	 * 1. checks if the element is clickable or not
	 * 2. if it is then tries to stop the page load
	 * 3. then uses the standard click
	 */
	public static void elementClick(WebDriver driver, By byObject) {

		// Wait for the element to be clickable

		WebDriverWait wait = new WebDriverWait(driver,waitTimeinSec);

		try {
			wait.until(ExpectedConditions.elementToBeClickable(byObject));
			// continue even if the page is not loaded
			driver.findElement(By.tagName("body")).sendKeys("Keys.ESCAPE");
			driver.findElement(byObject).click();

			
			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	} // end of elementClick

	/*
	 * Following function is wrapper around for sendKeys
	 * it does two things
	 * 1. checks if the element is clickable or not
	 * 2. if it is then tries to stop the page load
	 * 3. then uses the standard click
	 */
	public static void sendKeys(WebDriver driver,By byObject, String strText) {


		try {

			//call the click method from this class to ensure that it is clickable
			WrapperMethods.elementClick(driver,byObject);
			WebElement element = driver.findElement(byObject);
			// clear the text box
			element.clear();
			element.sendKeys(strText);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}

	} // end of sendKeys
/*
	 * Following function is wrapper around for findElements
	 * it does two things
	 * 1. checks if all the WebElements are visible or not by a locator
	 * 2. use an explicit wait and then returns a list of webElements
	 */
	static public List <WebElement> getElementList(WebDriver driver, By byObj) {

		List <WebElement> listObj = null;
		WebDriverWait wait = new WebDriverWait(driver,waitTimeinSec);
		// wait for all the elements to be visible
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy( byObj));

		listObj = driver.findElements(byObj);

		return listObj;
	} // end of getElementList



}// end of WrapperMethods
