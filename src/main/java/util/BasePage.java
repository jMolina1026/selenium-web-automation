package util;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Attachment;

public abstract class BasePage {
    protected final WebDriver driver;
    protected WebDriverWait wait;
    protected Actions action;
    
	public BasePage(WebDriver driver){
        this.driver = driver;
        this.action = new Actions(this.driver);

        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
	
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }
    
    /**
     * Takes a screenshot of the page under test
     */
    public void takeScreenshot() {
        final byte[] screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        saveScreenshot(screenShot);
    }
    
    /**
     * Waits for the element to appear on the page
     * @param element - locator used to identify the element.
     */
    public void waitExplicitlyForElement(WebElement element) {
    	wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    /**
     * Gets and stores the Elements text if there is one associated.
     * @param element - locator used to identify the element.
     * @return - The elements text
     */
    public String getElementText(WebElement element) {
    	waitExplicitlyForElement(element);
    	return element.getText();
    }
    
    /**
     * Displays if the element is present or not
     * @param element - locator used to identify the element.
     * @return - the element is present on the page
     */
    public boolean isElementPresent(WebElement element) {
    	return element.isDisplayed();
    }
    
    /**
     * This method will type text into an existing input field
     * @param element - locator used to identify the element.
     * @param text - String to type into an input field
     */
    public void typeTextIntoTheInputField(WebElement element, String text) {
    	waitExplicitlyForElement(element);
    	element.sendKeys(text);
    }
    
    /**
     * The method will click the element in question
     * @param element - locator used to identify the element.
     */
    public void clickTheElement(WebElement element) {
    	waitExplicitlyForElement(element);
    	element.click();
    }
}
