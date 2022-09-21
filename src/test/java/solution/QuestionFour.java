package solution;

import org.testng.annotations.Test;

import utils.HelperFunctions;
import utils.MoreHelperFunctions;
import utils.WrapperMethods;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeTest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class QuestionFour {
	
	  WebDriver driver;

		@Parameters({"browser"})
		@BeforeTest
		public void beforeTest(@Optional("chrome") String browser) {

			driver = HelperFunctions.createAppropriateDriver(browser);
	  }
	
  @Test(dataProvider = "dp")
  public void f(Integer n, String s) {
	  driver.get("https://mockexam1cpsat.agiletestingalliance.org/");
	  By byPopup= By.xpath("//*[@id=\"elementor-popup-modal-341\"]/div/div[4]/i");
		WrapperMethods.elementClick(driver,byPopup);
	 MoreHelperFunctions.wait(2);
	  By byHomeMenu= By.xpath("//*[@id=\"menu-primary-spacious\"]/li[1]");
	  WrapperMethods.elementClick(driver,byHomeMenu);
	  By byChallenge1= By.xpath("//*[@id=\"menu-item-207\"]/a");
	  WrapperMethods.elementClick(driver,byChallenge1);
	  MoreHelperFunctions.wait(2);
	  By byChallengePopup= By.xpath("//*[@id=\"elementor-popup-modal-298\"]/div[2]/i");
	  WrapperMethods.elementClick(driver,byChallengePopup);
	  By byChallengePlusOne= By.xpath("//*[@id=\"eael-adv-accordion-cf8d59b\"]/div[1]");
	  WebElement pluselement=driver.findElement(byChallengePlusOne);
	  MoreHelperFunctions.scrollIntoView(driver, pluselement);
	  MoreHelperFunctions.wait(2);
	  for(int i=1;i<=5;i++)
	  {
		  By byBackgroundDiv= By.cssSelector("#eael-adv-accordion-cf8d59b > div:nth-child("+i+")");
		  WebElement element=driver.findElement(byBackgroundDiv);
		  String actualColrbeforeClick=element.getCssValue("background-color");
		  System.out.println("The actual Color before click" + actualColrbeforeClick);
		  By byChallengePlus= By.xpath("//*[@id=\"eael-adv-accordion-cf8d59b\"]/div["+i+"]");
		  System.out.println(byChallengePlus);
		  WrapperMethods.elementClick(driver,byChallengePlus);
		  MoreHelperFunctions.wait(2);
		  By byChallengePlusText= By.xpath("//*[@id=\"elementor-tab-content-217"+i+"\"]/p[2]");
		  System.out.println(byChallengePlusText);
		  String paraText=driver.findElement(byChallengePlusText).getText();
		  System.out.println("Para Text"+i+" "+paraText);
		  HelperFunctions.captureScreenShot(driver, "src/test/java/solution/screenshots.JPEG");
		  By afterClickbyBackgroundDiv= By.cssSelector("#eael-adv-accordion-cf8d59b > div:nth-child("+i+")");
		  WebElement element1=driver.findElement(afterClickbyBackgroundDiv);
		  String actualColrafterClick=element1.getCssValue("background-color");
		  System.out.println("The actual Color After click" + actualColrafterClick);
		//  Assert.assertNotEquals(actualColrbeforeClick, actualColrafterClick);
		 
	  }
	  driver.get("https://mockexam1cpsat.agiletestingalliance.org/index.php/challenge-2/");
		/*
		 * By allLink= By.tagName("a"); List<WebElement> elements=
		 * driver.findElements(allLink); for(int i=1;i<=elements.size();i++) { String
		 * challenge2Authors=elements.get(i).getText();
		 * System.out.println("challenge2Authors" + challenge2Authors);
		 */
	  for(int i=1;i<=2;i++)
	  {
		  for(int j=1;j<=5;j++) {
			  String authorsText=driver.findElement(By.xpath("//*[@id=\"post-177\"]/div/div/div/div/div/section["+i+"]/div/div/div["+j+"]/div/div/div[2]/div/h6/a")).getText();
			  System.out.println("The authors are" +authorsText);
		  }
	  }
	  
	  
  }

  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { 1, "a" }
    };
  }


  @AfterTest
  public void afterTest() {
	  driver.close();
  }

}
