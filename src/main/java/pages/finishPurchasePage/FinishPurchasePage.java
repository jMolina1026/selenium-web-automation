package pages.finishPurchasePage;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import util.BasePage;

public class FinishPurchasePage extends BasePage {
	public FinishPurchasePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css = "span.title")
	public WebElement finishPurchaseTitleElement;
	
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
	
	@FindBys(@FindBy(css = "div.summary_info > div:not(div.cart_footer)"))
	public List<WebElement> summaryInfoElements;
	
	@FindBy(css = "button#cancel")
	public WebElement cancelOrderElement;
	
	@FindBy(css = "button#finish")
	public WebElement finishOrderElement;
	
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
	
	/**
	 * Returns true or false depending on if the element is present
	 * @return - TRUE, if the element is present
	 */
	@Step("Is the Finish Purchase Page Element Present?")
	@Attachment
	public boolean isFinishPurchasePageElementPresent(WebElement element) {
		return isElementPresent(element);
	}
	
	/**
	 * Clicks the Finish Purchase Cancel button
	 * @param element - locator used to identify the element
	 */
	@Step("Click the Finish Purchase Cancel button")
	public void clickFinishPurchaseCancelButton() {
		clickTheElement(cancelOrderElement);
	}
	
	/**
	 * Clicks the Finish Purchase Finish button
	 * @param element - locator used to identify the element
	 */
	@Step("Click the Finish Purchase Finish button")
	public void clickFinishPurchaseFinishButton() {
		clickTheElement(finishOrderElement);
	}
	
	
	/**
	 * Gets which list of elements to obtain on the page. This grabbing the elements on the 
	 * finish purchase page.
	 * @param number - which list of elements to obtain by case number
	 * @return - List of WebElements 
	 */
	public List<WebElement> getFinishPurchaseElementsList(int number) {
		List<WebElement> elements;
		switch (number) {
		case 0: 
			elements = productNameElements;
			break;
		case 1: 
			elements = productDescriptionElements;
			break;
		case 2: 
			elements = productPriceElements;
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + number);
		}
		return elements;
	}
	
	/**
	 * This is a list of Strings from the Finish purchase page
	 * used as the Expected in the assertEquals assertion.
	 * @param number - case number to switch between the different texts
	 * @return - String text
	 */
	public String listOfFinishPurchaseAssertText(int number, Double subTotalPrice, Double tax, Double totalPrice) {
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
		case 5:
			text = "Item total: $" + subTotalPrice.toString();
			break;
		case 6:
			text = "Tax: $" + tax;
			break;
		case 7:
			text = "Total: $" + totalPrice.toString();
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + number);
		}
		return text;
	}
	
	/**
	 * Get the Sub total price
	 * @return - sub total as type Double
	 */
	@Step("Get the sub total price")
	@Attachment
	public Double getSubTotalPrice() {
		ArrayList<Double> doubleArrayList = new ArrayList<Double>();
		Double subTotal = 0.00;
		for (int i = 0; i < getFinishPurchaseElementsList(2).size(); i++) {
			String productPriceString = getFinishPurchaseElementsList(2).get(i).getText().replace("$", "");
			Double productPriceDouble = Double.parseDouble(productPriceString);
			doubleArrayList.add(productPriceDouble);
			
			subTotal = subTotal + doubleArrayList.get(i);
		}
		return subTotal;
	}
	
}
