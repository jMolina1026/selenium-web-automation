package hamburgerMenuTests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import pages.hamburgerMenuPage.HamburgerMenuPage;
import pages.hamburgerMenuPage.HamburgerMenuPageFinalVariables;
import util.SeleniumWebDriver;

@Feature ("Swag Labs Hamburger Menu")
public class HamburgerMenuTest extends SeleniumWebDriver implements HamburgerMenuPageFinalVariables{
	HamburgerMenuPage hamburgerMenuPage;
	@BeforeMethod (alwaysRun = true)
	protected void testInitiation(){
		//init
		hamburgerMenuPage = new HamburgerMenuPage(driver);
        // Login
        loginPage.login(userName,passWord);
    }
	
	@Test(priority = 1, description = "Verify the Hamburger Menu Options", groups = {"All", "Sanity", "burgerMenuSanity"})
	@Story ("Verify the Hamburger Menu")
	@Description ("The Hamburger Menu contains all the required elements")
	public void verifyHamburgerMenuTest() {
		hamburgerMenuPage.clickHamburgerMenuButton();
		softAssert.assertTrue(hamburgerMenuPage.isHamburgerMenuItemPresent(0), ALL_ITEMS_ASSERTION_TEXT);
		softAssert.assertEquals(hamburgerMenuPage.getBurgerMenuItemsText(0), SWAG_LABS_BURGER_MENU_ALL_ITEMS);
		
		softAssert.assertTrue(hamburgerMenuPage.isHamburgerMenuItemPresent(1), ABOUT_ASSERTION_TEXT);
		softAssert.assertEquals(hamburgerMenuPage.getBurgerMenuItemsText(1), SWAG_LABS_BURGER_MENU_ABOUT);
		
		softAssert.assertTrue(hamburgerMenuPage.isHamburgerMenuItemPresent(2), LOGOUT_ASSERTION_TEXT);
		softAssert.assertEquals(hamburgerMenuPage.getBurgerMenuItemsText(2), SWAG_LABS_BURGER_MENU_LOGOUT);
		
		softAssert.assertTrue(hamburgerMenuPage.isHamburgerMenuItemPresent(3), RESET_ASSERTION_TEXT);
		softAssert.assertEquals(hamburgerMenuPage.getBurgerMenuItemsText(3), SWAG_LAB_BURGER_MENU_RESET);
		softAssert.assertAll();
	}
	
	@Test(priority = 2, description = "Verify that the Hamburger Menu Option 'All Items' takes the user to the home page", groups = {"All", "Regression", "burgerMenuRegression"})
	@Story ("Verify the Hamburger Menu")
	@Description ("The Hamburger Menu option 'All Items' will take the user to the home page")
	public void verifyHM_AllItemsTest() {
		hamburgerMenuPage.clickSwagLabsBackpackImgButton();
		hamburgerMenuPage.clickHamburgerMenuButton();
		hamburgerMenuPage.clickBurgerMenuOptionButton(hamburgerMenuPage.burgerMenuItemsListElements.get(0), hamburgerMenuPage.sauceLabsBikeLightImgElement);
		softAssert.assertFalse(hamburgerMenuPage.isHamburgerMenuItemNotPresent(0), ALL_ITEMS_IS_VISIBLE_ASSERTION_TEXT);
		softAssert.assertAll();
	}
	
	@Test(priority = 3, description = "Verify that the Hamburger Menu Option 'About' takes the about page for the site", groups = {"All", "Regression", "burgerMenuRegression"})
	@Story ("Verify the Hamburger Menu")
	@Description ("The Hamburger Menu option 'About' will take the user to a new page")
	public void verifyHM_AboutTest() {
		hamburgerMenuPage.clickHamburgerMenuButton();
		hamburgerMenuPage.clickBurgerMenuOptionButton(hamburgerMenuPage.burgerMenuItemsListElements.get(1), hamburgerMenuPage.sauceLabsLogoElement);
		softAssert.assertEquals(hamburgerMenuPage.getCurrentPageSiteUrl(), ABOUT_NEW_SITE_URL_ASSERTION_TEXT);
		softAssert.assertAll();
	}
	
	@Test(priority = 4, description = "Verify that the Hamburger Menu Option 'Logout' logs the user out of the site", groups = {"All", "Regression", "burgerMenuRegression"})
	@Story ("Verify the Hamburger Menu")
	@Description ("The Hamburger Menu option 'Logout' will log the user out of the site")
	public void verifyHM_LogoutTest() {
		hamburgerMenuPage.clickHamburgerMenuButton();
		hamburgerMenuPage.clickBurgerMenuOptionButton(hamburgerMenuPage.burgerMenuItemsListElements.get(2), hamburgerMenuPage.loginLogo);
		softAssert.assertEquals(hamburgerMenuPage.getLoginLogoText(), LOGOUT_LOGO_ASSERTION_TEXT);
		softAssert.assertAll();
	}
	
	@Test(priority = 5, description = "Verify that the Hamburger Menu Option 'Reset App State' resets the site", groups = {"All", "Regression", "burgerMenuRegression"})
	@Story ("Verify the Hamburger Menu")
	@Description ("The Hamburger Menu option 'Reset App State' will reset the site")
	public void verifyHM_ResetTest() {
		hamburgerMenuPage.clickAddToCartButton();
		hamburgerMenuPage.clickHamburgerMenuButton();
		hamburgerMenuPage.clickBurgerMenuOptionButton(hamburgerMenuPage.burgerMenuItemsListElements.get(3), hamburgerMenuPage.burgerMenuElement);
		softAssert.assertFalse(hamburgerMenuPage.doesElementExist(hamburgerMenuPage.badgeListElements), RESET_NO_BADGE_ASSERTION_TEXT);
		softAssert.assertAll();
	}
}
