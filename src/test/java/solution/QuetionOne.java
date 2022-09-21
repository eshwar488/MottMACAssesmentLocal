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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;

public class QuetionOne {
	
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
		By byMenu = By.xpath("//*[@id=\"menu-primary-spacious\"]/li[1]");
		WrapperMethods.elementClick(driver, byMenu);
		List<WebElement> elements=driver.findElements(By.tagName("a"));
		System.out.println("No of elements" +elements.size());
		for(int i=0;i<elements.size();i++) {
			String elementValues=elements.get(i).getText();
			System.out.println(elementValues);
		}
		List<WebElement> soacialelement= driver.findElements(By.xpath("//*[@id=\"lsi_widget-1\"]/ul/li"));
		for(int i=1;i<=soacialelement.size();i++) {
			System.out.println("The count is"+soacialelement.size());
			WebElement linkValues=driver.findElement(By.xpath("//*[@id=\"lsi_widget-1\"]/ul/li["+i+"]/a"));
			String actualLink=linkValues.getAttribute("href");
			System.out.println("//*[@id=\"lsi_widget-1\"]/ul/li["+i+"]/a");
			System.out.println("The Current URL's of Socail media icons are" +actualLink);
			MoreHelperFunctions.scrollIntoView(driver, linkValues);
			MoreHelperFunctions.wait(1);
			Actions action =new Actions(driver);
			action.contextClick(linkValues).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
			MoreHelperFunctions.wait(3);
			
		}
		
  }

  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { 1, "a" },
      new Object[] { 2, "b" },
    };
  }


  @AfterTest
  public void afterTest() {
	  driver.close();
  }

}
