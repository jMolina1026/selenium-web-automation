package loginPageTests;

import org.testng.annotations.BeforeMethod;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import util.SeleniumWebDriver;

public class LoginPageBusinessSteps extends SeleniumWebDriver {
	
	@BeforeMethod
    protected void testInitiation(){
        // Init pages
        
    }
	
	@Description ("Take a screenshot of the current page")
	@Step("Current Page Screenshot")
	public void takeScreenShot() {
		loginPage.takeScreenshot();
	}
	
//	@Story ("The Login Page")
//	@Description ("Verify the Login Page and its essential elements")
//	@Step ("Verify the Login Page elements")
//	public void verifyAllLoginPageElements() {
//		loginPage.verifyAllLoginPageElements();
//	}
//------------------- TestCase 1 --------------------------------------------------	
	@Description ("The login Logo should be at the top of the login page")
	@Step ("Is the Login Logo Present?")
	public boolean isLoginLogoPresent() {
		return loginPage.isLoginLogoPresent();
	}
	
	@Description ("The username field should be the first prompt in the login fields")
	@Step ("Is the Username field Present?")
	public boolean isUsernameFieldPresent() {
		return loginPage.isUsernameFieldPresent();
	}
	
	@Description ("The Password field should be the first prompt in the login fields")
	@Step ("Is the Password field Present?")
	public boolean isPasswordFieldPresent() {
		return loginPage.isPasswordFieldPresent();
	}
	
	@Description ("The login username credential area displays mulitiple valid usernames")
	@Step ("Are the Login username credentials Present?")
	public boolean isLoginCredentialsAreaPresent() {
		return loginPage.isLoginCredentialsAreaPresent();
	}
	
	@Description ("The login password area displays a valid password")
	@Step ("Is the Password area Present?")
	public boolean isLoginPasswordAreaPresent() {
		return loginPage.isLoginPasswordAreaPresent();
	}
	
//------------------- TestCase 2, 3, 4 --------------------------------------------------		
	
	@Description ("Peforms the action to Login with different credentials")
	@Step("Login into the Site")
	public void login(String un, String pwd) {
		loginPage.login(un, pwd);
	}
	
}
