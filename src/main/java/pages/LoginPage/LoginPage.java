package pages.LoginPage;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import util.BasePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    	typeTextIntoTheInputField(emailField, userName);
    }
    
    /**
     * The user should be able to type in a password.
     * @param password - enter a string in the password field
     */
    @Step ("Enter your password")
    public void enterPassword(String password) {
    	typeTextIntoTheInputField(passwordField, password);
    }
    
    /**
     * Clicks the login button to enter the site
     */
    @Step ("Click the Login button")
    public void clickLoginButton() {
    	clickTheElement(loginButton);
    }
    
    /**
     * The main login method to login into the site
     * @param userName - account username
     * @param password - account password
     */
	@Step("Login into the Site")
    public void login(String userName, String password) {
    	enterUserName(userName);
    	enterPassword(password);
    	clickLoginButton();
    }
    
    /**
     * Returns text from the logo
     * @return - header logo text
     */
	@Step("Get the header logo text")
	@Attachment
    public String getHeaderLogoText() {
    	return getElementText(headerLogo);
    }
    
    /**
     * Returns true or false depending on if the Error Message Box is present
     * @return - true if message box is present
     */
	@Step("Is the error message box present?")
	@Attachment
    public boolean isErrorMessageBoxPresent() {
    	return isElementPresent(errorMessageBox);
    }
    
    /**
     * When the wrong username or password is entered a message error box appears.
     * @return - error message box text
     */
	@Step("Get the error message box text")
	@Attachment
    public String getErrorMessageBoxText() {
    	return getElementText(errorMessageBox);
    }
    
    /**
     * The login Logo should be at the top of the login page
     * @return - true if Login Logo is present
     */
	@Step ("Is the Login Logo Present?")
	@Attachment
    public boolean isLoginLogoPresent() {
    	return isElementPresent(loginLogo);
    }
    
    /**
     * The username field should be the first prompt in the login fields
     * @return - true if Username Field is present
     */
	@Step ("Is the Username field Present?")
	@Attachment
    public boolean isUsernameFieldPresent() {
    	return isElementPresent(emailField);
    }
    
    /**
     * The Password field should be the first prompt in the login fields
     * @return - true if Password Field is present
     */
	@Step ("Is the Password field Present?")
	@Attachment
    public boolean isPasswordFieldPresent() {
    	return isElementPresent(passwordField);
    }
    
    /**
     * The login username credential area displays mulitiple valid usernames
     * @return - true if Username Crendentials are present
     */
	@Step ("Are the Login username credentials Present?")
	@Attachment
    public boolean isLoginCredentialsAreaPresent() {
    	return isElementPresent(loginCredentials);
    }
    
    /**
     * The login password area displays a valid password
     * @return - true if Login Password area is present
     */
	@Step ("Is the Password area Present?")
	@Attachment
    public boolean isLoginPasswordAreaPresent() {
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
