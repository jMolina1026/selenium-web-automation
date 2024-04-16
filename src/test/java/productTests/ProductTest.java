package productTests;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

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
	
	@Test(priority = 4, description = "Verfiy the Product Sort Options", groups = {"All", "Sanity", "productRegressions"})
	@Story ("Verify the Product Page")
	@Description ("The product page contains the ability to sort, user should be able to sort alphanumerically")
	public void verifyProductSortTest() {
		int optionsLength = productsPage.listOfSelectElements(productsPage.sortContainerElement).size();
		for (int i = 0; i < optionsLength; i++) {
			productsPage.selectSortOptionByText(i);
//			System.out.println(productsPage.sortOptionsArrayList());
			String selectedActiveOption = productsPage.getActiveSortOptionText();
			System.out.println(selectedActiveOption);
			softAssert.assertEquals(selectedActiveOption, productsPage.sortOptionsArrayList().get(i));
//			if (selectedActiveOption.equals(productsPage.sortOptionsArrayList().get(0))) { //A->Z
			
			ArrayList<Double> doubleArrayList = productsPage.createArrayListTypeDoubleFromMap(productMap.productPriceMap);
				ArrayList<String> arrayList = productsPage.testSwitch(i, productsPage.testSwitch2(i), doubleArrayList);
				int arrayListSize = arrayList.size();
				for (int j = 0; j < arrayListSize; j++) {
					System.out.println(productsPage.testSwitch3(i).get(j).getText());
					System.out.println(arrayList.get(j));
					softAssert.assertEquals(productsPage.testSwitch3(i).get(j).getText(), arrayList.get(j));
				}
				System.out.println("\n");
			
//			productsPage.testTest(i);
				
//			if (i == 0 || i == 1) {
//				ArrayList<String> sortedArrayList = null;
//				if (i == 0) {
//					sortedArrayList = productsPage.sortListAscendingOrder(productMap.productNameMap);
//				} else if (i == 1) {
//					sortedArrayList = productsPage.sortListDescendingOrder(productMap.productNameMap);
//				}
//				int arrayListSize = sortedArrayList.size();
//				for (int j = 0; j < arrayListSize; j++) {
//					System.out.println(productsPage.productNameElements.get(j).getText());
//					System.out.println(sortedArrayList.get(j));
//					softAssert.assertEquals(productsPage.productNameElements.get(j).getText(), sortedArrayList.get(j));
//				}
//				System.out.println("\n");
//			} else if (i == 2 || i == 3) {
//				ArrayList<Double> doubleArrayList = productsPage.createArrayListTypeDoubleFromMap(productMap.productPriceMap);
//				ArrayList<Double> sortedArrayList = null;
//				if (i == 2) {
//					sortedArrayList = productsPage.sortListAscendingOrder(doubleArrayList);
//				} else if (i == 3) {
//					sortedArrayList = productsPage.sortListDescendingOrder(doubleArrayList);
//				}
////				for (Double teString : sortedArrayList) {
////					System.out.println(teString);
////				}
//				int arrayListSize = sortedArrayList.size();
//				for (int j = 0; j < arrayListSize; j++) {
//					String listOfElements = productsPage.productPriceElements.get(j).getText().replace("$", "");
//					Double convertListToDouble = Double.parseDouble(listOfElements);
//					System.out.println("testListOfElements = " + convertListToDouble);
//					System.out.println("testArrayListOfPrices = " + sortedArrayList.get(j));
//					softAssert.assertEquals(convertListToDouble, sortedArrayList.get(j));
//				}
//				System.out.println("\n");
//			}
		}
		softAssert.assertAll();
	}
}
