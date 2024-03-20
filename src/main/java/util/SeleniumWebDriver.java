package util;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
//import org.testng.asserts.SoftAssert;

import pages.LoginPage;

public abstract class SeleniumWebDriver {
	public WebDriver driver;
    protected LoginPage loginPage;
    protected PropertyReader propertyReader = new PropertyReader(PropertyKey.CONFIG);
    protected String browserType;
//    protected SoftAssert softAssertion = new SoftAssert();

    public String userName = propertyReader.getValue(PropertyKey.USERNAME);
    public String passWord = propertyReader.getValue(PropertyKey.PASSWORD);



    @BeforeMethod
    public void init(){
        switch(propertyReader.getValue(PropertyKey.BROWSER_DRIVER)){
            case "fireFoxDriver":
                driver = new FirefoxDriver();
                browserType = "fireFox";
                break;
            case "chromeDriver":
                driver = new ChromeDriver();
                browserType = "chrome";
                break;
            case "safariDriver":
                driver = new SafariDriver();
                browserType = "safari";
                break;

        }
        
        loginPage = new LoginPage(driver);

        driver.get(propertyReader.getValue(PropertyKey.URL));
        driver.manage().window().maximize();

//        if (browserType.equalsIgnoreCase("chrome")) {
//            driver.manage().window().setSize(new Dimension(1680, 1050));
//        } else {
//            driver.manage().window().maximize();
//        }


    }

    @AfterMethod
    public void afterMethod() {
        if (driver != null) {
            driver.quit();
        }
    }


}
