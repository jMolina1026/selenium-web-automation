package pages.checkoutCompletePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import util.BasePage;

public class CheckoutCompletePage extends BasePage {
	public CheckoutCompletePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css = "span.title")
	public WebElement checkoutCompleteTitlElement;
	
	@FindBy(css = "h2.complete-header")
	public WebElement thanksForOrderElement;
	
// --------------------------- METHODS ------------------------------------    

	/**
     * Returns text from one the Checkout Complete title
     * @param element - locator used to identify the element
     * @return - Checkout Complete title text
     */
	@Step("Get the Checkout Complete Elements text")
	@Attachment
    public String getCheckoutCompleteElementsText(WebElement element) {
    	return getElementText(element);
    }
}
