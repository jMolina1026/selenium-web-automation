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
    
    public void takeScreenshot() {
        final byte[] screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        saveScreenshot(screenShot);
    }
    
    public String getElementText(WebElement element) {
    	return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }
    
    public boolean isElementPresent(WebElement element) {
    	return (element.isDisplayed());
    }
}
