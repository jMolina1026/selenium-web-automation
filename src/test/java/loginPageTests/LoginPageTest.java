package loginPageTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Story;
//import io.qameta.allure.Description;
//import io.qameta.allure.Step;
//import io.qameta.allure.Story;
import pages.LoginPage.LoginPageFinalVariables;

public class LoginPageTest extends LoginPageBusinessSteps implements LoginPageFinalVariables{
	
	@Test(priority = 1, description = "Verfiy the Login Page")
	@Story ("The Login Page")
	public void verifyLoginPageTest() {
		softAssert.assertTrue(isLoginLogoPresent());
		softAssert.assertTrue(isUsernameFieldPresent());
		softAssert.assertTrue(isPasswordFieldPresent());
		softAssert.assertTrue(isLoginCredentialsAreaPresent());
		softAssert.assertTrue(isLoginPasswordAreaPresent());
		softAssert.assertAll();
		takeScreenShot();
	}
	
	@Test (priority = 2, description = "Login into the Site")
	@Story ("The Login Page")
	public void loginTest() {
		login(userName, passWord);
		takeScreenShot();
		Assert.assertEquals(loginPage.getHeaderLogoText(), HEADER_LOGO_TEXT);
	}
	
	@Test (priority = 3, description = "Invalid Username")
	@Story ("The Login Page")
	public void loginWithWrongUsernameTest() {
		login(INVALID_USERNAME, passWord);
		takeScreenShot();
		softAssert.assertTrue(loginPage.isErrorMessageBoxPresent());
		softAssert.assertEquals(loginPage.getErrorMessageBoxText(), ERROR_MESSAGE_TEXT);
		softAssert.assertAll();
	}
	
	@Test (priority = 4, description = "Invalid Password")
	@Story ("The Login Page")
	public void loginWithWrongPasswordTest() {
		login(userName, INVALID_PASSWORD);
		takeScreenShot();
		softAssert.assertTrue(loginPage.isErrorMessageBoxPresent());
		softAssert.assertEquals(loginPage.getErrorMessageBoxText(), ERROR_MESSAGE_TEXT);
		softAssert.assertAll();
	}
}
