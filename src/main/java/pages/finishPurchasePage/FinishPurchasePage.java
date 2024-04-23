package pages.finishPurchasePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import util.BasePage;

public class FinishPurchasePage extends BasePage {
	public FinishPurchasePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css = "span.title")
	public WebElement finishPurchaseTitleElement;
	
// --------------------------- METHODS ------------------------------------    

	/**
     * Returns text from one the Finish Purchase title
     * @param element - locator used to identify the element
     * @return - Finish Purchase title text
     */
	@Step("Get the Finish Purchase Elements text")
	@Attachment
    public String getFinishPurchaseElementsText(WebElement element) {
    	return getElementText(element);
    }
}
