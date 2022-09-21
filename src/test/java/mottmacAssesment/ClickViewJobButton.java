package mottmacAssesment;

import org.testng.annotations.Test;

import utils.HelperFunctions;
import utils.WrapperMethods;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;

public class ClickViewJobButton {
	WebDriver driver;
	@Parameters({"browser"})
	 @BeforeTest
	  public void beforeTest(@Optional("chrome") String browser) {
		 driver=HelperFunctions.createAppropriateDriver(browser);
	  }
  @Test(dataProvider = "dp")
  public void f(Integer n, String s) {
	  String url="";
		driver.get("https://www.mottmac.com/careers/search");
		System.out.println("Requested URL is opened");
		By byCookies=By.id("ccc-notify-accept");
		WrapperMethods.elementClick(driver,byCookies);
		By byClose= By.xpath("//*[@id=\"cultureModal\"]/div/span");
		WrapperMethods.elementClick(driver, byClose);
		By selectByValue=By.xpath("//*[@id=\"match-selector\"]");
		WrapperMethods.elementClick(driver, selectByValue);
		WebElement element=driver.findElement(selectByValue);
		Select select=new Select(element);
		select.selectByVisibleText("Contains (any words)");
		By byFilter=By.xpath("//*[@id=\"search-career-search-temp\"]");
		WrapperMethods.sendKeys(driver, byFilter,"test engineer");
		By bySearchicon=By.xpath("//*[@id=\"j-search-box-careers\"]/div[1]/div[1]/span/button[2]");
		WrapperMethods.elementClick(driver, bySearchicon);
		 By byViewJob=By.xpath("//*[@id=\"j-careers-search__results\"]/div[1]/div/div/div[6]/a");
		 WrapperMethods.elementClick(driver, byViewJob);
		 By byViewJobDetails= By.xpath("//*[@id=\"cultureModal\"]/div/div[2]/ul/li[1]/a");
		 WrapperMethods.elementClick(driver, byViewJobDetails);
		
		
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
