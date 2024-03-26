package loginPageTests;

import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import pages.LoginPage.LoginPageFinalVariables;

@Feature ("The Login Page")
public class LoginPageTest extends LoginPageBusinessSteps implements LoginPageFinalVariables{
	
	@Test(priority = 1, description = "Verfiy the Login Page", groups = {"loginPageTests"})
	@Story ("Verify the login Page")
	@Description ("The login password area displays a valid password")
	public void verifyLoginPageTest() {
		softAssert.assertTrue(isLoginLogoPresent());
		softAssert.assertTrue(isUsernameFieldPresent());
		softAssert.assertTrue(isPasswordFieldPresent());
		softAssert.assertTrue(isLoginCredentialsAreaPresent());
		softAssert.assertTrue(isLoginPasswordAreaPresent());
		softAssert.assertAll();
	}
	
	@Test (priority = 2, description = "Login into the Site", groups = {"loginPageTests"})
	@Story ("Login Actions")
	public void loginTest() {
		login(userName, passWord);
		softAssert.assertEquals(getHeaderLogoText(), HEADER_LOGO_TEXT);
		softAssert.assertAll();
	}
	
	@Test (priority = 3, description = "Invalid Username", groups = {"loginPageTests"})
	@Story ("Login Actions")
	public void loginWithWrongUsernameTest() {
		login(INVALID_USERNAME, passWord);
		takeScreenShot();
		softAssert.assertTrue(isErrorMessageBoxPresent());
		softAssert.assertEquals(getErrorMessageBoxText(), ERROR_MESSAGE_TEXT);
		softAssert.assertAll();
	}
	
	@Test (priority = 4, description = "Invalid Password", groups = {"loginPageTests"})
	@Story ("Login Actions")
	public void loginWithWrongPasswordTest() {
		login(userName, INVALID_PASSWORD);
		takeScreenShot();
		softAssert.assertTrue(isErrorMessageBoxPresent());
		softAssert.assertEquals(getErrorMessageBoxText(), ERROR_MESSAGE_TEXT);
		softAssert.assertAll();
	}
	
	@Test (priority = 5, description = "Verify Required Username", groups = {"loginPageTests"})
	@Story ("Verify the login Page")
	public void loginWithNoUsernameEntered() {
		login("", passWord);
		takeScreenShot();
		softAssert.assertTrue(isErrorMessageBoxPresent());
		softAssert.assertEquals(getErrorMessageBoxText(), REQUIRED_USERNAME_TEXT);
		softAssert.assertAll();
	}
	
	@Test (priority = 6, description = "Verify Required Password", groups = {"loginPageTests"})
	@Story ("Verify the login Page")
	public void loginWithNoPasswordEntered() {
		login(userName, "");
		takeScreenShot();
		softAssert.assertTrue(isErrorMessageBoxPresent());
		softAssert.assertEquals(getErrorMessageBoxText(), REQUIRED_PASSWORD_TEXT);
		softAssert.assertAll();
	}
}
