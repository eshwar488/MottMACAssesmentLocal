package utils;


import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MoreHelperFunctions { 

// Scroll element to view
	public static void scrollIntoView(WebDriver driver,WebElement element ) {
		try {
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e){
			System.out.println("=============================================================");
			HelperFunctions.captureScreenShot(driver, "src\\test\\resources\\screenshots\\scrollexception.jpg");
			e.printStackTrace();
			System.out.println("=============================================================");
		}    
	}	


	// Scroll Up page by co-ordinates
	static public void scrollUp(WebDriver driver, int pixels) throws Exception{
		try {
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			if (pixels >= 0) {
				pixels = -250;
			}
			jse.executeScript("window.scrollBy(0,pixels)", "");
		} catch (Exception e){
			System.out.println("=============================================================");
			e.printStackTrace();
			System.out.println("=============================================================");
		}       
	}

	// Scroll Up page by co-ordinates
	static public void scrollDown(WebDriver driver, int pixels) throws Exception{
		try {
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			if (pixels >= 0) {
				pixels = 250;
			}
			jse.executeScript("window.scrollBy(0,pixels)", "");
		} catch (Exception e){
			System.out.println("=============================================================");
			e.printStackTrace();
			System.out.println("=============================================================");
		}       
	}


	// Wait for some seconds
	// implementing Thread.sleep with exception handling 
	
	static public void wait (int second) {

		try {
			Thread.sleep(second*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// HAndle Alert - takes driver, acceptOrDismiss and text to enter
	public static void handleAlert(WebDriver driver, boolean acceptOrDismiss, String text) {
		
		WebDriverWait wait = new WebDriverWait(driver, 10);

		wait.until(ExpectedConditions.alertIsPresent());
		
		Alert alert = driver.switchTo().alert();
		
		if (text != null) {
			alert.sendKeys(text);
			alert.accept();
		} else if (acceptOrDismiss) {
			alert.accept();
		}
		else {
			alert.dismiss();
		}
	} // handleAlert
	
}

