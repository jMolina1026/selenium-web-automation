package util;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
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
     * Get the current page url
     * @return - current page url
     */
    public String getCurrentPageUrl() {
    	return driver.getCurrentUrl();
    }
    
    /**
     * Waits for the element to appear on the page
     * @param element - locator used to identify the element.
     */
    public void waitExplicitlyForElement(WebElement element) {
    	wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    /**
     * Waits for the element to be invisible on the page
     * @param element - locator used to identify the element.
     */
    public void waitExplicitlyForInvisibleElement(WebElement element) {
    	wait.until(ExpectedConditions.invisibilityOf(element));
    }
    
    /**
     * Counts the total amount of elements
     * @param elements - locator used to identify multiple elements
     * @return - list of elements
     */
	public int countOfElements(List<WebElement> elements) {
		waitExplicitlyForElement(elements.get(0));
		return elements.size();
	}
    
    /**
     * Checking for element existence in the DOM
     * @param elementList - list of webelements
     * @return - true if element count is greater than zero
     */
    public boolean doesElementExist(List<WebElement> elementList) {
    	return elementList.size() > 0;
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
     * @return - the element is present on the page, TRUE
     */
    public boolean isElementPresent(WebElement element) {
    	waitExplicitlyForElement(element);
    	return element.isDisplayed();
    }
    
    /**
     * Displays if the element is present or not
     * @param element - locator used to identify the element.
     * @return - the element is Not present on the page, FALSE
     */
    public boolean isElementNotPresent(WebElement element) {
    	waitExplicitlyForInvisibleElement(element);
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
    
    /**
     * Scroll to the Element in focus and center it on the page
     * @param element - locator used to identify the element.
     */
    public void scrollToTheElement(WebElement element) {
//    	action.scrollToElement(element).perform();
    	JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'})", element);
        } catch (StaleElementReferenceException e){
            System.out.println("StaleElementReferenceException, unable to scroll");
        }
    }
    
    /**
     * Scroll by number of pixels horizontally, vertically or both
     * @param x - number of pixels to scroll horizontally
     * @param y - number of pixels to scroll vertically
     */
    public void scrollByPixels(int x, int y) {
    	action.scrollByAmount(x, y).perform();
    }
    
    /**
     * Wait for an specific amount of seconds
     * @param seconds - time desired to wait
     */
    public void waitSeconds(double seconds){
        try {
            int sleepTime = (int) seconds * 1000;
            TimeUnit.MILLISECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Stores window ids in array list and then switches to the active tab
     */
    public void switchFocusToNewBrowserTab() {
        ArrayList<String> tabWindow = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabWindow.get(1));
    }
}
