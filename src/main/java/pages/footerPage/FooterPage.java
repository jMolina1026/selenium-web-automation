package pages.footerPage;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import util.BasePage;

public class FooterPage extends BasePage {
	public FooterPage(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * Footer Element Locators
	 */
	
	@FindBy(css = "footer.footer")
	public WebElement footerFieldElement;
	
	@FindBys(@FindBy(css = "ul.social > li > a"))
	public List<WebElement> footerSocialMediaListElements;
	
	@FindBy(css = "div.footer_copy")
	public WebElement footerCopyRightElement;
	
	@FindBy(css = "a[aria-label='X']")
	public WebElement twitterXLogoElement;
	
	@FindBy(css = "a[aria-label='Facebook']")
	public WebElement facebookLogoElement;
	
	@FindBy(css = "a.nav__logo-link")
	public WebElement linkedInLogoElement;
	
// --------------------------- METHODS ------------------------------------    

	/**
	 * Returns true or false depending on if the element is present
	 * @return - TRUE, if the element is present
	 */
	@Step("Is the Footer Element Present")
	@Attachment
	public boolean isFooterElementPresent(WebElement element) {
		return isElementPresent(element);
	}
	
	/**
	 * Click a footer element and then wait for new element
	 * @param element - locator used to identify the element and click it.
	 * @param element2 - locator used to identify the element and wait for it.
	 */
	@Step("Click the Social Media Element")
	public void clickFooterSocialMediaElement(WebElement element, WebElement element2) {
		clickTheElement(element);
		switchFocusToNewBrowserTab();
		waitExplicitlyForElement(element2);
	}
	
	/**
     * Returns text from one the footer field items
     * @param index - position of element in the list
     * @return - footer items text
     */
	@Step("Get the Footer Elements text")
	@Attachment
    public String getFooterElementsText(WebElement element) {
    	return getElementText(element);
    }
}
