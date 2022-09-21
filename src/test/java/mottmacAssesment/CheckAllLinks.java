package mottmacAssesment;

import org.testng.annotations.Test;

import utils.HelperFunctions;
import utils.WrapperMethods;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeTest;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;

public class CheckAllLinks {
	WebDriver driver;
	@Parameters({"browser"})
	
	@BeforeTest
	  public void beforeTest(@Optional("chrome") String browser) {
		driver=HelperFunctions.createAppropriateDriver(browser);
	  }
  @Test(dataProvider = "dp")
  public void f(Integer n, String s) {
	  String url="";
		driver.get("https://www.mottmac.com");
		System.out.println("Requested URL is opened");
		By byCookies=By.id("ccc-notify-accept");
		WrapperMethods.elementClick(driver,byCookies);
		By byClose= By.xpath("//*[@id=\"cultureModal\"]/div/span");
		WrapperMethods.elementClick(driver, byClose);
		List<WebElement> allURLs = driver.findElements(By.tagName("a"));
	     System.out.println("Total links on the Wb Page: " + allURLs.size());
	     Iterator<WebElement> iterator = allURLs.iterator();
	      while (iterator.hasNext()) {
	    	  url = iterator.next().getText();
	    	  System.out.println(url);
	      }
	  
  }

  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { 1, "a" },
      
    };
  }
  

  @AfterTest
  public void afterTest() {
	  driver.close();
  }

}
