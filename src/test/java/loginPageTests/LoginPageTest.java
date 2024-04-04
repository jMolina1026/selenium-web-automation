package loginPageTests;

import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import pages.LoginPage.LoginPageFinalVariables;
import util.SeleniumWebDriver;

@Feature ("Swag Labs - Login Page")
public class LoginPageTest extends SeleniumWebDriver implements LoginPageFinalVariables{

	@Test(priority = 1, description = "Verfiy the Login Page", groups = {"All", "Sanity", "loginSanity"})
	@Story ("Verify the login Page")
	@Description ("The login password area displays a valid password")
	public void verifyLoginPageTest() {
		softAssert.assertTrue(loginPage.isLoginLogoPresent());
		softAssert.assertTrue(loginPage.isUsernameFieldPresent());
		softAssert.assertTrue(loginPage.isPasswordFieldPresent());
		softAssert.assertTrue(loginPage.isLoginCredentialsAreaPresent());
		softAssert.assertTrue(loginPage.isLoginPasswordAreaPresent());
		softAssert.assertAll();
	}

	@Test (priority = 2, description = "Login into the Site", groups = {"All", "Regression", "loginRegression"})
	@Story ("Login Actions")
	@Description ("Performs the action to Login with valid credentials")
	public void loginTest() {
		loginPage.login(userName, passWord);
		softAssert.assertEquals(loginPage.getHeaderLogoText(), HEADER_LOGO_TEXT);
		softAssert.assertAll();
	}

	@Test (priority = 3, description = "Invalid Username", groups = {"All", "Regression", "loginRegression"})
	@Story ("Login Actions")
	@Description("Attempts to Login with an invalid username")
	public void loginWithWrongUsernameTest() {
		loginPage.login(INVALID_USERNAME, passWord);
		loginPage.takeScreenshot();
		softAssert.assertTrue(loginPage.isErrorMessageBoxPresent());
		softAssert.assertEquals(loginPage.getErrorMessageBoxText(), ERROR_MESSAGE_TEXT);
		softAssert.assertAll();
	}

	@Test (priority = 4, description = "Invalid Password", groups = {"All", "Regression", "loginRegression"})
	@Story ("Login Actions")
	@Description("Attempts to Login with an invalid password")
	public void loginWithWrongPasswordTest() {
		loginPage.login(userName, INVALID_PASSWORD);
		loginPage.takeScreenshot();
		softAssert.assertTrue(loginPage.isErrorMessageBoxPresent());
		softAssert.assertEquals(loginPage.getErrorMessageBoxText(), ERROR_MESSAGE_TEXT);
		softAssert.assertAll();
	}

	@Test (priority = 5, description = "Verify Required Username", groups = {"All", "Regression", "loginRegression"})
	@Story ("Verify the login Page")
	@Description("Verifies if a username has been added to the username field")
	public void loginWithNoUsernameEntered() {
		loginPage.login("", passWord);
		loginPage.takeScreenshot();
		softAssert.assertTrue(loginPage.isErrorMessageBoxPresent());
		softAssert.assertEquals(loginPage.getErrorMessageBoxText(), REQUIRED_USERNAME_TEXT);
		softAssert.assertAll();
	}

	@Test (priority = 6, description = "Verify Required Password", groups = {"All", "Regression", "loginRegression"})
	@Story ("Verify the login Page")
	@Description("Verifies if a password has been added to the password field")
	public void loginWithNoPasswordEntered() {
		loginPage.login(userName, "");
		loginPage.takeScreenshot();
		softAssert.assertTrue(loginPage.isErrorMessageBoxPresent());
		softAssert.assertEquals(loginPage.getErrorMessageBoxText(), REQUIRED_PASSWORD_TEXT);
		softAssert.assertAll();
	}
}
