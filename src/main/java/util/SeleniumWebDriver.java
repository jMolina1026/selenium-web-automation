package util;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;

//import org.openqa.selenium.Dimension;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.bidi.script.Message;
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
    
//	String browserDriver = System.getProperty("driverName");
//	String runType = System.getProperty("headedOrHeadless");
//
//    public String userName = System.getProperty("userName");
//    public String passWord = System.getProperty("passWord");
    
    public String getPropertyKey(String key) {
    	return propertyReader.getValue(key);
    }
    
	String browserDriver = System.getProperty(getPropertyKey(PropertyKey.DRIVER_NAME));
	String runType = System.getProperty(getPropertyKey(PropertyKey.HEADED_OR_HEADLESS));
	String userAgentChromeVersion = System.getProperty(PropertyKey.USER_AGENT_CHROME_VERSION);

    public String userName = System.getProperty(getPropertyKey(PropertyKey.USERNAME));
    public String passWord = System.getProperty(getPropertyKey(PropertyKey.PASSWORD));

    @BeforeMethod (alwaysRun = true)
    public void init() throws Exception{
        switch(browserDriver){
            case "fireFoxDriver":
                driver = new FirefoxDriver();
                browserType = "fireFox";
                break;
            case "chromeDriver":
            	chromeOptions.addArguments(getPropertyKey(PropertyKey.WINDOW_SIZE));
            	if (runType.equals(getPropertyKey(PropertyKey.RUN_HEADELESS_MODE))) {
                	chromeOptions.addArguments(runType);
                	chromeOptions.addArguments(getPropertyKey(PropertyKey.USER_AGENT_CHROME).concat(userAgentChromeVersion));
                    driver = new ChromeDriver(chromeOptions);
            	} else if (runType.equals(getPropertyKey(PropertyKey.RUN_HEADED_MODE))){
            		driver = new ChromeDriver(chromeOptions);
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

        driver.get(getPropertyKey(PropertyKey.URL));

//        driver.manage().window().maximize();
//        if (browserType.equalsIgnoreCase("chrome")) {
//            driver.manage().window().setSize(new Dimension(1680,1050));;
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
    
    /**
     * Wait for an specific amount of seconds
     * @param seconds - time desired to wait
     */
    public void waitSeconds(double seconds){
        try {
            int sleepTime = (int) seconds * 1000;
            TimeUnit.MILLISECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public String getUserAgent() {
		String userAgentString = (String) ((JavascriptExecutor) driver).executeScript("return navigator.userAgent;");
//		System.out.println(userAgentString);
		return userAgentString;
	}

	public String replaceUserAgent() {
		String newUserAgent = getUserAgent().replace("Headless", "");
//		System.out.println(newUserAgent);
		return newUserAgent;
	}

    @AfterMethod (alwaysRun = true)
    public void afterMethod() {
    	takeScreenshot();
    	waitSeconds(3);
		System.out.println("The test is now done");
		replaceUserAgent();
    	if (driver != null) {
            driver.quit();
        }
    }


}
