package sampleMavenSeleniumProject;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import pages.LoginPage;
import util.SeleniumWebDriver;

public class LoginPageTest extends SeleniumWebDriver {	
	@Description ("Login to the Site")
	@Test (priority = 1)
	public void loginTest() throws InterruptedException {
		loginPage.login(userName, passWord);
		loginPage.takeScreenshot();
		System.out.println("The test is now done");
		Assert.assertEquals(loginPage.getHeaderLogoText(), "Swag Labs_");
		Thread.sleep(3000);
	}
	
	@Description ("Attempt to login with an invalid username")
	@Test (priority = 2)
	public void loginWithWrongUsername() throws InterruptedException {
		loginPage.login("WrongUserName", passWord);
		loginPage.takeScreenshot();
		System.out.println("The test is now done");
		Thread.sleep(3000);
	}
}
