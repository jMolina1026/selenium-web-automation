package productsPage;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.testng.asserts.SoftAssert;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import util.BasePage;

public class ProductsPage extends BasePage{

	public ProductsPage (WebDriver driver) {
		super(driver);
	}
	
	@FindBys(@FindBy(css = "div.inventory_item"))
	public List<WebElement> productItemContainerElements;
	
	@FindBys(@FindBy(css = "div.inventory_item_name"))
	public List<WebElement> productNameElements;
	
	@FindBys(@FindBy(css = "div.inventory_item_desc"))
	public List<WebElement> productDescriptionElements;
	
	@FindBys(@FindBy(css = "div.inventory_item_price"))
	public List<WebElement> productPriceElements;
	
	//button#add-to-cart-sauce-labs-backpack
	@FindBys(@FindBy(css = "button.btn_primary"))
	public List<WebElement> productAddToCartButtonsElements;
	
	@FindBys(@FindBy(css = "div.inventory_item_img"))
	public List<WebElement> productImgElements;
	
	@FindBys(@FindBy(css = "span.shopping_cart_badge"))
	public List<WebElement> shoppingCartBadgeElements;
	
	@FindBys(@FindBy(css = "button.btn_secondary"))
	public List<WebElement> productRemoveButtonsElements;
	
// --------------------------- METHODS ------------------------------------    
	SoftAssert softAssert = new SoftAssert();
	/**
	 * Returns true or false depending on if the element is present
	 * @return - TRUE, if the element is present
	 */
	@Step("Is the Products Element Present?")
	@Attachment
	public boolean isProductPageElementPresent(WebElement element) {
		return isElementPresent(element);
	}
	
	/**
	 * Returns true or false depending on if the element is Not present
	 * @return - False, if the element is present
	 */
	@Step("Does the Products Element Exist?")
	@Attachment
	public boolean doesProductPageElementExist(List<WebElement> elements) {
		return doesElementExist(elements);
	}
	
	/**
     * Returns text from one the footer field items
     * @param element - locator used to identify the element
     * @return - products item text
     */
	@Step("Get the Products Elements text")
	@Attachment
    public String getProductPageElementsText(WebElement element) {
    	return getElementText(element);
    }
	
	/**
     * Counts the total amount of elements
     * @param elements - locator used to identify multiple elements
     * @return - list of elements
	 */
	public int countOfProductElements(List<WebElement> elements) {
		return countOfElements(elements);
	}
	
	public void clickAddToCartButton(int index) {
		clickTheElement(productAddToCartButtonsElements.get(index));
	}
	
	public void clickRemoveFromCartButton(int index) {
		clickTheElement(productRemoveButtonsElements.get(index));
	}
	
	
//	public boolean isShoppingCart
	
	
// -----------------------------------------------------------------------------------------	
	/**
	 * Checks to see if all elements are present on the products page
	 * uses two for loops to accomplish this
	 */
	public void areTheProductPageElementsPresent() {
		List<List<WebElement>> listOfElements = List.of(productImgElements, productNameElements);
		boolean isPresent = false;
		for (int i = 0; i < listOfElements.size(); i++) {
			for (int j = 0; j < listOfElements.get(i).size(); j++) {
				isPresent = isProductPageElementPresent(listOfElements.get(i).get(j));
				softAssert.assertTrue(isPresent, "Position [" + i + "," + j + "] --> Element Locator: " +  listOfElements.get(i).get(j));
			}
		}
		softAssert.assertAll();
	}
}
