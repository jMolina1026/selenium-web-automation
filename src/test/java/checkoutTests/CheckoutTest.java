package checkoutTests;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import pages.checkoutPage.CheckoutPage;
import pages.headerPage.HeaderPage;
import productsPage.ProductsPage;
import util.SeleniumWebDriver;

public class CheckoutTest extends SeleniumWebDriver{
	CheckoutPage checkoutPage;
	HeaderPage headerPage;
	ProductsPage productsPage;
	@BeforeMethod (alwaysRun = true)
	protected void testInitiation() {
		//init 
		checkoutPage = new CheckoutPage(driver);
		headerPage = new HeaderPage(driver);
		productsPage = new ProductsPage(driver);
		//login
		loginPage.login(userName, passWord);
	}
	
	@Test(priority = 1, description = "Verfiy the Checkout Page", groups = {"All", "Sanity", "checkoutSanity"})
	@Story ("Verify the Checkout Page")
	@Description ("The checkout page contains the selected items that were added to the cart")
	public void verifyCheckoutPageTest() {
		List<WebElement> productATCElementsList = productsPage.productAddToCartButtonsElements;
		int elementsLength = productATCElementsList.size() - 1;
		System.out.println("This iteration elementsLength = " + (elementsLength + 1));
		int randomIndex = checkoutPage.getRandomNumber(0, elementsLength);
		System.out.println("This iteration randomIndex = " + randomIndex);
		String productNameText = productsPage.getProductPageElementsText(productsPage.productNameElements.get(randomIndex));
		String productDescText = productsPage.getProductPageElementsText(productsPage.productDescriptionElements.get(randomIndex));
		String productPriceText = productsPage.getProductPageElementsText(productsPage.productPriceElements.get(randomIndex));
		System.out.println("text = " + productNameText);
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
	
	@Test(priority = 2, description = "Verfiy the Checkout Page", groups = {"All", "Sanity", "checkoutRegression"})
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
			System.out.println("This iteration elementsLength = " + (elementsLength + 1));
			randomIndex = checkoutPage.getRandomNumber(0, elementsLength);
			System.out.println("This iteration randomIndex = " + randomIndex);
			text = productsPage.getProductPageElementsText(productsPage.productNameElements.get(randomIndex));
			System.out.println("text = " + text);
			stringArrayList.add(text);
			productsPage.clickAddToCartButton(randomIndex);
		}
		
		for (String arrayList : stringArrayList) {
			System.out.println(arrayList);
		}
		softAssert.assertAll();
	}
}
