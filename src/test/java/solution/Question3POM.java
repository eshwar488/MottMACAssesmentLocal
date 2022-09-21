package solution;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Question3POM {
 WebDriver driver;
	By tableHeaderTitle = By.xpath("//*[@id=\"post-151\"]/div/header/h1");
	
	By tableSearchTextbox =By.xpath("//*[@id=\"footable_350\"]/thead/tr[1]/th/form/div/div/input");
	By tableColumn= By.xpath("//*[@id=\"footable_350\"]/tbody/tr");
	
	public List<String> searchParticipantName(String Name){
		
		List<WebElement> totalcount= driver.findElements(tableColumn);
		System.out.println(totalcount.size());
		if(totalcount.size()==0) {
			return null;
		}
		else {
			for(int i=0;i<totalcount.size();i++)
			{
				String theActualText=totalcount.get(i).getText();
				System.out.println("The Text is"+theActualText);
			}
			
		}
		return null;
		
	}
}
