package solution.QuestionPaper1;

import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;

import utils.HelperFunctions;
import utils.WrapperMethods;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeTest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;

public class Question1 {
	WebDriver driver;
	@Parameters({"browser"})
	 @BeforeTest
	  public void beforeTest(@Optional("chrome") String browser) {
		driver=HelperFunctions.createAppropriateDriver("chrome");
	  }
  @Test(dataProvider = "dp")
  public void f(Integer n, String s) {
	  driver.get("https://mockexam1cpsat.agiletestingalliance.org/");
	  By byPopup=By.xpath("//*[@id=\"elementor-popup-modal-341\"]/div/div[4]/i");
	  WrapperMethods.elementClick(driver, byPopup);
	  By byMenu=By.xpath("//*[@id=\"menu-primary-spacious\"]/li[1]");
	  WrapperMethods.elementClick(driver, byMenu);
	  List<WebElement> elements=driver.findElements(By.tagName("a"));
	  System.out.println("No of elements" +elements.size());
	  for(int i=0;i<elements.size();i++)
	  {
		  String elementValues=elements.get(i).getText();
		  System.out.println("Element Values" +elementValues);
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
  }

}
