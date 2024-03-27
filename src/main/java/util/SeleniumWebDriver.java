package util;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import io.qameta.allure.Attachment;

import org.testng.Assert;

import pages.LoginPage.LoginPage;

public abstract class SeleniumWebDriver {
	public WebDriver driver;
    protected LoginPage loginPage;
    protected PropertyReader propertyReader = new PropertyReader(PropertyKey.CONFIG);
    protected String browserType;
    protected SoftAssert softAssert;
    public Assert hardAssert;

    public String userName = propertyReader.getValue(PropertyKey.USERNAME);
    public String passWord = propertyReader.getValue(PropertyKey.PASSWORD);



    @BeforeMethod (alwaysRun = true)
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
        softAssert = new SoftAssert();

        driver.get(propertyReader.getValue(PropertyKey.URL));
        driver.manage().window().maximize();

//        if (browserType.equalsIgnoreCase("chrome")) {
//            driver.manage().window().setSize(new Dimension(1680, 1050));
//        } else {
//            driver.manage().window().maximize();
//        }


    }
    
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }

    public void takeScreenshot() {
        final byte[] screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        saveScreenshot(screenShot);
    }

    @AfterMethod (alwaysRun = true)
    public void afterMethod() throws InterruptedException {
    	takeScreenshot();
    	Thread.sleep(3000);
		System.out.println("The test is now done");
    	if (driver != null) {
            driver.quit();
        }
    }


}
