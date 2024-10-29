package checkoutTests;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import pages.checkoutInfoPage.CheckoutInfoPage;
import pages.checkoutPage.CheckoutPage;
import pages.headerPage.HeaderPage;
import pages.productsPage.ProductsPage;
import util.SeleniumWebDriver;

public class CheckoutTest extends SeleniumWebDriver{
	CheckoutPage checkoutPage;
	HeaderPage headerPage;
	ProductsPage productsPage;
	CheckoutInfoPage checkoutInfoPage;
	@BeforeMethod (alwaysRun = true)
	protected void testInitiation() {
		//init 
		checkoutPage = new CheckoutPage(driver);
		headerPage = new HeaderPage(driver);
		productsPage = new ProductsPage(driver);
		checkoutInfoPage = new CheckoutInfoPage(driver);
		//login
		loginPage.login(userName, passWord);
	}
	
	@Test(priority = 1, description = "Verfiy the Checkout Page", groups = {"All", "Sanity", "checkoutSanity"})
	@Story ("Verify the Checkout Page")
	@Description ("The checkout page contains the selected items that were added to the cart")
	public void verifyCheckoutPageTest() {
		List<WebElement> productATCElementsList = productsPage.productAddToCartButtonsElements;
		int elementsLength = productATCElementsList.size() - 1;
		int randomIndex = checkoutPage.getRandomNumber(0, elementsLength);
		String productNameText = productsPage.getProductPageElementsText(productsPage.productNameElements.get(randomIndex));
		String productDescText = productsPage.getProductPageElementsText(productsPage.productDescriptionElements.get(randomIndex));
		String productPriceText = productsPage.getProductPageElementsText(productsPage.productPriceElements.get(randomIndex));
		productsPage.clickAddToCartButton(randomIndex);
		headerPage.clickShoppingCartButton();
		
		softAssert.assertTrue(checkoutPage.isCheckoutPageElementPresent(checkoutPage.yourCartElement), "Your Cart not present");
		softAssert.assertEquals(checkoutPage.getCheckoutElementsText(checkoutPage.yourCartElement), "Your Cart");
		
		softAssert.assertTrue(checkoutPage.isCheckoutPageElementPresent(checkoutPage.quantityElement), "QTY not present");
		softAssert.assertEquals(checkoutPage.getCheckoutElementsText(checkoutPage.quantityElement), "QTY");
		
		softAssert.assertTrue(checkoutPage.isCheckoutPageElementPresent(checkoutPage.descriptionElement), "Description not present");
		softAssert.assertEquals(checkoutPage.getCheckoutElementsText(checkoutPage.descriptionElement), "Description");
		
		softAssert.assertTrue(checkoutPage.isCheckoutPageElementPresent(checkoutPage.cartQuantityElements.get(0)), "Cart Quantity not present");
		softAssert.assertEquals(checkoutPage.getCheckoutElementsText(checkoutPage.cartQuantityElements.get(0)), "1");
		
		softAssert.assertTrue(checkoutPage.isCheckoutPageElementPresent(checkoutPage.productNameElements.get(0)), "Product Name not present");
		softAssert.assertEquals(checkoutPage.getCheckoutElementsText(checkoutPage.productNameElements.get(0)), productNameText);
		
		softAssert.assertTrue(checkoutPage.isCheckoutPageElementPresent(checkoutPage.productDescriptionElements.get(0)), "Product Description not present");
		softAssert.assertEquals(checkoutPage.getCheckoutElementsText(checkoutPage.productDescriptionElements.get(0)), productDescText);

		softAssert.assertTrue(checkoutPage.isCheckoutPageElementPresent(checkoutPage.productPriceElements.get(0)), "Product Price not present");
		softAssert.assertEquals(checkoutPage.getCheckoutElementsText(checkoutPage.productPriceElements.get(0)), productPriceText);

		softAssert.assertTrue(checkoutPage.isCheckoutPageElementPresent(checkoutPage.productRemoveFromCartElements.get(0)), "Remove button not present");
		softAssert.assertEquals(checkoutPage.getCheckoutElementsText(checkoutPage.productRemoveFromCartElements.get(0)), "Remove");
		
		softAssert.assertTrue(checkoutPage.isCheckoutPageElementPresent(checkoutPage.continueShoppingBtnElement), "Continue Shopping Button not present");
		softAssert.assertEquals(checkoutPage.getCheckoutElementsText(checkoutPage.continueShoppingBtnElement), "Continue Shopping");
		
		softAssert.assertTrue(checkoutPage.isCheckoutPageElementPresent(checkoutPage.checkoutButtonElement), "Checkout Button not present");
		softAssert.assertEquals(checkoutPage.getCheckoutElementsText(checkoutPage.checkoutButtonElement), "Checkout");
		
		softAssert.assertAll();
	}
	
	@Test(priority = 2, description = "Verfiy the Checkout Page", groups = {"All", "Regression", "checkoutRegression"})
	@Story ("Verify the Checkout Page")
	@Description ("The checkout page contains the selected items that were added to the cart and that will be checked out")
	public void verifyCheckoutProcessTest() {
		int countOfClicks = 3;
		int randomIndex = 0;
		String text = "";
		ArrayList<String> stringArrayList = new ArrayList<String>();
		for (int i = 0; i < countOfClicks; i++) {
			List<WebElement> productATCElementsList = productsPage.productAddToCartButtonsElements;
			int elementsLength = productATCElementsList.size() - 1;
			randomIndex = checkoutPage.getRandomNumber(0, elementsLength);
			text = productsPage.getProductPageElementsText(productsPage.productNameElements.get(randomIndex));
			stringArrayList.add(text);
			productsPage.clickAddToCartButton(randomIndex);
		}
		softAssert.assertEquals(Integer.parseInt(productsPage.getProductPageElementsText(productsPage.shoppingCartBadgeElements.get(0))), countOfClicks);
		
		headerPage.clickShoppingCartButton();
		checkoutPage.waitExplicitlyForElement(checkoutPage.yourCartElement);
		softAssert.assertEquals(checkoutPage.getCheckoutElementsText(checkoutPage.yourCartElement), "Your Cart");
		
		checkoutPage.clickContinueShoppingButton(checkoutPage.continueShoppingBtnElement);
		checkoutPage.waitExplicitlyForElement(headerPage.hscTitleElement);
		softAssert.assertEquals(headerPage.getSecondaryHeaderTitleText(), "Products");
		
		headerPage.clickShoppingCartButton();
		checkoutPage.waitExplicitlyForElement(checkoutPage.yourCartElement);
		softAssert.assertEquals(checkoutPage.getCheckoutElementsText(checkoutPage.yourCartElement), "Your Cart");
		
		checkoutPage.clickRemoveProductButton(checkoutPage.productRemoveFromCartElements.get(countOfClicks - 1));
		softAssert.assertEquals(Integer.parseInt(productsPage.getProductPageElementsText(productsPage.shoppingCartBadgeElements.get(0))), checkoutPage.productNameElements.size(), "Shopping Cart badge should be equal");
		
		checkoutPage.clickCheckoutButton(checkoutPage.checkoutButtonElement);
		checkoutPage.waitExplicitlyForElement(checkoutInfoPage.firstNameFieldElement);
		softAssert.assertEquals(checkoutInfoPage.getCheckoutInfoElementsText(checkoutInfoPage.checkoutYourInfoTitleElement), "Checkout: Your Information");

		softAssert.assertAll();
	}
}
