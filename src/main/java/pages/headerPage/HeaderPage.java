package pages.headerPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import util.BasePage;

public class HeaderPage extends BasePage{
	public HeaderPage(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * Primary Header page Element Locators
	 */
	@FindBy(css = "div.bm-burger-button")
	private WebElement burgerMenuElement;
	
	@FindBy(css = "a.shopping_cart_link")
	private WebElement shoppingCartElement;
	
    @FindBy(css = "div.app_logo")
    private WebElement headerLogoElement;
    
    /**
     * Secondary header page element locators
     */
    
//    @FindBy(css = "div.header_secondary_container")
//    private WebElement headerSecondaryContainerElement;
//    
//    @FindBy(css = "span.title")
//    private WebElement hscTitleElement;
//  
//    @FindBy(css = "span.select_container")
//    private WebElement hscFilterElement;
    
    @FindBy(css = "div.header_secondary_container > span.title")
    private WebElement hscTitleElement;
    
    @FindBy(css = "div.header_secondary_container select.product_sort_container")
    private WebElement hscFilterElement;
    
    
// --------------------------- METHODS ------------------------------------    
	
//    public boolean secondaryHeaderTitle() {
//    	return headerSecondaryContainerElement.findElement((By) hscTitleElement).isDisplayed();
//    }
    
    /**
     * Returns text from the logo
     * @return - header logo text
     */
	@Step("Get the header logo text")
	@Attachment
    public String getHeaderLogoText() {
    	return getElementText(headerLogoElement);
    } 

    /**
     * Returns true or false depending on if the header logo is present
     * @return - true if logo is present
     */
	@Step("Is the header logo present?")
	@Attachment
    public boolean isHeaderLogoPresent() {
    	return isElementPresent(headerLogoElement);
    }
	
    /**
     * Returns true or false depending on if the Burger Menu Button is present
     * @return - true if button is present
     */
	@Step("Is the Burger Menu Button present?")
	@Attachment
    public boolean isHamburgerMenuButtonPresent() {
    	return isElementPresent(burgerMenuElement);
    }
	
    /**
     * Returns true or false depending on if the Shopping Cart is present
     * @return - true if button is present
     */
	@Step("Is the Shoping Cart present?")
	@Attachment
    public boolean isShoppingCartPresent() {
    	return isElementPresent(shoppingCartElement);
    }
	
    /**
     * Returns true or false depending on if the Secondary Header Title is present
     * @return - true if button is present
     */
	@Step("Is the Secondary Header Title present?")
	@Attachment
    public boolean isSecondaryHeaderTitlePresent() {
	  return isElementPresent(hscTitleElement);
    }
	
    /**
     * Returns text from the Secondary Header Title
     * @return - Secondary Header Title text
     */
	@Step("Get the secondary header title text")
	@Attachment
    public String getSecondaryHeaderTitleText() {
    	return getElementText(hscTitleElement);
    }
	
    /**
     * Returns true or false depending on if the Secondary Header Filter is present
     * @return - true if filter is present
     */
	@Step("Is the Secondary Header Filter present?")
	@Attachment
    public boolean isSecondaryHeaderFilterPresent() {
	  return isElementPresent(hscFilterElement);
    }
	
    /**
     * Clicks the Hamburger Menu Button to enter open the menu
     */
    @Step ("Click the Hamburger Menu Button")
    public void clickHamburgerMenuButton() {
    	clickTheElement(burgerMenuElement);
    }
}
