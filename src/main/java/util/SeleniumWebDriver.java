package util;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;

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

import pages.LoginPage.LoginPage;

public abstract class SeleniumWebDriver {
	public WebDriver driver;
    protected LoginPage loginPage;
    protected PropertyReader propertyReader = new PropertyReader(PropertyKey.CONFIG);
	protected ChromeOptions chromeOptions = new ChromeOptions();
    protected String browserType;
    protected SoftAssert softAssert;

    public String getPropertyKey(String key) {
    	return propertyReader.getValue(key);
    }
    
	String browserDriver = System.getProperty(PropertyKey.DRIVER_NAME);
	String runType = System.getProperty(PropertyKey.HEADED_OR_HEADLESS);
	String userAgentChromeVersion = System.getProperty(PropertyKey.USER_AGENT_CHROME_VERSION);

    public String userName = System.getProperty(PropertyKey.USERNAME);
    public String passWord = System.getProperty(PropertyKey.PASSWORD);

    @BeforeMethod (alwaysRun = true)
    public void init() throws Exception{
        switch(browserDriver){
            case "fireFoxDriver":
                driver = new FirefoxDriver();
                browserType = "fireFox";
                break;
            case "chromeDriver":
            	chromeOptions.addArguments(getPropertyKey(PropertyKey.WINDOW_SIZE));
            	if (runType.equals(getPropertyKey(PropertyKey.RUN_HEADLESS_MODE))) {
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
				throw new Exception("None of the programmed or available drivers are being used, check that -DdriverName has the correct value assigned");
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
     * Wait for a specific amount of seconds
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
        // System.out.println(userAgentString);
		return (String) ((JavascriptExecutor) driver).executeScript("return navigator.userAgent;");
	}

	public String replaceUserAgent() {
        // System.out.println(newUserAgent);
		return getUserAgent().replace("Headless", "");
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
