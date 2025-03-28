package finishPurchaseTests;

import java.text.DecimalFormat;
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

public class FinishPurchaseTest extends SeleniumWebDriver{
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
	}
	
	@Test(priority = 1, description = "Verfiy the Final Purchase Page", groups = {"All", "Sanity", "finalPurchaseSanity"})
	@Story ("Verify the Final Purchase Page")
	@Description ("The Final Purchase page contains the fields for final overview before checking out")
	public void verifyFinalPurchasePageTest() {
		softAssert.assertTrue(finishPurchasePage.isFinishPurchasePageElementPresent(finishPurchasePage.finishPurchaseTitleElement));
		softAssert.assertEquals(finishPurchasePage.getFinishPurchaseElementsText(finishPurchasePage.finishPurchaseTitleElement), "Checkout: Overview");
		
		softAssert.assertTrue(finishPurchasePage.isFinishPurchasePageElementPresent(finishPurchasePage.quantityElement));
		softAssert.assertEquals(finishPurchasePage.getFinishPurchaseElementsText(finishPurchasePage.quantityElement), "QTY");
		
		softAssert.assertTrue(finishPurchasePage.isFinishPurchasePageElementPresent(finishPurchasePage.descriptionElement));
		softAssert.assertEquals(finishPurchasePage.getFinishPurchaseElementsText(finishPurchasePage.descriptionElement), "Description");
		
		for (int i = 0; i < productsPageArrayList.size(); i++) {
			for (int j = 0; j < productsPageArrayList.size(); j++) {
				softAssert.assertTrue(finishPurchasePage.isFinishPurchasePageElementPresent(finishPurchasePage.getFinishPurchaseElementsList(i).get(j)));
				softAssert.assertEquals(finishPurchasePage.getFinishPurchaseElementsText(finishPurchasePage.getFinishPurchaseElementsList(i).get(j)), productsPageArrayList.get(i).get(j));
			}
		}
		
		Double subTotalPrice = finishPurchasePage.getSubTotalPrice();
		Double taxDouble = subTotalPrice * 0.08;
		DecimalFormat formatter = new DecimalFormat("#0.00");
		String tax = formatter.format(taxDouble);
		Double totalPrice = subTotalPrice + Double.parseDouble(tax);
		for (int i = 0; i < finishPurchasePage.summaryInfoElements.size(); i++) {
			softAssert.assertEquals(finishPurchasePage.getFinishPurchaseElementsText(finishPurchasePage.summaryInfoElements.get(i)), 
					finishPurchasePage.listOfFinishPurchaseAssertText(i, subTotalPrice, Double.parseDouble(tax), totalPrice));
		}	
		softAssert.assertAll();
	}
	
	@Test(priority = 2, description = "Verfiy the Final Purchase Page and cancel the purchase", groups = {"All", "Regression", "finalPurchaseRegression"})
	@Story ("Verify the Final Purchase Page")
	@Description ("The Final Purchase page contains the fields for canceling the purchase")
	public void verifyCancelFinalPurchaseTest() {
		finishPurchasePage.clickFinishPurchaseCancelButton();
		finishPurchasePage.waitExplicitlyForElement(productsPage.sortContainerElement);
		softAssert.assertEquals(productsPage.getProductPageElementsText(productsPage.productPageTitleElement), "Products");
		softAssert.assertAll();
	}
	
	@Test(priority = 3, description = "Verfiy the Final Purchase Page and finish the purchase", groups = {"All", "Regression", "finalPurchaseRegression"})
	@Story ("Verify the Final Purchase Page")
	@Description ("The Final Purchase page contains the fields for completing the purchase")
	public void verifyCompleteCheckoutTest() {
		finishPurchasePage.clickFinishPurchaseFinishButton();
		finishPurchasePage.waitExplicitlyForElement(checkoutCompletePage.thanksForOrderElement);
		softAssert.assertEquals(checkoutCompletePage.getCheckoutCompleteElementsText(checkoutCompletePage.checkoutCompleteTitlElement), "Checkout: Complete!");
		softAssert.assertAll();
	}
}
