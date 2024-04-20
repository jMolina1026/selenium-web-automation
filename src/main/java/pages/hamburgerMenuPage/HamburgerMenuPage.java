package pages.hamburgerMenuPage;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import util.BasePage;

public class HamburgerMenuPage extends BasePage {
	public HamburgerMenuPage(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * Hamburger Menu Element Locators
	 */
	
	@FindBy(css = "div.bm-burger-button")
	public WebElement burgerMenuElement;
	
	@FindBy(css = "button#react-burger-cross-btn")
	private WebElement burgerMenuCloseBtnElement;
	
	@FindBys(@FindBy(css = "nav.bm-item-list > a"))
	public List<WebElement> burgerMenuItemsListElements;
	
	@FindBy(css = "img[alt='Sauce Labs Backpack']")
	public WebElement sauceLabsBackpackImgElement;
	
	@FindBy(css = "img[alt='Sauce Labs Bike Light']")
	public WebElement sauceLabsBikeLightImgElement;
	
	@FindBy(css = "img[src='/images/logo.svg']")
	public WebElement sauceLabsLogoElement;
	
    @FindBy(css = "div.login_logo")
    public WebElement loginLogo;
    
    @FindBys(@FindBy(css = "span.shopping_cart_badge"))
    public List<WebElement> badgeListElements;
    
    @FindBys(@FindBy(css = "button.btn_primary"))
    public List<WebElement> addToCartListElements;
// --------------------------- METHODS ------------------------------------ 
	
    /**
     * Clicks the Hamburger Menu Button to enter open the menu
     */
    @Step ("Click the Hamburger Menu Button")
    public void clickHamburgerMenuButton() {
    	clickTheElement(burgerMenuElement);
    	waitExplicitlyForElement(burgerMenuCloseBtnElement);
    }
    
    /**
     * Clicks the Hamburger Menu Close Button to enter open the menu
     */
    @Step ("Click the Hamburger Menu Close Button")
    public void clickHamburgerMenuCloseButton() {
    	clickTheElement(burgerMenuCloseBtnElement);
    	waitExplicitlyForInvisibleElement(burgerMenuCloseBtnElement);
    }
    
    /**
     * Clicks the backpack Button to enter open the menu
     */
    @Step ("Click the Backpack Button")
    public void clickSwagLabsBackpackImgButton() {
    	clickTheElement(sauceLabsBackpackImgElement);
    	waitExplicitlyForElement(sauceLabsBackpackImgElement);
    }
    
    /**
     * Clicks the Add to Cart Button to add an item to the shopping cart
     */
    @Step ("Click the Add to cart Button")
    public void clickAddToCartButton() {
    	clickTheElement(addToCartListElements.get(0));
    	waitExplicitlyForElement(badgeListElements.get(0));
    }
    
    /**
     * Clicks a burger menu option to navigate to a new page
     * @param element - locator used to identify the element.
     * @param element2 - locator used to identify the element.
     */
    @Step ("Click a burger menu option")
    public void clickBurgerMenuOptionButton(WebElement element, WebElement element2) {
    	clickTheElement(element);
    	waitExplicitlyForElement(element2);
    }
	
	/**
     * Returns text from one the burger menu items
     * @param index - position of element in the list
     * @return - menu items text
     */
	@Step("Get the Burger Menu Items text")
	@Attachment
    public String getBurgerMenuItemsText(int index) {
    	return getElementText(burgerMenuItemsListElements.get(index));
    }
	
    /**
     * Returns true or false depending on if the Burger Menu Item is present
     * @param index - position of element in the list
     * @return - true if Menu Item is present
     */
	@Step("Is the Hamburger Menu Items present?")
	@Attachment
    public boolean isHamburgerMenuItemPresent(int index) {
	  return isElementPresent(burgerMenuItemsListElements.get(index));
    }
	
    /**
     * Returns true or false depending on if the Burger Menu Item is Not present
     * @param index - position of element in the list
     * @return - false, Menu Item is Not present
     */
	@Step("Is the hamburger menu items Not present?")
	@Attachment
    public boolean isHamburgerMenuItemNotPresent(int index) {
	  return isElementNotPresent(burgerMenuItemsListElements.get(index));
    }
	
	/**
     * Get the current page url
     * @return - current page url 
	 */
	@Step("Check the current site url")
	@Attachment
	public String getCurrentPageSiteUrl() {
		return getCurrentPageUrl();
	}
	
    /**
     * Returns text from the login page logo
     * @return - login page logo text
     */
	@Step("Get the login logo text")
	@Attachment
    public String getLoginLogoText() {
    	return getElementText(loginLogo);
    }
	
	/**
	 * The badge will not exist in the DOM if no items are added to the cart
	 * @return - False
	 */
	@Step("See if the badge exists when an item is added")
	@Attachment
	public boolean doesTheShoppingCartBadgeExist() {
		return doesElementExist(badgeListElements);
	}
	
	
	
}
