package productTests;

import java.util.ArrayList;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import productsPage.ProductsPage;
import productsPage.ProductsPageMap;
import util.SeleniumWebDriver;

public class ProductTest extends SeleniumWebDriver {
	ProductsPage productsPage;
	ProductsPageMap productMap;
	@BeforeMethod (alwaysRun = true)
	protected void testInitiation() {
		//init 
		productsPage = new ProductsPage(driver);
		productMap = new ProductsPageMap();
		//login
		loginPage.login(userName, passWord);
	}
	
	
	@Test(priority = 1, description = "Verfiy the Product Page", groups = {"All", "Sanity", "productSanity"})
	@Story ("Verify the Product Page")
	@Description ("The product page contains all available items for sale")
	public void verifyProductPageTest() {
		int productsLength = productsPage.countOfElements(productsPage.productItemContainerElements);
		System.out.println(productsLength);
		for (int i = 0; i < productsLength; i++) {
			softAssert.assertTrue(productsPage.isProductPageElementPresent(productsPage.productImgElements.get(i)));
			
			softAssert.assertTrue(productsPage.isProductPageElementPresent(productsPage.productNameElements.get(i)));
			softAssert.assertEquals(productsPage.getProductPageElementsText(productsPage.productNameElements.get(i)), productMap.getProductNameMapValue(i));
			
			softAssert.assertTrue(productsPage.isProductPageElementPresent(productsPage.productDescriptionElements.get(i)));
			softAssert.assertEquals(productsPage.getProductPageElementsText(productsPage.productDescriptionElements.get(i)), productMap.getProductDescMapValue(i));

			softAssert.assertTrue(productsPage.isProductPageElementPresent(productsPage.productPriceElements.get(i)));
			softAssert.assertEquals(productsPage.getProductPageElementsText(productsPage.productPriceElements.get(i)), productMap.getProductPriceMapValue(i));
			
			softAssert.assertTrue(productsPage.isProductPageElementPresent(productsPage.productAddToCartButtonsElements.get(i)));
			softAssert.assertEquals(productsPage.getProductPageElementsText(productsPage.productAddToCartButtonsElements.get(i)), productMap.getProductATCMapValue(i));
		}
		softAssert.assertAll();
	}
	
	@Test(priority = 2, description = "Verfiy the Product Page V2", groups = {"All", "SanityV2", "productSanityV2"})
	@Story ("Verify the Product Page - V2")
	@Description ("The product page contains all available items for sale - V2")
	public void verifyProductPageV2Test() {
		productsPage.areTheProductPageElementsPresent();
	}
	
	@Test(priority = 3, description = "Verfiy the Product Add to Cart action", groups = {"All", "Sanity", "productRegression"})
	@Story ("Verify the Product Page")
	@Description ("The product page contains 'Add to Cart'buttons, user should be able to add or remove via this button")
	public void verifyProductAddToCartTest() {
		int addToCartlength = productsPage.productAddToCartButtonsElements.size();
		for (int i = 0; i < addToCartlength; i++) {
			productsPage.clickAddToCartButton(0);
			softAssert.assertTrue(productsPage.isProductPageElementPresent(productsPage.shoppingCartBadgeElements.get(0)), "The shopping cart badge is not present after adding an item, check if visible on page");
			softAssert.assertEquals(productsPage.getProductPageElementsText(productsPage.shoppingCartBadgeElements.get(0)), Integer.toString(i + 1));
		}
		int removeFromCartlength = productsPage.productRemoveButtonsElements.size();
		for (int j = removeFromCartlength; j > 0; j--) {
			productsPage.clickRemoveFromCartButton(j - 1);
			if (productsPage.productRemoveButtonsElements.size() >= 1) {
				softAssert.assertTrue(productsPage.isProductPageElementPresent(productsPage.shoppingCartBadgeElements.get(0)), "The shopping cart badge is not present after removing an item, check if visible on page (removal case expects badge to exist unless all items are removed)");
				softAssert.assertEquals(productsPage.getProductPageElementsText(productsPage.shoppingCartBadgeElements.get(0)), Integer.toString(productsPage.productRemoveButtonsElements.size()));
			} else {
				waitSeconds(2);
				softAssert.assertFalse(productsPage.doesProductPageElementExist(productsPage.shoppingCartBadgeElements), "The shopping cart should not exist if not items have been added to the cart");
			}
		}
		softAssert.assertAll();
	}
	
	@Test(priority = 4, description = "Verfiy the Product Sort Options", groups = {"All", "Regression", "productRegression"})
	@Story ("Verify the Product Page")
	@Description ("The product page contains the ability to sort, user should be able to sort alphanumerically")
	public void verifyProductSortTest() {
		int optionsLength = productsPage.listOfSelectElements(productsPage.sortContainerElement).size();
		for (int i = 0; i < optionsLength; i++) {
			productsPage.selectSortOptionByText(i);
			String selectedActiveOption = productsPage.getActiveSortOptionText();
			softAssert.assertEquals(selectedActiveOption, productsPage.sortOptionsArrayList().get(i));		
			if (i == 0 || i == 1) {
				ArrayList<String> sortedArrayList = productsPage.sortStringArrayList(i);
				int arrayListSize = sortedArrayList.size();
				for (int j = 0; j < arrayListSize; j++) {
					softAssert.assertEquals(productsPage.productNameElements.get(j).getText(), sortedArrayList.get(j));
				}
			} else if (i == 2 || i == 3) {
				ArrayList<Double> sortedArrayList = productsPage.sortDoubleArrayList(i);
				int arrayListSize = sortedArrayList.size();
				for (int j = 0; j < arrayListSize; j++) {
					String listOfElements = productsPage.productPriceElements.get(j).getText().replace("$", "");
					Double convertListToDouble = Double.parseDouble(listOfElements);
					softAssert.assertEquals(convertListToDouble, sortedArrayList.get(j));
				}
			}
		}
		softAssert.assertAll();
	}
}
