package footerTests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import pages.footerPage.FooterPage;
import pages.footerPage.FooterPageFinalVariables;
import util.SeleniumWebDriver;

@Feature ("Swag Labs - Footer Field")
public class FooterTest extends SeleniumWebDriver implements FooterPageFinalVariables{
	FooterPage footerPage;
	@BeforeMethod (alwaysRun = true)
	protected void testInitiation(){
		//init
		footerPage = new FooterPage(driver);
        // Login
        loginPage.login(userName,passWord);
    }
	
	@Test(priority = 1, description = "Verify the Footer Elements", groups = {"All", "Sanity", "FooterSanity"})
	@Story ("Verify the Footer Fields and Elements")
	@Description ("The Footer field contains all the required elements")
	public void verifyFooterFieldTest() {
		softAssert.assertTrue(footerPage.isFooterElementPresent(footerPage.footerFieldElement), SWAG_LABS_FOOTER_FIELD);
		
		softAssert.assertTrue(footerPage.isFooterElementPresent(footerPage.footerSocialMediaListElements.get(0)), SWAG_LABS_TWITTER_ICON);
		softAssert.assertEquals(footerPage.getFooterElementsText(footerPage.footerSocialMediaListElements.get(0)), TWITTER_ASSERTION_TEXT);
		
		softAssert.assertTrue(footerPage.isFooterElementPresent(footerPage.footerSocialMediaListElements.get(1)), SWAG_LABS_FACEBOOK_ICON);
		softAssert.assertEquals(footerPage.getFooterElementsText(footerPage.footerSocialMediaListElements.get(1)), FACEBOOK_ASSERTION_TEXT);
		
		softAssert.assertTrue(footerPage.isFooterElementPresent(footerPage.footerSocialMediaListElements.get(2)), SWAG_LABS_LINKEDIN_ICON);
		softAssert.assertEquals(footerPage.getFooterElementsText(footerPage.footerSocialMediaListElements.get(2)), LINKEDIN_ASSERTION_TEXT);
		
		softAssert.assertTrue(footerPage.isFooterElementPresent(footerPage.footerCopyRightElement), SWAG_LABS_COPY_RIGHT);
		softAssert.assertEquals(footerPage.getFooterElementsText(footerPage.footerCopyRightElement), COPY_RIGHT_ASSERTION_TEXT);
		softAssert.assertAll();
	}
	
	@Test(priority = 2, description = "Verify the Twitter/X icon footer element", groups = {"All", "Regression", "FooterRegression"})
	@Story ("Verify the footer Social Media Icons and Copy Right")
	@Description ("The Twitter/X icon is displayed and navigates the user to the Saucelabs twitter account")
	public void verifyTwitterXIconTest() {
		footerPage.clickFooterSocialMediaElement(footerPage.footerSocialMediaListElements.get(0), footerPage.twitterXLogoElement);
		softAssert.assertEquals(footerPage.getCurrentPageUrl(), SAUCE_LABS_TWITTER_URL_ASSERTION_TEXT);
		softAssert.assertAll();
	}
	
	@Test(priority = 3, description = "Verify the Facebook icon footer element", groups = {"All", "Regression", "FooterRegression"})
	@Story ("Verify the footer Social Media Icons and Copy Right")
	@Description ("The Facebook icon is displayed and navigates the user to the Saucelabs facebook account")
	public void verifyFacebookIconTest() {
		footerPage.clickFooterSocialMediaElement(footerPage.footerSocialMediaListElements.get(1), footerPage.facebookLogoElement);
		softAssert.assertEquals(footerPage.getCurrentPageUrl(), SAUCE_LABS_FACEBOOK_URL_ASSERTION_TEXT);
		softAssert.assertAll();
	}
	
	@Test(priority = 4, description = "Verify the LinkedIn icon footer element", groups = {"All", "Regression", "FooterRegression"})
	@Story ("Verify the footer Social Media Icons and Copy Right")
	@Description ("The LinkedIn icon is displayed and navigates the user to the Saucelabs LinkedIn account")
	public void verifyLinkedInIconTest() {
		footerPage.clickFooterSocialMediaElement(footerPage.footerSocialMediaListElements.get(2), footerPage.linkedInLogoElement);
		softAssert.assertEquals(footerPage.getCurrentPageUrl(), SAUCE_LABS_LINKEDIN_URL_ASSERTION_TEXT);
		softAssert.assertAll();
	}
}
