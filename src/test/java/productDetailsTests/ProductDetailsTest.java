package productDetailsTests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import pages.productDetailsPage.ProductDetailsPage;
import productsPage.ProductsPage;
import productsPage.ProductsPageMap;
import util.SeleniumWebDriver;

public class ProductDetailsTest extends SeleniumWebDriver {
	ProductsPage productsPage;
	ProductDetailsPage productDetailsPage;
	ProductsPageMap productMap;
	@BeforeMethod (alwaysRun = true)
	protected void testInitiation() {
		//init 
		productsPage = new ProductsPage(driver);
		productDetailsPage = new ProductDetailsPage(driver);
		productMap = new ProductsPageMap();
		//login
		loginPage.login(userName, passWord);
	}
	
	@Test(priority = 1, description = "Verfiy the Product Details Page", groups = {"All", "Sanity", "productDetailsSanity"})
	@Story ("Verify the Product Details Page")
	@Description ("The product Details page contains the selected items details")
	public void verifyProductDetailsPageTest() {
		List<WebElement> productNameElementsList = productDetailsPage.productNameElements;
		int elementsLength = productNameElementsList.size() - 1;
		int randomIndex = productDetailsPage.getRandomNumber(0, elementsLength);
		String productsPageNameText = productsPage.getProductPageElementsText(productsPage.productNameElements.get(randomIndex));
		String productsPageDescText = productsPage.getProductPageElementsText(productsPage.productDescriptionElements.get(randomIndex)); 
		String productsPagePriceText = productsPage.getProductPageElementsText(productsPage.productPriceElements.get(randomIndex)); 
		productDetailsPage.clickProductTitleButton(productNameElementsList, randomIndex);
				
		softAssert.assertTrue(productDetailsPage.isProductDetailsElementPresent(productDetailsPage.backToProductsElement));
		softAssert.assertEquals(productDetailsPage.getProductDetailsElementsText(productDetailsPage.backToProductsElement), "Back to products");
		
		softAssert.assertTrue(productDetailsPage.isProductDetailsElementPresent(productDetailsPage.productImgElement));
		
		softAssert.assertTrue(productDetailsPage.isProductDetailsElementPresent(productDetailsPage.productNameElement));
		softAssert.assertEquals(productDetailsPage.getProductDetailsElementsText(productDetailsPage.productNameElement), productsPageNameText);
		
		softAssert.assertTrue(productDetailsPage.isProductDetailsElementPresent(productDetailsPage.productDescriptionElement));
		softAssert.assertEquals(productDetailsPage.getProductDetailsElementsText(productDetailsPage.productDescriptionElement), productsPageDescText);
		
		softAssert.assertTrue(productDetailsPage.isProductDetailsElementPresent(productDetailsPage.productPriceElement));
		softAssert.assertEquals(productDetailsPage.getProductDetailsElementsText(productDetailsPage.productPriceElement), productsPagePriceText);
		
		softAssert.assertTrue(productDetailsPage.isProductDetailsElementPresent(productDetailsPage.productAddToCartElement));
		softAssert.assertEquals(productDetailsPage.getProductDetailsElementsText(productDetailsPage.productAddToCartElement), "Add to cart");
		
		softAssert.assertAll();
	}
	
	@Test(priority = 2, description = "Verfiy the Product Details add to cart", groups = {"All", "Regression", "productDetailsRegression"})
	@Story ("Verify the Product Details Page")
	@Description ("The product Detail page contains an 'Add to Cart'buttons, user should be able to add or remove via this button")
	public void verifyProductDetailsAddToCartTest() {
		List<WebElement> productNameElementsList = productDetailsPage.productNameElements;
		int elementsLength = productNameElementsList.size() - 1;
		int randomIndex = productDetailsPage.getRandomNumber(0, elementsLength);
		String productsPageNameText = productsPage.getProductPageElementsText(productsPage.productNameElements.get(randomIndex));
		productDetailsPage.clickProductTitleButton(productNameElementsList, randomIndex);
				
		softAssert.assertTrue(productDetailsPage.isProductDetailsElementPresent(productDetailsPage.backToProductsElement));
		softAssert.assertEquals(productDetailsPage.getProductDetailsElementsText(productDetailsPage.backToProductsElement), "Back to products");
		
		softAssert.assertTrue(productDetailsPage.isProductDetailsElementPresent(productDetailsPage.productNameElement));
		softAssert.assertEquals(productDetailsPage.getProductDetailsElementsText(productDetailsPage.productNameElement), productsPageNameText);
		
		productDetailsPage.clickAddToCartButton(productDetailsPage.productAddToCartElement);
		softAssert.assertEquals(productDetailsPage.getProductDetailsElementsText(productDetailsPage.productRemoveFromCartElement), "Remove");
		softAssert.assertTrue(productDetailsPage.doesProductPageElementExist(productDetailsPage.shoppingCartBadgeElements));
		
		productDetailsPage.clickRemoveFromCartButton(productDetailsPage.productRemoveFromCartElement);
		softAssert.assertFalse(productDetailsPage.doesProductPageElementExist(productDetailsPage.shoppingCartBadgeElements));
		
		productDetailsPage.clickBackToProductsButton(productDetailsPage.backToProductsElement);
		softAssert.assertEquals(productsPage.getProductPageElementsText(productsPage.productPageTitleElement), "Products");
		
		softAssert.assertAll();
	}
}
