package pages.checkoutInfoPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import util.BasePage;

public class CheckoutInfoPage extends BasePage{
	public CheckoutInfoPage(WebDriver Driver) {
		super(Driver);
	}
	
	@FindBy(css = "span.title")
	public WebElement checkoutYourInfoTitleElement;
	
	@FindBy(css = "input#first-name")
	public WebElement firstNameFieldElement;
	
	@FindBy(css = "input#last-name")
	public WebElement lastNameElement;
	
	@FindBy(css = "input#postal-code")
	public WebElement postalCodeElement;
	
	@FindBy(css = "button#cancel")
	public WebElement cancelOrderElement;
	
	@FindBy(css = "input#continue")
	public WebElement continueOrderElement;
	
	@FindBy(css = "div.error-message-container")
	public WebElement errorMessageElement;
	
// --------------------------- METHODS ------------------------------------    

	/**
     * Returns text from one the Checkout Information Title
     * @param element - locator used to identify the element
     * @return - Checkout Information title text
     */
	@Step("Get the Checkout Information Elements text")
	@Attachment
    public String getCheckoutInfoElementsText(WebElement element) {
    	return getElementText(element);
    }
	
	/**
	 * The the placeholder text from each input field
	 * @param element - locator used to identify the element
	 * @return - element attribute value
	 */
	@Step("Get the Checkout Information Elements Placeholder text")
	@Attachment
	public String getPersonalInfoAttribute(WebElement element, String attribute) {
		return getElementAttribute(element, attribute);
	}
	
	/**
	 * Returns true or false depending on if the element is present
	 * @return - TRUE, if the element is present
	 */
	@Step("Is the Checkout Info Page Element Present?")
	@Attachment
	public boolean isCheckoutInfoPageElementPresent(WebElement element) {
		return isElementPresent(element);
	}
	
    /**
     * The user should be able to type in their username.
     * @param userName - enter a string in the username field
     */
    @Step ("Enter your text into the input fields")
    public void typeTextIntoCheckoutInfoField(WebElement element, String textToInput) {
    	typeTextIntoTheInputField(element, textToInput);
    }
    
	/**
	 * Clicks the Checkout Info Continue button
	 * @param element - locator used to identify the element
	 */
	@Step("Click the Checkout Info Continue button")
	public void clickCheckoutInfoContinueButton() {
		clickTheElement(continueOrderElement);
	}
}
