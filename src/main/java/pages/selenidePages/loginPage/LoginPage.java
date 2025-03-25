package pages.selenidePages.loginPage;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

  public final SelenideElement emailField = $("input#user-name");
  private final SelenideElement passwordField = $("input#password");
  private final SelenideElement loginButton = $("input#login-button");
  private final SelenideElement headerLogo = $("div.app_logo");
  private final SelenideElement errorMessageBox = $("h3[data-test=\"error\"]");
  private final SelenideElement loginLogo = $("div.login_logo");
  private final SelenideElement loginCredentials = $("div#login_credentials");
  private final SelenideElement loginPassword = $("div.login_password");

  public LoginPage enterUserName(String userName) {
    emailField.setValue(userName);
    return this;
  }

  public LoginPage enterPassword(String password) {
    passwordField.setValue(password);
    return this;
  }

  public LoginPage clickLoginButton() {
    loginButton.click();
    return this;
  }

  public String getHeaderLogoText() {
    return headerLogo.shouldBe(visible).getText();
  }

  public LoginPage verifyLoginSuccess() {
    headerLogo.shouldBe(visible).shouldHave(text("sdf"));
    return this;
  }
}
