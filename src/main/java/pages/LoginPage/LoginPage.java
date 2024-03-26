package pages.LoginPage;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.qameta.allure.Step;
import util.BasePage;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	

	/**
	 * Login Page Element Locators
	 */
    @FindBy(css = "input#user-name")
    public WebElement emailField;
    
    @FindBy(css = "input#password")
    private WebElement passwordField;
    
    @FindBy(css = "input#login-button")
    private WebElement loginButton;
    
    @FindBy(css = "div.app_logo")
    private WebElement headerLogo;
    
    @FindBy(css = "h3[data-test=\"error\"]")
    private WebElement errorMessageBox;
    
    @FindBy(css = "div.login_logo")
    private WebElement loginLogo;
    
    @FindBy(css = "div#login_credentials")
    private WebElement loginCredentials;
    
    @FindBy(css = "div.login_password")
    private WebElement loginPassword;
    
    /**
     * The user should be able to type in their username.
     * @param userName - enter a string in the username field
     */
    @Step ("Enter your Username")
    public void enterUserName(String userName) {
    	wait.until(ExpectedConditions.visibilityOf(emailField)).sendKeys(userName);
    }
    
    /**
     * The user should be able to type in a password.
     * @param password - enter a string in the password field
     */
    @Step ("Enter your password")
    public void enterPassword(String password) {
    	wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
    }
    
    /**
     * Clicks the login button to enter the site
     */
    @Step ("Click the Login button")
    public void clickLoginButton() {
    	wait.until(ExpectedConditions.visibilityOf(loginButton)).click();
    }
    
    /**
     * The main login method to login into the site
     * @param userName - account username
     * @param password - account password
     */
//    @Step ("Login to the site")
    public void login(String userName, String password) {
    	enterUserName(userName);
    	enterPassword(password);
    	clickLoginButton();
    }
    
    /**
     * Returns text from the logo
     * @return - header logo text
     */
    public String getHeaderLogoText() {
    	return getElementText(headerLogo);
    }
    
    /**
     * Returns true or false depending on if the Error Message Box is present
     * @return - true if message box is present
     */
    public boolean isErrorMessageBoxPresent() {
    	return isElementPresent(errorMessageBox);
    }
    
    /**
     * When the wrong username or password is entered a message error box appears.
     * @return - error message box text
     */
    public String getErrorMessageBoxText() {
    	return getElementText(errorMessageBox);
    }
    
    /**
     * Returns true or false depending on if the Login Logo is present
     * @return - true if Login Logo is present
     */
    public Boolean isLoginLogoPresent() {
    	return isElementPresent(loginLogo);
    }
    
    /**
     * Returns true or false depending on if the Username field is present
     * @return - true if Username Field is present
     */
    public Boolean isUsernameFieldPresent() {
    	return isElementPresent(emailField);
    }
    
    /**
     * Returns true or false depending on if the Password field is present
     * @return - true if Password Field is present
     */
    public Boolean isPasswordFieldPresent() {
    	return isElementPresent(passwordField);
    }
    
    /**
     * Returns true or false depending on if the Username Credentials are present
     * @return - true if Username Crendentials are present
     */
    public Boolean isLoginCredentialsAreaPresent() {
    	return isElementPresent(loginCredentials);
    }
    
    /**
     * Returns true or false depending on if the Password Credential is present
     * @return - true if Login Password area is present
     */
    public Boolean isLoginPasswordAreaPresent() {
    	return isElementPresent(loginPassword);
    }
    
    /**
     * Verifies that all essential fields are present on the login page
     */
    public void verifyAllLoginPageElements() {
    	isLoginLogoPresent();
    	isUsernameFieldPresent();
    	isPasswordFieldPresent();
    	isLoginCredentialsAreaPresent();
    	isLoginPasswordAreaPresent();
    }
    
}
