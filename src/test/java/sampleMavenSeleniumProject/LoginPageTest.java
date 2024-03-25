package sampleMavenSeleniumProject;

import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import pages.LoginPage.LoginPageFinalVariables;
import util.SeleniumWebDriver;

public class LoginPageTest extends SeleniumWebDriver implements LoginPageFinalVariables{
	
	@Test(priority = 0, description = "Verfiy the Login Page")
	@Story ("The Login Page")
	@Description ("Verify the Login Page and its essential elements")
	@Step ("Verify the Login Page elements")
	public void verifyLoginPageTest() {
		loginPage.verifyAllLoginPageElements();
		loginPage.takeScreenshot();
	}
	
	@Test (priority = 1, description = "Login to the Site")
	@Story ("The Login Page")
	@Description ("Login to the Site")
	@Step ("Login with valid Username and Password")
	public void loginTest() {
		loginPage.login(userName, passWord);
		loginPage.takeScreenshot();
		hardAssert.assertEquals(loginPage.getHeaderLogoText(), HEADER_LOGO_TEXT);
	}
	
	@Test (priority = 2, description = "Invalid Username")
	@Story ("The Login Page")
	@Description ("Attempt to login with an invalid username")
	@Step ("Invalid Username")
	public void loginWithWrongUsernameTest() {
		loginPage.login(INVALID_USERNAME, passWord);
		loginPage.takeScreenshot();
		softAssert.assertTrue(loginPage.isErrorMessageBoxPresent());
		softAssert.assertEquals(loginPage.getErrorMessageBoxText(), ERROR_MESSAGE_TEXT);
		softAssert.assertAll();
	}
	
	@Test (priority = 3, description = "Invalid Password")
	@Story ("The Login Page")
	@Description ("Attempt to login with an invalid password")
	@Step ("Invalid Password")
	public void loginWithWrongPasswordTest() {
		loginPage.login(userName, INVALID_PASSWORD);
		loginPage.takeScreenshot();
		softAssert.assertTrue(loginPage.isErrorMessageBoxPresent());
		softAssert.assertEquals(loginPage.getErrorMessageBoxText(), ERROR_MESSAGE_TEXT);
		softAssert.assertAll();
	}
}
