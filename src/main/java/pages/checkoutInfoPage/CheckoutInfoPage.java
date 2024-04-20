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
}
