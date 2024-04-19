package pages.productDetailsPage;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import productsPage.ProductsPage;
import util.BasePage;

public class ProductDetailsPage extends ProductsPage{
	public ProductDetailsPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css = "div.inventory_details_name")
	public WebElement productNameElement;
	
	@FindBy(css = "div.inventory_details_desc")
	public WebElement productDescriptionElement;

	@FindBy(css = "div.inventory_details_price")
	public WebElement productPriceElement;
	
	@FindBy(css = "button#add-to-cart")
	public WebElement productAddToCartElement;
	
	@FindBy(css = "img.inventory_details_img")
	public WebElement productImgElement;
	
	@FindBy(css = "button#back-to-products")
	public WebElement backToProductsElement;
	
// --------------------------- METHODS ------------------------------------    
	
	/**
	 * Returns true or false depending on if the element is present
	 * @return - TRUE, if the element is present
	 */
	@Step("Is the Product Details Element Present?")
	@Attachment
	public boolean isProductDetailsElementPresent(WebElement element) {
		return isElementPresent(element);
	}
	
	/**
     * Returns text from one the footer field items
     * @param element - locator used to identify the element
     * @return - products item text
     */
	@Step("Get the Product Details Elements text")
	@Attachment
    public String getProductDetailsElementsText(WebElement element) {
    	return getElementText(element);
    }
	
	/**
	 * Clicks the Product Title Button button depending on the locator
	 * @param element - locator used to identify the element
	 */
	public void clickProductTitleButton(List<WebElement> elements, int index) {
		clickTheElement(elements.get(index));
	}
}
