package util;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
	protected ChromeOptions chromeOptions = new ChromeOptions();
    protected String browserType;
    protected SoftAssert softAssert;
    public Assert hardAssert;
    
	String browserDriver = System.getProperty(propertyReader.getValue(PropertyKey.DRIVER_NAME));
	String runType = System.getProperty(propertyReader.getValue(PropertyKey.HEADED_OR_HEADLESS));

    public String userName = System.getProperty(propertyReader.getValue(PropertyKey.USERNAME));
    public String passWord = System.getProperty(propertyReader.getValue(PropertyKey.PASSWORD));

    @BeforeMethod (alwaysRun = true)
    public void init() throws Exception{
        switch(browserDriver){
            case "fireFoxDriver":
                driver = new FirefoxDriver();
                browserType = "fireFox";
                break;
            case "chromeDriver":
            	if (runType.equals(propertyReader.getValue(PropertyKey.RUN_HEADELESS_MODE))) {
                	chromeOptions.addArguments(runType);
                    driver = new ChromeDriver(chromeOptions);
            	} else if (runType.equals(propertyReader.getValue(PropertyKey.RUN_HEADED_MODE))){
                	driver = new ChromeDriver();
            	}
                browserType = "chrome";
                break;
            case "safariDriver":
                driver = new SafariDriver();
                browserType = "safari";
                break;
			default:
				throw new Exception("None of the programmed or available drivers are being used, check that -Dbrowser has the correct value assigned");
        }
        
        loginPage = new LoginPage(driver);
        softAssert = new SoftAssert();

        driver.get(propertyReader.getValue(PropertyKey.URL));
        driver.manage().window().maximize();

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
