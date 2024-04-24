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
	
	@FindBy(css = "img.pony_express")
	public WebElement checkMarkElement;
	
	@FindBy(css = "div.complete-text")
	public WebElement orderDispatchedElement;
	
	@FindBy(css = "h2.complete-header")
	public WebElement thanksForOrderElement;
	
	@FindBy(css = "button[name='back-to-products']")
	public WebElement backToHomeButtonElement;
	
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
	
	/**
	 * Clicks the Checkout Complete Back Home button
	 * @param element - locator used to identify the element
	 */
	@Step("Click the Checkout Complete Back Home button")
	public void clickCheckoutCompleteBackHomeButton() {
		clickTheElement(backToHomeButtonElement);
	}
	
	/**
	 * This is a list of Strings from the Complete Checkout page
	 * used as the Expected in the assertEquals assertion.
	 * @param number - case number to switch between the different texts
	 * @return - String text
	 */
	public String listOfCompleteCheckoutAssertText(int number, Double subTotalPrice, Double tax, Double totalPrice) {
		String text;
		switch (number) {
		case 0: 
			text = "Payment Information:";
			break;
		case 1: 
			text = "SauceCard #31337";
			break;
		case 2: 
			text = "Shipping Information:";
			break;
		case 3:
			text = "Free Pony Express Delivery!";
			break;
		case 4:
			text = "Price Total";
			break;

		default:
			throw new IllegalArgumentException("Unexpected value: " + number);
		}
		return text;
	}
}
