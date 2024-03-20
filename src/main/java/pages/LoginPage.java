package pages;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
    @Step ("Login to the site")
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
    
}
