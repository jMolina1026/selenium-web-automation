package loginPageTests;

import org.testng.annotations.BeforeMethod;

import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
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
	
//------------------- booleans --------------------------------------------------	
	@Description ("The login Logo should be at the top of the login page")
	@Step ("Is the Login Logo Present?")
	@Attachment
	public boolean isLoginLogoPresent() {
		return loginPage.isLoginLogoPresent();
	}
	
	@Description ("The username field should be the first prompt in the login fields")
	@Step ("Is the Username field Present?")
	@Attachment
	public boolean isUsernameFieldPresent() {
		return loginPage.isUsernameFieldPresent();
	}
	
	@Description ("The Password field should be the first prompt in the login fields")
	@Step ("Is the Password field Present?")
	@Attachment
	public boolean isPasswordFieldPresent() {
		return loginPage.isPasswordFieldPresent();
	}
	
	@Description ("The login username credential area displays mulitiple valid usernames")
	@Step ("Are the Login username credentials Present?")
	@Attachment
	public boolean isLoginCredentialsAreaPresent() {
		return loginPage.isLoginCredentialsAreaPresent();
	}
	
	@Description ("The login password area displays a valid password")
	@Step ("Is the Password area Present?")
	@Attachment
	public boolean isLoginPasswordAreaPresent() {
		return loginPage.isLoginPasswordAreaPresent();
	}
	
	@Description("The error message box is displayed whenever there is a user input error when filling out the login form")
	@Step("Is the error message box present?")
	@Attachment
	public boolean isErrorMessageBoxPresent() {
		return loginPage.isErrorMessageBoxPresent();
	}
	
//------------------- Strings --------------------------------------------------		

	@Description("The header logo text is displayed on the top of the page/site after login is successful")
	@Step("Get the header logo text")
	@Attachment
	public String getHeaderLogoText() {
		return loginPage.getHeaderLogoText();
	}
	
	@Description("When displayed, the error message box appears with text")
	@Step("Get the error message box text")
	@Attachment
	public String getErrorMessageBoxText() {
		return loginPage.getErrorMessageBoxText();
	}
	
//------------------- Login --------------------------------------------------		

	@Description ("Peforms the action to Login with different credentials")
	@Step("Login into the Site")
	public void login(String un, String pwd) {
		loginPage.login(un, pwd);
	}
	
}
