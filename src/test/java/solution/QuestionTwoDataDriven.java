package solution;

import org.testng.annotations.Test;

import utils.DataReaders;
import utils.DataWriter;
import utils.HelperFunctions;
import utils.MoreHelperFunctions;
import utils.WrapperMethods;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;

public class QuestionTwoDataDriven {
  
	WebDriver driver;

	@Parameters({"browser"})
	@BeforeTest
	public void beforeTest(@Optional("chrome") String browser) {

		driver = HelperFunctions.createAppropriateDriver(browser);
	  }

	 @Test(dataProvider = "dp")
	  public void f(String param1, String param2,String param3,String param4) throws Exception {
			driver.get("https://mockexam1cpsat.agiletestingalliance.org/");
			By byPopup= By.xpath("//*[@id=\"elementor-popup-modal-341\"]/div/div[4]/i");
			WrapperMethods.elementClick(driver,byPopup);
			MoreHelperFunctions.wait(2);
			By byMenu = By.xpath("//*[@id=\"menu-primary-spacious\"]/li[1]");
			WrapperMethods.elementClick(driver, byMenu);
			JavascriptExecutor js = (JavascriptExecutor) driver;
		    js.executeScript("window.scrollBy(0,-350)", "");
		    Thread.sleep(3000);
			By listofParticipants = By.xpath("//*[@id=\"menu-item-163\"]/a");
			WrapperMethods.elementClick(driver, listofParticipants);
			MoreHelperFunctions.wait(2);
			
			By tablePaginationButton = By.xpath("//*[@id=\"footable_350\"]/tfoot/tr/td/div/ul/li[11]/a");
			By tablePaginationButtonParent = By.xpath("//*[@id=\"footable_350\"]/tfoot/tr/td/div/ul/li[11]/a//parent::li");
			MoreHelperFunctions.scrollIntoView(driver, driver.findElement(tablePaginationButton));
			boolean isNavigation = true;
			boolean matching=false;
			do {
				List<WebElement> tableRows = driver.findElements(By.xpath("//table/tbody/tr"));
				System.out.println("Number of Rows =" +tableRows.size());
				//tableBrowser(tableRows);
				 matching=tableBrowserMatching(tableRows, param2, param3);
				if(matching==true)
				{
					break;
				}
				driver.findElement(tablePaginationButton).click();
				WebElement parent = driver.findElement(tablePaginationButtonParent);
				String className= parent.getAttribute("class") ;
						if(className.contains("disabled")) {
							isNavigation =false;	
						}
						MoreHelperFunctions.wait(2);		
				
			} while (isNavigation);
			
			System.out.println("Matching =" +matching);
			DataWriter.writeToFileSingleValue("src/test/java/solution/results.xlsx", "sheet1", "Tset "+param1+":Matched ="+matching);
	  }
	 
	 void tableBrowser(List<WebElement> rows) {
			for(int i=1;i<rows.size();i++) {
				WebElement singleRow=rows.get(i);
				String xpathColumn="//"+singleRow.getTagName()+"[@class='"+singleRow.getAttribute("class")+"']/td";
				List<WebElement> trial = singleRow.findElements(By.xpath(xpathColumn));
				for(int j=0;j<trial.size();j++) {
					String columnText=trial.get(j).getText();
					System.out.println(":"+columnText);
				} //end of j loop(column loop)
				System.out.println();
			} // end of row loop =for loop
		}	 
	
	 boolean tableBrowserMatching(List<WebElement> rows,String name,String designation) {
		 boolean matching =false;
			for(int i=0;i<rows.size();i++) {
				WebElement singleRow=rows.get(i);
				String xpathColumn="//"+singleRow.getTagName()+"[@class='"+singleRow.getAttribute("class")+"']/td";
				List<WebElement> trial = singleRow.findElements(By.xpath(xpathColumn));
				boolean nameMatch=false;
				boolean designationMatch=false;
				for(int j=0;j<trial.size();j++) {
					String columnText=trial.get(j).getText();
					System.out.println(":"+columnText);
					if(j==1) {
						if(columnText.equals(name)) {
							nameMatch=true;
						}
					}
					if(j==2) {
						if(columnText.equals(designation)) {
							designationMatch=true;
						}
					}
				} //end of j loop(column loop)
				if(nameMatch && designationMatch)
				{
					matching=true;
					break;
				}
				System.out.println();
			} // end of row loop =for loop
			
			return matching;
		}
  @DataProvider
  public Object[][] dp() throws IOException {
	  String filename= "src/test/java/solution/TestData.xlsx";
	  String sheetName = "Sheet1";
	  Object[][] data=DataReaders.getExcelDataUsingPoi(filename, sheetName);
	  return data;
	  /*
    return new Object[][] {
      new Object[] { "1", "a","a","a" },
      new Object[] { "2", "b","b","b" },
    }; */
  }
 

  @AfterTest
  public void afterTest() {
	  driver.close();
  }

}
