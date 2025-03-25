package selenideTests.loginTests;

import org.testng.annotations.Test;
import pages.selenidePages.loginPage.LoginPage;
import util.SeleniumWebDriver;

public class LoginTest extends SeleniumWebDriver {

  @Test (groups = "selenideLogin")
  public void loginTest2() {
    LoginPage loginPage = new LoginPage();
    loginPage.enterUserName(userName)
        .enterPassword(passWord)
        .clickLoginButton();
  }
}
