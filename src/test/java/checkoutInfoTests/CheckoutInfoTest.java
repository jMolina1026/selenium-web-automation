package checkoutInfoTests;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import pages.checkoutInfoPage.CheckoutInfoPage;
import pages.checkoutPage.CheckoutPage;
import pages.finishPurchasePage.FinishPurchasePage;
import pages.headerPage.HeaderPage;
import productsPage.ProductsPage;
import util.SeleniumWebDriver;

public class CheckoutInfoTest extends SeleniumWebDriver {
	CheckoutPage checkoutPage;
	HeaderPage headerPage;
	ProductsPage productsPage;
	CheckoutInfoPage checkoutInfoPage;
	FinishPurchasePage finishPurchasePage;
	@BeforeMethod (alwaysRun = true)
	protected void testInitiation() {
		//init 
		checkoutPage = new CheckoutPage(driver);
		headerPage = new HeaderPage(driver);
		productsPage = new ProductsPage(driver);
		checkoutInfoPage = new CheckoutInfoPage(driver);
		finishPurchasePage = new FinishPurchasePage(driver);
		//login
		loginPage.login(userName, passWord);
		
		// intial steps
		int countOfClicks = 2;
		int randomIndex = 0;
		for (int i = 0; i < countOfClicks; i++) {
			List<WebElement> productATCElementsList = productsPage.productAddToCartButtonsElements;
			int elementsLength = productATCElementsList.size() - 1;
			randomIndex = checkoutPage.getRandomNumber(0, elementsLength);
			productsPage.clickAddToCartButton(randomIndex);
		}
		
		headerPage.clickShoppingCartButton();
		checkoutPage.waitExplicitlyForElement(checkoutPage.yourCartElement);
		checkoutPage.clickCheckoutButton(checkoutPage.checkoutButtonElement);
		checkoutPage.waitExplicitlyForElement(checkoutInfoPage.firstNameFieldElement);
	}
	
	@Test(priority = 1, description = "Verfiy the Checkout Info Page", groups = {"All", "Sanity", "checkoutInfoSanity"})
	@Story ("Verify the Checkout Info Page")
	@Description ("The checkout Info page contains fields to add personal info")
	public void verifyCheckoutInfoPageTest() {
		softAssert.assertTrue(checkoutInfoPage.isCheckoutInfoPageElementPresent(checkoutInfoPage.checkoutYourInfoTitleElement));
		softAssert.assertEquals(checkoutInfoPage.getCheckoutInfoElementsText(checkoutInfoPage.checkoutYourInfoTitleElement), "Checkout: Your Information");
		
		softAssert.assertTrue(checkoutInfoPage.isCheckoutInfoPageElementPresent(checkoutInfoPage.firstNameFieldElement));
		softAssert.assertEquals(checkoutInfoPage.getPersonalInfoAttribute(checkoutInfoPage.firstNameFieldElement, "placeholder"), "First Name");
		
		softAssert.assertTrue(checkoutInfoPage.isCheckoutInfoPageElementPresent(checkoutInfoPage.lastNameElement));
		softAssert.assertEquals(checkoutInfoPage.getPersonalInfoAttribute(checkoutInfoPage.lastNameElement, "placeholder"), "Last Name");
		
		softAssert.assertTrue(checkoutInfoPage.isCheckoutInfoPageElementPresent(checkoutInfoPage.postalCodeElement));
		softAssert.assertEquals(checkoutInfoPage.getPersonalInfoAttribute(checkoutInfoPage.postalCodeElement, "placeholder"), "Zip/Postal Code");
		
		softAssert.assertTrue(checkoutInfoPage.isCheckoutInfoPageElementPresent(checkoutInfoPage.cancelOrderElement));
		softAssert.assertEquals(checkoutInfoPage.getCheckoutInfoElementsText(checkoutInfoPage.cancelOrderElement), "Cancel");
		
		softAssert.assertTrue(checkoutInfoPage.isCheckoutInfoPageElementPresent(checkoutInfoPage.continueOrderElement));
		softAssert.assertEquals(checkoutInfoPage.getPersonalInfoAttribute(checkoutInfoPage.continueOrderElement, "value"), "Continue");
				
		softAssert.assertAll();
	}
	
	@Test(priority = 2, description = "Verfiy the Checkout Info Page and fill out the fields", groups = {"All", "Regression", "checkoutInfoRegression"})
	@Story ("Verify the Checkout Info Page")
	@Description ("The checkout Info page contains fields to add personal info")
	public void verifyCheckoutInfoFieldsTest() {
		checkoutInfoPage.typeTextIntoCheckoutInfoField(checkoutInfoPage.firstNameFieldElement, "John");
		checkoutInfoPage.typeTextIntoCheckoutInfoField(checkoutInfoPage.lastNameElement, "Tester");
		checkoutInfoPage.typeTextIntoCheckoutInfoField(checkoutInfoPage.postalCodeElement, "91245");
		checkoutInfoPage.clickCheckoutInfoContinueButton();
		softAssert.assertEquals(finishPurchasePage.getFinishPurchaseElementsText(finishPurchasePage.finishPurchaseTitleElement), "Checkout: Overview");
		softAssert.assertAll();
	}

}
