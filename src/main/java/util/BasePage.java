package util;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected final WebDriver driver;
    protected WebDriverWait wait;
//    protected SoftAssert softAssertion = new SoftAssertion();
    protected Actions action;
    
	public BasePage(WebDriver driver){
        this.driver = driver;
        this.action = new Actions(this.driver);

        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
}
