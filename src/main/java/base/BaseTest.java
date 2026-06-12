package base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import utils.BrowserFactory;
import utils.ScreenshotUtils;

/**
 * BaseTest class - Base class for all test classes.
 * Provides WebDriver setup and teardown.
 * Uses TestListener (ITestListener) for test execution monitoring and reporting.
 * 
 * Test Listener Features:
 * ✓ Logs test start, pass, fail, skip events
 * ✓ Captures screenshots on test failures
 * ✓ Provides detailed test execution reports
 * ✓ Tracks test duration and success rates
 */
@Listeners(TestListener.class)
public class BaseTest {

    protected WebDriver driver;

    /**
     * Initializes WebDriver using BrowserFactory.
     * Default browser is Chrome. Can be overridden by setting "browser" system property.
     * 
     * Example: mvn test -Dbrowser=firefox
     */
    @BeforeMethod
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        driver = BrowserFactory.createDriver(browser);
    }

    /**
     * Tears down the WebDriver instance after each test.
     * Captures screenshot on test failure.
     */
    @AfterMethod
    public void tearDown(ITestResult result) {
        // Capture screenshot if test failed
        if (result.getStatus() == ITestResult.FAILURE) {
            ScreenshotUtils.takeScreenshot(driver, result.getTestName());
            System.out.println("✓ Screenshot captured for failed test: " + result.getTestName());
        }
        
        // Quit the driver
        BrowserFactory.quitDriver(driver);
    }

    /**
     * Getter method to access WebDriver instance.
     * Used by TestListener to capture screenshots on failures.
     *
     * @return WebDriver instance
     */
    public WebDriver getDriver() {
        return driver;
    }
}
