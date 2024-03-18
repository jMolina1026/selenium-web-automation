package pages;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import util.BasePage;

import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
    private WebElement emailField;
    
    @FindBy(css = "input#password")
    private WebElement passwordField;
    
    /**
     * Login Page Methods
     */
    @Step ("Enter Your Username")
    public void enterUserName() {
    	wait.until(ExpectedConditions.visibilityOf(emailField));
    	emailField.sendKeys("standard_user");
    }
    
    public void enterPassword() {
    	wait.until(ExpectedConditions.visibilityOf(passwordField));
    	passwordField.sendKeys("secret_sauce");
    }
    
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }
    
    public void takeScreenshot() {
        final byte[] screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        saveScreenshot(screenShot);
    }
}
