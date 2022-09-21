package solution;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import utils.HelperFunctions;
import utils.MoreHelperFunctions;
import utils.WrapperMethods;

public class QuetionThree {

	WebDriver driver;
	String browser="chrome";
	@Before
	public void setUp() throws Exception {
		driver = HelperFunctions.createAppropriateDriver(browser);
	}

	@After
	public void tearDown() throws Exception {
		driver.close();
	}

	@Test
	public void test() throws InterruptedException {
		driver.get("https://mockexam1cpsat.agiletestingalliance.org/");
		By byPopup= By.xpath("//*[@id=\"elementor-popup-modal-341\"]/div/div[4]/i");
		WrapperMethods.elementClick(driver,byPopup);
		MoreHelperFunctions.wait(2);
		By byMenu = By.xpath("//*[@id=\"menu-primary-spacious\"]/li[1]");
		WrapperMethods.elementClick(driver, byMenu);
		By listofParticipants = By.xpath("//*[@id=\"menu-item-163\"]/a");
		MoreHelperFunctions.scrollIntoView(driver, driver.findElement(listofParticipants));
		WrapperMethods.elementClick(driver, listofParticipants);
	    Thread.sleep(1000);
	    By tableSearchTextBox = By.xpath("//*[@id=\"post-151\"]/div/header/h1");
		MoreHelperFunctions.scrollIntoView(driver, driver.findElement(tableSearchTextBox));
		Thread.sleep(3000);
		
	}

}
