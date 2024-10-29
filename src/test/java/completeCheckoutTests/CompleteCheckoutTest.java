package completeCheckoutTests;

import java.util.ArrayList;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import pages.checkoutCompletePage.CheckoutCompletePage;
import pages.checkoutInfoPage.CheckoutInfoPage;
import pages.checkoutPage.CheckoutPage;
import pages.finishPurchasePage.FinishPurchasePage;
import pages.headerPage.HeaderPage;
import pages.productsPage.ProductsPage;
import util.SeleniumWebDriver;

public class CompleteCheckoutTest extends SeleniumWebDriver {
	CheckoutPage checkoutPage;
	HeaderPage headerPage;
	ProductsPage productsPage;
	CheckoutInfoPage checkoutInfoPage;
	FinishPurchasePage finishPurchasePage;
	CheckoutCompletePage checkoutCompletePage;
	ArrayList<ArrayList<String>> productsPageArrayList;
	@BeforeMethod (alwaysRun = true)
	protected void testInitiation() {
		//init 
		checkoutPage = new CheckoutPage(driver);
		headerPage = new HeaderPage(driver);
		productsPage = new ProductsPage(driver);
		checkoutInfoPage = new CheckoutInfoPage(driver);
		finishPurchasePage = new FinishPurchasePage(driver);
		checkoutCompletePage = new CheckoutCompletePage(driver);
		//login
		loginPage.login(userName, passWord);
		
		// intial steps
		int countOfClicks = 3;
		for (int i = 0; i < countOfClicks; i++) {
			productsPage.clickAddToCartButton(0);
			waitSeconds(0.5);
		}
		productsPageArrayList = productsPage.getProductsPageTextArrayList();
		
		headerPage.clickShoppingCartButton();
		checkoutPage.waitExplicitlyForElement(checkoutPage.yourCartElement);
		waitSeconds(0.5);
		checkoutPage.clickCheckoutButton(checkoutPage.checkoutButtonElement);
		checkoutPage.waitExplicitlyForElement(checkoutInfoPage.firstNameFieldElement);
		waitSeconds(0.5);
		checkoutInfoPage.typeTextIntoCheckoutInfoField(checkoutInfoPage.firstNameFieldElement, "John");
		checkoutInfoPage.typeTextIntoCheckoutInfoField(checkoutInfoPage.lastNameElement, "Tester");
		checkoutInfoPage.typeTextIntoCheckoutInfoField(checkoutInfoPage.postalCodeElement, "91245");
		checkoutInfoPage.clickCheckoutInfoContinueButton();
		finishPurchasePage.waitExplicitlyForElement(finishPurchasePage.finishPurchaseTitleElement);
		finishPurchasePage.clickFinishPurchaseFinishButton();
		finishPurchasePage.waitExplicitlyForElement(checkoutCompletePage.thanksForOrderElement);
	}
	
	@Test(priority = 1, description = "Verfiy the Checkout Complete Page", groups = {"All", "Sanity", "checkoutCompleteSanity"})
	@Story ("Verify the Checkout Complete Page")
	@Description ("The Checkout Complete page contains the fields once the client has checked out")
	public void verifyFinalPurchasePageTest() {
		softAssert.assertEquals(checkoutCompletePage.getCheckoutCompleteElementsText(checkoutCompletePage.checkoutCompleteTitlElement), 
				"Checkout: Complete!");
		softAssert.assertEquals(checkoutCompletePage.getCheckoutCompleteElementsText(checkoutCompletePage.thanksForOrderElement), 
				"Thank you for your order!");
		softAssert.assertEquals(checkoutCompletePage.getCheckoutCompleteElementsText(checkoutCompletePage.orderDispatchedElement), 
				"Your order has been dispatched, and will arrive just as fast as the pony can get there!");
		softAssert.assertEquals(checkoutCompletePage.getCheckoutCompleteElementsText(checkoutCompletePage.backToHomeButtonElement), 
				"Back Home");
		softAssert.assertAll();
	}
	
	@Test(priority = 2, description = "Verfiy the Checkout Complete Page and go back home", groups = {"All", "Regression", "checkoutCompleteRegression"})
	@Story ("Verify the Checkout Complete Page")
	@Description ("The Checkout Complete page contains the back home button and will take the user back home")
	public void verifyFinalPurchaseBackHomeTest() {
		checkoutCompletePage.clickCheckoutCompleteBackHomeButton();
		productsPage.waitExplicitlyForElement(productsPage.sortContainerElement);
		softAssert.assertEquals(productsPage.getProductPageElementsText(productsPage.productPageTitleElement), "Products");
		softAssert.assertAll();
	}

}
