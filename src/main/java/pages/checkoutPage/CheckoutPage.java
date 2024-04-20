package pages.checkoutPage;

import java.lang.reflect.Array;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.testng.internal.junit.ArrayAsserts;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import util.BasePage;

public class CheckoutPage extends BasePage {
	public CheckoutPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css = "span.title")
	public WebElement yourCartElement;
	
	@FindBy(css = "div.cart_quantity_label")
	public WebElement quantityElement;
	
	@FindBy(css = "div.cart_desc_label")
	public WebElement descriptionElement;
	
	@FindBys(@FindBy(css = "div.inventory_item_name"))
	public List<WebElement> productNameElements;
	
	@FindBys(@FindBy(css = "div.inventory_item_desc"))
	public List<WebElement> productDescriptionElements;
	
	@FindBys(@FindBy(css = "div.inventory_item_price"))
	public List<WebElement> productPriceElements;
	
	@FindBys(@FindBy(css = "button.btn_secondary:not(button.back)"))
	public List<WebElement> productRemoveFromCartElements;
	
	@FindBys(@FindBy(css = "div.cart_quantity"))
	public List<WebElement> cartQuantityElements;
	
	@FindBy(css = "button.btn_secondary.back")
	public WebElement continueShoppingBtnElement;
	
	@FindBy(css = "button#checkout")
	public WebElement checkoutButtonElement;
	
// --------------------------- METHODS ------------------------------------    
	
	/**
	 * Returns true or false depending on if the element is present
	 * @return - TRUE, if the element is present
	 */
	@Step("Is the Checkout Page Element Present?")
	@Attachment
	public boolean isCheckoutPageElementPresent(WebElement element) {
		return isElementPresent(element);
	}
	
	/**
     * Returns text from one the footer field items
     * @param element - locator used to identify the element
     * @return - products item text
     */
	@Step("Get the Checkout Elements text")
	@Attachment
    public String getCheckoutElementsText(WebElement element) {
    	return getElementText(element);
    }
	
// -------------------------- TestScript --------------------------------------	
	public WebElement switchTest(int number) {
		WebElement element;
		switch (number) {
		case 0: element = yourCartElement; break;
		case 1: element = quantityElement; break;
		case 2: element = descriptionElement; break;
		case 3: element = cartQuantityElements.get(0); break;
		case 4: element = productNameElements.get(0); break;
		case 5: element = productDescriptionElements.get(0); break;
		case 6: element = productPriceElements.get(0); break;
		case 7: element = productRemoveFromCartElements.get(0); break;
		case 8: element = continueShoppingBtnElement; break;
		case 9: element = checkoutButtonElement; break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + number);
		}
		return element;
	}
}
