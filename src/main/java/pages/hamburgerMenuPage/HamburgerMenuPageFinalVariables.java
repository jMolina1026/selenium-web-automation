package pages.hamburgerMenuPage;

public interface HamburgerMenuPageFinalVariables {
	// Source of Truth Text
	String SWAG_LABS_BURGER_MENU_ALL_ITEMS = "All Items";
	String SWAG_LABS_BURGER_MENU_ABOUT = "About";
	String SWAG_LABS_BURGER_MENU_LOGOUT = "Logout"; 
	String SWAG_LAB_BURGER_MENU_RESET = "Reset App State";
	
	// Assertion text
	String ALL_ITEMS_ASSERTION_TEXT = "The 'All Items' option is not present";
	String ABOUT_ASSERTION_TEXT = "The 'About' option is not present";
	String LOGOUT_ASSERTION_TEXT = "The 'Logout' option is not present";
	String RESET_ASSERTION_TEXT = "The 'Reset' option is not present";
	String ALL_ITEMS_IS_VISIBLE_ASSERTION_TEXT = "The menu item 'All Items' is currently present.  The burger menu should have closed upon returning to the homepage";
	String ABOUT_NEW_SITE_URL_ASSERTION_TEXT = "https://saucelabs.com/";
	String LOGOUT_LOGO_ASSERTION_TEXT = "Swag Labs";
	String RESET_NO_BADGE_ASSERTION_TEXT = "The badge is still currently added to the shopping cart";
}
