package productTests;

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
//		int sizeOfMap = productMap.getProductNameMapSize();
//		System.out.println("Size of the Products Map = " + sizeOfMap);
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
		}
		softAssert.assertAll();
	}
	
	@Test(priority = 2, description = "Verfiy the Product Page V2", groups = {"All", "SanityV2", "productSanityV2"})
	@Story ("Verify the Product Page - V2")
	@Description ("The product page contains all available items for sale - V2")
	public void verifyProductPageV2Test() {
		productsPage.areTheProductPageElementsPresent();
	}
}
