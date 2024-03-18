package sampleMavenSeleniumProject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import pages.LoginPage;

public class LoginPageTest {
	WebDriver driver = new ChromeDriver();
//	WebDriver driver = new FirefoxDriver();
//	WebDriver driver = new SafariDriver();
//	WebDriver driver = new EdgeDriver();
	
	LoginPage loginPage = new LoginPage(driver);
	
	
	@Description ("Login to the Site")
	@Test (priority = 1)
	public void pageLoginTest() throws InterruptedException {
		System.out.println("hello");
		driver.get("https://www.saucedemo.com/");
		loginPage.enterUserName();
		loginPage.enterPassword();
		loginPage.takeScreenshot();
		System.out.println("The test is now done");
		Thread.sleep(3000);
		driver.quit();
	}
}
