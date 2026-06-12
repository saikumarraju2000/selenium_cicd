package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.time.Duration;

/**
 * BrowserFactory class to manage WebDriver creation and configuration.
 * This factory supports multiple browsers: Chrome, Firefox, and Edge.
 */
public class BrowserFactory {

    private static final long IMPLICIT_WAIT = 10;  // seconds
    private static final long PAGE_LOAD_TIMEOUT = 30;  // seconds

    /**
     * Creates and returns a WebDriver instance based on browser type.
     *
     * @param browser Browser type (chrome, firefox, edge)
     * @return WebDriver instance
     */
    public static WebDriver createDriver(String browser) {
        WebDriver driver;
        String browserType = browser.toLowerCase();

        System.out.println("========== Creating WebDriver for browser: " + browserType + " ==========");

        try {
            switch (browserType) {
                case "chrome":
                    System.out.println("Initializing Chrome driver...");
                    driver = createChromeDriver();
                    System.out.println("✓ Chrome driver created successfully");
                    break;
                case "firefox":
                    System.out.println("Initializing Firefox driver...");
                    driver = createFirefoxDriver();
                    System.out.println("✓ Firefox driver created successfully");
                    break;
                case "edge":
                    System.out.println("Initializing Edge driver...");
                    driver = createEdgeDriver();
                    System.out.println("✓ Edge driver created successfully");
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

            configureDriver(driver);
            System.out.println("✓ WebDriver configured successfully\n");
            return driver;
        } catch (Exception e) {
            System.err.println("✗ Error creating WebDriver for " + browserType);
            System.err.println("Exception: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to create WebDriver for " + browserType, e);
        }
    }

    /**
     * Creates and returns a Chrome WebDriver with predefined options.
     *
     * @return Chrome WebDriver
     */
    private static WebDriver createChromeDriver() {
        System.out.println("  > Setting up ChromeDriver...");
        WebDriverManager.chromedriver().setup();
        System.out.println("  > ChromeDriver setup complete");
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);
        
        // Add platform-specific arguments for Linux
        if (isLinux()) {
            options.addArguments("no-sandbox");
            options.addArguments("disable-dev-shm-usage");
        }
        
        System.out.println("  > Creating ChromeDriver instance...");
        return new ChromeDriver(options);
    }

    /**
     * Creates and returns a Firefox WebDriver with predefined options.
     *
     * @return Firefox WebDriver
     */
    private static WebDriver createFirefoxDriver() {
        System.out.println("  > Setting up FirefoxDriver...");
        WebDriverManager.firefoxdriver().setup();
        System.out.println("  > FirefoxDriver setup complete");
        
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("start-maximized");
        
        System.out.println("  > Creating FirefoxDriver instance...");
        return new FirefoxDriver(options);
    }

    /**
     * Creates and returns an Edge WebDriver with predefined options.
     *
     * @return Edge WebDriver
     */
    private static WebDriver createEdgeDriver() {
        System.out.println("  > Setting up EdgeDriver...");
        System.out.println("  > OS: " + System.getProperty("os.name"));
        System.out.println("  > OS Architecture: " + System.getProperty("os.arch"));
        
        try {
            WebDriverManager.edgedriver().setup();
            System.out.println("  > EdgeDriver setup complete");
        } catch (Exception e) {
            System.err.println("  ✗ Error during EdgeDriver setup: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to setup EdgeDriver", e);
        }
        
        try {
            EdgeOptions options = new EdgeOptions();
            
            // Absolute minimal configuration for Edge
            options.addArguments("start-maximized");
            
            System.out.println("  > Creating EdgeDriver instance...");
            EdgeDriver driver = new EdgeDriver(options);
            System.out.println("  > EdgeDriver instance created successfully");
            return driver;
            
        } catch (Exception e) {
            System.err.println("  ✗ Error creating EdgeDriver instance: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to create EdgeDriver instance", e);
        }
    }

    /**
     * Checks if the current operating system is Linux.
     *
     * @return true if running on Linux, false otherwise
     */
    private static boolean isLinux() {
        return System.getProperty("os.name").toLowerCase().contains("linux");
    }

    /**
     * Configures WebDriver with implicit waits and page load timeouts.
     *
     * @param driver WebDriver instance to configure
     */
    private static void configureDriver(WebDriver driver) {
        System.out.println("  > Setting implicit wait: " + IMPLICIT_WAIT + " seconds");
        System.out.println("  > Setting page load timeout: " + PAGE_LOAD_TIMEOUT + " seconds");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_TIMEOUT));
    }

    /**
     * Quits/closes the WebDriver instance.
     *
     * @param driver WebDriver instance to quit
     */
    public static void quitDriver(WebDriver driver) {
        if (driver != null) {
            System.out.println("Closing WebDriver...");
            driver.quit();
            System.out.println("✓ WebDriver closed successfully");
        }
    }
}
