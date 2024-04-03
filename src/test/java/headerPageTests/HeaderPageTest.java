package headerPageTests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import pages.headerPage.HeaderPage;
import util.SeleniumWebDriver;

@Feature ("Swag Labs - Header Bar")
public class HeaderPageTest extends SeleniumWebDriver {
	HeaderPage headerPage;
	@BeforeMethod (alwaysRun = true)
	protected void testInitiation(){
		//init
		headerPage = new HeaderPage(driver);
        // Login
        loginPage.login(userName,passWord);
    }
	
	@Test(priority = 1, description = "Verify the Primary Header", groups = {"All", "Sanity", "headerSanity"})
	@Story ("Verify the header bar")
	@Description ("The header bar contains all the required elements")
	public void verifyHeaderBarTest() {
		softAssert.assertTrue(headerPage.isHeaderLogoPresent());
		softAssert.assertTrue(headerPage.isHamburgerMenuButtonPresent());
		softAssert.assertTrue(headerPage.isShoppingCartPresent());
		softAssert.assertTrue(headerPage.isSecondaryHeaderTitlePresent());
		softAssert.assertTrue(headerPage.isSecondaryHeaderFilterPresent());
		softAssert.assertAll();
	}
}
